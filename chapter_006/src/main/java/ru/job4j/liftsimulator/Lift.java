package ru.job4j.liftsimulator;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Класс лифта.
 * Запускается в отдельном потоке. Использует общий ресурс: очередь вызовов.
 */
public class Lift implements Runnable {

    //высота одного этажа, м
    private double floorHeight;

    //скорость лифта при движении, м/с
    private double speed;

    //время между открытием и закрытием дверей, мс
    private long waiting;

    //текущий этаж
    private int currentFloor = 1;

    //очередь вызовов этажей
    private PriorityBlockingQueue<Integer> queue;

    //флаг остановки работы лифта
    private volatile boolean stopped = false;

    Lift(PriorityBlockingQueue<Integer> queue, double floorHeight, double speed, long waiting) {
        this.queue = queue;
        this.floorHeight = floorHeight;
        this.speed = speed;
        this.waiting = waiting;
    }

    public void run() {
        while (!stopped) {
            try {
                this.goToFloor(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод перемещения лифта к запрошенному этажу.
     * @param requestedFloor запрошенный этаж
     */
    private void goToFloor(Integer requestedFloor) {
        System.out.println(String.format("Лифт движется с этажа %s на этаж %s", this.currentFloor, requestedFloor));

        while (!(this.currentFloor == requestedFloor)) {
            try {
                //поток приостанавливается на время, необходимое на перемещение лифта через пролет
                Thread.sleep((long) (this.floorHeight / this.speed * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (currentFloor < requestedFloor) {
                //едем вверх
                this.currentFloor++;
            } else {
                //едем вниз
                this.currentFloor--;
            }

            System.out.println(String.format("Lift is on the floor %s", this.currentFloor));

            //если этаж текущего нахождения вызывали, то открываем двери
            if (this.queue.contains(this.currentFloor)) {
                this.openDoors();
                this.queue.remove(this.currentFloor);
            }
        }

        this.openDoors();
    }

    /**
     * Метод открытия дверей.
     */
    private void openDoors() {
        System.out.println("двери открываются");
        try {
            Thread.sleep(this.waiting);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("двери закрываются");
    }

    /**
     * Метод остановки лифта.
     */
    public void doStop() {
        this.stopped = true;
        Thread.currentThread().interrupt();
    }
}
