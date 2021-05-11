package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;

public class PlayerCyborg extends Cyborg {


	private static PlayerCyborg myCyborg;
	private PlayerCyborg() {
	
		this.setSteeringDirection(0);
		this.setHeading(0);
		this.setSpeed(0);
		this.setMaximumSpeed(40);
		this.setEnergyConsumptionRate(3);;
		this.setDamageLevel(0);
		this.setEnergyLevel(100);;
		this.setLastBaseReached(1);;
		this.setColor(ColorUtil.BLUE);
		this.setSize(15);
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
	public void collideWithCyborg()
	 {
		this.setDamageLevel(this.getDamageLevel()+5);
		this.setColor(ColorUtil.rgb(0, 0, (this.getDamageLevel()*250)/10));
		this.setSpeedWithDamage(this.getDamageLevel());
	 }
	public String cyborgCollide()
	{
		return "Cyborg's damage level is "+ this.getDamageLevel()+"\nCyborg's color is: " + "[" + ColorUtil.red(this.getColor()) + "," + 
				+ ColorUtil.green(this.getColor()) + "," + 
				+ ColorUtil.blue(this.getColor()) + "]";
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
}
