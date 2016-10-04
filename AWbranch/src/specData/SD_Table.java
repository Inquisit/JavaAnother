package specData;

import java.io.UnsupportedEncodingException;

import globals.DATA_INTERVALS;

public class SD_Table extends SpecificData 
{
	protected boolean isQF;
	protected String sQueryFormula;
	protected boolean isSort;
	protected String sSort;
	protected boolean bBind;
	protected int iFormID;
	
	public SD_Table()
	{
		super();
		isQF = false;
	}
	
	public void parse(SD_Byte bSD)
	{
		int iBlockSize = 0;
		String sSD;
		try 
		{
			sSD = new String(bSD.bSD, "Windows-1251");
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
			return;
		}
		bSD.iCurPos = DATA_INTERVALS.iSize; 
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
			bSD.iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isMF = true;
				this.sMainFormula = sSD.substring(bSD.iCurPos, bSD.iCurPos + iBlockSize);
				bSD.iCurPos += iBlockSize;
			}
		}
		else
		{
			bSD.iCurPos += DATA_INTERVALS.MAIN_FORMULA.getPos();
		}
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
			bSD.iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isAF = true;
				this.sAddFormula = sSD.substring(bSD.iCurPos, bSD.iCurPos + iBlockSize);
				bSD.iCurPos += iBlockSize;
			}
		}
		else
		{
			bSD.iCurPos += DATA_INTERVALS.ADD_FORMULA.getPos();
		}
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += DATA_INTERVALS.iSize-1;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]);
			if (iBlockSize != 0)
			{
				this.bCalcMethod = true;
			}
			bSD.iCurPos += DATA_INTERVALS.RECALC.getPos();
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]);
			if (iBlockSize != 0)
			{
				this.bRecalc = true;
			}
			bSD.iCurPos += 4;
		}
		else
		{
			bSD.iCurPos += DATA_INTERVALS.CALC_METHOD.getPos();
		}
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
			bSD.iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isDS = true;
				this.sDisabled = sSD.substring(bSD.iCurPos, bSD.iCurPos + iBlockSize);
				bSD.iCurPos += iBlockSize;
			}
		}
		else
		{
			bSD.iCurPos += DATA_INTERVALS.DISABLED.getPos();
		}
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
			bSD.iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isIN = true;
				this.sInvisible = sSD.substring(bSD.iCurPos, bSD.iCurPos + iBlockSize);
				bSD.iCurPos += iBlockSize;
			}
		}
		else
		{
			bSD.iCurPos += DATA_INTERVALS.INVISIBLE.getPos();
		}
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
			bSD.iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isSF = true;
				this.sSelFormula = sSD.substring(bSD.iCurPos, bSD.iCurPos + iBlockSize);
				bSD.iCurPos += iBlockSize;
			}
		}
		else
		{
			bSD.iCurPos += DATA_INTERVALS.SEL_FORMULA.getPos();
		}
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]);
			bSD.iCurPos += 4;
			if (iBlockSize != 0)
			{
				bSD.iCurPos += iBlockSize;
				if (iBlockSize == 4)
				{
					this.bService = true;
				}
			}
		}
		
		bSD.iCurPos += 16;
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
			bSD.iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isQF = true;
				this.sQueryFormula = sSD.substring(bSD.iCurPos, bSD.iCurPos + iBlockSize);
				bSD.iCurPos += iBlockSize;
			}
		}
		else
		{
			bSD.iCurPos += DATA_INTERVALS.QUERY_FORMULA.getPos();
		}
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
			bSD.iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isSF = true;
				this.sSort = sSD.substring(bSD.iCurPos, bSD.iCurPos + iBlockSize);
				bSD.iCurPos += iBlockSize;
			}
		}
		else
		{
			bSD.iCurPos += DATA_INTERVALS.SORT_FORMULA.getPos();
		}
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bBind = true;
			iFormID = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos+4]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 5]) * 256;
		}
	}
}
