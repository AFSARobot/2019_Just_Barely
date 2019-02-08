package frc.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedController;

public class Lift {

private SpeedController liftact;
public enum LiftState{
    raised,lowered
};
public LiftState currentState = LiftState.lowered;
Preferences prefs;

public Lift(SpeedController Raise){
    liftact = Raise;
    prefs = Preferences.getInstance();
}
public void Raise() {
    currentState = LiftState.raised;
    liftact.set(prefs.getDouble("LiftSpeed", 0.5));
}
public void Lower() {
    currentState = LiftState.lowered;
    liftact.set(prefs.getDouble("LowerSpeed", -0.5));
}
public void stopMotor() {
    liftact.set(0);
}
}