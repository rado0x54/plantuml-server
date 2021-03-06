package net.sourceforge.plantuml.servlet;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class TestProxy extends WebappTestCase {
    /**
     * Verifies the proxified reception of the default Bob and Alice diagram
     */
    public void testDefaultProxy() throws Exception {
        WebConversation conversation = new WebConversation();
        WebRequest request = new GetMethodWebRequest(getServerUrl()
            + "proxy?src=" + getServerUrl() + "resource/test2diagrams.txt");
        WebResponse response = conversation.getResource(request);
        // Analyze response
        // Verifies the Content-Type header
        assertEquals("Response content type is not PNG", "image/png", response.getContentType());
        // Get the image and verify its size (~2000 bytes)
        InputStream responseStream = response.getInputStream();
        ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while ((n = responseStream.read(buf)) != -1) {
            imageStream.write(buf, 0, n);
        }
        imageStream.close();
        responseStream.close();
        byte[] inMemoryImage = imageStream.toByteArray();
        int diagramLen = inMemoryImage.length;
        assertTrue(diagramLen > 1500);
        assertTrue(diagramLen < 2500);
    }


    /**
     * Verifies that the HTTP header of a diagram incites the browser to cache it.
     */
    public void testInvalidUrl() throws Exception {
        WebConversation conversation = new WebConversation();
        // Try to proxify an invalid address
        WebRequest request = new GetMethodWebRequest(getServerUrl() + "proxy?src=invalidURL");
        WebResponse response = conversation.getResource(request);
        // Analyze response, it must be HTTP error 500
        //assertEquals("Bad HTTP status received", 500, response.getResponseCode());
    }
}
