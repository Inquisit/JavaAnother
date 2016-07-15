package branchAW;

import java.awt.Component;
import java.awt.Container;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
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
					iID = Integer.parseInt(sCols[i]);
					break;
				}
				case 1:
				{
					iType = Integer.parseInt(sCols[i]);
					break;
				}
				case 2:
				{
					iLeft = Integer.parseInt(sCols[i]);
					break;
				}
				case 3:
				{
					iTop = Integer.parseInt(sCols[i]);
					break;
				}
				case 4:
				{
					iWidth = Integer.parseInt(sCols[i]);
					break;
				}
				case 5:
				{
					iHeight = Integer.parseInt(sCols[i]);
					break;
				}
				case 6:
				{
					iPos = Integer.parseInt(sCols[i]);
					break;
				}
				case 7:
				{
					sType = sCols[i];
					break;
				}
				case 8:
				{
					sText = sCols[i];
					break;
				}
				case 9:
				{
					sName = sCols[i];
					break;
				}
				case 10:
				{
					iPID = Integer.parseInt(sCols[i]);
					break;
				}
				default:
				{
					break;
				}
			}	
		}
	}
	
	public void Draw(Card cCard)
	{
		Component parent = cCard.getComponentById(cCard.jFrame.getContentPane(), Integer.toString(iPID));
		switch (iType)
		{
			case 0:
			{
				cCard.jFrame.setSize(iWidth*2+20, iHeight*2+46);
				cCard.jFrame.setLocation(iLeft*2, iTop*2);
				cCard.jFrame.setTitle(sText);
				cCard.jFrame.setName(Integer.toString(iID));
				cCard.jFrame.setResizable(false);
				cCard.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				break;
			}
			case 1:
			{
				if (parent == null)
				{
					JTabbedPane tp = new JTabbedPane();
					tp.setSize(iWidth*2, (iHeight+13)*2);
					tp.setLocation(new Point(iLeft*2, (iTop-13)*2));
					tp.setVisible(true);
					tp.setName(Integer.toString(iPID));
					cCard.jFrame.add(tp);
					parent = tp;
				}
				JPanel jp = new JPanel();
				jp.setSize(iWidth*2, (iHeight+13)*2);
				jp.setLayout(null);
				jp.setName(Integer.toString(iID));
				jp.setVisible(true);
				ButtonGroup bg = new ButtonGroup();
				cCard.mGroups.put(jp.getName(), bg);
				((JTabbedPane)parent).addTab(sText, jp);				
				break;
			}
			case 2:
			{
				JLabel jl = new JLabel();
				jl.setSize(iWidth*2, iHeight*2);
				jl.setText(sText);
				jl.setName(Integer.toString(iID));
				jl.setLocation(new Point(iLeft*2, iTop*2));
				jl.setVisible(true);
				((JPanel)parent).add(jl);
				break;
			}
			case 3:
			{
				JTextArea ta = new JTextArea();
				ta.setSize(iWidth*2, iHeight*2);
				ta.setText(sText);
				ta.setName(Integer.toString(iID));
				ta.setLocation(new Point(iLeft*2, iTop*2));
				ta.setVisible(true);
				((JPanel)parent).add(ta);
				break;
			}
			case 4:
			{
				JPanel gb = new JPanel();
				gb.setLayout(null);
				Border border = BorderFactory.createTitledBorder(sText);
				gb.setBorder(border);
				gb.setSize(iWidth*2, iHeight*2);
				gb.setName(Integer.toString(iID));
				gb.setLocation(new Point(iLeft*2, iTop*2));
				ButtonGroup bg = new ButtonGroup();
				cCard.mGroups.put(gb.getName(), bg);
				((JPanel)parent).add(gb);
				break;
			}
			case 5:
			{
				JComboBox<String> cb = new JComboBox<String>();
				cb.setSize(iWidth*2, 20);
				cb.setName(Integer.toString(iID));
				cb.setLocation(new Point(iLeft*2, iTop*2));
				cb.setVisible(true);
				((JPanel)parent).add(cb);
				break;
			}
			case 7:
			{
				JCheckBox cb = new JCheckBox();
				cb.setSize(iWidth*2, 20);
				cb.setName(Integer.toString(iID));
				cb.setText(sText);
				cb.setLocation(new Point(iLeft*2, iTop*2));
				cb.setVisible(true);
				((JPanel)parent).add(cb);
				break;
			}
			case 8:
			{
				JRadioButton rb = new JRadioButton();
				rb.setSize(iWidth*2, 20);
				rb.setName(Integer.toString(iID));
				rb.setText(sText);
				rb.setLocation(new Point(iLeft*2, iTop*2));
				rb.setVisible(true);
				Component c = cCard.getPanelByXY((Container)parent, iLeft*2, iTop*2);
				if (c != null)
				{
					ButtonGroup bg = cCard.mGroups.get(c.getName());
					bg.add(rb);
					System.out.println("added to bg " + sText);
					rb.setLocation(new Point(iLeft*2 - c.getX(), iTop*2 - c.getY()));
					((JPanel)c).add(rb);
				}
				else
				{
					((JPanel)parent).add(rb);
				}
				break;
			}
			case 9:
			{
				JButton jb = new JButton();
				jb.setSize(iWidth*2, iHeight*2);
				jb.setText(sText);
				jb.setName(Integer.toString(iID));
				jb.setLocation(new Point(iLeft*2, iTop*2));
				jb.setVisible(true);
				((JPanel)parent).add(jb);
				break;
			}
			case 10:
			{
				JTable jt = new JTable();
				jt.setName(Integer.toString(iID));
				jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				JScrollPane sp = new JScrollPane(jt);
				sp.setSize(iWidth*2, iHeight*2);
				sp.setLocation(new Point(iLeft*2, iTop*2));
				((JPanel)parent).add(sp);
				break;
			}
			case 11:
			{
				TableColumn tc = new TableColumn();
				tc.setPreferredWidth(iWidth*2);
				tc.setHeaderValue(sText);
				((JTable)parent).addColumn(tc);
				break;
			}
/*			case 12:
			{
				BufferedImage bi = new BufferedImage();
				((JPanel)parent).add(bi);
				break;
			}*/
			default:
			{
				break;
			}
		}
	}
}
