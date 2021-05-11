package com.mycompany.a2;
import com.codename1.ui.Command;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.events.ActionEvent;

public class CollideWithBaseCommand extends Command {

	private GameWorld gw;
	public CollideWithBaseCommand(GameWorld gw)
	{
		super("Collide With Base");
		this.gw=gw;
	}
	@Override 
	public void actionPerformed(ActionEvent e)
	{
		gw.collideWithBase();
	}
}
