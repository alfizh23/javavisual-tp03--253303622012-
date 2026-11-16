
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private CharacterManager manager;

    private JPanel mainPanel;
    private JPanel cardsPanel;

    private JLabel totalLabel;
    private JLabel averageLabel;
    private JLabel highestLabel;

    public MainWindow(CharacterManager manager) {

        super("Character Manager v0.6");

        this.manager = manager;

        initComponents();

        pack();
        setMinimumSize(new Dimension(750, 550));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        mainPanel = new JPanel(new BorderLayout(12, 12));

        mainPanel.setBackground(AppTheme.BG_MAIN);

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        16, 16, 16, 16
                )
        );

        // NORTH: Header, statistik, dan form
        JPanel topPanel = new JPanel(new BorderLayout(0, 12));

        topPanel.setBackground(AppTheme.BG_MAIN);

        topPanel.add(
                createHeaderPanel(),
                BorderLayout.NORTH
        );

        CharacterFormPanel formPanel =
                new CharacterFormPanel(
                        manager,
                        this::refreshList
                );

        topPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                topPanel,
                BorderLayout.NORTH
        );

        // CENTER: Daftar karakter
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

        // Menampilkan data awal
        refreshList();
    }

    // Membuat header dan statistik
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

        JPanel statsPanel = new JPanel(
                new GridLayout(1, 3, 8, 6)
        );

        statsPanel.setBackground(AppTheme.BG_MAIN);

        totalLabel = createStatLabel("Total: 0");

        averageLabel = createStatLabel("Average Level: 0");

        highestLabel = createStatLabel("Highest Level: 0");

        statsPanel.add(totalLabel);
        statsPanel.add(averageLabel);
        statsPanel.add(highestLabel);

        headerPanel.add(
                statsPanel,
                BorderLayout.SOUTH
        );

        return headerPanel;
    }

    // Membuat label statistik
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

    // Membuat area scroll daftar karakter
    private JScrollPane createCharacterScrollPane() {

        cardsPanel = new JPanel(
                new GridLayout(0, 1, 0, 8)
        );

        cardsPanel.setBackground(AppTheme.BG_MAIN);

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

    // Memperbarui daftar karakter dan statistik
    public void refreshList() {

        cardsPanel.removeAll();

        // Mengambil data terbaru dari Manager
        for (Character character : manager.getAllCharacters()) {

            cardsPanel.add(
                    createCharacterLabel(character)
            );
        }

        // Memperbarui statistik
        totalLabel.setText(
                "Total: " + manager.getTotalCharacters()
        );

        averageLabel.setText(
                "Average Level: "
                + String.format("%.1f", manager.getAverageLevel())
        );

        highestLabel.setText(
                "Highest Level: " + getHighestLevel()
        );

        // Memperbarui tampilan Swing
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    // Membuat kartu karakter
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

    // Membuat legenda role
    private JPanel createLegendPanel() {

        JPanel legendPanel = new JPanel();

        legendPanel.setLayout(
                new BoxLayout(
                        legendPanel,
                        BoxLayout.Y_AXIS
                )
        );

        legendPanel.setBackground(AppTheme.BG_MAIN);

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

        for (CharacterType type : CharacterType.values()) {

            JLabel roleLabel = new JLabel(
                    type.toString()
            );

            roleLabel.setFont(AppTheme.FONT_BODY);

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

    // Membuat status bar
    private JPanel createStatusBar() {

        JPanel statusBar = new JPanel(
                new BorderLayout()
        );

        statusBar.setBackground(AppTheme.BG_MAIN);

        statusBar.setBorder(
                BorderFactory.createEmptyBorder(
                        8, 0, 0, 0
                )
        );

        JLabel statusText = new JLabel(
                "Character Manager"
        );

        statusText.setFont(AppTheme.FONT_BODY);
        statusText.setForeground(AppTheme.TEXT_MAIN);

        JLabel versionText = new JLabel("v0.6");

        versionText.setFont(AppTheme.FONT_MONO);
        versionText.setForeground(AppTheme.TEXT_MAIN);

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

    // Mencari level tertinggi
    private int getHighestLevel() {

        int highest = 0;

        for (Character character : manager.getAllCharacters()) {

            if (character.getLevel() > highest) {
                highest = character.getLevel();
            }
        }

        return highest;
    }
}
