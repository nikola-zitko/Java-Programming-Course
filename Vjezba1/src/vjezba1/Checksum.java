/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vjezba1;

/**
 *
 * @author User
 */
public interface Checksum {
        void update(int b);
        void update(byte[] b, int off, int len);
        void update(byte[] buf);
        long getValue();
        void reset();
}
