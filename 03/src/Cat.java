public class Cat extends PetAnimal{
    public Cat(String name, int year, int month, int day, String... commands) {
        super(name, year, month, day, commands);
    }

    @Override
    public String performCommand(int command) {
        return "I will ignore you, human";
    }
}