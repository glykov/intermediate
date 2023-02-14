public class Prise implements Comparable<Prise> {
    private int id;
    private String name;
    private int weight;

    public Prise(int id, String name, int weight) {
        this.id = id;
        this.name = name;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Prise " + name + ", id = " + id +
                ", weight = " + weight;
    }

    @Override 
    public int compareTo(Prise other) {
        return weight - other.weight;
    }
}
