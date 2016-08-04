package branchAW;

import java.io.UnsupportedEncodingException;

import globals.DATA_INTERVALS;

public class SD_Image extends SpecificData
{
	protected boolean isImage;
	protected byte[] bImage;
	protected int iScale;
	protected boolean bBorder;
	protected boolean bPath;
	protected String sPath;
	
	SD_Image()
	{
		super();
		isImage = false;
		iScale = 0;
		bBorder = false;
		bPath = false;
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
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]) + Byte.toUnsignedInt(bSD[iCurPos + 1]) * 256;
			isImage = true;
			iCurPos += 4 + iBlockSize;
			int iImSize = Byte.toUnsignedInt(bSD[iCurPos]) + Byte.toUnsignedInt(bSD[iCurPos + 1]) * 256 + Byte.toUnsignedInt(bSD[iCurPos + 2]) * 65536 + Byte.toUnsignedInt(bSD[iCurPos + 3]) * 16777216;
			bImage = new byte [iImSize];
			bImage[0] = Byte.decode("0x42");
			bImage[1] = Byte.decode("0x4D");
			bImage[2] = bSD[iCurPos];
			++iCurPos;
			bImage[3] = bSD[iCurPos];
			++iCurPos;
			bImage[4] = bSD[iCurPos];
			++iCurPos;
			bImage[5] = bSD[iCurPos];
			++iCurPos;
			bImage[6] = 0;
			bImage[7] = 0;
			bImage[8] = 0;
			bImage[9] = 0;
			bImage[10] = (byte) (bSD[iCurPos] + Byte.decode("0x0E"));
			bImage[11] = 0;
			bImage[12] = 0;
			bImage[13] = 0;
			for (int i = 14; i < iImSize; ++i, ++iCurPos)
			{
				bImage[i] = bSD[iCurPos];
			}
		}
		else
		{
			iCurPos += DATA_INTERVALS.IMAGE.getPos();
		}
		
		iCurPos += 14;
		if (bSD[iCurPos] != 0)
		{
			iScale = Byte.toUnsignedInt(bSD[iCurPos]);
		}
		
		iCurPos += 4;
		if (bSD[iCurPos] != 0)
		{
			bBorder = true;
		}
		
		iCurPos += 4;
		if (bSD[iCurPos] != 0)
		{
			bPath = true;
			iCurPos += 4;
			iBlockSize = iCurPos;
			while (bSD[iCurPos] != 0)
			{
				++iCurPos;
			}
			sPath = sSD.substring(iBlockSize, iCurPos);
		}
	}
}
