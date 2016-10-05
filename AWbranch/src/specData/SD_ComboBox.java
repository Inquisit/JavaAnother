package specData;

import java.io.UnsupportedEncodingException;

public class SD_ComboBox extends SpecificData 
{
	protected boolean isBSF;
	protected StringBuffer sBoxSelFormula;
	protected boolean isKF;
	protected StringBuffer sKeyFormula;
	protected int iBoxType;
	protected String sUnique;
	protected String sTableName;
	protected String sColumnName;
	protected String sQuery;
	protected String sAddKey;
	protected int iSortType;
	protected boolean isWithCode;
	
	public SD_ComboBox()
	{
		super();
		isBSF = false;
		isKF = false;
		isWithCode = false;
		iBoxType = 0;
		iSortType = 0;
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
		
		this.isBSF = SD_Methods.sdGetFormula(bSD, sBoxSelFormula, sSD);
		this.isKF = SD_Methods.sdGetFormula(bSD, sKeyFormula, sSD);
		
		iSortType = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos+1416]);
		iBoxType = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos+1420]);
		isWithCode = (Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos+1556])==0);
		
		switch (iBoxType)
		{
			case 0:
			{
				sUnique = sSD.substring(bSD.iCurPos, bSD.iCurPos + 128).trim();
				break;
			}
			case 1:
			{
				bSD.iCurPos += 136;
				sTableName = sSD.substring(bSD.iCurPos, bSD.iCurPos + 128).trim();
				bSD.iCurPos += 129;
				sColumnName = sSD.substring(bSD.iCurPos, bSD.iCurPos + 128).trim();
				break;
			}
			case 2:
			{
				bSD.iCurPos += 394;
				sQuery = sSD.substring(bSD.iCurPos, bSD.iCurPos + 1020).trim();
				break;
			}
			case 3:
			{
				break;
			}
			case 4:
			{
				sUnique = sSD.substring(bSD.iCurPos, bSD.iCurPos + 128).trim();
				bSD.iCurPos += 1424;
				sAddKey = sSD.substring(bSD.iCurPos, bSD.iCurPos + 128).trim();
				break;
			}
			default:
			{
				break;
			}
		}
	}
}