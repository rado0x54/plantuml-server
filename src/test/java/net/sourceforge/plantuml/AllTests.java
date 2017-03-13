package net.sourceforge.plantuml;

import junit.framework.Test;
import junit.framework.TestSuite;
import net.sourceforge.plantuml.common.TestRegex;
import net.sourceforge.plantuml.servlet.*;

public class AllTests extends TestSuite {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        // $JUnit-BEGIN$
        suite.addTestSuite(TestForm.class);
        suite.addTestSuite(TestImage.class);
        suite.addTestSuite(TestAsciiArt.class);
        suite.addTestSuite(TestSVG.class);
        suite.addTestSuite(TestProxy.class);
        suite.addTestSuite(TestMap.class);
        suite.addTestSuite(TestCharset.class);
        suite.addTestSuite(TestRegex.class);
        // $JUnit-END$
        return suite;
    }

}
