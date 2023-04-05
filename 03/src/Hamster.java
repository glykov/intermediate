import java.util.Random;

public class Hamster extends PetAnimal{
    public Hamster(String name, int year, int month, int day, String... commands) {
        super(name, year, month, day, commands);
    }

    @Override
    public String performCommand(int command) {
        Random random = new Random();
        int chance = random.nextInt();

        if (chance < 50) {
            return "Ignoring and eating carrot";
        }

        return "I can only run in the wheel";
    }
}