package nio;

import java.nio.ByteBuffer;

/**
 * Created by luosv on 2017/9/14 0014.
 */
public class TypesInByteBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.clear();

        buffer.putInt(30);
        buffer.putLong(10000000000L);
        buffer.putDouble(Math.PI);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getDouble());
    }

}
