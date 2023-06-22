package zombieWar;

import java.util.*;
import java.io.*;
import static zombieWar.ZWarUtils.*;

// Zombie Project - Zach Kasen, Gianna Bedford, Samuel Sobotka
public class ZombieWar {

    // each type of weapon
    static Weapon[] commonWeapons = {
            new RangedWeapon("Pistol", 20, 0.9, 1, "%s was shot by %s using %s"),
            new MeleeWeapon("Axe", 30, 2, "%s was chopped in two by %s using %s"),
            new MeleeWeapon("Crowbar", 15, 2, "%s was bashed by %s's %s"),
            new MeleeWeapon("Kitchen Knife", 10, 2, "%s was stabbed by %s with a %s"),
            new MeleeWeapon("Sword", 20, 2, "%s was sliced by %s with a %s")
    };

    static Weapon[] uncommonWeapons = {
            new RangedWeapon("Shotgun", 4, 0.6, 15, "%s was riddled with shot by %s's %s"),
            new RangedWeapon("Submachine Gun", 8, 0.8, 3, "%s was perforated by %s using %s"),
            new RangedWeapon("Bow and Arrow", 18, 0.95, 1, "%s was shot by %s's %s"),
            new MeleeWeapon("Chainsaw",18,2, "%s was Texas Chainsaw Massacred by %s. Yes, he had a %s.")
    };

    static Weapon[] rareWeapons = {
            new RangedWeapon("Assault Rifle", 10, 0.9, 3, "%s was tactically shot by %s's tacticool %s"),
            new MeleeWeapon("Frying Pan", 10, 2, "%s was bonked by %s with a %s"),
            new MeleeWeapon("Lance", 35, 1.5, "%s was charged by the noble %s with his steed and %s")
    };

    static Weapon[] epicWeapons = {
            new RangedWeapon("Sniper", 30, 1, 1, "%s was sniped by %s using %s"),
            new RangedWeapon("Ninja Star", 8, 0.75, 4, "%s was ninja'd by %s's %s"),
            new MeleeWeapon("Lucille", 30, 2, "%s was baseballed by %s. %s On Top!"), // barbed wire bat from The Walking Dead
            new RangedWeapon("Mario Kart Blue Shell", 50, 4,1, "%s was shelled by %s with a %s")
    };

    static Weapon[] legendaryWeapons = {
            new RangedWeapon("Star Platinum: Za Warudo", 6, 4, 30, "%s was ORA ORA'd by %s's stand %s"), // While Star Platinum does punch zombies, the stand itself has 6 ft range plus SP's arms, so it counts as ranged (survivor does not have to walk up to zombie to ORA ORA it)
            new RangedWeapon("Necronomicon", 160, 4, 1, "%s could not stand against %s's undead army summoned with the %s"), // attack in percent, scaling with undead attack (summoned undead buffed by Necronomicon)
            new RangedWeapon("Flamethrower", 1, 0.4,200, "%s was burned by %s's %s")
    };

    public static void main(String[] args) {

        int numSurvivors = (int) (Math.random() * 19 + 1); // Random number of survivors
        int numZombies = (int) (Math.random() * 19 + 1); // Random number of survivors

        ArrayList<Weapon> weaponPool = new ArrayList<>(); // Weapon pool

        double odds = (double) numSurvivors / (numSurvivors + numZombies); // Survivor percentage (survivors to total, less than 50% means survivors are outnumbered)

        // Generate survivors
        ArrayList<Survivor> survivors = generateSurvivors(numSurvivors, true);

        // add random weapons to the pool
        int totalRarity = 101;
        int rarityChoice;
        int weaponChoice;

        for (int i = 0; i < numSurvivors; i++) {

            rarityChoice = (int) (Math.random() * totalRarity);

            if (rarityChoice < 50) { // Common
                weaponChoice = (int) (Math.random() * commonWeapons.length);
                weaponPool.add(commonWeapons[weaponChoice]);
            } else if (rarityChoice < 50 + 30) { // Uncommon
                weaponChoice = (int) (Math.random() * uncommonWeapons.length);
                weaponPool.add(uncommonWeapons[weaponChoice]);
            } else if (rarityChoice < 50 + 30 + 15) { // Rare
                weaponChoice = (int) (Math.random() * rareWeapons.length);
                weaponPool.add(rareWeapons[weaponChoice]);
            } else if (rarityChoice < 50 + 30 + 15 + 5) { // Epic
                weaponChoice = (int) (Math.random() * epicWeapons.length);
                weaponPool.add(epicWeapons[weaponChoice]);
            } else if (rarityChoice < 50 + 30 + 15 + 5 + 1) { // Legendary
                weaponChoice = (int) (Math.random() * legendaryWeapons.length);
                weaponPool.add(legendaryWeapons[weaponChoice]);
            }

        }

        // Randomly distribute weapons
        for (Survivor survivor : survivors) {
            weaponChoice = (int) (Math.random() * weaponPool.size());
            survivor.setWeapon(weaponPool.get(weaponChoice));
            weaponPool.remove(weaponChoice);
        }

        // Generate zombies
        ArrayList<Zombie> zombies = generateZombies(numZombies, true);

        System.out.println();
        System.out.printf("Odds of survivors winning: %.2f%%\n", odds * 100);

        // Main loop -- runs until either all survivors or all zombies die
        while (!survivors.isEmpty() && !zombies.isEmpty()) {

            for (Survivor survivor : survivors) { // Each survivor

                for (Zombie zombie : zombies) { // attacks every zombie.

                    if (survivor.getWeapon().getName().equals("Necronomicon")) { // Special Necronomicon attack

                        // Summon three zombies to fight for the survivors!
                        ArrayList<Zombie> summonedZombies = ZWarUtils.generateZombies(3, false);

                        // Each summoned zombie attacks the target
                        for (Zombie summoned : summonedZombies) {
                            summoned.attack(zombie, summoned.getAttackDamage() * (survivor.getWeapon().getAttack() / 100));
                            if (zombie.isDead()) break;
                        }

                    } else {
                        survivor.attack(zombie);
                    }

                    if (zombie.isDead()) {
                        zombies.remove(zombie);
                        printDeathMessage(survivor, zombie);
                        break; // Do not continue attacking a dead zombie
                    }

                }

            }

            for (Zombie zombie : zombies) { // Then, each zombie

                for (Survivor survivor : survivors) { // attacks every survivor.

                    zombie.attack(survivor);

                    if (survivor.isDead()) {
                        survivors.remove(survivor);
                        printDeathMessage(zombie, survivor);
                        break; // Do not continue attacking a dead survivor
                    }

                }

            }

        }

        // Report
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

            // Print name, health, and weapon of each survivor (some might need medical attention :P)
            for (Survivor survivor : survivors)
                System.out.printf("%-16s Health: %-8d Weapon: %s\n", survivor.getName(), survivor.getHealth(), survivor.getWeapon().getName());

            System.out.println("Humanity lives to survive another day!");
            percent = survivors.size() / (double) numSurvivors; // Percent calculation
            System.out.printf("Percent of survivors remaining: %.2f%%\n", percent * 100);

        }

        // Re-run simulation or save results to file
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

}