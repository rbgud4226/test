package 주소록db연결;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//싱글톤
//db연결해서 사용할 connection 객체를 반환
public class DBConnect {
	private static DBConnect dbconn = new DBConnect(); //내부에서만 객체생성가능 = 싱글톤이기때문에
	private String url = "jdbc:oracle:thin:@localhost:1521/xe"; //@db서버ip : 리스너번호 / SID값 확인방법 ctrl alt del 작업관리자 - 서비스 - oracleservice뒤에 있는거
	
	private DBConnect() {} //싱글톤, 생성자가 private 
	
	public Connection conn() {		
		try {
			//드라이버 로드. 클래스 이름으로 찾아서 메모리에 얹음
			Class.forName("oracle.jdbc.OracleDriver"); //빌드 패스를 선행안했으면 오류 발생
			//db에 로그인. 세션 수립하고 생성된 connection 객체 반환
			return DriverManager.getConnection(url, "hr", "hr"); //연결이 정상이면 Connection 객체 반환
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static DBConnect getInstance() { //외부에서 사용하기위한 메서드
		return dbconn;
	}
	
}






