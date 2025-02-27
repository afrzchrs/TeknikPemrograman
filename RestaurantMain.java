package Restaurant;

public class RestaurantMain {
    public static void main(String[] args) {
        Restaurant menu = new Restaurant(10); // Kapasitas menu

        menu.tambahMenuMakanan("Bala-Bala", 1000, 20);
        menu.tambahMenuMakanan("Gehuw", 1000, 20);
        menu.tambahMenuMakanan("Tahu", 1000, 0);
        menu.tambahMenuMakanan("Molen", 1000, 20);

        menu.tampilMenuMakanan();

        System.out.println("\nMelakukan pemesanan...");
        menu.pemesanan("Bala-Bala", 5);
        menu.pemesanan("Tahu", 2);
        menu.pemesanan("Gehuw", 10);

        System.out.println("\nMenu setelah pemesanan:");
        menu.tampilMenuMakanan();
    }
}
