package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.Command;
import com.arcrobotics.ftclib.command.RunCommand;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class BlueTeleop extends TeleopBase{
    private Command straighten;

    @Override
    public void run() {
        super.run();
        a1.whileActiveContinuous(straighten);
    }

    @Override
    public void configureCommands() {
        super.configureCommands();
        straighten = new RunCommand(()-> robot.driveSubsystem.straighten(90));
    }

}