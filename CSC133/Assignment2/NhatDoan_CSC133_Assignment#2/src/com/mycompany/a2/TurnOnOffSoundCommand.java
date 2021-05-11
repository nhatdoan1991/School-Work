package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.mycompany.a2.GameWorld;
import com.codename1.ui.events.ActionEvent;

public class TurnOnOffSoundCommand extends Command{

	private GameWorld gw;
	private CheckBox cb;
	public TurnOnOffSoundCommand(GameWorld gw, CheckBox cb) {
		super("Sound ON");
		this.gw=gw;
		this.cb=cb;
		this.cb.getAllStyles().setBgColor(ColorUtil.rgb(161, 172, 173));
		this.cb.getAllStyles().setFgColor(ColorUtil.WHITE);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		gw.ChangeSound();
	}

}
