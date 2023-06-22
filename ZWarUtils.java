package zombieWar;

import java.util.ArrayList;

public class ZWarUtils {

    public static ArrayList<Zombie> generateZombies(int numZombies, boolean message) {

        ArrayList<Zombie> zombies = new ArrayList<>(); // Zombie array

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

        if (message) {
            System.out.printf("But %d zombies are waiting for them. The survivors face %d common infected and %d tanks.\n",
                    zombies.size(), numCommonInfected, numTanks);
        }

        return zombies;

    }

    public static ArrayList<Survivor> generateSurvivors(int numSurvivors, boolean message) {

        ArrayList<Survivor> survivors = new ArrayList<>(); // Survivor array

        // counters for each survivor type
        int numScientists = 0;
        int numCivilians = 0;
        int numSoldiers = 0;

        // Generate survivor array
        for (int s = 0; s < numSurvivors; s++) {

            int survivorType = (int) (Math.random() * 3);

            survivors.add(switch (survivorType) {
                case 0 -> new Scientist("Scientist " + ++numScientists, null); // Type 0 -> Scientist
                case 1 -> new Civilian("Civilian " + ++numCivilians, null); // Type 1 -> Civilian
                case 2 -> new Soldier("Soldier " + ++numSoldiers, null); // Type 2 -> Soldier
                default -> // Any other type is invalid
                        throw new IllegalStateException(String.format("Survivor type number %d does not correspond to a possible survivor type.", survivorType));
            });

        }

        if (message) {
            System.out.printf("There are %d survivors trying to make it to safety. There are %d scientists, %d civilians, and %d soldiers.\n",
                    survivors.size(), numScientists, numCivilians, numSoldiers);
        }

        return survivors;

    }

    public static void printDeathMessage(Survivor killer, Zombie victim) {
        // Print death message based on weapon
        System.out.printf(killer.getWeapon().getKillAction() + "\n", victim.getName(), killer.getName(), killer.getWeapon().getName());
    }

    public static void printDeathMessage(Zombie killer, Survivor victim) {

        switch (killer.getClass().getSimpleName()) { // Print custom death message by simple class name
            case "CommonInfected" -> System.out.printf("%s was eaten by %s\n", victim.getName(), killer.getName());
            case "Tank" -> System.out.printf("%s was crushed by %s\n", victim.getName(), killer.getName());
        }

    }

}