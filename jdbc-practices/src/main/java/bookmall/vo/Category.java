package bookmall.vo;

public class Category {
	private Long no;
	private String titleName;
	
	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Override
	public String toString() {
		return "Category [no=" + no + ", titleName=" + titleName + "]";
	}
	
	
}
