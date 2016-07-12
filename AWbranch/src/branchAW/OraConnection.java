package branchAW;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class OraConnection 
{
	public Connection conn;
	
	public OraConnection()
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
	
	public OraConnection(String sConn, String sUser, String sPass)
	{
		try 
		{
			conn = DriverManager.getConnection(sConn, sUser, sPass);
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