import java.nio.charset.*;
import java.util.*;
import java.io.*;

public class Main {
    private static final int[] idArray = new int[] {4, 5, 6, 7, 8, 9, 10};
    private static final String[] nameArray = new String[] {"board games", "robot", "constructing set", "teddy bear", "doll", "car"};
    private static final int[] weightArray = new int[] {1, 2, 3};
    private static final PriorityQueue<Prise> prises = new PriorityQueue<>();

    public static void readDataFromFile(String fileName) {
        try (Scanner in = new Scanner(new FileInputStream(fileName), StandardCharsets.UTF_8);) {
            while (in.hasNextLine()) {
                int id = in.nextInt();
                int weight = in.nextInt();
                String name = in.nextLine().trim();
                prises.add(new Prise(id, name, weight));
            }
        } catch (IOException e) {
            System.out.println("Cannot read data from file " + fileName);
        }
    }

    public static void writeDataToFile(String fileName) {
        try (PrintWriter out = new PrintWriter(fileName, StandardCharsets.UTF_8)) {
            while (!prises.isEmpty()) {
                var prise = prises.poll();
                StringBuilder sb = new StringBuilder();
                sb.append(prise.getId() + " ");
                sb.append(prise.getWeight() + " ");
                sb.append(prise.getName());
                out.println(sb.toString());
            }    
        } catch (IOException e) {
            System.out.println("Cannot write data to file " + fileName);
        }
    }

    public static void put(String description) {
        String[] items = description.split("\\s+", 3);
        prises.add(new Prise(Integer.parseInt(items[0]), items[2], Integer.parseInt(items[1])));
    }

    public static void get(String fileName) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(new File(fileName), true))) {
            var prise = prises.poll();
            int id = prise.getId();
            int weight = prise.getWeight();
            String name = prise.getName();
            System.out.printf("The toy with id %d will be available in %d of cases\n", id, (weight * 10));
            out.println(id + " " + weight + " " + name);   
        } catch (IOException e) {
            System.out.println("Cannot write data to file " + fileName);
        }
    }


    public static void main(String[] args) {
        // reading three records
        readDataFromFile("data.txt");

        Random rand = new Random();
        
        for (int i = 0; i < 7; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(idArray[i] + " ");
            sb.append(weightArray[rand.nextInt(weightArray.length)] + " ");
            sb.append(nameArray[rand.nextInt(nameArray.length)]);
            put(sb.toString());
        }

        while(!prises.isEmpty()) {
            get("sorted_data.txt");
        }


    }
}
