package com.yeen.data.helper

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.Base64
import javax.inject.Inject

class DataEncoding @Inject constructor() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun encodeToString(data: ByteArray?): String? {
        return Base64.getEncoder().encodeToString(data)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun decode(data: String?): ByteArray? {
        return Base64.getDecoder().decode(data)
    }
}
