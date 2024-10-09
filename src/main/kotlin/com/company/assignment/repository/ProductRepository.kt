package com.company.assignment.repository
import com.company.assignment.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>{}