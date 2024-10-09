package com.company.assignment.services

import com.company.assignment.dto.CategoryDTO
import com.company.assignment.dto.PaginatedResponse
import com.company.assignment.dto.ProductDTO
import com.company.assignment.entity.Category
import com.company.assignment.repository.CategoryRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoryRepository: CategoryRepository) {

    fun getAllCategories(page: Int): PaginatedResponse<CategoryDTO> {
        val categoryPage = categoryRepository.findAll(PageRequest.of(page, 10))
        val categories = categoryPage.content.map { category ->
            // Fetch products for each category and map them to ProductDTO
            val products = category.products.map { product ->
                ProductDTO(product.id, product.name)
            }
            CategoryDTO(category.id, category.name, products)
        }
        return PaginatedResponse(
            content = categories,
            currentPage = categoryPage.number,
            totalPages = categoryPage.totalPages,
            totalElements = categoryPage.totalElements
        )
    }

    fun createCategory(category: Category): Category = categoryRepository.save(category)

    fun getCategoryById(id: Long): CategoryDTO {
        val category = categoryRepository.findById(id).orElseThrow { Exception("Category not found") }
        // Fetch products for the category and map them to ProductDTO
        val products = category.products.map { product ->
            ProductDTO(product.id, product.name)
        }
        return CategoryDTO(category.id, category.name, products)
    }

    fun updateCategory(id: Long, updatedCategory: Category): Category {
        // Fetch the actual Category entity, not the DTO
        val category = categoryRepository.findById(id).orElseThrow { Exception("Category not found") }

        // Update the category's name and save it
        return categoryRepository.save(category.copy(name = updatedCategory.name))
    }

    fun deleteCategory(id: Long): String {
        val category = categoryRepository.findById(id)
            .orElseThrow { RuntimeException("Category not found") }
        categoryRepository.delete(category)
        return "Category with ID $id was successfully deleted."
    }
}
