package nio;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * 文件锁定
 * <p>
 * Created by luosv on 2017/9/15 0015.
 */
public class UseFileLocks {

    private static final int start = 10;
    private static final int end = 20;

    public static void main(String[] args) throws Exception {
        // Get file channel
        RandomAccessFile raf = new RandomAccessFile("usefilelocks.txt", "rw");
        FileChannel fc = raf.getChannel();

        // Get lock
        System.out.println("Trying to get lock");
        FileLock lock = fc.lock(start, end, false);
        System.out.println("got lock");

        // Pause
        System.out.println("Pausing");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Release lock
        System.out.println("going ro release lock");
        lock.release();
        System.out.println("released lock");

        raf.close();
    }

}
