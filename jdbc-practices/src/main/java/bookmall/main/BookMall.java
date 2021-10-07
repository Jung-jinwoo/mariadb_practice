package bookmall.main;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderBookDao;
import bookmall.dao.OrderDao;
import bookmall.vo.Book;
import bookmall.vo.Cart;
import bookmall.vo.CartDto;
import bookmall.vo.Category;
import bookmall.vo.Member;
import bookmall.vo.Order;
import bookmall.vo.OrderBook;
import bookmall.vo.OrderBookDto;
import bookmall.vo.OrderDto;

public class BookMall {
	public static void main(String[] args) {
		//insertMember();
		//insertCategory();
		//insertBook();
		//insertCart();
		//insertOrder();
		//insertOrderBook();
		
		displayBookInfo();
		displayCategoryInfo();
		displayMemberInfo();
		displayCartInfo();
		dispalyOrderInfo();
		displayOrderBookInfo();
	} //main

	
	private static void insertOrderBook() {
		OrderBookDao orderBookDao = new OrderBookDao();
		OrderBook orderbook = null;

		orderbook = new OrderBook();
		orderbook.setCount(4);
		orderbook.setSalePrice(54000);
		orderbook.setBookNo(1L);
		orderbook.setOrderNo(1L);
		orderBookDao.insert(orderbook);
		
		orderbook = new OrderBook();
		orderbook.setCount(2);
		orderbook.setSalePrice(45000);
		orderbook.setBookNo(2L);
		orderbook.setOrderNo(1L);
		orderBookDao.insert(orderbook);
	}


	private static void insertOrder() {
		OrderDao orderDao = new OrderDao();
		Order order = null;
		
		order = new Order();
		order.setOrderno("222-222-2222");
		order.setTotalPrice(40500);
		order.setAddress("동서대학교 센텀캠퍼스 704호");
		order.setMemberNo(1L);
		orderDao.insert(order);
	}


	private static void insertCart() {
		CartDao cartDao = new CartDao();
		Cart cart = null;
		
		cart = new Cart();
		cart.setCount(3L);
		cart.setBookNo(1L);
		cart.setMemberNo(1L);
		cartDao.insert(cart);
		
		cart = new Cart();
		cart.setCount(5L);
		cart.setBookNo(2L);
		cart.setMemberNo(2L);
		cartDao.insert(cart);
	}


	private static void insertMember() {
		MemberDao memberDao = new MemberDao();
		Member member = null;
		
		member = new Member();
		member.setName("홍길동");
		member.setEmail("gildong@naver.com");
		member.setPassword("1234");
		member.setPhone("010-1234-5678");
		memberDao.insert(member);
		
		member = new Member();
		member.setName("성춘향");
		member.setEmail("chunhyang@naver.com");
		member.setPassword("1234");
		member.setPhone("010-9876-5432");
		memberDao.insert(member);
		
	}

	private static void insertCategory() {
		CategoryDao categoryDao = new CategoryDao();
		Category category = null;
		
		category = new Category();
		category.setTitleName("자바");
		categoryDao.insert(category);
		
		category = new Category();
		category.setTitleName("데이터베이스");
		categoryDao.insert(category);
		
		category = new Category();
		category.setTitleName("네트워크");
		categoryDao.insert(category);
	}

	private static void insertBook() {
		BookDao bookDao = new BookDao();
		Book book = null;
		
		book = new Book();
		book.setTitle("Eclipse");
		book.setPrice(15000);
		book.setCategoryNo(1L);
		bookDao.insert(book);
		
		book = new Book();
		book.setTitle("DataBase");
		book.setPrice(25000);
		book.setCategoryNo(2L);
		bookDao.insert(book);
		
		book = new Book();
		book.setTitle("Network");
		book.setPrice(35000);
		book.setCategoryNo(3L);
		bookDao.insert(book);
	}
	
	private static void displayCategoryInfo() {
		System.out.println("****** 카테고리 정보 출력 ******");
		List<Category> list = new CategoryDao().findAll();
		
		for(Category category : list) {
			String info = String.format("[%d] 카테고리: %s", category.getNo(), category.getTitleName());
			System.out.println(info);
		}
		System.out.println();
		
	}

	private static void displayBookInfo() {
		System.out.println("****** 도서 정보 출력 ******");
		List<Book> list = new BookDao().findAll();
		
		for(Book book : list) {
			String info = String.format("[%d] 제목: %s, 가격: %d", book.getNo(), book.getTitle(), book.getPrice());
			System.out.println(info);
		}
		System.out.println();
	}
	
	private static void displayMemberInfo() {
		System.out.println("****** 회원 정보 출력 ******");
		List<Member> list = new MemberDao().findAll();
		
		for(Member member : list) {
			String info = String.format("[%d] 이름: %s, 이메일: %s, 전화번호: %s", member.getNo(), member.getName(), member.getEmail(), member.getPhone());
			System.out.println(info);
		}
		System.out.println();
	}
	
	private static void displayCartInfo() {
		System.out.println("****** 장바구니 정보 출력 ******");
		List<CartDto> list = new CartDao().findAll();
		
		for(CartDto cart : list) {
			String info = String.format("이름: %s, 책 제목: %s, 수량: %d, 총 가격: %d", cart.getName(), cart.getTitle(), cart.getCount(), cart.getPrice() * cart.getCount());
			System.out.println(info);
		}
		System.out.println();
	}

	private static void dispalyOrderInfo() {
		System.out.println("****** 주문 정보 출력 ******");
		List<OrderDto> list = new OrderDao().findAll();
		
		for(OrderDto order : list) {
			String info = String.format("주문번호: %s, 이름: %s, 이메일: %s, 총 가격: %d, 주소: %s", order.getOrderNo(), order.getName(), order.getEmail(), order.getTotalPrice(), order.getAddress());
			System.out.println(info);
		}
		System.out.println();
	}
	
	private static void displayOrderBookInfo() {
		System.out.println("****** 주문 도서 정보 출력 ******");
		List<OrderBookDto> list = new OrderBookDao().findAll();
		
		for(OrderBookDto orderbook : list) {
			String info = String.format("도서번호: %d, 도서제목: %s, 수량: %d", orderbook.getBookNo(), orderbook.getTitle(), orderbook.getCount());
			System.out.println(info);
		}
		System.out.println();
	}
}
