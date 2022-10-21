//David Halevi 305268153 Moshe samahov 205787229
package commands;

import storage.Storage;

public interface Command {

    void execute(Storage storage) throws Exception;

    void onError(Throwable t);

    void onSuccess();
}
