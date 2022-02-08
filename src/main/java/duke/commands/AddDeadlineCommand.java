package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * Command class that adds a deadline task to list.
 */
public class AddDeadlineCommand extends Command<String> {
    private TaskList list;
    private String[] echo;
    private Storage storage;

    /**
     * Constructor that initialises the adding of deadline to list.
     *
     * @param list list of tasks to add this deadline to
     * @param echo the input details of what to be added
     * @param storage store of the list to be added to
     * @throws DukeException when task cannot be added
     */
    public AddDeadlineCommand(TaskList list, String[] echo, Storage storage) throws DukeException {
        this.list = list;
        this.echo = echo;
        this.storage = storage;
    }

    /**
     * Executes the creation of deadline task.
     *
     * @return a output response for creating a deadline task
     * @throws DukeException thrown when deadline cannot be created
     */
    public String execute() throws DukeException {
        String err = "Oh no! The description of deadline cannot be empty... Try again :)\n";
        String wrongFormat = "Oh no! The format for deadline task is wrong... Try again :)\n";
        String response = "";
        if (echo.length == 1) {
            assert echo.length == 1;
            throw new DukeException(err);
        }
        String description = echo[1];
        if (description.isEmpty()) {
            assert false : "There is no description for this deadline";
            throw new DukeException(err);
        }
        String[] details = description.split(" /by ", 2);
        if (details.length == 1) {
            assert false : "The format for this deadline should be: deadline example /by yyyy-MM-DD";
            throw new DukeException(wrongFormat);
        }
        String info = details[0];
        String date = details[1];
        Deadline curr = new Deadline(info, date);
        list.addTask(curr);
        response = Ui.showAddResponse(curr.toString(), list.getSize());
        assert response != null;
        try {
            storage.writeToFile(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    /**
     * Hint to stop the bot from running.
     *
     * @return false to not stop the bot from running
     */
    public boolean isExit() {
        return false;
    }
}
