package branchAW;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.util.AbstractMap;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Card 
{
	public JFrame jFrame;
	public ArrayList<CardField> aFields;
	public AbstractMap<String, ButtonGroup> mGroups;
	
	public Card()
	{
		jFrame = new JFrame();
		aFields = new ArrayList<CardField>();
	}
	
	public void Draw()
	{
		for (CardField cf : aFields)
		{
			cf.Draw(this);
		}
	}
	
	public Component getComponentById(Container container, String componentId)
	{

        if(container.getComponents().length > 0)
        {
            for(Component c : container.getComponents())
            {
                if(componentId.equals(c.getName()))
                {
                    return c;
                }
                if(c instanceof Container)
                {
                    Component sub = getComponentById((Container) c, componentId);
                    if (sub == null)
                    	continue;
                    return sub;
                }
            }
        }

        return null;

    }
	
	public Component getPanelByXY(Container container, int iX, int iY)
	{
		if(container.getComponents().length > 0)
	        {
	            for(Component c : container.getComponents())
	            {
	                Point p = c.getLocation();
	                int iH = c.getHeight();
	                int iW = c.getWidth();
	                if (((p.x + iW >= iX) && (p.x <= iX) && (p.y + iH >= iY) && (p.y <= iY)) && (c instanceof JPanel))
	                {
	                	return c;
	                }
	                if(c instanceof Container)
	                {
	                    Component sub = getPanelByXY((Container) c, iX, iY);
	                    if (sub == null)
	                    	continue;
	                    return sub;
	                }
	            }
	        }

		return null;
	}
}
