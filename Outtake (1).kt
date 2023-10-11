package org.firstinspires.ftc.teamcode.hardware

import android.annotation.SuppressLint
import com.qualcomm.hardware.rev.RevTouchSensor
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import com.qualcomm.robotcore.hardware.TouchSensor

import org.firstinspires.ftc.robotcore.external.Telemetry
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.util.*
import kotlin.math.absoluteValue
import com.qualcomm.robotcore.hardware.DistanceSensor


/**
 * OutTake subsystem.
 *
 * This class controls the hardware for placing freight
 */

class Outtake(hwMap: HardwareMap) {


    companion object {


        // New
        const val intakeServoLeft=1.0
        const val intakeServoRight=0.0



        /*** AUTO ***/
        //START

        const val intakeClawOpen = 0.78;
        const val intakeClawClose = 0.34;

        const val intakeArmPos = 0.5;

        const val outtakeServoArmPos = 0.2;

        const val outtakeClawOpen = 0.9;
        const val outtakeClawClose = 0.6;

        const val outtakeMotorPosUp = 1500;
        const val outtakeMotorInitPos = 0;

        const val outtakeArmInitPos = 0.0;
        const val intakeArmInitPos = 0.8;

    }

    //Servos

    val intakeServoClaw= hwMap.servo["intakeServoClaw"] ?: throw Exception("Failed to find servo intakeServoClaw");
    val intakeServoArm = hwMap.servo["intakeServoArm"] ?: throw Exception("Failed to find servo intakeServoArm");

    val outtakeServoArm = hwMap.servo["outtakeServoArm"] ?: throw Exception("Failed to find servo outtakeServoArm");
    val outtakeServoClaw = hwMap.servo["outtakeServoClaw"] ?: throw Exception("Failed to find servo outtakeServoClaw");

    //Motors

    val outtakeMotorLeft = hwMap.dcMotor["outtakeMotorLeft"] ?: throw Exception("Failed to find servo outtakeMotorLeft");
    val outtakeMotorRight = hwMap.dcMotor["outtakeMotorRight"] ?: throw Exception("Failed to find servo outtakeMotorRight");





    //SLIDER positions are protected in order to remain private. That's why we use these 3 functions to determine where the slider is
    //relative to target position
    init {
        intakeOpenClaw()
        outtakeOpenClaw()

        outtakeMotorLeft.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        outtakeMotorRight.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER

        outtakeMotorRight.direction = DcMotorSimple.Direction.FORWARD

        outtakeMotorRight.power = 0.0
        outtakeMotorLeft.power = 0.0

        outtakeMotorLeft.mode = DcMotor.RunMode.RUN_USING_ENCODER
        outtakeMotorRight.mode = DcMotor.RunMode.RUN_USING_ENCODER;
        // mode , directia, run using encoder, putere initala 0,
    }
    //functions

    fun intakeOpenClaw(){
        intakeServoClaw.position = intakeClawOpen;
    }

    fun intakeCloseClaw(){
        intakeServoClaw.position = intakeClawClose;
    }

    fun intakeArmLift(){
        intakeServoArm.position= intakeArmPos;
    }

    fun outttakeArmLift(){
        outtakeServoArm.position= outtakeServoArmPos;
    }

    fun outtakeOpenClaw(){
        outtakeServoClaw.position = outtakeClawOpen;
    }

    fun outtakeCloseClaw(){
        outtakeServoClaw.position = outtakeClawClose;
    }

    fun outtakeMotorsGoUp(){

        outtakeMotorLeft.targetPosition = outtakeMotorPosUp;
        outtakeMotorRight.targetPosition = outtakeMotorPosUp;

        outtakeMotorRight.mode = DcMotor.RunMode.RUN_TO_POSITION;
        outtakeMotorLeft.mode = DcMotor.RunMode.RUN_TO_POSITION;

        outtakeMotorLeft.power = 0.5;
        outtakeMotorRight.power = 0.5;



    }


    fun outtakeServoArmInit(){
        outtakeServoArm.position = outtakeArmInitPos;
    }

    fun intakeServoArmInit(){
        intakeServoArm.position = intakeArmInitPos;
    }

    fun outtakeMotorsGoDown(){

        outtakeMotorLeft.targetPosition = outtakeMotorInitPos;
        outtakeMotorRight.targetPosition = outtakeMotorInitPos;

        outtakeMotorRight.mode = DcMotor.RunMode.RUN_TO_POSITION;
        outtakeMotorLeft.mode = DcMotor.RunMode.RUN_TO_POSITION;

        outtakeMotorLeft.power = 0.2;
        outtakeMotorRight.power = 0.2;


        outtakeMotorLeft.mode = DcMotor.RunMode.RESET_ENCODERS
        outtakeMotorRight.mode = DcMotor.RunMode.RESET_ENCODERS

    }
    fun getMidPozInt ():Int
    {
        return outtakeMotorPosUp-outtakeMotorRight.currentPosition
    }

}
