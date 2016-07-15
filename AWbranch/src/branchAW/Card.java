package branchAW;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;

public class Card 
{
	ArrayList<CardField> aFields;
	Map<String, ButtonGroup> mGroups;
	
	public static Component getComponentById(Container container, String componentId)
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
	
	public static Component getPanelByXY(Container container, int iX, int iY)
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
