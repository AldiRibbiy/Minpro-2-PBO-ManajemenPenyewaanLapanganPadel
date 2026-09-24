# Minpro-2-PBO-SistemPenyewaanLapanganPadel

## Deskripsi Singkat Program
Program ini adalah pengembangan lanjutan dari Mini Project 1, yaitu **Sistem Manajemen Penyewaan Lapangan Padel** berbasis Java (console/CLI). 
Tujuan program ini adalah membantu pengelola tempat penyewaan lapangan padel dalam mencatat data lapangan, data peralatan (raket dan bola), serta transaksi penyewaannya secara terkomputerisasi. 
Pada versi ini, program dikembangkan dengan menerapkan konsep **inheritance**, **validasi input** yang lebih ketat, serta **polymorphism** (overriding dan overloading).

Program terdiri dari 4 class:
- **ItemSewa** — *superclass* (induk), berisi atribut umum yang dimiliki semua barang yang bisa disewa: id, nama, dan harga.
- **Lapangan** — *subclass* dari ItemSewa, menambahkan atribut `status` (Tersedia/Disewa).
- **Peralatan** — *subclass* dari ItemSewa, menambahkan atribut `stok`.
- **Penyewaan** — class transaksi yang mencatat data sewa (baik sewa lapangan maupun sewa peralatan): id, nama penyewa, jenis, nama item, dan total harga.
- **Main** — entry point program, berisi `ArrayList` untuk menyimpan seluruh data serta seluruh menu dan proses CRUD.

## Penjelasan Alur Program

1. Saat program dijalankan, sistem otomatis mengisi **dummy data awal** (2 data lapangan dan 2 data peralatan) ke dalam `ArrayList`, sehingga saat memilih menu "Lihat", data langsung tampil tanpa harus input dari awal.
2. Program menampilkan **menu utama** dengan 3 pilihan besar:
   - 1. Kelola Lapangan
   - 2. Kelola Peralatan (Raket & Bola)
   - 3. Kelola Penyewaan
   - 0. Keluar
3. Setelah memilih salah satu menu utama, program masuk ke **submenu** berisi operasi CRUD: Tambah, Lihat, Ubah, Hapus, dan Kembali.
4. Pemilihan menu dilakukan dengan mengetik angka, diproses menggunakan percabangan `switch-case` (menu utama) dan `if-else` (submenu).
5. Setiap input dari pengguna (nama, harga, jumlah, stok, durasi) divalidasi terlebih dahulu sebelum diproses lebih lanjut (penjelasan lengkap ada di bagian Validasi Input di bawah).
6. Pada menu **Tambah Penyewaan**, pengguna memilih jenis sewa (Lapangan/Peralatan), memilih item dari daftar, lalu memasukkan durasi (lapangan) atau jumlah (peralatan). Total harga dihitung otomatis, status lapangan berubah menjadi "Disewa", dan stok peralatan berkurang sesuai jumlah yang disewa. Khusus sewa lapangan dengan durasi 3 jam atau lebih, pengguna otomatis mendapat diskon 10%.
7. Setiap kali memilih menu "Lihat", program menampilkan seluruh data menggunakan perulangan `for-each`.
8. Program terus berulang menampilkan submenu (`do-while`) sampai pengguna memilih 0 untuk kembali, dan terus berulang di menu utama sampai pengguna memilih 0 untuk keluar.

## Penjelasan Penerapan Encapsulation dan Inheritance

**Encapsulation:**
Seluruh atribut pada class `ItemSewa`, `Lapangan`, `Peralatan`, dan `Penyewaan` dideklarasikan dengan access modifier `private`,
sehingga tidak bisa diakses langsung dari luar class. Untuk mengakses atau mengubah nilainya, disediakan method `getter` dan `setter` yang bersifat `public`. 
Contoh: atribut `harga` pada `ItemSewa` bersifat `private`, tapi disediakan `getHarga()` untuk membaca nilainya dan `setHarga()` untuk mengubahnya.

**Inheritance:**
Class `ItemSewa` dibuat sebagai **superclass** (induk) yang bersifat `abstract`, karena `ItemSewa` merupakan konsep umum yang tidak pernah dibuat objeknya
secara langsung — yang benar-benar disewakan adalah *Lapangan* atau *Peralatan*. Class `Lapangan` dan `Peralatan` merupakan **subclass** yang mewarisi (`extends`) `ItemSewa`,
sehingga otomatis mendapatkan atribut dan method `id`, `nama`, `harga` beserta getter-setter-nya, tanpa perlu menuliskan ulang. Kedua subclass ini kemudian menambahkan atribut
khususnya masing-masing (`status` untuk Lapangan, `stok` untuk Peralatan) serta memanggil constructor superclass menggunakan `super(id, nama, harga)`.

## Penjelasan Letak Penerapan Nilai Tambah (Polymorphism)

Nilai tambah yang diterapkan pada program ini adalah **Polymorphism**, dalam dua bentuk:

1. **Method Overriding** — Method `tampilkanInfo()` dideklarasikan sebagai method abstrak di superclass `ItemSewa`, lalu di-*override* secara berbeda oleh `Lapangan` (menampilkan status) dan `Peralatan` (menampilkan stok). Penerapannya terlihat pada method `tampilkanItem(ItemSewa item)` di `Main.java`: method ini menerima parameter bertipe superclass `ItemSewa`, tapi hasil tampilannya otomatis menyesuaikan objek asli yang dikirim (Lapangan atau Peralatan) — inilah bentuk polymorphism.
2. **Method Overloading** — Terdapat dua method dengan nama sama `hitungTotal` di `Main.java`, tapi jumlah parameternya berbeda: `hitungTotal(double harga, int jumlah)` untuk perhitungan biasa, dan `hitungTotal(double harga, int jumlah, double diskonPersen)` untuk perhitungan dengan diskon. Method versi diskon ini dipanggil secara otomatis pada fitur "Tambah Penyewaan Lapangan" apabila durasi sewa 3 jam atau lebih (diskon 10%).

## Validasi Input

Program menerapkan validasi input pada beberapa method di `Main.java`:
- `bacaTeks()` — memastikan input teks tidak boleh kosong.
- `bacaHarga()` — memastikan input harga berupa angka dan lebih besar dari 0.
- `bacaJumlah()` — memastikan input jumlah/stok/durasi berupa bilangan bulat dan lebih besar dari 0.
- `bacaAngka()` — menangkap kesalahan input non-angka pada pemilihan menu agar program tidak crash.

Setiap validasi menggunakan `try-catch` untuk menangani `NumberFormatException`, dan perulangan `while`/`do-while` agar pengguna terus diminta memasukkan ulang data sampai valid.
