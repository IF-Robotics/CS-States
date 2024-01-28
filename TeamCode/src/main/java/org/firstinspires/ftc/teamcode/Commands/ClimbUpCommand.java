package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeSubsystem;

public class ClimbUpCommand extends CommandBase {

    OuttakeSubsystem outtakeSubsystem;
    IntakeSubsystem intakeSubsystem;
    int slidePosition;
    int currentPosition;

    public ClimbUpCommand(OuttakeSubsystem outtakeSubsystem, IntakeSubsystem intakeSubsystem, int slidePosition) {
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(outtakeSubsystem, intakeSubsystem);

        this.slidePosition = slidePosition;
    }

    @Override
    public void initialize() {
        currentPosition = outtakeSubsystem.getSlidePosition();

    }

    @Override
    public void execute() {
        outtakeSubsystem.powerSlides(0.5);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        outtakeSubsystem.stopSlides();
    }

}
