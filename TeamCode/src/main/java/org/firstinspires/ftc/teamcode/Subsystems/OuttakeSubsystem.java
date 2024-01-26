package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeSubsystem extends SubsystemBase {
    Motor outSlideL, outSlideR;
    Servo outArmL, outArmR, outL, outR;
    public OuttakeSubsystem(Motor outSlideL, Motor outSlideR, Servo outArmL, Servo outArmR, Servo outL, Servo outR) {
        this.outSlideL = outSlideL;
        this.outSlideR = outSlideR;
        this.outArmL = outArmL;
        this.outArmR = outArmR;
        this.outL = outL;
        this.outR = outR;
    }
}
