package clientserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Класс сервера для получения данных от клиента.
 */
public class Server {

    public static void main(String[] args) throws IOException {
        int port = 1234;

        ServerSocket server = new ServerSocket(port);

        boolean stopped = false;

        //цикл готовности принятия сообщений пока не придет сообщение bye
        while (!stopped) {
            //подтверждаем работоспособность сервера
            System.out.println("Waiting for connection");

            Socket client = server.accept();

            PrintWriter pw = new PrintWriter(client.getOutputStream(), true);
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

            String str = br.readLine();
            while (!str.equals("server-stop")) {
                System.out.println("Received Message: " + str);

                pw.println("Server's answer:");
                pw.println(str);
                pw.println("end-of-server-message");
                str = br.readLine();
            }
            stopped = true;

            pw.close();
            br.close();
        }
        System.out.println("End of connection");
    }
}
