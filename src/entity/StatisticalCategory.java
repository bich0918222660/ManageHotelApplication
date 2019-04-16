package entity;

public class StatisticalCategory {

	private String categoryName;
	private int count;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public StatisticalCategory(String categoryName, int count) {
		super();
		this.categoryName = categoryName;
		this.count = count;
	}

}
