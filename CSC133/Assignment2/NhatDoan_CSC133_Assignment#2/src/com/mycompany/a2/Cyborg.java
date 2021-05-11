package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;

abstract public class Cyborg extends Movable implements ISteerable {
	
	private int steeringDirection;
	private int maximumSpeed;
	private int energyLevel;
	private int energyComsumptionRate;
	private int damageLevel=0;
	private int lastBaseReached;
	
	public Cyborg()
	{
		this.steeringDirection=0;
		super.setHeading(0);
		super.setSpeed(0);
		this.maximumSpeed=40;
		this.energyComsumptionRate=5;
		this.damageLevel=0;
		this.energyLevel=100;
		this.lastBaseReached=1;
		super.setColor(ColorUtil.BLUE);
		super.setSize(15);
		super.setRandomLocation();

	}
	public void changeHeading(char input) {
		switch(input) {
		case 'l':
			if(this.steeringDirection>-40)
			{
				this.steeringDirection=this.steeringDirection-5;
				System.out.println("You steering direction is "+ this.getSteeringDirection());
			}
			else
			{
				System.out.println("You cant steer left anymore!!!!");
			}
			break;
		case 'r':
			if(this.steeringDirection<40)
			{
				this.steeringDirection=this.steeringDirection+5;
				System.out.println("You steering direction is "+ this.getSteeringDirection());
			}
			else
			{
				System.out.println("You cant steer right anymore!!!!");
			}
			break;
		}	
	}
	public int getSteeringDirection()
	{
		return this.steeringDirection;
	}
	public void setSteeringDirection(int newSteeringDirection)
	{
		this.steeringDirection=newSteeringDirection;
	}
	public int getMaximumSpeed() {
		return this.maximumSpeed;
	}
	public void setMaximumSpeed(int s) {
		this.maximumSpeed=s;
	}
	public int getEnergyLevel()
	{
		return this.energyLevel;
	}
	public void setEnergyLevel(int energy)
	{
		this.energyLevel=energy;
	}
	public int getEnergyConsumptionRate() {
		return this.energyComsumptionRate;
	}
	public void setEnergyConsumptionRate(int rate) {
		this.energyComsumptionRate=rate;
	}
	public void setDamageLevel(int Damage) {
		this.damageLevel=Damage;
	}
	public int getDamageLevel() {
		return this.damageLevel;
	}
	public boolean isMaxSpeed() {
		return super.getSpeed()>this.getMaximumSpeed();
	}
	public void speedUp()
	{
		super.setSpeed(super.getSpeed()+5);
		this.setSpeedWhileDamage();
		if(this.isMaxSpeed())
		{
			super.setSpeed(maximumSpeed);
			System.out.println("You reached to the max speed!!!");
		}
	}
	public void slowDown()
	{
		super.setSpeed(super.getSpeed()-5);
		this.setSpeedWhileDamage();
		if(super.getSpeed()<=0)
		{
			super.setSpeed(0);
			System.out.println("You stopped!!!");
		}
	}
	public boolean isBroken() {
		return (this.getDamageLevel() >=10);
	}
	public boolean isOutOfBattery() {
		return (this.getEnergyLevel()<=0);
	}
	public int reduceSpeedWithDamage() {
		 return ((this.getSpeed()*this.damageLevel)/10);
	}
	
	public void baseReach(int baseNumber) {
		 if(baseNumber==(this.lastBaseReached+1))
		 {
			 this.lastBaseReached=baseNumber;
		 }
	}
	public int getLastBaseReached() {
		 return this.lastBaseReached;
	 }
	public void setLastBaseReached(int lastBase) {
		this.lastBaseReached= lastBase;
	}
	public int reachEnergyStation(int energy) {
		 this.energyLevel=this.energyLevel+energy;
		 if(this.getEnergyLevel()>=100) {
			 int a=this.getEnergyLevel()-100;
			 this.setEnergyLevel(100);
			 return a;//MAX
		 }
		 return 0;
	 }
	public boolean isAtLastBase()
	 {
		 if(super.getLastBase()==this.lastBaseReached)
		 {
			 return true;
		 }
		 return false;
	 }
	public void setSpeedWhileDamage() {
		super.setSpeedWithDamage(this.getDamageLevel());
		
	}
	 @Override
	public void setSize(int size) 
	 {}
	public String toString() {
			String thisclassData = super.toString()+(" maxSpeed = " +this.getMaximumSpeed()+" steeringDirection= "+this.getSteeringDirection()+" energyLevel= "+this.getEnergyLevel()+" damageLevel="+this.getDamageLevel());
			return thisclassData;
	 }

}
