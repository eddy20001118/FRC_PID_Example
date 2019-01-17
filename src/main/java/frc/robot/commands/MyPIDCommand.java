/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

//创建一个有内建PID控制的Command
public class MyPIDCommand extends PIDCommand {
    private Encoder m_leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    private Encoder m_rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
    private PIDController myController;
    public MyPIDCommand() {
        //在父构造函数中直接设置pid的系数
        super(2,0,0);
        //获取内建PIDController的实例
        myController = getPIDController();
        //用户可以直接调用一些封装过的PIDController的api进行属性设定，如果没有则访问PIDController实例进行设定
        setSetpoint(20); //设置目标值
        setInputRange(0,200); //输入范围
        requires(Robot.m_myGeneralSubsystem);
    }

    @Override
    protected void initialize() {
        myController.enable();
    }

    @Override
    protected void execute() {
        //execute函数一般无需进行操作，除非你需要动态的设定目标值
    }

    @Override
    protected boolean isFinished() {
        return myController.onTarget();
    }

    @Override
    protected void end() {
        Robot.m_myGeneralSubsystem.setSpeed(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected double returnPIDInput() {
        return (m_leftEncoder.getRate() + m_rightEncoder.getRate())/2;
    }

    @Override
    protected void usePIDOutput(double output) {
        //当pid控制启动后，会自动调用该接口并传入计算结果
        Robot.m_myGeneralSubsystem.setSpeed(output);
    }
}
