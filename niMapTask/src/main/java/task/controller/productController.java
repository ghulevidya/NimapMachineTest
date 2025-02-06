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

import task.dto.productDTO;
import task.entity.Response;
import task.service.productService;

@RestController
@RequestMapping("/api")
public class productController {

	@Autowired
	private productService service;

	@PostMapping("/products")
	public ResponseEntity<Response<productDTO>> addProduct(@RequestBody productDTO p) {
		productDTO product = service.addProduct(p);
		Response<productDTO> response = new Response<>();
		response.setMessage("Product Added...");
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setData(product);
		return new ResponseEntity<Response<productDTO>>(response, HttpStatus.CREATED);
	}

	@GetMapping("products/{id}")
	public ResponseEntity<Response<productDTO>> findProductById(@PathVariable int id) {
		productDTO product = service.findProductById(id);
		Response<productDTO> response = new Response<>();
		if (product != null) {
			response.setMessage("Product found...");
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setData(product);
		} else {
			response.setMessage("Product Not found...");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setData(product);
		}
		return new ResponseEntity<Response<productDTO>>(response, HttpStatus.CREATED);
	}

	@DeleteMapping("products/{id}")
	public ResponseEntity<Response<productDTO>> deleteProductById(@PathVariable int id) {
		productDTO product = service.findProductById(id);
		Response<productDTO> response = new Response<>();
		if (product != null) {
			service.deleteProductById(id);
			response.setMessage("Product deleted successfully...");
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setData(product);
		} else {
			response.setMessage("Product Not found...");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setData(product);
		}
		return new ResponseEntity<Response<productDTO>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity<Response<productDTO>> updateProduct(@PathVariable int id, @RequestBody productDTO c) {
		productDTO product = service.findProductById(id);
		Response<productDTO> response = new Response<>();
		if (product != null) {
			product.setName(c.getName());
			product.setPrice(c.getPrice());
			product.setCategory(c.getCategory());
			if (service.updateProduct(product) != null) {
				response.setMessage("Product Updated...");
				response.setStatusCode(HttpStatus.OK.value());
				response.setData(product);
			} else {
				response.setMessage("Something went wrong...");
				response.setStatusCode(HttpStatus.NOT_FOUND.value());
				response.setData(product);
			}
		} else {
			response.setMessage("Product Not found...");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			response.setData(product);
		}

		return new ResponseEntity<Response<productDTO>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/products")
	public ResponseEntity<Response<Page<productDTO>>> getAllproducts(@RequestParam int page,
			@RequestParam(defaultValue = "5", required = false) int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<productDTO> categoriesPage = service.getProducts(pageable);
		Response<Page<productDTO>> response = new Response<>();
		response.setMessage("Paginated Products List...");
		response.setStatusCode(HttpStatus.OK.value());
		response.setData(categoriesPage);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
