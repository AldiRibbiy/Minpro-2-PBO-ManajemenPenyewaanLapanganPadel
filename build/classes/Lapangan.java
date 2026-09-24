public class Lapangan {
    private String id;
    private String nama;
    private double harga;
    private String status;

    public Lapangan(String id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.status = "Tersedia";
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return "ID: " + id + " | Nama: " + nama + " | Harga/Jam: Rp" + harga + " | Status: " + status;
    }
}
