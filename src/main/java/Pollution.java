import HES.Systems.EnvironmentalSystem;

public class Pollution extends Sensor{

    // Constructor

    public Pollution(EnvironmentalSystem env){
        super(env);
    }

    // Constant

    private static final double MAX_Pollution = 5.0;
    private double readPollution(){
        return getEnv().getPollutionFeed().getPollution();
    }



    // Methods

    @Override
    public boolean isOutOfRange(){
        return readPollution() > MAX_Pollution;
    }

    @Override
    public void control(){
        if (isOutOfRange()){
          getEnv().turnAirPurifierOn(true);
        } else {
            getEnv().turnAirPurifierOn(false);
        }
    }

    @Override
    public String getCellText(){
        return "Pollution: " + readPollution() + "\nMax pollution: " + MAX_Pollution + "\nPurifier: " + getEnv().isAirPurifierOn();
    }

}
