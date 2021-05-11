package com.mycompany.a3;


import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;

public class Base extends Fixed {
	
	private int sequenceNumber;
	private final int BASE_SIZE=80;
	public Base()
	{		
		super.setSize(BASE_SIZE);
		super.setColor(ColorUtil.GRAY);
		super.setRandomLocation();
	
	}

	public int getSequenceNumber() {
		return this.sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber=sequenceNumber;
		
	}
	@Override
	public void setSize(int size) {}
	public void setColor(int color) {}
	public String toString() {
		String thisclassData;
		thisclassData="Base  "+ super.toString()+(" SequenceNunmber = "+this.getSequenceNumber());
		return thisclassData;
	}
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		int xLocation = (int) (this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLocation = (int) (this.getLocation().getY() + pCmpRelPrnt.getY());
		
		int[] xPoints = {xLocation,xLocation-60,xLocation+60};
		int[] yPoints = {yLocation-80,yLocation+40,yLocation+40};
		
		int nPoints = 3;
		
		g.setColor(this.getColor());
		g.drawPolygon(xPoints, yPoints, nPoints);
		g.fillPolygon(xPoints, yPoints, nPoints);
		String toWrite = Integer.toString(this.getSequenceNumber());
		g.setColor(ColorUtil.WHITE);
		Font f= g.getFont();
		int width = f.stringWidth(toWrite);
		int height = f.getHeight();
		g.drawString(toWrite, xLocation-width/2, yLocation-height/2);
	}
	
}
