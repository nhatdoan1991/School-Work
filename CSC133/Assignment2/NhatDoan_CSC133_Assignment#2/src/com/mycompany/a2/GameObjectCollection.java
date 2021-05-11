package com.mycompany.a2;

import java.util.Vector;

public class GameObjectCollection implements ICollection{

	private Vector<GameObject> gameObjects ;
	private GameWorld gw;
	
	public GameObjectCollection(GameWorld gw) {
		gameObjects= new Vector<GameObject>();
		this.gw=gw;
	}
	
	public void add(GameObject gO)
	{
		gameObjects.addElement(gO);
	}
	
	public IIterator getIterator()
	{
		return new GameWorldVectorIterator();
	}
	public void remove(GameObject gO) {
		gameObjects.remove(gO);
	}
	public void clear() {
		gameObjects.clear();
	}
	private class GameWorldVectorIterator implements IIterator{
		
		private int currentElementIndex;
		public GameWorldVectorIterator()
		{
			currentElementIndex=-1;
		}
		public boolean hasNext()
		{
			if(gameObjects.size() <=0||currentElementIndex== gameObjects.size()-1) return false;
			return true;
		}
		
		public GameObject getNext()
		{
			currentElementIndex++;
			return gameObjects.elementAt(currentElementIndex);
		}
		public GameObject atElement(int index)
		{
			return gameObjects.elementAt(index);
			
		}
	}
}
