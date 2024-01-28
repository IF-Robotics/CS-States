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

import org.firstinspires.ftc.teamcode.Commands.ArmDownCommand;
import org.firstinspires.ftc.teamcode.Commands.ArmUpCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeInCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeOutcommand;

@TeleOp
public class TeleopBase extends CommandOpMode {
    Command teleDrive;
    Button r2, l2, y2, x2, a2, down2, b2, up2, lb2, option;
    Main robot;
    GamepadEx gpad1, gpad2;
    Trigger autodrop, autoIntake;
    Command straighten, dropL, dropR, dropBoth, closeBoth, armMid, armDown, armUp, intakeIn, intakeOut, transfer, stopSpin, reset;
    double loopTime = 0;
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
//        bindTriggers();

        double loop = System.nanoTime();
        telemetry.addData("hz ", 1000000000/(loop-loopTime));
        loopTime = loop;
    }

    public void bindTriggers() {
        robot.driveSubsystem.setDefaultCommand(teleDrive);
        option.whenPressed(reset);
        //button for intake to go out and pick up pixels, come back when released
        down2.whileHeld(intakeOut);
        down2.whenReleased(intakeIn);

        //transfer
        x2.whileHeld(transfer);

        //button to out and up
        y2.whenPressed(armUp);
        b2.whenPressed(armMid);
        a2.whenPressed(armDown);
        //button to drop left and right
        r2.whenPressed(dropL);
        l2.whenPressed(dropR);
        up2.whenPressed(dropBoth);
        lb2.whenPressed(stopSpin);
        //button to hold to check sensors and autodrop both

        //or potentially different modes?
        //trigger to come back when 2 pixels are collected

        //climb: press and hold for to go out and then release to go back

        //autoIntake.whenActive() TODO: Code Blinken or rumble to indicate pixels intaken
    }


    public void configureButtons() {
        r2 = new GamepadButton(gpad2, GamepadKeys.Button.DPAD_RIGHT);
        l2 = new GamepadButton(gpad2, GamepadKeys.Button.DPAD_LEFT);
        b2 = new GamepadButton(gpad2, GamepadKeys.Button.B);
        y2 = new GamepadButton(gpad2, GamepadKeys.Button.Y);
        x2 = new GamepadButton(gpad2, GamepadKeys.Button.X);
        a2 = new GamepadButton(gpad2, GamepadKeys.Button.A);
        lb2 = new GamepadButton(gpad2, GamepadKeys.Button.LEFT_BUMPER);
        up2 = new GamepadButton(gpad2, GamepadKeys.Button.DPAD_UP);
        down2 = new GamepadButton(gpad2, GamepadKeys.Button.DPAD_DOWN);
        autoIntake = new Trigger(() -> robot.intakeSubsystem.getLidar());
        autodrop = new Trigger(() -> robot.outtakeSubsystem.getBackdropLidar());
        option = new GamepadButton(gpad1, GamepadKeys.Button.START);
    }

    public void configureCommands() {
        teleDrive = new RunCommand(()-> robot.driveSubsystem.teleDrive(gamepad1), robot.driveSubsystem);
        dropL = new InstantCommand(()-> robot.outtakeSubsystem.dropL());
        dropR = new InstantCommand(()-> robot.outtakeSubsystem.dropR());
        dropBoth = new InstantCommand(()-> robot.outtakeSubsystem.dropBoth());
        armMid = new ArmUpCommand(robot.outtakeSubsystem, robot.intakeSubsystem, 1500);
        armUp = new ArmUpCommand(robot.outtakeSubsystem, robot.intakeSubsystem, 2100);
        armDown = new ArmDownCommand(robot.outtakeSubsystem, robot.intakeSubsystem, 0);
        stopSpin = new InstantCommand(()-> {robot.intakeSubsystem.setInSpin(0);});
        intakeIn = new IntakeInCommand(robot.intakeSubsystem, robot.inSlideSubsystem);
        intakeOut = new IntakeOutcommand(robot.intakeSubsystem, robot.inSlideSubsystem);
        transfer = new InstantCommand(()-> robot.intakeSubsystem.setInSpin(-.35));
        reset = new InstantCommand(()-> robot.driveSubsystem.reset());
    }
}
