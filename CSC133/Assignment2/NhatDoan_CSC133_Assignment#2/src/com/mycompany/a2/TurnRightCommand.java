package com.mycompany.a2;

import com.codename1.ui.Command;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.events.ActionEvent;

public class TurnRightCommand extends Command{

	private GameWorld gw;
	public TurnRightCommand(GameWorld gw)
	{
		super("Right Turn");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.rightTurn();
		System.out.println("Steering Right");
	}
}