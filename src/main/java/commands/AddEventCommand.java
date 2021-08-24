package commands;
import tasks.EventTask;
import java.time.format.DateTimeParseException;
import exceptions.DukeException;

/**
 * This is an AddEventCommand Class, which inherits from AddCommand.
 * The execution of this command will add an event task to the task list.
 */
public class AddEventCommand extends AddCommand {
    private static final String DELIMITER = "/at";
    public static final String KEYWORD = "event";
    private static final int TASK_INDEX = 0;
    private static final int DATETIME_INDEX = 1;
    private static final int INPUT_PARAMETERS = 2;
    private static final String INPUT_FORMAT = String.format("\t\"%s [task] %s " +
            "[dd-mm-yyyy hh:mm]\"", KEYWORD, DELIMITER);

    /**
     * Constructor for AddEventCommand.
     * @param userInput The input string entered by the user.
     * @throws DukeException
     */
    public AddEventCommand (String userInput) throws DukeException {
        // Check whether input contains delimiter
        boolean hasDelimiter = userInput.contains(DELIMITER);
        if (!hasDelimiter) {
            throw new DukeException("OOPS!!! " +
                    "Please ensure your input is in the following format:\n" + INPUT_FORMAT);

        }

        // Check whether input contains task and date/time
        String[] inputData = userInput.substring(KEYWORD.length()).trim().split(DELIMITER);
        boolean isValidInput = (inputData.length == INPUT_PARAMETERS);
        if (!isValidInput) {
            throw new DukeException("OOPS!!! " +
                    "Please ensure your input is in the following format:\n" + INPUT_FORMAT);
        }

        // Retrieve input parameters
        String taskName = inputData[TASK_INDEX].trim();
        String dateTime = inputData[DATETIME_INDEX].trim();
        try {
            this.task = new EventTask(taskName, dateTime);
        } catch(DateTimeParseException e) {
            throw new DukeException("OOPS!!! " +
                    "Please ensure your input is in the following format:\n" + INPUT_FORMAT);
        }
    }
}
