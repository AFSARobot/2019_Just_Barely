package frc.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;

public class GrabWithServo {
    private Servo servoLeft, servoRight;

    public enum State {
        grabbed, released, grabbing, releasing
    };

    public State currentState = State.released;
    Preferences prefs;

    public GrabWithServo(Servo left, Servo right) {
        prefs = Preferences.getInstance();
        servoLeft = left;
        servoRight = right;
    }

    public void collect() {
        currentState = State.grabbing;
        servoLeft.set(prefs.getDouble("GrabAngleCollectLeft", 81));
        servoLeft.set(prefs.getDouble("GrabAngleCollectRight", 99));
    }

    public void release() {
        currentState = State.releasing;
        servoLeft.set(prefs.getDouble("GrabAngleCollectLeft", 90));
        servoLeft.set(prefs.getDouble("GrabAngleCollectRight", 90));
    }
}