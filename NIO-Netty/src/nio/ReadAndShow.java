package nio;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
 * <p>
 * Created by luosv on 2017/9/13 0013.
 */
public class ReadAndShow {

    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
                + File.separator + "src" + File.separator + "nio" + File.separator + "readandshow.txt");

        FileChannel channel = fis.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        channel.read(buffer);

        buffer.flip();

        while (buffer.hasRemaining()) {
            byte b = buffer.get();
            System.out.print((char) b);
        }

        fis.close();
    }

}
