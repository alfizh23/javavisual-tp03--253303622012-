
package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    // Menyimpan daftar karakter
    private ArrayList<Character> characters = new ArrayList<>();

    // Menambahkan karakter dan menolak nama duplikat
    public boolean addCharacter(Character character) {
        if (character == null) {
            return false;
        }

        if (findByName(character.getName()) != null) {
            return false;
        }

        characters.add(character);
        return true;
    }

    // Mencari karakter berdasarkan nama
    public Character findByName(String name) {
        if (name == null) {
            return null;
        }

        for (Character character : characters) {
            if (character.getName().equals(name)) {
                return character;
            }
        }

        return null;
    }

    // Menghapus karakter berdasarkan nama
    public boolean removeCharacter(String name) {
        Character character = findByName(name);

        if (character == null) {
            return false;
        }

        characters.remove(character);
        return true;
    }

    // Menghitung jumlah karakter yang tersimpan
    public int getTotalCharacters() {
        return characters.size();
    }

    // Mengembalikan salinan ArrayList
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    // Menghitung rata-rata level
    public double getAverageLevel() {
        if (characters.isEmpty()) {
            return 0;
        }

        int totalLevel = 0;

        for (Character character : characters) {
            totalLevel += character.getLevel();
        }

        return (double) totalLevel / characters.size();
    }

    // Mencari karakter berdasarkan tipe
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();

        if (type == null) {
            return result;
        }

        for (Character character : characters) {
            if (character.getType() == type) {
                result.add(character);
            }
        }

        return result;
    }

    /**
     * Mencari karakter dengan attack power tertinggi.
     * Mengembalikan null jika daftar kosong.
     */
    public Character findStrongest() {
        if (characters.isEmpty()) {
            return null;
        }

        Character strongest = characters.get(0);

        for (Character character : characters) {
            if (character.getAttackPower()
                    > strongest.getAttackPower()) {
                strongest = character;
            }
        }

        return strongest;
    }
}
