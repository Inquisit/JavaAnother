package branchAW;

import java.io.UnsupportedEncodingException;

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
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]);
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isMF = true;
				this.sMainFormula = sSD.substring(iCurPos, iCurPos + iBlockSize);
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
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]);
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isAF = true;
				this.sAddFormula = sSD.substring(iCurPos, iCurPos + iBlockSize);
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
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]);
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isDS = true;
				this.sDisabled = sSD.substring(iCurPos, iCurPos + iBlockSize);
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
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]);
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isIN = true;
				this.sInvisible = sSD.substring(iCurPos, iCurPos + iBlockSize);
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
			iBlockSize = Byte.toUnsignedInt(bSD[iCurPos]);
			iCurPos += 4;
			if (iBlockSize != 0)
			{
				this.isSF = true;
				this.sSelFormula = sSD.substring(iCurPos, iCurPos + iBlockSize);
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
			isIcon = true;
			iCurPos += 542;
			//iCurPos += 62;
			int lngth = bSD.length - iCurPos - 4;
			bIcon = new byte [lngth];
			for (int i = 0; i < lngth; ++i)
			{
				bIcon[i] = bSD[iCurPos + i];
			}
			bIcon[0]=Byte.decode("0x00");
			bIcon[1]=Byte.decode("0x00");
			bIcon[2]=Byte.decode("0x01");
			bIcon[3]=Byte.decode("0x00");
			bIcon[4]=Byte.decode("0x01");
			bIcon[5]=Byte.decode("0x00");
			bIcon[6]=Byte.decode("0x20");
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
			bIcon[21]=Byte.decode("0x00");
		}
	}
}
