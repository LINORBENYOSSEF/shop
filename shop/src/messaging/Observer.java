package messaging;

import commands.CommandQueue;

public interface Observer {

    void handle(Message message, CommandQueue commandQueue);
}
