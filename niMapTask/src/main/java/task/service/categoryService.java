package task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import task.entity.Category;
import task.repository.categoryRepo;

@Service
public class categoryService {

	@Autowired
	categoryRepo repo;

	public Category addCatagory(Category c) {
		return repo.save(c);
	}

	public Category findCatagoryById(int id) {
		return repo.findById(id).orElse(null);
	}

	public Category updateCatagory(Category c) {
		return repo.save(c);
	}

	public void deleteCatagoryById(int id) {
		repo.deleteById(id);
	}

	public Page<Category> getCategories(Pageable pageable) {
		return repo.findAll(pageable);
	}

}
