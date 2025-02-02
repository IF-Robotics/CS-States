package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.InSlideSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;

public class IntakeOutcommand extends CommandBase {
    IntakeSubsystem intakeSubsystem;
    InSlideSubsystem inSlideSubsystem;
    ElapsedTime timer = new ElapsedTime();
    public IntakeOutcommand(IntakeSubsystem intakeSubsystem, InSlideSubsystem inSlideSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
        this.inSlideSubsystem = inSlideSubsystem;
        addRequirements(intakeSubsystem, inSlideSubsystem);
    }

    @Override
    public void initialize() {
        timer.reset();
        intakeSubsystem.setArm(intakeSubsystem.armDown);
    }

    @Override
    public void execute() {
        if(timer.milliseconds() > 100) {
            intakeSubsystem.setWrist(intakeSubsystem.wristDown);
            intakeSubsystem.setInSpin(1);
        }
    }

    @Override
    public boolean isFinished() {
        return timer.milliseconds() > 200;
    }
}
