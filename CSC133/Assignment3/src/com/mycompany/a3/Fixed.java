package com.mycompany.a3;

import com.codename1.charts.models.Point;

abstract public class Fixed extends GameObject implements ISelectable{
	private boolean selected=false;

	@Override
	public String toString() {
		String thisClassString=super.toString();
		return thisClassString;
	}
	public void setSelected(boolean selected)
	{
	
		this.selected=selected;
	}
	public boolean isSelected() {
		return this.selected;
	}
	@Override
	public boolean collidewith(GameObject otherobject) {
		//nothing happens here
		return false;
	}

	@Override
	public void handleCollision(GameObject otherobject) {
		// Nothing happends to the bases and energy Station
	}
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrint)
	{
		int px = (int) pPtrRelPrnt.getX();
		int py = (int) pPtrRelPrnt.getY();
		
		int xLoc = (int) ((int)this.getLocation().getX() + pCmpRelPrint.getX());
		int yLoc = (int) ((int)this.getLocation().getY() + pCmpRelPrint.getY());
		
		if ( ((px >= xLoc - getSize() /  2) && (px <= xLoc + getSize() / 2)) && 
		   ((py >= yLoc - getSize() / 2) && (py <= yLoc + getSize() / 2)))
		{
			return true;
		}
		return false;
	}
}
