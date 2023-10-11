package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DistanceSensor
import com.qualcomm.robotcore.util.ElapsedTime
import com.qualcomm.robotcore.hardware.TouchSensor
import org.firstinspires.ftc.teamcode.hardware.Hardware
import org.firstinspires.ftc.teamcode.hardware.Outtake
import java.lang.Math.atan2

@TeleOp(name = "CompleteDrive", group = "Main")
class CompleteDrive: OpMode() {

    override fun preInit() {
    }
    override fun preInitLoop() {
        telemetry.addLine("Waiting for start...")
        telemetry.update()
        idle()
    }


    override fun Hardware.run() {

        val gp1 = Gamepad(gamepad1)
        val gp2 = Gamepad(gamepad2)



        //TODO Adjust these variables according to the needs

        var isIntakeClawOpen = false;
        var isOuttakeClawOpen = false;


        waitForStart()
        while(opModeIsActive())
        {
            val power = speed
            val rotPower = rotation
            hw.motors.move(direction, power, rotPower)


            // Cod de Drive

            //Open/Close Intake Claw

            if(gp2.checkToggle(Gamepad.Button.X) && !isIntakeClawOpen){

                outtake.intakeOpenClaw();
                isIntakeClawOpen = true;

            } else if(gp2.checkToggle(Gamepad.Button.X) && isIntakeClawOpen) {

                outtake.intakeCloseClaw();
                isIntakeClawOpen = false;
            }



            if(gp2.checkToggle(Gamepad.Button.A)){

                outtake.intakeArmLift();
            }


            if(gp2.checkToggle(Gamepad.Button.Y)){
                outtake.outttakeArmLift();
            }

            //Open/Close Outtake Claw

            if(gp2.checkToggle(Gamepad.Button.DPAD_DOWN) && !isOuttakeClawOpen){

                outtake.outtakeOpenClaw()
                isOuttakeClawOpen = true;
            } else if(gp2.checkToggle(Gamepad.Button.DPAD_DOWN) && isOuttakeClawOpen){

                outtake.outtakeOpenClaw();
                isOuttakeClawOpen = false;
            }

            //MOTORS

            if(gp2.checkToggle(Gamepad.Button.RIGHT_BUMPER)){
                outtake.outtakeMotorsGoUp();
            }

            if(gp2.checkToggle(Gamepad.Button.LEFT_BUMPER)){
                outtake.outtakeMotorsGoDown();
            }


        }
    }

    ///The direction in which the robot is translating
    private val direction: Double
        get() {
            val x = -gamepad1.left_stick_x.toDouble()  // -
            val y = -gamepad1.left_stick_y.toDouble() // +

            return atan2(y, x) / Math.PI * 180.0 - 90.0
        }

    /// Rotation around the robot's Z axis.
    private val rotation: Double
        get() = -gamepad1.right_stick_x.toDouble()  // -

    /// Translation speed.
    private val speed: Double
        get() {
            val x = gamepad1.left_stick_x.toDouble() //+
            val y = gamepad1.left_stick_y.toDouble() //+

            return Math.sqrt((x * x) + (y * y))
        }

}