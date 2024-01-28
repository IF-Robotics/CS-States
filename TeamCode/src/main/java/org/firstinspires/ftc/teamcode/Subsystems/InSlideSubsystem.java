package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;


public class InSlideSubsystem extends SubsystemBase {
    DcMotor inSlideL, inSlideR;
    double slidePower = 0;
    int slidePosition;
    private double p=0.003, i=0.2, d=0.0001, f=0.0015;
    private PIDFController slideController = new PIDFController(p,i,d,f);
    public InSlideSubsystem(DcMotor inSlideL, DcMotor inSlideR) {
        this.inSlideL = inSlideL;
        this.inSlideR = inSlideR;
    }

    public void setPower(double power) {
        WriteSubsystem.motorNewPower.put(inSlideL, power);
        WriteSubsystem.motorNewPower.put(inSlideR, power);
    }

    public void setPosition(int position) {
        slidePosition = position;
    }

    @Override
    public void periodic() {
//        slidePower = slideController.calculate(ReadSubsystem.encoderValues.get(inSlideR), slidePosition);
//        WriteSubsystem.motorNewPower.put(inSlideR, slidePower);
//        WriteSubsystem.motorNewPower.put(inSlideL, slidePower);
    }
}
