package bookmall.vo;

public class OrderBook {
	private int count;
	private int salePrice;
	private Long bookNo;
	private Long orderNo;
	
	public int getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	@Override
	public String toString() {
		return "OrderBook [count=" + count + ", salePrice=" + salePrice + ", bookNo=" + bookNo + ", orderNo=" + orderNo
				+ "]";
	}
	
	
}
