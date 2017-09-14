package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * https://www.ibm.com/developerworks/cn/education/java/j-nio/j-nio.html
 * <p>
 * Created by luosv on 2017/9/13 0013.
 */
public class CopyFile {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: java CopyFile infile outfile");
            System.exit(1);
        }

        String inFile = args[0];
        String outFile = args[1];

        FileInputStream fis = new FileInputStream(inFile);
        FileOutputStream fos = new FileOutputStream(outFile);

        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (true) {
            buffer.clear();

            int r = inChannel.read(buffer);

            if (r == -1) {
                break;
            }

            buffer.flip();

            outChannel.write(buffer);
        }
    }

}
