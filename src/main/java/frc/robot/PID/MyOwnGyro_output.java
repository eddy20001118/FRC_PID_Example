package frc.robot.PID;

import edu.wpi.first.wpilibj.PIDOutput;

public class MyOwnGyro_output implements PIDOutput {

    //一个成员变量，用于存储pid计算的结果
    private double value = 0;

    public double getValue() {
        //用户调用这个自定义的方法即可返回最终pid的计算结果
        return value;
    }

    @Override
    public void pidWrite(double output) {
        //在这里可以先处理结果，然后再赋值给成员变量
        value = output;
    }
}
