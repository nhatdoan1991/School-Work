package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class PauseCommand extends Command{

	private Game g;
	public PauseCommand(Game g) {
		super("Pause");
		this.g=g;
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		g.gamePausePlay();
	}
}
