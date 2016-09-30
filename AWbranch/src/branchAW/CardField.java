package branchAW;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
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
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.table.TableColumn;

import globals.FIELD_TYPES;
import globals.GLOBAL_CONSTANTS;
import net.sf.image4j.codec.ico.ICODecoder;

public class CardField implements Comparable<CardField>
{
	private int iID;
	private FIELD_TYPES fType;
	private int iLeft;
	private int iTop;
	private int iWidth;
	private int iHeight;
	private int iPos;
	private String sType;
	private String sText;
	private String sName;
	private int iPID;
	private SD_Byte bSD;
	private SpecificData sdData;
	
	public CardField(int inID, FIELD_TYPES fnType, int inLeft, int inTop, int inWidth, int inHeight, int inPos, String snType, String snText, String snName, int inPID, byte[] bnSD)
	{
		iID = inID;
		fType = fnType;
		iLeft = inLeft;
		iTop = inTop;
		iWidth = inWidth;
		iHeight = inHeight;
		iPos = inPos;
		sType = snType;
		sText = snText;
		sName = snName;
		iPID = inPID;
		bSD = new SD_Byte(bnSD);
	}
	
	public void Draw(Card cCard)
	{
		Component parent = cCard.getComponentById(cCard.jFrame.getContentPane(), Integer.toString(iPID));
		switch (fType)
		{
			case TABBED_PANE:
			{
				if (iID == 0)
				{
					cCard.jFrame.setSize((int)((iWidth+10)*GLOBAL_CONSTANTS.SCALE), (int)((iHeight+23)*GLOBAL_CONSTANTS.SCALE));
					cCard.jFrame.setLocation((int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE));
					cCard.jFrame.setTitle(sText);
					cCard.jFrame.setName(Integer.toString(iID));
					cCard.jFrame.setResizable(false);
					cCard.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					sdData = new SD_MainTab();
					sdData.parse(bSD);
					if (((SD_MainTab)sdData).isIcon)
					{
						List<BufferedImage> img = null;
						try
						{
							img = ICODecoder.read(new ByteArrayInputStream(((SD_MainTab)sdData).bIcon));
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
							break;
						}
						BufferedImage target = img.get(0);
						for (BufferedImage bi : img)
						{
							if (bi.getHeight() == 16)
							{
								target = bi;
								break;
							}
						}
						Image scaled = target.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
						cCard.jFrame.setIconImage(scaled);
					}
				}
				else
				{
					sdData = new SD_TabbedPane();
					sdData.parse(bSD);
				}
				JTabbedPane tp = new JTabbedPane(((SD_TabbedPane)sdData).iSide + 1);
				tp.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
				tp.setSize((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(iHeight*GLOBAL_CONSTANTS.SCALE));
				tp.setLocation(new Point((int)((iID==0?0:iLeft)*GLOBAL_CONSTANTS.SCALE), (int)((iID==0?0:iTop)*GLOBAL_CONSTANTS.SCALE)));
				tp.setVisible(true);
				tp.setName(Integer.toString(iID));
				if (parent == null)
				{
					cCard.jFrame.add(tp);
				}
				else
				{
					((JPanel)parent).add(tp);
				}
				break;
			}
			case TAB_PANEL:
			{
				sdData = new SD_TabPanel();
				sdData.parse(bSD);
				JPanel jp = new JPanel();
				jp.setSize((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(iHeight*GLOBAL_CONSTANTS.SCALE));
				jp.setLayout(null);
				jp.setName(Integer.toString(iID));
				jp.setVisible(true);
				ButtonGroup bg = new ButtonGroup();
				cCard.mGroups.put(jp.getName(), bg);
				if (((SD_TabPanel)sdData).isIcon)
				{
					List<BufferedImage> img = null;
					try
					{
						img = ICODecoder.read(new ByteArrayInputStream(((SD_TabPanel)sdData).bIcon));
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
						break;
					}
					BufferedImage target = img.get(0);
					for (BufferedImage bi : img)
					{
						if (bi.getHeight() == 16)
						{
							target = bi;
							break;
						}
					}
					Image scaled = target.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
					ImageIcon icon = new ImageIcon(scaled);
					((JTabbedPane)parent).addTab(sText, icon, jp);
				}
				else
				{
					((JTabbedPane)parent).addTab(sText, jp);
				}
				break;
			}
			case LABEL:
			{
				sdData = new SD_Label();
				sdData.parse(bSD);
				JLabel jl = new JLabel();
				jl.setSize((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(iHeight*GLOBAL_CONSTANTS.SCALE));
				jl.setText(sText);
				jl.setName(Integer.toString(iID));
				jl.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE)));
				jl.setVisible(true);
				Component c = cCard.getPanelByXY((Container)parent, (int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE));
				if (c != null)
				{
					jl.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE) - c.getX(), (int)(iTop*GLOBAL_CONSTANTS.SCALE) - c.getY()));
					((JPanel)c).add(jl);
				}
				else
				{
					((JPanel)parent).add(jl);
				}
				break;
			}
			case TEXT_AREA:
			{
				sdData = new SD_TextArea();
				sdData.parse(bSD);
				JTextArea ta = new JTextArea();
				ta.setSize((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(iHeight*GLOBAL_CONSTANTS.SCALE));
				ta.setText(sText);
				ta.setName(Integer.toString(iID));
				ta.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE)));
				ta.setVisible(true);
				Border border = BorderFactory.createEtchedBorder();
				ta.setBorder(border);
				Component c = cCard.getPanelByXY((Container)parent, (int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE));
				if (c != null)
				{
					ta.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE) - c.getX(), (int)(iTop*GLOBAL_CONSTANTS.SCALE) - c.getY()));
					((JPanel)c).add(ta);
				}
				else
				{
					((JPanel)parent).add(ta);
				}
				break;
			}
			case GROUP_PANE:
			{
				sdData = new SD_GroupPane();
				sdData.parse(bSD);
				JPanel gb = new JPanel();
				gb.setLayout(null);
				Border border = BorderFactory.createTitledBorder(sText);
				gb.setBorder(border);
				gb.setSize((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(iHeight*GLOBAL_CONSTANTS.SCALE));
				gb.setName(Integer.toString(iID));
				gb.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE)));
				ButtonGroup bg = new ButtonGroup();
				cCard.mGroups.put(gb.getName(), bg);
				((JPanel)parent).add(gb);
				break;
			}
			case COMBO_BOX:
			{
				JComboBox<String> cb = new JComboBox<String>();
				cb.setSize((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(GLOBAL_CONSTANTS.COMBOBOX_SIZE*GLOBAL_CONSTANTS.SCALE));
				cb.setName(Integer.toString(iID));
				cb.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE)));
				cb.setVisible(true);
				Component c = cCard.getPanelByXY((Container)parent, (int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE));
				if (c != null)
				{
					cb.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE) - c.getX(), (int)(iTop*GLOBAL_CONSTANTS.SCALE) - c.getY()));
					((JPanel)c).add(cb);
				}
				else
				{
					((JPanel)parent).add(cb);
				}
				break;
			}
			case CHECK_BOX:
			{
				JCheckBox cb = new JCheckBox();
				cb.setSize((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(iHeight*GLOBAL_CONSTANTS.SCALE));
				cb.setName(Integer.toString(iID));
				cb.setText(sText);
				cb.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE)));
				cb.setVisible(true);
				Component c = cCard.getPanelByXY((Container)parent, (int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE));
				if (c != null)
				{
					cb.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE) - c.getX(), (int)(iTop*GLOBAL_CONSTANTS.SCALE) - c.getY()));
					((JPanel)c).add(cb);
				}
				else
				{
					((JPanel)parent).add(cb);
				}
				break;
			}
			case RADIO_BUTTON:
			{
				sdData = new SD_RadioButton();
				sdData.parse(bSD);
				JToggleButton tb = new JToggleButton();
				//JRadioButton tb = new JRadioButton();
				tb.setSize((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(iHeight*GLOBAL_CONSTANTS.SCALE));
				tb.setName(Integer.toString(iID));
				tb.setText(sText);
				tb.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE)));
				tb.setVisible(true);
				Component c = cCard.getPanelByXY((Container)parent, (int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE));
				if (c != null)
				{
					ButtonGroup bg = cCard.mGroups.get(c.getName());
					bg.add(tb);
					tb.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE) - c.getX(), (int)(iTop*GLOBAL_CONSTANTS.SCALE) - c.getY()));
					((JPanel)c).add(tb);
				}
				else
				{
					((JPanel)parent).add(tb);
				}
				break;
			}
			case BUTTON:
			{
				JButton jb = new JButton();
				jb.setSize((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(iHeight*GLOBAL_CONSTANTS.SCALE));
				jb.setText(sText);
				jb.setName(Integer.toString(iID));
				jb.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE)));
				jb.setVisible(true);
				Component c = cCard.getPanelByXY((Container)parent, (int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE));
				if (c != null)
				{
					jb.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE) - c.getX(), (int)(iTop*GLOBAL_CONSTANTS.SCALE) - c.getY()));
					((JPanel)c).add(jb);
				}
				else
				{
					((JPanel)parent).add(jb);
				}
				break;
			}
			case TABLE:
			{
				JTable jt = new JTable();
				jt.setName(Integer.toString(iID));
				jt.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				JScrollPane sp = new JScrollPane(jt);
				sp.setSize((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(iHeight*GLOBAL_CONSTANTS.SCALE));
				sp.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE)));
				Component c = cCard.getPanelByXY((Container)parent, (int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE));
				if (c != null)
				{
					sp.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE) - c.getX(), (int)(iTop*GLOBAL_CONSTANTS.SCALE) - c.getY()));
					((JPanel)c).add(sp);
				}
				else
				{
					((JPanel)parent).add(sp);
				}
				break;
			}
			case COLUMN:
			{
				TableColumn tc = new TableColumn();
				tc.setPreferredWidth((int)(iWidth*GLOBAL_CONSTANTS.SCALE));
				tc.setHeaderValue(sText);
				((JTable)parent).addColumn(tc);
				break;
			}
			case IMAGE:
			{
				sdData = new SD_Image();
				sdData.parse(bSD);
				if (!((SD_Image)sdData).isImage)
				{
					break;
				}
				BufferedImage img = null;
				try 
				{
		            InputStream inputStream = new ByteArrayInputStream(((SD_Image)sdData).bImage);
		            img = ImageIO.read(inputStream);
		        } 
				catch (IOException ex) 
				{
		            System.out.println(ex.getMessage());
		        }
				Image scaled = null;
				switch (((SD_Image)sdData).iScale)
				{
					case 1:
					{
						scaled = img.getScaledInstance((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(iHeight*GLOBAL_CONSTANTS.SCALE), java.awt.Image.SCALE_SMOOTH);
						break;
					}
					case 2:
					{
						double iWScale, iHScale;
						iWScale = (iWidth*GLOBAL_CONSTANTS.SCALE) / img.getWidth();
						iHScale = (iHeight*GLOBAL_CONSTANTS.SCALE) / img.getHeight();
						if (iWScale < iHScale)
						{
							scaled = img.getScaledInstance((int)(img.getWidth()*iWScale), (int)(img.getHeight()*iWScale), java.awt.Image.SCALE_SMOOTH);
						}
						else
						{
							scaled = img.getScaledInstance((int)(img.getWidth()*iHScale), (int)(img.getHeight()*iHScale), java.awt.Image.SCALE_SMOOTH);
						}
						break;
					}
					default:
					{
						scaled = img;
						break;
					}
				}
				ImageIcon icon = new ImageIcon(scaled);
				JLabel jl = new JLabel(icon);
				jl.setHorizontalAlignment(JLabel.LEFT);
				jl.setVerticalAlignment(JLabel.TOP);
				if (((SD_Image)sdData).bBorder)
				{
					jl.setBorder(BorderFactory.createLineBorder(Color.black));
				}
				jl.setHorizontalTextPosition(JLabel.RIGHT);
			    jl.setVerticalTextPosition(JLabel.BOTTOM);
				jl.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE)));
				jl.setSize((int)(iWidth*GLOBAL_CONSTANTS.SCALE), (int)(iHeight*GLOBAL_CONSTANTS.SCALE));
				jl.setName(Integer.toString(iID));
				jl.setVisible(true);
				Component c = cCard.getPanelByXY((Container)parent, (int)(iLeft*GLOBAL_CONSTANTS.SCALE), (int)(iTop*GLOBAL_CONSTANTS.SCALE));
				if (c != null)
				{
					jl.setLocation(new Point((int)(iLeft*GLOBAL_CONSTANTS.SCALE) - c.getX(), (int)(iTop*GLOBAL_CONSTANTS.SCALE) - c.getY()));
					((JPanel)c).add(jl);
				}
				else
				{
					((JPanel)parent).add(jl);
				}
				break;
			}
			default:
			{
				break;
			}
		}
	}
	
	@Override
	public String toString()
	{
		return Integer.toString(iID) + " " + sText + " " + fType.toString();
	}

	@Override
	public int compareTo(CardField o) 
	{
		if (this.iPID != o.iPID)
		{
			return ((this.iPID > o.iPID)?1:-1);
		}
		if (this.fType != o.fType)
		{
			if (this.fType == FIELD_TYPES.GROUP_PANE)
			{
				return -1;
			}
			if (o.fType == FIELD_TYPES.GROUP_PANE)
			{
				return 1;
			}
		}
		if (this.iPos != o.iPos)
		{
			return ((this.iPos > o.iPos)?1:-1);
		}
		return 0;
	}
}
