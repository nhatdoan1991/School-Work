package com.mycompany.a3;

import com.codename1.ui.Command;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.events.ActionEvent;

public class BreakCommand extends Command{

	private GameWorld gw;
	public BreakCommand(GameWorld gw)
	{
		super("Break");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{	
		gw.setConsoleDisplay("Breaking Cyborg\n");
		gw.cyborgBreak();
		System.out.println("Break");
	}
}
