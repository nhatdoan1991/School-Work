package com.mycompany.a2;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class NonPlayerCyborg extends Cyborg{

	private Random random = new Random();
	private IStrategy currentStrategy;
	public NonPlayerCyborg() {
		this.setSteeringDirection(0);
		this.setHeading(random.nextInt(360));
		this.setSpeed(0);
		this.setMaximumSpeed(30+random.nextInt(15));
		this.setEnergyConsumptionRate(0);;
		this.setDamageLevel(0);
		this.setEnergyLevel(100);;
		this.setLastBaseReached(1);;
		this.setColor(ColorUtil.YELLOW);
		this.setSize(20);
		this.setRandomLocation();
		this.setSpeedWithDamage(0);
	}
	
	public void collideWithPlayerCyborg()
	 {
		this.setDamageLevel(this.getDamageLevel()+2);
		this.setColor(ColorUtil.rgb(0, 0, (this.getDamageLevel()*250)/10));
		this.setSpeedWithDamage(this.getDamageLevel());
	 }
	public void setStrategy(IStrategy s) {
			currentStrategy=s;
	}
	public void invokeStratergy() {
		currentStrategy.apply();
	}
	@Override 
	public String toString() {	
		String strategy = null;
		if(currentStrategy instanceof ReachToLastBaseStrategy)
		{
			strategy = "Reach To Last Base ,";
		}else if(currentStrategy instanceof AttackPlayerCyborgStrategy){
			strategy = "Attack Player Cyborg ,";
		}
		return ("NPC Cyborg: Straegy "+strategy+super.toString());
	}
}
