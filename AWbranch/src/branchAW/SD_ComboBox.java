package branchAW;

import java.io.UnsupportedEncodingException;

import globals.DATA_INTERVALS;

public class SD_ComboBox extends SpecificData 
{
	protected boolean isBSF;
	protected String sBoxSelFormula;
	protected boolean isKF;
	protected String sKeyFormula;
	protected int iBoxType;
	protected String sUnique;
	protected String sTableName;
	protected String sColumnName;
	protected String sQuery;
	protected String sAddKey;
	protected int iSortType;
	protected boolean isWithCode;
	
	SD_ComboBox()
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
			bSD.iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
			bSD.iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isBSF = true;
				this.sBoxSelFormula = sSD.substring(bSD.iCurPos, bSD.iCurPos + iBlockSize);
				bSD.iCurPos += iBlockSize;
			}
		}
		else
		{
			bSD.iCurPos += DATA_INTERVALS.BOX_SEL_FORMULA.getPos();
		}
		
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += DATA_INTERVALS.iSize;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
			bSD.iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isKF = true;
				this.sKeyFormula = sSD.substring(bSD.iCurPos, bSD.iCurPos + iBlockSize);
				bSD.iCurPos += iBlockSize;
			}
		}
		else
		{
			bSD.iCurPos += DATA_INTERVALS.KEY_FORMULA.getPos();
		}
		
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