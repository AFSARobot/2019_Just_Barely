package frc.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedController;

    public class Ramp {
private SpeedController ramp1;
public enum Rampstate {
    extended, retracted
};
public Rampstate currentState = Rampstate.retracted;
private SpeedController ramp2;
Preferences prefs;

public Ramp(SpeedController control1, SpeedController control2){
ramp1 = control1; 
ramp2 = control2;
prefs = Preferences.getInstance();
}
public void extend() {
 currentState = Rampstate.extended;
   ramp1.set(prefs.getDouble("RampDeploySpeed", 0.5));
   ramp2.set(prefs.getDouble("RampDeploySpeed", 0.5)); 
}
public void retract() {
    currentState = Rampstate.retracted;
    ramp1.set(prefs.getDouble("RampRetractSpeed", -0.5));
    ramp2.set(prefs.getDouble("RampRetractSpeed", -0.5));
}
public void stopMotor() {
    ramp1.set(0);
    ramp2.set(0);
}


}