package pikabot.command;

import pikabot.TaskList;
import pikabot.Storage;
import pikabot.Parser;
import pikabot.Ui;

import pikabot.task.Event;
import pikabot.exception.EventException;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to create an EventCommand.
 */
public class EventCommand extends Command {

    String[] eventCommand;

    /**
     * Constructs an EventCommand.
     *
     * @param eventCommand String array containing input string from user.
     */
    public EventCommand(String[] eventCommand) {
        this.eventCommand = eventCommand;
    }

    /**
     * Executes EventCommand by creating an Event task.
     *
     * @param taskList TaskList to add the Event task to.
     * @param storage Storage to update data file in computer.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            Event currEvent = Parser.parseEvent(eventCommand);
            taskList.add(currEvent);
            Ui.indicateAddedTask(currEvent, taskList);
            storage.appendToFile(currEvent);

        } catch (EventException | IOException e) {
            Ui.printExceptionMessage(e);

        } catch (DateTimeParseException e) {
            Ui.printExceptionCustomisedMessage("The description of an event must" +
                "contain a date in the numerical format YYYY-MM-DD");
        }
    }
}
