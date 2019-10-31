package vjezba1;


public class CRC32 implements Checksum {

    private long crc;
    CRC32() {
        crc=0L;
    }
    @Override
    public long getValue() {
        return crc;
    }
    @Override
    public void reset() {
        crc=0L;
    }
    @Override
    public void update(byte[] b) {
        this.update(b, 0, b.length);
    }
    
    @Override
    public void update(byte[] b, int off, int len) {
//reverse bits in each byte of b[]
        int input = 0, n = 7;
//append 32 zero bits
        int[] newarray = new int[len + 4];
        for (int i = off; i < len; i++) {
            while (n > 0) {
                input += (b[i] & 1) << n;
                b[i] >>= 1;
                n--;
            }
            n = 7;
            newarray[i] = input;
            input = 0;
        }
//xor the first 4 bytes with 0xFFFFFFFF
        for (int i = 0; i < 4; i++) {
            newarray[i] ^= 0xFF;
        }
//CRC32 devision
        long poly = 0x104C11DB7L;
        long msg = 0;
        //first 32 bits...
        for (int i = 0; i < 4; i++) {
            msg += newarray[i];
            if (i != 3) {
                msg <<= 8;
            }
        }
        int mask = 128;
        int shift = 7;
        long cmp = 0x100000000L;
        //33. bit
        msg <<= 1;
        msg += ((newarray[4] & mask) >> shift);
        int numb_of_steps = ((newarray.length - 4) * 8) - 1;
        int current_index = 4;
        int br = 1;

        while (numb_of_steps > 0) {
            if (br == 8 && (current_index < newarray.length)) {
                current_index++;
                br = 0;
                mask = 128 * 2;
                shift = 8;
            }
            if ((msg & cmp) == 0) {
                br++;
                mask /= 2;
                shift--;
                msg <<= 1;
                msg += ((newarray[current_index] & mask) >> shift);
                numb_of_steps--;
            }
            msg ^= poly;
        }
        while (msg > 0xFFFFFFFFL) {
            msg ^= poly;
        }
//XOR the remainder with 0xFFFFFFFF
        msg ^= 0xFFFFFFFFL;
//reverse bits
        int num = 31;
        long remainder = 0L;
        while (num >= 0) {
            remainder += ((msg & 1) << num);
            msg >>= 1;
            num--;
        }
        crc = remainder;
    }
    public void update(int b) {
        byte bb[]=new byte[1];
        bb[0]=(byte) b;
        this.update(bb, 0, 1);
    }
}
