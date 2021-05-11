package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

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
		this.setEnergyLevel(1000);;
		this.setLastBaseReached(1);;
		this.setColor(ColorUtil.rgb(255,0,0));
		this.setSize(80);
		this.setRandomLocation();
		this.setSpeedWithDamage(0);
		this.setLastBaseReached(1);
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

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xLocation = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLocation = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		
		g.setColor(this.getColor());
		g.drawRect(xLocation-this.getSize()/2, yLocation-this.getSize()/2, this.getSize()*5, this.getSize()*5);
	}

}
