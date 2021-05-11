package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;

public class ReachToLastBaseStrategy implements IStrategy{
	
	private NonPlayerCyborg npc;
	private GameWorld gw;

	public ReachToLastBaseStrategy(NonPlayerCyborg npc,GameWorld gw) {
		this.npc=npc;
		this.gw=gw;
	}
	
	public void apply() {
		int lastBase=npc.getLastBaseReached();
		if(lastBase!= gw.getLastBase())
		{
		Point BaseLocation= gw.findBase(lastBase+1).getLocation();
		float x=npc.getX()-BaseLocation.getX();
		float y=npc.getY()-BaseLocation.getY();
		int angle = 90-(int)Math.toDegrees( MathUtil.atan2(Math.abs(y), Math.abs(x)));
		if(x>0)
		{
			if(y>0) {
				angle=180+angle;
			}else if(y<0){
				angle=360-angle;
			}else {
				angle = 270;
			}
		}else if(x<0){
			if(y>0) {
				angle=180-angle;
			}else if(y<0){
				
			}else {
				angle = 270;
			}
		}else {
			if(y>0) {
				angle=180;
			}else if(y<0){
				angle=0;
			}else {
			// right at base x=0, y= 0;
				//npc.setLastBaseReached(lastBase++);
			}
		}
		// change heading of NPC
		if(npc.getHeading()>angle)
		{
			if(npc.getHeading()-angle>=180)
			{
				if(npc.getHeading()-angle>40) {
					npc.setSteeringDirection(40);
					npc.setSpeed(0);
				}
				else
				{
					npc.setSteeringDirection(40-(npc.getHeading()-angle));
					npc.setSpeed(0);
				}
			}else {
				if(npc.getHeading()-angle>40) {
					npc.setSteeringDirection(-40);
					npc.setSpeed(0);
				}
				else
				{
					npc.setSteeringDirection(-(npc.getHeading()-angle));
					npc.setSpeed(0);
				}
			}
		}else if (npc.getHeading()<angle)
		{
			if(angle-npc.getHeading()>=180)
			{
				if(360-angle+npc.getHeading()>40) {
					npc.setSteeringDirection(-40);
					npc.setSpeed(0);
				}
				else
				{
					npc.setSteeringDirection(-(360-angle+npc.getHeading()));
					npc.setSpeed(0);
				}
			}else {
				if(360-angle+npc.getHeading()>40) {
					npc.setSteeringDirection(40);
					npc.setSpeed(0);
				}
				else
				{
					npc.setSteeringDirection(40-(360-angle+npc.getHeading()));
					npc.setSpeed(0);
				}
			}
		}else {
			npc.setSteeringDirection(0);
			npc.setSpeed(npc.getMaximumSpeed());
		}
		}
	}
}
