package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.Subsystem;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.ReadSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.WriteSubsystem;

import java.util.List;


public class Main {
    private DcMotorEx BL, BR, FR, FL;
    private DcMotorEx inSlideL, inSlideR, outSlideL, outSlideR;
    private Servo inArm, drone, inWrist, outArmL, outArmR, outL, outR;
    private CRServo inSpin;
    public Subsystem readSubsystem, writeSubsystem, intakeSubsystem, inSlideSubsystem, planeSubsystem;
    public DriveSubsystem driveSubsystem;
    public OuttakeSubsystem outtakeSubsystem;
    IMU imu;
    HardwareMap hardwareMap;
    Telemetry telemetry;

        public Main(Boolean isTeleop, HardwareMap hardwareMap, Telemetry telemetry) {
            this.hardwareMap = hardwareMap;
            this.telemetry = telemetry;
            initSubsystems();
            if(isTeleop) {
                initTeleop();
            } else {
                initAuto();
            }
        }

        public void initSubsystems() {
            CommandScheduler scheduler = CommandScheduler.getInstance();
            scheduler.reset();

            //bulk reads
            List<LynxModule> allHubs = hardwareMap.getAll(LynxModule.class);
            for (LynxModule hub : allHubs) {
                hub.setBulkCachingMode(LynxModule.BulkCachingMode.AUTO);
            }

            //imu
            imu = hardwareMap.get(IMU.class, "imu");
            //TODO: make this match the actual robot
            IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                    RevHubOrientationOnRobot.UsbFacingDirection.UP));
            imu.initialize(parameters);

//            BL = new Motor(hardwareMap, "BL");
//            BR = new Motor(hardwareMap, "BR");
//            FR = new Motor(hardwareMap, "FR");
//            FL = new Motor(hardwareMap, "FL");
            BL = hardwareMap.get(DcMotorEx.class, "BL");
            BR = hardwareMap.get(DcMotorEx.class, "BR");
            FR = hardwareMap.get(DcMotorEx.class, "FR");
            FL = hardwareMap.get(DcMotorEx.class, "FL");

            /*inSlideL = new Motor(hardwareMap, "inSlideL");
            inSlideR = new Motor(hardwareMap, "inSlideR");
            outSlideL = new Motor(hardwareMap, "outSlideL");*/
            outSlideR = hardwareMap.get(DcMotorEx.class, "outSlideR");
            outSlideR.setDirection(DcMotorSimple.Direction.REVERSE);

            DcMotor[] writeMotors = {BL, BR, FR, FL};//, inSlideL, inSlideR, outSlideL, outSlideR};

            /*inArm = hardwareMap.get(Servo.class, "inArm");
            drone = hardwareMap.get(Servo.class, "drone");
            inWrist = hardwareMap.get(Servo.class, "inWrist");
            outArmL = hardwareMap.get(Servo.class, "outArmL");
            outArmR = hardwareMap.get(Servo.class, "outArmR");
            outL = hardwareMap.get(Servo.class, "outL");
            outR = hardwareMap.get(Servo.class, "outR");

            Servo[] writeServos = {inArm, drone, inWrist, outArmL, outArmR, outL, outR};
            writeSubsystem = new WriteSubsystem(writeMotors, writeServos, inSpin);

            inSlideSubsystem = new InSlideSubsystem(inSlideL, inSlideR);
            intakeSubsystem = new IntakeSubsystem(inSpin, inArm, inWrist);
            outtakeSubsystem = new OuttakeSubsystem(outSlideL, outSlideR, outArmL, outArmR,outL, outR);*/

            Servo[] writeServos = {};
            inSpin = hardwareMap.get(CRServo.class, "inSpin");
            writeSubsystem = new WriteSubsystem(writeMotors, writeServos, inSpin);
            driveSubsystem = new DriveSubsystem(FL, FR, BR, BL, imu);
//            scheduler.registerSubsystem(writeSubsystem, intakeSubsystem, inSlideSubsystem, outtakeSubsystem, driveSubsystem);
        }

        public void initTeleop() {
            DcMotor[] motorEncoders = {};//inSlideR, outSlideR};
            DcMotorEx[] driveMotors = {FL, FR, BR, BL};
            readSubsystem = new ReadSubsystem(motorEncoders, driveMotors, imu, telemetry);
        }

        public void initAuto() {
            //TODO: add servos to the motor endcoders and initialize and set up readSubsystem
        }
}
