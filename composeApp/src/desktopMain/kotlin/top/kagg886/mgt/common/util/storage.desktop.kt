package top.kagg886.mgt.common.util

import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.getString
import top.kagg886.mgt.common.Res
import top.kagg886.mgt.common.cant_read_file
import top.kagg886.mgt.common.sendSnackBar
import java.awt.Desktop
import java.io.File
import java.util.concurrent.CompletableFuture
import javax.swing.JFileChooser
import javax.swing.JFileChooser.APPROVE_OPTION
import javax.swing.JFileChooser.FILES_ONLY
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

actual fun getConfigRootFile(): File = File(System.getProperty("user.home")).resolve(".config").resolve("mgt").apply {
    if (!exists()) {
        absoluteFile.mkdirs()
    }
}

actual fun openConfigFile() {
    Desktop.getDesktop().open(getConfigRootFile());
}

actual suspend fun selectFile(): FileInfo? =  suspendCoroutine { continuation ->
    CompletableFuture.supplyAsync {
        val chooser = JFileChooser().apply {
            fileSelectionMode = FILES_ONLY

        }
        val flag = chooser.showOpenDialog(null)
        if (flag == APPROVE_OPTION) {
            val f = chooser.selectedFile ?: return@supplyAsync null
            if (!f.canRead()) {
                runBlocking {
                    sendSnackBar(getString(Res.string.cant_read_file))
                }
                continuation.resume(null)
            }
            continuation.resume(FileInfo(f.name, f.inputStream()))
        }
        continuation.resume(null)
    }
}