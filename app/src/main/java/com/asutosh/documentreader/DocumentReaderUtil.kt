package com.thoughtleaf.textsumarizex

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import com.asutosh.documentreader.FilePathHelper
import com.tom_roush.pdfbox.cos.COSDocument
import com.tom_roush.pdfbox.io.RandomAccessFile
import com.tom_roush.pdfbox.pdfparser.PDFParser
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.tom_roush.pdfbox.text.PDFTextStripper
import com.tom_roush.pdfbox.util.PDFBoxResourceLoader
import org.apache.poi.hwpf.HWPFDocument
import org.apache.poi.hwpf.extractor.WordExtractor
import org.apache.poi.xwpf.usermodel.XWPFDocument
import org.apache.poi.xwpf.usermodel.XWPFParagraph
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader


class DocumentReaderUtil {

    companion object {

        /**
         * @param uri - uri of the file on device
         * @param context - context object
         */
        fun getMimeType(uri: Uri, context: Context?): String? {
            val contentResolver: ContentResolver = context?.contentResolver!!
            return contentResolver.getType(uri)
        }

        /**
         * @param uri - uri of the file on device
         * @param context - context object
         */
        fun readPdfFileContent(uri: Uri?, context: Context?): String {
            PDFBoxResourceLoader.init(context)
            val file = File(context?.let { FilePathHelper(it).getPath(uri!!) }!!)

            val parser = PDFParser(RandomAccessFile(file, "r"))
            parser.parse()

            val cosDoc: COSDocument = parser.document

            val pdfStripper = PDFTextStripper()
            var document: PDDocument? = null
            document = PDDocument(cosDoc)
            pdfStripper.startPage = 0
            pdfStripper.endPage = 1
            return pdfStripper.getText(document)
        }

        /**
         * @param file - the file on device
         * @param context - context object
         */
        fun readPdfFileContent(file: File?, context: Context?): String {
            PDFBoxResourceLoader.init(context)

            val parser = PDFParser(RandomAccessFile(file, "r"))
            parser.parse()

            val cosDoc: COSDocument = parser.document

            val pdfStripper = PDFTextStripper()
            var document: PDDocument? = null
            document = PDDocument(cosDoc)
            pdfStripper.startPage = 0
            pdfStripper.endPage = 1
            return pdfStripper.getText(document)
        }

        /**
         * @param uri - uri of the file on device
         * @param context - context object
         */
        fun readTxtFileContent(uri: Uri?, context: Context?): String {

            val inputStream = context?.contentResolver?.openInputStream(uri!!)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream!!, "UTF-8"))

            val inputString = bufferedReader.use { it.readText() }
            inputStream.close()
            bufferedReader.close()

            return inputString
        }

        /**
         * @param uri - uri of the file on device
         * @param context - context object
         */
        fun readWordDocFile(uri: Uri?, context: Context?): String {

            val file = File(context?.let { FilePathHelper(it).getPath(uri!!) }!!)
            val fullDocumentString: StringBuilder = StringBuilder()
            val fis = FileInputStream(file.absolutePath)

            if (file.extension == "doc") {
                val doc = HWPFDocument(fis)
                val we = WordExtractor(doc)
                val paragraphs: Array<String> = we.paragraphText

                for (para in paragraphs) {
                    fullDocumentString.append(para)
                }
                fis.close()

            } else if (file.extension == "docx") {
                val document = XWPFDocument(fis)
                val paragraphs: List<XWPFParagraph> = document.paragraphs

                for (para in paragraphs) {
                    fullDocumentString.append(para.text)
                }
                fis.close()
            }
            return fullDocumentString.toString()
        }

        /**
         * @param file - the file on device
         * @param context - context object
         */
        fun readWordDocFile(file: File?, context: Context?): String {

            val fullDocumentString: StringBuilder = StringBuilder()
            val fis = FileInputStream(file?.absolutePath!!)

            if (file.extension == "doc") {
                val doc = HWPFDocument(fis)
                val we = WordExtractor(doc)
                val paragraphs: Array<String> = we.paragraphText

                for (para in paragraphs) {
                    fullDocumentString.append(para)
                }
                fis.close()

            } else if (file.extension == "docx") {
                val document = XWPFDocument(fis)
                val paragraphs: List<XWPFParagraph> = document.paragraphs

                for (para in paragraphs) {
                    fullDocumentString.append(para.text)
                }
                fis.close()
            }
            return fullDocumentString.toString()
        }

    }

}