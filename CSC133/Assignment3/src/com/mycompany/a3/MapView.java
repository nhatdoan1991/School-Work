package com.mycompany.a3;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private TextArea displayMap;
	private IGameWorld tempWorld;
	
	public MapView() {
		
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255, 0, 0)));
		this.setLayout(new BorderLayout());
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(255);
	}
	
	@Override
	public void update(Observable o, Object arg)
	{
		
	 	tempWorld = (IGameWorld) arg;
		this.repaint();
	}
	
	@Override 
	public void paint(Graphics g) {
		super.paint(g);
		
		Point pCmpRelPrnt = new Point(this.getX(),this.getY());
		IIterator iterator = tempWorld.getCollection().getIterator();
		while(iterator.hasNext())
		{
			GameObject obj = iterator.getNext();
			if(obj instanceof IDrawable)
			{
				((IDrawable)obj).draw(g, pCmpRelPrnt);
			}
		}
		
	}
	
	public double getMapWidth()
	{
		return (double) this.getWidth();
	}

	public double getMapHeight()
	{
		return (double) this.getHeight();
	}
	@Override
	public void pointerPressed(int x, int y ) {
		Point clickPoint = new Point(x - getParent().getAbsoluteX(), y - getParent().getAbsoluteY());
		Point originPoint = new Point(getX(), getY());
		if(tempWorld.getPause()) {
			tempWorld.processPointerClick(clickPoint, originPoint);
		}
	}
}
