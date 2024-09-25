 /*Project - Personal Fitness Tracker 2.0
 *Author: Khang Ho 
 --------------------------------------*/
import java.util.ArrayList;
import java.util.Scanner;

class Workout 
{
    String date;
    String type; 
    double distance; 
    double duration; 
    double calories; 
    int steps; 

    public Workout(String date, String type, double distance, double duration) 
    {
        this.date = date;
        this.type = type;
        this.distance = distance;
        this.duration = duration;
        this.calories = calculateCalories(); 
        this.steps = type.equalsIgnoreCase("Walking") ? calculateSteps() : 0; 
    }

    private double calculateCalories() 
    {
        switch (type.toLowerCase()) 
        {
            case "running":
                return distance * 62; 
            case "cycling":
                return distance * 50; 
            case "walking":
                return distance * 45; 
            default:
                return 0; 
        }
    }

    private int calculateSteps() 
    {
        return (int)(distance * 1200); 
    }
}

public class PersonalFitnessTracker2 
{
    private static ArrayList<Workout> workouts = new ArrayList<>();

    public static void main(String[] args) 
    {
        Scanner scan = new Scanner(System.in);
        String moreWorkouts;

        do 
        {
            String date;
            while (true) 
            {
                System.out.print("Enter workout date (YYYY-MM-DD): ");
                date = scan.nextLine();
                if (date.matches("\\d{4}-\\d{2}-\\d{2}")) 
                {
                    break; 
                } else 
                {
                    System.out.println("Invalid date format. Please try again.");
                }
            }

            System.out.print("Enter workout type (e.g., Running, Cycling): ");
            String type = scan.nextLine();

            double distance = -1;
            while (distance < 0) 
            {
                System.out.print("Enter distance (in kilometers): ");
                distance = scan.nextDouble();
                if (distance < 0) 
                {
                    System.out.println("Distance cannot be negative. Please enter a valid distance.");
                }
            }

            double duration = -1;
            while (duration < 0) 
            {
                System.out.print("Enter duration (in minutes): ");
                duration = scan.nextDouble();
                if (duration < 0) 
                {
                    System.out.println("Duration cannot be negative. Please enter a valid duration.");
                }
            }

            workouts.add(new Workout(date, type, distance, duration));

            System.out.print("Do you want to add another workout? (yes/no): ");
            scan.nextLine(); 
            moreWorkouts = scan.nextLine();
        } while (moreWorkouts.equalsIgnoreCase("yes"));   

        displaySummary();
        scan.close();
    }

    private static void displaySummary() 
    {
        double totalDistance = 0;
        double totalDuration = 0;
        double totalCalories = 0;
        int totalSteps = 0;

        System.out.println("\n--- Fitness Summary Report ---");
        for (Workout workout : workouts) 
        {
            System.out.printf("Date: %s | Type: %s | Distance: %.2f km | Duration: %.3f min | Calories: %.2f kcal | Steps: %d%n",
                    workout.date, workout.type, workout.distance, workout.duration, workout.calories, workout.steps);
            totalDistance += workout.distance;
            totalDuration += workout.duration;
            totalCalories += workout.calories;
            totalSteps += workout.steps;
        }

        double averagePace = totalDistance > 0 ? totalDuration / totalDistance : 0;
        System.out.printf("Total Distance: %.2f km%n", totalDistance);
        System.out.printf("Total Duration: %.3f min%n", totalDuration);
        System.out.printf("Total Calories: %.2f kcal%n", totalCalories);
        System.out.printf("Total Steps (Walking): %d steps%n", totalSteps);
        System.out.printf("Average Pace: %.2f min/km%n", averagePace);
    }
}