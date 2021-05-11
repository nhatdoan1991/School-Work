package com.mycompany.a3;
import java.util.Random;
import java.util.Observable;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;




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

	boolean pause = false;
	boolean positionning = false;
	private Sound collisionSound, lostLifeSound, chargeEnergySound;
	
	
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
		}else 
		{
			IIterator theObjects = myObjectCollection.getIterator();
			while(theObjects.hasNext())
			{
				GameObject gO= theObjects.getNext();
				if(gO instanceof NonPlayerCyborg)
				{
					if(((NonPlayerCyborg) gO).getLastBaseReached()== this.getLastBase())
					{
						Command cOk= new Command("OK");
						Dialog.show("Sorry!!!", "a NPC won this game within " +this.getGameTime()+ "seconds.\n Please restart the game!", cOk);
						if(this.getLiveOfPlayer()==0)
						{
							System.out.println("GameOver!!!!!!");
							this.endGameShow();
							this.exitTrue();
							this.exit('y');	
						}else
						{
							this.cyborgReset();
						}
					}
				}
			}
			
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
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if(gO instanceof PlayerCyborg)
			{
				return ((PlayerCyborg) gO).getLastBaseReached();
			}
		}
		return 1;
	}
	@Override
	public int getPlayerEnergyLevel() {
	
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if(gO instanceof PlayerCyborg)
			{
				return ((PlayerCyborg) gO).getEnergyLevel();
			}
		}
		return 1;
	}
	@Override
	public int getPlayerDamageLevel() {
		
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if(gO instanceof PlayerCyborg)
			{
				return ((PlayerCyborg) gO).getDamageLevel();
			}
		}
		return 1;
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
	public void setSound(boolean sound)
	{
		this.sound=sound;
	}
	public void ChangeSound() {
		this.sound=!sound;
		this.InformObservers();
	}
	
	@Override
	public GameObjectCollection getCollection() {
		return myObjectCollection;
	}
	public void init()
	{
		
		collisionSound = new Sound("Crash.wav");
		//lostLifeSound = new Sound("explode.wav");
		chargeEnergySound = new Sound("charging.wav");
		
	
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
		for(int i=1; i<= numberOfDrone; i++)
		{
			createGameObject(d);
		}
		for(int i=1; i<= numberOfEnergyStation; i++)
		{
			createGameObject(e);
		}
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject gO= theObjects.getNext();
			if (gO instanceof Base) {
				Base base = (Base) gO;
				base.setSequenceNumber(numberOfBase+1);
				numberOfBase++;
			}
			if(gO instanceof NonPlayerCyborg)
			{
				NonPlayerCyborg npc =  (NonPlayerCyborg) gO;
				float x=0,y=0;
				if(random.nextInt(2)==1)
				{
					x= this.findBase(1).getX()+random.nextInt(100)+100;
				}
				else 
				{
					x= this.findBase(1).getX()+random.nextInt(100)-200;
				}
				if(random.nextInt(2)==1)
				{
					y= this.findBase(1).getY()+random.nextInt(100)+100;
				}
				else 
				{
					y= this.findBase(1).getY()+random.nextInt(100)-200;
				}
				
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
	
		findPlayerCyborg().setLocation(findBase(1).getX(),findBase(1).getY());
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
		//Point point = randomLocation();
		switch(nameObject) {
		case 'c':
			PlayerCyborg c=PlayerCyborg.getPlayerCyborg();
			myObjectCollection.add(c);
			break;
		case 'd':
			Drone d = new Drone();
			//d.setLocation(point.getX(), point.getY());
			myObjectCollection.add(d);
			break;
		case 'b':
			Base b = new Base();
			//b.setLocation(point.getX(), point.getY());
			myObjectCollection.add(b);
			break;
		case 'e':
			EnergyStation e = new EnergyStation(this);
			//e.setLocation(point.getX(), point.getY());
			myObjectCollection.add(e);
			break;
		case 'n':
			NonPlayerCyborg n = new NonPlayerCyborg();
			//n.setLocation(point.getX(), point.getY());
			myObjectCollection.add(n);
			break;
		}
	}
	/*public Point randomLocation()
	{
		boolean test = false;
		Point point = new Point(0,0);
		double x,y;
		do {
		x= random.nextInt(1300)-50;
		y= random.nextInt(1100)-50;
		
		
		IIterator theObjects = myObjectCollection.getIterator();
		if(theObjects.hasNext())
		{
		while(theObjects.hasNext() && test==false)
		{
			
			GameObject gO = theObjects.getNext();
					
			double distanceX = x - gO.getLocation().getX();
			double distanceY = y - gO.getLocation().getY();
			double distanceBetweenObjectsSquare = distanceX*distanceX+distanceY*distanceY;
			double thisRadius = 25.0;
			double otherObjectRadius= gO.getSize()/2;
			double sumRadiusSquare= thisRadius*thisRadius+2*thisRadius*otherObjectRadius+otherObjectRadius*otherObjectRadius;
			if(distanceBetweenObjectsSquare - sumRadiusSquare > 75)
			{
				test = false;
			}else {
				test = true;
			}
			
		}
		}
		}while( test==true);
		point.setX((int)x);
		point.setY((int) y);
		return point;
	}*/

	public void clickTick(double elapsedTime)
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
					if(c.isOutOfBattery()||c.isBroken())
					{
						
						this.playLostLifeSound();
						this.cyborgReset();
					}else
					{
					c.move(elapsedTime);
					c.energyLostAfterTick();
					c.setHeading(c.getHeading()+c.getSteeringDirection());
					c.checkHeadingBoudaries();
					}
				}
				if(mov instanceof Drone)
				{
					Drone d = (Drone) mov;
					d.move(elapsedTime);
					d.changeRandomHeading();
				}
				if(mov instanceof NonPlayerCyborg)
				{
					NonPlayerCyborg n = (NonPlayerCyborg) mov;
					n.setSpeedWithDamage(n.getDamageLevel());
					n.move(elapsedTime);
					n.setHeading(n.getHeading()+n.getSteeringDirection());
					n.checkHeadingBoudaries();
					n.invokeStratergy();
				}
			}
		}
		this.checkCollision();
	
		gameTime++;
		InformObservers();
	}
	public void checkCollision() {
		IIterator theObjects = myObjectCollection.getIterator();
		while(theObjects.hasNext())
		{
			GameObject current = theObjects.getNext();
			if(current instanceof Movable)
			{
			IIterator otherObjects = myObjectCollection.getIterator();
			while(otherObjects.hasNext())
			{
				GameObject others = otherObjects.getNext();
				if(current != others)
				{
						if(current.collidewith(others))
						{
							if(others instanceof EnergyStation)
							{
								EnergyStation eS = (EnergyStation)others;
								if(eS.getenergyCapacity()>0 && current instanceof Cyborg)
								{
									this.createGameObject('e');
									chargeEnergySound.play();
								}
							}
							if(current instanceof Cyborg && others instanceof Cyborg)
							{
								collisionSound.play();
							}
							if(current instanceof Cyborg && others instanceof Drone)
							{
								collisionSound.play();
							}
							current.handleCollision(others);
		
						}
				}
			}
			}
		}
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
		this.findPlayerCyborg().setEnergyLevel(1000);
		this.findPlayerCyborg().setColor(ColorUtil.rgb(255,10,10));
		this.findPlayerCyborg().setHeading(0);
		this.findPlayerCyborg().setSpeed(0);
		this.findPlayerCyborg().setSteeringDirection(0);
		this.findPlayerCyborg().setLastBaseReached(1);
		this.clearObjects();
		this.init();
		this.InformObservers();
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
	public void setPause(boolean pause)
	{
		this.pause=pause;
	}
	public boolean getPause()
	{
		return this.pause;
	}
	public void changeStatusPositioning() {
		this.positionning = !positionning;
	}
	public boolean getStatusPositioning()
	{
		return this.positionning;
	}
	
	public void processPointerClick(Point clickPoint, Point originPoint) {
		IIterator clickIterator = myObjectCollection.getIterator();
		
		while(clickIterator.hasNext()) {
			GameObject gO = clickIterator.getNext();
			if(gO instanceof ISelectable) {
				
				if(this.getStatusPositioning() && ((Fixed)gO).isSelected()) {
				
					((Fixed)gO).setLocation(clickPoint.getX() - originPoint.getX(), clickPoint.getY() - originPoint.getY());
					
					changeStatusPositioning();
					((Fixed)gO).setSelected(false);
				} else {	
					if(((Fixed)gO).contains(clickPoint, originPoint)) {
						((Fixed)gO).setSelected(true);
					} else {
						((Fixed)gO).setSelected(false);
					}
				}
				
			}
		}
		this.setChanged();
		this.notifyObservers(this);
	}
	public void addEnergyStation()
	{
		this.createGameObject('e');
	}
	public int getSize()
	{
		return myObjectCollection.getSize();
	}
	
	public void playCollisionSound() {
		if(this.getSound())
		{
			//collisionSound.play();
		}
		
	}
	public void playLostLifeSound() {
		if(this.getSound())
		{
			//lostLifeSound.play();
		}
	}
	public void playChargeEnergySound() {
		if(this.getSound())
		{
			//chargeEnergySound.play();
		}
	}
}
