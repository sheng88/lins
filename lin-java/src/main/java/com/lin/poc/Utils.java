package com.lin.poc;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Utils {

    private static final DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
    private static final TransformerFactory tFactory = TransformerFactory.newInstance();

    public static int getNumOfDaysInCurMonth(){
        int curMon = Calendar.getInstance().get(Calendar.MONTH);
        int curYear = Calendar.getInstance().get(Calendar.YEAR);

        Calendar mycal = new GregorianCalendar(curYear, curMon, 1);
        return mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public static String applyXslt(String xsltFile, String xmlFileStr) {
        String result = null;
        try{
            File stylesheet = new File(xsltFile);
            InputStream xmlInputStream = new ByteArrayInputStream(xmlFileStr.getBytes());


            DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
            Document xmlDoc = dBuilder.parse(xmlInputStream);

            StreamSource stylesource = new StreamSource(stylesheet);
            Transformer transformer = tFactory.newTransformer(stylesource);
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.setParameter("{http://tfa.vv10.rbc.com}delimiter", ",");

            DOMSource domSrc = new DOMSource(xmlDoc);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            StreamResult streamResult = new StreamResult(outStream);

            transformer.transform(domSrc, streamResult);
            ByteArrayOutputStream outStreamResult = (ByteArrayOutputStream) streamResult.getOutputStream();
            result = outStreamResult.toString("UTF-8");

        } catch (Exception ex){
            ex.printStackTrace();
        }
        return result;
    }
}
