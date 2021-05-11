package com.mycompany.a2;
import java.util.Random;
import java.util.Observable;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;

public class GameWorld extends Observable implements IGameWorld {
	Random random = new Random();
	private Game myGame;
	private GameObject myGameOject;
	
	private double mapWidth;
	private double mapHeight;
	
	private int gameTime=0;
	private int liveOfPlayer=3;
	private boolean endGame=false;
	
	private int lastBase;
	private int numberOfDrone;
	private int numberOfEnergyStation;
	private boolean exit1=false;
	private String ConsoleDisplay="";
	private boolean sound=true;
	
	
	//private Vector<GameObject> gameObjects = new Vector<GameObject>();
	
	private GameObjectCollection myObjectCollection = new GameObjectCollection(this);
	
	private void InformObservers() {
		GameWorldProxy gwp = new GameWorldProxy(this);
		this.setChanged();
		this.notifyObservers(gwp);
		
		if (this.endGame)
		{
			if(this.endGameShow())
			{
				System.exit(0);
				
			}
		}
	}
	public boolean endGameShow() {
		String gameOverTxt = "Thank you for playing but the game is over.\nPlease restart the program.";
		return Dialog.show("Game Over!", gameOverTxt, "Ok", null);
	}
	public GameWorld(Game g)
	{
		myGame=g;
	}
	public GameWorld()
	{
	}
	public GameWorld(GameObject gO)
	{
		myGameOject=gO;
	}
	public String getConsoleDisplay() {
		return this.ConsoleDisplay;
	}
	public void setConsoleDisplay(String consoleDisplay)
	{
		this.ConsoleDisplay=consoleDisplay;
	}
	public int getLastBase() {
		return this.lastBase;
	}
	public void setMapWidth(double width) {
		this.mapWidth= width;
	}
	public double getMapWidth() {
		return this.mapWidth;
	}
	public void setMapHeight(double Height) {
		this.mapHeight= Height;
	}
	public double getMapHeight() {
		return this.mapHeight;
	}
	public void endGame()
	{
			wonGame();		
			System.out.println("GameOver!!!!!!");
			this.endGameShow();
			this.exitTrue();
			this.exit('y');				
									
	}
	public boolean getEndGame()
	{
		return this.endGame;
	}
	public void wonGame() {
		if(this.findPlayerCyborg().getLastBaseReached()==this.getLastBase())
		{
			Command cOk= new Command("OK");
			Dialog.show("Congratulation!!!", "You won the game within " +this.getGameTime()+ "seconds.\n Please restart the game!", cOk);
			System.out.println("\nYou Won The Game!");
			System.out.println("Your time is "+this.getGameTime()+" and you have "+this.getLiveOfPlayer()+"lives left :D");
			try
			{
			    Thread.sleep(5000);
			}
			catch(InterruptedException ex)
			{
			    Thread.currentThread().interrupt();
			}
			System.exit(0);
		}
	}
	public void setEndGame(boolean result) {
		this.endGame=result;
	}
	public void setLiveOfPlayer(int numberOfLive)
	{
		liveOfPlayer = numberOfLive;
	}
	@Override 
	public int getLastBaseCyborg()
	{
		return this.findPlayerCyborg().getLastBaseReached();
	}
	@Override
	public int getLiveOfPlayer()
	{
		return this.liveOfPlayer;
	}
	@Override
	public int getGameTime() {
		return this.gameTime;
	}
	public void setNumberOfEnergyStation(int number) {
		this.numberOfEnergyStation= number;
	}
	public int getNumberOfEnergyStation() {
		return this.numberOfEnergyStation;
	}
	@Override
	public boolean getSound()
	{
		return this.sound;
		
	}
	public void ChangeSound() {
		sound=!sound;
		this.InformObservers();
	}
	
