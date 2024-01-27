package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

import java.util.HashMap;

public class ReadSubsystem extends SubsystemBase {
    public static HashMap<DcMotor, Integer> encoderValues;
    public static HashMap<HardwareDevice, Double> sensorValues;
    public static HashMap<String, Double> updateTelemetry;
    DcMotorEx[] driveMotors;
    double current;
    private IMU imu;
    private Telemetry telemetry;

    public ReadSubsystem(DcMotor[] encoders, DcMotorEx[] drivemotors, IMU imu, Telemetry telemetry) {
        encoderValues = new HashMap();
        sensorValues = new HashMap<>();
        updateTelemetry = new HashMap<>();
        this.driveMotors = drivemotors;
        for(DcMotor m: encoders) {
            encoderValues.put(m, 0);
        }
        this.imu = imu;
        this.telemetry = telemetry;
    }

    @Override
    public void periodic() {
        for(DcMotor m: encoderValues.keySet()) {
            encoderValues.put(m, m.getCurrentPosition());
        }
        sensorValues.put(imu, imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));
        updateTelemetry.put("imu", sensorValues.get(imu));

        current = 0;
        for(DcMotorEx m: driveMotors) {
            current += m.getCurrent(CurrentUnit.AMPS);
        }
        telemetry.addData("Drive amps: ", current);

        for(String s: updateTelemetry.keySet()) {
            telemetry.addData(s, updateTelemetry.get(s));
        }
        updateTelemetry.clear();

        telemetry.update();
    }
}
