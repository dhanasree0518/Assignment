package com.company.assignment.dto

data class ProductWithCategoryDTO(
    val id: Long,
    val name: String,
    val category: SimpleCategoryDTO
)