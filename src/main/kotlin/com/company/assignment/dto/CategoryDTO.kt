package com.company.assignment.dto

data class CategoryDTO(
    val id: Long,
    val name: String,
    val products: List<ProductDTO> = listOf()
)