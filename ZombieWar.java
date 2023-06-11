package zombieWar;

import java.util.ArrayList;

// Zombie Project - Zach Kasen, Gianna Bedford, Samuel Sobotka
public class ZombieWar {

	public static void main(String[] args) {
		
		int numSurvivors = (int) (Math.random() * 19 + 1);
		int numZombies = (int) (Math.random() * 19 + 1);

		ArrayList<Survivor> survivors = new ArrayList<>();
		ArrayList<Zombie> zombies = new ArrayList<>();

		int numScientists = 1;
		int numCivilians = 1;
		int numSoldiers = 1;

		for (int s = 0; s < numSurvivors; s++) {

			int survivorType = (int) (Math.random() * 3);

			survivors.add(switch (survivorType) {
				case 0 -> new Scientist("Scientist " + numScientists++);
				case 1 -> new Civilian("Civilian " + numCivilians++);
				case 2 -> new Soldier("Soldier " + numSoldiers++);
				default ->
						throw new IllegalStateException(String.format("Survivor type number %d does not correspond to a possible survivor type.", survivorType));
			});

		}

		System.out.printf("There are %d survivors trying to make it to safety. There are %d scientists, %d civilians, and %d soldiers.\n",
				survivors.size(), --numScientists, --numCivilians, --numSoldiers);

		int numCommonInfected = 1;
		int numTanks = 1;

		for (int z = 0; z < numZombies; z++) {

			int zombieType = (int) (Math.random() * 4);

			zombies.add(switch (zombieType) {
				case 0,1,2 -> new CommonInfected("CommonInfected " + numCommonInfected++);
				case 3 -> new Tank("Tank " + numTanks++);
				default ->
						throw new IllegalStateException(String.format("Zombie type number %d does not correspond to a possible zombie type.", zombieType));
			});

		}

		System.out.printf("But %d zombies are waiting for them. The survivors face %d common infected and %d tanks.\n",
				zombies.size(), --numCommonInfected, --numTanks);

		System.out.println();

		while (!survivors.isEmpty() && !zombies.isEmpty()) {

			for (Survivor survivor : survivors) {

				for (Zombie zombie : zombies) {

					survivor.attack(zombie);

					if (zombie.isDead()) {
						zombies.remove(zombie);
						printDeathMessage(survivor, zombie);
						break;
					}

				}

			}

			for (Zombie zombie : zombies) {

				for (Survivor survivor : survivors) {

					zombie.attack(survivor);

					if (survivor.isDead()) {
						survivors.remove(survivor);
						printDeathMessage(zombie, survivor);
						break;
					}

				}

			}

		}

		System.out.println();

		if (survivors.isEmpty()) {
			System.out.println("None of the survivors made it. :(");
		} else {
			System.out.printf("%d survivors have made it to safety!", survivors.size());
		}
		
	}

	private static void printDeathMessage(Survivor killer, Zombie victim) {

		switch (killer.getClass().getSimpleName()) {
			case "Scientist" -> System.out.printf("%s was slapped by %s\n", victim.getName(), killer.getName());
			case "Civilian" -> System.out.printf("%s was speared by %s\n", victim.getName(), killer.getName());
			case "Soldier" -> System.out.printf("%s was shot by %s\n", victim.getName(), killer.getName());
		}

	}

	private static void printDeathMessage(Zombie killer, Survivor victim) {

		switch (killer.getClass().getSimpleName()) {
			case "CommonInfected" -> System.out.printf("%s was eaten by %s\n", victim.getName(), killer.getName());
			case "Tank" -> System.out.printf("%s was crushed by %s\n", victim.getName(), killer.getName());
		}

	}

}
