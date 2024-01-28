package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class DriveSubsystem extends SubsystemBase {
    DcMotor FL, FR, BR, BL;
    IMU imu;
    double integralSum = 0;
    public static double Kp=1,Ki=.1,Kd=0.0001;
    ElapsedTime timer = new ElapsedTime();
    double target = 0, referenceAngle = 0, lastError=0;
    public static FtcDashboard dashboard;
    Telemetry telemetry;

    public DriveSubsystem(DcMotor FL, DcMotor FR, DcMotor BR, DcMotor BL, IMU imu) {
        this.BL = BL;
        this.FL = FL;
        this.FR = FR;
        this.BR = BR;
        this.imu = imu;

        BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BL.setDirection(DcMotorSimple.Direction.REVERSE);
        FL.setDirection(DcMotorSimple.Direction.REVERSE);
        BR.setDirection(DcMotorSimple.Direction.FORWARD);
        FR.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void setPower(double power) {
        WriteSubsystem.motorNewPower.put(FL, power);
        WriteSubsystem.motorNewPower.put(BL, power);
        WriteSubsystem.motorNewPower.put(FR, power);
        WriteSubsystem.motorNewPower.put(BR, power);
    }

    public void straighten(int target) {
        this.target = target;
    }

    public void teleDrive(Gamepad gamepad1) {
        double botHeading = ReadSubsystem.sensorValues.get(imu);

        referenceAngle = Math.toRadians(target);
        double y = -gamepad1.left_stick_y;; // Remember, Y stick value is reversed
        double x = 1.3 * gamepad1.left_stick_x;
        double rx;

        if(gamepad1.right_stick_x > 0.1 || gamepad1.right_stick_x < -.1){
            rx = gamepad1.right_stick_x;
            target = Math.toDegrees(botHeading);
            integralSum = 0;
        } else{
            rx = -PIDcontrol(referenceAngle, botHeading);
        }


        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;
        WriteSubsystem.motorNewPower.put(FL, frontLeftPower);
        WriteSubsystem.motorNewPower.put(BL, backLeftPower);
        WriteSubsystem.motorNewPower.put(FR, frontRightPower);
        WriteSubsystem.motorNewPower.put(BR, backRightPower);
    }

    public double angleWrap(double radians) {

        while (radians > Math.PI) {
            radians -= 2 * Math.PI;
        }
        while (radians < -Math.PI) {
            radians += 2 * Math.PI;
        }

        // keep in mind that the result is in radians
        return radians;
    }

    public double PIDcontrol(double reference, double state) {
        double error = angleWrap(reference - state);
        integralSum += error * timer.seconds();
        double derivative = (error - lastError) / timer.seconds();
        lastError = error;

        timer.reset();

        double output = (error * Kp) + (derivative * Kd) + (integralSum * Ki);
        return output;
    }

    public void reset() {
        imu.resetYaw();
        target = 0;
    }

    @Override
    public void periodic() {
//        dashboard = FtcDashboard.getInstance();
//        telemetry = dashboard.getTelemetry();
//        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
//        telemetry.addData("target", target);
//        telemetry.addData("current",ReadSubsystem.sensorValues.get(imu));
//        telemetry.update();
    }
}
