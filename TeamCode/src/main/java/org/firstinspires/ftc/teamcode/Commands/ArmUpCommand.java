package org.firstinspires.ftc.teamcode.Commands;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.WriteSubsystem;

@Config

public class ArmUpCommand extends CommandBase {
    OuttakeSubsystem outtakeSubsystem;
    IntakeSubsystem intakeSubsystem;
    int slidePosition;
    double armPosition;
    public static double increment = .01;
    ElapsedTime timer = new ElapsedTime();
    public ArmUpCommand(OuttakeSubsystem outtakeSubsystem, IntakeSubsystem intakeSubsystem, int slidePosition) {
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(outtakeSubsystem, intakeSubsystem);

        this.slidePosition = slidePosition;
    }

    @Override
    public void initialize() {
        timer.reset();
        intakeSubsystem.setWrist(.6);
        armPosition = outtakeSubsystem.getArm();
    }

    @Override
    public void execute() {
        if(timer.milliseconds() > 150) {
            outtakeSubsystem.setSlides(slidePosition);
        }
        armPosition = Math.max(armPosition - increment, OuttakeSubsystem.armOut); //TODO: figure out why this works
        outtakeSubsystem.setArm(armPosition);
    }

    @Override
    public boolean isFinished() {
        return armPosition < OuttakeSubsystem.armOut && timer.milliseconds() > 200;
    }
}
