package org.firstinspires.ftc.teamcode.Commands;

import com.arcrobotics.ftclib.command.CommandBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.OuttakeSubsystem;

public class ClimbUpCommand extends CommandBase {

    OuttakeSubsystem outtakeSubsystem;
    IntakeSubsystem intakeSubsystem;
    ElapsedTime timer = new ElapsedTime();

    public ClimbUpCommand(OuttakeSubsystem outtakeSubsystem, IntakeSubsystem intakeSubsystem) {
        this.outtakeSubsystem = outtakeSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(outtakeSubsystem, intakeSubsystem);
    }

    @Override
    public void initialize() {
        intakeSubsystem.setWrist(.11);
        intakeSubsystem.setArm(.235);
        timer.reset();
    }

    @Override
    public void execute() {
        if(timer.milliseconds() > 200) {
            outtakeSubsystem.setSlides(1700);
        }
    }

    @Override
    public boolean isFinished() {
        return outtakeSubsystem.getSlidePosition() > 1500;
    }

}
