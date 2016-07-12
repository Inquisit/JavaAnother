package branchAW;

import javax.swing.JLabel;
import java.util.ArrayList;

import javax.swing.JFrame;

public class AW 
{

	public static void main(String[] args) 
	{
		ArrayList<String> sRows = new ArrayList<String>();
		OraConnection ora = new OraConnection();
		ora.Connect();
		ora.Select("select t.field_id, t.field_type, t.field_left, t.field_top, t.field_width, t.field_height, t.field_pos, t.data_type, t.field_name, t.physical_name, t.pfield_id from CARD_FIELDS t where t.type_id = 158 order by t.field_id", sRows);
		ora.Disconnect();
		ArrayList<CardField> cFields = new ArrayList<CardField>();
		for (String s: sRows)
		{
			System.out.println(s);
			CardField cf = new CardField(s);
			cFields.add(cf);
		}
		JFrame frame = new JFrame();
		for (CardField cf : cFields)
		{
			cf.Draw(frame);
		}
		JLabel cmp = new JLabel();
		frame.add(cmp);
		frame.setVisible(true);
	}

}
