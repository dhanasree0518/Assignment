package com.company.assignment.controller
import com.company.assignment.dto.CategoryDTO
import com.company.assignment.dto.PaginatedResponse
import com.company.assignment.entity.Category
import com.company.assignment.services.CategoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/categories")
class CategoryController(private val categoryService: CategoryService) {

    @GetMapping
    fun getAllCategories(@RequestParam(defaultValue = "0") page: Int): PaginatedResponse<CategoryDTO> {
        return categoryService.getAllCategories(page)
    }

    @PostMapping
    fun createCategory(@RequestBody category: Category): Category {
        return categoryService.createCategory(category)
    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: Long): CategoryDTO {
        return categoryService.getCategoryById(id)
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: Long, @RequestBody category: Category): Category {
        return categoryService.updateCategory(id, category)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: Long): String {
        return categoryService.deleteCategory(id)
    }
}
