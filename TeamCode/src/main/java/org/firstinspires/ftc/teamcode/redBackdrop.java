package org.firstinspires.ftc.teamcode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;

import android.util.Size;

import androidx.annotation.NonNull;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.VisionProcessor;

import java.util.ArrayList;
import java.util.Arrays;

@Config
@Autonomous(name = "redBackdrop", group = "Autonomous")
public class redBackdrop extends LinearOpMode{

    public int propPosition = 1;
    public TeamPropProcessor propProcessor;
    public VisionPortal propPortal;

    @Override
    public void runOpMode() {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(12, -64.25, Math.toRadians(90)));

        Action Left = drive.actionBuilder(drive.pose)
                .build();

        Action Mid = drive.actionBuilder(drive.pose)
                .splineToLinearHeading(new Pose2d(19, -38, Math.toRadians(-90)), Math.PI / 2)
                .build();

        Action Right = drive.actionBuilder(drive.pose)
                //.lineToXConstantHeading()
                .build();

        propProcessor = new TeamPropProcessor(true);
        initVisionPortal(new ArrayList<>(Arrays.asList(propProcessor)));

        while(!isStarted()) {
            updatePropReading();
            telemetry.addData("Ev propPosition" , propPosition);
            //telemetry.addData("position", robot.aPipe.getPosition());
            //telemetry.addData("x-value", robot.aPipe.getX());
            //telemetry.addData("y-value", robot.aPipe.getY());
            //telemetry.addData("max area", robot.aPipe.getMaxContour());
            telemetry.update();
        }

        waitForStart();

        Action trajectoryActionChosen;
        if(propPosition == 0){
            trajectoryActionChosen = Left;
        } else if(propPosition == 1){
            trajectoryActionChosen = Mid;
        } else{
            trajectoryActionChosen = Right;
        }

        Actions.runBlocking(
                new SequentialAction(
                        trajectoryActionChosen
                )
        );


    }
    public void updatePropReading() {
        try {
            propPosition = propProcessor.getPosition();
            double[] avgs = propProcessor.getVals();
            switch(propPosition) {
                case 0:
                    telemetry.addData("Prop Pos", "left"); break;
                case 1:
                    telemetry.addData("Prop Pos", "middle"); break;
                case 2:
                    telemetry.addData("Prop Pos", "right"); break;
            }
            telemetry.addData("Left Avg:", avgs[0]);
            telemetry.addData("Middle Avg:", avgs[1]);
            telemetry.addData("Right Avg:", avgs[2]);

        } catch (Exception e) {}
    }

    public void initVisionPortal(ArrayList<VisionProcessor> processors) {
        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));

        // Choose a camera resolution. Not all cameras support all resolutions.
        builder.setCameraResolution(new Size(320, 240));

        // Enable the RC preview (LiveView).  Set "false" to omit camera monitoring.
        builder.enableLiveView(true);

        // Set the stream format; MJPEG uses less bandwidth than default YUY2.
        builder.setStreamFormat(VisionPortal.StreamFormat.YUY2);

        // Choose whether or not LiveView stops if no processors are enabled.
        // If set "true", monitor shows solid orange screen if no processors enabled.
        // If set "false", monitor shows camera view without annotations.
        builder.setAutoStopLiveView(false);

        for (VisionProcessor processor : processors) {
            builder.addProcessor(processor);
        }

        propPortal = builder.build();

    }
}
