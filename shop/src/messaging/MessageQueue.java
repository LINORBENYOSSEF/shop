//David Halevi 305268153 Moshe samahov 205787229
package messaging;

import commands.CommandQueue;
import commands.HandleObserverCommand;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageQueue {

    private static MessageQueue instance;

    public static void setInstance(MessageQueue instance) {
        MessageQueue.instance = instance;
    }

    public static MessageQueue getInstance() {
        return instance;
    }

    private BlockingQueue<Message> messages;
    private List<Message> messageHistory;
    private List<Observer> observers;
    private Thread thread;

    public MessageQueue(CommandQueue commandQueue) {
        messages = new LinkedBlockingQueue<>();
        messageHistory = new CopyOnWriteArrayList<>();
        observers = new CopyOnWriteArrayList<>();

        thread = new Thread(new Task(commandQueue, messages, observers));
        thread.setDaemon(true);
        thread.start();
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    public void clearObservers() {
        observers.clear();
    }

    public void sendMessage(Message message) {
        messages.add(message);
        messageHistory.add(message);
    }

    public List<Message> getMessageHistory() {
        return messageHistory;
    }

    private static class Task implements Runnable {

        private CommandQueue commandQueue;
        private BlockingQueue<Message> messages;
        private List<Observer> observers;

        private Task(CommandQueue commandQueue, BlockingQueue<Message> messages, List<Observer> observers) {
            this.commandQueue = commandQueue;
            this.messages = messages;
            this.observers = observers;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    Message message = messages.take();
                    handleMessage(message);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }

        private void handleMessage(Message message) {
            long baseDelay = 2000;
            long delayMs = baseDelay;
            for (Observer observer : observers) {
                commandQueue.addCommand(new HandleObserverCommand(message, observer, commandQueue), delayMs);
                delayMs += baseDelay;
            }
        }
    }
}
