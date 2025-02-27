package Ticketing;

import java.util.ArrayList;

class Film {
    private String judul;
    private String genre;
    private int durasi; // dalam menit

    public Film(String judul, String genre, int durasi) {
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }

    public void tampilkanInfo() {
        System.out.println("Judul: " + judul);
        System.out.println("Genre: " + genre);
        System.out.println("Durasi: " + durasi + " menit");
    }
}

class Tiket {
    private Film film;
    private String jadwal;
    private double harga;

    public Tiket(Film film, String jadwal, double harga) {
        this.film = film;
        this.jadwal = jadwal;
        this.harga = harga;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getJadwal() {
        return jadwal;
    }

    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public void tampilkanInfo() {
        System.out.println("\n=== Detail Tiket ===");
        film.tampilkanInfo();
        System.out.println("Jadwal: " + jadwal);
        System.out.println("Harga: Rp. " + String.format("%,.2f", harga));
    }
}

class Pelanggan {
    private String nama;
    private String email;
    
    public Pelanggan(String nama, String email) {
        this.nama = nama;
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

class Pemesanan {
    private Pelanggan pelanggan;
    private Tiket tiket;
    private int jumlah;

    public Pemesanan(Pelanggan pelanggan, Tiket tiket, int jumlah) {
        this.pelanggan = pelanggan;
        this.tiket = tiket;
        this.jumlah = jumlah;
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public void setPelanggan(Pelanggan pelanggan) {
        this.pelanggan = pelanggan;
    }

    public Tiket getTiket() {
        return tiket;
    }

    public void setTiket(Tiket tiket) {
        this.tiket = tiket;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void prosesPemesanan() {
        System.out.println("\n=== Pesanan Berhasil! ===");
        System.out.println("Pelanggan: " + pelanggan.getNama());
        System.out.println("Film: " + tiket.getFilm().getJudul());
        System.out.println("Jadwal: " + tiket.getJadwal());
        System.out.println("Jumlah Tiket: " + jumlah);
        System.out.println("Total Harga: Rp. " + String.format("%,.2f", (jumlah * tiket.getHarga())));
    }
}

class Theater {
    private ArrayList<Film> daftarFilm;

    public Theater() {
        this.daftarFilm = new ArrayList<>();
    }

    public void tambahFilm(Film film) {
        daftarFilm.add(film);
    }

    public void tampilkanFilm() {
        System.out.println("\n===== Daftar Film di Theater =====");
        if (!daftarFilm.isEmpty()) {
            for (int i = 0; i < daftarFilm.size(); i++) {
                System.out.println((i + 1) + ". " + daftarFilm.get(i).getJudul() + " - " + daftarFilm.get(i).getGenre());
        } 
        }
    }

    public Film cariFilm(String judul) {
        for (Film film : daftarFilm) {
            if (film.getJudul().equalsIgnoreCase(judul)) {
                return film;
            }
        }
        return null;
    }
}

class Ticketing {
    public static void main(String[] args) {
        // Membuat theater dan menambahkan film
        Theater theater = new Theater();
        theater.tambahFilm(new Film("Inception", "Sci-Fi", 148));
        theater.tambahFilm(new Film("Interstellar", "Sci-Fi", 169));
        theater.tambahFilm(new Film("Titanic", "Romance", 195));

        // Menampilkan daftar film
        theater.tampilkanFilm();

        // Mencari film tertentu
        String filmPilihan = "Inception";
        Film film1 = theater.cariFilm(filmPilihan);

        if (film1 != null) {
            film1.tampilkanInfo();

            // Membuat objek pelanggan
            Pelanggan pelanggan1 = new Pelanggan("Andi", "andi@email.com");

            // Membuat objek tiket
            Tiket tiket1 = new Tiket(film1, "20:00 WIB", 50000);
            tiket1.tampilkanInfo();

            // Membuat objek pemesanan
            Pemesanan pemesanan1 = new Pemesanan(pelanggan1, tiket1, 2);
            pemesanan1.prosesPemesanan();
        } else {
            System.out.println("\nFilm tidak ditemukan.");
        }
    }
}
