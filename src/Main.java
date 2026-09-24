import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Lapangan> daftarLapangan = new ArrayList<>();
    static ArrayList<Peralatan> daftarPeralatan = new ArrayList<>();
    static ArrayList<Penyewaan> daftarPenyewaan = new ArrayList<>();
    static int noLapangan = 1;
    static int noPeralatan = 1;
    static int noSewa = 1;

    public static void main(String[] args) {
        // Dummy data awal, wajib ada minimal 1 data di setiap ArrayList
        daftarLapangan.add(new Lapangan("L" + noLapangan++, "Lapangan A", 150000));
        daftarLapangan.add(new Lapangan("L" + noLapangan++, "Lapangan B", 175000));
        daftarPeralatan.add(new Peralatan("P" + noPeralatan++, "Raket Padel", 25000, 10));
        daftarPeralatan.add(new Peralatan("P" + noPeralatan++, "Bola Padel", 20000, 20));

        int pilihan;
        do {
            System.out.println("\n===== SISTEM PENYEWAAN LAPANGAN PADEL =====");
            System.out.println("1. Kelola Lapangan");
            System.out.println("2. Kelola Peralatan (Raket & Bola)");
            System.out.println("3. Kelola Penyewaan");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = bacaAngka();

            switch (pilihan) {
                case 1:
                    menuLapangan();
                    break;
                case 2:
                    menuPeralatan();
                    break;
                case 3:
                    menuPenyewaan();
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan program ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 0);
    }

    // ================== METHOD VALIDASI INPUT ==================

    // Validasi input angka pilihan menu (boleh angka berapa saja, termasuk 0)
    static int bacaAngka() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            return -1; // nilai tidak valid, akan otomatis masuk ke "default"
        }
    }

    // Validasi input teks agar tidak boleh kosong
    static String bacaTeks(String label) {
        String teks;
        do {
            System.out.print(label);
            teks = sc.nextLine().trim();
            if (teks.isEmpty()) {
                System.out.println("Input tidak boleh kosong! Coba lagi.");
            }
        } while (teks.isEmpty());
        return teks;
    }

    // Validasi input harga: harus angka dan harus lebih dari 0
    static double bacaHarga(String label) {
        while (true) {
            System.out.print(label);
            try {
                double nilai = Double.parseDouble(sc.nextLine().trim());
                if (nilai <= 0) {
                    System.out.println("Harga harus lebih besar dari 0!");
                    continue;
                }
                return nilai;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    // Validasi input jumlah/stok/durasi: harus bilangan bulat dan lebih dari 0
    static int bacaJumlah(String label) {
        while (true) {
            System.out.print(label);
            try {
                int nilai = Integer.parseInt(sc.nextLine().trim());
                if (nilai <= 0) {
                    System.out.println("Jumlah harus lebih besar dari 0!");
                    continue;
                }
                return nilai;
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat!");
            }
        }
    }

    // ================== POLYMORPHISM ==================

    // Overriding: method ini menerima tipe SUPERCLASS (ItemSewa) sebagai parameter,
    // tapi hasil yang tampil berbeda tergantung objek asli yang dikirim
    // (Lapangan atau Peralatan), karena masing-masing meng-override tampilkanInfo().
    static void tampilkanItem(ItemSewa item) {
        System.out.println(item.tampilkanInfo());
    }

    // Overloading: dua method dengan nama sama "hitungTotal" tapi jumlah
    // parameter berbeda. Versi dengan diskon dipakai untuk sewa lapangan
    // dengan durasi panjang (>= 3 jam).
    static double hitungTotal(double harga, int jumlah) {
        return harga * jumlah;
    }

    static double hitungTotal(double harga, int jumlah, double diskonPersen) {
        double total = harga * jumlah;
        total = total - (total * diskonPersen / 100);
        return total;
    }

    // ===================== MENU LAPANGAN =====================
    static void menuLapangan() {
        int pilihan;
        do {
            System.out.println("\n-- Menu Lapangan --");
            System.out.println("1. Tambah Lapangan");
            System.out.println("2. Lihat Lapangan");
            System.out.println("3. Ubah Lapangan");
            System.out.println("4. Hapus Lapangan");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            pilihan = bacaAngka();

            if (pilihan == 1) {
                String nama = bacaTeks("Nama Lapangan: ");
                double harga = bacaHarga("Harga per Jam: ");
                daftarLapangan.add(new Lapangan("L" + noLapangan++, nama, harga));
                System.out.println("Lapangan berhasil ditambahkan!");

            } else if (pilihan == 2) {
                if (daftarLapangan.isEmpty()) {
                    System.out.println("Belum ada data lapangan.");
                } else {
                    for (Lapangan l : daftarLapangan) {
                        tampilkanItem(l); // polymorphism: memanggil tampilkanInfo() versi Lapangan
                    }
                }

            } else if (pilihan == 3) {
                String id = bacaTeks("Masukkan ID Lapangan: ");
                boolean ditemukan = false;
                for (Lapangan l : daftarLapangan) {
                    if (l.getId().equalsIgnoreCase(id)) {
                        l.setNama(bacaTeks("Nama Baru: "));
                        l.setHarga(bacaHarga("Harga Baru: "));
                        ditemukan = true;
                        System.out.println("Data berhasil diubah!");
                    }
                }
                if (!ditemukan) {
                    System.out.println("Lapangan tidak ditemukan.");
                }

            } else if (pilihan == 4) {
                String id = bacaTeks("Masukkan ID Lapangan: ");
                boolean berhasil = daftarLapangan.removeIf(l -> l.getId().equalsIgnoreCase(id));
                System.out.println(berhasil ? "Data berhasil dihapus!" : "Lapangan tidak ditemukan.");
            }
        } while (pilihan != 0);
    }

    // ===================== MENU PERALATAN =====================
    static void menuPeralatan() {
        int pilihan;
        do {
            System.out.println("\n-- Menu Peralatan (Raket & Bola) --");
            System.out.println("1. Tambah Peralatan");
            System.out.println("2. Lihat Peralatan");
            System.out.println("3. Ubah Peralatan");
            System.out.println("4. Hapus Peralatan");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            pilihan = bacaAngka();

            if (pilihan == 1) {
                String nama = bacaTeks("Nama Peralatan (Raket/Bola): ");
                double harga = bacaHarga("Harga Sewa: ");
                int stok = bacaJumlah("Jumlah Stok: ");
                daftarPeralatan.add(new Peralatan("P" + noPeralatan++, nama, harga, stok));
                System.out.println("Peralatan berhasil ditambahkan!");

            } else if (pilihan == 2) {
                if (daftarPeralatan.isEmpty()) {
                    System.out.println("Belum ada data peralatan.");
                } else {
                    for (Peralatan p : daftarPeralatan) {
                        tampilkanItem(p); // polymorphism: memanggil tampilkanInfo() versi Peralatan
                    }
                }

            } else if (pilihan == 3) {
                String id = bacaTeks("Masukkan ID Peralatan: ");
                boolean ditemukan = false;
                for (Peralatan p : daftarPeralatan) {
                    if (p.getId().equalsIgnoreCase(id)) {
                        p.setNama(bacaTeks("Nama Baru: "));
                        p.setHarga(bacaHarga("Harga Baru: "));
                        p.setStok(bacaJumlah("Stok Baru: "));
                        ditemukan = true;
                        System.out.println("Data berhasil diubah!");
                    }
                }
                if (!ditemukan) {
                    System.out.println("Peralatan tidak ditemukan.");
                }

            } else if (pilihan == 4) {
                String id = bacaTeks("Masukkan ID Peralatan: ");
                boolean berhasil = daftarPeralatan.removeIf(p -> p.getId().equalsIgnoreCase(id));
                System.out.println(berhasil ? "Data berhasil dihapus!" : "Peralatan tidak ditemukan.");
            }
        } while (pilihan != 0);
    }

    // ===================== MENU PENYEWAAN =====================
    static void menuPenyewaan() {
        int pilihan;
        do {
            System.out.println("\n-- Menu Penyewaan --");
            System.out.println("1. Tambah Penyewaan (Lapangan/Peralatan)");
            System.out.println("2. Lihat Penyewaan");
            System.out.println("3. Ubah Penyewaan");
            System.out.println("4. Hapus Penyewaan");
            System.out.println("0. Kembali");
            System.out.print("Pilih: ");
            pilihan = bacaAngka();

            if (pilihan == 1) {
                tambahPenyewaan();

            } else if (pilihan == 2) {
                if (daftarPenyewaan.isEmpty()) {
                    System.out.println("Belum ada data penyewaan.");
                } else {
                    for (Penyewaan s : daftarPenyewaan) {
                        System.out.println(s);
                    }
                }

            } else if (pilihan == 3) {
                String id = bacaTeks("Masukkan ID Penyewaan: ");
                boolean ditemukan = false;
                for (Penyewaan s : daftarPenyewaan) {
                    if (s.getId().equalsIgnoreCase(id)) {
                        s.setNamaPenyewa(bacaTeks("Nama Penyewa Baru: "));
                        s.setTotalHarga(bacaHarga("Total Harga Baru: "));
                        ditemukan = true;
                        System.out.println("Data berhasil diubah!");
                    }
                }
                if (!ditemukan) {
                    System.out.println("Penyewaan tidak ditemukan.");
                }

            } else if (pilihan == 4) {
                String id = bacaTeks("Masukkan ID Penyewaan: ");
                boolean berhasil = daftarPenyewaan.removeIf(s -> s.getId().equalsIgnoreCase(id));
                System.out.println(berhasil ? "Data berhasil dihapus!" : "Penyewaan tidak ditemukan.");
            }
        } while (pilihan != 0);
    }

    // Menambah data penyewaan, bisa untuk lapangan ataupun peralatan
    static void tambahPenyewaan() {
        System.out.println("Sewa apa? 1. Lapangan   2. Peralatan");
        int jenis = bacaAngka();
        String nama = bacaTeks("Nama Penyewa: ");

        if (jenis == 1) {
            if (daftarLapangan.isEmpty()) {
                System.out.println("Belum ada data lapangan.");
                return;
            }
            for (Lapangan l : daftarLapangan) {
                tampilkanItem(l);
            }
            String id = bacaTeks("Masukkan ID Lapangan: ");
            Lapangan lp = null;
            for (Lapangan l : daftarLapangan) {
                if (l.getId().equalsIgnoreCase(id)) {
                    lp = l;
                }
            }
            if (lp == null) {
                System.out.println("Lapangan tidak ditemukan.");
                return;
            }
            int durasi = bacaJumlah("Durasi (jam): ");

            double total;
            if (durasi >= 3) {
                // overloading: pakai versi hitungTotal dengan diskon 10% untuk sewa >= 3 jam
                total = hitungTotal(lp.getHarga(), durasi, 10);
                System.out.println("Sewa >= 3 jam, dapat diskon 10%!");
            } else {
                total = hitungTotal(lp.getHarga(), durasi);
            }

            daftarPenyewaan.add(new Penyewaan("SW" + noSewa++, nama, "Lapangan", lp.getNama(), total));
            lp.setStatus("Disewa");
            System.out.println("Penyewaan lapangan berhasil! Total: Rp" + total);

        } else if (jenis == 2) {
            if (daftarPeralatan.isEmpty()) {
                System.out.println("Belum ada data peralatan.");
                return;
            }
            for (Peralatan p : daftarPeralatan) {
                tampilkanItem(p);
            }
            String id = bacaTeks("Masukkan ID Peralatan: ");
            Peralatan pl = null;
            for (Peralatan p : daftarPeralatan) {
                if (p.getId().equalsIgnoreCase(id)) {
                    pl = p;
                }
            }
            if (pl == null) {
                System.out.println("Peralatan tidak ditemukan.");
                return;
            }
            int jumlah = bacaJumlah("Jumlah: ");
            if (jumlah > pl.getStok()) {
                System.out.println("Stok tidak mencukupi. Stok tersedia: " + pl.getStok());
                return;
            }
            double total = hitungTotal(pl.getHarga(), jumlah);
            daftarPenyewaan.add(new Penyewaan("SW" + noSewa++, nama, "Peralatan", pl.getNama(), total));
            pl.setStok(pl.getStok() - jumlah);
            System.out.println("Penyewaan peralatan berhasil! Total: Rp" + total);

        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }
}