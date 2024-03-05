import java.util.Scanner;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class Main {
    public static void main(String[] args) {
    	AnsiConsole.systemInstall();
        Scanner scanner = new Scanner(System.in);
        int gameCount = 5;
        int symptomCount = 22;

        // Data structure to store symptom severity for recent games
        int[][] symptomSeverity = new int[gameCount][symptomCount];

        for (int game = 0; game < gameCount; game++) {
            System.out.println("\nPlease enter symptom severity information for Game " + (game + 1) + ":");

            for (int symptom = 0; symptom < symptomCount; symptom++) {
                System.out.print("Enter severity for " + getSymptomName(symptom) + " (none (0), mild (1-2), moderate (3-4), & severe (5-6)): ");
                int severity = scanner.nextInt();

                if (severity < 0 || severity > 6) {
                    System.out.println("Invalid severity level. Please enter a value between 0 and 6.");
                    symptom--; // Repeat for the same symptom
                } else {
                    symptomSeverity[game][symptom] = severity;
                }
            }

            // Display symptom summary after each game
            displaySymptomSummary(symptomSeverity, game);
            if (game > 0) {
                String rating = calculateOverallRating(symptomSeverity, game);
                System.out.println("Overall Rating: " + rating);
                if (game >= 2) {
                    String riskyCondition = checkRiskyCondition(symptomSeverity, game);
                    displayRiskyConditionIndicator(riskyCondition);
                }
            }
        }

        scanner.close();
    }

    // Function to display symptom summary for a specific game
    private static void displaySymptomSummary(int[][] symptoms, int game) {
        System.out.println("\nSymptom Summary for Game " + (game + 1) + ":");
        int totalSymptoms = 0;
        int severityScore = 0;

        for (int i = 0; i < symptoms[game].length; i++) {
            System.out.println(getSymptomName(i) + ": " + symptoms[game][i]);
            totalSymptoms++;
            if(symptoms[game][i] == 0)
            {
            	totalSymptoms--;
            }
            severityScore += symptoms[game][i];
        }

        System.out.println("Total Number of Symptoms: " + totalSymptoms);
        System.out.println("Symptom Severity Score: " + severityScore);
    }

    // Function to calculate the overall rating based on criteria
    private static String calculateOverallRating(int[][] symptoms, int game) {
        if (game < 1) {
            return "No Difference";
        }

        int totalDifference = 0;
        int severityScore = 0;

        for (int i = 0; i < symptoms[game].length; i++) {
            totalDifference += Math.abs(symptoms[game][i] - symptoms[game - 1][i]);
            severityScore += symptoms[game][i];
        }

        if (totalDifference < 3 && severityScore < 10) {
            return "No Difference";
        } else if (totalDifference < 3 && severityScore >= 10) {
            return "Unsure";
        } else if(totalDifference >= 3 || severityScore >= 15){
            return "Very Different";
        }
		return "Error: could not determine Overall rating";
    }

    // Function to check for a risky condition
    private static String checkRiskyCondition(int[][] symptoms, int game) {
        if (game < 2) {
            return "Not Risky";
        }

        String rating1 = calculateOverallRating(symptoms, game - 2);
        String rating2 = calculateOverallRating(symptoms, game - 1);

        if (rating1.equals("Very Different") && rating2.equals("Very Different")) {
            return "Risky";
        }
        else if(rating1.equals("No Different") && rating2.equals("No Different")) 
        {
        	return "Not Risky";
        }
        return "Unsure";
    }

 // Function to display risky condition indicator with color
    private static void displayRiskyConditionIndicator(String condition) {
        System.out.println("Risky Condition: " + condition);

        Ansi.Color color;

        // Determine the color based on the condition
        if (condition.equals("Risky")) {
            color = Ansi.Color.RED;
        } else if (condition.equals("Not Risky")) {
            color = Ansi.Color.GREEN;
        } else if (condition.equals("Unsure")) {
            color = Ansi.Color.YELLOW;
        } else {
            color = Ansi.Color.DEFAULT;
        }

        System.out.println(Ansi.ansi().fg(color).a("************").reset());
        System.out.println(Ansi.ansi().fg(color).a("*  " + condition + "   *").reset());
        System.out.println(Ansi.ansi().fg(color).a("************").reset());
    }
    

    // Helper function to get symptom names
    private static String getSymptomName(int index) {
        String[] symptomNames = {
            "HEADACHE", "PRESSURE_IN_HEAD", "NECK_PAIN", "NAUSEA_OR_VOMITTING", "DIZZINESS", "BLURRED_VISION",
            "BALANCE_PROBLEMS", "SENSITIVITY_TO_LIGHT", "SENSITIVITY_TO_NOISE", "FEELING_SLOWED_DOWN",
            "FEELING_LIKE_IN_A_FOG", "DONT_FEEL_RIGHT", "DIFFICULTY_CONCENTRATING", "DIFFICULTY_REMEMBERING",
            "FATIGUE_LOW_ENERGY", "CONFUSION", "DROWSINESS", "TROULE_FALLING_ASLEEP", "MORE_EMOTIONAL",
            "IRRITABILITY", "SADNESS", "NERVOUS_OR_ANXIOUS"
        };

        return symptomNames[index];
    }
    
}
