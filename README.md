[![](https://jitpack.io/v/Asutosh11/DocumentReader.svg)](https://jitpack.io/#Asutosh11/DocumentReader)


# DocumentReader
 
This library reads word documents (.doc and .docx), txt and PDF files, and gives the output content of the document as a String.

<i>If you have ever tried to read contents of a PDF or MS word document on Android, you know how painful it is. 
This library makes your work easy.</i>

<p><b>How to import in gradle?</b></p>


1.<code>
repositories {
			...
			maven { url 'https://jitpack.io' }
}
</code><br/>

2.<code>
dependencies {
  ....
  implementation 'com.github.Asutosh11:DocumentReader:0.1'
}
</code>

<p><b>Usage in code</b></p>

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
    val DocString : String = when (DocumentReaderUtil.getMimeType(file, applicationContext)) {
            "text/plain" -> DocumentReaderUtil.readTxtFileContent(file, applicationContext)
            "application/pdf" -> DocumentReaderUtil.readPdfFileContent(file, applicationContext)
            "application/msword" -> DocumentReaderUtil.readWordDocFile(file, applicationContext)
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document" -> 
                                        DocumentReaderUtil.readWordDocFile(file, applicationContext)
             else -> ""
            }
