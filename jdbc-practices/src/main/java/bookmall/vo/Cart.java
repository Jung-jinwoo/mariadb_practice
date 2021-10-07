package bookmall.vo;

public class Cart {
	private Long count;
	private Long bookNo;
	private Long memberNo;
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	
	@Override
	public String toString() {
		return "Cart [count=" + count + ", bookNo=" + bookNo + ", memberNo=" + memberNo + "]";
	}
	
	
}
