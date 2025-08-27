package com.example.aasc.util

import android.graphics.ImageFormat
import android.graphics.Rect
import android.graphics.YuvImage
import android.util.Base64
import androidx.camera.core.ImageProxy
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

fun imageProxyToBase64Jpeg(imageProxy: ImageProxy, jpegQuality: Int = 90): String {
    val yBuffer: ByteBuffer = imageProxy.planes[0].buffer // Y
    val uBuffer: ByteBuffer = imageProxy.planes[1].buffer // U
    val vBuffer: ByteBuffer = imageProxy.planes[2].buffer // V

    val ySize = yBuffer.remaining()
    val uSize = uBuffer.remaining()
    val vSize = vBuffer.remaining()

    val nv21 = ByteArray(ySize + uSize + vSize)
    yBuffer.get(nv21, 0, ySize)
    vBuffer.get(nv21, ySize, vSize)
    uBuffer.get(nv21, ySize + vSize, uSize)

    val yuv = YuvImage(
        nv21,
        ImageFormat.NV21,
        imageProxy.width,
        imageProxy.height,
        null
    )
    val out = ByteArrayOutputStream()
    yuv.compressToJpeg(Rect(0, 0, imageProxy.width, imageProxy.height), jpegQuality, out)
    val jpegBytes = out.toByteArray()

    return Base64.encodeToString(jpegBytes, Base64.NO_WRAP)
}
