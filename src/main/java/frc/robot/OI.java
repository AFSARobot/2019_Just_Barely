package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
    public Joystick spaghetticon = new Joystick(0);

    public double get_Yaxis() {
        return spaghetticon.getY();
    }

    public double get_Xaxis() {
        return spaghetticon.getX();
    }

    public double get_Zaxis() {
        return spaghetticon.getZ();
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
}
