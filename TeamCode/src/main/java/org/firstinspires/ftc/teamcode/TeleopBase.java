package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.button.Button;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.command.button.Trigger;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TeleopBase extends CommandOpMode {
    Command teleDrive;
    Button r2, l2, y2, x2, a2, down2;
    Main robot;
    GamepadEx gpad1, gpad2;
    Command straighten, dropL, dropR, dropBoth, closeBoth;
    Trigger autodrop;
    @Override
    public void initialize() {
        gpad1 = new GamepadEx(gamepad1);
        gpad2 = new GamepadEx(gamepad2);
        robot = new Main(true, hardwareMap, telemetry);
        configureButtons();
        configureCommands();
        bindTriggers();
    }

    @Override
    public void run() {
        super.run();
    }

    public void bindTriggers() {
        robot.driveSubsystem.setDefaultCommand(teleDrive);
        //button for intake to go out and pick up pixels, come back when released
        //button to out and up
        //button to drop left and right
//        r2.whenPressed(dropL);
//        l2.whenPressed(dropR);
//        down2.whenPressed(dropBoth);
        //button to hold to check sensors and autodrop both

        //or potentially different modes?
        //trigger to come back when 2 pixels are collected

        //climb: press and hold for to go out and then release to go back
    }

    public void configureButtons() {
        r2 = new GamepadButton(gpad2, GamepadKeys.Button.DPAD_RIGHT);
        l2 = new GamepadButton(gpad2, GamepadKeys.Button.DPAD_LEFT);
        y2 = new GamepadButton(gpad2, GamepadKeys.Button.Y);
        x2 = new GamepadButton(gpad2, GamepadKeys.Button.X);
        a2 = new GamepadButton(gpad2, GamepadKeys.Button.A);
        down2 = new GamepadButton(gpad2, GamepadKeys.Button.DPAD_DOWN);
    }

    public void configureCommands() {
        teleDrive = new RunCommand(()-> robot.driveSubsystem.teleDrive(gamepad1), robot.driveSubsystem);
        dropL = new InstantCommand(()-> robot.outtakeSubsystem.dropL());
        dropR = new InstantCommand(()-> robot.outtakeSubsystem.dropR());
        dropBoth = new InstantCommand(()-> robot.outtakeSubsystem.dropBoth());
    }
}
