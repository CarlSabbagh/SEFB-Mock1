import HES.Systems.EnvironmentalSystem;

public abstract class Sensor {   // Always put abstract for the parent class otherwise I won't be able to have my abstract methods
    private EnvironmentalSystem env;

    // Constructors

    public Sensor(EnvironmentalSystem env){  // no need to put anything for the constructor
        this.env = env;
    }

    // Getters

    public EnvironmentalSystem getEnv(){
        return env;
    }

    // Abstract methods

    public abstract boolean isOutOfRange();
    public abstract void control();
    public abstract String getCellText();
}
