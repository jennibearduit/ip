package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exceptions.MorganException;
import parser.TaskParser;
import tasks.TaskList;
import tasks.Task;

/**
 * This is a storage.Storage class, which encapsulates storage of tasks.
 */
public class Storage {
    private static final String DIRECTORY_PATH = "./morgan-files";
    private static final String FILE_PATH = DIRECTORY_PATH + "/tasks.txt";
    private final TaskParser parser = new TaskParser();
    private final File file;
    protected static final String DELIMITER = TaskParser.DELIMITER;
    private static final String FILE_ERROR = "Warning: Unable to locate storage data. " +
            "A new storage file has been created.";
    private static final String DECODE_ERROR = "Warning: Some tasks may be missing due " +
            "to storage file tampering. ";

    /**
     * Constructor for storage.Storage.
     * @throws MorganException
     */
    public Storage() {
        File directory = new File(DIRECTORY_PATH);
        boolean isDirectoryExist = directory.exists();
        if (!isDirectoryExist) {
            directory.mkdir();
        }
        this.file = new File(FILE_PATH);
    }

    /**
     * Loads data from specified file.
     * @param taskList The existing list of tasks from the file.
     * @throws MorganException
     */
    public void load(TaskList taskList) throws MorganException {
        boolean hasInvalidFormatting = false;
        try {
            Scanner sc = new Scanner(this.file);
            taskList.clear();
            while (sc.hasNextLine()) {
                String taskString = sc.nextLine();
                try {
                    Task task = parser.decode(taskString);
                    taskList.addTask(task);
                } catch (MorganException e) {
                    hasInvalidFormatting = true;
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            this.save(taskList);
            throw new MorganException(FILE_ERROR);
        }
        if (hasInvalidFormatting) {
            throw new MorganException(DECODE_ERROR);
        }
    }


    /**
     * Saves the list of tasks into a file.
     * @param taskList The list of tasks to be saved.
     * @throws MorganException
     */
    public void save(TaskList taskList) throws MorganException {
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
            throw new MorganException(e.getMessage());
        }
    }
}
