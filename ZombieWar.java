package zombieWar;

import java.util.*;
import java.io.*;

// Zombie Project - Zach Kasen, Gianna Bedford, Samuel Sobotka
public class ZombieWar {

    public static void main(String[] args) {

        int numSurvivors = (int) (Math.random() * 19 + 1); // Random number of survivors
        int numZombies = (int) (Math.random() * 19 + 1); // Random number of survivors

        ArrayList<Survivor> survivors = new ArrayList<>(); // Survivor array
        ArrayList<Zombie> zombies = new ArrayList<>(); // Zombie array

        double odds = (double) numSurvivors / (numSurvivors + numZombies); // Survivor percentage (survivors to total, less than 50% means survivors are outnumbered)

        // counters for each survivor type
        int numScientists = 0;
        int numCivilians = 0;
        int numSoldiers = 0;

        // Generate survivor array
        for (int s = 0; s < numSurvivors; s++) {

            int survivorType = (int) (Math.random() * 3);

            survivors.add(switch (survivorType) {
                case 0 -> new Scientist("Scientist " + ++numScientists); // Type 0 -> Scientist
                case 1 -> new Civilian("Civilian " + ++numCivilians); // Type 1 -> Civilian
                case 2 -> new Soldier("Soldier " + ++numSoldiers); // Type 2 -> Soldier
                default -> // Any other type is invalid
                        throw new IllegalStateException(String.format("Survivor type number %d does not correspond to a possible survivor type.", survivorType));
            });

        }

        System.out.printf("There are %d survivors trying to make it to safety. There are %d scientists, %d civilians, and %d soldiers.\n",
                survivors.size(), numScientists, numCivilians, numSoldiers);

        // counters for each zombie type
        int numCommonInfected = 0;
        int numTanks = 0;

        // Generate zombie array
        for (int z = 0; z < numZombies; z++) {

            int zombieType = (int) (Math.random() * 4);

            zombies.add(switch (zombieType) {
                case 0,1,2 -> new CommonInfected("CommonInfected " + ++numCommonInfected); // 75% chance for common infected
                case 3 -> new Tank("Tank " + ++numTanks); // 25% chance for tank
                default -> // Any other type is invalid
                        throw new IllegalStateException(String.format("Zombie type number %d does not correspond to a possible zombie type.", zombieType));
            });

        }

        System.out.printf("But %d zombies are waiting for them. The survivors face %d common infected and %d tanks.\n",
                zombies.size(), numCommonInfected, numTanks);

        System.out.println();
        System.out.printf("Odds of survivors winning: %.2f%%\n", odds * 100);

        // Main loop -- runs until either all survivors or all zombies die
        while (!survivors.isEmpty() && !zombies.isEmpty()) {

            for (Survivor survivor : survivors) { // Each survivor

                for (Zombie zombie : zombies) { // attacks every zombie

                    survivor.attack(zombie);

                    if (zombie.isDead()) {
                        zombies.remove(zombie);
                        printDeathMessage(survivor, zombie);
                        break; // Do not continue attacking a dead zombie
                    }

                }

            }


            for (Zombie zombie : zombies) { // Then, each zombie

                for (Survivor survivor : survivors) { // attacks every survivor

                    zombie.attack(survivor);

                    if (survivor.isDead()) {
                        survivors.remove(survivor);
                        printDeathMessage(zombie, survivor);
                        break; // Do not continue attacking a dead survivor
                    }

                }

            }

        }

        System.out.println();
        double percent = 0; // Percent of survivors that made it to safety

        if (survivors.isEmpty()) { // Zombies ate everyone

            System.out.println("None of the survivors made it. ☠");
            System.out.println();
            System.out.println("The zombie(s) remaining are:");

            for (Zombie zombie : zombies)
                System.out.println(zombie.getName());

            System.out.println("The zombies have taken over; all is lost!");

        } else {

            System.out.printf("%d survivors have made it to safety!\n", survivors.size());
            System.out.println();
            System.out.println("The survivor(s) are:");

            // Print name and health of each survivor (some might need medical attention :P)
            for (Survivor survivor : survivors)
                System.out.printf("%s Health: %d\n", survivor.getName(), survivor.getHealth());

            System.out.println("Humanity lives to survive another day!");
            percent = survivors.size() / (double) numSurvivors; // Percent calculation
            System.out.printf("Percent of survivors remaining: %.2f%%\n", percent * 100);

        }

        try (Scanner input = new Scanner(System.in)) {

            System.out.println("Would you like to run the simulation again? (y/n)");
            String answer = input.nextLine();

            if (answer.equalsIgnoreCase("y"))
                main(args); // Re-run the program
            else {

                System.out.println("Game over!");

                // Open file and append new results
                try (PrintWriter out = new PrintWriter(new FileWriter("results.txt", true))) {

                    out.println("\nZombie War Results");
                    out.println("==================");

                    if (survivors.isEmpty()) {

                        out.println("The zombies have taken over!\n");
                        out.println("Zombies remaining:");

                        for (Zombie zombie : zombies)
                            out.println(zombie.getName());

                    } else {

                        out.println("Humanity survives another day!");
                        out.println("The survivors that saved humanity:");

                        for (Survivor survivor : survivors)
                            out.println(survivor.getName());

                    }

                    out.println("Percentage of survivors remaining: " + percent * 100 + "%");
                    out.println("\n\n\n");
                    System.out.println("Results saved...");

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

    }

    private static void printDeathMessage(Survivor killer, Zombie victim) {

        switch (killer.getClass().getSimpleName()) { // Print custom death message by simple class name
            case "Scientist" -> System.out.printf("%s was slapped by %s\n", victim.getName(), killer.getName());
            case "Civilian" -> System.out.printf("%s was speared by %s\n", victim.getName(), killer.getName());
            case "Soldier" -> System.out.printf("%s was shot by %s\n", victim.getName(), killer.getName());
        }

    }

    private static void printDeathMessage(Zombie killer, Survivor victim) {

        switch (killer.getClass().getSimpleName()) { // Print custom death message by simple class name
            case "CommonInfected" -> System.out.printf("%s was eaten by %s\n", victim.getName(), killer.getName());
            case "Tank" -> System.out.printf("%s was crushed by %s\n", victim.getName(), killer.getName());
        }

    }

}