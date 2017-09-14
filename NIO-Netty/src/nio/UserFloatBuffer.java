package nio;

import java.nio.FloatBuffer;

/**
 * http://www.cnblogs.com/lxzh/archive/2013/05/10/3071680.html
 * <p>
 * Created by luosv on 2017/9/13 0013.
 */
public class UserFloatBuffer {

    public static void main(String[] args) throws Exception {
        FloatBuffer buffer = FloatBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); ++i) {
            float f = (float) Math.sin((((float) i) / 10) * (2 * Math.PI));
            buffer.put(f);
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            float f = buffer.get();
            System.out.println(f);
        }
    }

}
