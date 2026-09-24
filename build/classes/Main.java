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
        // data awal
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

    // Membaca input angka dengan validasi sederhana
    static int bacaAngka() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
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
                System.out.print("Nama Lapangan: ");
                String nama = sc.nextLine();
                System.out.print("Harga per Jam: ");
                double harga = Double.parseDouble(sc.nextLine());
                daftarLapangan.add(new Lapangan("L" + noLapangan++, nama, harga));
                System.out.println("Lapangan berhasil ditambahkan!");

            } else if (pilihan == 2) {
                if (daftarLapangan.isEmpty()) {
                    System.out.println("Belum ada data lapangan.");
                } else {
                    for (Lapangan l : daftarLapangan) {
                        System.out.println(l);
                    }
                }

            } else if (pilihan == 3) {
                System.out.print("Masukkan ID Lapangan: ");
                String id = sc.nextLine();
                boolean ditemukan = false;
                for (Lapangan l : daftarLapangan) {
                    if (l.getId().equalsIgnoreCase(id)) {
                        System.out.print("Nama Baru: ");
                        l.setNama(sc.nextLine());
                        System.out.print("Harga Baru: ");
                        l.setHarga(Double.parseDouble(sc.nextLine()));
                        ditemukan = true;
                        System.out.println("Data berhasil diubah!");
                    }
                }
                if (!ditemukan) {
                    System.out.println("Lapangan tidak ditemukan.");
                }

            } else if (pilihan == 4) {
                System.out.print("Masukkan ID Lapangan: ");
                String id = sc.nextLine();
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
                System.out.print("Nama Peralatan (Raket/Bola): ");
                String nama = sc.nextLine();
                System.out.print("Harga Sewa: ");
                double harga = Double.parseDouble(sc.nextLine());
                System.out.print("Jumlah Stok: ");
                int stok = Integer.parseInt(sc.nextLine());
                daftarPeralatan.add(new Peralatan("P" + noPeralatan++, nama, harga, stok));
                System.out.println("Peralatan berhasil ditambahkan!");

            } else if (pilihan == 2) {
                if (daftarPeralatan.isEmpty()) {
                    System.out.println("Belum ada data peralatan.");
                } else {
                    for (Peralatan p : daftarPeralatan) {
                        System.out.println(p);
                    }
                }

            } else if (pilihan == 3) {
                System.out.print("Masukkan ID Peralatan: ");
                String id = sc.nextLine();
                boolean ditemukan = false;
                for (Peralatan p : daftarPeralatan) {
                    if (p.getId().equalsIgnoreCase(id)) {
                        System.out.print("Nama Baru: ");
                        p.setNama(sc.nextLine());
                        System.out.print("Harga Baru: ");
                        p.setHarga(Double.parseDouble(sc.nextLine()));
                        System.out.print("Stok Baru: ");
                        p.setStok(Integer.parseInt(sc.nextLine()));
                        ditemukan = true;
                        System.out.println("Data berhasil diubah!");
                    }
                }
                if (!ditemukan) {
                    System.out.println("Peralatan tidak ditemukan.");
                }

            } else if (pilihan == 4) {
                System.out.print("Masukkan ID Peralatan: ");
                String id = sc.nextLine();
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
                System.out.print("Masukkan ID Penyewaan: ");
                String id = sc.nextLine();
                boolean ditemukan = false;
                for (Penyewaan s : daftarPenyewaan) {
                    if (s.getId().equalsIgnoreCase(id)) {
                        System.out.print("Nama Penyewa Baru: ");
                        s.setNamaPenyewa(sc.nextLine());
                        System.out.print("Total Harga Baru: ");
                        s.setTotalHarga(Double.parseDouble(sc.nextLine()));
                        ditemukan = true;
                        System.out.println("Data berhasil diubah!");
                    }
                }
                if (!ditemukan) {
                    System.out.println("Penyewaan tidak ditemukan.");
                }

            } else if (pilihan == 4) {
                System.out.print("Masukkan ID Penyewaan: ");
                String id = sc.nextLine();
                boolean berhasil = daftarPenyewaan.removeIf(s -> s.getId().equalsIgnoreCase(id));
                System.out.println(berhasil ? "Data berhasil dihapus!" : "Penyewaan tidak ditemukan.");
            }
        } while (pilihan != 0);
    }

    // Menambah data penyewaan, bisa untuk lapangan ataupun peralatan
    static void tambahPenyewaan() {
        System.out.println("Sewa apa? 1. Lapangan   2. Peralatan");
        System.out.print("Pilih: ");
        int jenis = bacaAngka();

        System.out.print("Nama Penyewa: ");
        String nama = sc.nextLine();

        if (jenis == 1) {
            for (Lapangan l : daftarLapangan) {
                System.out.println(l);
            }
            System.out.print("Masukkan ID Lapangan: ");
            String id = sc.nextLine();
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
            System.out.print("Durasi (jam): ");
            int durasi = Integer.parseInt(sc.nextLine());
            double total = lp.getHarga() * durasi;
            daftarPenyewaan.add(new Penyewaan("SW" + noSewa++, nama, "Lapangan", lp.getNama(), total));
            lp.setStatus("Disewa");
            System.out.println("Penyewaan lapangan berhasil! Total: Rp" + total);

        } else if (jenis == 2) {
            for (Peralatan p : daftarPeralatan) {
                System.out.println(p);
            }
            System.out.print("Masukkan ID Peralatan: ");
            String id = sc.nextLine();
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
            System.out.print("Jumlah: ");
            int jumlah = Integer.parseInt(sc.nextLine());
            if (jumlah > pl.getStok()) {
                System.out.println("Stok tidak mencukupi. Stok tersedia: " + pl.getStok());
                return;
            }
            double total = pl.getHarga() * jumlah;
            daftarPenyewaan.add(new Penyewaan("SW" + noSewa++, nama, "Peralatan", pl.getNama(), total));
            pl.setStok(pl.getStok() - jumlah);
            System.out.println("Penyewaan peralatan berhasil! Total: Rp" + total);

        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }
}
