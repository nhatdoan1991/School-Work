package com.mycompany.a2;

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
	public String toString() {
		String thisclassData;
		thisclassData="Base  "+ super.toString()+(" SequenceNunmber = "+this.getSequenceNumber());
		return thisclassData;
	}
	
}
