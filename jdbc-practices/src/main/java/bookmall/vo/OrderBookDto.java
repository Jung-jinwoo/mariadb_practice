package bookmall.vo;

public class OrderBookDto {
	private Long bookNo;
	private String title;
	private int count;
	
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "OrderBookDto [bookNo=" + bookNo + ", title=" + title + ", count=" + count + "]";
	}
	
	
}
