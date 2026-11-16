
package com.unpri.charactermanager.model;

import java.util.Objects;

public class Character {

    private static int totalCharacterCreated = 0;

    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana;

    public Character(String name, CharacterType type, int level) {
        validateCharacter(name, type, level);

        this.name = name.trim();
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
        this.mana = 0;

        totalCharacterCreated++;
    }

    public Character(int id, String name, CharacterType type,
                     int level, int hitPoints, int attackPower) {

        validateCharacter(name, type, level);

        this.id = id;
        this.name = name.trim();
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        this.mana = 0;

        totalCharacterCreated++;
    }

    private static void validateCharacter(
            String name, CharacterType type, int level) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Nama karakter tidak boleh kosong."
            );
        }

        if (name.trim().length() > 20) {
            throw new IllegalArgumentException(
                    "Nama karakter maksimal 20 karakter."
            );
        }

        if (type == null) {
            throw new IllegalArgumentException(
                    "Role karakter harus dipilih."
            );
        }

        if (level < 1 || level > 99) {
            throw new IllegalArgumentException(
                    "Level harus berada di antara 1 sampai 99."
            );
        }

        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException(
                    "Karakter MAGE tidak boleh memiliki level lebih dari 60."
            );
        }
    }

    private int calculateBaseHP() {
        return 100 + (level * 10);
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CharacterType getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getMana() {
        return mana;
    }

    public static int getTotalCharacterCreated() {
        return totalCharacterCreated;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        validateCharacter(this.name, this.type, level);

        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException(
                    "Mana tidak boleh negatif."
            );
        }

        this.mana = mana;
    }

    public boolean isAlive() {
        return hitPoints > 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Character other = (Character) obj;
        return Objects.equals(this.name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", hp=" + hitPoints +
                ", atk=" + attackPower +
                ", mana=" + mana +
                '}';
    }
}
