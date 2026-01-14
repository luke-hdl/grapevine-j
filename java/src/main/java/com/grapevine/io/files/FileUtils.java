package com.grapevine.io.files;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Utility class for common file operations and XML handling.
 */
public class FileUtils {
    
    private static final SimpleDateFormat[] DATE_FORMATS = {
        new SimpleDateFormat("M/d/yyyy h:mm:ss a", Locale.US),
        new SimpleDateFormat("M/d/yyyy", Locale.US),
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US),
        new SimpleDateFormat("yyyy-MM-dd", Locale.US)
    };
    
    /**
     * Parse a Document from an XML file.
     */
    public static Document parseXmlFile(String filePath) throws Exception {
        File file = new File(filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(file);
    }
    
    /**
     * Create a new XML Document.
     */
    public static Document createDocument() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.newDocument();
    }
    
    /**
     * Write a Document to an XML file.
     */
    public static void writeXmlFile(Document doc, String filePath) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
    }
    
    /**
     * Get an attribute value as a String, or return an empty string if not present.
     */
    public static String getAttribute(Element element, String name) {
        return getAttribute(element, name, "");
    }
    
    /**
     * Get an attribute value as a String, or return a default value if not present.
     */
    public static String getAttribute(Element element, String name, String defaultValue) {
        String value = element.getAttribute(name);
        return value.isEmpty() ? defaultValue : value;
    }
    
    /**
     * Get an attribute value as an integer.
     */
    public static int getIntAttribute(Element element, String name, int defaultValue) {
        String value = element.getAttribute(name);
        if (value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Get an attribute value as a float.
     */
    public static float getFloatAttribute(Element element, String name, float defaultValue) {
        String value = element.getAttribute(name);
        if (value.isEmpty()) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Get an attribute value as a boolean.
     */
    public static boolean getBooleanAttribute(Element element, String name, boolean defaultValue) {
        String value = element.getAttribute(name);
        if (value.isEmpty()) {
            return defaultValue;
        }
        return "yes".equalsIgnoreCase(value) || "true".equalsIgnoreCase(value);
    }
    
    /**
     * Get an attribute value as a Date.
     */
    public static Date getDateAttribute(Element element, String name) {
        String value = element.getAttribute(name);
        if (value.isEmpty()) {
            return null;
        }
        
        for (SimpleDateFormat format : DATE_FORMATS) {
            try {
                return format.parse(value);
            } catch (ParseException e) {
                // Try next format
            }
        }
        
        return null;
    }
    
    /**
     * Format a Date for XML output.
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return DATE_FORMATS[0].format(date);
    }
    
    /**
     * Get child elements by tag name.
     */
    public static List<Element> getChildElements(Element parent, String tagName) {
        List<Element> elements = new ArrayList<>();
        NodeList nodeList = parent.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE && node.getParentNode() == parent) {
                elements.add((Element) node);
            }
        }
        return elements;
    }
    
    /**
     * Get the first child element by tag name.
     */
    public static Element getFirstChildElement(Element parent, String tagName) {
        NodeList nodeList = parent.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE && node.getParentNode() == parent) {
                return (Element) node;
            }
        }
        return null;
    }
    
    /**
     * Get all direct child elements.
     */
    public static List<Element> getDirectChildElements(Element parent) {
        List<Element> elements = new ArrayList<>();
        NodeList nodeList = parent.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                elements.add((Element) node);
            }
        }
        return elements;
    }
    
    /**
     * Get CDATA or text content from an element.
     */
    public static String getTextContent(Element element) {
        if (element == null) {
            return "";
        }
        return element.getTextContent().trim();
    }
    
    /**
     * Create a CDATA section in the document.
     */
    public static void setCDataContent(Document doc, Element element, String content) {
        if (content != null && !content.isEmpty()) {
            element.appendChild(doc.createCDATASection(content));
        }
    }
    
    /**
     * Convert a Document to a String for debugging.
     */
    public static String documentToString(Document doc) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            return writer.getBuffer().toString();
        } catch (Exception e) {
            return "";
        }
    }
}
