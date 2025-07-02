package example;

public class Laptop {
    private String logo;
    private Processor processor;
    private RAM ram;
    private HardDrive hardDrive;

    public Laptop(String logo, Processor processor, RAM ram, HardDrive hardDrive) {
        this.logo = logo;
        this.processor = processor;
        this.ram = ram;
        this.hardDrive = hardDrive;
    }

    @Override
    public String toString() {
        return "Laptop{" + "logo='" + logo + '\'' + ", processor=" + processor + ", ram=" + ram + ", hardDrive=" + hardDrive + '}';
    }
}
