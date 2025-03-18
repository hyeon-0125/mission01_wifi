package db;

public class SqlList {

	/*
	 * -------테이블 생성 쿼리-------
	 */

	// [테이블 생성 쿼리] 와이파이 정보
	public static String strTB_WIFI_INFO = ""
			+ " CREATE TABLE IF NOT EXISTS TB_WIFI_INFO (              "
			+ " 	MNG_NO          VARCHAR(10)  NOT NULL PRIMARY KEY  " // 관리번호
			+ "   , CITY_GU         VARCHAR(20)  NULL                  " // 자치구
			+ "   , WIFI_NAME       VARCHAR(50)  NULL                  " // 와이파이명
			+ "   , ADDRESS_ROAD    VARCHAR(300) NULL                  " // 도로명주소
			+ "   , ADDRESS_DETAIL  VARCHAR(300) NULL                  " // 상세주소
			+ "   , INSTALL_LOC     INTEGER      NULL                  " // 설치위치(층)
			+ "   , INSTALL_TYPE    VARCHAR(100) NULL                  " // 설치유형
			+ "   , INSTALL_ORGAN   VARCHAR(30)  NULL                  " // 설치기관
			+ "   , SERVICE_GBN     VARCHAR(20)  NULL                  " // 서비스구분
			+ "   , NET_TYPE        VARCHAR(20)  NULL                  " // 망종류
			+ "   , INSTALL_YEAR    VARCHAR(4)   NULL                  " // 설치년도
			+ "   , INOUT_GBN       VARCHAR(5)   NULL                  " // 실내외구분
			+ "   , WIFI_CONN_ENV   VARCHAR(50)  NULL                  " // WIFI접속환경
			+ "   , LOC_X           INTEGER      NULL                  " // X좌표
			+ "   , LOC_Y           INTEGER      NULL                  " // Y좌표
			+ "   , WORK_DATE       DATE         NULL                  " // 작업일자
			+ " )                                                      ";

	
	// [테이블 생성 쿼리] 위치 히스토리
	public static String strTB_LOCATION_HIS = ""
			+ " CREATE TABLE IF NOT EXISTS TB_LOCATION_HIS (                   "
			+ "        LOC_ID    INTEGER    NOT NULL PRIMARY KEY AUTOINCREMENT " // 위치 ID
			+ "      , LOC_X     INTEGER    NOT NULL                           " // X 좌표
			+ "      , LOC_Y     INTEGER    NULL                               " // Y 좌표
			+ "      , HIS_DATE  DATE       NULL                               " // 조회일자
			+ " )                                                              ";
	
	// [테이블 생성 쿼리] 북마크 그룹
	public static String strTB_BOOKMARK_GROUP = ""
			+ " CREATE TABLE IF NOT EXISTS TB_BOOKMARK_GROUP (                      "
			+ "        GROUP_ID     INTEGER      NOT NULL PRIMARY KEY AUTOINCREMENT " // 북마크 그룹 ID
			+ "      , GROUP_NAME   VARCHAR(100) NULL                               " // 북마크 그룹 이름
			+ "      , SEQ          INTEGER      NULL                               " // 순서
			+ "      , CREATE_DATE  DATE         NULL                               " // 등록일자
			+ "      , UPDATE_DATE  DATE         NULL                               " // 수정일자
			+ " )                                                                   ";
	
	// [테이블 생성 쿼리] 북마크 목록
	public static String strTB_BOOKMARK_LIST = ""
			+ " CREATE TABLE IF NOT EXISTS TB_BOOKMARK_LIST (                     "
			+ "        BOOKMARK_ID  INTEGER    NOT NULL PRIMARY KEY AUTOINCREMENT " // 북마크 ID
			+ "      , GROUP_ID     INTEGER    NULL                               " // 북마크 그룹 ID
			+ "      , CREATE_DATE  DATE       NULL                               " // 등록일자
			+ " )                                                                 ";

	
	// [조회 쿼리] 북마크 목록 조회
	public static String strSelectBookmarkList = ""
			+ " SELECT GROUP_ID                                                  "
			+ "      , GROUP_NAME                                                "
			+ "      , SEQ                                                       "
			+ "      , STRFTIME('%Y-%m-%dT%H:%M:%S', CREATE_DATE) AS CREATE_DATE "
			+ "      , STRFTIME('%Y-%m-%dT%H:%M:%S', UPDATE_DATE) AS UPDATE_DATE "
			+ "   FROM TB_BOOKMARK_GROUP                                         "
			+ "  ORDER BY SEQ, GROUP_ID                                          ";
	/*
	 * -------PK 생성 쿼리-------
	 */
	
	// [PK 생성 쿼리] 와이파이 정보
	public static String strPK_WIFI_INFO = ""
			+ " ALTER TABLE TB_WIFI_INFO        "
	        + " 	ADD CONSTRAINT PK_WIFI_INFO "
			+ " 	PRIMARY KEY (               "
			+ " 		MNG_NO                  "  // 관리번호
			+ " )                               ";
	
	// [PK 생성 쿼리] 와이파이 정보 전체
	public static String strPK_WIFI_INFO_ALL = ""
			+ " ALTER TABLE TB_WIFI_INFO_ALL        "
	        + " 	ADD CONSTRAINT PK_WIFI_INFO_ALL "
			+ " 	PRIMARY KEY (                   "
			+ " 		MNG_NO                      "  // 관리번호
			+ " )                                   ";
	
	// [PK 생성 쿼리] 위치 히스토리
	public static String strPK_LOCATION_HIS = ""
			+ " ALTER TABLE TB_LOCATION_HIS        "
	        + " 	ADD CONSTRAINT PK_LOCATION_HIS "
			+ " 	PRIMARY KEY (                  "
			+ " 		LOC_ID                     " // 위치 ID
			+ " )                                  ";
	

	// [PK 생성 쿼리] 북마크 그룹
	public static String strPK_BOOKMARK_GROUP = ""
			+ " ALTER TABLE TB_BOOKMARK_GROUP        "
		    + " 	ADD CONSTRAINT PK_BOOKMARK_GROUP "
			+ " 	PRIMARY KEY (                    "
			+ " 		GROUP_ID                     " // 북마크 그룹 ID
			+ " )                                    ";
	
	// [PK 생성 쿼리] 북마크 그룹
	public static String strPK_BOOKMARK_LIST = ""
			+ " ALTER TABLE TB_BOOKMARK_LIST        "
		    + " 	ADD CONSTRAINT PK_BOOKMARK_LIST "
			+ " 	PRIMARY KEY (                   "
			+ " 		BOOKMARK_ID                 " // 북마크 ID
			+ " )                                   ";
	
}
