package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Rate;
import seedu.address.model.person.Timing;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_RATE = "-651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_START_TIME = "99-99-9999 11:00";
    private static final String INVALID_END_TIME = "99-99-9999 11:00";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_RATE = BENSON.getRate().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_START_TIME = BENSON.getTiming().startTime;
    private static final String VALID_END_TIME = BENSON.getTiming().endTime;
    private static final String VALID_MARK = BENSON.getMark().toString();
    private static final Sting VALID_CONTACT =
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        INVALID_NAME, VALID_RATE, VALID_ADDRESS,
                        VALID_START_TIME, VALID_END_TIME, VALID_MARK,
                        VALID_CONTACT, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                null, VALID_RATE, VALID_ADDRESS, VALID_START_TIME, VALID_END_TIME, VALID_MARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME, INVALID_RATE, VALID_ADDRESS,
                        VALID_START_TIME, VALID_END_TIME, VALID_MARK, VALID_TAGS);
        String expectedMessage = Rate.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, null, VALID_ADDRESS, VALID_START_TIME, VALID_END_TIME, VALID_MARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Rate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                        VALID_NAME, VALID_RATE, INVALID_ADDRESS,
                        VALID_START_TIME, VALID_END_TIME, VALID_MARK, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_RATE, null, VALID_START_TIME, VALID_END_TIME, VALID_MARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTiming_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_RATE, VALID_ADDRESS, INVALID_START_TIME, VALID_END_TIME, VALID_MARK, VALID_TAGS);
        String expectedMessage = Timing.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);

        person = new JsonAdaptedPerson(
                VALID_NAME, VALID_RATE, VALID_ADDRESS, VALID_START_TIME, INVALID_END_TIME, VALID_MARK, VALID_TAGS);
        expectedMessage = Timing.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);

        person = new JsonAdaptedPerson(
                VALID_NAME, VALID_RATE, VALID_ADDRESS, INVALID_START_TIME, INVALID_END_TIME, VALID_MARK, VALID_TAGS);
        expectedMessage = Timing.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }


    @Test
    public void toModelType_nullTiming_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                VALID_NAME, VALID_RATE, VALID_ADDRESS, null, VALID_END_TIME, VALID_MARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Timing.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);

        person = new JsonAdaptedPerson(
                VALID_NAME, VALID_RATE, VALID_ADDRESS, VALID_START_TIME, null, VALID_MARK, VALID_TAGS);
        expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Timing.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);

        person = new JsonAdaptedPerson(
                VALID_NAME, VALID_RATE, VALID_ADDRESS, null, null, VALID_MARK, VALID_TAGS);
        expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Timing.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(
                        VALID_NAME, VALID_RATE, VALID_ADDRESS,
                        VALID_START_TIME, VALID_END_TIME, VALID_MARK, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
