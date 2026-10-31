
package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import com.unpri.charactermanager.model.CharacterType;

public final class AppTheme {

    private AppTheme() {
        // Utility class untuk tema aplikasi
    }

    // Warna utama aplikasi
    public static final Color BACKGROUND_COLOR =
            new Color(245, 247, 250);

    public static final Color CARD_BACKGROUND =
            new Color(255, 255, 255);

    public static final Color TEXT_COLOR =
            new Color(45, 52, 64);

    public static final Color SECONDARY_TEXT =
            new Color(110, 118, 129);

    public static final Color GOLD_COLOR =
            new Color(184, 134, 11);

    public static final Color BUTTON_COLOR =
            new Color(45, 85, 145);

    public static final Color BORDER_COLOR =
            new Color(220, 225, 232);

    // Warna berdasarkan role karakter
    public static final Color WARRIOR_COLOR =
            new Color(255, 230, 225);

    public static final Color MAGE_COLOR =
            new Color(232, 225, 255);

    public static final Color ARCHER_COLOR =
            new Color(222, 245, 226);

    public static final Color HEALER_COLOR =
            new Color(220, 240, 255);

    // Font aplikasi menggunakan logical font
    public static final Font TITLE_FONT =
            new Font(Font.SANS_SERIF, Font.BOLD, 24);

    public static final Font BODY_FONT =
            new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    public static final Font BOLD_FONT =
            new Font(Font.SANS_SERIF, Font.BOLD, 14);

    public static final Font STAT_FONT =
            new Font(Font.MONOSPACED, Font.PLAIN, 13);

    // Border aplikasi
    public static final Border CARD_BORDER =
            BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(
                            BORDER_COLOR, 1),
                    BorderFactory.createEmptyBorder(
                            10, 12, 10, 12)
            );

    public static final Border PANEL_BORDER =
            BorderFactory.createEmptyBorder(
                    15, 15, 15, 15);

    public static final Border SCROLL_BORDER =
            BorderFactory.createLineBorder(
                    BORDER_COLOR, 1);

    // Mengembalikan warna sesuai role
    public static Color colorFor(CharacterType type) {

        if (type == null) {
            return CARD_BACKGROUND;
        }

        switch (type) {
            case WARRIOR:
                return WARRIOR_COLOR;

            case MAGE:
                return MAGE_COLOR;

            case ARCHER:
                return ARCHER_COLOR;

            case HEALER:
                return HEALER_COLOR;

            default:
                return CARD_BACKGROUND;
        }
    }
}
