package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
    public Joystick spaghetticon = new Joystick(0);

    public double get_Yaxis() {
        return DeadBits(spaghetticon.getY());
    }

    public double get_Xaxis() {
        return DeadBits(spaghetticon.getX());
    }

    public double get_Zaxis() {
        return DeadBits(spaghetticon.getZ());
    }

    public double get_Slider() {
        return spaghetticon.getThrottle();
    }

    public boolean get_Turnbutton() {
        return spaghetticon.getRawButton(3);
    }

    public boolean get_GrabButton()
    {
        return spaghetticon.getTriggerPressed();
    }

    public boolean get_FullDriveToggle() {
        return spaghetticon.getRawButtonPressed(7);
    }
    public boolean get_HoldAngleDrive() {
        return spaghetticon.getRawButtonPressed(8);
    }
    private double DeadBits(double seb) {
        if (seb < -0.1 || seb > 0.1) {
            return seb;
        }
        return 0;
    }
}
