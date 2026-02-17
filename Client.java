import java.io.*;
import java.net.*;

public class Client {
    
    public static String sendRequest(String host, int port, double num1, String operator, double num2) {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
            String request = num1 + " " + operator + " " + num2;
            out.println(request);
            
            String response = in.readLine();
            return response;
            
        } catch (IOException e) {
            return "ERROR: Connection failed - " + e.getMessage();
        }
    }
    
    public static void main(String[] args) {
        if (args.length != 5) {
            System.out.println("Usage: java Client <host> <port> <num1> <operator> <num2>");
            System.out.println("Operators: A (addition), S (subtraction), M (multiplication), D (division)");
            return;
        }
        
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        double num1 = Double.parseDouble(args[2]);
        String operator = args[3];
        double num2 = Double.parseDouble(args[4]);
        
        String result = sendRequest(host, port, num1, operator, num2);
        System.out.println("Result: " + result);
    }
}
