//David Halevi 305268153 Moshe samahov 205787229
package messaging;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Message {

    private String subject;
    private String text;
    private List<Observer> acknowledgedBy;

    public Message(String subject, String text) {
        this.subject = subject;
        this.text = text;
        this.acknowledgedBy = new CopyOnWriteArrayList<>();
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }

    public void acknowledge(Observer observer) {
        acknowledgedBy.add(observer);
    }

    public List<Observer> getAcknowledgedBy() {
        return acknowledgedBy;
    }
}
