package zombieWar;

public class ZombieWar {
	//Zombie Project - Zach Kasen, Gianna Bedford, Samuel Sabotka




	//survivor class

	//Scientist, Civilian, Soldier
	class Survivor{
	    private int health;
	    private int attackDamage;
	    private String survivorType;
	    private boolean isDead;


	    public int getHealth(){
	        return health;
	    }
	    public int getAttackDamage(){
	        return attackDamage;
	    }
	    public String getSurvivorType(){
	        return survivorType;
	    }
	    public boolean getIsDead(){
	        return isDead;
	    }
	    public void setHealth(int health){
	        this.health = health;
	    }
	    public void setAttackDamage(int attackDamage){
	        this.attackDamage = attackDamage;
	    }
	    public void setSurvivorType(String survivorType){
	        this.survivorType = survivorType;
	    }
	    public void setIsDead(boolean isDead){
	        this.isDead = isDead;
	    }
	    public Survivor(int health, int attackDamage, String survivorType){
	        this.health = health;
	        this.attackDamage = attackDamage;
	        this.survivorType = survivorType;
	        this.isDead = false;
	    }



	}




	//zombie class

	//CommonInfected and Tank

	class Zombie{
	    private int health;
	    private int attackDamage;
	    private String zombieType;
	    private boolean isDead;

	    public Zombie(int health, int attackDamage, String zombieType){
	    	this.health = health;
	        this.attackDamage = attackDamage;
	        this.zombieType = zombieType;
	    }
	    public int getAttackDamange(){
	        return attackDamage;
	    }
	 
	    public String getZombieType(){
	        return zombieType;
	    }

	    public void setAttackDatmage(int attackDamage){
	        this.attackDamage = attackDamage;
	    }
	  
	    public void setZombieType(String zombieType){
	        this.zombieType = zombieType;
	    }
	    public void setHealth(int health){
	        this.health = health;
	    }
	    public int getHealth(){
	        return health;
	    }
	    public void setIsDead(boolean isDead){
	        this.isDead = isDead;
	    }
	    public boolean getIsDead(){
	        return isDead;
	    }
	  
	}

	public static void main(String[] args) {
		
		
		
	}

}
