package specData;

import java.io.UnsupportedEncodingException;

import globals.DATA_INTERVALS;

public class SD_CheckBox extends SpecificData
{
	protected boolean bMultiLine;
	protected boolean bButtonLike;
	protected boolean isIcoUnCh;
	protected byte[] bIcoUnCh;
	protected boolean isIcoChosen;
	protected byte[] bIcoChosen;
	protected boolean isIcoUndef;
	protected byte[] bIcoUndef;
	
	public SD_CheckBox()
	{
		super();
		isIcoChosen = false;
		isIcoUnCh = false;
		isIcoUndef = false;
		bMultiLine = false;
		bButtonLike = false;
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
		else
		{
			bSD.iCurPos += DATA_INTERVALS.SERVICE.getPos();
		}
		
		bSD.iCurPos += 16;
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			isIcoUnCh = true;
			int iIconSize = SD_Methods.sdGetIcoSize(bSD);
			bIcoUnCh = new byte [iIconSize];
			SD_Methods.sdParseIco(bSD, bIcoUnCh);
		}
		else
		{
			bSD.iCurPos += 4;
		}
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			isIcoChosen = true;
			int iIconSize = SD_Methods.sdGetIcoSize(bSD);
			bIcoChosen = new byte [iIconSize];
			SD_Methods.sdParseIco(bSD, bIcoChosen);
		}
		else
		{
			bSD.iCurPos += 4;
		}
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			isIcoUndef = true;
			int iIconSize = SD_Methods.sdGetIcoSize(bSD);
			bIcoUndef = new byte [iIconSize];
			SD_Methods.sdParseIco(bSD, bIcoUndef);
		}
		else
		{
			bSD.iCurPos += 4;
		}
		
		bSD.iCurPos += 12;
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			this.bButtonLike = true;
		}
		
		bSD.iCurPos += DATA_INTERVALS.MULTILINE.getPos();
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			this.bMultiLine = true;
		}
	}
}
