package com.yogur.panel.scripts;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class AutoSpell {
    public static String title = "AutoSpell";
    private static final JCheckBox checkbox = new JCheckBox("enabled");
    private static final JCheckBox champAutoSpellScript = new JCheckBox("AutoSpell Script Specific Champ");
    private static final JCheckBox checkboxQ = new JCheckBox("enabledQ");
    private static final JCheckBox checkboxW= new JCheckBox("enabledW");
    private static final JCheckBox checkboxE = new JCheckBox("enabledE");
    private static final JCheckBox checkboxR = new JCheckBox("enabledR");

    public static JPanel createPanel(){
        JPanel jPanel= new JPanel();
        jPanel.add(checkbox);
        jPanel.add(champAutoSpellScript);
        jPanel.add(checkboxQ);
        jPanel.add(checkboxW);
        jPanel.add(checkboxE);
        jPanel.add(checkboxR);
        return jPanel;
    }

    public static boolean isEnabled(){
        return checkbox.isSelected();
    }
    public static boolean isEnabledQ(){
        return checkboxQ.isSelected();
    }
    public static boolean isEnabledSpecific(){
        return champAutoSpellScript.isSelected();
    }
    public static boolean isEnabledW(){
        return checkboxW.isSelected();
    }
    public static boolean isEnabledE(){
        return checkboxE.isSelected();
    }
    public static boolean isEnabledR(){
        return checkboxR.isSelected();
    }

}
