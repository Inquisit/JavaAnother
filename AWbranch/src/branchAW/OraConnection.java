package branchAW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OraConnection 
{
	public Connection conn;
	
	public void Connect()
	{
		try
		{
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.103.47:1521:STISTER", "DWADM", "DWADM");
			//conn = DriverManager.getConnection("jdbc:oracle:thin:@172.30.200.235:1521:EDS", "DWADM", "EDS_ADM");
		}
		catch (SQLException e) 
		{
			conn = null;
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
			conn = null;
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