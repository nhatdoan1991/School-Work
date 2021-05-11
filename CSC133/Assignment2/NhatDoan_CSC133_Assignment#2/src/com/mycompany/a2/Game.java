package com.mycompany.a2;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Toolbar;
import com.codename1.charts.util.ColorUtil;

public class Game extends Form{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	public Game() {
	gw = new GameWorld(this);
	mv = new MapView();
	sv = new ScoreView();
	
	gw.addObserver(mv);
	gw.addObserver(sv);
	
	gw.setMapWidth(mv.getMapWidth());
	gw.setMapHeight(mv.getMapHeight());
	
	System.out.print("Map Width is: "+ mv.getMapWidth());
	System.out.println(" and Map Height is: "+ mv.getMapHeight());
	
	this.setLayout(new BorderLayout());
	SetUpSideMenu();

	add(BorderLayout.NORTH, sv);
	add(BorderLayout.CENTER, mv);

	westButtons();
	eastButtons();
	southButtons();
	
	gw.init();
	this.show();
	}
	private void westButtons() {
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));//start adding components at a location 50 pixels below the upper border of the container
		leftContainer.getAllStyles().setPadding(Component.TOP, 100);
		Command accelerate =  new accelerateCommand(gw);
		leftContainer.add(new GameButton(accelerate));
		addKeyListener('a', accelerate);
		Command leftTurn = new LeftTurnCommand(gw);
		leftContainer.add(new GameButton(leftTurn));
		addKeyListener('l', leftTurn);
		leftContainer.add(new GameButton(new ChangeStrategyCommand(gw)));
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.GRAY));
		add(BorderLayout.WEST,leftContainer);
	}
	private void eastButtons() {
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));//start adding components at a location 50 pixels below the upper border of the container
		rightContainer.getAllStyles().setPadding(Component.TOP, 100);
		Command breakC =  new BreakCommand(gw);
		rightContainer.add(new GameButton(breakC));
		addKeyListener('b', breakC);
		Command rightTurn = new TurnRightCommand(gw);
		rightContainer.add(new GameButton(rightTurn));
		addKeyListener('r', rightTurn);
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.GRAY));
		add(BorderLayout.EAST,rightContainer);
	}
	private void southButtons() {
		Container bottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));//start adding components at a location 50 pixels below the upper border of the container
		bottomContainer.add(new GameButton(new CollideWithNPCCommand(gw)));
		bottomContainer.add(new GameButton(new CollideWithBaseCommand(gw)));
		Command collideEnergy = new CollideWithEnergyStationCommand(gw);
		bottomContainer.add(new GameButton(collideEnergy));
		addKeyListener('e', collideEnergy);
		Command collideDrone =  new CollideWithDroneCommand(gw);
		bottomContainer.add(new GameButton(collideDrone));
		addKeyListener('g',collideDrone);
		Command tick = new tickCommand(gw);
		bottomContainer.add(new GameButton(tick));
		addKeyListener('t',tick);
		bottomContainer.setHeight(100);
		bottomContainer.getAllStyles().setPadding(Component.LEFT, 340);
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.GRAY));
		add(BorderLayout.SOUTH,bottomContainer);
	}
	private void SetUpSideMenu()
	{		
		Toolbar menu = new Toolbar();
		this.setToolbar(menu);
		menu.setTitle("~~~~ SILI-CHALLENGE GAME ~~~~");
		menu.getAllStyles().getBackgroundGradientRelativeSize();
		menu.getAllStyles().getBgTransparency();

		Command newGame = new accelerateCommand(gw);
		menu.addCommandToSideMenu(newGame);
		
		Command about = new AboutCommand(gw);
		menu.addCommandToSideMenu(about);
		
		CheckBox soundOn = new CheckBox("Sound");
		TurnOnOffSoundCommand sound = new TurnOnOffSoundCommand(gw,soundOn);
		soundOn.setCommand(sound);
		soundOn.getAllStyles().setBgColor(ColorUtil.GRAY);
		menu.addComponentToSideMenu(soundOn);;
		
		Command exit = new exitCommand(gw);
		menu.addCommandToSideMenu(exit);
		
		Command titleBarAreaItem2 = new HelpCommand(gw); 
		menu.addCommandToRightBar(titleBarAreaItem2);

	}
}
