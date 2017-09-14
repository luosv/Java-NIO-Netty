package nio;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
 * <p>
 * Created by luosv on 2017/9/13 0013.
 */
public class WriteSomeBytes {

    private static final byte MESSAGE[] = {83, 111, 109, 101, 32, 98, 121, 116, 101, 115, 46};

    public static void main(String[] args) throws Exception {
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")
                + File.separator + "src" + File.separator + "nio" + File.separator + "writesomebytes.txt");

        FileChannel channel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        for (byte i : MESSAGE) {
            buffer.put(i);
        }

        buffer.flip();

        channel.write(buffer);

        fos.close();
    }

}
