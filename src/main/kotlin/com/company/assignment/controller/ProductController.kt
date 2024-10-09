package com.company.assignment.controller
import com.company.assignment.dto.PaginatedResponse
import com.company.assignment.dto.ProductWithCategoryDTO
import com.company.assignment.entity.Product
import com.company.assignment.services.ProductService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService) {

    @GetMapping
    fun getAllProducts(@RequestParam(defaultValue = "0") page: Int): PaginatedResponse<ProductWithCategoryDTO > {
        return productService.getAllProducts(page)
    }

    @PostMapping
    fun createProduct(@RequestBody product: Product): Product {
        return productService.createProduct(product)
    }

    @GetMapping("/{id}")
    fun getProductById(@PathVariable id: Long): ProductWithCategoryDTO {
        return productService.getProductById(id)
    }

    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody product: Product): Product {
        return productService.updateProduct(id, product)
    }

    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): String {
        return productService.deleteProduct(id)
    }
}

