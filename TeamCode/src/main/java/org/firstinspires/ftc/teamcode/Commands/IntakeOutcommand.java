package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.InSlideSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeOutcommand extends CommandBase {
    IntakeSubsystem intakeSubsystem;
    InSlideSubsystem inSlideSubsystem;
    public IntakeOutcommand(IntakeSubsystem intakeSubsystem, InSlideSubsystem inSlideSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.inSlideSubsystem = inSlideSubsystem;
        addRequirements(intakeSubsystem, inSlideSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.setArm(intakeSubsystem.armDown);
        intakeSubsystem.setWrist(intakeSubsystem.wristDown);
    }

    @Override
    public void execute() {
        intakeSubsystem.setInSpin(1);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
