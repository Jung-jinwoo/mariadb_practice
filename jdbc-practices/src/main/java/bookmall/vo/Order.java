package bookmall.vo;

public class Order {
	private String orderno;
	private Long no;
	private int totalPrice;
	private String address;
	private Long memberNo;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	@Override
	public String toString() {
		return "Order [no=" + no + ", orderno=" + orderno + ", totalPrice=" + totalPrice + ", address=" + address
				+ ", memberNo=" + memberNo + "]";
	}
	
	
}
