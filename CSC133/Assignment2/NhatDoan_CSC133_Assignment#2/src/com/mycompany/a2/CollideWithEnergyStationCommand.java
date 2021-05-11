package com.mycompany.a2;
import com.codename1.ui.Command;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.events.ActionEvent;

public class CollideWithEnergyStationCommand extends Command {
	private GameWorld gw;
	public CollideWithEnergyStationCommand(GameWorld gw) {
		super("Collide With Energy Station");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Collided With an Energy Station ");
		gw.collideWithEnergyStation();
	}

}
