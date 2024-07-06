package top.kagg886.mgt.common.util

enum class DeviceType {
    DESKTOP,ANDROID
}

expect fun getDeviceType(): DeviceType