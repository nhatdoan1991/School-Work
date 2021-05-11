package com.mycompany.a2;
import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	private TextArea displayMap;
	public MapView() {
		
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255, 0, 0)));
		this.setLayout(new BorderLayout());
		Container myComponent = new Container(new FlowLayout());
		Label myLabel = new Label("Map View");
		myLabel.getAllStyles().setPadding(LEFT, 70);

		this.setOpaque(true);
  		this.getAllStyles().setBgColor(ColorUtil.rgb(0, 50, 255));
		this.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 0));
		myComponent.add(myLabel);
		this.add(BorderLayout.NORTH,myComponent);
		this.getAllStyles().setMargin(LEFT, 3);
		this.getAllStyles().setMargin(RIGHT, 3);
		displayMap = new TextArea();
		displayMap.setEditable(false);
		displayMap.getAllStyles().setBgTransparency(0);
		
		this.setWidth(1000);
		this.setHeight(1000);
		
		this.add(BorderLayout.CENTER, displayMap);
	}
	public void update(Observable o, Object arg)
	{
		
	 	GameWorldProxy tempProxy = (GameWorldProxy) arg;
		IIterator iterator = tempProxy.getCollection().getIterator();
		String fullText = "";
		if(tempProxy.getConsoleDisplay()==null)
		{
			fullText = "\n";
		}
		else {
			fullText = ""+tempProxy.getConsoleDisplay();
		}
		while (iterator.hasNext())
		{
			fullText += iterator.getNext().toString() + "\n";
		}
		displayMap.setText(fullText);
		this.repaint();
	}
	
	public double getMapWidth()
	{
		return (double) this.getWidth();
	}

	public double getMapHeight()
	{
		return (double) this.getHeight();
	}
}
