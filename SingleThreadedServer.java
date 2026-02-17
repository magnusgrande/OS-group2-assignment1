import java.io.*;
import java.net.*;

public class SingleThreadedServer {
    private static final int PORT = 8080;
    
    public static void main(String[] args) {
        System.out.println("Single-Threaded Server starting on port " + PORT);
        
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
    
    private static void handleClient(Socket clientSocket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String request = in.readLine();
            if (request != null) {
                String result = processRequest(request);
                out.println(result);
            }
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
    
    private static String processRequest(String request) {
        try {
            String[] parts = request.split(" ");
            if (parts.length != 3) {
                return "ERROR: Invalid request format. Expected: <number> <operator> <number>";
            }
            
            double num1 = Double.parseDouble(parts[0]);
            String operator = parts[1].toUpperCase();
            double num2 = Double.parseDouble(parts[2]);
            
            double result;
            switch (operator) {
                case "A":
                    result = num1 + num2;
                    break;
                case "S":
                    result = num1 - num2;
                    break;
                case "M":
                    result = num1 * num2;
                    break;
                case "D":
                    if (num2 == 0) {
                        return "ERROR: Division by zero";
                    }
                    result = num1 / num2;
                    break;
                default:
                    return "ERROR: Invalid operator. Use A, S, M, or D";
            }
            
            return String.valueOf(result);
        } catch (NumberFormatException e) {
            return "ERROR: Invalid number format";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
