package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class MyPIDSubsystem extends PIDSubsystem {

    //创建一个有内建PID控制的Subsystem
    private SpeedController motor1 = new Spark(0);
    private Encoder m_leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    private Encoder m_rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
    private PIDController myController;

    public MyPIDSubsystem() {
        super(2.0, 0.0, 0.0);
        setAbsoluteTolerance(0.05);
        myController = getPIDController();
        myController.setContinuous(false);
//        与PIDCommand唯一的区别是Subsystem是没有生命周期的，所以要在构造函数中
//        启动PID控制，或者暴露到一个函数中允许用户自己选择何时启动PID控制
        myController.enable();
    }

    @Override
    public void initDefaultCommand() {
    }

    public void setSpeed(double speed) {
        motor1.set(speed);
    }

    @Override
    protected double returnPIDInput() {
        return (m_leftEncoder.getRate() + m_rightEncoder.getRate())/2;
    }

    @Override
    protected void usePIDOutput(double output) {
        setSpeed(output);
    }
}
