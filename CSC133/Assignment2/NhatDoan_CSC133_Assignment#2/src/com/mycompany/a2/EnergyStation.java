package com.mycompany.a2;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class EnergyStation extends Fixed {
	private int energyCapacity;
	Random random = new Random();
	
	public EnergyStation()
	{		
		super.setSize(10+random.nextInt(30));
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
}
