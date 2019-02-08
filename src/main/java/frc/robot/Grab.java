package frc.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;

public class Grab{
    private Timer GrabTimer;
    private SpeedController motor;
    public enum State{
        grabbed, released, grabbing, releasing
    };

    public State currentState = State.released;
    Preferences prefs;
    public Grab(SpeedController controller){
        motor = controller;
  prefs = Preferences.getInstance();
  GrabTimer = new Timer();
    }

    public void collect(){
        currentState = State.grabbing;
        motor.set(prefs.getDouble("GrabSpeed", 0.5));
        GrabTimer.start();
    }

    public void release(){
        currentState = State.releasing;
        motor.set(prefs.getDouble("ReleaseSpeed", -0.5));
        GrabTimer.start();
    }
    public void stopMotor() {
        motor.set(0);
    }
    public void operate() {
        
        if (GrabTimer.get()>1) {
            switch(currentState) {
            case grabbing:
            currentState = State.grabbed;
            break;
            case releasing:
            currentState = State.released;
            break;
            }
            GrabTimer.stop();
        }
        switch(currentState) {
            case grabbing:
            motor.set(prefs.getDouble("GrabSpeed", 0.5));
            break;
            case releasing:
            motor.set(prefs.getDouble("ReleaseSpeed",-0.5));
            break;
            case grabbed:
            case released:
            default:
            motor.set(0);
            
        }
    }
}