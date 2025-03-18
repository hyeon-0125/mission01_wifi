package api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import db.DBConn;
import db.SqlList;

public class WifiAPI {

	private String API_FRONT_ADDRESS = "http://openapi.seoul.go.kr:8088/";
	private String API_KEY = "744b6753516c737334344a69476a4a";
	private String API_BACK_ADDRESS = "/xml/TbPublicWifiInfo/";

	private String API_ADDRESS = API_FRONT_ADDRESS + API_KEY + API_BACK_ADDRESS;

	static DBConn dbConn = new DBConn();
	
	public WifiAPI() {
		
	}

	// tag값의 정보를 가져오는 함수
	public static String getTagValue(String tag, Element eElement) {

		// 결과를 저장할 result 변수 선언
		String result = "";
		
		try {
			NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
			
			if (nlList != null) {
				result = nlList.item(0).getTextContent();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		

		return result;
	}

	// tag값의 정보를 가져오는 함수
	public static String getTagValue(String tag, String childTag, Element eElement) {

		// 결과를 저장할 result 변수 선언
		String result = "";

		try {
			NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();

			for (int i = 0; i < eElement.getElementsByTagName(childTag).getLength(); i++) {

				// result += nlList.item(i).getFirstChild().getTextContent() + " ";
				result += nlList.item(i).getChildNodes().item(0).getTextContent() + " ";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int getWifiInfo() {

		int intTotalDataCnt = 0;
		PreparedStatement pState = null;
		
		try {

			int intListCnt = 0;
			int intPreStartIndex = 1;
			int intInsertDataCnt = 1;

			Document documentInfo = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(API_ADDRESS + "1/2");

			documentInfo.getDocumentElement().normalize();

			NodeList nList = documentInfo.getElementsByTagName("TbPublicWifiInfo");

			Node nNode = nList.item(0);

			if (nNode != null) {
				Element eElement = (Element) nNode;
				intListCnt = Integer.parseInt(getTagValue("list_total_count", eElement));
				System.out.println("list cnt : " + intListCnt);
			}

			while (true) {
				try {
					String url = API_ADDRESS + intPreStartIndex + "/" + (intPreStartIndex + 999) + "/";
					
					documentInfo = DocumentBuilderFactory.newInstance().newDocumentBuilder()
							.parse(url);

					documentInfo.getDocumentElement().normalize();

					nList = documentInfo.getElementsByTagName("row");
					
					for (int index = 0; index < nList.getLength(); index++) {
						nNode = nList.item(index);
						
						if (nNode != null) {
							Element eElement = (Element) nNode;

							String strMAG_NO = getTagValue("X_SWIFI_MGR_NO", eElement);
							String strCITY_GU = getTagValue("X_SWIFI_WRDOFC", eElement);
							String strWIFI_NAME = getTagValue("X_SWIFI_MAIN_NM", eElement);
							String strADDRESS_ROAD = getTagValue("X_SWIFI_ADRES1", eElement);
							String strADDRESS_DETAIL = getTagValue("X_SWIFI_ADRES2", eElement);
							String strINSTALL_LOC = getTagValue("X_SWIFI_INSTL_FLOOR", eElement);
							String strINSTALL_TYPE = getTagValue("X_SWIFI_INSTL_TY", eElement);
							String strINSTALL_ORGAN = getTagValue("X_SWIFI_INSTL_MBY", eElement);
							String strSERVICE_GBN = getTagValue("X_SWIFI_SVC_SE", eElement);
							String strNET_TYPE = getTagValue("X_SWIFI_CMCWR", eElement);
							String strINSTALL_YEAR = getTagValue("X_SWIFI_CNSTC_YEAR", eElement);
							String strINOUT_GBN = getTagValue("X_SWIFI_INOUT_DOOR", eElement);
							String strWIFI_CONN_ENV = getTagValue("X_SWIFI_REMARS3", eElement);
							String strLOC_X = getTagValue("LAT", eElement);
							String strLOC_Y = getTagValue("LNT", eElement);
							String strWORK_DATE = getTagValue("WORK_DTTM", eElement);

							String strSQL = "" 
									+ " INSERT OR REPLACE INTO TB_WIFI_INFO ( " 
									+ " 	MNG_NO                 "
									+ "   , CITY_GU                " 
									+ "   , WIFI_NAME              "
									+ "   , ADDRESS_ROAD           " 
									+ "   , ADDRESS_DETAIL         "
									+ "   , INSTALL_LOC            " 
									+ "   , INSTALL_TYPE           "
									+ "   , INSTALL_ORGAN          " 
									+ "   , SERVICE_GBN            "
									+ "   , NET_TYPE               " 
									+ "   , INSTALL_YEAR           "
									+ "   , INOUT_GBN              " 
									+ "   , WIFI_CONN_ENV          "
									+ "   , LOC_X         	       " 
									+ "   , LOC_Y                  "
									+ "   , WORK_DATE              " 
									+ " )                          "
									+ " VALUES (                   " 
									+ " 	?                      " // 1 MNG_NO
									+ "   , ?                      " // 2 CITY_GU
									+ "   , ?                      " // 3 WIFI_NAME
									+ "   , ?                      " // 4 ADDRESS_ROAD
									+ "   , ?                      " // 5 ADDRESS_DETAIL
									+ "   , ?                      " // 6 INSTALL_LOC
									+ "   , ?                      " // 7 INSTALL_TYPE
									+ "   , ?                      " // 8 INSTALL_ORGAN
									+ "   , ?                      " // 9 SERVICE_GBN
									+ "   , ?                      " // 10 NET_TYPE
									+ "   , ?                      " // 11 INSTALL_YEAR
									+ "   , ?                      " // 12 INOUT_GBN
									+ "   , ?                      " // 13 WIFI_CONN_ENV
									+ "   , ?                      " // 14 LOC_X
									+ "   , ?                      " // 15 LOC_Y
									+ "   , ?                      " // 16 WORK_DATE
									+ " )                          ";

							pState = dbConn.returnStatemenet(strSQL);
							
							pState.setObject(1, strMAG_NO);
							pState.setObject(2, strCITY_GU);
							pState.setObject(3, strWIFI_NAME);
							pState.setObject(4, strADDRESS_ROAD);
							pState.setObject(5, strADDRESS_DETAIL);
							pState.setObject(6, strINSTALL_LOC);
							pState.setObject(7, strINSTALL_TYPE);
							pState.setObject(8, strINSTALL_ORGAN);
							pState.setObject(9, strSERVICE_GBN);
							pState.setObject(10, strNET_TYPE);
							pState.setObject(11, strINSTALL_YEAR);
							pState.setObject(12, strINOUT_GBN);
							pState.setObject(13, strWIFI_CONN_ENV);
							pState.setObject(14, strLOC_X);
							pState.setObject(15, strLOC_Y);
							pState.setObject(16, strWORK_DATE);

							intInsertDataCnt = dbConn.updateData(pState);

							System.out.println("Insert " + strMAG_NO + " " + intInsertDataCnt);
						}
					}

					intPreStartIndex += 1000;

					if (intPreStartIndex > intListCnt) {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			String strSQL = " SELECT COUNT(*) DATA_CNT FROM TB_WIFI_INFO ";
			pState = dbConn.returnStatemenet(strSQL);
			
			ResultSet rs = pState.executeQuery();
			
			while(rs.next()) {
				intTotalDataCnt = Integer.parseInt(rs.getString("DATA_CNT"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			 try {
	                // PreparedStatement 종료
	                if( pState != null ) {
	                	pState.close();
	                }
	                
	                // Database 연결 종료
	                dbConn.closeConn();
	                
	            } catch ( SQLException e ) {
	                e.printStackTrace();
	            }
		}
		
		return intTotalDataCnt;
	}

}
