package Restaurant;

public class Menu {
    private String nama;
    private double harga;
    private int stok;

    public Menu(String nama, double harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public boolean isOutOfStock() {
        return stok == 0;
    }

    public void kurangiStok(int jumlah) {
        if (stok >= jumlah) {
            stok -= jumlah;
        } else {
            System.out.println("Stok tidak cukup!");
        }
    }
}
