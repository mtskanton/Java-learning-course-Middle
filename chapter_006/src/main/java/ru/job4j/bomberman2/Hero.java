package ru.job4j.bomberman2;

public class Hero extends Character {

    Hero(Board board, String name, int x, int y) {
        super(board, name, x, y);
    }

    public void run() {
        //блокируем ячейку, на которой находимся
        this.cell.lock();

        //герой перемещается каждую секунду до остановки игры
        while (!stopped) {
            int[] direction = getDirection();
            int moveX = this.x + direction[0];
            int moveY = this.y + direction[1];

            //если новые координаты в рамках поля
            if (moveX >= 0 && moveX < board.getWidth() && moveY >= 0 && moveY < board.getHeight()) {
                try {
                    move(moveX, moveY);

                    //герой должен каждую секунду двигаться на новую клетку.
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
