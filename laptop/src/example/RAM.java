package example;

public class RAM {

    private int size;
    private String generation;

    public RAM(int sizeString, String generation) {
        this.size = size;
        this.generation = generation;

    }

    @Override
    public String toString() {
        return "RAM{" + "size=" + size + ", generation='" + generation + '\'' + '}';
    }
}
