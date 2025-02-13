package seedu.event.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.time.Clock;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.event.commons.core.GuiSettings;
import seedu.event.commons.core.LogsCenter;
import seedu.event.logic.commands.Command;
import seedu.event.logic.commands.CommandResult;
import seedu.event.logic.commands.exceptions.CommandException;
import seedu.event.logic.parser.EventBookParser;
import seedu.event.logic.parser.exceptions.ParseException;
import seedu.event.model.Model;
import seedu.event.model.ReadOnlyContactList;
import seedu.event.model.ReadOnlyEventBook;
import seedu.event.model.contact.Contact;
import seedu.event.model.event.Event;
import seedu.event.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final EventBookParser eventBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        eventBookParser = new EventBookParser(Clock.systemDefaultZone());
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = eventBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveEventBook(model.getEventBook());
            storage.saveContactList(model.getContactList());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyEventBook getEventBook() {
        return model.getEventBook();
    }

    @Override
    public ReadOnlyContactList getContactList() {
        return model.getContactList();
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return model.getFilteredEventList();
    }

    @Override
    public ObservableList<Contact> getFilteredContactList() {
        return model.getFilteredContactList();
    }

    @Override
    public Path getEventBookFilePath() {
        return model.getEventBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
