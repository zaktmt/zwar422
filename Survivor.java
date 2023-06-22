package zombieWar;

// Survivor class
public abstract class Survivor {

    private int health;
    private final int attackDamage;

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public void attack(Zombie zombie) {

        Weapon weapon = this.getWeapon();
        double accuracy = (this.getBaseAccuracy() * this.getWeapon().getAccuracy());

        if (weapon instanceof RangedWeapon) {

            for (int i = 0; i < ((RangedWeapon) weapon).getNumProjectiles(); i++) {

                if (Math.random() < accuracy) {
                    zombie.setHealth(zombie.getHealth() - weapon.getAttack());
                }

            }

        } else if (weapon instanceof MeleeWeapon) {

            if (Math.random() < accuracy) {
                zombie.setHealth(zombie.getHealth() - (this.attackDamage + weapon.getAttack()));
            }

        }

    }

    public Survivor(int health) {
        this.health = health;
        this.attackDamage = 2;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public abstract String getName();
    public abstract double getBaseAccuracy();
    public abstract Weapon getWeapon();
    public abstract void setWeapon(Weapon weapon);

}

// Scientist with 20 HP, 30% base accuracy
class Scientist extends Survivor {

    private Weapon weapon;
    private final String name;
    private final double baseAccuracy;

    public Scientist(String name, Weapon weapon) {
        super(20);
        this.name = name;
        this.baseAccuracy = 0.3;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public double getBaseAccuracy() {
        return baseAccuracy;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
}

// Civilian with 50 HP, 60% base accuracy
class Civilian extends Survivor {

    private Weapon weapon;
    private final String name;
    private final double baseAccuracy;

    public Civilian(String name, Weapon weapon) {
        super(50);
        this.name = name;
        this.baseAccuracy = 0.6;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public double getBaseAccuracy() {
        return baseAccuracy;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

}

// Soldier with 100 HP, 90% base accuracy
class Soldier extends Survivor {

    private Weapon weapon;
    private final String name;
    private final double baseAccuracy;

    public Soldier(String name, Weapon weapon) {
        super(100);
        this.name = name;
        this.baseAccuracy = 0.9;
        this.weapon = weapon;
    }

    public String getName() {
        return name;
    }

    public double getBaseAccuracy() {
        return baseAccuracy;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

}