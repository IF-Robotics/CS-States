package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.OuttakeSubsystem;
@Config

public class ArmUpCommand extends CommandBase {
    OuttakeSubsystem outtakeSubsystem;
    int slidePosition;
    double armPosition;
    public static double increment = .02;
    public ArmUpCommand(OuttakeSubsystem outtakeSubsystem, int slidePosition) {
        this.outtakeSubsystem = outtakeSubsystem;
        this.slidePosition = slidePosition;
    }

    @Override
    public void initialize() {
        outtakeSubsystem.setSlides(slidePosition);
        armPosition = OuttakeSubsystem.armNeutral;
    }

    @Override
    public void execute() {
        armPosition = armPosition - increment;
        outtakeSubsystem.setArm(armPosition);
    }

    @Override
    public boolean isFinished() {
        return armPosition < OuttakeSubsystem.armOut;
    }
}
