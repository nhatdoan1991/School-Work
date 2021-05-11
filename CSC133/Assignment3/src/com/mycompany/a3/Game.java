package com.mycompany.a3;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Toolbar;
import com.codename1.charts.util.ColorUtil;

public class Game extends Form implements Runnable{
	public final double TIME_PER_CLICK = 20;
	
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	private int timeElapsed = 0;
	private UITimer timer;
	
	private BGSound backgroundSound;
	//private Sound sound;
	boolean pause = false;
	
	private AboutCommand aboutCmd;
	private GameButton aboutBt;
	private accelerateCommand accelerateCmd;
	private GameButton accelerateBt;
	private BreakCommand breakCmd;
	private GameButton breakBt;
	private ChangeStrategyCommand changeSCmd;
	private GameButton changeSBt;
	private HelpCommand helpCmd;
	private GameButton helpBt;
	private LeftTurnCommand leftCmd;
	private GameButton leftBt;
	private TurnRightCommand rightCmd;
	private GameButton rightBt;
	private PauseCommand pauseCmd;
	private GameButton pauseBt;
	private exitCommand exitCmd;
	private GameButton exitBt;
	private PositionCommand positionCmd;
	private GameButton positionBt;
	
	private GameButton pauseGame;
	
	public Game() {
		backgroundSound = new BGSound("background.wav");
		
		this.setLayout(new BorderLayout());
		this.setScrollable(false);
		
		gw = new GameWorld(this);
		mv = new MapView();
		sv = new ScoreView();
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		
		this.setLayout(new BorderLayout());
		SetUpSideMenu();

		add(BorderLayout.NORTH, sv);
		add(BorderLayout.CENTER, mv);

		westButtons();
		eastButtons();
		southButtons();
		gw.setMapWidth(mv.getWidth());
		gw.setMapHeight(mv.getHeight());
		System.out.print("Map Width is: "+ mv.getMapWidth());
		System.out.println(" and Map Height is: "+ mv.getMapHeight());
		gw.init();
		this.show();
		
		backgroundSound.play();	
		timer = new UITimer(this);
		timer.schedule((int)TIME_PER_CLICK, true, this);
		
		
			
	}
	private void westButtons() {
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));//start adding components at a location 50 pixels below the upper border of the container
		leftContainer.getAllStyles().setPadding(Component.TOP, 100);
		
		accelerateCmd =  new accelerateCommand(gw);
		accelerateBt =new GameButton(accelerateCmd);
		leftContainer.add(accelerateBt);
		addKeyListener('a', accelerateCmd);
		
		leftCmd = new LeftTurnCommand(gw);
		leftBt= new GameButton(leftCmd);
		leftContainer.add(leftBt);
		addKeyListener('l', leftCmd);
		
		changeSCmd = new ChangeStrategyCommand(gw);
		changeSBt = new GameButton(changeSCmd);
		leftContainer.add(changeSBt);
		
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.GRAY));
		add(BorderLayout.WEST,leftContainer);
	}
	private void eastButtons() {
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));//start adding components at a location 50 pixels below the upper border of the container
		rightContainer.getAllStyles().setPadding(Component.TOP, 100);
		
		breakCmd =  new BreakCommand(gw);
		breakBt = new GameButton(breakCmd);
		rightContainer.add(breakBt);
		addKeyListener('b', breakCmd);
		
		rightCmd = new TurnRightCommand(gw);
		rightBt = new GameButton(rightCmd);
		rightContainer.add(rightBt);
		addKeyListener('r', rightCmd);
		
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(1,ColorUtil.GRAY));
		add(BorderLayout.EAST,rightContainer);
	}
	private void southButtons() {
		Container bottomContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));//start adding components at a location 50 pixels below the upper border of the container
		
		positionCmd = new PositionCommand(gw);
		positionBt = new GameButton(positionCmd);
		bottomContainer.add(positionBt);
		
		pauseCmd = new PauseCommand(this);
		pauseBt= new GameButton(pauseCmd);
		bottomContainer.add((pauseBt));

		bottomContainer.setHeight(100);
		bottomContainer.getAllStyles().setPadding(Component.LEFT, 850);
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

		accelerateCmd = new accelerateCommand(gw);
		menu.addCommandToSideMenu(accelerateCmd);
		
		aboutCmd = new AboutCommand(gw);
		menu.addCommandToSideMenu(aboutCmd);
		
		CheckBox soundOn = new CheckBox("Sound");
		TurnOnOffSoundCommand sound = new TurnOnOffSoundCommand(this,soundOn);
		soundOn.setCommand(sound);
		soundOn.getAllStyles().setBgColor(ColorUtil.GRAY);
		menu.addComponentToSideMenu(soundOn);;
		
		exitCmd = new exitCommand(gw);
		menu.addCommandToSideMenu(exitCmd);
		
		helpCmd = new HelpCommand(gw); 
		menu.addCommandToRightBar(helpCmd);

	}
	@Override
	public void run() {
		gw.clickTick(TIME_PER_CLICK);
		timeElapsed++;
	}
	
	public void ChangeSound(boolean sound)
	{
		if(!sound)
		{
			backgroundSound.play();
		}else
		{
			backgroundSound.pause();
		}
		gw.ChangeSound();
	}
	public void gamePausePlay()
	{
		this.pause = !pause;
		gw.setPause(!(gw.getPause()));
		
		if(pause)
		{		
			accelerateBt.setEnabled(false);
			breakBt.setEnabled(false);
			changeSBt.setEnabled(false);
			leftBt.setEnabled(false);
			
			rightBt.setEnabled(false);
			timer.cancel();
			pauseBt.setText("Resume");
			removeKeyListener('a', accelerateCmd);
			removeKeyListener('b', breakCmd);
			removeKeyListener('r', rightCmd);
			removeKeyListener('l', leftCmd);
			
		}else
		{
			timer.schedule((int) TIME_PER_CLICK, true, this);
			pauseBt.setText("Pause");
			
			accelerateBt.setEnabled(true);
			breakBt.setEnabled(true);
			changeSBt.setEnabled(true);
			leftBt.setEnabled(true);
			rightBt.setEnabled(true);
			positionBt.setEnabled(true);
			addKeyListener('a', accelerateCmd);
			addKeyListener('b', breakCmd);
			addKeyListener('r', rightCmd);
			addKeyListener('l', leftCmd);
		}
	}
}
