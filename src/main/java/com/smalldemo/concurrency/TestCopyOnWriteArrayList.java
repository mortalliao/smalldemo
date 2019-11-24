package com.smalldemo.concurrency;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Jim
 * <pre>
 * CopyOnWriteArrayList / CopyOnWriteArraySet : "写入并复制"
 *
 * 添加操作多时,效率低, 因为每次添加时都会进行复制, 开销非常大, 并发迭代操作多时可以选择.
 *
 * </pre>
 */
public class TestCopyOnWriteArrayList {

    public static void main(String[] args) {
        HelloThread helloThread = new HelloThread();

        for (int i = 0; i < 10; i++) {
            new Thread(helloThread).start();
        }

    }
}

class HelloThread implements Runnable {

//    private static List<String> list = Collections.synchronizedList(new ArrayList<>());

    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {

        Iterator<String> it = list.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());

            // java.util.ConcurrentModificationException
            list.add("AA");
        }
    }
}
