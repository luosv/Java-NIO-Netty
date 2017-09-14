package nio;

import java.nio.ByteBuffer;

/**
 * Created by luosv on 2017/9/14 0014.
 */
public class CreateBuffer {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put((byte) 'a');
        buffer.put((byte) 'b');
        buffer.put((byte) 'c');

        buffer.flip();

        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());

        ByteBuffer readOnly = buffer.asReadOnlyBuffer(); // 只读缓冲区 方法返回一个与原缓冲区完全相同的缓冲区(并与其共享数据)，只不过它是只读的

        System.out.println("--------");

        readOnly.flip();

        while (readOnly.hasRemaining()) {
            System.out.println((char) readOnly.get());
        }

        //readOnly.put((byte) 'd'); // 不能写入数据 异常：java.nio.ReadOnlyBufferException
    }

}
