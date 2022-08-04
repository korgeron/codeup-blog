package com.codeup.springblog.repos;

import com.codeup.springblog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends JpaRepository <Product , Long> {

}
