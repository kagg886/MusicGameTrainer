package top.kagg886.mgt.common.util

import java.io.InputStream

fun InputStream.readBuffered(buffer: ByteArray, onReceived: (bytes: ByteArray,len:Int) -> Unit) {
    var len: Int
    while (read(buffer).also { len = it } != -1) {
        onReceived(buffer,len)
    }
}