package frc.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;

public class GrabWithServo {
    private Servo servoLeft, servoRight;

    public enum State {
        grabbed, released
    };

    public State currentState = State.released;
    Preferences prefs;

    public GrabWithServo(Servo left, Servo right) {
        prefs = Preferences.getInstance();
        servoLeft = left;
        servoRight = right;
    }

    public void collect() {
        currentState = State.grabbed;
        servoLeft.set(prefs.getDouble("GrabAngleCollectLeft", 0.5));
        servoRight.set(prefs.getDouble("GrabAngleCollectRight", 0.5));
    }

    public void release() {
        currentState = State.released;
        servoLeft.set(prefs.getDouble("GrabAngleReleaseLeft", 0.5));
        servoRight.set(prefs.getDouble("GrabAngleReleaseRight", 0.5));
    }
}