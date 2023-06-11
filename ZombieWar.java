package zombieWar;

import java.util.ArrayList;

// Zombie Project - Zach Kasen, Gianna Bedford, Samuel Sobotka
public class ZombieWar {

	public static void main(String[] args) {
		
		int numSurvivors = (int) (Math.random() * 19 + 1);
		int numZombies = (int) (Math.random() * 19 + 1);

		System.out.printf("There are %d survivors trying to make it to safety.\nBut %d zombies are waiting for them.\n", numSurvivors, numZombies);

		ArrayList<Survivor> survivors = new ArrayList<>();
		ArrayList<Zombie> zombies = new ArrayList<>();

		for (int s = 0; s < numSurvivors; s++) {

			int survivorType = (int) (Math.random() * 3);

			survivors.add(switch (survivorType) {
				case 0 -> new Scientist();
				case 1 -> new Civilian();
				case 2 -> new Soldier();
				default ->
						throw new IllegalStateException(String.format("Survivor type number %d does not correspond to a possible survivor type.", survivorType));
			});

		}

		for (int z = 0; z < numZombies; z++) {

			int zombieType = (int) (Math.random() * 2);

			zombies.add(switch (zombieType) {
				case 0 -> new CommonInfected();
				case 1 -> new Tank();
				default ->
						throw new IllegalStateException(String.format("Zombie type number %d does not correspond to a possible zombie type.", zombieType));
			});

		}

		while (!survivors.isEmpty() && !zombies.isEmpty()) {

			for (Survivor survivor : survivors) {

				for (Zombie zombie : zombies) {

					survivor.attack(zombie);

					if (zombie.isDead()) {
						zombies.remove(zombie);
						// Death message here - coming soon!
						break;
					}

				}

			}

			for (Zombie zombie : zombies) {

				for (Survivor survivor : survivors) {

					zombie.attack(survivor);

					if (survivor.isDead()) {
						survivors.remove(survivor);
						// Death message here - coming soon!
						break;
					}

				}

			}

		}

		if (survivors.isEmpty()) {
			System.out.println("None of the survivors made it. :(");
		} else {
			System.out.printf("%d survivor(s) have made it to safety!", survivors.size());
		}
		
	}

}
