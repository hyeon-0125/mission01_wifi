package db;

public class DataWifiInfo {
	public String MAG_NO = "";
	public String CITY_GU = "";
	public String WIFI_NAME = "";
	public String ADDRESS_ROAD = "";
	public String ADDRESS_DETAIL = "";
	public String INSTALL_LOC = "";
	public String INSTALL_TYPE = "";
	public String SERVICE_GBN = "";
	public String NET_TYPE = "";
	public String INSTALL_YEAR = "";
	public String INOUT_GBN = "";
	public String WIFI_CONN_ENV = "";
	public String LOC_X = "";
	public String LOC_Y = "";
	public String WORK_DATE = "";
	
	public DataWifiInfo(String psMagNo
				      , String psCityGu
				      , String psWifiName
				      , String psAddressRoad
				      , String psAddressDetail
				      , String psInstallLoc
				      , String psInstallType
				      , String psServiceGbn
				      , String psNetType
				      , String psInstallYear
				      , String psInoutGbn
				      , String psWifiConnEnv
				      , String psLocX
				      , String psLocY
				      , String psWorkDate) {
		
		MAG_NO = psMagNo;
		CITY_GU = psCityGu;
		WIFI_NAME = psWifiName;
		ADDRESS_ROAD = psAddressRoad;
		ADDRESS_DETAIL = psAddressDetail;
		INSTALL_LOC = psInstallLoc;
		INSTALL_TYPE = psInstallType;
		SERVICE_GBN = psServiceGbn;
		NET_TYPE = psNetType;
		INSTALL_YEAR = psInstallYear;
		INOUT_GBN = psInoutGbn;
		WIFI_CONN_ENV = psWifiConnEnv;
		LOC_X = psLocX;
		LOC_Y = psLocY;
		WORK_DATE = psWorkDate;
	}
}
