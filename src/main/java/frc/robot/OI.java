package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
    public Joystick spaghetticon = new Joystick(0);

    public double get_Yaxis() {
        return DeadBits(spaghetticon.getRawAxis(1));
    }

    public double get_Xaxis() {
        return DeadBits(spaghetticon.getRawAxis(0));
    }

    public double get_Zaxis() {
        return DeadBits(spaghetticon.getRawAxis(2));
    }

    public double get_Slider() {
        return spaghetticon.getRawAxis(3);
    }

    public boolean get_Turnbutton() {
        return spaghetticon.getRawButton(2);
    }

    public boolean get_GrabButton()
    {
        return spaghetticon.getRawButtonPressed(1);
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
    public boolean get_RampBackDown() {
        return spaghetticon.getRawButton(5);
    }
    public boolean get_RampBackUp() {
        return spaghetticon.getRawButton(6);
    }
    public boolean get_RampFrontDown() {
        return spaghetticon.getRawButton(3);
    }
    public boolean get_RampFrontUp() {
        return spaghetticon.getRawButton(4);
    }
    public int TurnThing() {
        return spaghetticon.getPOV();
    }
    public boolean IsRight() {
        return spaghetticon.getPOV() == 90;
    }
    public boolean IsLeft() {
        return spaghetticon.getPOV() == 270;
    }

    public boolean get_LiftUpButton()
    {
        return spaghetticon.getRawButton(1);
    }

    public boolean get_LiftDownButton()
    {
        return spaghetticon.getRawButton(2);
    }
}
