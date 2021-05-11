package com.mycompany.a3;
import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


abstract public class GameObject extends GameWorld implements IDrawable, ICollider{

	
	Random random = new Random();
	private int size;
	private int color;
	private Point location= new Point();
	
	abstract public void draw(Graphics g, Point pCmpRelPrnt);
	public void setSize(int size)
	{
		this.size=size;
	}
	public GameObject() {	
	}
	public int getSize()
	{
		return this.size;
	}
	
	public Point getLocation()
	{	
		return location;
	}
	public float getX()
	{
		return this.getLocation().getX();
	}
	public float getY()
	{
		return this.getLocation().getY();
	}
	public void setLocation(float x,float y) 
	{
		this.location.setX(x);
		this.location.setY(y);
	}
	public void setLocation(Point point) 
	{
		this.location=point;
	}
	public void setRandomLocation() {
		this.location.setX(100+random.nextInt(1200));
		this.location.setY(100+random.nextInt(900));
	}
	
	public void setColor(int newColor)
	{
		color = newColor;
	}
	public int getColor()
	{
		return this.color; 
	}
	@Override
	public String toString() {
		String thisClassData=" loc = "+this.getX()+", " + this.getY()+ " color = " + "[" + ColorUtil.red(this.getColor()) + "," 
				+ ColorUtil.green(this.getColor()) + "," +  
				 ColorUtil.blue(this.getColor()) + "] size = " +this.getSize();
		return (thisClassData);
	}
}
