package com.mycompany.a3;

import com.codename1.charts.models.Point;

interface IGameWorld {

	public int getGameTime();
	public int getLiveOfPlayer();
	public int getLastBaseCyborg();
	public int getPlayerEnergyLevel();
	public int getPlayerDamageLevel();
	public boolean getSound();
	public GameObjectCollection getCollection(); 
	public String getConsoleDisplay();
	public boolean getPause();
	public void processPointerClick(Point clickPoint, Point originPoint);
}
