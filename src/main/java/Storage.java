import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import exceptions.DukeException;
import tasks.TaskList;
import tasks.Task;

/**
 * This is a Storage class, which encapsulates storage of tasks.
 */
public class Storage {
    private static final String DIRECTORY_PATH = "./duke-files";
    private static final String FILE_PATH = DIRECTORY_PATH + "/tasks.txt";
    private final StorageParser parser = new StorageParser();
    private final File file;

    /**
     * Constructor for Storage.
     * @throws DukeException
     */
    public Storage() throws DukeException {
        File directory = new File(DIRECTORY_PATH);
        boolean isDirectoryExist = directory.exists();
        if (!isDirectoryExist) {
            directory.mkdir();
        }
        this.file = new File(FILE_PATH);
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! " + e.getMessage());
        }
    }

    /**
     * Loads data from specified file.
     * @param taskList The existing list of tasks from the file.
     * @throws DukeException
     */
    public void load(TaskList taskList) throws DukeException {
        try {
            Scanner sc = new Scanner(this.file);
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                try {
                    Task task = parser.decode(taskString);
                    taskList.addTask(task);
                } catch (DukeException e) {
                    continue;
                }
            }
        } catch (IOException e) {
            throw new DukeException("OOPS!!! " + e.getMessage());
        }
    }


    /**
     * Saves the list of tasks into a file.
     * @param taskList The list of tasks to be saved.
     * @throws DukeException
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            StringBuilder storageString = new StringBuilder();
            int numOfTasks = taskList.getNumOfTasks();
            for (int i = 1; i <= numOfTasks; i++) {
                Task t = taskList.getTask(i);
                storageString.append(parser.encode(t));
                if (i != numOfTasks) {
                    storageString.append("\n");
                }
            }
            fileWriter.write(String.valueOf(storageString));
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! " + e.getMessage());
        }
    }
}
