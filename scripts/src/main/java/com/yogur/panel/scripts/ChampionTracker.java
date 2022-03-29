package com.yogur.panel.scripts;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class ChampionTracker {
    public static String title = "ChampionTracker";
    private static JCheckBox checkbox = new JCheckBox("enabled");

    public static JPanel createPanel(){
        JPanel jPanel= new JPanel();
        jPanel.add(checkbox);
        return jPanel;
    }

    public static boolean isEnabled(){
       return checkbox.isSelected();
    }
}
