package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.InSlideSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeInCommand extends CommandBase {
    IntakeSubsystem intakeSubsystem;
    InSlideSubsystem inSlideSubsystem;
    public IntakeInCommand(IntakeSubsystem intakeSubsystem, InSlideSubsystem inSlideSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.inSlideSubsystem = inSlideSubsystem;
        addRequirements(intakeSubsystem, inSlideSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.setArm(intakeSubsystem.armNeutral);
        intakeSubsystem.setWrist(intakeSubsystem.wristNeutral);
    }

    @Override
    public void execute() {
        intakeSubsystem.setInSpin(.2);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
