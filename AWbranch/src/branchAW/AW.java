package branchAW;

import javax.swing.JLabel;

public class AW 
{

	public static void main(String[] args) 
	{
		OraConnection ora = new OraConnection();
		ora.Connect();
		Card cCard = new Card(200, ora.conn);
		cCard.Draw();
		JLabel cmp = new JLabel();
		cCard.jFrame.add(cmp);
		cCard.jFrame.setVisible(true);
		cCard.aFields.get(13).parseSpecificData();
		ora.Disconnect();
	}

}/**111111111*/
