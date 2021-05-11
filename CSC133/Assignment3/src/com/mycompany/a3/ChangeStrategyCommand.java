package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.events.ActionEvent;


public class ChangeStrategyCommand extends Command{

	private GameWorld gw;

	GameObjectCollection objCollection = new GameObjectCollection(gw);
	public ChangeStrategyCommand(GameWorld gw) {
		super("Change Strategies");
		this.gw =gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Command cStrategyOne = new Command("Attach Player");
		Command cStrategyTwo = new Command("Racing To Last Base");
		Command cCancel =  new Command("Cancel");
		Command[] cmds = new Command[] {cStrategyOne, cStrategyTwo,cCancel};
		Command c = Dialog.show("Choose a Strategy ", "", cmds);
		if(c==cStrategyOne) {
			gw.setConsoleDisplay("NPC changed to Attacking Player\n");
			System.out.println("NPC just changed to Strategy Attacking Player");
			//invoke Strategy One
			gw.changeStrategy('a');
			
		}else if(c==cStrategyTwo) {
			gw.setConsoleDisplay("NPC changed to Race To The Last Base\n");
			System.out.println("NPC just changed to Race To Last Base");
			//invoke Strategy Two
			gw.changeStrategy('r');
		}
	}
	
}
