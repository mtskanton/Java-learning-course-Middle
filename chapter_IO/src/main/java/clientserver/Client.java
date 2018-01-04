package clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Класс клиента.
 * Ввод из консоли отправляется к серверу
 * В ответ получаются данные от сервера и выводятся в консоль
 * Для окончания работы ввести в консоли "q"
 */
public class Client {

    public static void main(String[] args) throws IOException {

        int portNumber = 1234;

        System.out.println("Client is started");

        Socket socket = new Socket("127.0.0.1", portNumber);

        //поток чтения из консоли
        BufferedReader brc = new BufferedReader(new InputStreamReader(System.in));

        //поток для чтения из сокета
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //поток для записи символов в сокет
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        String console = brc.readLine();

        while (!console.equals("q")) {
            //передаем сообщение серверу
            pw.println(console);

            //печатаем ответ от сервера на консоль для проверки
            String answer = br.readLine();
            while (!answer.equals("end-of-server-message")) {
                System.out.println(answer);
                answer = br.readLine();
            }

            console = brc.readLine();
        }

        //посылаем серверу команду для остановки работы
        pw.println("server-stop");

        brc.close();
        br.close();
        pw.close();
        socket.close();
    }
}
