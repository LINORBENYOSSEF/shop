package commands;

import storage.Storage;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CommandQueue {

    private BlockingQueue<CommandContext> commands;
    private Thread thread;

    public CommandQueue(Storage storage) {
        commands = new LinkedBlockingQueue<>();

        thread = new Thread(new Task(storage, commands));
        thread.setDaemon(true);
        thread.start();
    }

    public void addCommand(Command command) {
        commands.add(new CommandContext(command, 0));
    }

    public void addCommand(Command command, long delayMs) {
        commands.add(new CommandContext(command, (long) (delayMs * 1e6)));
    }

    private static class CommandContext {

        private Command command;
        private long executionDelay;
        private long enqueuedTime;

        private CommandContext(Command command, long executionDelay) {
            this.command = command;
            this.executionDelay = executionDelay;
            this.enqueuedTime = System.nanoTime();
        }

        public void execute(Storage storage) {
            try {
                command.execute(storage);
                command.onSuccess();
            } catch (Throwable t) {
                command.onError(t);
            }
        }

        public boolean shouldExecuteNow() {
            return System.nanoTime() - enqueuedTime >= executionDelay;
        }
    }

    private static class Task implements Runnable {

        private Storage storage;
        private BlockingQueue<CommandContext> commands;

        public Task(Storage storage, BlockingQueue<CommandContext> commands) {
            this.storage = storage;
            this.commands = commands;
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
                try {
                    CommandContext command = commands.take();
                    if (command.shouldExecuteNow()) {
                        command.execute(storage);
                    } else {
                        commands.add(command);
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
