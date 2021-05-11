package com.mycompany.a1;

public class Point {
	private float x, y ;
	public Point(){
		x = (float) 0.0 ;
		y = (float)0.0 ;
	}
	public float getX() 
	{
		return x ;
	}
	public float getY() 
	{
		return y ;
	}
	public void setLocationXY(float x, float y)
	{
		this.x=x;
		this.y=y;
	}
	
	public void setX(float newX ) 
	{
		this.x = newX;
	}
	public void setY(float newY )
	{
		this.y = newY;
	}
}
