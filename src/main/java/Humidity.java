import HES.Systems.EnvironmentalSystem;

public class Humidity extends Sensor{

    // Constructor

    public Humidity (EnvironmentalSystem env){
        super(env);
    }

    // Methods to get the values

    public double getHumInside(){
        return getEnv().getHumidityFeed().getInsideHumidity();
    }

    public double getHumOutside(){
        return getEnv().getHumidityFeed().getOutsideHumidity();
    }

    // Constant

    private double MAX_HumidityDiff() {
        return 0.1 * getHumOutside();
    }

    // Abstract methods
    double diff() {
        return getHumInside() - getHumOutside();
    }
    @Override
    public boolean isOutOfRange(){
        return diff() > MAX_HumidityDiff();

    }

    @Override
    public void control(){
        if (isOutOfRange()){
            getEnv().turnDehumidifierOn(true);
        } else{
            getEnv().turnDehumidifierOn(false);
        }
    }

    @Override
    public String getCellText(){
        return "Humidity Inside: " + getHumInside() + "\nHumidity Outside: " + getHumOutside() + "\nDifference: " + diff() + "\nPurifier: " + getEnv().isDehumidifierOn();
    }
}
