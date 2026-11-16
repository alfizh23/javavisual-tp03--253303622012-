
package com.unpri.charactermanager.model;

import java.util.ArrayList;

public class CharacterManager {

    private static final int MAX_CHARACTERS = 20;

    private ArrayList<Character> characters = new ArrayList<>();

    // Menambahkan karakter
    public boolean addCharacter(Character character) {

        if (character == null) {
            return false;
        }

        String name = character.getName();

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        // Memeriksa nama duplikat
        if (isNameTaken(name)) {
            return false;
        }

        // Memeriksa batas maksimal karakter
        if (characters.size() >= MAX_CHARACTERS) {
            throw new IllegalArgumentException(
                    "Jumlah karakter sudah mencapai batas maksimal 20."
            );
        }

        characters.add(character);

        return true;
    }

    // Mencari karakter berdasarkan nama
    public Character findByName(String name) {

        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        String cleanName = name.trim();

        for (Character character : characters) {

            if (character.getName().equalsIgnoreCase(cleanName)) {
                return character;
            }
        }

        return null;
    }

    // Memeriksa apakah nama sudah digunakan
    public boolean isNameTaken(String name) {

        return findByName(name) != null;
    }

    // Menghapus karakter
    public boolean removeCharacter(String name) {

        Character character = findByName(name);

        if (character == null) {
            return false;
        }

        characters.remove(character);

        return true;
    }

    // Menghitung jumlah karakter
    public int getTotalCharacters() {
        return characters.size();
    }

    // Mengembalikan salinan daftar karakter
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

    // Mencari karakter berdasarkan role
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

    // Mencari karakter dengan attack tertinggi
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
