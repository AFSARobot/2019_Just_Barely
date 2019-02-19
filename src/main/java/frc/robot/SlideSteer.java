package frc.robot;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import java.lang.Math;

public class SlideSteer extends RobotDriveBase {
    private SpeedController left_forward;
    private SpeedController right_forward;
    private SpeedController front_side;
    private SpeedController back_side;

    public SlideSteer(SpeedController left, SpeedController right, SpeedController front, SpeedController back) {
        left_forward = left;
        right_forward = right;
        front_side = front;
        back_side = back;

        right_forward.setInverted(true);
        front_side.setInverted(true);
    }

    public void DriveFunction(double x, double y) {
        left_forward.set(y);
        right_forward.set(y);
        front_side.set(x);
        back_side.set(x);
        feedWatchdog();
    }

    public void AntiStrafe(double turn) {
        left_forward.set(turn);
        right_forward.set(-turn);
        front_side.set(turn);
        back_side.set(-turn);
        
        feedWatchdog();

    }
    public void FullDrive(double get_Xaxis, double get_Yaxis, double get_Zaxis) {
       double LFspeed = get_Yaxis + get_Zaxis; //2
        double RFspeed = get_Yaxis - get_Zaxis;//0
        double FSspeed = get_Xaxis - get_Zaxis;//0
        double BSspeed = get_Xaxis + get_Zaxis;//2
        double AB = Math.max(Math.abs(LFspeed), Math.abs(RFspeed));
        double CD = Math.max(Math.abs(FSspeed), Math.abs(BSspeed));
        double E = Math.max(Math.abs(AB),Math.abs(CD));
        if (E > 1) {
            LFspeed /= E;
            RFspeed /= E;
            FSspeed /= E;
            BSspeed /= E;
        }
        left_forward.set(LFspeed);
        right_forward.set(RFspeed);
        front_side.set(FSspeed);
        back_side.set(BSspeed);
        feedWatchdog();
    }

    @Override
    public void initSendable(SendableBuilder builder) {

    }

    @Override
    public void stopMotor() {
        left_forward.set(0);
        right_forward.set(0);
        front_side.set(0);
        back_side.set(0);
        feedWatchdog();
    }

    @Override
    public String getDescription() {
        return null;
    }

}