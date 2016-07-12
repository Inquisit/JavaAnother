package branchAW;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AW 
{

	public static void main(String[] args) 
	{
		OraConnection ora = new OraConnection();

		try 
		{
			Statement st = ora.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select 1 as int from dual union select 2 as int from dual");
			ResultSetMetaData md = rs.getMetaData();
			int iCols = md.getColumnCount();
			ArrayList<String> sRows = new ArrayList<String>();
			
			while (rs.next())
			{
				String sRow = "";
				for (int i = 1; i <= iCols; ++i)
				{
					sRow += rs.getString(i);
				}
				sRows.add(sRow);
			}
			
			for (String s: sRows)
			{
				System.out.println(s);
			}		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		ora.Disconnect();
	}

}
