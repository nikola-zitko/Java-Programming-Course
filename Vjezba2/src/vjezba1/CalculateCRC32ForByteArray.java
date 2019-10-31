/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vjezba1;


/*Generate CRC32 Checksum For Byte Array Example
This Java example shows how to get the CRC32 checksum value for 
array of bytes using CRC32 Java class.*/
//import java.util.zip.CRC32;
//import java.util.zip.Checksum;

public class CalculateCRC32ForByteArray {

    public static void main(String args[]) {

        String str = "Generate CRC32 Checksum For Byte Array Example";

      
        byte bytes[] = str.getBytes();

        CRC32 checksum = new CRC32();

       
        checksum.update(bytes, 0, bytes.length);
       
        long lngChecksum = checksum.getValue();

        System.out.println("CRC32 checksum for byte array is :" + lngChecksum);
    }
}
