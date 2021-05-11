package com.mycompany.a1;
import java.util.Random;

import com.codename1.charts.util.ColorUtil;

	
public class Drone extends Movable{
	
	Random random = new Random();
	
	public Drone() {
		super.setHeading(random.nextInt(360));
		super.setSpeed(5+random.nextInt(6));
		super.setSize(10+random.nextInt(41));
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
		return super.toString();
	}
}
