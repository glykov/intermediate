import java.io.IOException;
import java.util.*;

public class AnimalRegistry {
    private List<Animal> registry;
    private final Scanner scanner;

    public AnimalRegistry() {
        registry = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // printing main menu
    public void printMainMenu() {
        System.out.println("What would you like to do? (enter your choice number):");
        System.out.println("1. Add animal to registry");
        System.out.println("2. Work with animal in registry");
        System.out.println("3. Exit");
    }

    public int getAction() {
        printMainMenu();
        int choice = Integer.parseInt(scanner.nextLine());
        return choice;
    }

    // methods pertinent to adding new animal
    public void addAnimal() {
        try (AnimalCounter ac = new AnimalCounter()) {
            printAnimalTypes();
            int animalType = Integer.parseInt(scanner.nextLine());
            switch (animalType) {
                case 1:
                    addPetAnimal("dog");
                    break;
                case 2:
                    addPetAnimal("cat");
                    break;
                case 3:
                    addPetAnimal("hamster");
                    break;
                case 4:
                    addPackAnimal("horse");
                    break;
                case 5:
                    addPackAnimal("donkey");
                    break;
                case 6:
                    addPackAnimal("camel");
                    break;
                default:
                    System.out.println("You entered wrong number");
            }
        } catch (IOException e) {
            System.out.println("Counter is not closed");
        }
    }

    public void printAnimalTypes() {
        System.out.println("What animal would you like to add? (enter your choice number):");
        System.out.println("1. Dog");
        System.out.println("2. Cat");
        System.out.println("3. Hamster");
        System.out.println("4. Horse");
        System.out.println("5. Donkey");
        System.out.println("6. Camel");
    }

    private void addPetAnimal(String type) {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the year of birth: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the month of birth(1 to 12): ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the day of birth: ");
        int day = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the commands your animal can perform (separated by spaces): ");
        String[] commands = scanner.nextLine().split("\\S+");
        
        if (type.equals("dog")) {
            registry.add(new Dog(name, year, month, day, commands));
        } else if (type.equals("cat")) {
            registry.add(new Cat(name, year, month, day, commands));
        } else {
            registry.add(new Hamster(name, year, month, day, commands));
        }
    }

    private void addPackAnimal(String type) {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the year of birth: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the month of birth(1 to 12): ");
        int month = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the day of birth: ");
        int day = Integer.parseInt(scanner.nextLine());
        System.out.print("What load can this animal carry? ");
        double load = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter the commands your animal can perform (separated by spaces): ");
        String[] commands = scanner.nextLine().split("\\S+");
        
        if (type.equals("horse")) {
            registry.add(new Horse(name, year, month, day, load, commands));
        } else if (type.equals("donkey")) {
            registry.add(new Donkey(name, year, month, day, load, commands));
        } else {
            registry.add(new Camel(name, year, month, day, load, commands));
        }
    }

    // methods pertinent to working with animal already in registry
    public void workingWithExistingAnimal() {
        Animal friend = choosingAnimal();

        printWorkingMenu();
        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            printCommands(friend);
        } else if (choice == 2) {
            teachNewCommand(friend);
        } else {
            System.out.println("Choice is wrong");
        }
    }

    public Animal choosingAnimal() {
        System.out.println("We have following animals in the registry: ");
        for (int i = 0; i < registry.size(); i++) {
            System.out.printf("%d. %s - the %s\n", (i + 1), registry.get(i).getName(), registry.get(i).getClass().getSimpleName());
        }
        
        System.out.println("Which one do you want to work with?");
        int choice = Integer.parseInt(scanner.nextLine()) - 1;

        return registry.get(choice);
    }

    public void printWorkingMenu() {
        System.out.println("What would you like to do? (enter the choice number)");
        System.out.println("1. Watch the commad list");
        System.out.println("2. Teach the new commad");
    }

    public void printCommands(Animal animal) {
        System.out.println(animal.getName() + " can perform following commands:");
        for (var command : animal.getCommands()) {
            System.out.println(command);
        }
    }

    public void teachNewCommand(Animal animal) {
        System.out.print("Enter the command you want to teach your friend: ");
        String command = scanner.nextLine();
        animal.addCommand(command);
    }
}