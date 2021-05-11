package com.mycompany.a2;

import com.codename1.ui.Command;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.events.ActionEvent;

public class accelerateCommand extends Command{

	private GameWorld gw;
	public accelerateCommand(GameWorld gw)
	{
		super("Acceleration");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		System.out.println("Accelerate");
		gw.setConsoleDisplay("Cybrog was just accelerated\n");
		gw.SpeedUp();
	}
}
