import java.util.Random;
import java.util.Scanner;

// Class Terorist (Menerapkan enkapsulasi)
class Terorist {
    private String name;
    private int hp;
    private int armor;

    public Terorist(String name, int hp, int armor) {
        this.name = name;
        this.hp = hp;
        this.armor = armor;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0); // Mencegah HP bernilai negatif
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = Math.max(armor, 0); // Mencegah Armor bernilai negatif
    }
}

// Class Weapon (Menerapkan enkapsulasi)
class Weapon {
    private String weaponName;
    private int weaponDamage;

    public Weapon(String weaponName, int weaponDamage) {
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public int getWeaponDamage() {
        return weaponDamage;
    }
}

// Class Player
class Player {
    private String playerName;
    private int hp;
    private int armor;

    public Player(String name, int hp, int armor) {
        this.playerName = name;
        this.hp = hp;
        this.armor = armor;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = Math.max(hp, 0); // Mencegah HP negatif
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = Math.max(armor, 0); // Mencegah Armor negatif
    }
}

// Class CounterStrike (Menggunakan Terorist dan Weapon -> "use-a" Relationship)
class PB {
    private Player player;
    private Terorist terorist;
    private Weapon[] weapons;
    private Random random = new Random();
    private Scanner scanner = new Scanner(System.in);

    public PB(Player player) {
        this.player = player;
        this.terorist = new Terorist("Terrorist", 100, 50);

        // Daftar senjata yang bisa digunakan
        this.weapons = new Weapon[]{
            new Weapon("Pistol", 15),
            new Weapon("M4A4", 30),
            new Weapon("Grenade", 50)
        };
    }

    public void start() {
        System.out.println("\n=======> Battle Start! " + player.getPlayerName() + " vs " + terorist.getName() + " <=======");

        while (terorist.getHp() > 0 && player.getHp() > 0) {
            playerTurn();
            if (terorist.getHp() <= 0) {
                System.out.println("\n" + player.getPlayerName() + " defeated the Terrorist!");
                break;
            }

            terroristTurn();
            if (player.getHp() <= 0) {
                System.out.println("\n" + player.getPlayerName() + " was defeated by the Terrorist...");
                break;
            }
        }
    }

    private void playerTurn() {
        System.out.println("\n == <> ==| "+ player.getPlayerName() +"'s TURN ! |== <> == ");

        System.out.println("\nTerrorist HP: " + terorist.getHp() + " | Armor: " + terorist.getArmor() + "");
        System.out.println("\nPlayer HP: " + player.getHp() + " | Armor: " + player.getArmor()+ "\n");

        System.out.println("====| WEAPON LIST |====");
        for (int i = 0; i < weapons.length; i++) {
            System.out.println((i + 1) + ". " + weapons[i].getWeaponName() + " (Damage: " + weapons[i].getWeaponDamage() + ")");
        }
        
        System.out.println("Choose your weapon:");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > weapons.length) {
            System.out.println(" Invalid choice! Turn skipped.");
            return;
        }

        Weapon chosenWeapon = weapons[choice - 1];
        int damage = chosenWeapon.getWeaponDamage();

        if (chosenWeapon.getWeaponName().equals("Grenade")) {
            if (random.nextInt(100) < 50) { // 50% chance hit
                System.out.println(" \nBOOM! Grenade exploded! It dealt " + damage + " damage!");
            } else {
                System.out.println("\nThe grenade missed!");
                return;
            }
        } else {
            System.out.println("\nYou used " + chosenWeapon.getWeaponName() + "! It dealt " + damage + " damage!");
        }

        // Damage ke armor dulu sebelum ke HP
        if (terorist.getArmor() > 0) {
            int remainingArmor = Math.max(terorist.getArmor() - damage, 0);
            int absorbedDamage = terorist.getArmor() - remainingArmor;
            terorist.setArmor(remainingArmor);
            damage -= absorbedDamage;
        }

        if (damage > 0) {
            terorist.setHp(terorist.getHp() - damage);
        }
    }

    private void terroristTurn() {
        System.out.println("\n == <!> == The Terrorist is attacking! == <!> == ");
    
        // Pilih senjata secara acak dari daftar weapons
        int randomNum = random.nextInt(weapons.length); // Memastikan indeks valid
        Weapon chosenWeapon = weapons[randomNum];
        int Tdamage = chosenWeapon.getWeaponDamage();
    
        if (chosenWeapon.getWeaponName().equals("Grenade")) {
            if (random.nextInt(100) < 50) {                                                            // 50% chance hit
                System.out.println("\nBOOM! The Terrorist used Grenade! It dealt " + Tdamage + " damage!");
            } else {
                System.out.println("\nThe Terrorist threw a grenade, but it missed!");
                return;
            }
        } else {
            System.out.println("\nThe Terrorist used " + chosenWeapon.getWeaponName() + "! It dealt " + Tdamage + " damage!");
        }
    
        // Damage ke armor dulu sebelum ke HP
        if (player.getArmor() > 0) {
            int remainingArmor = Math.max(player.getArmor() - Tdamage, 0);
            int absorbedDamage = player.getArmor() - remainingArmor;
            player.setArmor(remainingArmor);
            Tdamage -= absorbedDamage;
        }
    
        if (Tdamage > 0) {
            player.setHp(player.getHp() - Tdamage);
        }
        
        System.out.println("\n player's Hp after taking damage : " + player.getHp() + " & Armor: " + player.getArmor());   
    } 
}
// Main Class
public class TugasJava{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Player Name: ");
        String name = scanner.nextLine();

        Player player = new Player(name, 100, 50); // Menambahkan HP dan armor untuk player
        PB battle = new PB(player);
        battle.start();
    } 
}

