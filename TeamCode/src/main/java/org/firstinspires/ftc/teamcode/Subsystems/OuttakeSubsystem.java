package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@Config
public class OuttakeSubsystem extends SubsystemBase {
    DcMotor outSlideL, outSlideR;
    Servo outArmL, outArmR, outL, outR;
    public static double p=0.003, i=0.2, d=0.000, f=0.00; //TODO: tune this
    public static final double armNeutral = .72, armOut = .328;
    private PIDFController slideController = new PIDFController(p,i,d,f);
    double slidePower = 0;

    private static double lDrop = .13, rDrop = .62, lClose = .50, rClose = .14; //TODO: figure out these values

    public static double slidePosition;
    public static FtcDashboard dashboard;
    Telemetry telemetry;
    public OuttakeSubsystem(DcMotor outSlideL, DcMotor outSlideR, Servo outArmL, Servo outArmR, Servo outL, Servo outR) {
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

    @Override
    public void periodic() {
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
