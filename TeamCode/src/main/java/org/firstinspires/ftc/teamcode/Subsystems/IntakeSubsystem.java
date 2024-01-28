package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class IntakeSubsystem extends SubsystemBase {
    private Servo inArm, inWrist;
    public static double armNeutral = .043, wristNeutral = .218;
    public static double armDown = .843, wristDown = .976;
    private CRServo inSpin;
    private DigitalChannel intakeLidarL, intakeLidarR;
    public IntakeSubsystem(CRServo inSpin, Servo inArm, Servo inWrist, DigitalChannel intakeLidarL, DigitalChannel intakeLidarR) {
        this.inArm = inArm;
        this.inSpin = inSpin;
        this.inWrist = inWrist;
        this.intakeLidarL = intakeLidarL;
        this.intakeLidarR = intakeLidarR;
    }

    public void setArm(double position) {
        WriteSubsystem.servoNewPosition.put(inArm, position);
    }

    public void setWrist(double position) {
        WriteSubsystem.servoNewPosition.put(inWrist, position);
    }

    public void setInSpin(double power) {
        WriteSubsystem.inSpinNewPower = power;
    }

    public double getArmPosition() {
        return WriteSubsystem.servoNewPosition.get(inArm);
    }
    public boolean getLidar() {
        return intakeLidarL.getState() && intakeLidarR.getState();
    }
}
