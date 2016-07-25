package branchAW;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class AW 
{

	public static void main(String[] args) 
	{
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) 
		{
			e.printStackTrace();
		}
		OraConnection ora = new OraConnection();
		ora.Connect();
		Card cCard = new Card(199, ora.conn);
		cCard.Draw();
		JLabel cmp = new JLabel();
		cCard.jFrame.add(cmp);
		cCard.jFrame.setVisible(true);
		ora.Disconnect();
	}

}
