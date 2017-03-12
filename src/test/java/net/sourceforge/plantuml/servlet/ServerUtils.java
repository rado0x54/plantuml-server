package net.sourceforge.plantuml.servlet;

import java.net.InetSocketAddress;

import org.eclipse.jetty.server.NetworkConnector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

public class ServerUtils {

    private Server server;

    ServerUtils(boolean start) throws Exception {
        server = new Server(new InetSocketAddress("127.0.0.1", 0));
        WebAppContext webApp = getWebAppContext();

        // This webapp will use jsps and jstl. We need to enable the
        // AnnotationConfiguration in order to correctly
        // set up the jsp container
        Configuration.ClassList classlist = Configuration.ClassList
                .setServerDefault( server );
        classlist.addBefore(
                "org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
                "org.eclipse.jetty.annotations.AnnotationConfiguration" );

        server.setHandler(webApp);
        server.addBean(webApp);


        if (start) {
            startServer();
        }
    }

    public ServerUtils() throws Exception {
        this(false);
    }

    void startServer() throws Exception {
        server.start();
    }

    void stopServer() throws Exception {
        server.stop();
    }

    public String getServerUrl() {
        NetworkConnector connector = (NetworkConnector)server.getConnectors()[0];
        return String.format("http://%s:%d/plantuml/", connector.getHost(), connector.getLocalPort());
    }


    private static WebAppContext getWebAppContext() {
       WebAppContext webapp = new WebAppContext();
       webapp.setWar("src/main/webapp");
       webapp.setContextPath("/plantuml");

        // Set the ContainerIncludeJarPattern so that jetty examines these
        // container-path jars for tlds, web-fragments etc.
        // If you omit the jar that contains the jstl .tlds, the jsp engine will
        // scan for them instead.
        webapp.setAttribute(
                "org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
                ".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$" );

        return webapp;
    }

}
