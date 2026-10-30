
package com.unpri.charactermanager.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import com.unpri.charactermanager.model.Character;

public class MainWindow extends JFrame {

    private JPanel mainPanel;
    private JPanel characterPanel;
    private JLabel averageLabel;

    private ArrayList<Character> characterList;
    private double averageLevel;

    public MainWindow(ArrayList<Character> characterList,
                      double averageLevel) {

        super("Character Manager TP8");

        this.characterList = characterList;
        this.averageLevel = averageLevel;

        initComponents();

        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
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
            new java.awt.Font("Arial", java.awt.Font.BOLD, 20)
        );

        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel daftar karakter
        characterPanel = new JPanel();

        characterPanel.setLayout(
            new GridLayout(0, 1, 5, 5)
        );

        // Menampilkan karakter secara dinamis
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

        JScrollPane scrollPane = new JScrollPane(characterPanel);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel statistik
        JPanel statisticsPanel = new JPanel(
            new FlowLayout(FlowLayout.CENTER)
        );

        averageLabel = new JLabel(
            "Rata-rata Level: "
            + String.format("%.2f", averageLevel)
        );

        averageLabel.setFont(
            new java.awt.Font("Arial", java.awt.Font.BOLD, 15)
        );

        statisticsPanel.add(averageLabel);

        mainPanel.add(statisticsPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
}
