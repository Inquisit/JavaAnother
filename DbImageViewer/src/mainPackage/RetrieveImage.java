package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.zip.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

public class RetrieveImage 
{  
	public static void main(String[] args) 
	{  
		try
		{   	
			String Path = System.getenv("Path");
			String[] pathes = Path.split(";");
			String PathToOra=null;
			for(String token : pathes)
			{
				if(token.lastIndexOf("product") != -1)
				{
					PathToOra = token.substring(0, token.length()-3);
					break;
				}
				else if(token.lastIndexOf("ora92") != -1)
				{
					PathToOra = token.substring(0, token.length()-3); 
					break;
				}
			}
			
			System.setProperty("oracle.net.tns_admin", PathToOra+"network\\admin");
			Connection con=DriverManager.getConnection( "jdbc:oracle:thin:@"+args[0], "GUEST", "GUEST");  
			      
			PreparedStatement ps=con.prepareStatement("select t.CONTENT from DWADM.FILE_CONT t where FILE_ID = " + args[1]);
			
			
			BufferedImage img = null;
			ResultSet rs=ps.executeQuery();  
			if(rs.next())
			{ 
				InputStream barr=rs.getBinaryStream(1);
				GZIPInputStream gzs = new GZIPInputStream(barr);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
		        try
		        {
		            IOUtils.copy(gzs, out);
		        } 
		        catch(IOException e)
		        {
		            throw new RuntimeException(e);
		        }
		        ByteArrayInputStream bas = new ByteArrayInputStream(out.toByteArray());
				img = ImageIO.read(bas); 
				bas.close();
				out.close();
				gzs.close();
				barr.close();
				
			}      
			rs.close();
			ps.close();
			con.close();  
			JFrame jFrame= new JFrame();
			jFrame.setLayout(null);		
			JLabel jl = new JLabel();
			ImageIcon icon = new ImageIcon(img);
			jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			jFrame.setSize(icon.getIconWidth(), icon.getIconHeight());
			jFrame.setResizable(false);
			jFrame.setLocation(150, 150);
			jl.setIcon(icon);
			jl.setLocation(0, 0);
			jl.setSize(icon.getIconWidth(), icon.getIconHeight());
			jl.setVisible(true);
			jFrame.add(jl);
			jFrame.setVisible(true); 
		}
		catch (Exception e) 
		{
			e.printStackTrace();  
		}
		
	}  
	
}  