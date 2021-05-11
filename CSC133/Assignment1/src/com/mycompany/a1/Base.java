package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Base extends Fixed {
	
	private int sequenceNumber;

	public Base()
	{		
		super.setSize(25);
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
	public void setLocation(double x, double y)
	{}
	public String toString() {
		return super.toString()+(" SequenceNunmber = "+this.getSequenceNumber());
	}
	
}
