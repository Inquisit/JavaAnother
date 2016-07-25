package globals;

public enum DATA_INTERVALS 
{
	MAIN_FORMULA, ADD_FORMULA, CALC_METHOD, RECALC, DISABLED, INVISIBLE, SEL_FORMULA, SERVICE;
	
	public static final int iSize = 16;
	public static final int MIN_SD_SIZE = 72;
	
	public int getPos()
	{
		switch (this)
		{
			case MAIN_FORMULA:
			{
				return 16;
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
			default:
			{
				return 0;
			}
		}
	}
}
