package specData;

import java.nio.ByteBuffer;

public class SD_Methods 
{
	private final static int iSize = 16;
	
	public static boolean sdGetFormula(SD_Byte bSD, StringBuffer sFormula, String sSD)
	{
		int iBlockSize = 0;
		if (bSD.bSD[bSD.iCurPos] != 0)
		{
			bSD.iCurPos += iSize;
			iBlockSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
			bSD.iCurPos += 4;
			if (iBlockSize != 0)
			{
				sFormula = new StringBuffer(sSD.substring(bSD.iCurPos, bSD.iCurPos + iBlockSize));
				bSD.iCurPos += iBlockSize;
				return true;
			}
			return false;
		}
		else
		{
			bSD.iCurPos += 4;
			return false;
		}
	}

	public static int sdGetIcoSize(SD_Byte bSD)
	{
		int iIcoSize = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) + Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos + 1]) * 256;
		bSD.iCurPos += 528;
		int iImCount = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]);
		iIcoSize -= 522 + 16 * iImCount;
		bSD.iCurPos = new Integer(bSD.iCurPos);
		return iIcoSize;
	}
	
	public static boolean sdParseIco(SD_Byte bSD, byte[] bIcon)
	{
		int iImCount = Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]);
		bIcon[0]=Byte.decode("0x00");
		bIcon[1]=Byte.decode("0x00");
		bIcon[2]=Byte.decode("0x01");
		bIcon[3]=Byte.decode("0x00");
		bIcon[4]=Byte.decode(Integer.toHexString(iImCount));
		bIcon[5]=Byte.decode("0x00");
		bSD.iCurPos += 4;
		int iImSize = 0;
		for (int i = 0; i < iImCount; ++i)
		{
			int iCurImSize = 0;
			bIcon[6 + i * 16] = bSD.bSD[bSD.iCurPos];/*w*/
			bSD.iCurPos += 4;
			bIcon[7 + i * 16] = bSD.bSD[bSD.iCurPos];/*h*/
			bIcon[8 + i * 16] = 0;/*colors*/
			bIcon[9 + i * 16] = 0;/*reserved*/
			bIcon[10 + i * 16] = 0;/*planes*/
			bIcon[11 + i * 16] = 0;/*planes*/
			bIcon[12 + i * 16] = 0;/*bpp*/
			bIcon[13 + i * 16] = 0;/*bpp*/
			bSD.iCurPos += 12;
			bIcon[14 + i * 16] = bSD.bSD[bSD.iCurPos];/*size*/
			iCurImSize += Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]);
			++bSD.iCurPos;
			bIcon[15 + i * 16] = bSD.bSD[bSD.iCurPos];/*size*/
			iCurImSize += Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) * 256;
			++bSD.iCurPos;
			bIcon[16 + i * 16] = bSD.bSD[bSD.iCurPos];/*size*/
			iCurImSize += Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) * 65536;
			++bSD.iCurPos;
			bIcon[17 + i * 16] = bSD.bSD[bSD.iCurPos];/*size*/
			iCurImSize += Byte.toUnsignedInt(bSD.bSD[bSD.iCurPos]) * 16777216;
			++bSD.iCurPos;
			int iOffset = 6 + 16 * iImCount + iImSize;
			byte[] bytes = ByteBuffer.allocate(4).putInt(iOffset).array();
			bIcon[18 + i * 16] = bytes[3];/*offset*/
			bIcon[19 + i * 16] = bytes[2];/*offset*/
			bIcon[20 + i * 16] = bytes[1];/*offset*/
			bIcon[21 + i * 16] = bytes[0];/*offset*/
			iImSize += iCurImSize;
			bSD.iCurPos += 12;
		}
		for (int i = 6 + 16 * iImCount; i < bIcon.length; ++i, ++bSD.iCurPos)
		{
			bIcon[i] = bSD.bSD[bSD.iCurPos];
		}
		return true;
	}
	
}
