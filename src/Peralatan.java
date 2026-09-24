/**
 * Subclass Peralatan, mewarisi (extends) superclass ItemSewa.
 * Menambahkan atribut khusus yang tidak dimiliki Lapangan, yaitu "stok".
 */
public class Peralatan extends ItemSewa {
    private int stok;

    // Constructor subclass, memanggil constructor superclass dengan super()
    public Peralatan(String id, String nama, double harga, int stok) {
        super(id, nama, harga);
        this.stok = stok;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    // Override method abstrak dari superclass (polymorphism - overriding)
    @Override
    public String tampilkanInfo() {
        return "ID: " + getId() + " | Nama: " + getNama()
                + " | Harga Sewa: Rp" + getHarga() + " | Stok: " + stok;
    }
}