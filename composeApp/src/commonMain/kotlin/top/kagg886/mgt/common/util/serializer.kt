package top.kagg886.mgt.common.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

object LocalDateTimeAsLongSerializer : KSerializer<LocalDateTime> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(LocalDateTime::class.java.name)
    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeLong(value.toInstant(ZoneOffset.UTC).toEpochMilli())
    }

    override fun deserialize(decoder: Decoder): LocalDateTime {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(decoder.decodeLong()), ZoneOffset.UTC)
    }
}