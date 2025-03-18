package db;

public class DataLocationHis {
	public String LOC_ID = "";
	public String LOC_X = "";
	public String LOC_Y = "";
	public String HIS_DATE = "";
	
	public DataLocationHis (String psLocID, String psLocX, String psLocY, String psHisDate) {
		LOC_ID = psLocID;
		LOC_X = psLocX;
		LOC_Y = psLocY;
		HIS_DATE = psHisDate;
	}
}
