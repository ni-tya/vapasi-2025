package example;

public class HardDrive {

    private int size;
    private String type;

    public HardDrive(int size, String type) {
        this.size = size;
        this.type = type;
    }

    @Override
    public String toString() {
        return "HardDrive{" + "size=" + size + ", type='" + type + '\'' + '}';
    }
}
