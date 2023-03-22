import java.util.Random;

public class Donkey extends PackAnimal{
    public Donkey(String name, int year, int month, int day, double load, String... commands) {
        super(name, year, month, day, load, commands);
    }

    @Override
    public String performCommand(int command) {
        Random random = new Random();
        int chance = random.nextInt(100);

        if (chance < 30) {
            return "Won't do nothing";
        }

        return super.performCommand(command);
    }
}