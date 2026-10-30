
import javax.swing.SwingUtilities;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.model.CharacterManager;
import com.unpri.charactermanager.view.MainWindow;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== Character Manager TP8 ===");

        CharacterManager manager = new CharacterManager();

        // Menguji list kosong
        System.out.println("\n--- Empty List Test ---");
        System.out.println("Average level saat kosong: "
                + manager.getAverageLevel());

        // Membuat karakter lama
        Character hero = new Character(
                "Arjuna", CharacterType.WARRIOR, 5);

        Character wizard = new Character(
                "Merlin", CharacterType.MAGE, 3);

        Character scout = new Character(
                "Athena", CharacterType.ARCHER, 4);

        Character healer = new Character(
                "Luna", CharacterType.HEALER, 2);

        // Karakter baru TP8
        Character knight = new Character(
                "Orion", CharacterType.WARRIOR, 6);

        Character priest = new Character(
                "Freya", CharacterType.HEALER, 3);

        // Bagian A: menambahkan karakter
        System.out.println("\n--- Add Character ---");
        System.out.println("Tambah Arjuna: "
                + manager.addCharacter(hero));
        System.out.println("Tambah Merlin: "
                + manager.addCharacter(wizard));
        System.out.println("Tambah Athena: "
                + manager.addCharacter(scout));
        System.out.println("Tambah Luna: "
                + manager.addCharacter(healer));
        System.out.println("Tambah Orion: "
                + manager.addCharacter(knight));
        System.out.println("Tambah Freya: "
                + manager.addCharacter(priest));

        // Menguji nama duplikat
        Character duplicate = new Character(
                "Arjuna", CharacterType.MAGE, 2);

        System.out.println("Tambah Arjuna duplikat: "
                + manager.addCharacter(duplicate));

        // Menampilkan seluruh karakter
        System.out.println("\n--- All Characters ---");

        for (Character character : manager.getAllCharacters()) {
            System.out.println(character);
        }

        // Mencari karakter
        System.out.println("\n--- Find Character ---");
        System.out.println("Cari Merlin: "
                + manager.findByName("Merlin"));
        System.out.println("Cari Budi: "
                + manager.findByName("Budi"));

        // Menghitung total karakter
        System.out.println("\n--- Total Characters ---");
        System.out.println("Total: "
                + manager.getTotalCharacters());

        // Bagian B: rata-rata level
        System.out.println("\n--- Average Level ---");
        System.out.println("Average level: "
                + manager.getAverageLevel());

        // Bagian B: mencari berdasarkan tipe
        System.out.println("\n--- Find By Type ---");
        System.out.println("WARRIOR: "
                + manager.findByType(CharacterType.WARRIOR));
        System.out.println("MAGE: "
                + manager.findByType(CharacterType.MAGE));

        // Bagian C: mencari karakter terkuat
        System.out.println("\n--- Strongest Character ---");
        System.out.println("Terkuat: "
                + manager.findStrongest());

        // Menguji salinan ArrayList
        System.out.println("\n--- Copy Experiment ---");

        java.util.ArrayList<Character> copy =
                manager.getAllCharacters();

        System.out.println("Jumlah copy sebelum clear: "
                + copy.size());

        copy.clear();

        System.out.println("Jumlah copy setelah clear: "
                + copy.size());

        System.out.println("Jumlah manager setelah clear copy: "
                + manager.getTotalCharacters());

        // Menguji reference
        System.out.println("\n--- Reference Experiment ---");

        Character a = new Character(
                "ReferenceHero", CharacterType.WARRIOR, 1);

        Character b = a;

        b.setLevel(8);

        System.out.println("a.getLevel(): " + a.getLevel());
        System.out.println("a == b: " + (a == b));

        // Menguji equals()
        System.out.println("\n--- Equals Experiment ---");

        Character x = new Character(
                "Dewi", CharacterType.HEALER, 2);

        Character y = new Character(
                "Dewi", CharacterType.MAGE, 5);

        System.out.println("x == y: " + (x == y));
        System.out.println("x.equals(y): " + x.equals(y));

        // Menghapus karakter
        System.out.println("\n--- Remove Character ---");

        System.out.println("Hapus Merlin: "
                + manager.removeCharacter("Merlin"));

        System.out.println("Hapus Budi: "
                + manager.removeCharacter("Budi"));

        System.out.println("Total setelah hapus: "
                + manager.getTotalCharacters());

        // Menguji penghitung objek static
        System.out.println("\n--- Static Counter ---");
        System.out.println("Total objek Character dibuat: "
                + Character.getTotalCharacterCreated());

        System.out.println("\n=== Program selesai ===");

        // Menjalankan GUI menggunakan Swing Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {

            MainWindow window = new MainWindow(
                    manager.getAllCharacters(),
                    manager.getAverageLevel()
            );

            window.setVisible(true);
        });
    }
}
