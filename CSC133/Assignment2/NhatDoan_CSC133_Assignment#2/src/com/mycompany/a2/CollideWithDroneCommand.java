package com.mycompany.a2;
import com.codename1.ui.Command;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.events.ActionEvent;

public class CollideWithDroneCommand extends Command {
	private GameWorld gw;
	public CollideWithDroneCommand(GameWorld gw2) {
		super("Collide With Drone");
		this.gw=gw2;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Collided With an Drone");
		gw.collideWithDrone();
		if(gw.findPlayerCyborg().isBroken())
		{
			gw.cyborgReset();
		}
	
	}

}