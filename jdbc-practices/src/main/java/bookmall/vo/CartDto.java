package bookmall.vo;

public class CartDto {
	private String name;
	private String title;
	private Long count;
	private int price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "CartDto [name=" + name + ", title=" + title + ", count=" + count + ", price=" + price + "]";
	}
	
	
}
