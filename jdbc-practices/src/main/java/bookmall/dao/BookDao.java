package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.Book;


public class BookDao {

	public List<Book> findAll() {
		List<Book> result = new ArrayList<Book>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			//3. SQL 준비
			String sql = "select no, title, price, category_no from book";
			pstmt = conn.prepareStatement(sql);

			//5. SQL 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				int price = rs.getInt(3);
				Long categoryNo = rs.getLong(4);
				
				Book book = new Book();
				book.setNo(no);
				book.setTitle(title);
				book.setPrice(price);
				book.setCategoryNo(categoryNo);
				
				result.add(book);
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
	
	public boolean insert(Book book) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			//3. SQL 준비
			String sql = "insert into book values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			//4. 바인딩(binding)
			pstmt.setString(1, book.getTitle());
			pstmt.setInt(2, book.getPrice());
			pstmt.setLong(3, book.getCategoryNo());

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
