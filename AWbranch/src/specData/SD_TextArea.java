package specData;

import java.io.UnsupportedEncodingException;

public class SD_TextArea extends SpecificData 
{
	protected boolean bMultiLine;
	protected boolean bHScroll;
	protected boolean bVScroll;
	
	public SD_TextArea()
	{
		super();
		bMultiLine = false;
		bHScroll = false;
		bVScroll = false;
	}
	
	public void parse(SD_Byte bSD)
	{
		int iCurPos = 0;
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
		bSD.iCurPos = iSize; 
		
		this.isMF = SD_Methods.sdGetFormula(bSD, sMainFormula, sSD);		
		this.isAF = SD_Methods.sdGetFormula(bSD, sAddFormula, sSD);
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += iSize-1;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]);
			if (iBlockSize != 0)
			{
				this.bCalcMethod = true;
			}
			bSD.iCurPos += 4;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]);
			if (iBlockSize != 0)
			{
				this.bRecalc = true;
			}
			bSD.iCurPos += 4;
		}
		else
		{
			bSD.iCurPos += 4;
		}
		
		
		this.isDS = SD_Methods.sdGetFormula(bSD, sDisabled, sSD);
		this.isIN = SD_Methods.sdGetFormula(bSD, sInvisible, sSD);
		this.isSF = SD_Methods.sdGetFormula(bSD, sSelFormula, sSD);
		
		if (bSD.bSD[iCurPos] != 0)
		{
			iCurPos += iSize;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[iCurPos]);
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
			iCurPos += 4;
		}
		
		iCurPos += 16;
		
		if (bSD.bSD[iCurPos] != 0)
		{
			this.bMultiLine = true;
		}
		
		iCurPos += 4;
		
		if (bSD.bSD[iCurPos] != 0)
		{
			this.bHScroll = true;
		}
		
		iCurPos += 4;
		
		if (bSD.bSD[iCurPos] != 0)
		{
			this.bVScroll = true;
		}
	}
}
