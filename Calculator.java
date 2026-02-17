public class Calculator {
    
    public static String processRequest(String request) {
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
