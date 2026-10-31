Iya, saya paham. Kita rapikan semuanya sekaligus dari awal. README lama tetap memuat TP3, TP5, dan TP8, kemudian ditambahkan TP10. Kamu tinggal ganti seluruh isi `README.md` dengan versi di bawah ini. Path screenshot sudah saya masukkan sesuai struktur folder project kamu.

# Character Manager — Java Visual SIH-6406

Aplikasi Character Manager merupakan aplikasi Java sederhana untuk mengelola karakter game menggunakan konsep Object-Oriented Programming (OOP), Java Swing, dan arsitektur Model-View.

**Nama:** Dhaifullah Al Hafizh Zuldiansyah\
**NIM:** 253303622012\
**Kelas:** SI Pagi A

---

# TP3 — Implementasi OOP

## Deskripsi

Character Manager adalah aplikasi Java sederhana yang dirancang untuk mengelola karakter dalam game menggunakan konsep Pemrograman Berorientasi Objek (Object-Oriented Programming/OOP). Aplikasi ini menerapkan penggunaan class, constructor, enkapsulasi, getter dan setter, serta enum untuk menentukan jenis karakter.

Aplikasi memiliki atribut karakter seperti level, poin kesehatan (HP), kekuatan serangan (attack power), dan mana. Selain itu, terdapat method `isAlive()` untuk memeriksa apakah karakter masih hidup.

## Screenshot Output

Screenshot berikut menampilkan hasil keluaran program pada terminal, termasuk hasil pemanggilan method `isAlive()`.



## Identitas Mahasiswa

- **Nama:** Dhaifullah Al Hafizh Zuldiansyah
- **NIM:** 253303622012
- **Kelas:** SI Pagi A

---

# TP5 — CharacterManager

## A. Implementasi Core

Fitur yang telah diimplementasikan dan diuji:

- `equals()` membandingkan karakter berdasarkan nama.
- `addCharacter()` menambahkan karakter dan menolak nama duplikat.
- `findByName()` mencari karakter berdasarkan nama.
- `removeCharacter()` menghapus karakter berdasarkan nama.
- `getTotalCharacters()` menghitung jumlah karakter yang tersimpan.
- `getAllCharacters()` mengembalikan salinan daftar karakter.

Pengujian menunjukkan empat karakter berhasil ditambahkan, sedangkan penambahan karakter dengan nama Arjuna yang sudah ada ditolak.

## B. Mini-Challenge

- `getAverageLevel()` menghitung rata-rata level karakter dan mengembalikan `0` ketika daftar kosong.
- `findByType(CharacterType type)` mengembalikan daftar karakter berdasarkan tipe yang dipilih.

Hasil pengujian menunjukkan rata-rata level empat karakter adalah `3.5`. Pencarian berdasarkan tipe WARRIOR menghasilkan Arjuna, sedangkan tipe MAGE menghasilkan Merlin.

## C. Fitur Tambahan: findStrongest()

Saya menambahkan method `findStrongest()` untuk mencari karakter dengan attack power tertinggi. Method ini melakukan pemeriksaan terhadap seluruh karakter yang tersimpan, kemudian mengembalikan karakter dengan nilai attack power paling tinggi. Fitur ini membantu mengetahui karakter dengan kekuatan serangan terbesar tanpa harus membandingkan setiap karakter secara manual.

---

# TP8 — Implementasi GUI Character Manager

## Screenshot Aplikasi

**Tampilan awal (5 karakter):**



**Tampilan setelah menambahkan karakter Nova:**



## Penjelasan Alur Data dan MVC

Pada program ini, `Main` membuat objek `CharacterManager` dan menambahkan beberapa karakter ke dalamnya. Selanjutnya, data karakter diambil menggunakan `getAllCharacters()` dan nilai rata-rata level diperoleh melalui `getAverageLevel()`. Kedua data tersebut diteruskan ke `MainWindow` untuk ditampilkan menggunakan komponen GUI Swing, termasuk daftar karakter dan statistik rata-rata level.

Ketika tombol tambah karakter ditekan, karakter Nova ditambahkan ke daftar karakter dan statistik diperbarui secara otomatis.

