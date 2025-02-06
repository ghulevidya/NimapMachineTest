package task.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import task.dto.categoryDTO;
import task.dto.productDTO;
import task.entity.Category;
import task.entity.Product;
import task.repository.categoryRepo;
import task.repository.productRepo;

@Service
public class productService {

	@Autowired
	productRepo repo;

	@Autowired
	categoryRepo categoryRepo;

	public productDTO addProduct(productDTO p) {
		Category category;
		category = categoryRepo.findById(p.getCategory().getId()).orElse(null);

		Product product = new Product();
		product.setProduct_Name(p.getName());
		product.setPrice(p.getPrice());
		product.setCategory(category);
		Product savedProduct = repo.save(product);
		return new productDTO(savedProduct);
	}

	public productDTO findProductById(int id) {
		Product p = repo.findById(id).orElse(null);
		if (p == null) {
			return null;
		}
		categoryDTO category1 = new categoryDTO();
		Category category = categoryRepo.findById(p.getCategory().getId()).orElse(null);
		category1.setId(category.getId());
		category1.setName(category.getName());
		productDTO productDTO = new productDTO();
		productDTO.setId(p.getProduct_Id());
		productDTO.setName(p.getProduct_Name());
		productDTO.setPrice(p.getPrice());
		productDTO.setCategory(category1);
		return productDTO;
	}

	public productDTO updateProduct(productDTO p) {
		Category category;
		category = categoryRepo.findById(p.getCategory().getId()).orElse(null);
		if (category != null) {
			Product product = new Product();
			product.setProduct_Id(p.getId());
			product.setProduct_Name(p.getName());
			product.setPrice(p.getPrice());
			product.setCategory(category);
			Product savedProduct = repo.save(product);
			return new productDTO(savedProduct);
		} else {
			return null;
		}
	}

	public void deleteProductById(int id) {
		repo.deleteById(id);
	}

	public Page<productDTO> getProducts(Pageable pageable) {
		Page<Product> productsPage = repo.findAll(pageable);
		List<productDTO> productDTOList = new ArrayList<>();
		for (Product p : productsPage.getContent()) {
			productDTO productDTO = new productDTO();
			productDTO.setId(p.getProduct_Id());
			productDTO.setName(p.getProduct_Name());
			productDTO.setPrice(p.getPrice());
			Category category = p.getCategory();
			categoryDTO categoryDTO = new categoryDTO();
			categoryDTO.setId(category.getId());
			categoryDTO.setName(category.getName());
			productDTO.setCategory(categoryDTO);
			productDTOList.add(productDTO);
		}

		Page<productDTO> productDTOPage = new PageImpl<>(productDTOList);
		return productDTOPage;
	}

}
