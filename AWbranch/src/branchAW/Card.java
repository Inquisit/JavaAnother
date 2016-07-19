package branchAW;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Card 
{
	public JFrame jFrame;
	public ArrayList<CardField> aFields;
	public HashMap<String, ButtonGroup> mGroups;
	public int iCardType;
	
	public Card(int iTypeID, Connection conn)
	{
		jFrame = new JFrame();
		aFields = new ArrayList<CardField>();
		mGroups = new HashMap<String, ButtonGroup>();
		iCardType = iTypeID;
		fillCardContent(conn);
		aFields.sort(null);
	}
	
	public void fillCardContent(Connection conn)
	{
		if (conn == null)
			return;
		try 
		{
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select t.field_id, t.field_type, t.field_left, t.field_top, t.field_width, t.field_height, t.field_pos, t.data_type, t.field_name, t.physical_name, t.pfield_id, t.specific_data from CARD_FIELDS t where t.type_id = "+Integer.toString(iCardType)+" order by t.field_id");
			
			while (rs.next())
			{
				aFields.add(new CardField(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getBytes(12)));
			}		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
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
