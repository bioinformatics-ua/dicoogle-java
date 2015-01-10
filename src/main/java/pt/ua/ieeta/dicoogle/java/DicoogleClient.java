/**
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

package pt.ua.ieeta.dicoogle.java;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.restlet.resource.ClientResource;
import pt.ua.ieeta.dicoogle.java.dicom.QueryLevel;
import pt.ua.ieeta.dicoogle.java.responses.XMLDumpResponses;
import pt.ua.ieeta.dicoogle.java.responses.XMLResponses;

/**
 * 
 * @author Luís A. Bastião Silva <bastiao@ua.pt>
 */
public class DicoogleClient implements IDicoogleClient
{

    private String endPoint = "http://localhost:";
    
    public DicoogleClient(String endPoint)
    {
        this.endPoint = endPoint;
    }
    
    public List<Object> searchFreeText(String query, QueryLevel level, boolean deep) {
        // Create the client resource  
        ClientResource resource = new ClientResource(this.endPoint + EndPoints.DIM + "?q=" + query);

        // Customize the referrer property  
        XMLResponses xmlResponse = null ; 
        try {
            // Write the response entity on the console
            StringWriter writer = new StringWriter();
            resource.get().write(writer);
            
            xmlResponse = new XMLResponses(writer.getBuffer().toString(), level, deep);
        } catch (IOException ex) {
            Logger.getLogger(DicoogleClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return (List)xmlResponse.getImageResults();
    }

    public List<Object> searchAdvanced(String query) {
        return this.searchAdvanced(query, QueryLevel.IMAGE, false);
    }

    /**
     * Dump tags of a image
     * @param sopInstanceUID
     * @return map with a tags and values 
     */
    public Map<String,String> dump(String sopInstanceUID) {
        ClientResource resource = new ClientResource(this.endPoint + EndPoints.DUMP + "?uid=" + sopInstanceUID);
        
        // Customize the referrer property  
        XMLDumpResponses xmlResponse = null ; 
        try {
            // Write the response entity on the console
            StringWriter writer = new StringWriter();
            resource.get().write(writer);
            
            String dumpXMLStr= writer.getBuffer().toString();
            xmlResponse = new XMLDumpResponses(dumpXMLStr);
            
        } catch (IOException ex) {
            Logger.getLogger(DicoogleClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return xmlResponse.getTags(); 

    }

    public void download(String sopInstanceUID, File dest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int numberOfResults(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Object> searchFreeText(String query) {
        return this.searchFreeText(query, QueryLevel.IMAGE, false);
    }

    public List<Object> searchAdvanced(String query, QueryLevel level, boolean deep) {
        // Create the client resource  
        ClientResource resource = new ClientResource(this.endPoint + EndPoints.DIM + "?advq=" + query);

        // Customize the referrer property  
        
        try {
            // Write the response entity on the console
            resource.get().write(System.out);
        } catch (IOException ex) {
            Logger.getLogger(DicoogleClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
}
