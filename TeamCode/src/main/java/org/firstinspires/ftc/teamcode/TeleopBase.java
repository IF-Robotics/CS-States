package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.RunCommand;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleopBase extends CommandOpMode {
    Command teleDrive;
    Main robot = new Main(true, hardwareMap, telemetry);
    @Override
    public void initialize() {
        configureButtons();
        configureCommands();
        bindTriggers();
    }

    public void bindTriggers() {
        robot.driveSubsystem.setDefaultCommand(teleDrive);
        //button for intake to go out and pick up pixels, come back when released
        //button to out and up
        //button to drop left and right
        //button to hold to check sensors and autodrop both

        //or potentially different modes?
        //trigger to come back when 2 pixels are collected

        //climb: press and hold for to go out and then release to go back
    }

    public void configureButtons() {

    }

    public void configureCommands() {
        teleDrive = new RunCommand(()-> robot.driveSubsystem.teleDrive(gamepad1), robot.driveSubsystem);
    }
}
