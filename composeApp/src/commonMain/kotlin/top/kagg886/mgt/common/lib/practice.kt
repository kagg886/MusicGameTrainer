package top.kagg886.mgt.common.lib

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.getString
import top.kagg886.mgt.common.Res
import top.kagg886.mgt.common.not_found_file_in_dict
import top.kagg886.mgt.common.util.LocalDateTimeAsLongSerializer
import top.kagg886.mgt.common.util.getConfigRootFile
import java.io.File
import java.io.FileNotFoundException
import java.io.OutputStream
import java.time.LocalDateTime

@Serializable
data class PracticeConfig(
    val speed: Float = 1f,
    @Serializable(LocalDateTimeAsLongSerializer::class) val lastOpenTime: LocalDateTime = LocalDateTime.now()
)

val practiceRoot = getConfigRootFile().resolve("practices").apply {
    if (exists().not()) {
        mkdirs()
    }
}


data class Practice(val root: File) {
    var config: PracticeConfig
        private set

    private val configFile: File
    val videoFile: File

    val name: String = root.name

    init {
        if (!root.exists() && !root.isDirectory) {
            throw FileNotFoundException("${root.name} 不存在")
        }
        val (configFile, videoFile) = root.listFiles()!!.run {
            val configFile = firstOrNull { it.name == "config.json" }
                ?: throw FileNotFoundException(runBlocking {
                    getString(Res.string.not_found_file_in_dict, root.name, "config.json")
                })
            val videoFile = firstOrNull { it.name == "video.mp4" }
                ?: throw FileNotFoundException(runBlocking {
                    getString(Res.string.not_found_file_in_dict, root.name, "video.mp4")
                })
            configFile to videoFile
        }

        this.config = Json.decodeFromString(configFile.readText())
        this.configFile = configFile
        this.videoFile = videoFile
    }

    fun setConfig(dsl: PracticeConfig.() -> PracticeConfig) {
        config = dsl(config)
        configFile.writeText(Json.encodeToString(config))
    }

    companion object {
        fun import(
            name: String
        ): OutputStream {
            val root = practiceRoot.resolve(name).apply {
                mkdirs()
            }
            root.resolve("config.json").apply {
                createNewFile()
                writeText(Json.encodeToString(PracticeConfig()))
            }
            return root.resolve("video.mp4").apply {
                createNewFile()
            }.outputStream()
        }
    }
}