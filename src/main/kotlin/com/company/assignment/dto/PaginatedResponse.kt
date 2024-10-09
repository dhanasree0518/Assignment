package com.company.assignment.dto

data class PaginatedResponse<T>(
    val content: List<T>,
    val currentPage: Int,
    val totalPages: Int,
    val totalElements: Long
)
