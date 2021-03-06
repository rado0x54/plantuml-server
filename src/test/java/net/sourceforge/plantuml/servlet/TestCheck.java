package net.sourceforge.plantuml.servlet;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import net.sourceforge.plantuml.common.Constants;

import java.nio.charset.Charset;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TestCheck extends WebappTestCase {
    /**
     * Verifies the generation of a syntax check for the following sample:
     * Bob -> Alice : hello
     */
    public void testCorrectSequenceDiagram() throws Exception {
        WebConversation conversation = new WebConversation();
        WebRequest request = new GetMethodWebRequest(getServerUrl()
            + "check/" + Constants.BOB_ALICE_HELLO_ENC);
        WebResponse response = conversation.getResource(request);
        // Analyze response
        // Verifies the Content-Type header
        assertEquals("Response content type is not TEXT PLAIN", "text/plain", response.getContentType());
        assertEquals("Response character set is not UTF-8", UTF_8, Charset.forName(response.getCharacterSet()));
        // Get the content, check its first characters and verify its size
        String checkResult = response.getText();
        assertTrue("Response content is not starting with (2 participants)",
                checkResult.startsWith("(2 participants)"));
        int checkLen = checkResult.length();
        assertTrue(checkLen > 1);
        assertTrue(checkLen < 100);
    }

    /**
     * Check the syntax of an invalid sequence diagram :
     * Bob -
     */
    public void testWrongDiagramSyntax() throws Exception {
        WebConversation conversation = new WebConversation();
        WebRequest request = new GetMethodWebRequest(getServerUrl() + "check/SyfFKj050000");
        WebResponse response = conversation.getResource(request);
        // Analyze response
        String checkResult = response.getText();
        assertTrue("Response is not an error", checkResult.startsWith("(Error)"));
    }

}
