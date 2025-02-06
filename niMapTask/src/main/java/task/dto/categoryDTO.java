package task.dto;

import task.entity.Category;

public class categoryDTO {

	private int id;
	private String name;

	public categoryDTO(Category category) {
		this.id = category.getId();
		this.name = category.getName();
	}

	public categoryDTO() {
		super();
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

}
