package task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import task.entity.Category;

import task.entity.Response;
import task.service.categoryService;

@RestController
@RequestMapping("/api")
public class categoryController {
	@Autowired
	categoryService service;

	@PostMapping("/categories")
	public ResponseEntity<Response<Category>> addCatagory(@RequestBody Category c) {
		Category catagory = service.addCatagory(c);
		Response<Category> response = new Response<>();
		response.setMessage("Catagory Added...");
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setData(catagory);
		return new ResponseEntity<Response<Category>>(response, HttpStatus.CREATED);
	}

	@GetMapping("categories/{id}")
	public ResponseEntity<Response<Category>> findCatagoryById(@PathVariable int id) {
		Category catagory = service.findCatagoryById(id);
		Response<Category> response = new Response<>();
		if (catagory != null) {
			response.setMessage("Catagory found...");
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setData(catagory);
		} else {
			response.setMessage("Catagory Not found...");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setData(catagory);
		}
		return new ResponseEntity<Response<Category>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("categories/{id}")
	public ResponseEntity<Response<Category>> deleteCatagoryById(@PathVariable int id) {
		Category catagory = service.findCatagoryById(id);
		service.deleteCatagoryById(id);
		Response<Category> response = new Response<>();
		if (catagory != null) {
			response.setMessage("Catagory deleted successfully...");
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setData(catagory);
		} else {
			response.setMessage("Catagory Not found...");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setData(catagory);
		}
		return new ResponseEntity<Response<Category>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/categories/{id}")
	public ResponseEntity<Response<Category>> updateCatagory(@PathVariable int id, @RequestBody Category c) {
		Category catagory = service.findCatagoryById(id);
		Response<Category> response = new Response<>();
		if (catagory != null) {
			catagory.setName(c.getName());
			service.updateCatagory(catagory);
			response.setMessage("Catagory Updated...");
			response.setStatusCode(HttpStatus.OK.value());
			response.setData(catagory);
		} else {
			response.setMessage("Catagory Not found...");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setData(catagory);
		}

		return new ResponseEntity<Response<Category>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/categories")
	public ResponseEntity<Response<Page<Category>>> getAllCategories(@RequestParam int page,
			@RequestParam(defaultValue = "5", required = false) int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Category> categoriesPage = service.getCategories(pageable);
		Response<Page<Category>> response = new Response<>();
		response.setMessage("Paginated Categories List...");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(categoriesPage);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
