public abstract class PackAnimal extends Animal {
    private double load;

    public PackAnimal(String name, int year, int month, int day, double load, String... commands) {
        super(name, year, month, day, commands);
        this.load = load;
    }

    public double getLoad() {
        return load;
    }

    public void setLoad(double load) {
        this.load = load;
    }
}