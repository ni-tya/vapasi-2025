package example;

public class Processor {

    private String generation;
    private int gigahertz;

    public Processor(String generation, int gigahertz) {
        this.generation = generation;
        this.gigahertz = gigahertz;
    }

    @Override
    public String toString() {
        return "Processor{" + "generation='" + generation + '\'' + ", gigahertz=" + gigahertz + '}';
    }
}
