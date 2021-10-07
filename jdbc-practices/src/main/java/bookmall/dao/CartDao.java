package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.Cart;
import bookmall.vo.CartDto;

public class CartDao {
	
	public List<CartDto> findAll() {
		List<CartDto> result = new ArrayList<CartDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			//3. SQL 준비
			String sql = "select m.name, b.title, c.count, b.price "
					+ "from cart c, book b, member m "
					+ "where c.book_no = b.no "
					+ "and c.member_no = m.no";
			pstmt = conn.prepareStatement(sql);

			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String name = rs.getString(1);
				String title = rs.getString(2);
				Long count = rs.getLong(3);
				int price = rs.getInt(4);
				
				
				
				CartDto cartDto = new CartDto();
				cartDto.setName(name);
				cartDto.setTitle(title);
				cartDto.setCount(count);
				cartDto.setPrice(price);
				
				result.add(cartDto);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public boolean insert(Cart cart) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			//3. SQL 준비
			String sql = "insert into cart values(?,?,?)";
			pstmt = conn.prepareStatement(sql);

			//4. 바인딩(binding)
			pstmt.setLong(1, cart.getCount());
			pstmt.setLong(2, cart.getBookNo());
			pstmt.setLong(3, cart.getMemberNo());

			//5. SQL 실행
			int count = pstmt.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// clean up
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
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
