package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.mycompany.a3.GameWorld;
import com.codename1.ui.events.ActionEvent;

public class TurnOnOffSoundCommand extends Command{

	private Game g;
	private CheckBox cb;
	public TurnOnOffSoundCommand(Game g, CheckBox cb) {
		super("Sound ON");
		this.g = g;
		this.cb=cb;
		this.cb.getAllStyles().setBgColor(ColorUtil.rgb(161, 172, 173));
		this.cb.getAllStyles().setFgColor(ColorUtil.WHITE);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		g.ChangeSound(cb.isSelected());
	}

}
