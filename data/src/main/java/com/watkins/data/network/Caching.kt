package com.watkins.data.network


/**
 * @author Lisa Watkins
 *
 * For requests using network connection, cache results for up to 5 minutes. For requests that only use the HTTP cache,
 * use results up to 1 week old.
 */
enum class CachePolicy(val policy: String) {
    CACHE_ONLY(policy = "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7),
    WITH_NETWORK("public, max-age=" + 5 * 60)
}

const val CACHE_CONTROL = "Cache-Control"