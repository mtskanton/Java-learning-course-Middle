package ru.job4j.file;

import java.io.*;

public class IOExample {

    /**
     * Метод использования надстройки для буферизации передачи данных.
     */
    public void bufferedOutput() {
        String text = "Here is the text for buffered IO example";
        byte[] bytes = text.getBytes();

        try (FileOutputStream fos = new FileOutputStream("D:\\ioBufferedExample.txt");
            BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            bos.write(bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод использования надстройки для буферизации приема данных.
     */
    public void bufferedInput() {

        try (FileInputStream fis = new FileInputStream("D:\\ioBufferedExample.txt");
            BufferedInputStream bis = new BufferedInputStream(fis)) {

            int i;
            while ((i = bis.read()) != -1) {
                System.out.print((char) i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод записи потока примитивных типов данных.
     */
    public void dataOutput() {

        String length = "Item";
        Double size = 1.12835235;
        Boolean confirmed = true;


        try (FileOutputStream fos = new FileOutputStream("D:\\ioDataExample.txt");
            DataOutputStream dos = new DataOutputStream(fos)) {

            dos.writeUTF(length);
            dos.writeDouble(size);
            dos.writeBoolean(confirmed);

        } catch (IOException e) {
                e.printStackTrace();
        }

    }


    /**
     * Метод чтения потока примитивных типов данных.
     */
    public void dataInput() {

        try (DataInputStream dis = new DataInputStream(new FileInputStream("D:\\ioDataExample.txt"))) {

            System.out.printf("%s with the size %.2f confirmed: %b", dis.readUTF(), dis.readDouble(), dis.readBoolean());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Метод считывает ввод из консоли построчно и записывает в файл используя буфер.
     */
    public void consoleWriter() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\consoleWriter.txt"))) {
            String line = br.readLine();
            while (!line.equals("q")) {
                bw.write(line);
                bw.append("\r\n");
                bw.flush();
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        IOExample io = new IOExample();

//        io.bufferedOutput();
//        io.bufferedInput();

//        io.dataOutput();
//        io.dataInput();

        io.consoleWriter();
    }
}
