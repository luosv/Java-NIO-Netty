package nio;

import java.nio.ByteBuffer;

/**
 * Created by luosv on 2017/9/14 0014.
 */
public class CreateArrayBuffer {

    public static void main(String[] args) {
        byte array[] = new byte[1024];

        ByteBuffer buffer = ByteBuffer.wrap(array);

        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');

        buffer.flip();

        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
    }

}
