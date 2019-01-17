package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MyGeneralSubsystem extends Subsystem {

    private SpeedController motor1 = new Spark(0);

    @Override
    protected void initDefaultCommand() {

    }

    public void setSpeed(double speed) {
        motor1.set(speed);
    }
}
