package com.smalldemo.multithreading.traditionalthread;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Jim
 */
public class TraditionalTimer {

    private static int count = 0;

    public static void main(String[] args) {
//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("TimerTask.run()....");
//            }
//        }, 10000, 3000);

//        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("bombing!");
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        System.out.println("bombing!");
//                    }
//                }, 2000);
//            }
//        }, 2000);

        class MyTimerTask extends TimerTask {

            @Override
            public void run() {
                count = (count + 1) % 2;
                System.out.println("bombing!");
                new Timer().schedule(new MyTimerTask(), 2000 + 2000 * count);
            }
        }

        new Timer().schedule(new MyTimerTask(), 2000);

        while (true) {
            System.out.println(Calendar.getInstance().get(Calendar.SECOND));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
