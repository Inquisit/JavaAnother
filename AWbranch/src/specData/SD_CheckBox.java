package specData;

import java.io.UnsupportedEncodingException;

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
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += iSize;
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
			bSD.iCurPos += 4;
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
		
		bSD.iCurPos += 4;
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			this.bMultiLine = true;
		}
	}
}
