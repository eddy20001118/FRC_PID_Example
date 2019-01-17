package frc.robot.PID;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class MyOwnGyro_source implements PIDSource {
    private Encoder m_leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
    private Encoder m_rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);

    //pidSourceType指定是输入的数据是速率（可为负，代表方向），还是位移的距离（非负）
    private PIDSourceType pidSourceType = PIDSourceType.kRate;

    //用户可以动态的改变pidSourceType（不建议）
    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
        pidSourceType = pidSource;
    }

    //用户可以获取pidSourceType，查看输入数据的类型（没啥用）
    @Override
    public PIDSourceType getPIDSourceType() {
        return pidSourceType;
    }

    //用户必须在这个接口中返回（处理后）PID的输入数据，通常来自传感器
    @Override
    public double pidGet() {
        //这里返回左右两边编码器速率的平均值
        return (m_leftEncoder.getRate() + m_rightEncoder.getRate())/2;
    }
}
