package com.example.weatherforecast.data.model.api

enum class ApiCode(val value: Int) {
    SUCCESS(200),
    CREATED(201),
    NO_CONTENT(204),
    TIMEOUT(-100),
    UNAUTHORIZED(401),
    FAILURE(-101),
    LOST_CONNECTION(-102),
    UNKNOWN(500),
}

class ApiResponse<T, E>(
    private var code: Int? = null,
    private var message: String? = null,
    private var result: T? = null,
    private var error: E? = null,
) {

    fun isSuccess(): Boolean {
        return ApiCode.SUCCESS.value == code ||
                ApiCode.CREATED.value == code ||
                ApiCode.NO_CONTENT.value == code
    }

    fun result(): T? = result

    fun code(): Int = code ?: 0

    fun message(): String? = message

    fun error(): E? = error

    fun result(value: T?): ApiResponse<T, E> {
        result = value
        return this
    }

    fun code(value: Int): ApiResponse<T, E> {
        code = value
        return this
    }

    fun message(value: String?): ApiResponse<T, E> {
        message = value
        return this
    }

    fun error(value: E?): ApiResponse<T, E> {
        error = value
        return this
    }

    fun toLogging(): String {
        val log = StringBuilder("")
        log.append("error ${error()}")
        log.append(", code ${code()}")
        log.append(", result ${result()}")
        return log.toString()
    }
}