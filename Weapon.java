package zombieWar;

public abstract class Weapon {

    private final String name;
    private final int attack;
    private final double accuracy;
    private final String killAction;

    public Weapon(String name, int attack, double accuracy, String killAction) {
        this.name = name;
        this.attack = attack;
        this.accuracy = accuracy;
        this.killAction = killAction;
    }

    public int getAttack() {
        return attack;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public String getName() {
        return name;
    }

    public String getKillAction() {
        return killAction;
    }

}

class MeleeWeapon extends Weapon {
    public MeleeWeapon(String name, int attack, double accuracy, String killAction) {
        super(name, attack, accuracy, killAction);
    }
}

class RangedWeapon extends Weapon {

    private final int numProjectiles;

    public RangedWeapon(String name, int attack, double accuracy, int numProjectiles, String killAction) {
        super(name, attack, accuracy, killAction);
        this.numProjectiles = numProjectiles;
    }

    public int getNumProjectiles() {
        return numProjectiles;
    }

}