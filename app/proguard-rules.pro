# ScanMate AI Pro safe release rules.
# Release R8 is enabled for v1.5.0. These rules keep scanner/OCR/Room/Compose integrations stable.

-keep class com.synthbyte.scanmate.data.** { *; }
-keep class com.synthbyte.scanmate.domain.** { *; }
-keep class com.google.zxing.** { *; }
-keep class com.google.mlkit.** { *; }
-keep class com.google.android.gms.internal.mlkit_** { *; }
-keep class androidx.camera.** { *; }
-keep class androidx.room.** { *; }
-keep class com.squareup.moshi.** { *; }
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes InnerClasses,EnclosingMethod
-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.**
-dontwarn okio.**
-keep class com.synthbyte.scanmate.widgets.** { *; }
-keep class * extends android.appwidget.AppWidgetProvider { *; }
-keep class coil.** { *; }
-dontwarn coil.**
-keep class androidx.compose.runtime.** { *; }
-keepclassmembers class * {
    @androidx.compose.runtime.Stable *;
    @androidx.compose.runtime.Immutable *;
}
-keep class com.synthbyte.scanmate.utils.** { *; }
-keep class com.synthbyte.scanmate.util.** { *; }
-keep class com.synthbyte.scanmate.widgets.** { *; }

-assumenosideeffects class okhttp3.logging.HttpLoggingInterceptor {
    public void log(java.lang.String);
}
-dontwarn okhttp3.logging.**
-dontwarn org.spongycastle.**
-keep class org.spongycastle.crypto.** { *; }
-keep class org.spongycastle.jce.** { *; }
-keep class org.spongycastle.asn1.** { *; }
-dontwarn org.spongycastle.**
-keep class org.spongycastle.** { *; }
-keep class com.itextpdf.** { *; }

# Security/runtime keep rules for password-protected PDF export and encrypted preferences.
-dontwarn com.itextpdf.**
-keep class org.bouncycastle.** { *; }
-dontwarn org.bouncycastle.**
-keep class androidx.security.crypto.** { *; }
-dontwarn androidx.security.crypto.**
-keep class com.google.crypto.tink.** { *; }
-dontwarn com.google.crypto.tink.**



# ============================================================
# ScanMate AI Pro - Release R8 compatibility rules
# Reason:
# Apache POI, XMLBeans, PDFBox Android, and Log4j reference
# optional desktop/server classes that are not available on Android.
# Debug builds pass, but Release R8 fails unless these optional
# references are ignored.
# ============================================================

# PDFBox Android optional JPEG2000 encoder/decoder
-dontwarn com.gemalto.jp2.**
-dontwarn com.tom_roush.pdfbox.filter.JPXFilter

# Java AWT desktop classes referenced by Apache POI optional rendering/debug paths
-dontwarn java.awt.**
-dontwarn java.awt.color.**
-dontwarn java.awt.font.**
-dontwarn java.awt.geom.**
-dontwarn java.awt.image.**

# XMLBeans / POI optional StAX references
-dontwarn javax.xml.stream.**
-dontwarn org.apache.xmlbeans.**

# Saxon optional XPath backend used by XMLBeans if present
-dontwarn net.sf.saxon.**

# Batik optional SVG rendering used by Apache POI slideshow/image code
-dontwarn org.apache.batik.**

# OSGi optional service loading used by Log4j
-dontwarn org.osgi.**

# Logging optional integrations
-dontwarn org.apache.logging.log4j.**

# Keep core POI/XMLBeans classes used for DOCX generation
-keep class org.apache.poi.** { *; }
-keep class org.openxmlformats.schemas.** { *; }
-keep class com.microsoft.schemas.** { *; }
-keep class org.apache.xmlbeans.** { *; }

# Keep PDFBox Android classes used for PDF processing/export
-keep class com.tom_roush.pdfbox.** { *; }
