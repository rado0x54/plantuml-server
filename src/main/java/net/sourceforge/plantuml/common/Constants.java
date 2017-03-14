package net.sourceforge.plantuml.common;

import java.util.regex.Pattern;

/**
 * Created by Martin Riedel on 13/03/2017.
 *
 * Seperate Public Class for Common Constants
 */
public final class Constants {


    public static final String BOB_ALICE_HELLO_DEC_NO_TAGS = "Bob -> Alice : hello";
    public static final String BOB_ALICE_HELLO_DEC = "@startuml\nBob -> Alice : hello\n@enduml";
    public static final String BOB_ALICE_HELLO_ENC = "SyfFKj2rKt3CoKnELR1Io4ZDoSa70000";

    public static final String ENCODED_ATTRIBUTE_NAME = "encoded";
    public static final String DECODED_ATTRIBUTE_NAME = "decoded";

    public static final String UML_URL_ENC_PARAMETER = "uml";


    public static final Pattern REGEX_URL_LAST_PATH_PATTERN = Pattern.compile("^.*[^a-zA-Z0-9\\-\\_]([a-zA-Z0-9\\-\\_]+)");

    public static final Pattern REGEX_UML_PATTERN = Pattern.compile(".*/uml/(.*)");

}
