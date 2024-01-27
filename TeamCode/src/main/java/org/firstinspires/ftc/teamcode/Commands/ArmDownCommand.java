package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.OuttakeSubsystem;

public class ArmDownCommand extends CommandBase {
    OuttakeSubsystem outtakeSubsystem;
    int slidePosition;
    public ArmDownCommand(OuttakeSubsystem outtakeSubsystem, int slidePosition) {
        this.outtakeSubsystem = outtakeSubsystem;
        this.slidePosition = slidePosition;
    }

    @Override
    public void initialize() {
        outtakeSubsystem.setArm(OuttakeSubsystem.armNeutral);
        outtakeSubsystem.setSlides(slidePosition);
        outtakeSubsystem.closeBoth();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}