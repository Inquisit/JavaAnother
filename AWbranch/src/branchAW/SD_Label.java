package branchAW;

import java.io.UnsupportedEncodingException;

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
		int iBlockSize = 0;
		String sSD;
		if (bSD.length <= DATA_INTERVALS.MIN_SD_SIZE)
		{
			return;
		}
		try 
		{
			sSD = new String(bSD, "Windows-1251");
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
			return;
		}
		System.out.println(sSD);
		iCurPos = DATA_INTERVALS.MAIN_FORMULA.getPos(); 
		
		if (bSD[iCurPos] != 0)
		{
			iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]);
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isMF = true;
				System.out.println(iCurPos);
				System.out.println(iBlockSize);
				this.sMainFormula = sSD.substring(iCurPos, iBlockSize + 36);
				iCurPos += iBlockSize;
				System.out.println(this.sMainFormula);
			}
		}
		else
		{
			iCurPos += DATA_INTERVALS.ADD_FORMULA.getPos();;
		}
		
		if (bSD[iCurPos] != 0)
		{
			iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]);
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isAF = true;
				System.out.println(iCurPos);
				System.out.println(iBlockSize);
				this.sAddFormula = sSD.substring(iCurPos, iBlockSize);
				iCurPos += iBlockSize;
				System.out.println(this.sAddFormula);
			}
		}
		else
		{
			iCurPos += DATA_INTERVALS.CALC_METHOD.getPos();;
		}
		
		if (bSD[iCurPos] != 0)
		{
			iCurPos += DATA_INTERVALS.iSize-1;
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]);
			if (iBlockSize != 0)
			{
				this.bCalcMethod = true;
			}
			iCurPos += DATA_INTERVALS.RECALC.getPos();
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]);
			if (iBlockSize != 0)
			{
				this.bCalcMethod = true;
			}
		}
		else
		{
			iCurPos += DATA_INTERVALS.DISABLED.getPos();;
		}
	}
}
