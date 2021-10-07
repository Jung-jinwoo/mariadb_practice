package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.Member;

public class MemberDao {
	public List<Member> findAll() {
		List<Member> result = new ArrayList<Member>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select no, name, email, password, phone from member";
			pstmt = conn.prepareStatement(sql);

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String password = rs.getString(4);
				String phone = rs.getString(5);
				
				Member member = new Member();
				member.setNo(no);
				member.setName(name);
				member.setEmail(email);
				member.setPassword(password);
				member.setPhone(phone);

				result.add(member);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean insert(Member member) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "insert into member values(null, ?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getEmail());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getPhone());

			// 5. SQL 실행
			int count = pstmt.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			// 1. JDBC Driver 로딩
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기
			String url = "jdbc:mysql://127.0.0.1:3307/bookmall?charset=utf8";
			conn = DriverManager.getConnection(url, "bookmall", "bookmall");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return conn;
	}
}
