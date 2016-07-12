package branchAW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class OraConnection 
{
	public Connection conn;
	
	public void Connect()
	{
		try
		{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.103.47:1521:QTEST", "DWADM", "DWADM");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void Connect(String sIP, String sPort, String sDB, String sUser, String sPass)
	{
		try 
		{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@"+sIP+":"+sPort+":"+sDB, sUser, sPass);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void Select(String sQuery, ArrayList<String> sRows)
	{
		try 
		{
			Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery(sQuery);
			ResultSetMetaData md = rs.getMetaData();
			int iCols = md.getColumnCount();
			
			while (rs.next())
			{
				String sRow = "";
				for (int i = 1; i <= iCols; ++i)
				{
					sRow += rs.getString(i) + "||";
				}
				sRows.add(sRow);
			}		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void Disconnect()
	{
		try 
		{
			conn.close();
		} 
		catch (SQLException e) 
		{
			
		}
	}
}