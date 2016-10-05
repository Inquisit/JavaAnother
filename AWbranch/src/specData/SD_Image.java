package specData;

import java.io.UnsupportedEncodingException;

public class SD_Image extends SpecificData
{
	protected boolean isImage;
	protected byte[] bImage;
	protected int iScale;
	protected boolean bBorder;
	protected boolean bPath;
	protected String sPath;
	
	public SD_Image()
	{
		super();
		isImage = false;
		iScale = 0;
		bBorder = false;
		bPath = false;
	}
	
	public boolean isImage()
	{
		return isImage;
	}
	
	public byte[] getImage()
	{
		return bImage;
	}
	
	public int getScale()
	{
		return iScale;
	}
	
	public boolean isBorder()
	{
		return bBorder;
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
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
			bSD.iCurPos += 4 + iBlockSize;
			int iImSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256 + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 2]) * 65536 + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 3]) * 16777216;
			if (iImSize != 0)
			{
				isImage = true;
				bImage = new byte [iImSize];
				bImage[0] = Byte.decode("0x42");
				bImage[1] = Byte.decode("0x4D");
				bImage[2] = bSD.bSD[bSD.iCurPos];
				++bSD.iCurPos;
				bImage[3] = bSD.bSD[bSD.iCurPos];
				++bSD.iCurPos;
				bImage[4] = bSD.bSD[bSD.iCurPos];
				++bSD.iCurPos;
				bImage[5] = bSD.bSD[bSD.iCurPos];
				++bSD.iCurPos;
				bImage[6] = 0;
				bImage[7] = 0;
				bImage[8] = 0;
				bImage[9] = 0;
				bImage[10] = (byte) (bSD.bSD[bSD.iCurPos] + Byte.decode("0x0E"));
				bImage[11] = 0;
				bImage[12] = 0;
				bImage[13] = 0;
				for (int i = 14; i < iImSize; ++i, ++bSD.iCurPos)
				{
					bImage[i] = bSD.bSD[bSD.iCurPos];
				}
			}
		}
		else
		{
			bSD.iCurPos += 4;
		}
		
		bSD.iCurPos += 14;
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			iScale = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]);
		}
		
		bSD.iCurPos += 4;
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bBorder = true;
		}
		
		bSD.iCurPos += 4;
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bPath = true;
			bSD.iCurPos += 4;
			iBlockSize = bSD.iCurPos;
			while (bSD.bSD[bSD.iCurPos] != 0)
			{
				++bSD.iCurPos;
			}
			sPath = sSD.substring(iBlockSize, bSD.iCurPos);
		}
	}
}
