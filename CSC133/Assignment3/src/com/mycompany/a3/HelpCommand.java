package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;


public class HelpCommand extends Command{

	private GameWorld gw;
	public HelpCommand(GameWorld gw) {
		super("Help");
		this.gw=gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("Help");
		Form form= new Form();
		Container myContainer = new Container();
		myContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		myContainer.add(new Label("'a' - Acceleration key."));
		myContainer.add(new Label("'b' -  Break key."));
		myContainer.add(new Label("'l' - Turn Left key."));
		myContainer.add(new Label("'r' - Turn Right key."));
		myContainer.add(new Label("'e' - Collide Energy Station key."));
		myContainer.add(new Label("'t' - Tick CLock key."));
		myContainer.add(new Label("'g' - Collide Drone key."));
		gw.setConsoleDisplay("Showing Help\n");
		gw.Help();
		form.add(myContainer);
		Command cCancel = new Command("Cancel");
		Dialog.show("Help", form, cCancel);
	}

}