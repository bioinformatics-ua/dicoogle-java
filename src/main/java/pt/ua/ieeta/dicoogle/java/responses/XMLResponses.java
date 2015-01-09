
package pt.ua.ieeta.dicoogle.java.responses;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class XMLResponses {
    
    
    public XMLResponses(String response){
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        InputSource is = new InputSource(new StringReader(response));
        Document doc = null;
        try {
            doc = docBuilder.parse(is);
        } catch (SAXException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (doc!=null)
        {
            doc.getDocumentElement().normalize();
        }
        

    }
}
