package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeSubsystem;

public class ArmDownCommand extends CommandBase {
    OuttakeSubsystem outtakeSubsystem;
    IntakeSubsystem intakeSubsystem;
    int slidePosition;
    public ArmDownCommand(OuttakeSubsystem outtakeSubsystem, IntakeSubsystem intakeSubsystem, int slidePosition) {
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(outtakeSubsystem, intakeSubsystem);

        this.slidePosition = slidePosition;
    }

    @Override
    public void initialize() {
        outtakeSubsystem.setArm(OuttakeSubsystem.armNeutral);
        outtakeSubsystem.setSlides(slidePosition);
        outtakeSubsystem.closeBoth();
        intakeSubsystem.setWrist(.6);
    }

    @Override
    public boolean isFinished() {
        return outtakeSubsystem.getSlidePosition() < 150;
    }

    @Override
    public void end(boolean isInterrupted) {
        intakeSubsystem.setWrist(IntakeSubsystem.wristNeutral);
    }
}