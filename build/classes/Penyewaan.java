public class Penyewaan {
    private String id;
    private String namaPenyewa;
    private String jenis;     // "Lapangan" atau "Peralatan"
    private String namaItem;
    private double totalHarga;

    public Penyewaan(String id, String namaPenyewa, String jenis, String namaItem, double totalHarga) {
        this.id = id;
        this.namaPenyewa = namaPenyewa;
        this.jenis = jenis;
        this.namaItem = namaItem;
        this.totalHarga = totalHarga;
    }

    public String getId() {
        return id;
    }

    public String getNamaPenyewa() {
        return namaPenyewa;
    }

    public void setNamaPenyewa(String namaPenyewa) {
        this.namaPenyewa = namaPenyewa;
    }

    public String getJenis() {
        return jenis;
    }

    public String getNamaItem() {
        return namaItem;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public void setTotalHarga(double totalHarga) {
        this.totalHarga = totalHarga;
    }

    public String toString() {
        return "ID Sewa: " + id + " | Penyewa: " + namaPenyewa + " | Jenis: " + jenis
                + " | Item: " + namaItem + " | Total: Rp" + totalHarga;
    }
}
