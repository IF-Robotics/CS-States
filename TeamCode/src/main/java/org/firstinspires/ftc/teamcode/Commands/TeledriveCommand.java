package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Subsystems.DriveSubsystem;

public class TeledriveCommand extends CommandBase {
    DriveSubsystem driveSubsystem;
    Gamepad gamepad1;
    double power;
    public TeledriveCommand(DriveSubsystem driveSubsystem, Gamepad gamepad1) {
        this.driveSubsystem = driveSubsystem;
        this.gamepad1 = gamepad1;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        if(gamepad1.left_bumper) {
            power = .6;
        } else if(gamepad1.right_bumper) {
            power = .4;
        } else {
            power = 1;
        }
        driveSubsystem.teleDrive(gamepad1, power);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
