package top.kagg886.mgt.common.util

import java.io.File
import java.io.InputStream

expect fun getConfigRootFile(): File

expect fun openConfigFile()


data class FileInfo(
    val name: String,
    val stream: InputStream,
)

expect suspend fun selectFile(): FileInfo?