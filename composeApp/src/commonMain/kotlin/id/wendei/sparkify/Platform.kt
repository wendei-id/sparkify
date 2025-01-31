package id.wendei.sparkify

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform