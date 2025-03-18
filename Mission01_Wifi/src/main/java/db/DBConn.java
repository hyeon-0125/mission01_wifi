package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DBConn {
	
	// 상수 설정
    //   - Database 변수
    private static final String SQLITE_JDBC_DRIVER = "org.sqlite.JDBC";
    private static final String SQLITE_FILE_DB_URL = "jdbc:sqlite:wifiInfo.db";
    private static final String SQLITE_MEMORY_DB_URL = "jdbc:sqlite::memory";
 
    //  - Database 옵션 변수
    private static final boolean OPT_AUTO_COMMIT = false;
    private static final int OPT_VALID_TIMEOUT = 500;
    
    //   - DateFormat 설정
    public static final SimpleDateFormat DATETIME_FMT = new SimpleDateFormat("yyyyMMddHHmmss");
 
    // 변수 설정
    //   - Database 접속 정보 변수
    public static Connection conn = null;
    private String driver = null;
    private String url = null;
 
    // 생성자
    public DBConn(){
        this(SQLITE_FILE_DB_URL);
    }
    public DBConn(String url) {
        // JDBC Driver 설정
        this.driver = SQLITE_JDBC_DRIVER;
        this.url = url;
    }
 
    // DB 연결 함수
    public Connection createConn() {
        try {
            // JDBC Driver Class 로드
            Class.forName(this.driver);
 
            // DB 연결 객체 생성
            this.conn = DriverManager.getConnection(this.url);
 
            // 로그 출력
            System.out.println("CONNECTED");
 
            // 옵션 설정
            //   - 자동 커밋
            this.conn.setAutoCommit(OPT_AUTO_COMMIT);
 
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
 
        return this.conn;
    }
 
    // DB 연결 종료 함수
    public void closeConn() {
        try {
            if( this.conn != null ) {
                this.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.conn = null;
 
            // 로그 출력
            System.out.println("CLOSED");
        }
    }
 
    // DB 재연결 함수
    public Connection ensureConn() {
        try {
            if( this.conn == null || this.conn.isValid(OPT_VALID_TIMEOUT) ) {
                closeConn();      // 연결 종료
                createConn();     // 연결
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
 
        return this.conn;
    }
 
    // DB 연결 객체 가져오기
    public Connection getConn() {
        return this.conn;
    }
    
    // SQL 실행 함수
    public ResultType executeSQL(final String SQL) throws SQLException {
        // 변수설정
        //   - Database 변수
        Connection conn = null;
        Statement stmt = null;
 
        //   - 결과 변수
        ResultType result = ResultType.FAILURE;
 
        try {
            // Database 연결
            conn = ensureConn();
 
            // Statement 객체  생성
            stmt = conn.createStatement();
 
            // SQL 실행
            stmt.execute(SQL);
 
            // 트랜잭션 COMMIT
            conn.commit();
 
            // 성공
            result = ResultType.SUCCESS;
 
        } catch (SQLException e) {
            // 오류출력
            System.out.println(e.getMessage());
 
            // 트랜잭션 ROLLBACK
            if( conn != null ) {
                conn.rollback();
            }
 
            // 오류
            result = ResultType.FAILURE;
 
        } finally {
            // Statement 종료
            if( stmt != null ) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
 
        // 결과 반환
        return result;
    }
    
    public PreparedStatement returnStatemenet(String psSQL) {
    	try {
    		ensureConn();
    		return conn.prepareStatement(psSQL);
    	} catch (Exception e) {
            e.printStackTrace();
    	}
    	
    	return null;
    }
 
    // 테이블 조회 함수
    public boolean checkTable(String tableName) throws SQLException {
        // 변수설정
        //   - Database 변수
        Connection conn = ensureConn();
        DatabaseMetaData meta = conn.getMetaData();
 
        // 테이블 목록 조회
        ResultSet tables = meta.getTables(null, null, tableName, null);
 
        // 테이블 생성 확인
        return ( tables.next() ? tables.getRow() != 0 : false );
    }
 
    // 테이블 생성 함수
    public void createTable(String tableName, String SQL) throws SQLException {
        // 테이블 확인
        if( checkTable(tableName) ) {
            System.out.println("테이블이 이미 존재합니다.");
        }
        
        // SQL 실행 및 반환
        ResultType result = executeSQL(SQL);

        // 테이블 생성 결과 출력
        switch( result ) {
            case SUCCESS:
                System.out.println(tableName + " 테이블 생성 완료.");
                break;
            case WARNING:
                System.out.println(tableName + " 테이블이 이미 존재합니다.");
                break;
            case FAILURE:
                System.out.println(tableName + " 테이블 생성 실패.");
                break;
        }
    }
 
    // 테이블 삭제 함수
    public void dropTable(String tableName) throws SQLException {
        // 테이블 확인
        if( !checkTable(tableName) ) {
            System.out.println(tableName + "테이블이 존재하지 않습니다.");
        }
        
        ResultType result = executeSQL("DROP TABLE IF EXISTS "+ tableName);
 
        // 테이블 삭제 결과 출력
        switch( result ) {
            case SUCCESS:
                System.out.println(tableName + " 테이블 삭제 완료.");
                break;
            case WARNING:
                System.out.println(tableName + " 테이블이 존재하지 않습니다.");
                break;
            case FAILURE:
                System.out.println(tableName + " 테이블 삭제 실패.");
                break;
        }
    }
    
    // 데이터 삽입 함수
    public int updateData(PreparedStatement pState/*, String psSQL, Map<String, Object> dataMap*/) throws SQLException {

        //   - 입력 결과 변수
        int inserted = 0;
 
        try {

            // 쿼리 실행
            pState.executeUpdate();
 
            // 입력건수  조회
            inserted = pState.getUpdateCount();
 
            // 트랜잭션 COMMIT
            conn.commit();
 
        } catch (SQLException e) {
            // 오류출력
            System.out.println(e.getMessage());
            
            // 트랜잭션 ROLLBACK
            if( conn != null ) {
                conn.rollback();
            }
            
            // 오류
            inserted = -1;
 
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
 
        // 결과 반환
        //   - 입력된 데이터 건수
        return inserted;
    }
    
    // 데이터 조회 함수
    public ResultSet selectData(PreparedStatement pState/*, Map<String, Object> dataMap*/){

        ResultSet rs = null;

        
        try {

            // 데이터 조회
        	rs = pState.executeQuery();
        	
        } catch (SQLException e) {
            // 오류처리
            System.out.println(e.getMessage());
            
        }
 
        // 결과 반환
        //   - 조회된 데이터 리스트
        return rs;
    }
    
    // 결과 코드 정의
    public enum ResultType {
        SUCCESS(1),     // 성공
        WARNING(0),     // 경고
        FAILURE(-1);    // 실패
 
        // 코드 변수
        private int code = 0;
 
        // 코드값 설정
        private ResultType(int code){
            this.code = code;
        }
 
        // 코드값 가져오기
        public int getCode() {
            return this.code;
        }
    }

}
