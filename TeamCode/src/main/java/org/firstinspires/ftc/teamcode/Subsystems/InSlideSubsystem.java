package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;

public class InSlideSubsystem extends SubsystemBase {
    DcMotor inSlideL, inSlideR;
    public InSlideSubsystem(DcMotor inSlideL, DcMotor inSlideR) {
        this.inSlideL = inSlideL;
        this.inSlideR = inSlideR;
    }

    public void setPower(double power) {
        WriteSubsystem.motorNewPower.put(inSlideL, power);
        WriteSubsystem.motorNewPower.put(inSlideR, power);
    }

    public void setPosition(int position) {
        //TODO: add pid math here
    }
}
