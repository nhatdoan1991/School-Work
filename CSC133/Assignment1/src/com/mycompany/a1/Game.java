package com.mycompany.a1;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;
import com.codename1.charts.util.ColorUtil;

public class Game extends Form{
	private GameWorld gw;
	private char holdKey;
	public Game() {
	gw = new GameWorld();
	gw.init();
	play();
	}
	private void play()
	{
		Label myLabel=new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		myTextField.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
				String sCommand=myTextField.getText().toString();
				char hold=sCommand.charAt(0);
				myTextField.clear();
				if(sCommand.length() != 0)
						switch (sCommand.charAt(0)) {
						case 'x':
							setHoldKey(sCommand.charAt(0));
							System.out.println("Are you sure that you want to exit the game???\n Type 'y' for yes and 'n' for no.");
							break;
						case 'a':
							setHoldKey(sCommand.charAt(0));
							int lastSpeed=gw.findCyborg().getSpeed();
							gw.findCyborg().speedUp();
							if(gw.findCyborg().getSpeed()!=lastSpeed) {
								System.out.println("\nYour Cyborg's speed is "+ gw.findCyborg().getSpeed() + " units per tick\n");
							}
							break;
						case 'b':
							setHoldKey(sCommand.charAt(0));
							int lastSpeedBreak=gw.findCyborg().getSpeed();
							gw.findCyborg().slowDown();
							if(gw.findCyborg().getSpeed()!=lastSpeedBreak)
							{
								System.out.println("\n.Your Cyborg's speed is "+ gw.findCyborg().getSpeed() + " units per tick\n");
							}
							break;
						case 'l':
							setHoldKey(sCommand.charAt(0));
							gw.findCyborg().changeHeading('l');
							break;
						case 'r':
							setHoldKey(sCommand.charAt(0));
							gw.findCyborg().changeHeading('r');
							break;
						case 'c':
							setHoldKey(sCommand.charAt(0));
							if(!gw.findCyborg().isBroken())
							{
								gw.findCyborg().collideWithCyborg();
								System.out.println("You just collided with another cyborg");
								System.out.println("Cyborg's damage level is "+ gw.findCyborg().getDamageLevel());
								System.out.println("Cyborg's color is: " + "[" + ColorUtil.red(gw.findCyborg().getColor()) + "," + 
										+ ColorUtil.green(gw.findCyborg().getColor()) + "," + 
										+ ColorUtil.blue(gw.findCyborg().getColor()) + "]");
							}else {
								gw.setLiveOfPlayer(gw.getLiveOfPlayer()-1);
								gw.findCyborg().resetCyborg(gw.findBase().getLocation());
								if(gw.getLiveOfPlayer()==0)
								{
									gw.setEndGame(true);
									gw.endGame();
								}
								System.out.println("\nYour Cyborg is destroyed. You lost one live, and your Cyborg was recreated");
							}
							
							break;	
						case '1' :
						case '2' :
						case '3' :
						case '4' :
						case '5' :
						case '6' :
						case '7' :
						case '8' :
						case '9' :
							int numberHold= hold;
							numberHold=numberHold-48;
							setHoldKey(sCommand.charAt(0));
							gw.findCyborg().baseReach(numberHold);
							if(gw.findCyborg().getLastBaseReached()==gw.getLastBase())
							{
								System.out.println("You Won The Game!\n");
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
							break;
							
						case 'e':
							setHoldKey(sCommand.charAt(0));
							System.out.println("\nEnergy of this station before collision is "+gw.findEnergyStation().getenergyCapacity());
							System.out.println("Energy of Cyborg before collision is "+gw.findCyborg().getEnergyLevel());
							
							gw.findCyborg().reachEnergyStation(gw.findEnergyStation().getenergyCapacity());
							
							System.out.println("Energy of Cyborg is recharged to " + gw.findCyborg().getEnergyLevel());
							
							gw.findEnergyStation().setEnergyCapacity(0);
							
							gw.findEnergyStation().setColor(ColorUtil.rgb(144,238,144));//light green
							
							System.out.println("\nEnergy of this station after collision is " + gw.findEnergyStation().getenergyCapacity());
							System.out.println("Color of this station is: " + "[" + ColorUtil.red(gw.findEnergyStation().getColor()) + "," +
																			ColorUtil.green(gw.findEnergyStation().getColor()) + "," + 							
																			+ ColorUtil.blue(gw.findEnergyStation().getColor()) + "]");
							gw.createGameObject('e');
							System.out.println("\nA new energy station was just created!!!!");
							break;
						case 'g':
							setHoldKey(sCommand.charAt(0));
							if(!gw.findCyborg().isBroken())
							{
								gw.findCyborg().collideWithDrone();
								System.out.println("\nYou just collided with a drone");
								System.out.println("Cyborg's damage level is "+ gw.findCyborg().getDamageLevel());
								System.out.println("Cyborg's color is: " + "[" + ColorUtil.red(gw.findCyborg().getColor()) + "," + 
										+ ColorUtil.green(gw.findCyborg().getColor()) + "," + 
										+ ColorUtil.blue(gw.findCyborg().getColor()) + "]");
							}else {
								gw.setLiveOfPlayer(gw.getLiveOfPlayer()-1);
								gw.findCyborg().resetCyborg(gw.findBase().getLocation());
								if(gw.getLiveOfPlayer()==0)
								{
									gw.setEndGame(true);
									gw.endGame();
								}
								System.out.println("\nYour Cyborg is destroyed. You lost one live, and your Cyborg was recreated");
							}
							break;
						case 't':
							setHoldKey(sCommand.charAt(0));
							gw.clickTick();
							if(gw.findCyborg().isOutOfBattery()||gw.findCyborg().isBroken())
							{
								gw.setLiveOfPlayer(gw.getLiveOfPlayer()-1);;//Cyborg died
								if(gw.getLiveOfPlayer()==0)
								{
									gw.endGame();
								}else
								{
									System.out.println("\nYour Cyborg ran out of energy or too damaged. You lost one live!!");
									gw.findCyborg().resetCyborg(gw.findBase().getLocation());
								}
								
							}
							break;
						case 'd':
							setHoldKey(sCommand.charAt(0));
							gw.displayCyborg();
				
							break;
						case 'm':
							setHoldKey(sCommand.charAt(0));
							gw.displayMap();
							break;
						case 'n':
							if(getHoldKey()=='x')
							{
								System.out.println("\nThe Game continues");
							}
							setHoldKey(sCommand.charAt(0));
							
							break;
						case 'y':
							
							if(getHoldKey()=='x')
							{
								gw.exit();
							}
							setHoldKey(sCommand.charAt(0));
							break;	
						default:
							System.out.println("\nYour Input is invalid.Please enter valid command!!!\n");
							break;
						
	//add code to handle rest of the commands
						} //switch
				} //actionPerformed
		} //new ActionListener()
		); //addActionListener
	} //play
	private void setHoldKey(char key)
	{
		this.holdKey=key;
	}
	private char getHoldKey() {
		return this.holdKey;
	}
}
