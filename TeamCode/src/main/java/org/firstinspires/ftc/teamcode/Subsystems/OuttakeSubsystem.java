package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@Config
public class OuttakeSubsystem extends SubsystemBase {
    DcMotor outSlideL, outSlideR;
    Servo outArmL, outArmR, outL, outR;
    private DigitalChannel outtakeLidarL, outtakeLidarR, backdropLidar;
    public static double p=0.003, i=0.2, d=0.000, f=0.00;
    public static final double armNeutral = .678, armOut = .328;
    private PIDFController slideController = new PIDFController(p,i,d,f);
    double slidePower = 0;

    public static double lDrop = .13, rDrop = .62, lClose = .50, rClose = .14;

    public static double slidePosition = 0;
    public static FtcDashboard dashboard;
    Gamepad gamepad2;
    Telemetry telemetry;
    public OuttakeSubsystem(DcMotor outSlideL, DcMotor outSlideR, Servo outArmL, Servo outArmR, Servo outL, Servo outR, Gamepad gamepad2, DigitalChannel outtakeLidarL, DigitalChannel outtakeLidarR, DigitalChannel backdropLidar) {
        this.outSlideL = outSlideL;
        this.outSlideR = outSlideR;
        this.outArmL = outArmL;
        this.outArmR = outArmR;
        this.outL = outL;
        this.outR = outR;
        this.gamepad2 = gamepad2;
        this.outtakeLidarL = outtakeLidarL;
        this.outtakeLidarR = outtakeLidarR;
        this.backdropLidar = backdropLidar;

        outArmL.setPosition(armNeutral);
        closeBoth();
    }

    public void setSlides(int position) {
        slidePosition = position;
    }

    public void incrementSlides(int add) {
        slidePosition = slidePosition + add;
    }

    public void stopSlides() {
        slidePower = 0.0;
    }

    public void powerSlides(double slidePower) {
        this.slidePower = slidePower;
    }

    public void setArm(double position) {
        WriteSubsystem.servoNewPosition.put(outArmL, position);
        WriteSubsystem.servoNewPosition.put(outArmR, position);
    }
    public double getArm() {
        return outArmL.getPosition();
    }

    public void dropL(){
        WriteSubsystem.servoNewPosition.put(outL, lDrop);
    }

    public void dropR() {
        WriteSubsystem.servoNewPosition.put(outR, rDrop);
    }

    public void dropBoth() {
        dropL();
        dropR();
    }

    public void closeBoth() {
        WriteSubsystem.servoNewPosition.put(outL, lClose);
        WriteSubsystem.servoNewPosition.put(outR, rClose);
    }

    public boolean getBackdropLidar() {
        return !backdropLidar.getState();
    }

    public boolean getPixelLidars() {
        return !outtakeLidarL.getState() && !outtakeLidarR.getState();
    }

    public boolean getLeftLidar() {
        return !outtakeLidarL.getState();
    }

    public boolean getRightLidar() {
        return !outtakeLidarR.getState();
    }

    public int getSlidePosition() {
        return ReadSubsystem.encoderValues.get(outSlideL);
    }

    @Override
    public void periodic() {
//        if (gamepad2.left_trigger > .1) {
//            slidePosition += 10 * gamepad2.left_stick_y;
//        }

        slideController = new PIDFController(p,i,d,f);
        slidePower = slideController.calculate(ReadSubsystem.encoderValues.get(outSlideL), slidePosition);
        WriteSubsystem.motorNewPower.put(outSlideL, slidePower);
        WriteSubsystem.motorNewPower.put(outSlideR, slidePower);

        dashboard = FtcDashboard.getInstance();
        telemetry = dashboard.getTelemetry();
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addData("slideTarget", slidePosition);
        telemetry.addData("currentSlide", ReadSubsystem.encoderValues.get(outSlideL));
        telemetry.addData("slidePoewr", slidePower);
        telemetry.addData("outL Amps", ((DcMotorEx) outSlideL).getCurrent(CurrentUnit.AMPS));

        telemetry.update();
    }
}
