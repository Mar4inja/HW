package task_3_CoutDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;


public class Passenger extends Thread {
    private static CountDownLatch latch;
    public Passenger(String name, CountDownLatch latch) {
        this.latch = latch;
        setName(name);
        start();
    }

    @Override
    public void run() {
        try {
            long start = System.currentTimeMillis();
            Random random = new Random();
            int millis = random.nextInt(9000) + 1000;
            Thread.sleep(millis);
            System.out.printf("%s вошёл в аэропорт.%n", getName());

            Thread.sleep(millis);
            System.out.printf("%s сдал багаж и зарегистрировался.%n", getName());

            Thread.sleep(millis);
            System.out.printf("%s прошёл паспортный контроль.%n", getName());



            Thread.sleep(millis);
            System.out.printf("%s совершает посадку в самолёт.%n", getName());
            latch.countDown();

            try {
                latch.await();
                System.out.printf("Пассажиры выполнили все задания!.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Thread.sleep(millis);

            System.out.printf("%s вылетел, время вылета - %d.%n",
                    getName(), (System.currentTimeMillis() - start) / 1000);

        } catch (InterruptedException e) {
            System.out.println("Ошибка!");
        }
    }
}