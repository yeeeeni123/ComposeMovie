package com.yeen.domain.pref

import android.os.Build
import android.text.TextUtils
import androidx.annotation.RequiresApi
import com.yeen.data.helper.DataConverter
import com.yeen.data.helper.DataEncoding
import com.yeen.data.order.IStorage

class StorageManager(
    private val storage: StorageProvider,
    private val converter: DataConverter,
    private val encoding: DataEncoding
) : IStorage {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun <T> save(key: String, value: T): Boolean {
        var saved = false
        val serializedData = converter.serialize(value)
        if (serializedData != null) {
            encoding.encodeToString(serializedData)?.also { data ->
                saved = storage.save(key, data)
            }
        }
        return saved
    }

    override fun <T> save(key: String, value: T, callback: (Boolean) -> Unit) {
        callback(save(key, value))
    }

    override fun exists(key: String): Boolean {
        return storage.get(key) != null
    }

    override fun <T> get(key: String): T? {
        val savedData = storage.get(key)
        var data: T? = null
        if (!TextUtils.isEmpty(savedData)) {
            data = converter.deserialize<T>(encoding.decode(savedData))
        }
        return data
    }

    override fun <T> get(key: String, callback: (data: T?) -> Unit) {
        callback(get(key))
    }

    override fun remove(key: String): Boolean {
        return storage.remove(key)
    }

    override fun remove(key: String, callback: (Boolean) -> Unit) {
        callback(remove(key))
    }

    override fun clear(): Boolean {
        storage.clear()
        return true
    }
}
