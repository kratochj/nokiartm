/**
 * mobile-model created at Aug 4, 2008
 */
package eu.kratochvil.rtm.nokia.util;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * 
 * @author karol.bucek@jetminds.com
 *
 */
public final class ByteDataInput extends ByteArrayInputStream implements DataInput {
    
    //private static final byte[] EMPTY_BUFF = new byte[0];
    
    /**
     */
    public ByteDataInput() {
        super(new byte[0]);
    }
    
    /*
    public byte[] getBuffer() {
        return this.buf;
    }
    */
    
    /**
     * Sets the internal byte buffer.
     * @param buf the new byte buffer
     */
    public void setBuffer(byte[] buf) {
        //super.reset();
        this.buf = buf;
        this.pos = 0;
        this.count = buf.length;
        this.mark = 0;
    }

    /**
     * Sets the internal byte buffer.
     * @param buf the new byte buffer
     * @param offset
     * @param length
     */
    public void setBuffer(byte[] buf, int offset, int length) {
        //super.reset();
        this.buf = buf;
        this.pos = offset;
        this.count = Math.min(offset + length, buf.length);
        this.mark = offset;
    }
    
    private final DataInputStream dataInputStream = new DataInputStream(this);
    
    /**
     * @return read data
     * @throws IOException
     * @see java.io.DataInputStream#readBoolean()
     */
    public boolean readBoolean() throws IOException {
        return dataInputStream.readBoolean();
    }

    /**
     * @return read data
     * @throws IOException
     * @see java.io.DataInputStream#readByte()
     */
    public byte readByte() throws IOException {
        return dataInputStream.readByte();
    }

    /**
     * @return read data
     * @throws IOException
     * @see java.io.DataInputStream#readChar()
     */
    public char readChar() throws IOException {
        return dataInputStream.readChar();
    }

    /**
     * @return read data
     * @throws IOException
     * @see java.io.DataInputStream#readDouble()
     */
    public double readDouble() throws IOException {
        return dataInputStream.readDouble();
    }

    /**
     * @return read data
     * @throws IOException
     * @see java.io.DataInputStream#readFloat()
     */
    public float readFloat() throws IOException {
        return dataInputStream.readFloat();
    }

    /**
     * @param b
     * @param off
     * @param len
     * @throws IOException
     * @see java.io.DataInputStream#readFully(byte[], int, int)
     */
    public void readFully(byte[] b, int off, int len) throws IOException {
        dataInputStream.readFully(b, off, len);
    }

    /**
     * @param b
     * @throws IOException
     * @see java.io.DataInputStream#readFully(byte[])
     */
    public void readFully(byte[] b) throws IOException {
        dataInputStream.readFully(b);
    }

    /**
     * @return read data
     * @throws IOException
     * @see java.io.DataInputStream#readInt()
     */
    public int readInt() throws IOException {
        return dataInputStream.readInt();
    }

    /**
     * @return read data
     * @throws IOException
     * @see java.io.DataInputStream#readLong()
     */
    public long readLong() throws IOException {
        return dataInputStream.readLong();
    }

    /**
     * @return read data
     * @throws IOException
     * @see java.io.DataInputStream#readShort()
     */
    public short readShort() throws IOException {
        return dataInputStream.readShort();
    }

    /**
     * @return read data
     * @throws IOException
     * @see java.io.DataInputStream#readUnsignedByte()
     */
    public int readUnsignedByte() throws IOException {
        return dataInputStream.readUnsignedByte();
    }

    /**
     * @return read data
     * @throws IOException
     * @see java.io.DataInputStream#readUnsignedShort()
     */
    public int readUnsignedShort() throws IOException {
        return dataInputStream.readUnsignedShort();
    }

    /**
     * @return read data
     * @throws IOException
     * @see java.io.DataInputStream#readUTF()
     */
    public String readUTF() throws IOException {
        return dataInputStream.readUTF();
    }

    /**
     * @see java.io.DataInput#skipBytes(int)
     */
    public int skipBytes(int n) throws IOException {
        return dataInputStream.skipBytes(n);
    }

    /**
     * @see java.io.DataInput#readLine()
     */
    public String readLine() {
        //throw new Error("readLine() not supported");
        Asserts.fail("ByteDataInput.readLine()");
        return null;
    }
    
}
