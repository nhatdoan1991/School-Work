package com.mycompany.a3;
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
		if((((100-(damage*10))*this.getSpeed())/100)>0)
		this.speedWithDamage= ((100-(damage*10))*this.getSpeed())/100;
		else this.speedWithDamage=0;
	}
	
	public void move(double elapsedTime) {
		this.setMovX(elapsedTime);
		this.setMovY(elapsedTime);
		if((super.getLocation().getX()+movX)>1350||super.getLocation().getX()+movX<50||super.getLocation().getY()+movY>1050||super.getLocation().getY()+movY<50)
		{
			this.bound();
		}else
		{
			super.setLocation(this.getMovX()+super.getX(),this.getMovY()+super.getY());
		}
	}
	private void setMovX(double elapsedTime)
	{
		this.movX=Math.round((Math.cos(Math.toRadians(-(this.heading-90)))*this.getSpeedWithDamage()*elapsedTime/1000));
	}
	private float getMovX()
	{
		return this.movX;
	}
	private void setMovY(double elapsedTime)
	{
		this.movY=Math.round((Math.sin(Math.toRadians(-(this.heading-90)))*this.getSpeedWithDamage()*elapsedTime/1000));
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
	@Override
	public boolean collidewith(GameObject otherobject) {
		boolean result = false;
		double distanceX = this.getLocation().getX() - otherobject.getLocation().getX();
		double distanceY = this.getLocation().getY() - otherobject.getLocation().getY();
		double distanceBetweenObjectsSquare = distanceX*distanceX+distanceY*distanceY;
		double thisRadius = this.getSize()/2;
		double otherObjectRadius= otherobject.getSize()/2;
		double sumRadiusSquare= thisRadius*thisRadius+2*thisRadius*otherObjectRadius+otherObjectRadius*otherObjectRadius;
		if(distanceBetweenObjectsSquare <= sumRadiusSquare)
		{
			result=true;
		}
		return result;
	}
	
}
