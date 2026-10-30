
package com.unpri.charactermanager.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel characterPanel;
    private JLabel averageLabel;
    private JLabel totalLabel;

    private ArrayList<Character> characterList;

    public MainWindow(ArrayList<Character> characterList,
                      double averageLevel) {

        super("Character Manager TP8");

        this.characterList = new ArrayList<>(characterList);

        initComponents();

        setSize(650, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        updateStatistics();
    }

    private void initComponents() {

        mainPanel = new JPanel(new BorderLayout(10, 10));

        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );

        // Judul aplikasi
        JLabel titleLabel = new JLabel(
            "CHARACTER MANAGER",
            JLabel.CENTER
        );

        titleLabel.setFont(
            new java.awt.Font("Arial", java.awt.Font.BOLD, 22)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel daftar karakter
        characterPanel = new JPanel();

        characterPanel.setLayout(
            new GridLayout(0, 1, 5, 5)
        );

        displayCharacters();

        JScrollPane scrollPane = new JScrollPane(characterPanel);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel bagian bawah
        JPanel bottomPanel = new JPanel(
            new BorderLayout(5, 5)
        );

        JPanel statisticsPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER)
        );

        averageLabel = new JLabel();
        totalLabel = new JLabel();

        averageLabel.setFont(
            new java.awt.Font("Arial", java.awt.Font.BOLD, 15)
        );

        totalLabel.setFont(
            new java.awt.Font("Arial", java.awt.Font.BOLD, 15)
        );

        statisticsPanel.add(totalLabel);
        statisticsPanel.add(new JLabel(" | "));
        statisticsPanel.add(averageLabel);

        bottomPanel.add(statisticsPanel, BorderLayout.NORTH);

        // Tombol menambahkan karakter
        JButton addButton = new JButton("Tambah Karakter");

        addButton.addActionListener(e -> {

            Character newCharacter = new Character(
                "Nova", CharacterType.MAGE, 5
            );

            if (characterList.add(newCharacter)) {

                displayCharacters();
                updateStatistics();

                addButton.setEnabled(false);
                addButton.setText("Karakter Berhasil Ditambahkan");
            }
        });

        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER)
        );

        buttonPanel.add(addButton);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Menampilkan karakter secara dinamis
    private void displayCharacters() {

        characterPanel.removeAll();

        for (Character character : characterList) {

            JLabel characterLabel = new JLabel(
                "Nama: " + character.getName()
                + " | Tipe: " + character.getType()
                + " | Level: " + character.getLevel()
                + " | HP: " + character.getHitPoints()
                + " | ATK: " + character.getAttackPower()
            );

            characterLabel.setBorder(
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
            );

            characterPanel.add(characterLabel);
        }

        characterPanel.revalidate();
        characterPanel.repaint();
    }

    // Menghitung statistik berdasarkan data terbaru
    private void updateStatistics() {

        int totalLevel = 0;

        for (Character character : characterList) {
            totalLevel += character.getLevel();
        }

        double averageLevel = 0;

        if (!characterList.isEmpty()) {
            averageLevel = (double) totalLevel / characterList.size();
        }

        totalLabel.setText(
            "Total Karakter: " + characterList.size()
        );

        averageLabel.setText(
            "Rata-rata Level: "
            + String.format("%.2f", averageLevel)
        );
    }
}
