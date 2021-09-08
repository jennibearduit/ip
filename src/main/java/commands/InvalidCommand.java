package commands;
import exceptions.MorganException;
import storage.Storage;
import tasks.TaskList;

/**
 * This class inherits from Command and encapsulates an invalid command.
 * Execution of this command will send a message to user to enter a
 * valid command.
 */
public class InvalidCommand extends Command {
    private static final String INVALID_COMMAND_ERROR = "Please enter a valid command.";

    /**
     * Throws an exception to prompt user to enter a valid command.
     * @param tasks The existing list of tasks.
     * @return The completion message after execution.
     * @throws MorganException If no matching command is found.
     */
    public String execute(TaskList tasks, Storage storage) throws MorganException {
        assert tasks != null && storage != null;
        throw new MorganException(INVALID_COMMAND_ERROR);
    }
}
