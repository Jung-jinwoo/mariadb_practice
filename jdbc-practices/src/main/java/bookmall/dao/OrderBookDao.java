package bookmall.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.OrderBook;
import bookmall.vo.OrderDto;

public class OrderBookDao {
	public List<OrderDto> findAll() {
		List<OrderDto> result = new ArrayList<OrderDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "select o.orderno, m.name, m.email, o.total_price, o.address "
					+ "from bookmall.order o, member m "
					+ "where o.member_no = m.no";
			pstmt = conn.prepareStatement(sql);

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String orderNo = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				int totalPrice = rs.getInt(4);
				String address = rs.getString(5);
				
				OrderDto orderDto = new OrderDto();
				orderDto.setOrderNo(orderNo);
				orderDto.setName(name);
				orderDto.setEmail(email);
				orderDto.setTotalPrice(totalPrice);
				orderDto.setAddress(address);

				result.add(orderDto);
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

	public boolean insert(OrderBook orderbook) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			// 3. SQL 준비
			String sql = "insert into order_book values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			// 4. 바인딩(binding)
			pstmt.setInt(1, orderbook.getCount());
			pstmt.setInt(2, orderbook.getSalePrice());
			pstmt.setLong(3, orderbook.getBookNo());
			pstmt.setLong(4, orderbook.getOrderNo());

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
