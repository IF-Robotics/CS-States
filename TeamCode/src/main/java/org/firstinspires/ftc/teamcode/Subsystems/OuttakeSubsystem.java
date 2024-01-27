package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Servo;

public class OuttakeSubsystem extends SubsystemBase {
    Motor outSlideL, outSlideR;
    Servo outArmL, outArmR, outL, outR;
    public static double p=0.003, i=0.2, d=0.0001, f=0.0015; //TODO: tune this
    private PIDFController slideController = new PIDFController(p,i,d,f);

    private static double lDrop, rDrop, lClose, rClose; //TODO: figure out these values

    double slidePosition;
    public OuttakeSubsystem(Motor outSlideL, Motor outSlideR, Servo outArmL, Servo outArmR, Servo outL, Servo outR) {
        this.outSlideL = outSlideL;
        this.outSlideR = outSlideR;
        this.outArmL = outArmL;
        this.outArmR = outArmR;
        this.outL = outL;
        this.outR = outR;
    }

    public void setSlides(int position) {
        slidePosition = position;
    }

    public void setArm(double position) {
        WriteSubsystem.servoNewPosition.put(outArmL, position);
        WriteSubsystem.servoNewPosition.put(outArmR, position);
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
}
