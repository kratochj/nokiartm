/**
 * mobile-model created at Aug 4, 2008
 */
package eu.kratochvil.rtm.nokia.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Byte based output stream that implements {@link DataOutput}.
 * 
 * @author karol.bucek@jetminds.com
 */
public final class ByteDataOutput extends ByteArrayOutputStream implements DataOutput {
    
    /**
     */
    public ByteDataOutput() {
        super(64);
    }
    
    /**
     * Returns the internal byte buffer.
     * @return byte buffer
     */
    public byte[] getBuffer() {
        return this.buf;
    }
    
    private final DataOutputStream dataOutputStream = new DataOutputStream(this);

    /**
     * @param v
     * @throws IOException
     * @see java.io.DataOutputStream#writeBoolean(boolean)
     */
    public void writeBoolean(boolean v) throws IOException {
        dataOutputStream.writeBoolean(v);
    }

    /**
     * @param v
     * @throws IOException
     * @see java.io.DataOutputStream#writeByte(int)
     */
    public void writeByte(int v) throws IOException {
        dataOutputStream.writeByte(v);
    }

    /**
     * @param v
     * @throws IOException
     * @see java.io.DataOutputStream#writeChar(int)
     */
    public void writeChar(int v) throws IOException {
        dataOutputStream.writeChar(v);
    }

    /**
     * @param s
     * @throws IOException
     * @see java.io.DataOutputStream#writeChars(java.lang.String)
     */
    public void writeChars(String s) throws IOException {
        dataOutputStream.writeChars(s);
    }

    /**
     * @param v
     * @throws IOException
     * @see java.io.DataOutputStream#writeDouble(double)
     */
    public void writeDouble(double v) throws IOException {
        dataOutputStream.writeDouble(v);
    }

    /**
     * @param v
     * @throws IOException
     * @see java.io.DataOutputStream#writeFloat(float)
     */
    public void writeFloat(float v) throws IOException {
        dataOutputStream.writeFloat(v);
    }

    /**
     * @param v
     * @throws IOException
     * @see java.io.DataOutputStream#writeInt(int)
     */
    public void writeInt(int v) throws IOException {
        dataOutputStream.writeInt(v);
    }

    /**
     * @param v
     * @throws IOException
     * @see java.io.DataOutputStream#writeLong(long)
     */
    public void writeLong(long v) throws IOException {
        dataOutputStream.writeLong(v);
    }

    /**
     * @param v
     * @throws IOException
     * @see java.io.DataOutputStream#writeShort(int)
     */
    public void writeShort(int v) throws IOException {
        dataOutputStream.writeShort(v);
    }

    /**
     * @param str
     * @throws IOException
     * @see java.io.DataOutputStream#writeUTF(java.lang.String)
     */
    public void writeUTF(String str) throws IOException {
        dataOutputStream.writeUTF(str);
    }

    /**
     * @see java.io.DataOutput#writeBytes(java.lang.String)
     */
    public void writeBytes(String s) {
        //throw new Error("writeBytes() not supported");
        Asserts.fail("ByteDataOutput.writeBytes()");
    }
    
}
