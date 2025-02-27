package Restaurant;

public class Restaurant {
    private Menu[] daftarMenu;
    private int id;

    public Restaurant(int kapasitas) {
        this.daftarMenu = new Menu[kapasitas];
        this.id = 0;
    }

    public void tambahMenuMakanan(String nama, double harga, int stok) {
        if (id < daftarMenu.length) {
            daftarMenu[id] = new Menu(nama, harga, stok);
            id++;
        } else {
            System.out.println("Menu penuh, tidak bisa menambah makanan baru.");
        }
    }

    public void tampilMenuMakanan() {
        System.out.println("Daftar makanan yang tersedia");
        System.out.println("===========================");
        for (int i = 0; i < id; i++) {
            if (!daftarMenu[i].isOutOfStock()) {
                System.out.println(daftarMenu[i].getNama() + " [" + daftarMenu[i].getStok() + "]\tRp. " + daftarMenu[i].getHarga());
            }
        }
    }

    public void pemesanan(String nama, int jumlah) {
        for (int i = 0; i < id; i++) {
            if (daftarMenu[i].getNama().equalsIgnoreCase(nama)) {
                if (!daftarMenu[i].isOutOfStock() && daftarMenu[i].getStok() >= jumlah) {
                    daftarMenu[i].kurangiStok(jumlah);
                    System.out.println("Pesanan berhasil: " + jumlah + " " + nama + " dipesan.");
                } else {
                    System.out.println("Stok tidak cukup untuk " + nama + ". Tersisa: " + daftarMenu[i].getStok());
                }
                return;
            }
        }
        System.out.println("Makanan tidak ditemukan di menu.");
    }
}
