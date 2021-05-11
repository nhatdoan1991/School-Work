package com.mycompany.a1;
import java.util.Random;
import com.codename1.charts.util.ColorUtil;

public class EnergyStation extends Fixed {
	private int energyCapacity;
	Random random = new Random();
	
	public EnergyStation()
	{		
		super.setSize(10+random.nextInt(30));
		super.setColor(ColorUtil.GREEN);
		super.setRandomLocation();
		this.energyCapacity=(super.getSize()*5);// energyCapacity is proportional with size (ratio 1:2)
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
	public void setLocation(float x, float y)
	{}
	public String toString() 
	{
		return super.toString()+(" Capacity = "+this.getenergyCapacity());
	}
}
