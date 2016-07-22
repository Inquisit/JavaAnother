package branchAW;

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
	protected boolean isCM;	
	protected boolean isRC;
	protected boolean isDS;
	protected boolean isIN;
	protected boolean isSF;
	protected boolean isSR;
	
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
		isCM = false;	
		isRC = false;
		isDS = false;
		isIN = false;
		isSF = false;
		isSR = false;
	}
	
	public abstract void parse(byte[] bSD);
}