	@Override
	public GameObjectCollection getCollection() {
		return myObjectCollection;
	}
	public void init()
	{
		endGame=false;
		char d='d',b='b',c='c',e='e',n='n';
		int numberOfBase=0;
		createGameObject(c);
	
		numberOfDrone=2+random.nextInt(4);
		numberOfEnergyStation=2+random.nextInt(4);
		lastBase=4+random.nextInt(6);
		for(int i=1; i<= lastBase; i++)
		{
			createGameObject(b);
			
		}
		for(int i=1; i<= 3; i++)
		{
			createGameObject(n);
		}
		System.out.println("New Map was just created \n There are 3 NPC, "+  this.lastBase+" bases, "+numberOfDrone+" drones, "+numberOfEnergyStation+" energy stations was created!");
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if (gO instanceof Base) {
				Base base = (Base) gO;
				base.setSequenceNumber(numberOfBase+1);
				if(numberOfBase+1==1)
				{
					findPlayerCyborg().setLocation(findBase(1).getLocation());
				}
				numberOfBase++;
			}
			if(gO instanceof NonPlayerCyborg)
			{
				NonPlayerCyborg npc =  (NonPlayerCyborg) gO;
				float x,y;
				x= this.findBase(1).getX()+random.nextInt(100)-50;
				y= this.findBase(1).getY()+random.nextInt(100)-50;
				if(x<0)
				{
					x=0;
				}else if(x>1000)
				{
					x=1000;
				}
				if(y<0)
				{
					y=0;
				}else if(y>1000)
				{
					y=1000;
				}
				npc.setLocation(x,y);
				if(random.nextInt(2)==0)
				{
					npc.setStrategy(new AttackPlayerCyborgStrategy(npc,this));
				}else
				{
					npc.setStrategy(new ReachToLastBaseStrategy(npc,this));
				}
				npc.invokeStratergy();
			}
		}
		for(int i=1; i<= numberOfDrone; i++)
		{
			createGameObject(d);
		}
		for(int i=1; i<= numberOfEnergyStation; i++)
		{
			createGameObject(e);
		}
		InformObservers();
	}
	public PlayerCyborg findPlayerCyborg() {
		IIterator theObjects = myObjectCollection.getIterator();
		
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if(gO instanceof PlayerCyborg)
			{
				return (PlayerCyborg) gO;
			}
		}
		return null;
	}
	public NonPlayerCyborg findNonPlayerCyborg() {
		IIterator theObjects = myObjectCollection.getIterator();
		
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if(gO instanceof NonPlayerCyborg)
			{
				return (NonPlayerCyborg) gO;
			}
		}
		return null;
	}
	public Base findBase(int baseNumber) {
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if(gO instanceof Base && ((Base) gO).getSequenceNumber()== baseNumber)
			{
				return (Base) gO;
			}
		}
		return null;
	}
	public EnergyStation findEnergyStation() {
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if(gO instanceof EnergyStation)
			{
				return (EnergyStation) gO;
			}
		}
		return null;
	}
	public Drone findDrone() {
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if(gO instanceof Drone)
			{
				return (Drone) gO;
			}
		}
		return null;
	}
	public void createGameObject(char nameObject) {
		switch(nameObject) {
		case 'c':
			PlayerCyborg c=PlayerCyborg.getPlayerCyborg();
			myObjectCollection.add(c);
			break;
		case 'd':
			Drone d = new Drone();
			myObjectCollection.add(d);
			break;
		case 'b':
			Base b = new Base();
			myObjectCollection.add(b);
			break;
		case 'e':
			EnergyStation e = new EnergyStation();
			myObjectCollection.add(e);
			break;
		case 'n':
			NonPlayerCyborg n = new NonPlayerCyborg();
			myObjectCollection.add(n);
			break;
		}
	}
	public void clickTick()
	{
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if(gO instanceof Movable)
			{
				Movable mov = (Movable) gO;
				if(mov instanceof PlayerCyborg)
				{
					PlayerCyborg c = (PlayerCyborg) mov;
					c.setSpeedWithDamage(c.getDamageLevel());
					if(this.findPlayerCyborg().isOutOfBattery()||this.findPlayerCyborg().isBroken())
					{
						this.cyborgReset();
					}else
					{
					c.move();
					c.energyLostAfterTick();
					c.setHeading(c.getHeading()+c.getSteeringDirection());
					c.checkHeadingBoudaries();
					System.out.println("My new location is at ("+c.getX()+","+c.getY()+")");
					System.out.println("My new heading is "+c.getHeading());
					}
				}
				if(mov instanceof Drone)
				{
					Drone d = (Drone) mov;
					d.move();
					d.changeRandomHeading();
				}
				if(mov instanceof NonPlayerCyborg)
				{
					NonPlayerCyborg n = (NonPlayerCyborg) mov;
					n.setSpeedWithDamage(n.getDamageLevel());
					n.move();
					n.setHeading(n.getHeading()+n.getSteeringDirection());
					n.checkHeadingBoudaries();
					n.invokeStratergy();
				}
			}
		}
		this.setConsoleDisplay("Time has clicked\n");
		gameTime++;
		InformObservers();
	}
	public void cyborgReset() {
		System.out.println("Your Cyborg is destroyed. You lost one live!");
		this.setLiveOfPlayer(this.getLiveOfPlayer()-1);
		System.out.println("Your number of lives is: "+this.getLiveOfPlayer());
		if(this.getLiveOfPlayer()==0)
		{
			this.setEndGame(true);
			this.endGame();
		}
		this.findPlayerCyborg().setDamageLevel(0);
		this.findPlayerCyborg().setEnergyLevel(100);
		this.findPlayerCyborg().setColor(ColorUtil.BLUE);
		this.findPlayerCyborg().setHeading(0);
		this.findPlayerCyborg().setSpeed(0);
		this.findPlayerCyborg().setSteeringDirection(0);
		this.findPlayerCyborg().setLastBaseReached(1);
		this.clearObjects();
		this.init();
	}
	public void displayCyborg()
	{
				System.out.println("\nYou have "+this.getLiveOfPlayer()+" lives left\n"
						+"Your clocktime is at "+ this.getGameTime() + " ticks");
				System.out.println("\nYour Cybrog: ");
				System.out.println("++last base reached is "+this.findPlayerCyborg().getLastBaseReached());
				System.out.println("++energy level is "+ this.findPlayerCyborg().getEnergyLevel());
				System.out.println("++damage level is " +this.findPlayerCyborg().getDamageLevel());		
	}
	
	public void displayMap()
	{
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if (gO instanceof Cyborg)
			{
				Cyborg c = (Cyborg) gO;
				System.out.println(c.toString());
			}
			if (gO instanceof Cyborg)
			{
				Cyborg c = (Cyborg) gO;
				System.out.println(c.toString());
			}
			if (gO instanceof Drone)
			{
				Drone d = (Drone) gO;
				System.out.println(d);
			}
			if (gO instanceof Base )
			{
				Base b = (Base) gO;
				System.out.println(b);
			}
			if (gO instanceof EnergyStation)
			{
				EnergyStation e = (EnergyStation) gO;
				System.out.println(e);
			}
		}
	}

	public void cyborgBreak() {
		int lastSpeedBreak=this.findPlayerCyborg().getSpeedWithDamage();
		this.findPlayerCyborg().slowDown();
		if(this.findPlayerCyborg().getSpeedWithDamage()!=lastSpeedBreak)
		{
			System.out.println("Your Cyborg's speed is "+ this.findPlayerCyborg().getSpeedWithDamage() + " units per tick");
		}
		this.InformObservers();
	}
	public void SpeedUp() {
		this.findPlayerCyborg().speedUp();
		this.InformObservers();
	}
	public void collideWithCyborg()
	{	
		this.setConsoleDisplay("Cyborg just collided with a NPC\n ");
		this.InformObservers();
	}
	public void collideWithBase() {
		Command cOk = new Command("Enter");
		Command cCancel =  new Command("Cancel");
		Command[] cmds = new Command[] {cOk,cCancel};
		TextField myTF = new TextField();
		Command c= Dialog.show("Enter the base number: ", myTF, cmds);
		String sCommand=myTF.getText().toString();
		
		if(c==cOk)
		{
			if(sCommand.length() != 0) {
				switch (sCommand.charAt(0)) {
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					System.out.println("Collided With Base " + sCommand.charAt(0));
					int numberHold= sCommand.charAt(0);
					numberHold=numberHold-48;
					findPlayerCyborg().baseReach(numberHold);
					wonGame();
					this.setConsoleDisplay("Cyborg just reached to base "+ numberHold+"\n");
					break;
				default:
					System.out.println("\nYour Input is invalid.Please enter valid command!!!\n");
					Command Cancel =  new Command("Cancel");
					Dialog.show("Error","You must enter a number from 1-9!!",Cancel);
					break;
				}	
			}else {
				Command Cancel =  new Command("Cancel");
				Dialog.show("Error","You must enter a number from 1-9!!",Cancel);
			}
		}
		this.InformObservers();
	}
	public void collideWithEnergyStation() {
		IIterator theObjects = myObjectCollection.getIterator();
		int random1= random.nextInt(numberOfEnergyStation);
		System.out.println("Energy of station number "+(random1+1)+" is "+((EnergyStation) theObjects.atElement(random1+lastBase+numberOfDrone+4)).getenergyCapacity()+", and energy of Cyborg is "+this.findPlayerCyborg().getEnergyLevel());
		int color=((EnergyStation) theObjects.atElement(random1+lastBase+numberOfDrone+4)).getenergyCapacity();
		int engeryRemained=this.findPlayerCyborg().reachEnergyStation(((EnergyStation) theObjects.atElement(random1+lastBase+numberOfDrone+4)).getenergyCapacity());
		System.out.println(engeryRemained);
		if(engeryRemained<0)
		{
			engeryRemained=0;
		}
		((EnergyStation) theObjects.atElement(random1+lastBase+numberOfDrone+4)).setEnergyCapacity(engeryRemained); 
		System.out.println("Energy of Cyborg is recharged to " + this.findPlayerCyborg().getEnergyLevel());
		System.out.println("Energy that remained in the station is: "+((EnergyStation) theObjects.atElement(random1+lastBase+numberOfDrone+4)).getenergyCapacity());
		((EnergyStation) theObjects.atElement(random1+lastBase+numberOfDrone+4)).setColor(ColorUtil.rgb(144*(100-100*((EnergyStation) theObjects.atElement(random1+lastBase+numberOfDrone+4)).getenergyCapacity())/((color+1)*100),255, 144*(100-100*((EnergyStation) theObjects.atElement(random1+lastBase+numberOfDrone+4)).getenergyCapacity())/((color+1)*100)));//light green
		System.out.println("Color of this station is: " + "[" + ColorUtil.red(((EnergyStation) theObjects.atElement(random1+lastBase+numberOfDrone+4)).getColor()) + "," +
														ColorUtil.green(((EnergyStation) theObjects.atElement(random1+lastBase+numberOfDrone+4)).getColor()) + "," + 							
														+ ColorUtil.blue(((EnergyStation) theObjects.atElement(random1+lastBase+numberOfDrone+4)).getColor()) + "]");
		this.createGameObject('e');
		System.out.println("\nA new energy station was just created!!!!");
		numberOfEnergyStation++;
		this.setConsoleDisplay("Cyborg just collided an Energy Station\n" );
		InformObservers();
	}

	public void clearObjects()
	{
		myObjectCollection.clear();
	}
	public void exitTrue()
	{
		this.exit1=true;
	}
	public void exitFasle()
	{
		this.exit1=false;
	}

	public void exit(char x) {

		if( this.exit1==true) {
		if(x=='y')
		{
			System.exit(0);
		}else if (x== 'n')
		{
			
			System.out.println("\nThe Game continues");
			this.exit1=false;
		}
		}
	}
	@Override
	public int getPlayerEnergyLevel() {
	
		return this.findPlayerCyborg().getEnergyLevel();
	}
	@Override
	public int getPlayerDamageLevel() {
		
		return this.findPlayerCyborg().getDamageLevel();
	}
	public void collideWithDrone() {
		this.findPlayerCyborg().collideWithDrone();
		this.setConsoleDisplay("Cyborg just collided a Drone\n" );
		this.InformObservers();;
	}
	public void about() {
		this.InformObservers();		
	}
	public void Help() {
		this.InformObservers();	
	}
	public void turnLeft() {
		this.findPlayerCyborg().changeHeading('l');
		this.setConsoleDisplay("Cyborg turned left\n");
		this.InformObservers();
	}
	public void rightTurn() {
		this.findPlayerCyborg().changeHeading('r');
		this.setConsoleDisplay("Cyborg turned right\n");
		this.InformObservers();
	}

	public void changeStrategy(char a) {
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if(gO instanceof NonPlayerCyborg)
			{
				if(a=='a')
				{
					((NonPlayerCyborg) gO).setStrategy(new AttackPlayerCyborgStrategy((NonPlayerCyborg) gO,this));
				}
				else if(a=='r')
				{
					((NonPlayerCyborg) gO).setStrategy(new ReachToLastBaseStrategy((NonPlayerCyborg) gO,this));
				}
			}
		
		}
		this.InformObservers();
	}
	
}
