package com.example.meepmeep;

import static java.lang.Thread.sleep;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.SampleMecanumDrive;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity bot = new DefaultBotBuilder(meepMeep).build();
        //SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        Pose2d blueAud = new Pose2d(-40.25, 64.25, Math.toRadians(-90));
        Pose2d blueBack = new Pose2d(7.75, 64.25, Math.toRadians(-90));
        Pose2d redBack = new Pose2d(7.75, -64.25, Math.toRadians(90));
        Pose2d redAud = new Pose2d(-40.25, -64.25, Math.toRadians(90));
        int teamProp = 1;



        RoadRunnerBotEntity blueAudL = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(blueAud)
                                .lineToSplineHeading(new Pose2d(-40.6, 56, Math.toRadians(-67)))
                                //purple
                                .addDisplacementMarker(() ->{})
                                .splineToSplineHeading(new Pose2d(-20, 10), Math.toRadians(0))
                                .lineToSplineHeading(new Pose2d(50,10, Math.toRadians(0)))
                                .build()
                );

        RoadRunnerBotEntity blueAudC = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(blueAud)
                                //purple
                                .lineToLinearHeading(new Pose2d(-40.25,62, Math.toRadians(-90)))
                                .addDisplacementMarker(() ->{})
                                .lineToLinearHeading(new Pose2d(20,59, Math.toRadians(-90)))
                                .lineToSplineHeading(new Pose2d(50,59, Math.toRadians(-180)))

                                .build()
                );

        RoadRunnerBotEntity blueAudR = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(blueAud)
                                //purple
                                .lineToLinearHeading(new Pose2d(-45,62, Math.toRadians(-90)))
                                .addDisplacementMarker(() ->{})
                                .lineToLinearHeading(new Pose2d(20,59, Math.toRadians(-90)))
                                .lineToSplineHeading(new Pose2d(50,59, Math.toRadians(-180)))

                                .build()
                );

        RoadRunnerBotEntity blueBackL = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(blueBack)
                                //purple
                                .lineToLinearHeading(new Pose2d(20,59, Math.toRadians(-90)))
                                .addDisplacementMarker(() ->{})
                                .lineToSplineHeading(new Pose2d(50,59, Math.toRadians(-180)))

                                .build()
                );

        RoadRunnerBotEntity blueBackC = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(blueBack)
                                //purple
                                .addDisplacementMarker(() ->{})
                                .lineToLinearHeading(new Pose2d(20,59, Math.toRadians(-90)))

                                .lineToSplineHeading(new Pose2d(50,59, Math.toRadians(-180)))

                                .build()
                );

        RoadRunnerBotEntity blueBackR = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(blueBack)
                                //purple
                                .lineToLinearHeading(new Pose2d(20,59, Math.toRadians(-120)))
                                .addDisplacementMarker(() ->{

                                })
                                .lineToSplineHeading(new Pose2d(50,59, Math.toRadians(-180)))

                                .build()
                );

        RoadRunnerBotEntity redAudL = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(redAud)
                                //purple
                                .lineToLinearHeading(new Pose2d(-50,-59, Math.toRadians(90)))
                                .addDisplacementMarker(() ->{})
                                .lineToLinearHeading(new Pose2d(20,-59, Math.toRadians(90)))

                                .lineToSplineHeading(new Pose2d(50,-59, Math.toRadians(-180)))

                                .build()
                );


        RoadRunnerBotEntity redAudC = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(redAud)
                                //purple
                                .addDisplacementMarker(() ->{})
                                .lineToLinearHeading(new Pose2d(20,-59, Math.toRadians(90)))

                                .lineToSplineHeading(new Pose2d(50,-59, Math.toRadians(-180)))

                                .build()
                );

        RoadRunnerBotEntity redAudR = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(redAud)
                                //purple
                                .lineToLinearHeading(new Pose2d(-50,-59, Math.toRadians(60)))
                                .addDisplacementMarker(() ->{})
                                .lineToLinearHeading(new Pose2d(20,-59, Math.toRadians(90)))

                                .lineToSplineHeading(new Pose2d(50,-59, Math.toRadians(-180)))

                                .build()
                );

        RoadRunnerBotEntity redAudRC = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(redAud)
                                //purple
                                .lineToLinearHeading(new Pose2d(-50,-59, Math.toRadians(60)))
                                .addDisplacementMarker(() ->{})
                                .splineToSplineHeading(new Pose2d(-20, -10), Math.toRadians(0))
                                .lineToSplineHeading(new Pose2d(50,-10, Math.toRadians(0)))

                                .build()
                );

        RoadRunnerBotEntity redBackL = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(redBack)
                                //purple
                                .lineToLinearHeading(new Pose2d(20,-59, Math.toRadians(120)))
                                .addDisplacementMarker(() ->{})
                                .lineToSplineHeading(new Pose2d(50,-59, Math.toRadians(-180)))

                                .build()
                );

        RoadRunnerBotEntity redBackC = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(redBack)
                                //purple
                                .addDisplacementMarker(() ->{})
                                .lineToLinearHeading(new Pose2d(20,-59, Math.toRadians(90)))

                                .lineToSplineHeading(new Pose2d(50,-59, Math.toRadians(-180)))

                                .build()
                );

        RoadRunnerBotEntity redBackR = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(redBack)
                                //purple
                                .lineToLinearHeading(new Pose2d(20,-59, Math.toRadians(90)))
                                .addDisplacementMarker(() ->{})
                                .lineToSplineHeading(new Pose2d(50,-59, Math.toRadians(-180)))

                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(blueAudL)
                .addEntity(blueAudC)
                .addEntity(blueAudR)
                .addEntity(blueBackL)
                .addEntity(blueBackC)
                .addEntity(blueBackR)
                .addEntity(redAudL)
                .addEntity(redAudC)
                .addEntity(redAudR)
                .addEntity(redAudRC)
                .addEntity(redBackL)
                .addEntity(redBackC)
                .addEntity(redBackR)
                .start();
    }
}
