package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private ArrayList<Character> characterList;

    public MainWindow(ArrayList<Character> characterList) {
        super("Character Manager v0.5");

        this.characterList = characterList;

        initComponents();

        pack();
        setMinimumSize(new Dimension(650, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBackground(AppTheme.BG_MAIN);
        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(16, 16, 16, 16)
        );

        // NORTH: Header dan panel statistik
        mainPanel.add(
                createHeaderPanel(),
                BorderLayout.NORTH
        );

        // CENTER: Daftar karakter dalam JScrollPane
        mainPanel.add(
                createCharacterScrollPane(),
                BorderLayout.CENTER
        );

        // EAST: Legenda role
        mainPanel.add(
                createLegendPanel(),
                BorderLayout.EAST
        );

        // SOUTH: Status bar
        mainPanel.add(
                createStatusBar(),
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    private JPanel createHeaderPanel() {

        JPanel headerPanel = new JPanel(
                new BorderLayout(0, 8)
        );

        headerPanel.setBackground(AppTheme.BG_MAIN);

        JLabel title = new JLabel(
                "Character Manager",
                SwingConstants.CENTER
        );

        title.setFont(AppTheme.FONT_TITLE);
        title.setForeground(AppTheme.TEXT_MAIN);

        headerPanel.add(
                title,
                BorderLayout.NORTH
        );

        // Panel tambahan: GridLayout 2 x 2
        JPanel statsPanel = new JPanel(
                new GridLayout(2, 2, 8, 6)
        );

        statsPanel.setBackground(AppTheme.BG_MAIN);

        statsPanel.add(
                createStatLabel(
                        "Total: " + characterList.size()
                )
        );

        statsPanel.add(
                createStatLabel(
                        "Avg Lv: "
                        + String.format("%.1f", getAverageLevel())
                )
        );

        statsPanel.add(
                createStatLabel(
                        "Highest Lv: " + getHighestLevel()
                )
        );

        statsPanel.add(
                createStatLabel(
                        "Roles: " + CharacterType.values().length
                )
        );

        headerPanel.add(
                statsPanel,
                BorderLayout.SOUTH
        );

        return headerPanel;
    }

    private JLabel createStatLabel(String text) {

        JLabel label = new JLabel(
                text,
                SwingConstants.CENTER
        );

        label.setFont(AppTheme.FONT_BODY);
        label.setForeground(AppTheme.TEXT_MAIN);

        label.setBorder(
                BorderFactory.createEmptyBorder(
                        6, 6, 6, 6
                )
        );

        return label;
    }

    private JScrollPane createCharacterScrollPane() {

        // Panel untuk kartu karakter
        JPanel cardsPanel = new JPanel(
                new GridLayout(0, 1, 0, 8)
        );

        cardsPanel.setBackground(AppTheme.BG_MAIN);

        for (Character character : characterList) {
            cardsPanel.add(
                    createCharacterLabel(character)
            );
        }

        /*
         * Panel pembungkus.
         * Grid kartu ditempatkan di NORTH agar kartu
         * tetap berkumpul di bagian atas area scroll.
         */
        JPanel wrapperPanel = new JPanel(
                new BorderLayout()
        );

        wrapperPanel.setBackground(AppTheme.BG_MAIN);

        wrapperPanel.add(
                cardsPanel,
                BorderLayout.NORTH
        );

        JScrollPane scroll = new JScrollPane(
                wrapperPanel
        );

        scroll.setPreferredSize(
                new Dimension(470, 280)
        );

        scroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        return scroll;
    }

    private JLabel createCharacterLabel(Character c) {

        String info =
                c.getName()
                + " | " + c.getType()
                + " | Lv." + c.getLevel()
                + " | HP:" + c.getHitPoints()
                + " | ATK:" + c.getAttackPower();

        JLabel label = new JLabel(info);

        label.setFont(AppTheme.FONT_BODY);
        label.setForeground(AppTheme.TEXT_LIGHT);

        label.setBackground(
                AppTheme.colorFor(c.getType())
        );

        label.setOpaque(true);

        label.setBorder(
                AppTheme.CARD_BORDER
        );

        label.setToolTipText(
                AppTheme.describe(c.getType())
        );

        label.setCursor(
                Cursor.getPredefinedCursor(
                        Cursor.HAND_CURSOR
                )
        );

        return label;
    }

    private JPanel createLegendPanel() {

        JPanel legendPanel = new JPanel();

        legendPanel.setLayout(
                new BoxLayout(
                        legendPanel,
                        BoxLayout.Y_AXIS
                )
        );

        legendPanel.setBackground(
                AppTheme.BG_MAIN
        );

        legendPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        4, 8, 4, 4
                )
        );

        JLabel title = new JLabel("LEGEND");

        title.setFont(AppTheme.FONT_BODY);
        title.setForeground(AppTheme.TEXT_MAIN);

        legendPanel.add(title);

        legendPanel.add(
                Box.createVerticalStrut(12)
        );

        // Legend dibuat dari enum Model
        for (CharacterType type : CharacterType.values()) {

            JLabel roleLabel = new JLabel(
                    type.toString()
            );

            roleLabel.setFont(
                    AppTheme.FONT_BODY
            );

            roleLabel.setForeground(
                    AppTheme.colorFor(type)
            );

            roleLabel.setToolTipText(
                    AppTheme.describe(type)
            );

            legendPanel.add(roleLabel);

            legendPanel.add(
                    Box.createVerticalStrut(8)
            );
        }

        legendPanel.add(
                Box.createVerticalGlue()
        );

        return legendPanel;
    }

    private JPanel createStatusBar() {

        JPanel statusBar = new JPanel(
                new BorderLayout()
        );

        statusBar.setBackground(
                AppTheme.BG_MAIN
        );

        statusBar.setBorder(
                BorderFactory.createEmptyBorder(
                        8, 0, 0, 0
                )
        );

        JLabel statusText = new JLabel(
                "Character Manager"
        );

        statusText.setFont(
                AppTheme.FONT_BODY
        );

        statusText.setForeground(
                AppTheme.TEXT_MAIN
        );

        JLabel versionText = new JLabel(
                "v0.5"
        );

        versionText.setFont(
                AppTheme.FONT_MONO
        );

        versionText.setForeground(
                AppTheme.TEXT_MAIN
        );

        statusBar.add(
                statusText,
                BorderLayout.WEST
        );

        statusBar.add(
                versionText,
                BorderLayout.EAST
        );

        return statusBar;
    }

    private double getAverageLevel() {

        if (characterList.isEmpty()) {
            return 0;
        }

        int total = 0;

        for (Character character : characterList) {
            total += character.getLevel();
        }

        return (double) total / characterList.size();
    }

    private int getHighestLevel() {

        int highest = 0;

        for (Character character : characterList) {

            if (character.getLevel() > highest) {
                highest = character.getLevel();
            }
        }

        return highest;
    }
}