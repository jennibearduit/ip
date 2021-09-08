package commands;

import exceptions.MorganException;
import storage.Storage;
import tasks.TaskList;

/**
 * This is an FindCommand Class, which inherits from Command.
 * The execution of this command will return a list of tasks
 * which contains the keyword.
 */
public class FindCommand extends Command {
    public static final String KEYWORD = "find";
    private static final String INPUT_FORMAT = String.format("\t\"%s [keyword]\"", KEYWORD);
    private static final String INPUT_FORMAT_ERROR = String.format("Please "
            + "ensure your input is in the following format:\n" + INPUT_FORMAT);
    private static final String NOT_FOUND_ERROR = "No matching task found. "
            + "Please try another keyword.";
    private final String keyTerm;

    /**
     * Constructor for command.
     * @param userInput The input string entered by the user.
     * @throws MorganException If input format is invalid.
     */
    public FindCommand(String userInput) throws MorganException {
        assert userInput != null;

        String inputData = userInput.substring(KEYWORD.length()).trim();
        this.keyTerm = inputData.toLowerCase();

        // Checks if user specified key term
        if (keyTerm.isEmpty()) {
            throw new MorganException(INPUT_FORMAT_ERROR);
        }
    }

    /**
     * Returns a list of tasks containing the specified keyword.
     * @param tasks The existing list of tasks.
     * @return The list of tasks containing the specified keyword.
     */
    public String execute(TaskList tasks, Storage storage) throws MorganException {
        assert tasks != null && storage != null;
        TaskList foundTasks = tasks.findTasks(keyTerm);

        // Throws exception if no matching task found
        if (foundTasks.isEmpty()) {
            throw new MorganException(NOT_FOUND_ERROR);
        }

        // Message displayed upon execution
        String output = "Here are the matching tasks in your list:\n";
        output += foundTasks.toString();
        return output;
    }
}
