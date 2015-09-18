/**
 *
 * Copyright (C) 2015 - Luís A. Bastião Silva and Universidade de Aveiro This
 * program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */


package pt.ua.ieeta.dicoogle.java.responses;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import pt.ua.ieeta.dicoogle.java.dicom.Image;
import pt.ua.ieeta.dicoogle.java.dicom.Patient;
import pt.ua.ieeta.dicoogle.java.dicom.QueryLevel;
import pt.ua.ieeta.dicoogle.java.dicom.Serie;
import pt.ua.ieeta.dicoogle.java.dicom.Study;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class XMLResponses {
    
    private QueryLevel level;
    private boolean deep;
    private List<Image> resultImages;
    private List<Serie> resultSeries;
    private List<Study> resultStudies;
    private List<Patient> resultPatients;
    
    
    public XMLResponses(String response, QueryLevel level, boolean deep )
    {
        
        this.deep = deep;
        this.level = level;
        
        if (level==QueryLevel.IMAGE)
        {
            parseImageLevel(response);
        }
        else if (level==QueryLevel.SERIES)
        {
            parseSerieLevel(response);
        }
        else if (level==QueryLevel.STUDY)
        {
            parseStudyLevel(response);
        }
        else if (level==QueryLevel.PATIENT)
        {
            parsePatientLevel(response);
        }
        
        else {
            // TODO
            throw new UnsupportedOperationException("Not supported yet."); 
        }
        
    }
    
    
    public List<Image> getImageResults(){
        return this.resultImages;
    }
    
    public List<Serie> getSerieResults(){
        return this.resultSeries;
    }
    
    public List<Study> getStudyResults(){
        return this.resultStudies;
    }
    
    public List<Patient> getPatientResults(){
        return this.resultPatients;
    }
    
    
    
    private void parsePatientLevel(String response )
    {
        
        
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
        

        XPathFactory xpathf = XPathFactory.newInstance();
        XPath xpath = xpathf.newXPath();
        XPathExpression expr = null;
        try {
            expr = xpath.compile("/DIM/Patient");
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object res = null;
        try {
            res = expr.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        NodeList item = (NodeList) res;
        
        if (item.getLength()==0)
            return;
        
        // Iterate over the results according with the level 
        List<Patient> results = new ArrayList<Patient>();
        for (int i = 0 ; i<item.getLength(); i++)
        {
            Node itemNode = item.item(i);
            String patientID = itemNode.getAttributes().getNamedItem("id").getNodeValue();
            String patientName = itemNode.getAttributes().getNamedItem("name").getNodeValue();
            
            //TODO: The study part need to be fixed
            Patient patient = new Patient(patientID, patientName);
            results.add(patient);
            
        }
        this.resultPatients = results;
    
    }
    
    private void parseStudyLevel(String response )
    {
        
        
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
        

        XPathFactory xpathf = XPathFactory.newInstance();
        XPath xpath = xpathf.newXPath();
        XPathExpression expr = null;
        try {
            expr = xpath.compile("/DIM/Patient/Study");
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object res = null;
        try {
            res = expr.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        NodeList item = (NodeList) res;

        
        if (item.getLength()==0)
            return;
        
        // Iterate over the results according with the level 
        List<Study> results = new ArrayList<Study>();
        for (int i = 0 ; i<item.getLength(); i++)
        {
            Node itemNode = item.item(i);
            String studyDate = itemNode.getAttributes().getNamedItem("date").getNodeValue();
            String studyInstanceUID = itemNode.getAttributes().getNamedItem("id").getNodeValue();
            
            //TODO: The study part need to be fixed
            Study study = new Study(null, studyInstanceUID, studyDate);
            results.add(study);
            
        }
        this.resultStudies = results;
    
    }
    
    private void parseSerieLevel(String response )
    {
        
        
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
        

        XPathFactory xpathf = XPathFactory.newInstance();
        XPath xpath = xpathf.newXPath();
        XPathExpression expr = null;
        try {
            expr = xpath.compile("/DIM/Patient/Study/Serie");
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object res = null;
        try {
            res = expr.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        NodeList item = (NodeList) res;

        if (item.getLength()==0)
            return;
        
        // Iterate over the results according with the level 
        List<Serie> results = new ArrayList<Serie>();
        for (int i = 0 ; i<item.getLength(); i++)
        {
            Node itemNode = item.item(i);
            String modality = itemNode.getAttributes().getNamedItem("modality").getNodeValue();
            String seriesInstanceUID = itemNode.getAttributes().getNamedItem("id").getNodeValue();
            
            //TODO: The study part need to be fixed
            Serie serie = new Serie(null, seriesInstanceUID, modality);
            results.add(serie);
            
        }
        this.resultSeries = results;
    
    }
    
    private void parseImageLevel(String response){
        List<String> attributes = new ArrayList<String>();
        attributes.add("ViewPosition");
        attributes.add("InstanceNumber");
        attributes.add("NumberOfFrames");
        
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
        

        XPathFactory xpathf = XPathFactory.newInstance();
        XPath xpath = xpathf.newXPath();
        XPathExpression expr = null;
        try {
            expr = xpath.compile("//Image");
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object res = null;
        try {
            res = expr.evaluate(doc, XPathConstants.NODESET);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XMLResponses.class.getName()).log(Level.SEVERE, null, ex);
        }
        NodeList item = (NodeList) res;

        
        if (item.getLength()==0)
            return;
        
        
        // Iterate over the results according with the level 
        List<Image> listImages = new ArrayList<Image>();
        for (int i = 0 ; i<item.getLength(); i++)
        {
            Node itemNode = item.item(i);
            
            Image image = new Image(itemNode.getAttributes().getNamedItem("uid").getNodeValue(),
                    itemNode.getAttributes().getNamedItem("path").getNodeValue());
            
            // List other attributes 
            for (String a : attributes)
            {
                try
                {
                    image.setTag(a, itemNode.getAttributes().getNamedItem(a).getNodeValue());
                }
                catch (Exception e)
                {
                    
                }
                
            }
            listImages.add(image);
            
        }
        this.resultImages = listImages;


    }
}
