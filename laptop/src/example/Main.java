package example;

public class Main {
    public static void main(String[] args) {
        Processor p1 = new Processor("Ryzen 7", 4);
        RAM r1 = new RAM(16, "DDR4");
        HardDrive h1 = new HardDrive(1000, "HDD");
        Laptop manavLaptop = new Laptop("Lenovo", p1, r1, h1);

        Processor p2 = new Processor("i9", 5);
        RAM r2 = new RAM(32, "DDR5");
        HardDrive h2 = new HardDrive(2048, "SSD");
        Laptop rashmiLaptop = new Laptop("HP", p2, r2, h2);

        System.out.println("\nManav's Laptop: " + manavLaptop);
        System.out.println("\nRashmi's Laptop: " + rashmiLaptop);
    }
}