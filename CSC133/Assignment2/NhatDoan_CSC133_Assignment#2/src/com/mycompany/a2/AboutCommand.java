package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.events.ActionEvent;


public class AboutCommand extends Command{

	private GameWorld gw;
	public AboutCommand(GameWorld gw) {
		super("About");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		Dialog.show("About","Assignemnt #2 ~ Sili- Challenge Game \nBy Nhat Doan - 217523684 - CSC133- Sac State Univerity","OK","Cancle");
		System.out.println("Showing About");
		gw.about();
		gw.setConsoleDisplay("Showing About\n");
	}
}
