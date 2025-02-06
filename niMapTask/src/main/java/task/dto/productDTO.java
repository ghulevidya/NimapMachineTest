package task.dto;

import task.entity.Product;

public class productDTO {

	private int id;
	private String name;
	private double price;
	private categoryDTO category;

	public productDTO(Product product) {
		this.id = product.getProduct_Id();
		this.name = product.getProduct_Name();
		this.price = product.getPrice();
		this.category = new categoryDTO(product.getCategory());
	}

	public productDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public categoryDTO getCategory() {
		return category;
	}

	public void setCategory(categoryDTO category) {
		this.category = category;
	}

}
