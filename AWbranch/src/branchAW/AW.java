package branchAW;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.alee.laf.WebLookAndFeel;

import globals.GLOBAL_CONSTANTS;

public class AW 
{

	public static void main(String[] args) 
	{
/*		try 
		{
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.basic.BasicLookAndFeel");*/
//			WebLookAndFeel.install ();
			
			/*for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) 
			{
				System.out.println(info.getName());
		        if (GLOBAL_CONSTANTS.THEME.equals(info.getName())) 
		        {
		            UIManager.setLookAndFeel(info.getClassName());
		            //break;
		        }
		    }
			
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
		{
			e.printStackTrace();
		}*/
		OraConnection ora = new OraConnection();
		ora.Connect();
		Card cCard = new Card(199, ora.conn);
		cCard.Draw();
		JLabel cmp = new JLabel();
		cCard.jFrame.add(cmp);
		cCard.jFrame.setVisible(true);
		cCard.aFields.get(13).parseSpecificData();
		ora.Disconnect();
	}

}
