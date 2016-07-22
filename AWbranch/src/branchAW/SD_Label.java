package branchAW;

import globals.GLOBAL_CONSTANTS;

public class SD_Label extends SpecificData 
{
	SD_Label()
	{
		super();
	}
	
	public void parse(byte[] bSD)
	{
		if (bSD.length <= GLOBAL_CONSTANTS.MIN_SD_SIZE)
		{
			return;
		}
		
	}
}
