package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.PID.MyOwnGyro_output;
import frc.robot.PID.MyOwnGyro_source;
import frc.robot.Robot;

//创建一个标准的Command类
public class MyGeneralPIDControl extends Command {
    //不可以匿名的方式创建PIDOutput, 因为之后还要调用这个类中的getValue()函数
    //声明时不可以用父类PIDOutput，否则如果不cast就不能访问到用户自定义的函数(getValue())
    private MyOwnGyro_output myOwnGyro_output =
            new MyOwnGyro_output();

    //PIDController的构造函数有多种。在这个构造函数中，前三个参数分别为PID的系数kp,ki,kd
    //后两个参数分别为PIDSource接口类的匿名实例，和PIDOutput接口类的实例
    private PIDController myPidController =
            new PIDController(2.0, 0.0, 0.0, new MyOwnGyro_source(), myOwnGyro_output);

    public MyGeneralPIDControl() {
        //在这个Command构造函数中，对PIDController进行一些属性设定
        myPidController.setInputRange(0.0, 200); //数据输入范围
        myPidController.setOutputRange(-0.5, 0.5); //输出数据范围
        myPidController.setSetpoint(100); //目标值
        myPidController.setAbsoluteTolerance(10); //容忍绝对误差
        requires(Robot.m_myGeneralSubsystem);
    }

    @Override
    protected void initialize() {
        //在Command生命周期中的initialize()中打开PIDController，整个pid控制启动
        myPidController.enable();
    }

    @Override
    protected void execute() {
        //在Command生命周期中的execute()中，实时（每20ms）获取从PIDOutput接口类中获取计算结果
        System.out.println(myOwnGyro_output.getValue());
        Robot.m_myGeneralSubsystem.setSpeed(myOwnGyro_output.getValue());
    }

    @Override
    protected boolean isFinished() {
        //如果该Command类需要在完成后自动结束，则通过onTarget()获取PID控制是否已经达到目标
        //如果不需要自动结束，则直接return false
        System.out.println(myPidController.onTarget());
        return myPidController.onTarget();
    }

    @Override
    protected void end() {
        Robot.m_myGeneralSubsystem.setSpeed(0.0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
