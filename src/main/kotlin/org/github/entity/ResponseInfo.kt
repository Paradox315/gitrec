package org.github.entity

data class ResponseInfo<T>(
    val code: Int,
    val message: String,
    val data: T? = null
) {
    constructor() : this(ResponseCodeEnum.SUCCESS.code, ResponseCodeEnum.SUCCESS.message)
    constructor(responseCode: ResponseCodeEnum) : this(responseCode.code, responseCode.message)
    constructor(code: Int, message: String) : this(code, message, null)
    constructor(data: T) : this(ResponseCodeEnum.SUCCESS.code, ResponseCodeEnum.SUCCESS.message, data)
    constructor(data: T, msg: String) : this(ResponseCodeEnum.SUCCESS.code, msg, data)

    companion object {
        fun <T> success(): ResponseInfo<T> {
            return ResponseInfo()
        }

        fun <T> success(data: T): ResponseInfo<T> {
            return ResponseInfo(data)
        }

        fun <T> success(data: T, msg: String): ResponseInfo<T> {
            return ResponseInfo(data, msg)
        }

        fun fail(msg: String): ResponseInfo<Nothing> {
            return ResponseInfo(ResponseCodeEnum.FAIL.code, msg)
        }

        fun badRequest(msg: String): ResponseInfo<Nothing> {
            return ResponseInfo(ResponseCodeEnum.PARAM_ERROR.code, msg)
        }
    }
}