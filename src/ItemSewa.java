/**
 * Superclass ItemSewa.
 * Merupakan induk (parent class) dari Lapangan dan Peralatan,
 * karena keduanya sama-sama merupakan "sesuatu yang bisa disewa"
 * dan memiliki atribut umum: id, nama, dan harga.
 */
public abstract class ItemSewa {
    // Encapsulation: atribut bersifat private, hanya bisa diakses lewat getter/setter
    private String id;
    private String nama;
    private double harga;

    // Constructor superclass
    public ItemSewa(String id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
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

    // Method abstrak: WAJIB di-override oleh setiap subclass.
    // Inilah bentuk polymorphism (method overriding) yang diterapkan pada program ini.
    public abstract String tampilkanInfo();
}
