[![](https://jitpack.io/v/Asutosh11/DocumentReader.svg)](https://jitpack.io/#Asutosh11/DocumentReader)
[![API](https://img.shields.io/badge/API-5%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=5)
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-DocumentReader-blue.svg?style=flat)](https://android-arsenal.com/details/1/8136)


# DocumentReader
 
This library reads word documents (.doc and .docx), txt and PDF files, and gives the output content of the document as a String.

<i>If you have ever tried to read contents of a PDF or MS word document on Android, you know how painful it is. 
This library makes your work easy.</i>

<p><b>Dependency for build.gradle (Module: app)</b></p>


1.<code>
repositories {
			...
			maven { url 'https://jitpack.io' }
}
</code><br/>

2.<code>
dependencies {
  ....
  implementation 'com.github.Asutosh11:DocumentReader:0.11'
}
</code>

<p><b>How to use it?</b></p>

    // Read a pdf file from Uri
    val docString : String = DocumentReaderUtil.readPdfFileContent(fileUri, applicationContext)
    // Read a pdf file from File
    val docString : String = DocumentReaderUtil.readPdfFileContent(file, applicationContext)

    // read a doc file from Uri
    val docString : String = DocumentReaderUtil.readWordDocFile(fileUri, applicationContext)
    // read a doc file from File
    val docString : String = DocumentReaderUtil.readWordDocFile(fileUri, applicationContext)

    // read a docx file from Uri
    val docString : String = DocumentReaderUtil.readWordDocFile(fileUri, applicationContext)
    // read a docx file from File
    val docString : String = DocumentReaderUtil.readWordDocFile(file, applicationContext)

    // read a txt file from Uri
    val docString : String = DocumentReaderUtil.readTxtFileContent(fileUri, applicationContext)
    
    /*
    Even if you don't know your file type, 
    this library detects the file mime type and gives you the content of the file as a String
    */	      
    val docString : String = when (DocumentReaderUtil.getMimeType(file, applicationContext)) {
            "text/plain" -> DocumentReaderUtil.readTxtFileContent(file, applicationContext)
            "application/pdf" -> DocumentReaderUtil.readPdfFileContent(file, applicationContext)
            "application/msword" -> DocumentReaderUtil.readWordDocFile(file, applicationContext)
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> 
                                        DocumentReaderUtil.readWordDocFile(file, applicationContext)
             else -> ""
            }
