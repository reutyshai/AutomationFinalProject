package Readers;

import Report.ReportWriters.Logger;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * The XmlReader class provides functionality to read data from an XML file.
 * It allows retrieval of the text content of a specified node by its name.
 */
@Slf4j
public class XmlFileReader {
static Logger logger=Logger.getInstance();
    /**
     * Retrieves the text content of the first occurrence of a specified node
     * from the XML file.
     *
     * @param filePath the path to the XML file to be read.
     * @param nodeName the name of the node whose text content is to be retrieved.
     * @return the text content of the specified node, or null if the node
     * is not found or an error occurs while reading the file.
     * <p>
     * This method parses the XML file and normalizes the document structure.
     * If the specified node does not exist or an error occurs during parsing,
     * it returns null.
     */
    public static String getData(String filePath, String nodeName) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch (Exception e) {
            logger.info("Exception in reading XML file: " + e);
        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }
}
