import java.time.*;
import java.util.*;

public abstract class Animal {
    private String name;
    private LocalDate birthDate;
    private List<String> commands = new ArrayList<>();

    public Animal(String name, int year, int month, int day, String... commands) {
        this.name = name;
        birthDate = LocalDate.of(year, month, day);
        for (var command : commands) {
            this.commands.add(command);
        }
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void addCommand(String command) {
        commands.add(command);
    }

    public String performCommand(int command) {
        if (command >= 0 && command < commands.size()) {
            return commands.get(command);
        }
        return "No such command present";
    }

}