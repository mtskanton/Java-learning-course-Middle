package configuration.xml.property;

/**
 * Класс отображения сообщения.
 */
public class MessagePrinter {

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.printf("Printed message: %s", this.message);
    }
}
