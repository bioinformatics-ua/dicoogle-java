/**
 *
 * Copyright (C) 2013 - Luís A. Bastião Silva and Universidade de Aveiro This
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.restlet.resource.ClientResource;

/**
 *
 * @author bastiao
 */
public class DicoogleClient implements IDicoogleClient
{

    
    private String endPoint = "http://localhost:";
    
    public DicoogleClient(String endPoint)
    {
        this.endPoint = endPoint;
    }
    
    
    public List<String> searchFreeText(String query) {
        // Create the client resource  
        ClientResource resource = new ClientResource(this.endPoint + EndPoints.DIM + "?q=" + query);

        // Customize the referrer property  
        
        try {
            // Write the response entity on the console
            resource.get().write(System.out);
        } catch (IOException ex) {
            Logger.getLogger(DicoogleClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
        
    }

    public List<String> searchAdvanced(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> dump(String sopInstanceUID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void download(String sopInstanceUID, File dest) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int numberOfResults(String query) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
