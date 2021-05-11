package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

	
public class Drone extends Movable{
	
	Random random = new Random();
	public Drone() {
		super.setHeading(random.nextInt(360));
		super.setSpeed(5+random.nextInt(6));
		super.setSize(50+random.nextInt(41));
		super.setColor(ColorUtil.GRAY);
		super.setRandomLocation();
	}
	public void changeRandomHeading() {
		int randomNumber = random.nextInt(2);
		int randomTurn = random.nextInt(5);
		if(randomNumber==0)
		{
			super.setHeading(super.getHeading()-randomTurn);
		}else if(randomNumber==1)
		{
			super.setHeading(super.getHeading()+randomTurn);
		}
		super.checkHeadingBoudaries();
	}
	@Override
	public void setSize(int size) {}
	public void setColor(int color) {}
	public String toString() {
		String thisClassData ="Drone: " +super.toString();
		return thisClassData;
	}
	@Override 
	public void move(double elapsedTime)
	{
		float movX=Math.round((Math.cos(Math.toRadians(-(super.getHeading()-90)))*this.getSpeed()*elapsedTime/100));
		float movY=Math.round((Math.sin(Math.toRadians(-(super.getHeading()-90)))*this.getSpeed()*elapsedTime/100));
		if((super.getLocation().getX()+movX)>1250||super.getLocation().getX()+movX<50||super.getLocation().getY()+movY>950||super.getLocation().getY()+movY<50)
		{
			super.bound();
		}else
		{
			super.setLocation(movX+super.getX(),movY+super.getY());
		}
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xLocation = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLocation = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		
		int[] xPoints = {xLocation,xLocation-this.getSize(),xLocation+this.getSize()};
		int[] yPoints = {yLocation-this.getSize(),yLocation+this.getSize(),yLocation+this.getSize()};
		
		int nPoints = 3;
		
		g.setColor(this.getColor());
		g.drawPolygon(xPoints, yPoints, nPoints);
	}
	@Override
	public void handleCollision(GameObject otherobject) {
	}
}
