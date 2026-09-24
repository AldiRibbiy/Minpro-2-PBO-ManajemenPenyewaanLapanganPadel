public class Peralatan {
    private String id;
    private String nama;
    private double harga;
    private int stok;

    public Peralatan(String id, String nama, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
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

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public String toString() {
        return "ID: " + id + " | Nama: " + nama + " | Harga Sewa: Rp" + harga + " | Stok: " + stok;
    }
}
