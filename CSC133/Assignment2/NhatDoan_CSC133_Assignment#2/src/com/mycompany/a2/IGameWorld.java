package com.mycompany.a2;

interface IGameWorld {

	public int getGameTime();
	public int getLiveOfPlayer();
	public int getLastBaseCyborg();
	public int getPlayerEnergyLevel();
	public int getPlayerDamageLevel();
	public boolean getSound();
	public GameObjectCollection getCollection(); 
	public String getConsoleDisplay();
}
