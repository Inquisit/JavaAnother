package globals;

public enum FIELD_TYPES 
{
	TABBED_PANE, TAB_PANEL, LABEL, TEXT_AREA, GROUP_PANE, COMBO_BOX, CHECK_BOX, RADIO_BUTTON, BUTTON, TABLE, COLUMN, IMAGE, NONE;
	
	public static FIELD_TYPES getType(int iType)
	{
		switch (iType)
		{
			case 0:
			{
				return TABBED_PANE;
			}
			case 1:
			{
				return TAB_PANEL;
			}
			case 2:
			{
				return LABEL;
			}
			case 3:
			{
				return TEXT_AREA;
			}
			case 4:
			{
				return GROUP_PANE;
			}
			case 5:
			{
				return COMBO_BOX;
			}
			case 7:
			{
				return CHECK_BOX;
			}
			case 8:
			{
				return RADIO_BUTTON;
			}
			case 9:
			{
				return BUTTON;
			}
			case 10:
			{
				return TABLE;
			}
			case 11:
			{
				return COLUMN;
			}
			case 12:
			{
				return IMAGE;
			}
			default:
			{
				return NONE;
			}
		}
	}
	
	@Override
	public String toString()
	{
		switch (this)
		{
			case TABBED_PANE:
			{
				return "TABBED_PANE";
			}
			case TAB_PANEL:
			{
				return "TAB_PANEL";
			}
			case LABEL:
			{
				return "LABEL";
			}
			case TEXT_AREA:
			{
				return "TEXT_AREA";
			}
			case GROUP_PANE:
			{
				return "GROUP_PANE";
			}
			case COMBO_BOX:
			{
				return "COMBO_BOX";
			}
			case CHECK_BOX:
			{
				return "CHECK_BOX";
			}
			case RADIO_BUTTON:
			{
				return "RADIO_BUTTON";
			}
			case BUTTON:
			{
				return "BUTTON";
			}
			case TABLE:
			{
				return "TABLE";
			}
			case COLUMN:
			{
				return "COLUMN";
			}
			case IMAGE:
			{
				return "IMAGE";
			}
			default:
			{
				return "NONE";
			}
		}
	}
}