package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeSubsystem extends SubsystemBase {
    private Servo inArm, inWrist;
    public static double armNeutral, wristNeutral;
    public static double armDown, wristDown;
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
