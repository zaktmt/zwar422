package zombieWar;

// Zombie class
public abstract class Zombie {

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

    public void attack(Survivor survivor) {
        survivor.setHealth(survivor.getHealth() - this.attackDamage);
    }

    public Zombie(int health, int attackDamage) {
        this.health = health;
        this.attackDamage = attackDamage;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public abstract String getName();

}

// CommonInfected with 30 HP, 5 AD
class CommonInfected extends Zombie {

    private final String name;

    public CommonInfected(String name) {
        super(30, 5);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

// Tank with 150 HP, 20 AD
class Tank extends Zombie {

    private final String name;

    public Tank(String name) {
        super(150, 20);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}