import java.io.*;

public class AnimalCounter implements Closeable {
    static int count;

    public AnimalCounter() {
        count++;
    }
    
    @Override
    public void close() throws IOException {
        // do nothing
    }
}