/**
 * Subclass Lapangan, mewarisi (extends) superclass ItemSewa.
 * Menambahkan atribut khusus yang tidak dimiliki Peralatan, yaitu "status".
 */
public class Lapangan extends ItemSewa {
    private String status; // "Tersedia" atau "Disewa"

    // Constructor subclass, memanggil constructor superclass dengan super()
    public Lapangan(String id, String nama, double harga) {
        super(id, nama, harga);
        this.status = "Tersedia";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Override method abstrak dari superclass (polymorphism - overriding)
    @Override
    public String tampilkanInfo() {
        return "ID: " + getId() + " | Nama: " + getNama()
                + " | Harga/Jam: Rp" + getHarga() + " | Status: " + status;
    }
}