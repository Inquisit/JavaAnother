package branchAW;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import globals.DATA_INTERVALS;

public class SD_TabPanel extends SpecificData
{
	protected boolean isIcon;
	protected byte[] bIcon;
	
	SD_TabPanel()
	{
		super();
		isIcon = false;
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
			int iIcoSize = Byte.toUnsignedInt(bSD[iCurPos]) + Byte.toUnsignedInt(bSD[iCurPos + 1]) * 256;
			isIcon = true;
			iCurPos += 528;
			int iImCount = Byte.toUnsignedInt(bSD[iCurPos]);
			iIcoSize -= 522 + 16 * iImCount;
			bIcon = new byte [iIcoSize];
			bIcon[0]=Byte.decode("0x00");
			bIcon[1]=Byte.decode("0x00");
			bIcon[2]=Byte.decode("0x01");
			bIcon[3]=Byte.decode("0x00");
			bIcon[4]=Byte.decode(Integer.toHexString(iImCount));
			bIcon[5]=Byte.decode("0x00");
			iCurPos += 4;
			int iImSize = 0;
			for (int i = 0; i < iImCount; ++i)
			{
				int iCurImSize = 0;
				bIcon[6 + i * 16] = bSD[iCurPos];/*w*/
				iCurPos += 4;
				bIcon[7 + i * 16] = bSD[iCurPos];/*h*/
				bIcon[8 + i * 16] = 0;/*colors*/
				bIcon[9 + i * 16] = 0;/*reserved*/
				bIcon[10 + i * 16] = 0;/*planes*/
				bIcon[11 + i * 16] = 0;/*planes*/
				bIcon[12 + i * 16] = 0;/*bpp*/
				bIcon[13 + i * 16] = 0;/*bpp*/
				iCurPos += 12;
				bIcon[14 + i * 16] = bSD[iCurPos];/*size*/
				iCurImSize += Byte.toUnsignedInt(bSD[iCurPos]);
				++iCurPos;
				bIcon[15 + i * 16] = bSD[iCurPos];/*size*/
				iCurImSize += Byte.toUnsignedInt(bSD[iCurPos]) * 256;
				++iCurPos;
				bIcon[16 + i * 16] = bSD[iCurPos];/*size*/
				iCurImSize += Byte.toUnsignedInt(bSD[iCurPos]) * 65536;
				++iCurPos;
				bIcon[17 + i * 16] = bSD[iCurPos];/*size*/
				iCurImSize += Byte.toUnsignedInt(bSD[iCurPos]) * 16777216;
				++iCurPos;
				int iOffset = 6 + 16 * iImCount + iImSize;
				byte[] bytes = ByteBuffer.allocate(4).putInt(iOffset).array();
				bIcon[18 + i * 16] = bytes[3];/*offset*/
				bIcon[19 + i * 16] = bytes[2];/*offset*/
				bIcon[20 + i * 16] = bytes[1];/*offset*/
				bIcon[21 + i * 16] = bytes[0];/*offset*/
				iImSize += iCurImSize;
				iCurPos += 12;
			}
			for (int i = 6 + 16 * iImCount; i < iIcoSize; ++i, ++iCurPos)
			{
				bIcon[i] = bSD[iCurPos];
			}
			/*bIcon[6]=Byte.decode("0x20");
			bIcon[7]=Byte.decode("0x20");
			bIcon[8]=Byte.decode("0x00");
			bIcon[9]=Byte.decode("0x00");
			bIcon[10]=Byte.decode("0x01");
			bIcon[11]=Byte.decode("0x00");
			bIcon[12]=Byte.decode("0x20");
			bIcon[13]=Byte.decode("0x00");
			bIcon[14]=Byte.decode("-0x57");
			bIcon[15]=Byte.decode("0x10");
			bIcon[16]=Byte.decode("0x00");
			bIcon[17]=Byte.decode("0x00");
			bIcon[18]=Byte.decode("0x16");
			bIcon[19]=Byte.decode("0x00");
			bIcon[20]=Byte.decode("0x00");
			bIcon[21]=Byte.decode("0x00");*/
		}
	}
}
