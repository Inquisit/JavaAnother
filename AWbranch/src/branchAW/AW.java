package branchAW;

import javax.swing.JLabel;

public class AW 
{

	public static void main(String[] args) 
	{
		OraConnection ora = new OraConnection();
		ora.Connect();
		Card cCard = new Card(132, ora.conn);
		cCard.Draw();
		JLabel cmp = new JLabel();
		cCard.jFrame.add(cmp);
		cCard.jFrame.setVisible(true);
		ora.Disconnect();
	}

}/**111111111*/
