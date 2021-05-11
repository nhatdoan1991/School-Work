package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class PlayerCyborg extends Cyborg {


	private static PlayerCyborg myCyborg;
	private PlayerCyborg() {
	
		this.setSteeringDirection(0);
		this.setHeading(0);
		this.setSpeed(0);
		this.setMaximumSpeed(40);
		this.setEnergyConsumptionRate(1);;
		this.setDamageLevel(0);
		this.setEnergyLevel(1000);;
		this.setLastBaseReached(1);;
		this.setColor(ColorUtil.rgb(225, 20, 20));
		this.setSize(100);
		this.setRandomLocation();
		this.setSpeedWithDamage(0);
	}
	public static PlayerCyborg getPlayerCyborg() {
		if(myCyborg==null)
		{
			myCyborg = new PlayerCyborg();
		}
		return myCyborg;
	}
	public void energyLostAfterTick()
	{
		this.setEnergyLevel(this.getEnergyLevel()-this.getEnergyConsumptionRate());
	}

	public void collideWithDrone()
	{
		this.setDamageLevel(this.getDamageLevel()+3);
		super.setColor(ColorUtil.rgb(0, 0, (this.getDamageLevel()*250)/10));
		this.setSpeedWithDamage(this.getDamageLevel());
	}

	public String toString() {
		String thisclassData = "\nMy Cyborg"+super.toString();
		return thisclassData;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xLocation = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLocation = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		
		g.setColor(this.getColor());
		g.drawRect(xLocation-this.getSize()/2, yLocation-this.getSize()/2, this.getSize()*5, this.getSize()*5);
		g.fillRect(xLocation-this.getSize()/2, yLocation-this.getSize()/2, this.getSize()*5, this.getSize()*5);
	}
}
