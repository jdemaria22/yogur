package com.yogur.panel;

import com.yogur.panel.scripts.AutoSpell;
import com.yogur.panel.scripts.ChampionTracker;
import com.yogur.panel.scripts.CooldownTracker;
import com.yogur.panel.scripts.InfoTracker;
import com.yogur.panel.scripts.Orbwalker;

import javax.swing.*;

public class UiFrame {
    public static JFrame frameGlobal = new JFrame();
    public static JTabbedPane tp =new JTabbedPane();
    public static JScrollPane jScrollPane = new JScrollPane();
    public static void initFrame(){
        frameGlobal.setTitle(String.valueOf(getRandomNumber(111111111, 999999999)));
        frameGlobal.setSize(640, 480);
        frameGlobal.setLocationRelativeTo(null);
        frameGlobal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.add(Orbwalker.title, Orbwalker.createPanel());
        tp.add(CooldownTracker.title, CooldownTracker.createPanel());
        tp.add(ChampionTracker.title, ChampionTracker.createPanel());
        tp.add(AutoSpell.title, AutoSpell.createPanel());
        jScrollPane = new JScrollPane(InfoTracker.createPanel());
        tp.add(InfoTracker.title, jScrollPane);
        tp.setBounds(10, 10, 605, 410);
        frameGlobal.add(tp);
        frameGlobal.setLayout(null);
        frameGlobal.setVisible(true);
    }

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    public static void hideJFrame(){
        frameGlobal.setVisible(false);
    }
    public static void showJFrame(){
        frameGlobal.setVisible(true);
    }
}
