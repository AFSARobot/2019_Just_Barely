package frc.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class Rangefinder {
    AnalogInput rangeFinder;

    public Rangefinder(int analogChannel) {
        rangeFinder = new AnalogInput(analogChannel);
    }

    private double convertVoltageToRange(double signal)
    {
        return signal * 60;
    }

    public double getRange()
    {
        double signal = rangeFinder.getVoltage();
        double distance = convertVoltageToRange(signal);
        return distance;
    }

}