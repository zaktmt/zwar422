package zombieWar;

// Zombie class
public class Zombie {

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

    public Zombie(int health, int attackDamage) {
        this.health = health;
        this.attackDamage = attackDamage;
    }

}

// CommonInfected with 30 HP, 5 AD
class CommonInfected extends Zombie {
    public CommonInfected() {
        super(30, 5);
    }
}

// Tank with 150 HP, 20 AD
class Tank extends Zombie {
    public Tank() {
        super(150, 20);
    }
}