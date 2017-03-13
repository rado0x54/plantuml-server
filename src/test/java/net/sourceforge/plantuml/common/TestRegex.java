package net.sourceforge.plantuml.common;

import junit.framework.TestCase;

import java.util.regex.Matcher;

/**
 * Created by mriedel on 13/03/2017.
 */
public class TestRegex extends TestCase {

    private static final String HOST = "http://localhost:8080";

    public void testRegexUMLPatternRoot() {

        final Matcher recoverUml = Constants.REGEX_UML_PATTERN.matcher( "/uml/" + Constants.BOB_ALICE_HELLO_ENC);

        assertTrue(recoverUml.matches());
        assertEquals(Constants.BOB_ALICE_HELLO_ENC, recoverUml.group(1));
    }

    public void testRegexUMLPatternContextPath() {

        final Matcher recoverUml = Constants.REGEX_UML_PATTERN.matcher("/plantuml/uml/" + Constants.BOB_ALICE_HELLO_ENC);

        assertTrue(recoverUml.matches());
        assertEquals(Constants.BOB_ALICE_HELLO_ENC, recoverUml.group(1));
    }


    public void testRegexURLLastPatternRoot() {

        final Matcher m1 = Constants.REGEX_URL_LAST_PATH_PATTERN.matcher(HOST + "/plantuml/png/" + Constants.BOB_ALICE_HELLO_ENC);

        assertTrue(m1.find());
        assertEquals(Constants.BOB_ALICE_HELLO_ENC, m1.group(1));
    }

    public void testRegexURLLastPatternContextPath() {

        final Matcher m1 = Constants.REGEX_URL_LAST_PATH_PATTERN.matcher(HOST + "/png/" + Constants.BOB_ALICE_HELLO_ENC);

        assertTrue(m1.find());
        assertEquals(Constants.BOB_ALICE_HELLO_ENC, m1.group(1));
    }


//    public void testRegexUMLPattern() {
//        Constants.REGEX_URL_LAST_PATH_PATTERN;
//
//    }

}
