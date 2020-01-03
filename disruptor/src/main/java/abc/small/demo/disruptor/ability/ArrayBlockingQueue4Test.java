package abc.small.demo.disruptor.ability;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Jim
 */
public class ArrayBlockingQueue4Test {

    public static void main(String[] args) {
        final ArrayBlockingQueue<Data> queue = new ArrayBlockingQueue<Data>(100000000);
        final long startTime = System.currentTimeMillis();

        new Thread(() -> {
            long i = 0;
            while (i < Constants.EVENT_NUM_FM) {
                Data data = new Data(i, "C" + i);
                try {
                    queue.put(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int k = 0;
                while (k < Constants.EVENT_NUM_FM) {
                    try {
                        queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    k++;
                }
                long endTime = System.currentTimeMillis();
                System.out.println("ArrayBlockingQueue costTime = " + (endTime - startTime) + "ms");
            }
        }).start();
    }
}
