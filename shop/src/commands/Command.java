package commands;

import storage.Storage;

public interface Command {

    void execute(Storage storage) throws Exception;

    void onError(Throwable t);

    void onSuccess();
}
