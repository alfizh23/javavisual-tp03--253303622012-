
package com.unpri.charactermanager.model;

public class Character {

    // Atribut
    private int id;
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attackPower;
    private int mana; // atribut baru

    // Constructor 1: membuat karakter baru
    public Character(String name, CharacterType type, int level) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
        this.mana = 0; // mana awal
    }

    // Constructor 2: memuat karakter dari database
    public Character(int id, String name, CharacterType type,
                     int level, int hitPoints, int attackPower) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attackPower = attackPower;
        this.mana = 0; // mana awal
    }

    // Helper method
    private int calculateBaseHP() {
        return 100 + (level * 10);
    }

    private int calculateBaseAttack() {
        return 10 + (level * 5);
    }

    // Getter
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

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setLevel(int level) {
        if (level < 1) {
            throw new IllegalArgumentException(
                "Level minimal adalah 1"
            );
        }

        this.level = level;
        this.hitPoints = calculateBaseHP();
        this.attackPower = calculateBaseAttack();
    }

    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException(
                "Mana tidak boleh negatif"
            );
        }

        this.mana = mana;
    }

    // Mengecek apakah karakter masih hidup
    public boolean isAlive() {
        return hitPoints > 0;
    }

    // Representasi teks object
    @Override
    public String toString() {
        return "Character{id=" + id +
               ", name='" + name + '\'' +
               ", type=" + type +
               ", level=" + level +
               ", hp=" + hitPoints +
               ", atk=" + attackPower +
               ", mana=" + mana + '}';
    }
}
