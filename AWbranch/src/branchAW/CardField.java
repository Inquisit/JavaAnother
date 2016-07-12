package branchAW;

import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableColumn;

public class CardField 
{
	private int iID;
	private int iType;
	private int iPID;
	private int iPos;
	private int iTop;
	private int iLeft;
	private int iWidth;
	private int iHeight;
	private String sType;
	private String sName;
	private String sText;
	
	public String getsText() {
		return sText;
	}

	public void setsText(String sText) {
		this.sText = sText;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public int getiHeight() {
		return iHeight;
	}

	public void setiHeight(int iHeight) {
		this.iHeight = iHeight;
	}

	public int getiWidth() {
		return iWidth;
	}

	public void setiWidth(int iWidth) {
		this.iWidth = iWidth;
	}

	public int getiLeft() {
		return iLeft;
	}

	public void setiLeft(int iLeft) {
		this.iLeft = iLeft;
	}

	public int getiTop() {
		return iTop;
	}

	public void setiTop(int iTop) {
		this.iTop = iTop;
	}

	public int getiPos() {
		return iPos;
	}

	public void setiPos(int iPos) {
		this.iPos = iPos;
	}

	public int getiPID() {
		return iPID;
	}

	public void setiPID(int iPID) {
		this.iPID = iPID;
	}

	public int getiType() {
		return iType;
	}

	public void setiType(int iType) {
		this.iType = iType;
	}

	public int getiID() {
		return iID;
	}

	public void setiID(int iID) {
		this.iID = iID;
	}
	
	public CardField(String sRow)
	{
		String[] sCols = sRow.split("\\|\\|");
		for (int i = 0; i < sCols.length; ++i)
		{
			if (sCols[i] == "")
			{
				sCols[i] = "0";
			}
			switch (i)
			{
				case 0:
				{
					setiID(Integer.parseInt(sCols[i]));
					break;
				}
				case 1:
				{
					setiType(Integer.parseInt(sCols[i]));
					break;
				}
				case 2:
				{
					setiTop(Integer.parseInt(sCols[i]));
					break;
				}
				case 3:
				{
					setiLeft(Integer.parseInt(sCols[i]));
					break;
				}
				case 4:
				{
					setiWidth(Integer.parseInt(sCols[i]));
					break;
				}
				case 5:
				{
					setiHeight(Integer.parseInt(sCols[i]));
					break;
				}
				case 6:
				{
					setiPos(Integer.parseInt(sCols[i]));
					break;
				}
				case 7:
				{
					setsType(sCols[i]);
					break;
				}
				case 8:
				{
					setsText(sCols[i]);
					break;
				}
				case 9:
				{
					setsName(sCols[i]);
					break;
				}
				case 10:
				{
					setiPID(Integer.parseInt(sCols[i]));
					break;
				}
				default:
				{
					break;
				}
			}	
		}
	}
	
	public void Draw(JFrame jFrame)
	{
		switch (iType)
		{
			case 0:
			{
				jFrame.setSize(iWidth*2, iHeight*2);
				jFrame.setLocation(iLeft*2, iTop*2);
				jFrame.setTitle(sText);
				jFrame.setResizable(false);
				break;
			}
			case 2:
			{
				JLabel jl = new JLabel();
				jl.setSize(iWidth*2, iHeight*2);
				jl.setText(sText);
				jl.setLocation(new Point(iTop*2, iLeft*2));
				jl.setVisible(true);
				jFrame.add(jl, iPos);
				break;
			}
			case 3:
			{
				JTextArea ta = new JTextArea();
				ta.setSize(iWidth*2, iHeight*2);
				ta.setText(sText);
				ta.setLocation(new Point(iTop*2, iLeft*2));
				ta.setVisible(true);
				jFrame.add(ta, iPos);
				break;
			}
			case 5:
			{
				JComboBox<String> cb = new JComboBox<String>();
				cb.setSize(iWidth*2, 20);
				cb.setName(sText);
				cb.setLocation(new Point(iTop*2, iLeft*2));
				cb.setVisible(true);
				jFrame.add(cb, iPos);
				break;
			}
			case 9:
			{
				JButton jb = new JButton();
				jb.setSize(iWidth*2, iHeight*2);
				jb.setText(sText);
				jb.setLocation(new Point(iTop*2, iLeft*2));
				jb.setVisible(true);
				jFrame.add(jb, iPos);
				break;
			}
			case 10:
			{
				JTable jt = new JTable();
				jt.setSize(iWidth*2, iHeight*2);
				jt.setName(sText);
				jt.setLocation(new Point(iTop*2, iLeft*2));
				jt.setVisible(true);
				jFrame.add(jt, iPos);
				break;
			}
			case 11:
			{
//				JTable jt = (JTable)jFrame.getComponentAt();
				TableColumn tc = new TableColumn();
				tc.setWidth(iWidth);
				tc.setHeaderValue(sText);
//				jt.addColumn(tc);
				break;
			}
			default:
			{
				break;
			}
		}
	}
}