Dalam konsep MVC, class `Character` dan `CharacterManager` berperan sebagai Model karena mengelola data karakter. Class `MainWindow` berperan sebagai View karena menampilkan data kepada pengguna. Sementara itu, `Main` mengatur proses awal pembuatan data dan menampilkan jendela aplikasi.

## Karakter Tambahan

1. **Orion** — tipe WARRIOR, level 6. Ditambahkan sebagai karakter petarung dengan level tinggi.
2. **Freya** — tipe HEALER, level 3. Ditambahkan sebagai karakter pendukung.
3. **Nova** — tipe MAGE, level 5. Ditambahkan melalui tombol pada GUI untuk menunjukkan perubahan daftar karakter dan statistik secara dinamis.

## Analisis TP8

Google Docs — Analisis TP8

---

# TP10 — AppTheme & MainWindow v0.4

## A. Implementasi Tema

Pada TP10, aplikasi Character Manager dikembangkan dengan menggunakan class `AppTheme` untuk mengatur warna, font, dan border agar tampilan lebih konsisten.

Class `AppTheme` berada di package `view` dan memiliki beberapa konstanta warna, font, serta border yang digunakan oleh `MainWindow`. Method `colorFor(CharacterType type)` digunakan untuk menentukan warna kartu berdasarkan role karakter.

Implementasi ini membuat pengaturan tampilan lebih terpusat dan tidak perlu ditulis berulang kali pada setiap komponen.

## B. Screenshot Aplikasi

**Before — MainWindow v0.3**



**After — MainWindow v0.4**



## C. Mini-Challenge

Karakter dengan level minimal 5 ditampilkan menggunakan tulisan berwarna emas dan tebal. Batas level disimpan dalam konstanta bernama `GOLD_LEVEL_THRESHOLD` sehingga nilainya dapat diatur tanpa harus mengubah banyak bagian kode.

Aplikasi juga menampilkan minimal enam karakter dengan variasi role dan level yang berbeda.

## D. Implementasi AppTheme

Warna tampilan aplikasi disimpan di dalam class `AppTheme` karena pengaturan warna merupakan bagian dari tampilan atau View. Dengan cara ini, warna tidak perlu disimpan di class `Character` yang bertugas mengelola data karakter. Pemisahan tersebut membuat kode lebih terstruktur dan memudahkan perubahan tema tanpa mengubah Model. Selain itu, penggunaan `AppTheme` memungkinkan warna yang sama digunakan kembali oleh beberapa komponen aplikasi.

## E. Properti Tambahan

**1. Tooltip Informasi Karakter**

Setiap kartu karakter memiliki tooltip yang menampilkan informasi seperti nama, role, level, HP, ATK, dan mana. Fitur ini membantu pengguna melihat detail karakter tanpa membuat tampilan utama terlalu penuh.

**2. Karakter Level 1 Dinonaktifkan**

Karakter yang masih berada di level 1 dibuat nonaktif menggunakan `setEnabled(false)`. Fitur ini memberikan pembeda visual dan menunjukkan bahwa karakter tersebut memiliki kondisi khusus dibandingkan karakter lainnya.

## F. Analisis Bagian D — Google Docs

[JavaVisual TP10 — Analisis Properti & MVC](https://docs.google.com/document/d/1YduyrsfJwi4Mmx1Gu_p91PJkxbL1m5sxPvg8nv3zS0M/edit?usp=sharing)

Dokumen tersebut berisi analisis mengenai JavaBeans getter/setter, properti background JLabel, pengaturan ukuran komponen Swing, pemisahan Model dan View, serta penggunaan logical font dan `deriveFont()`.

---

## Struktur Folder Project

```
javavisual-tp03-[253303622012]/
│
├── src/
│   ├── Main.java
│   └── com/
│       └── unpri/
│           └── charactermanager/
│               ├── model/
│               │   ├── Character.java
│               │   ├── CharacterManager.java
│               │   └── CharacterType.java
│               │
│               ├── view/
│               │   ├── AppTheme.java
│               │   └── MainWindow.java
│               │
│               └── screenshots/
│                   ├── before-v03.png
│                   └── after-v04.png
│
├── screenshots/
│   ├── output.png
│   ├── TP8-sebelum.png
│   └── TP8-sesudah.png
│
└── README.md
```
