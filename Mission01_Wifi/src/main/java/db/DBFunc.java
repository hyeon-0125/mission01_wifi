package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBFunc {
	
	static DBConn dbConn = new DBConn();
	
	public static void createTable() throws SQLException {
		// 테이블 생성
		dbConn.createTable("TB_WIFI_INFO", SqlList.strTB_WIFI_INFO);
		dbConn.createTable("TB_LOCATION_HIS", SqlList.strTB_LOCATION_HIS);
		dbConn.createTable("TB_BOOKMARK_GROUP", SqlList.strTB_BOOKMARK_GROUP);
		dbConn.createTable("TB_BOOKMARK_LIST", SqlList.strTB_BOOKMARK_LIST);

		/*
		// PK 추가
		DB.executeSQL(InitSQL.strPK_WIFI_INFO);
		DB.executeSQL(InitSQL.strPK_WIFI_INFO_ALL);
		DB.executeSQL(InitSQL.strPK_LOCATION_HIS);
		DB.executeSQL(InitSQL.strPK_BOOKMARK_GROUP);
		DB.executeSQL(InitSQL.strPK_BOOKMARK_LIST);
		*/
	}
	
	public static int insertBookmarkGroup(String psGroupName, String psSeq) {
		int intInsertDataCnt = 0;
		
		PreparedStatement pState = null;

		try {
			String strSQL = "";
			
			
			
			strSQL = ""
					+ " INSERT INTO TB_BOOKMARK_GROUP (                   "
					+ " 	  GROUP_NAME                                  "
					+ "     , SEQ                                         "
					+ "     , CREATE_DATE                                 "
					+ "     , UPDATE_DATE                                 "
					+ " )                                                 "
					+ " SELECT ? AS GROUP_NAME                            " // 1 GROUP_NAME
					+ "      , ? AS SEQ                                   " // 2 SEQ
					+ "      , DATETIME('now','localtime') AS CREATE_DATE "
					+ "      , DATETIME('now','localtime') AS UPDATE_DATE ";
			
			pState = dbConn.returnStatemenet(strSQL);

			pState.setObject(1, psGroupName);
			pState.setObject(2, psSeq);

			intInsertDataCnt = dbConn.updateData(pState);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return intInsertDataCnt;
	}
	
	public static int updateBookmarkGroup(String psGroupID, String psGroupName, String psSeq) {
		int intInsertDataCnt = 0;
		
		PreparedStatement pState = null;

		try {
			String strSQL = "";
			
			strSQL = ""
					+ " UPDATE TB_BOOKMARK_GROUP                           "
					+ "    SET                                             "
					+ "         GROUP_NAME = ?                             "
					+ "       , SEQ        = ?                             "
					+ "       , UPDATE_DATE = DATETIME('now', 'localtime') "
					+ "  WHERE GROUP_ID = ?                                ";
			
			pState = dbConn.returnStatemenet(strSQL);

			pState.setObject(1, psGroupName);
			pState.setObject(2, psSeq);
			pState.setObject(3, psGroupID);

			intInsertDataCnt = dbConn.updateData(pState);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return intInsertDataCnt;
	}
	
	public static int insertBookmarkList(String psBookmarkID, String psGroupID, String psMngNo) {
		int intInsertDataCnt = 0;
		
		PreparedStatement pState = null;

		try {
			
			String strSQL = ""
					+ " INSERT OR REPLACE INTO TB_BOOKMARK_LIST ( "
					+ "    GROUP_ID                               "
					+ "  , MNG_NO                                 "
					+ "  , CREATE_DATE                            "
					+ " )                                         "
					+ " VALUES (                                  "
					+ "    ?                                      " // 2 GROUP_ID
					+ "  , ?                                      " // 3 MNG_NO
					+ "  , datetime('now','localtime')            "
					+ ")                                          ";
			
			
			pState = dbConn.conn.prepareStatement(strSQL);

			pState.setObject(1, psBookmarkID);
			pState.setObject(2, psGroupID);
			pState.setObject(3, psMngNo);

			intInsertDataCnt = dbConn.updateData(pState);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return intInsertDataCnt;
	}
	
	public static int insertLocationHis(String psLocX, String psLocY) {
		int intInsertDataCnt = 0;
		
		PreparedStatement pState = null;

		try {
			String strSQL = ""
					+ " INSERT OR REPLACE INTO TB_LOCATION_HIS (       "
					+ "    LOC_X                                       "
					+ "  , LOC_Y                                       "
					+ "  , HIS_DATE                                    "
					+ " )                                              "
					+ " SELECT ? AS LOC_X                              " // 1 LOC_X
					+ "      , ? AS LOC_Y                              " // 2 LOC_Y
					+ "      , datetime('now','localtime') AS HIS_DATE ";
			
			pState = dbConn.returnStatemenet(strSQL);

			pState.setObject(1, psLocX);
			pState.setObject(2, psLocY);

			intInsertDataCnt = dbConn.updateData(pState);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return intInsertDataCnt;
	}
	
	public static List<DataBookmarkGroup> searchBookmarkGroup() {
		List<DataBookmarkGroup> lstResult = new ArrayList<>();
		
		PreparedStatement pState = null;
		ResultSet rs = null;
		
		try {
			String strSQL = ""
					+ " SELECT GROUP_ID                                                  "
					+ "      , GROUP_NAME                                                "
					+ "      , SEQ                                                       "
					+ "      , STRFTIME('%Y-%m-%dT%H:%M:%S', CREATE_DATE) AS CREATE_DATE "
					+ "      , STRFTIME('%Y-%m-%dT%H:%M:%S', UPDATE_DATE) AS UPDATE_DATE "
					+ "   FROM TB_BOOKMARK_GROUP                                         "
					+ "  ORDER BY SEQ, GROUP_ID                                          ";
			
			pState = dbConn.returnStatemenet(SqlList.strSelectBookmarkList);
			
			rs = pState.executeQuery();
			
			while (rs.next()) {
				DataBookmarkGroup data = new DataBookmarkGroup();
				
				data.GROUP_ID = rs.getString("GROUP_ID");
				data.GROUP_NAME = rs.getString("GROUP_NAME");
				data.SEQ = rs.getString("SEQ");
				data.CREATE_DATE = rs.getString("CREATE_DATE");
				data.UPDATE_DATE = rs.getString("UPDATE_DATE");
				
				lstResult.add(data);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
            // PreparedStatement 종료
            if( pState != null ) {
                try {
                    pState.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		
		return lstResult;
	}
}
