package specData;

public abstract class SpecificData 
{
	protected String sMainFormula;
	protected String sAddFormula;
	protected boolean bCalcMethod;
	protected boolean bRecalc;
	protected String sDisabled;
	protected String sInvisible;
	protected String sSelFormula;
	protected boolean bService;
	
	protected boolean isMF;
	protected boolean isAF;
	protected boolean isDS;
	protected boolean isIN;
	protected boolean isSF;
	
	SpecificData()
	{
		sMainFormula = "";
		sAddFormula = "";
		sSelFormula = "";
		sDisabled = "";
		sInvisible = "";
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