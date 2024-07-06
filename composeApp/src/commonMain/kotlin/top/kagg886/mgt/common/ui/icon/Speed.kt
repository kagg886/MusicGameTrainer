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

public val Speed: ImageVector
    get() {
        if (_speed != null) {
            return _speed!!
        }
        _speed = Builder(name = "Speed", defaultWidth = 24.0.dp, defaultHeight = 24.0.dp,
                viewportWidth = 1024.0f, viewportHeight = 1024.0f).apply {
            path(fill = SolidColor(Color(0xFFFFFFFF)), stroke = null, fillAlpha = 0.01f,
                    strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                    strokeLineMiter = 4.0f, pathFillType = NonZero) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(1024.0f)
                verticalLineToRelative(1024.0f)
                horizontalLineTo(0.0f)
                close()
            }
            path(fill = SolidColor(Color(0xFF222222)), stroke = null, strokeLineWidth = 0.0f,
                    strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                    pathFillType = NonZero) {
                moveTo(512.0f, 42.67f)
                curveToRelative(83.71f, 0.0f, 164.35f, 21.97f, 235.26f, 63.15f)
                arcToRelative(42.67f, 42.67f, 0.0f, true, true, -42.84f, 73.81f)
                arcTo(381.99f, 381.99f, 0.0f, false, false, 512.0f, 128.0f)
                arcToRelative(384.0f, 384.0f, 0.0f, true, false, 384.0f, 384.0f)
                curveToRelative(0.0f, -68.14f, -17.71f, -133.55f, -50.94f, -191.23f)
                arcToRelative(42.67f, 42.67f, 0.0f, true, true, 73.98f, -42.58f)
                arcTo(467.33f, 467.33f, 0.0f, false, true, 981.33f, 512.0f)
                curveToRelative(0.0f, 259.2f, -210.13f, 469.33f, -469.33f, 469.33f)
                reflectiveCurveTo(42.67f, 771.2f, 42.67f, 512.0f)
                reflectiveCurveTo(252.8f, 42.67f, 512.0f, 42.67f)
                close()
                moveTo(666.62f, 302.46f)
                arcToRelative(42.67f, 42.67f, 0.0f, false, true, 54.91f, 54.91f)
                lineToRelative(-25.17f, 64.21f)
                arcToRelative(2424.75f, 2424.75f, 0.0f, false, true, -27.31f, 64.77f)
                lineToRelative(-9.73f, 21.59f)
                lineToRelative(-10.15f, 21.46f)
                curveToRelative(-16.34f, 33.58f, -29.99f, 56.41f, -43.01f, 69.46f)
                arcToRelative(128.0f, 128.0f, 0.0f, false, true, -181.03f, -181.03f)
                curveToRelative(13.06f, -13.01f, 35.88f, -26.67f, 69.46f, -43.01f)
                lineToRelative(21.46f, -10.15f)
                lineToRelative(10.58f, -4.78f)
                lineToRelative(22.4f, -9.94f)
                arcToRelative(2629.67f, 2629.67f, 0.0f, false, true, 80.81f, -33.28f)
                close()
                moveTo(605.14f, 418.82f)
                lineToRelative(-22.14f, 9.43f)
                arcToRelative(1741.23f, 1741.23f, 0.0f, false, false, -10.84f, 4.74f)
                lineToRelative(-20.39f, 9.22f)
                lineToRelative(-15.62f, 7.34f)
                lineToRelative(-13.91f, 6.91f)
                lineToRelative(-12.07f, 6.27f)
                lineToRelative(-10.03f, 5.55f)
                lineToRelative(-7.72f, 4.69f)
                curveToRelative(-3.29f, 2.13f, -5.63f, 3.88f, -6.91f, 5.21f)
                arcToRelative(42.67f, 42.67f, 0.0f, false, false, 60.33f, 60.33f)
                curveToRelative(1.71f, -1.71f, 4.27f, -5.33f, 7.42f, -10.5f)
                lineToRelative(5.12f, -8.87f)
                lineToRelative(5.97f, -11.09f)
                lineToRelative(6.61f, -13.01f)
                lineToRelative(7.13f, -14.81f)
                lineToRelative(8.28f, -17.92f)
                lineToRelative(9.39f, -21.29f)
                lineToRelative(9.39f, -22.19f)
                close()
            }
        }
        .build()
        return _speed!!
    }

private var _speed: ImageVector? = null
