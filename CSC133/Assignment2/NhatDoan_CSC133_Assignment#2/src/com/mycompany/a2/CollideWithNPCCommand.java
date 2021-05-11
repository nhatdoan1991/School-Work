package com.mycompany.a2;

import com.codename1.ui.Command;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.events.ActionEvent;

public class CollideWithNPCCommand extends Command{

	private GameWorld gw;
	public CollideWithNPCCommand(GameWorld gw)
	{
		super("Collide With NPC");
		this.gw=gw;
	}
	@Override 
	public void actionPerformed(ActionEvent e)
	{
	
		gw.findNonPlayerCyborg().collideWithPlayerCyborg();
		gw.findPlayerCyborg().collideWithCyborg();
		if(gw.findPlayerCyborg().isBroken())
		{
			gw.cyborgReset();
		}
		gw.collideWithCyborg();
		System.out.println("Collided With NPC");
	}
}
