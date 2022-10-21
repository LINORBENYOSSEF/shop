//David Halevi 305268153 Moshe samahov 205787229
package messaging;

import commands.CommandQueue;

public interface Observer {

    void handle(Message message, CommandQueue commandQueue);
}
