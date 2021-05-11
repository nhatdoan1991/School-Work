package com.mycompany.a2;
import com.codename1.ui.Command;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.events.ActionEvent;
public class tickCommand extends Command {
	private GameWorld gw;
	public tickCommand(GameWorld g) {
		super("Tick");
		this.gw=g;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("The clock has ticked");
		gw.clickTick();
	}

}
