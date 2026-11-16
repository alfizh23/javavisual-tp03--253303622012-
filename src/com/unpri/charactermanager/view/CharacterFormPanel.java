
package com.unpri.charactermanager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.model.CharacterType;

public class CharacterFormPanel extends JPanel {

    private JTextField nameField;
    private JComboBox<CharacterType> roleComboBox;
    private JSpinner levelSpinner;

    private JButton addButton;
    private JLabel errorLabel;

    private CharacterManager manager;
    private Runnable refreshCallback;

    private Border originalNameBorder;
    private Border originalRoleBorder;
    private Border originalLevelBorder;

    public CharacterFormPanel(
            CharacterManager manager,
            Runnable refreshCallback) {

        this.manager = manager;
        this.refreshCallback = refreshCallback;

        initComponents();
    }

    // Inisialisasi komponen
    private void initComponents() {

        setLayout(new BorderLayout(8, 8));
        setBackground(AppTheme.BG_MAIN);

        JPanel inputPanel = new JPanel(
                new GridLayout(2, 3, 8, 6)
        );

        inputPanel.setBackground(AppTheme.BG_MAIN);

        JLabel nameLabel = new JLabel("Character Name");
        nameLabel.setForeground(AppTheme.TEXT_MAIN);

        nameField = new JTextField(15);
        nameField.setFont(AppTheme.FONT_BODY);

        JLabel roleLabel = new JLabel("Character Role");
        roleLabel.setForeground(AppTheme.TEXT_MAIN);

        roleComboBox = new JComboBox<>(
                CharacterType.values()
        );

        roleComboBox.setFont(AppTheme.FONT_BODY);

        JLabel levelLabel = new JLabel("Character Level");
        levelLabel.setForeground(AppTheme.TEXT_MAIN);

        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 99, 1)
        );

        levelSpinner.setFont(AppTheme.FONT_BODY);

        inputPanel.add(nameLabel);
        inputPanel.add(roleLabel);
        inputPanel.add(levelLabel);

        inputPanel.add(nameField);
        inputPanel.add(roleComboBox);
        inputPanel.add(levelSpinner);

        add(inputPanel, BorderLayout.CENTER);

        originalNameBorder = nameField.getBorder();
        originalRoleBorder = roleComboBox.getBorder();
        originalLevelBorder = levelSpinner.getBorder();

        JPanel bottomPanel = new JPanel(
                new BorderLayout(8, 4)
        );

        bottomPanel.setBackground(AppTheme.BG_MAIN);

        errorLabel = new JLabel(" ");
        errorLabel.setFont(AppTheme.FONT_BODY);
        errorLabel.setForeground(Color.RED);

        addButton = new JButton("Add Character");

        bottomPanel.add(errorLabel, BorderLayout.CENTER);
        bottomPanel.add(addButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addCharacter());
    }

    // Validasi nama karakter
    private String validateName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return "Nama karakter tidak boleh kosong.";
        }

        String cleanName = name.trim();

        if (cleanName.length() > 20) {
            return "Nama karakter maksimal 20 karakter.";
        }

        if (!cleanName.matches("[\\p{L}]+(?: [\\p{L}]+)*")) {
            return "Nama hanya boleh berisi huruf dan satu spasi antar kata.";
        }

        if (manager.isNameTaken(cleanName)) {
            return "Nama karakter sudah digunakan.";
        }

        return null;
    }

    // Proses menambahkan karakter
    private void addCharacter() {

        clearError();

        String name = nameField.getText();

        String message = validateName(name);

        if (message != null) {

            showError(
                    message,
                    nameField,
                    originalNameBorder
            );

            return;
        }

        // Memastikan nilai spinner valid
        try {

            levelSpinner.commitEdit();

        } catch (ParseException ex) {

            showError(
                    "Level harus berupa angka yang valid.",
                    levelSpinner,
                    originalLevelBorder
            );

            return;
        }

        int level = (Integer) levelSpinner.getValue();

        CharacterType type =
                (CharacterType) roleComboBox.getSelectedItem();

        // Validasi role
        if (type == null) {

            showError(
                    "Role karakter harus dipilih.",
                    roleComboBox,
                    originalRoleBorder
            );

            return;
        }

        try {

            Character character = new Character(
                    name,
                    type,
                    level
            );

            boolean success = manager.addCharacter(character);

            if (!success) {

                showError(
                        "Karakter gagal ditambahkan. Nama mungkin sudah digunakan.",
                        nameField,
                        originalNameBorder
                );

                return;
            }

            // Reset form setelah berhasil
            nameField.setText("");
            levelSpinner.setValue(1);
            roleComboBox.setSelectedIndex(0);

            clearError();

            if (refreshCallback != null) {
                refreshCallback.run();
            }

            nameField.requestFocusInWindow();

        } catch (IllegalArgumentException ex) {

            String errorMessage = ex.getMessage();

            if (errorMessage == null) {
                errorMessage = "Terjadi kesalahan saat menambahkan karakter.";
            }

            String lowerMessage = errorMessage.toLowerCase();

            if (lowerMessage.contains("level")) {

                showError(
                        errorMessage,
                        levelSpinner,
                        originalLevelBorder
                );

            } else if (lowerMessage.contains("role")) {

                showError(
                        errorMessage,
                        roleComboBox,
                        originalRoleBorder
                );

            } else {

                showError(
                        errorMessage,
                        nameField,
                        originalNameBorder
                );
            }
        }
    }

    // Menampilkan pesan kesalahan
    private void showError(
            String message,
            JComponent component,
            Border originalBorder) {

        errorLabel.setText(message);

        component.setBorder(
                BorderFactory.createLineBorder(Color.RED, 2)
        );

        component.requestFocusInWindow();
    }

    // Menghapus pesan kesalahan
    private void clearError() {

        errorLabel.setText(" ");

        nameField.setBorder(originalNameBorder);
        roleComboBox.setBorder(originalRoleBorder);
        levelSpinner.setBorder(originalLevelBorder);
    }
}
