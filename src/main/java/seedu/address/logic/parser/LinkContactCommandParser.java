package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.LinkContactCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class LinkContactCommandParser implements Parser<LinkContactCommand> {

    public LinkContactCommand parse(String args) throws ParseException {
        try {
            String[] argarr = args.trim().split(" ");
            Index index = ParserUtil.parseIndex(argarr[0]);
            String num = argarr[1];
            return new LinkContactCommand(index, num);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            LinkContactCommand.MESSAGE_USAGE), pe);
        }
    }
}
