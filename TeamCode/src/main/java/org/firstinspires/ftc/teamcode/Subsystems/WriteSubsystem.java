package org.firstinspires.ftc.teamcode.Subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.HashMap;

public class WriteSubsystem extends SubsystemBase {
    private HashMap<Motor, Double> motorCurrentPower;
    public static HashMap<Motor, Double> motorNewPower;
    private HashMap<Servo, Double> servoCurrentPosition;
    public static HashMap<Servo, Double> servoNewPosition;
    private CRServo inSpin;
    private double inSpinPower;
    public static double inSpinNewPower;
    double tempPower = 0, tempPosition = 0;

    public WriteSubsystem(Motor[] motors, Servo[] servos, CRServo inSpin) {
        for(Motor m: motors) {
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
        for(Motor m: motorCurrentPower.keySet()) {
            tempPower = Math.round(motorNewPower.get(m) * 1000) / 1000;
            if(tempPower != motorCurrentPower.get(m)) {
                m.set(tempPower);
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
