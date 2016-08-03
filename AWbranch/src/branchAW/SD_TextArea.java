package branchAW;

import java.io.UnsupportedEncodingException;

import globals.DATA_INTERVALS;

public class SD_TextArea extends SpecificData 
{
	protected boolean bMultiLine;
	protected boolean bHScroll;
	protected boolean bVScroll;
	
	SD_TextArea()
	{
		super();
		bMultiLine = false;
		bHScroll = false;
		bVScroll = false;
	}
	
	public void parse(byte[] bSD)
	{
		int iCurPos = 0;
		int iBlockSize = 0;
		String sSD;
		try 
		{
			sSD = new String(bSD, "Windows-1251");
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
			return;
		}
		iCurPos = DATA_INTERVALS.iSize; 
		
		if (bSD[iCurPos] != 0)
		{
			iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]) + Byte.toUnsignedInt(bSD[iCurPos + 1]) * 256;
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isMF = true;
				this.sMainFormula = sSD.substring(iCurPos, iCurPos + iBlockSize - 1);
				iCurPos += iBlockSize;
			}
		}
		else
		{
			iCurPos += DATA_INTERVALS.MAIN_FORMULA.getPos();
		}
		
		if (bSD[iCurPos] != 0)
		{
			iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]) + Byte.toUnsignedInt(bSD[iCurPos + 1]) * 256;
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isAF = true;
				this.sAddFormula = sSD.substring(iCurPos, iCurPos + iBlockSize - 1);
				iCurPos += iBlockSize;
			}
		}
		else
		{
			iCurPos += DATA_INTERVALS.ADD_FORMULA.getPos();
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
				this.bRecalc = true;
			}
			iCurPos += 4;
		}
		else
		{
			iCurPos += DATA_INTERVALS.CALC_METHOD.getPos();
		}
		
		if (bSD[iCurPos] != 0)
		{
			iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]) + Byte.toUnsignedInt(bSD[iCurPos + 1]) * 256;
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isDS = true;
				this.sDisabled = sSD.substring(iCurPos, iCurPos + iBlockSize - 1);
				iCurPos += iBlockSize;
			}
		}
		else
		{
			iCurPos += DATA_INTERVALS.DISABLED.getPos();
		}
		
		if (bSD[iCurPos] != 0)
		{
			iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]) + Byte.toUnsignedInt(bSD[iCurPos + 1]) * 256;
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isIN = true;
				this.sInvisible = sSD.substring(iCurPos, iCurPos + iBlockSize - 1);
				iCurPos += iBlockSize;
			}
		}
		else
		{
			iCurPos += DATA_INTERVALS.INVISIBLE.getPos();
		}
		
		if (bSD[iCurPos] != 0)
		{
			iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]) + Byte.toUnsignedInt(bSD[iCurPos + 1]) * 256;
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isSF = true;
				this.sSelFormula = sSD.substring(iCurPos, iCurPos + iBlockSize - 1);
				iCurPos += iBlockSize;
			}
		}
		else
		{
			iCurPos += DATA_INTERVALS.SEL_FORMULA.getPos();
		}
		
		if (bSD[iCurPos] != 0)
		{
			iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]);
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				iCurPos += iBlockSize;
				if (iBlockSize == 4)
				{
					this.bService = true;
				}
			}
		}
		else
		{
			iCurPos += DATA_INTERVALS.SERVICE.getPos();
		}
		
		iCurPos += 16;
		
		if (bSD[iCurPos] != 0)
		{
			this.bMultiLine = true;
		}
		
		iCurPos += DATA_INTERVALS.MULTILINE.getPos();
		
		if (bSD[iCurPos] != 0)
		{
			this.bHScroll = true;
		}
		
		iCurPos += DATA_INTERVALS.HSCROLL.getPos();
		
		if (bSD[iCurPos] != 0)
		{
			this.bVScroll = true;
		}
	}
}
