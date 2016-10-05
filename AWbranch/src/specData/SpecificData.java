package specData;

public abstract class SpecificData 
{
	protected final int iSize = 16;
	protected StringBuffer sMainFormula;
	protected StringBuffer sAddFormula;
	protected boolean bCalcMethod;
	protected boolean bRecalc;
	protected StringBuffer sDisabled;
	protected StringBuffer sInvisible;
	protected StringBuffer sSelFormula;
	protected boolean bService;
	
	protected boolean isMF;
	protected boolean isAF;
	protected boolean isDS;
	protected boolean isIN;
	protected boolean isSF;
	
	SpecificData()
	{
		sMainFormula = new StringBuffer("");
		sAddFormula = new StringBuffer("");
		sSelFormula = new StringBuffer("");
		sDisabled = new StringBuffer("");
		sInvisible = new StringBuffer("");
		bCalcMethod = false;
		bRecalc = false;
		bService = false;
		
		isMF = false;
		isAF = false;
		isDS = false;
		isIN = false;
		isSF = false;
	}
	
	public abstract void parse(SD_Byte bSD);
}