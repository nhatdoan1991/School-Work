package com.mycompany.a2;

import com.codename1.ui.Command;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.events.ActionEvent;

public class LeftTurnCommand extends Command{

	private GameWorld gw;
	public LeftTurnCommand(GameWorld gw)
	{
		super("Left Turn");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.turnLeft();
		System.out.println("Steering Left");
	}
}
