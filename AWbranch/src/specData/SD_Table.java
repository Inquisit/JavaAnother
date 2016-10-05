package specData;

import java.io.UnsupportedEncodingException;

public class SD_Table extends SpecificData 
{
	protected boolean isQF;
	protected StringBuffer sQueryFormula;
	protected boolean isSort;
	protected StringBuffer sSort;
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
		
		bSD.iCurPos += 16;
		
		this.isQF = SD_Methods.sdGetFormula(bSD, sQueryFormula, sSD);
		this.isSF = SD_Methods.sdGetFormula(bSD, sSort, sSD);
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bBind = true;
			iFormID = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos+4]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 5]) * 256;
		}
	}
}
