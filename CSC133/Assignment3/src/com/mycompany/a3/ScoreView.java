package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

public class ScoreView extends Container implements Observer {

	private String sound = "OFF";
	Label timeLabel=new Label("Time: 0  ");
	Label livesLabel= new Label("Lives left: 3 ");
	Label lastBaseLabel= new Label("Player Last Base Reached: 1 ");
	Label EnergyLabel= new Label("Player Energy Level: 100 ");
	Label DamageLabel= new Label("Player Damage Level: 0 ");
	Label soundLabel= new Label("Sound:"+ sound);
	public ScoreView() {
		
		this.getAllStyles().setBorder(Border.createEmpty());
		this.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		timeLabel.getAllStyles().setAlignment(CENTER);
		this.getAllStyles().setPadding(LEFT, 120);
		timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		lastBaseLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		EnergyLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		DamageLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		timeLabel.getAllStyles().setPaddingRight(2);
		livesLabel.getAllStyles().setPaddingRight(2);
		lastBaseLabel.getAllStyles().setPaddingRight(5);
		EnergyLabel.getAllStyles().setPaddingRight(2);
		soundLabel.getAllStyles().setPaddingRight(2);
		DamageLabel.getAllStyles().setPaddingRight(2);
		this.add(timeLabel);
		this.add(livesLabel);
		this.add(lastBaseLabel);
		this.add(EnergyLabel);
		this.add(DamageLabel);
		this.add(soundLabel);	
	}

	public void update(Observable o, Object arg)
	{
		// code here to update labels from the game/player-cyborg state data
		IGameWorld realWorld = (IGameWorld) arg;
		timeLabel.setText("Time: " + realWorld.getGameTime()+"     ");
		livesLabel.setText("Lives left: "+realWorld.getLiveOfPlayer()+" ");
		lastBaseLabel.setText("Player Last Base Reached: "+realWorld.getLastBaseCyborg()+" ");
		if(realWorld.getPlayerEnergyLevel()<0)
		{
			EnergyLabel.setText("Player Energy Level:   0 ");
		}
		else
		{
			EnergyLabel.setText("Player Energy Level: "+realWorld.getPlayerEnergyLevel()+" ");

		}
		DamageLabel.setText("Player Damage Level: "+realWorld.getPlayerDamageLevel()+" ");
		if(realWorld.getSound())
		{
			soundLabel.setText("Sound: ON ");
		}else
		{
			soundLabel.setText("Sound: OFF ");
		}
		
		this.repaint();
	}
}
