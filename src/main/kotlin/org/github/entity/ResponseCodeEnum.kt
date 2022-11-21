package org.github.entity

enum class ResponseCodeEnum(
    val code: Int,
    val message: String
) {
    SUCCESS(10000, "成功"),
    FAIL(10001, "失败"),
    PARAM_ERROR(10002, "参数错误"),
}