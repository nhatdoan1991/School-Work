package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.events.ActionEvent;


public class exitCommand extends Command {

	private GameWorld gw;
	public exitCommand(GameWorld gw2) {
		super("Exit");
		this.gw=gw2;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Command cExit = new Command("Exit");
		Command cCancel =  new Command("Cancel");
		Command[] cmds = new Command[] {cExit,cCancel};
		Command c= Dialog.show("Do you want to exit the game? ","", cmds);
		if(c==cExit) {
			gw.exitTrue();
			gw.exit('y');
		}else {
			gw.exitTrue();
			gw.exitFasle();
		}
		
	}
}
