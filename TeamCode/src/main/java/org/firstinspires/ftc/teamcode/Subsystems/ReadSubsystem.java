package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.HashMap;

public class ReadSubsystem extends SubsystemBase {
    public static HashMap<Motor, Integer> encoderValues;
    public static HashMap<HardwareDevice, Double> sensorValues;
    private IMU imu;

    public ReadSubsystem(Motor[] encoders, IMU imu) {
        for(Motor m: encoders) {
            encoderValues.put(m, 0);
        }
        this.imu = imu;
    }

    @Override
    public void periodic() {
        for(Motor m: encoderValues.keySet()) {
            encoderValues.put(m, m.getCurrentPosition());
        }
        sensorValues.put(imu, imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

    }
}
