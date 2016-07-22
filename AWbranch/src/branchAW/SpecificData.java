package branchAW;

public abstract class SpecificData 
{
	String sMainFormula;
	String sAddFormula;
	boolean bCalcMethod;
	boolean bRecalc;
	String sDisabled;
	String sInvisible;
	String sSelFormula;
	boolean bService;
	
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
	}
	
	public abstract void parse(byte[] bSD);
}