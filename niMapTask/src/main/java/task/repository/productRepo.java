package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import task.entity.Product;

@Repository
public interface productRepo extends JpaRepository<Product, Integer> {

}
