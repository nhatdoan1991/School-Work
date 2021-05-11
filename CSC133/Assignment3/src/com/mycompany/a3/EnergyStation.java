package com.mycompany.a3;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;

public class EnergyStation extends Fixed {
	private int energyCapacity;
	Random random = new Random();	
	GameWorld gw;
	public EnergyStation(GameWorld gw)
	{
		this.gw=gw;
		super.setSize(90+random.nextInt(30));
		super.setColor(ColorUtil.rgb(144, 255, 144));
		super.setRandomLocation();
		this.energyCapacity=(super.getSize()*3);// energyCapacity is proportional with size (ratio 1:2)
	}

	public void setEnergyCapacity(int energy)
	{
		this.energyCapacity=energy;
	}
	public int getenergyCapacity() {
		return this.energyCapacity;
	}
	@Override
	public void setSize(int size) {}
	public String toString() 
	{
		String thisClassData = "EnergyStation "+super.toString()+(" Capacity = "+this.getenergyCapacity());
		return thisClassData;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xLocation = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLocation = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		
		g.setColor(this.getColor());
		g.fillArc(xLocation, yLocation,this.getSize(),this.getSize(), 0, 360);
		
		g.setColor(ColorUtil.BLACK);
		String toWrite = Integer.toString(this.getenergyCapacity());
		Font f = g.getFont();
		int width = f.stringWidth(toWrite);
		int height = f.getHeight();
		g.drawString(toWrite, xLocation+this.getSize()-width, yLocation+this.getSize()-height*2);
	}


}
