import HES.Systems.EnvironmentalSystem;


public class Temperature extends Sensor{
    private String WardName;
    private int WardID;


    // The constants
    private static final float Margin = 0.5f;
    private static final float[] Targets = {23f, 22f, 20f};

    // Constructors
    public Temperature(EnvironmentalSystem env, String WardName, int WardID){
        super(env);
        this.WardName = WardName;
        this.WardID = WardID;
    }

    // Helpers
    private float targets(){
        return Targets[WardID];
    }

    private float readTemp(){
        return getEnv().getTempFeed().getTemperature(WardID);
    }

    // Abstract Method
    public float diff() {
        return readTemp() - targets();
    }
    @Override
    public boolean isOutOfRange(){
        return Math.abs(diff()) > Margin;
    }

    @Override
    public void control() {
        if (diff() <-Margin) {
            getEnv().turnHeatingOn(WardID, true);
        } else if(diff() > Margin){
            getEnv().turnHeatingOn(WardID, false);
        }
    }

    @Override
    public String getCellText() {
        return WardName
                + "\nTemperature: " + String.format("%.1f", readTemp()) + " °C"
                + "\nTarget: " + targets() + " °C (± " + Margin + ")"
                + "\nHeating: " + getEnv().isHeatingOn(WardID);

    }
}
