package nio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * 字符集
 * <p>
 * Created by luosv on 2017/9/15 0015.
 */
public class UseCharsets {

    public static void main(String[] args) throws Exception {
        String inputFile = "sampleIn.txt";
        String outputFile = "sampleOut.txt";

        RandomAccessFile inf = new RandomAccessFile(inputFile, "r");
        RandomAccessFile outf = new RandomAccessFile(outputFile, "rw");
        long inputLength = new File(inputFile).length();

        FileChannel inc = inf.getChannel();
        FileChannel outc = outf.getChannel();

        MappedByteBuffer inputData = inc.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        Charset latin = Charset.forName("ISO-8859-1");
        CharsetDecoder decoder = latin.newDecoder();
        CharsetEncoder encoder = latin.newEncoder();

        CharBuffer cb = decoder.decode(inputData);

        // Process char data here

        ByteBuffer outputData = encoder.encode(cb);

        outc.write(outputData);

        inf.close();
        outf.close();
    }

}
