package com.project.neuronninja.data

data class ApiResponse<T>(
    var data: T? = null,
    var isLoading: Boolean? = null,
    var exception: Exception? = null
)
