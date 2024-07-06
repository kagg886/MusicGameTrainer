package top.kagg886.mgt.common.ui.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val Pause: ImageVector
    get() {
        if (_pause != null) {
            return _pause!!
        }
        _pause = Builder(
            name = "Pause", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
            viewportWidth = 1024.0f, viewportHeight = 1024.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(304.0f, 176.0f)
                horizontalLineToRelative(80.0f)
                verticalLineToRelative(672.0f)
                horizontalLineToRelative(-80.0f)
                close()
                moveTo(712.0f, 176.0f)
                horizontalLineToRelative(-64.0f)
                curveToRelative(-4.4f, 0.0f, -8.0f, 3.6f, -8.0f, 8.0f)
                verticalLineToRelative(656.0f)
                curveToRelative(0.0f, 4.4f, 3.6f, 8.0f, 8.0f, 8.0f)
                horizontalLineToRelative(64.0f)
                curveToRelative(4.4f, 0.0f, 8.0f, -3.6f, 8.0f, -8.0f)
                verticalLineTo(184.0f)
                curveToRelative(0.0f, -4.4f, -3.6f, -8.0f, -8.0f, -8.0f)
                close()
            }
        }
            .build()
        return _pause!!
    }

private var _pause: ImageVector? = null
