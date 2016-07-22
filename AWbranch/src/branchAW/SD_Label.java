package branchAW;

import globals.GLOBAL_CONSTANTS;
import globals.DATA_INTERVALS;

public class SD_Label extends SpecificData 
{
	SD_Label()
	{
		super();
	}
	
	public void parse(byte[] bSD)
	{
		int iCurPos = 0;
		if (bSD.length <= GLOBAL_CONSTANTS.MIN_SD_SIZE)
		{
			return;
		}
		iCurPos = DATA_INTERVALS.MAIN_FORMULA.getPos(); 
		if (bSD[iCurPos] != 0)
		{
			
		}
	}
}
