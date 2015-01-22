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

package pt.ua.ieeta.dicoogle.java.dicom;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Luís A. Bastião Silva <bastiao@ua.pt>
 */
public class Image {
    
    private String uri ; 
    private String uid ; 
    private Map<String, String> tags = new HashMap<String, String>();
    public Image(String uri)
    {
        this.uri = uri;
        
    }
    
    public Image(String uid, String uri)
    {
        this.uri = uri;
        this.uid = uid;
    }
    
    public void setTag(String tag, String value)
    {
        tags.put(tag, value);
    }
    
    public String getTag(String tag)
    {
        return tags.get(tag);
    }
    
    
    public String toString()
    {
        return this.uid + ", " + this.uri;
    }

    /**
     * @return the uri
     */
    public String getUri() {
        return uri;
    }

    /**
     * @param uri the uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {
        this.uid = uid;
    }
    

}
