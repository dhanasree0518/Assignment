package com.company.assignment.services

import com.company.assignment.dto.*
import com.company.assignment.entity.Product
import com.company.assignment.repository.CategoryRepository
import com.company.assignment.repository.ProductRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) {

    fun getAllProducts(page: Int): PaginatedResponse<ProductWithCategoryDTO > {
        val productPage = productRepository.findAll(PageRequest.of(page, 10))
        val products = productPage.content.map {
            ProductWithCategoryDTO (it.id, it.name, SimpleCategoryDTO(it.category.id, it.category.name))
        }
        return PaginatedResponse(
            content = products,
            currentPage = productPage.number,
            totalPages = productPage.totalPages,
            totalElements = productPage.totalElements
        )
    }

    fun createProduct(product: Product): Product {
        val category = categoryRepository.findById(product.category.id)
            .orElseThrow { Exception("Category not found") }
        return productRepository.save(product.copy(category = category))
    }

    fun getProductById(id: Long): ProductWithCategoryDTO {
        val product = productRepository.findById(id).orElseThrow { Exception("Product not found") }
        return ProductWithCategoryDTO (product.id, product.name, SimpleCategoryDTO(product.category.id, product.category.name))
    }

    fun updateProduct(id: Long, updatedProduct: Product): Product {
        val product = productRepository.findById(id).orElseThrow { Exception("Product not found") }
        return productRepository.save(product.copy(name = updatedProduct.name))
    }

    fun deleteProduct(id: Long): String {
        val product = productRepository.findById(id)
            .orElseThrow { RuntimeException("Product not found") }
        productRepository.delete(product)
        return "Product with ID $id was successfully deleted."
    }
}
