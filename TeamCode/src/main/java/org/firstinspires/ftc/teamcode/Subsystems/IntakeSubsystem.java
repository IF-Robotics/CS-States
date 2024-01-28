package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSubsystem extends SubsystemBase {
    private Servo inArm, inWrist;
    public static final double armNeutral = .060, wristNeutral = .094;
    public static final double armDown = 1, wristDown = .905; //TODO: figure out these actual values
    private CRServo inSpin;
    public IntakeSubsystem(CRServo inSpin, Servo inArm, Servo inWrist) {
        this.inArm = inArm;
        this.inSpin = inSpin;
        this.inWrist = inWrist;
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
}
