# ScanMate AI Pro safe release rules.
# Release R8 is enabled for v1.5.0. These rules keep scanner/OCR/Room/Compose integrations stable.

# ============================================================
# App core/domain/data
# ============================================================

-keep class com.synthbyte.scanmate.data.** { *; }
-keep class com.synthbyte.scanmate.domain.** { *; }
-keep class com.synthbyte.scanmate.utils.** { *; }
-keep class com.synthbyte.scanmate.util.** { *; }

# ============================================================
# ZXing / ML Kit / CameraX / Room / Moshi / Retrofit
# ============================================================

-keep class com.google.zxing.** { *; }
-keep class com.google.mlkit.** { *; }
-keep class com.google.android.gms.internal.mlkit_** { *; }

-keep class androidx.camera.** { *; }
-keep class androidx.room.** { *; }

-keep class com.squareup.moshi.** { *; }
-keep class retrofit2.** { *; }

-dontwarn javax.annotation.**
-dontwarn kotlin.Unit
-dontwarn retrofit2.**
-dontwarn okio.**

# ============================================================
# Kotlin / annotations / reflection metadata
# ============================================================

-keepattributes Signature
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes InnerClasses,EnclosingMethod

# ============================================================
# Widgets
# ============================================================

-keep class com.synthbyte.scanmate.widgets.** { *; }
-keep class * extends android.appwidget.AppWidgetProvider { *; }

# ============================================================
# Coil / Compose
# ============================================================

-keep class coil.** { *; }
-dontwarn coil.**

-keep class androidx.compose.runtime.** { *; }

-keepclassmembers class * {
    @androidx.compose.runtime.Stable *;
    @androidx.compose.runtime.Immutable *;
}

# ============================================================
# Debug logging
# ============================================================

-assumenosideeffects class okhttp3.logging.HttpLoggingInterceptor {
    public void log(java.lang.String);
}

-dontwarn okhttp3.logging.**

# ============================================================
# SpongyCastle / BouncyCastle / iText PDF security
# ============================================================

-dontwarn org.spongycastle.**
-keep class org.spongycastle.crypto.** { *; }
-keep class org.spongycastle.jce.** { *; }
-keep class org.spongycastle.asn1.** { *; }
-keep class org.spongycastle.** { *; }

-keep class com.itextpdf.** { *; }
-dontwarn com.itextpdf.**

# Security/runtime keep rules for password-protected PDF export and encrypted preferences.
-keep class org.bouncycastle.** { *; }
-dontwarn org.bouncycastle.**

-keep class androidx.security.crypto.** { *; }
-dontwarn androidx.security.crypto.**

-keep class com.google.crypto.tink.** { *; }
-dontwarn com.google.crypto.tink.**

# ============================================================
# PDFBox Android
# ============================================================

# Keep PDFBox Android classes used for PDF processing/export.
-keep class com.tom_roush.pdfbox.** { *; }

# PDFBox Android optional JPEG2000 encoder/decoder.
-dontwarn com.gemalto.jp2.**
-dontwarn com.tom_roush.pdfbox.filter.JPXFilter

# ============================================================
# Apache POI / XMLBeans / DOCX export
# ============================================================

# Keep core POI/XMLBeans classes used for DOCX generation.
-keep class org.apache.poi.** { *; }
-keep class org.openxmlformats.schemas.** { *; }
-keep class com.microsoft.schemas.** { *; }
-keep class org.apache.xmlbeans.** { *; }

# Apache POI references many optional OOXML schema classes.
-dontwarn org.apache.poi.**
-dontwarn org.openxmlformats.schemas.**
-dontwarn com.microsoft.schemas.**
-dontwarn schemaorg_apache_xmlbeans.**

# XMLBeans generated schema internals.
-dontwarn org.apache.xmlbeans.**
-dontwarn org.apache.xmlbeans.impl.schema.**
-dontwarn org.apache.xmlbeans.impl.values.**

# XMLBeans / POI optional StAX references.
-dontwarn javax.xml.stream.**

# Saxon optional XPath backend used by XMLBeans if present.
-dontwarn net.sf.saxon.**

# ============================================================
# Java desktop/server APIs referenced by POI optional paths
# ============================================================

# Java AWT desktop classes referenced by Apache POI optional rendering/debug paths.
-dontwarn java.awt.**
-dontwarn java.awt.color.**
-dontwarn java.awt.font.**
-dontwarn java.awt.geom.**
-dontwarn java.awt.image.**

# W3C DOM optional desktop/XML APIs referenced by POI signing/SVG paths.
-dontwarn org.w3c.dom.events.**
-dontwarn org.w3c.dom.svg.**
-dontwarn org.w3c.dom.traversal.**

# Batik optional SVG rendering used by Apache POI slideshow/image code.
-dontwarn org.apache.batik.**

# Extra POI optional digital-signature / XML drawing paths.
-dontwarn org.apache.poi.poifs.crypt.dsig.**
-dontwarn org.apache.poi.xslf.draw.**
-dontwarn org.apache.poi.xslf.usermodel.**

# ============================================================
# Log4j optional integrations
# ============================================================

# OSGi optional service loading used by Log4j.
-dontwarn org.osgi.**

# Logging optional integrations.
-dontwarn org.apache.logging.log4j.**
