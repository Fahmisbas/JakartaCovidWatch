package com.acsl.jakartacovidwatch.util

class ResponseStatus(
    val message : String?,
    val status : Status?
)

enum class Status {
    SUCCESS, FAILED
}