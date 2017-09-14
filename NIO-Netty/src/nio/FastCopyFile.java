package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 直接缓冲区
 * <p>
 * Created by luosv on 2017/9/14 0014.
 */
public class FastCopyFile {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.err.println("Usage: java FastCopyFile infile outfile");
            System.exit(1);
        }

        String inFile = args[0];
        String outFile = args[1];

        FileInputStream fis = new FileInputStream(inFile);
        FileOutputStream fos = new FileOutputStream(outFile);

        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024); // 这儿，创建的时候-直接缓冲区

        while (true) {
            buffer.clear();

            int r = fisChannel.read(buffer);

            if (r == -1) {
                break;
            }

            buffer.flip();

            fosChannel.write(buffer);
        }
    }

}
