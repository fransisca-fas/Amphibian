package fas.sharedcode.common

expect fun platformName(): String

fun createScreenMessage() = "Kotlin on ${platformName()}"