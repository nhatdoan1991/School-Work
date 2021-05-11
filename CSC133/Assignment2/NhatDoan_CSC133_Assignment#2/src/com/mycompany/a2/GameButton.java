package com.mycompany.a2;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.plaf.Border;
public class GameButton extends Button {

	public GameButton(Command cm)
	{
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBorder(Border.createBevelRaised());
		this.getAllStyles().setBorder(Border.createBevelLowered());
		this.getAllStyles().setBorder(Border.createDoubleBorder(5, ColorUtil.BLACK	));
  		this.getAllStyles().setMargin(TOP, 2);
  		this.getAllStyles().setMargin(BOTTOM, 2);
  		this.getAllStyles().setMargin(LEFT, 1);
  		this.getAllStyles().setMargin(RIGHT, 1);
  		this.getAllStyles().setPadding(TOP, 5);
  		this.getAllStyles().setPadding(BOTTOM, 5);
  		
  		this.setOpaque(true);
  		this.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 50, 255));
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		
		this.getPressedStyle().setBgTransparency(255/2);
		this.getPressedStyle().setBgColor(ColorUtil.rgb(0, 100, 220));
		this.getPressedStyle().setFgColor(ColorUtil.BLUE);
		
		this.getDisabledStyle().setBgTransparency(255);
		this.getDisabledStyle().setBgColor(ColorUtil.GRAY);
		this.getDisabledStyle().setFgColor(ColorUtil.BLUE);
		this.getDisabledStyle().setStrikeThru(true);
	
		this.setCommand(cm);
	}
}
