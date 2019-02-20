package frc.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.SpeedController;

    public class Ramp {
private SpeedController ramp1;
public enum Rampstate {
    extendedFront, retractedFront, extendedBack, retractedBack
};
public Rampstate currentState = Rampstate.retractedFront;
private SpeedController ramp2;
Preferences prefs;

public Ramp(SpeedController control1, SpeedController control2){
ramp1 = control1; 
ramp2 = control2;
prefs = Preferences.getInstance();
}
public void extendFront() {
 currentState = Rampstate.extendedFront;
   ramp2.set(prefs.getDouble("RampDeploySpeed", 0.5)); 
}
public void retractFront() {
    currentState = Rampstate.retractedFront;
    ramp2.set(prefs.getDouble("RampRetractSpeed", -0.5));
}
public void stopFront(){
    ramp2.stopMotor();
}
public void stopMotor() {
    ramp1.set(0);
    ramp2.set(0);
}
public void extendBack() {
    currentState = Rampstate.extendedBack;
    ramp1.set(prefs.getDouble("RampDeploySpeed", 0.5));
}
public void retractBack() {
    currentState = Rampstate.retractedBack;
    ramp1.set(prefs.getDouble("RampRetractSpeed", 0.5));
}
public void stopBack(){
    ramp1.stopMotor();
}
}