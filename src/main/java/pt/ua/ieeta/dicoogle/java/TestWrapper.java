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


package pt.ua.ieeta.dicoogle.java;

import java.util.List;
import pt.ua.ieeta.dicoogle.java.dicom.Image;
import pt.ua.ieeta.dicoogle.java.dicom.QueryLevel;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class TestWrapper {
    
    public static void main(String [] args)
    {
        long startTime = System.currentTimeMillis();

        DicoogleClient client = new DicoogleClient("http://localhost:6060/");
        client.searchFreeText("CT", QueryLevel.IMAGE, false);
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Estimated time: "+ estimatedTime);
        startTime = System.currentTimeMillis();
        List<Image> results= null;
        results = (List)client.searchFreeText("CT", QueryLevel.IMAGE, false);
        for( int i = 0; i<1000 ; i++)
        {
        
        Thread t = new Thread(new Runnable() {

                        public void run() {
                            
                            DicoogleClient client = new DicoogleClient("http://localhost:6060/");
                            client.searchAdvanced(":*:");
                        }
        });
        t.start();
        }
        estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Estimated time: "+ estimatedTime);
        
        System.out.println(client.dump("0.0.0.0.1.8811.2.1.20010413115754.12432"));
    }

}
