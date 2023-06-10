package zombieWar;

// Survivor class
public class Survivor {

    private int health;
    private int attackDamage;


    public int getHealth() {
        return health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public boolean isDead() {
        return this.health <= 0;
    }

    public Survivor(int health, int attackDamage) {
        this.health = health;
        this.attackDamage = attackDamage;
    }

}

// Scientist
class Scientist extends Survivor {
    public Scientist() {
        super(20, 2);
    }
}

// Civilian
class Civilian extends Survivor {
    public Civilian() {
        super(50, 5);
    }
}

// Soldier
class Soldier extends Survivor {
    public Soldier() {
        super(100, 10);
    }
}