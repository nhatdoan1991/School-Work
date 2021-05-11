package com.mycompany.a2;
abstract public class Movable extends GameObject{
	
	private int heading ;
	private int speed;
	private float movX;
	private float movY;
	private int speedWithDamage;
	
	public int getSpeedWithDamage()
	{
		return this.speedWithDamage;
	}
	public void setSpeedWithDamage(int damage) {
		this.speedWithDamage= ((100-(damage*10))*this.getSpeed())/100;
	}
	
	public void move() {
		this.setMovX();
		this.setMovY();
		if((super.getLocation().getX()+movX)>1000||super.getLocation().getX()+movX<0||super.getLocation().getY()+movY>1000||super.getLocation().getY()+movY<0)
		{
			this.bound();
		}else
		{
			super.setLocation(this.getMovX()+super.getX(),this.getMovY()+super.getY());
		}
	}
	private void setMovX()
	{
		this.movX=Math.round((Math.cos(Math.toRadians(-(this.heading-90)))*this.getSpeedWithDamage()));
	}
	private float getMovX()
	{
		return this.movX;
	}
	private void setMovY()
	{
		this.movY=Math.round((Math.sin(Math.toRadians(-(this.heading-90)))*this.getSpeedWithDamage()));
	}
	private float getMovY()
	{
		return this.movY;
	}
	public void setHeading(int heading) {
		this.heading=heading;
	}
	public int getHeading(){
		return this.heading;
	}
	public void setSpeed(int speed) {
		this.speed=speed;
	}
	public int getSpeed() {
		return this.speed;
	}
	public void bound() {
		this.heading=this.heading+180;
		this.checkHeadingBoudaries();
	}
	
	public void checkHeadingBoudaries()
	{
		if(this.heading>360)
		{
			this.heading=this.heading-360;
		}else if(this.heading<0)
		{
			this.heading=this.heading+360;
		}
	}
	@Override
	public String toString()
	{
		String thisCLassData=" heading = "+this.getHeading()+" speed = " +this.getSpeedWithDamage();
		thisCLassData =super.toString()+ thisCLassData;
		return thisCLassData;
	}
}
