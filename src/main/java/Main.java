import HES.Systems.EnvironmentalSystem;
import javax.swing.Timer; import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Main {
    static int seconds = 0;
    private static final String[] WARD_NAMES = {"North Ward", "Central Ward", "South Ward"};

    public static void main(String[] args) {
// 1. CREATE EVERYTHING ONCE
        EnvironmentalSystem system = new EnvironmentalSystem();
        ArrayList<Sensor> sensors = new ArrayList<>();
        for (int id = 0; id < WARD_NAMES.length; id++) {
            sensors.add(new Temperature(system, WARD_NAMES[id], id));
        }
        sensors.add(new Pollution(system));
        sensors.add(new Humidity(system));

        SensorGrid grid = new SensorGrid(system, sensors);
        Timer timer = new Timer(1000, new ActionListener() { // 1000 ms = 1 second
            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                grid.updateAll();
                if (seconds % 30 == 0) { // ← REPLACE 30: "every N seconds"
                    grid.log();
                }
            }
        });
        timer.start(); // LAST line of main
    }
}
