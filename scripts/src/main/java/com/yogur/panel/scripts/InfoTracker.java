package com.yogur.panel.scripts;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfoTracker {
    public static String title = "Info Tracked";
    private static final JCheckBox checkbox = new JCheckBox("Enabled");
    private static final JPanel jPanel= new JPanel();
    private static final JLabel jTeam = new JLabel();
    private static final JLabel jPositionx = new JLabel();
    private static final JLabel jPositiony = new JLabel();
    private static final JLabel jPositionz = new JLabel();
    private static final JLabel jHealth = new JLabel();
    private static final JLabel jMaxHealth = new JLabel();
    private static final JLabel jBaseAttack = new JLabel();
    private static final JLabel jBonusAttack = new JLabel();
    private static final JLabel jArmor = new JLabel();
    private static final JLabel jBonusArmor = new JLabel();
    private static final JLabel jMagicResist = new JLabel();
    private static final JLabel jDuration = new JLabel();
    private static final JLabel jIsVisible = new JLabel();
    private static final JLabel jObjectIndex = new JLabel();
    private static final JLabel jCrit = new JLabel();
    private static final JLabel jCritMulti = new JLabel();
    private static final JLabel jAbilityPower = new JLabel();
    private static final JLabel jAttackSpeedMulti = new JLabel();
    private static final JLabel jMovementSpeed = new JLabel();
    private static final JLabel jNetworkID = new JLabel();
    private static final JLabel jAttackRange = new JLabel();
    private static final JLabel jLevel = new JLabel();
    private static final JLabel jIsTargetable = new JLabel();
    private static final JLabel jSizeMultiplier = new JLabel();
    private static final JLabel jSpawnCount = new JLabel();
    private static final JLabel jIsAlive = new JLabel();
    public static final Map<String, Object> map = new HashMap<>();
    public static List<String> stringList= Arrays.asList(
            "team",
            "position.x",
            "position.y",
            "position.z",
            "health",
            "maxHealth",
            "baseAttack",
            "bonusAttack",
            "armor",
            "bonusArmor",
            "magicResist",
            "duration",
            "isVisible",
            "objectIndex",
            "crit",
            "critMulti",
            "abilityPower",
            "attackSpeedMulti",
            "movementSpeed",
            "networkID",
            "attackRange",
            "level",
            "isTargetable",
            "sizeMultiplier",
            "spawnCount",
            "isAlive");
    public static JPanel createPanel(){
        jPanel.add(checkbox);
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
        //mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel l=new JLabel("Info Champion.", SwingConstants.LEFT);
        jPanel.add(l);
        return jPanel;
    }

    public static void updateInfo(@NotNull Map<String, Object> info){
        deleteInfo();
        info.forEach((key,value) -> {
            jPanel.add(new JLabel(key + ": " + value , SwingConstants.LEFT));
        });
        refreshInfo();
    }

    public static void refreshInfo(){
        jPanel.revalidate();
        jPanel.repaint();
    }

    public static void deleteInfo(){
        Component[] components = jPanel.getComponents();
        for (Component component : components){
            if (component instanceof JLabel){
                jPanel.remove(component);
            }
        }
    }

    public static boolean isEnabled(){
        return checkbox.isSelected();
    }


}
