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
        zombie.setHealth(zombie.getHealth() - this.attackDamage);
    }

    public Survivor(int health, int attackDamage) {
        this.health = health;
        this.attackDamage = attackDamage;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    public abstract String getName();

}

// Scientist with 20 HP, 2 AD
class Scientist extends Survivor {

    private final String name;

    public Scientist(String name) {
        super(20, 2);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

// Civilian with 50 HP, 5 AD
class Civilian extends Survivor {

    private final String name;

    public Civilian(String name) {
        super(50, 5);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

// Soldier with 100 HP, 10 AD
class Soldier extends Survivor {

    private final String name;

    public Soldier(String name) {
        super(100, 10);
        this.name = name;
    }

    public String getName() {
        return name;
    }

}