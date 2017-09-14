package nio;

import java.nio.ByteBuffer;

/**
 * Created by luosv on 2017/9/14 0014.
 */
public class SliceBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); ++i) {
            buffer.put((byte) i);
        }

        buffer.flip(); // 这里必须翻转

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

        System.out.println("-------------");

        buffer.position(3);
        buffer.limit(6);

        ByteBuffer slice = buffer.slice();

        for (int i = 0; i < slice.capacity(); ++i) {
            byte b = slice.get(i);
            b *= 11;
            slice.put(i, b);
        }

        //slice.flip(); // 这里不需要翻转

        while (slice.hasRemaining()) {
            System.out.println(slice.get());
        }

        System.out.println("-------------");

        buffer.position(0);
        buffer.limit(buffer.capacity());

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }

}
