import java.util.Random;

public class Camel extends PackAnimal{
    public Camel(String name, int year, int month, int day, double load, String... commands) {
        super(name, year, month, day, load, commands);
    }

    @Override
    public String performCommand(int command) {
        Random random = new Random();
        int chance = random.nextInt(100);

        if (chance < 70) {
            return "Spit on you" + super.performCommand(command);
        }

        return super.performCommand(command);
    }
}