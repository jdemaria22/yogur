package com.yogur.panel.scripts;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CooldownTracker {
    public static String title = "Drawings";
    private static JCheckBox checkbox = new JCheckBox("Enabled");
    private static JCheckBox checkboxCooldownTracker = new JCheckBox("Cooldown Tracker");
    private static JCheckBox checkboxMinionsTracker = new JCheckBox("Minions Tracker");
    public static JPanel createPanel(){

        JPanel jPanel= new JPanel();
        jPanel.add(checkbox);
        jPanel.add(checkboxCooldownTracker);
        jPanel.add(checkboxMinionsTracker);
        return jPanel;
    }
    public static boolean isEnabled(){ return checkbox.isSelected(); }
    public static boolean cooldownTrackerIsEnabled(){
        return checkboxCooldownTracker.isSelected();
    }
    public static boolean minionsTrackerIsEnabled(){
        return checkboxMinionsTracker.isSelected();
    }

}
