package com.example.ticketing

class NativeLib {

    /**
     * A native method that is implemented by the 'ticketing' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'ticketing' library on application startup.
        init {
            System.loadLibrary("ticketing")
        }
    }
}