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

}

// Scientist with 20 HP, 2 AD
class Scientist extends Survivor {
    public Scientist() {
        super(20, 2);
    }
}

// Civilian with 50 HP, 5 AD
class Civilian extends Survivor {
    public Civilian() {
        super(50, 5);
    }
}

// Soldier with 100 HP, 10 AD
class Soldier extends Survivor {
    public Soldier() {
        super(100, 10);
    }
}