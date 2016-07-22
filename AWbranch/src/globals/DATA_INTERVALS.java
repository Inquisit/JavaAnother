package globals;

public enum DATA_INTERVALS 
{
	MAIN_FORMULA, ADD_FORMULA, CALC_METHOD, RECALC, DISABLED, INVISIBLE, SEL_FORMULA, SERVICE;
	
	public static final int iSize = 16;
	
	public int getPos()
	{
		switch (this)
		{
			case MAIN_FORMULA:
			{
				return 16;
			}
			default:
			{
				return 0;
			}
		}
	}
}
