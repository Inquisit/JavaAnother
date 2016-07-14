package branchAW;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

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
	
	private static Component getComponentById(Container container, String componentId)
	{

        if(container.getComponents().length > 0)
        {
            for(Component c : container.getComponents())
            {
                if(componentId.equals(c.getName()))
                {
                    return c;
                }
                if(c instanceof Container)
                {
                    Component sub = getComponentById((Container) c, componentId);
                    if (sub == null)
                    	continue;
                    return sub;
                }
            }
        }

        return null;

    }
	
	public void Draw(JFrame jFrame)
	{
		Component parent = getComponentById(jFrame.getContentPane(), Integer.toString(iPID));
		switch (iType)
		{
			case 0:
			{
				jFrame.setSize(iWidth*2+20, iHeight*2+20);
				jFrame.setLocation(iLeft*2, iTop*2);
				jFrame.setTitle(sText);
				jFrame.setName(Integer.toString(iID));
				//jFrame.setResizable(false);
				break;
			}
			case 1:
			{
				JTabbedPane tp = new JTabbedPane();
				JPanel jp = new JPanel();
				tp.setSize(iWidth*2, iHeight*2);
				tp.setLocation(new Point(iLeft*2, (iTop-13)*2));
				tp.setVisible(true);
				jp.setSize(iWidth*2, iHeight*2);
				jp.setLayout(null);
				jp.setName(Integer.toString(iID));
				jp.setVisible(true);
				tp.addTab(sText, jp);
				jFrame.add(tp);
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
				JScrollPane sp = new JScrollPane(jt);
				sp.setSize(iWidth*2, iHeight*2);
				sp.setLocation(new Point(iLeft*2, iTop*2));
				((JPanel)parent).add(sp);
				break;
			}
			case 11:
			{
				TableColumn tc = new TableColumn();
				tc.setWidth(iWidth);
				tc.setHeaderValue(sText);
				((JTable)parent).addColumn(tc);
				break;
			}
			default:
			{
				break;
			}
		}
	}
}
