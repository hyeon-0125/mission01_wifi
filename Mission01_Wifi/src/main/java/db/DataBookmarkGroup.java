package db;

public class DataBookmarkGroup {
	
	public String GROUP_ID = "";
	public String GROUP_NAME = "";
	public String SEQ = "";
	public String CREATE_DATE = "";
	public String UPDATE_DATE = "";
	
	public DataBookmarkGroup() {
		
	}
	
	public DataBookmarkGroup(String psGroupID, String psGroupName, String psSeq, String psCreateDate, String psUpdateDate) {
		GROUP_ID = psGroupID;
		GROUP_NAME = psGroupName;
		SEQ = psSeq;
		CREATE_DATE = psCreateDate;
		UPDATE_DATE = psUpdateDate;
	}
}
