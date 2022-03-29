package com.yogur.panel.scripts;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Orbwalker {
    public static String title = "Orbwalker";
    private static JCheckBox checkbox = new JCheckBox("Enabled");

    private static SpinnerNumberModel ping = new SpinnerNumberModel(
            Integer.valueOf(50),
            Integer.valueOf(0),
            Integer.valueOf(200),
            Integer.valueOf(1)
    );

    public static JPanel createPanel(){
        JSpinner spnNumber = new JSpinner(ping);
        JPanel jPanel= new JPanel();
        jPanel.add(checkbox);
        jPanel.add(new JLabel("Ping:"));
        jPanel.add(spnNumber);
        return jPanel;
    }

    public static boolean isEnabled(){
        return checkbox.isSelected();
    }

    public static Integer getPing() { return (Integer)ping.getValue();}
}
