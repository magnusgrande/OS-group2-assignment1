import java.util.*;
import java.util.concurrent.*;

public class TestRunner {
    private static final String HOST = "localhost";
    private static final int NUM_CLIENTS = 10;
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java TestRunner <server-type>");
            System.out.println("server-type: single or multi");
            return;
        }
        
        String serverType = args[0].toLowerCase();
        int port;
        
        if (serverType.equals("single")) {
            port = 8080;
            System.out.println("Testing Single-Threaded Server on port " + port);
        } else if (serverType.equals("multi")) {
            port = 8081;
            System.out.println("Testing Multi-Threaded Server on port " + port);
        } else {
            System.out.println("Invalid server type. Use 'single' or 'multi'");
            return;
        }
        
        System.out.println("Running " + NUM_CLIENTS + " simultaneous client requests...\n");
        
        // Create test data
        double[][] testData = {
            {10, 5},   // 10 + 5 = 15
            {20, 8},   // 20 - 8 = 12
            {7, 6},    // 7 * 6 = 42
            {100, 4},  // 100 / 4 = 25
            {15, 3},   // 15 + 3 = 18
            {50, 10},  // 50 - 10 = 40
            {9, 9},    // 9 * 9 = 81
            {144, 12}, // 144 / 12 = 12
            {33, 7},   // 33 + 7 = 40
            {100, 25}  // 100 - 25 = 75
        };
        
        String[] operators = {"A", "S", "M", "D", "A", "S", "M", "D", "A", "S"};
        
        long startTime = System.currentTimeMillis();
        
        ExecutorService executor = Executors.newFixedThreadPool(NUM_CLIENTS);
        CountDownLatch latch = new CountDownLatch(NUM_CLIENTS);
        List<Future<String>> futures = new ArrayList<>();
        
        for (int i = 0; i < NUM_CLIENTS; i++) {
            final int clientId = i + 1;
            final double num1 = testData[i][0];
            final double num2 = testData[i][1];
            final String operator = operators[i];
            
            Future<String> future = executor.submit(() -> {
                try {
                    latch.countDown();
                    latch.await(); // Wait for all threads to be ready
                    
                    long clientStart = System.currentTimeMillis();
                    String result = Client.sendRequest(HOST, port, num1, operator, num2);
                    long clientEnd = System.currentTimeMillis();
                    long duration = clientEnd - clientStart;
                    
                    return String.format("Client %2d: %.1f %s %.1f = %s (Time: %d ms)", 
                                       clientId, num1, operator, num2, result, duration);
                } catch (Exception e) {
                    return "Client " + clientId + ": ERROR - " + e.getMessage();
                }
            });
            
            futures.add(future);
        }
        
        // Collect and display results
        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (Exception e) {
                System.out.println("Error getting result: " + e.getMessage());
            }
        }
        
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        executor.shutdown();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("Total execution time: " + totalTime + " ms");
        System.out.println("Average time per request: " + (totalTime / NUM_CLIENTS) + " ms");
        System.out.println("=".repeat(60));
    }
}
