package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeSubsystem;

public class ClimbDownCommand extends CommandBase {

    OuttakeSubsystem outtakeSubsystem;
    IntakeSubsystem intakeSubsystem;

    public ClimbDownCommand(OuttakeSubsystem outtakeSubsystem, IntakeSubsystem intakeSubsystem) {
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(outtakeSubsystem, intakeSubsystem);
    }

    @Override
    public void initialize() {
        outtakeSubsystem.setSlides(0);
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return outtakeSubsystem.getSlidePosition() < 500;
    }

    @Override
    public void end(boolean interrupted) {
    }

}
