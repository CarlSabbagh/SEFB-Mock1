import HES.Systems.EnvironmentalSystem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SensorGrid {

    private ArrayList<Sensor> sensors;      // the objects to show: received from Main, NOT created here
    private JPanel[] panels;            // one panel per cell (so it can turn red)
    private JLabel[] labels;            // one label per cell (so its text can change)

    // Build the window ONCE
    public SensorGrid(EnvironmentalSystem env,ArrayList<Sensor> sensors) {
        this.sensors = sensors;
        int n = sensors.size();                          // one cell per object
        panels = new JPanel[n];
        labels = new JLabel[n];

        JFrame frame = new JFrame("Monitor");
        frame.setLayout(new GridLayout(1, n));         // (rows, columns)
        for (int i = 0; i < n; i++) {
            panels[i] = new JPanel();
            labels[i] = new JLabel("");
            panels[i].add(labels[i]);                  // label INSIDE the panel → the panel can go red
            frame.add(panels[i]);
        }
        frame.setSize(1000, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Every tick: each object acts, then its cell shows its text and colour
    public void updateAll() {
        for (int i = 0; i < sensors.size(); i++) {
            Sensor item = sensors.get(i);
            item.control();                                              // the object does its job
            labels[i].setText("<html>" + item.getCellText().replace("\n", "<br>") + "</html>");
            panels[i].setBackground(item.isOutOfRange() ? Color.RED : null); // null = back to normal
        }
    }

    // Every N ticks: the same information in the console
    public void log() {
        System.out.println("===== Report =====");
        for (Sensor item : sensors) {
            System.out.println(item.getCellText());
            System.out.println();
        }
    }
}