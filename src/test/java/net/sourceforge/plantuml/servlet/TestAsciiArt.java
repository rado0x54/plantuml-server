package net.sourceforge.plantuml.servlet;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;
import net.sourceforge.plantuml.common.Constants;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

public class TestAsciiArt extends WebappTestCase {

    /**
     * Verifies the generation of the ascii art for the Bob -> Alice sample
     */
    public void testSimpleSequenceDiagram() throws Exception {
        WebConversation conversation = new WebConversation();
        WebRequest request = new GetMethodWebRequest(getServerUrl() + "txt/" + Constants.BOB_ALICE_HELLO_ENC);
        WebResponse response = conversation.getResource(request);
        // Analyze response
        // Verifies the Content-Type header
        assertEquals("Response content type is not TEXT PLAIN", "text/plain", response.getContentType());
        assertEquals("Response character set is not UTF-8", UTF_8, Charset.forName(response.getCharacterSet()));
        // Get the content and verify its size
        String diagram = response.getText();
        int diagramLen = diagram.length();
        assertTrue(diagramLen > 200);
        assertTrue(diagramLen < 250);
    }

    /**
     * Verifies that URL-ENC UTXT Images are generated
     */
    public void testSimpleSequenceDiagramUrlEnc() throws Exception {
        WebConversation conversation = new WebConversation();
        WebRequest request = new GetMethodWebRequest(getServerUrl() + "txt?uml=" + URLEncoder.encode(Constants.BOB_ALICE_HELLO_DEC, StandardCharsets.UTF_8.toString()));
        WebResponse response = conversation.getResource(request);
        // Analyze response
        // Verifies the Content-Type header
        assertEquals("Response content type is not TEXT PLAIN", "text/plain", response.getContentType());
        assertEquals("Response character set is not UTF-8", UTF_8, Charset.forName(response.getCharacterSet()));
        // Get the content and verify its size
        String diagram = response.getText();
        int diagramLen = diagram.length();
        assertTrue(diagramLen > 200);
        assertTrue(diagramLen < 250);
    }

    /**
     * Verifies that URL-ENC UTXT Images are generated
     */
    public void testSimpleSequenceDiagramUrlEncNoTags() throws Exception {
        WebConversation conversation = new WebConversation();
        WebRequest request = new GetMethodWebRequest(getServerUrl() + "txt?uml=" + URLEncoder.encode(Constants.BOB_ALICE_HELLO_DEC_NO_TAGS, StandardCharsets.UTF_8.toString()));
        WebResponse response = conversation.getResource(request);

        // Analyze response
        // Verifies the Content-Type header
        assertEquals(200, response.getResponseCode());
        assertEquals("Response content type is not TEXT PLAIN", "text/plain", response.getContentType());
        assertEquals("Response character set is not UTF-8", UTF_8, Charset.forName(response.getCharacterSet()));
        // Get the content and verify its size
        String diagram = response.getText();
        int diagramLen = diagram.length();
        assertTrue(diagramLen > 200);
        assertTrue(diagramLen < 250);
    }
}
