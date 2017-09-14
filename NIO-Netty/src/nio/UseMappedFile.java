package nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 将文件映射到内存
 * <p>
 * Created by luosv on 2017/9/14 0014.
 */
public class UseMappedFile {

    private static final int start = 0;
    private static final int size = 1024;

    public static void main(String[] args) throws Exception {
        RandomAccessFile raf = new RandomAccessFile("usemappedfile.txt", "rw");
        FileChannel fc = raf.getChannel();

        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, start, size);

        mbb.put(0, (byte) 97);
        mbb.put(1023, (byte) 122);

        raf.close();
    }

}
