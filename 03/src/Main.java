import java.rmi.registry.Registry;
import java.util.*;

public class Main {
    private static AnimalRegistry registry = new AnimalRegistry();

    public static void main(String[] args) {
        while (true) {
            int action = registry.getAction();
            if (action == 3) {
                break;
            } else if (action == 1) {
                registry.addAnimal();
            } else if (action == 2) {
                registry.workingWithExistingAnimal();
            } else {
                System.out.println("You entered wrong number! Try again");
            }
        }
    }
}