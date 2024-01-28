package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.HashMap;

public class WriteSubsystem extends SubsystemBase {
    private HashMap<DcMotor, Double> motorCurrentPower;
    public static HashMap<DcMotor, Double> motorNewPower;
    private HashMap<Servo, Double> servoCurrentPosition;
    public static HashMap<Servo, Double> servoNewPosition;
    private CRServo inSpin;
    private double inSpinPower;
    public static double inSpinNewPower;
    double tempPower = 0, tempPosition = 0;

    public static boolean isPixelTransfered = false, isArmUp = false, isExtendOut = false;

    public WriteSubsystem(DcMotor[] motors, Servo[] servos, CRServo inSpin) {
        motorCurrentPower = new HashMap<>();
        motorNewPower = new HashMap<>();
        servoCurrentPosition = new HashMap<>();
        servoNewPosition = new HashMap<>();
        for(DcMotor m: motors) {
            motorCurrentPower.put(m, 0.0);
            motorNewPower.put(m, 0.0);
        }

        for(Servo s: servos) {
            servoCurrentPosition.put(s, 0.0);
            servoNewPosition.put(s, 0.0);
        }

        this.inSpin = inSpin;
    }

    @Override
    public void periodic() {
        //check all motors and update if needed
        for(DcMotor m: motorCurrentPower.keySet()) {
            tempPower = motorNewPower.get(m);//Math.round(motorNewPower.get(m) * 1000) / 1000;
            if(tempPower != motorCurrentPower.get(m)) {
                m.setPower(tempPower);
                motorCurrentPower.put(m, tempPower);
            }
        }

        //check all the servos and update if needed
        for(Servo s: servoCurrentPosition.keySet()) {
            tempPosition = servoNewPosition.get(s);
            if(tempPosition != servoCurrentPosition.get(s)) {
                s.setPosition(tempPosition);
                servoCurrentPosition.put(s, tempPosition);
            }
        }

        if(inSpinNewPower != inSpinPower) {
            inSpin.setPower(inSpinNewPower);
            inSpinPower = inSpinNewPower;
        }
    }
}
