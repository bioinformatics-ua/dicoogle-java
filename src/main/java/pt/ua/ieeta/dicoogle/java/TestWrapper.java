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

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class TestWrapper {
    
    public static void main(String [] args)
    {
        long startTime = System.currentTimeMillis();

        DicoogleClient client = new DicoogleClient("http://localhost:6060/");
        client.searchFreeText("CT");
        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Estimated time: "+ estimatedTime);
        startTime = System.currentTimeMillis();
        client.searchFreeText("CT");
        estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println("Estimated time: "+ estimatedTime);
    }

}
