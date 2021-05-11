package com.mycompany.a2;

import java.util.Observable;

public class GameWorldProxy extends Observable implements ICollection, IGameWorld{

	private GameWorld realGameWorld;
	
	public GameWorldProxy(GameWorld gw)
	{
		this.realGameWorld=gw;
	}
	public void add(GameObject gO) {
		//Code to add game objects through Proxy
	}
	
	public IIterator getIterator() {
		//Code to add game Iterators through Proxy
		return null;
	}
	
	public void remove(GameObject gO) {
		//Code to delete game objects through Proxy
	}
	
	public int getGameTime() {
		return realGameWorld.getGameTime();
	}
	public int getLiveOfPlayer(){
		return realGameWorld.getLiveOfPlayer();
	}
	public int getLastBaseCyborg(){
		return realGameWorld.getLastBaseCyborg();
	}
	public int getPlayerEnergyLevel() {
		return realGameWorld.findPlayerCyborg().getEnergyLevel();
	}
	public int getPlayerDamageLevel() {
		return realGameWorld.findPlayerCyborg().getDamageLevel();
	}
	public boolean getSound() {
		return realGameWorld.getSound();
	}
	
	public GameObjectCollection getCollection() {
		
		return realGameWorld.getCollection();
	}
	public String getConsoleDisplay() {
		return realGameWorld.getConsoleDisplay();
	}
}
