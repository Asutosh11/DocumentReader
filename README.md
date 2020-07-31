[![](https://jitpack.io/v/Asutosh11/DocumentReader.svg)](https://jitpack.io/#Asutosh11/DocumentReader)
[![API](https://img.shields.io/badge/API-5%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=5)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-DocumentReader-blue.svg?style=flat)](https://android-arsenal.com/details/1/8136)


# DocumentReader
 
This library reads word documents (.doc and .docx), txt and PDF files, and gives the output content of the document as a String.

<i>If you have ever tried to read contents of a PDF or MS word document on Android, you know how painful it is. 
This library makes your work easy.</i>

<p><b>Dependency for build.gradle (Module: app)</b></p>


```
repositories {
  ...
  maven { url 'https://jitpack.io' }
}
```

```
dependencies {
  ....
  implementation 'com.github.Asutosh11:DocumentReader:0.12'
  implementation "androidx.multidex:multidex:2.0.1"
}
```

```
packagingOptions {
   exclude 'META-INF/DEPENDENCIES'
   exclude 'META-INF/INDEX.LIST'
   exclude 'META-INF/spring.handlers'
   exclude 'META-INF/spring.schemas'
   exclude 'META-INF/cxf/bus-extensions.txt'
}
```

```
defaultConfig {
   ...
   multiDexEnabled true
}
```

<p><b>How to use it?</b></p>

```
// Read a pdf file from Uri
val docString : String = DocumentReaderUtil.readPdfFromUri(fileUri, applicationContext)
// Read a pdf file from File
val docString : String = DocumentReaderUtil.readPdfFromFile(file, applicationContext)
```

```
// read a doc file from Uri
val docString : String = DocumentReaderUtil.readWordDocFromUri(fileUri, applicationContext)
// read a doc file from File
val docString : String = DocumentReaderUtil.readWordDocFromFile(file, applicationContext)
```

```
// read a docx file from Uri
val docString : String = DocumentReaderUtil.readWordDocFromUri(fileUri, applicationContext)
// read a docx file from File
val docString : String = DocumentReaderUtil.readWordDocFromFile(file, applicationContext)
```

```
// read a txt file from Uri
val docString : String = DocumentReaderUtil.readTxtFromUri(fileUri, applicationContext)
```

```    
/*
 Even if you don't know your file type, 
 this library detects the file mime type and gives you the content of the file as a String
*/
val docString : String = when (DocumentReaderUtil.getMimeType(fileUri, applicationContext)) {
        "text/plain" -> DocumentReaderUtil.readTxtFromUri(fileUri, applicationContext)
        "application/pdf" -> DocumentReaderUtil.readPdfFromUri(fileUri, applicationContext)
        "application/msword" -> DocumentReaderUtil.readWordDocFromUri(fileUri, applicationContext)
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> 
                                        DocumentReaderUtil.readWordDocFromUri(fileUri, applicationContext)
         else -> ""
	 }
```
<br>
<h2><b>Thanks</b></h2>
<a href = "https://tika.apache.org/">The Apache Tika project</a><br>
<a href = "https://github.com/TomRoush/PdfBox-Android">Apache's PdfBox port by TomRoush</a>
