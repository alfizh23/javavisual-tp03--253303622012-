
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    // Batas level untuk Mini-Challenge
    private static final int GOLD_LEVEL_THRESHOLD = 5;

    private final CharacterManager manager;

    private JPanel mainPanel;
    private JPanel characterPanel;

    private JLabel totalLabel;
    private JLabel averageLabel;

    private JButton addButton;

    public MainWindow(CharacterManager manager) {

        super("Character Manager TP10 - v0.4");

        this.manager = manager;

        initComponents();
        refreshCharacters();
        updateStatistics();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {

        mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBackground(AppTheme.BACKGROUND_COLOR);
        mainPanel.setBorder(AppTheme.PANEL_BORDER);

        // Judul aplikasi
        JLabel titleLabel = new JLabel(
                "CHARACTER MANAGER",
                SwingConstants.CENTER
        );

        titleLabel.setFont(AppTheme.TITLE_FONT);
        titleLabel.setForeground(AppTheme.TEXT_COLOR);

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel daftar karakter
        characterPanel = new JPanel();

        characterPanel.setLayout(
                new BoxLayout(characterPanel, BoxLayout.Y_AXIS)
        );

        characterPanel.setBackground(AppTheme.BACKGROUND_COLOR);

        JScrollPane scrollPane = new JScrollPane(characterPanel);

        scrollPane.setPreferredSize(
                new java.awt.Dimension(620, 320)
        );

        scrollPane.setBorder(AppTheme.SCROLL_BORDER);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel statistik
        JPanel bottomPanel = new JPanel(
                new BorderLayout(8, 8)
        );

        bottomPanel.setBackground(AppTheme.BACKGROUND_COLOR);

        JPanel statisticsPanel = new JPanel(
                new GridLayout(1, 2, 10, 0)
        );

        statisticsPanel.setBackground(AppTheme.BACKGROUND_COLOR);

        totalLabel = new JLabel();
        averageLabel = new JLabel();

        totalLabel.setFont(AppTheme.STAT_FONT);
        totalLabel.setForeground(AppTheme.TEXT_COLOR);

        averageLabel.setFont(AppTheme.STAT_FONT);
        averageLabel.setForeground(AppTheme.TEXT_COLOR);

        statisticsPanel.add(totalLabel);
        statisticsPanel.add(averageLabel);

        bottomPanel.add(statisticsPanel, BorderLayout.NORTH);

        // Tombol tambah karakter
        JPanel buttonPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER)
        );

        buttonPanel.setBackground(AppTheme.BACKGROUND_COLOR);

        addButton = new JButton("Tambah Karakter");

        addButton.setFont(AppTheme.BOLD_FONT);
        addButton.setBackground(AppTheme.BUTTON_COLOR);
        addButton.setForeground(AppTheme.CARD_BACKGROUND);
        addButton.setFocusPainted(false);

        addButton.addActionListener(e -> addNewCharacter());

        buttonPanel.add(addButton);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Menampilkan semua karakter dari CharacterManager
    private void refreshCharacters() {

        characterPanel.removeAll();

        ArrayList<Character> characters = manager.getAllCharacters();

        for (Character character : characters) {
            characterPanel.add(createCharacterCard(character));
        }

        characterPanel.revalidate();
        characterPanel.repaint();
    }

    // Membuat kartu karakter
    private JPanel createCharacterCard(Character character) {

        JPanel card = new JPanel(new BorderLayout(12, 5));

        // Properti tambahan 1: warna kartu mengikuti role
        card.setBackground(AppTheme.colorFor(character.getType()));
        card.setOpaque(true);
        card.setBorder(AppTheme.CARD_BORDER);

        // Nama karakter
        JLabel nameLabel = new JLabel(character.getName());

        nameLabel.setFont(AppTheme.BODY_FONT);
        nameLabel.setForeground(AppTheme.TEXT_COLOR);

        // Mini-Challenge: level minimal 5 berwarna emas dan tebal
        if (character.getLevel() >= GOLD_LEVEL_THRESHOLD) {

            nameLabel.setForeground(AppTheme.GOLD_COLOR);
            nameLabel.setFont(AppTheme.BOLD_FONT);
        }

        // Properti tambahan 2: tooltip informasi karakter
        card.setToolTipText(
                "Nama: " + character.getName()
                + " | Role: " + character.getType()
                + " | Level: " + character.getLevel()
                + " | HP: " + character.getHitPoints()
                + " | ATK: " + character.getAttackPower()
                + " | Mana: " + character.getMana()
        );

        // Detail karakter
        JLabel detailLabel = new JLabel(
                "Role: " + character.getType()
                + "   |   Level: " + character.getLevel()
                + "   |   HP: " + character.getHitPoints()
                + "   |   ATK: " + character.getAttackPower()
        );

        detailLabel.setFont(AppTheme.BODY_FONT);
        detailLabel.setForeground(AppTheme.TEXT_COLOR);

        JPanel informationPanel = new JPanel(
                new GridLayout(2, 1, 0, 5)
        );

        informationPanel.setOpaque(false);

        informationPanel.add(nameLabel);
        informationPanel.add(detailLabel);

        card.add(informationPanel, BorderLayout.CENTER);

        // Status karakter
        JLabel statusLabel = new JLabel(
                character.isAlive() ? "ALIVE" : "DEFEATED"
        );

        statusLabel.setFont(AppTheme.BOLD_FONT);
        statusLabel.setForeground(AppTheme.SECONDARY_TEXT);

        card.add(statusLabel, BorderLayout.EAST);

        // Karakter level 1 memiliki nama nonaktif
        if (character.getLevel() == 1) {

            nameLabel.setEnabled(false);

            nameLabel.setToolTipText(
                    "Karakter level 1: nama dinonaktifkan sebagai penanda"
            );
        }

        return card;
    }

    // Menambahkan karakter baru
    private void addNewCharacter() {

        Character newCharacter = new Character(
                "Nova",
                CharacterType.MAGE,
                5
        );

        boolean added = manager.addCharacter(newCharacter);

        if (added) {

            refreshCharacters();
            updateStatistics();

            addButton.setText("Nova Berhasil Ditambahkan");
            addButton.setEnabled(false);

        } else {

            addButton.setText("Nama Nova Sudah Ada");
        }
    }

    // Memperbarui statistik
    private void updateStatistics() {

        totalLabel.setText(
                "Total Karakter: " + manager.getTotalCharacters()
        );

        averageLabel.setText(
                String.format(
                        java.util.Locale.US,
                        "Rata-rata Level: %.2f",
                        manager.getAverageLevel()
                )
        );
    }
}
