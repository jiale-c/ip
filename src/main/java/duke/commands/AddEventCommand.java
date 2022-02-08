package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Command class that adds a event task to list.
 */
public class AddEventCommand extends Command<String> {
    private TaskList list;
    private String[] echo;
    private Storage storage;

    /**
     * Constructor that initialises the adding of event to list.
     *
     * @param list list of tasks to add this event to
     * @param echo the input details of what to be added
     * @param storage store of the list to be added to
     * @throws DukeException when task cannot be added
     */
    public AddEventCommand(TaskList list, String[] echo, Storage storage) throws DukeException {
        this.list = list;
        this.echo = echo;
        this.storage = storage;
    }

    /**
     * Executes the creation of Event task.
     *
     * @return a output response for creating a deadline task
     * @throws DukeException thrown when event cannot be created
     */
    public String execute() throws DukeException {
        String err = "Oh no! The description of event cannot be empty... Try again :)\n";
        String wrongFormat = "Oh no! The format for event task is wrong... Try again :)\n";
        String response = "";
        if (echo.length == 1) {
            assert false : "The description for event is empty";
            throw new DukeException(err);
        }
        String description = echo[1];
        if (description.isEmpty()) {
            assert false : "The description for event is empty";
            throw new DukeException(err);
        }
        String[] details = description.split(" /at ", 2);
        if (details.length == 1) {
            assert false : "The format for event should be: event example /at YYYY-MM-DD";
            throw new DukeException(wrongFormat);
        }
        String info = details[0];
        String date = details[1];
        Event curr = new Event(info, date);
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
