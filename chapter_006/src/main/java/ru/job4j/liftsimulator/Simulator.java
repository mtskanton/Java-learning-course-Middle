package ru.job4j.liftsimulator;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Симулятор движения лифта.
 * Лифт останавливается на этаже, если этот этаж запрошен и находится по пути движения.
 */
public class Simulator {

    //кол-во этажей в подъезде (может быть от 5 до 20)
    private int floors;

    //очередность этажей
    PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();

    //класс лифта для запуска в отдельном потоке
    private Lift lift;

    Simulator(int floors, double floorHeight, double speed, long waiting) {
        this.floors = floors;
        lift = new Lift(this.queue, floorHeight, speed, waiting);
    }

    /**
     * Метод запуска движения лифта.
     */
    public void start() {
        if (this.floors < 5 || this.floors > 20) {
            throw new WrongFloorException("Этажность должна быть в диапазоне от 5 до 20 этажей");
        }
        new Thread(this.lift).start();
    }

    /**
     * Метод приостановки движения лифта.
     */
    public void doStop() {
        this.lift.doStop();
    }

    /**
     * Вызов этажа.
     * @param floor запрошенный этаж
     */
    public void requestFloor(int floor) {
        if (floor < 1 || floor > this.floors) {
            throw new WrongFloorException("Этаж задан неверно");
        }
        //если в очереди этого этажа нет, то добавляем
        if (!this.queue.contains(floor)) {
            this.queue.add(floor);
        }
    }

    public static void main(String[] args) {
        Simulator s = new Simulator(20, 2.7, 0.9, 1000);
        s.start();

        try {
            Thread.sleep(2000);
            s.requestFloor(7);
            Thread.sleep(2000);
            s.requestFloor(3);
            Thread.sleep(2000);
            s.requestFloor(3);
            Thread.sleep(2000);
            s.requestFloor(3);
            Thread.sleep(5000);
            s.requestFloor(1);
            Thread.sleep(4000);
            s.requestFloor(2);

            Thread.sleep(12000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        s.doStop();
    }
}
