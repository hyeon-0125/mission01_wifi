package db;

public class DataBookmarkList {
	
	public String BOOKMARK_ID = "";
	public String GROUP_ID = "";
	public String MNG_NO = "";
	public String CREATE_DATE = "";
	
	public DataBookmarkList(String psBookmarkID, String psGroupID, String psMngNo, String psCreateDate) {
		BOOKMARK_ID = psBookmarkID;
		GROUP_ID = psGroupID;
		MNG_NO = psMngNo;
		CREATE_DATE = psCreateDate;
	}
}
