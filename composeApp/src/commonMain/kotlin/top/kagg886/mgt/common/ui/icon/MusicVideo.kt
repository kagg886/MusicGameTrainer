package top.kagg886.mgt.common.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MusicVideo: ImageVector
    get() {
        if (_musicVideo != null) {
            return _musicVideo!!
        }
        _musicVideo = ImageVector.Builder(
            name = "MusicVideo", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
            viewportWidth = 1024.0f, viewportHeight = 1024.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF444444)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(342.02f, 640.0f)
                quadToRelative(0.0f, -52.01f, 38.02f, -89.98f)
                reflectiveQuadToRelative(89.98f, -38.02f)
                quadToRelative(13.99f, 0.0f, 41.98f, 8.02f)
                lineToRelative(0.0f, -264.02f)
                lineToRelative(214.02f, 0.0f)
                lineToRelative(0.0f, 86.02f)
                lineToRelative(-128.0f, 0.0f)
                lineToRelative(0.0f, 299.99f)
                quadToRelative(0.0f, 52.01f, -38.02f, 89.0f)
                reflectiveQuadToRelative(-89.98f, 36.99f)
                reflectiveQuadToRelative(-89.98f, -38.02f)
                reflectiveQuadToRelative(-38.02f, -89.98f)
                close()
                moveTo(896.0f, 809.98f)
                lineToRelative(0.0f, -596.01f)
                lineToRelative(-768.0f, 0.0f)
                lineToRelative(0.0f, 596.01f)
                lineToRelative(768.0f, 0.0f)
                close()
                moveTo(896.0f, 128.0f)
                quadToRelative(34.01f, 0.0f, 59.99f, 25.98f)
                reflectiveQuadToRelative(25.98f, 59.99f)
                lineToRelative(0.0f, 596.01f)
                quadToRelative(0.0f, 34.01f, -25.98f, 59.99f)
                reflectiveQuadToRelative(-59.99f, 25.98f)
                lineToRelative(-768.0f, 0.0f)
                quadToRelative(-34.01f, 0.0f, -59.99f, -25.98f)
                reflectiveQuadToRelative(-25.98f, -59.99f)
                lineToRelative(0.0f, -596.01f)
                quadToRelative(0.0f, -34.01f, 25.98f, -59.99f)
                reflectiveQuadToRelative(59.99f, -25.98f)
                lineToRelative(768.0f, 0.0f)
                close()
            }
        }
            .build()
        return _musicVideo!!
    }

private var _musicVideo: ImageVector? = null
