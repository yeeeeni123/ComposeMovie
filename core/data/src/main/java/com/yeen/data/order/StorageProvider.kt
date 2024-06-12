package com.yeen.data.order

interface StorageProvider {
    fun save(key: String, value: String): Boolean
    fun get(key: String, default: String? = null): String?
    fun remove(key: String): Boolean
    fun clear()
}
