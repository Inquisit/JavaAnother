package globals;

public enum DATA_INTERVALS 
{
	MAIN_FORMULA, ADD_FORMULA, CALC_METHOD, RECALC, DISABLED, INVISIBLE, SEL_FORMULA, SERVICE, MULTILINE, HSCROLL, VSCROLL, IMAGE, BOX_SEL_FORMULA, KEY_FORMULA;
	
	public static final int iSize = 16;
	
	public int getPos()
	{
		switch (this)
		{
			case MAIN_FORMULA:
			{
				return 4;
			}
			case ADD_FORMULA:
			{
				return 4;
			}
			case CALC_METHOD:
			{
				return 4;
			}
			case RECALC:
			{
				return 4;
			}
			case DISABLED:
			{
				return 4;
			}
			case INVISIBLE:
			{
				return 4;
			}
			case SEL_FORMULA:
			{
				return 4;
			}
			case SERVICE:
			{
				return 4;
			}
			case MULTILINE:
			{
				return 4;
			}
			case HSCROLL:
			{
				return 4;
			}
			case VSCROLL:
			{
				return 4;
			}
			case IMAGE:
			{
				return 4;
			}
			case BOX_SEL_FORMULA:
			{
				return 4;
			}
			case KEY_FORMULA:
			{
				return 4;
			}
			default:
			{
				return 0;
			}
		}
	}
}
