package 주소록db연결;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddrDao {
	private DBConnect db;

	public AddrDao() {
		db = DBConnect.getInstance();
	}

	// insert
	public int insertAddr(Addr addr) {
		Connection conn = db.conn();
		String sql = "insert into addr values(seq_addr.nextval,?,?,?)";
		int cnt = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
//			ps.setString(1, "seq_addr.nextval");
			ps.setString(1, addr.getName());
			ps.setString(2, addr.getTel());
			ps.setString(3, addr.getAddr());
			cnt = ps.executeUpdate(); // 추가된 숫자 반환
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}

	// update
	public int updateAddr(Addr addr) {
		Connection conn = db.conn();
		String sql = "update addr set tel=?, addr=? where num=?";
		int cnt = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, addr.getTel());
			ps.setString(2, addr.getAddr());
			ps.setInt(3, addr.getNum());
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}

	// delete
	public int deleteAddr(int num) {
		Connection conn = db.conn();
		String sql = "delete from addr where num = ?";
		int cnt = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			cnt = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return cnt;
	}

	// select
	public Addr selectAddr(int num) {
		Connection conn = db.conn();
		String sql = "select * from addr where num =  ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return new Addr(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	// selectAll
	public ArrayList<Addr> selectAll() {
		Connection conn = db.conn();
		String sql = "select * from addr order by num"; //num으로 정렬함
		ArrayList<Addr> list = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Addr(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// selectByName
	public ArrayList<Addr> selectByName(String name) {
		Connection conn = db.conn();
		ArrayList<Addr> list = new ArrayList<>();
		String sql = "select * from addr where name like ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Addr(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	// selectByTel
	public ArrayList<Addr> selectByTel(String tel) {
		Connection conn = db.conn();
		ArrayList<Addr> list = new ArrayList<>();
		String sql = "select * from addr where tel like ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + tel + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Addr(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
}
