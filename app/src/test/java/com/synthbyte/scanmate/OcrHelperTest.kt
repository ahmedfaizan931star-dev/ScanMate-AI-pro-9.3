package com.synthbyte.scanmate

import android.content.Context
import android.graphics.Bitmap
import androidx.test.core.app.ApplicationProvider
import com.synthbyte.scanmate.utils.OcrHelper
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

class OcrHelperTest {
    @Test
    fun estimateSkewAndRotateReturnsSourceUnchangedWhenWidthBelow100() {
        val source = Bitmap.createBitmap(80, 120, Bitmap.Config.ARGB_8888)

        val result = invokeBitmapPrivate("estimateSkewAndRotate", source)

        assertSame(source, result)
        source.recycle()
    }

    @Test
    fun estimateSkewAndRotateReturnsSourceUnchangedWhenBestAngleBelowThreshold() {
        val source = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888)

        val result = invokeBitmapPrivate("estimateSkewAndRotate", source)

        assertSame(source, result)
        source.recycle()
    }

    @Test
    fun preprocessForOcrReturnsBitmapForSmallSource() {
        val source = Bitmap.createBitmap(120, 120, Bitmap.Config.ARGB_8888)

        val result = invokeBitmapPrivate("preprocessForOcr", source)

        assertEquals(120, result.width)
        assertEquals(120, result.height)
        if (result !== source) result.recycle()
        source.recycle()
    }

    @Test
    fun extractBlocksFromFileReturnsEmptyListOnMissingFile() = runTest {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val missing = File(context.cacheDir, "missing_${System.nanoTime()}.jpg")

        val result = OcrHelper.extractBlocksFromFile(context, missing)

        assertTrue(result.isEmpty())
    }

    private fun invokeBitmapPrivate(methodName: String, source: Bitmap): Bitmap {
        val method = OcrHelper::class.java.getDeclaredMethod(methodName, Bitmap::class.java)
        method.isAccessible = true
        return method.invoke(OcrHelper, source) as Bitmap
    }
}
