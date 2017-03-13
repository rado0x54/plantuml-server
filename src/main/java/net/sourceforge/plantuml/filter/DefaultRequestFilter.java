package net.sourceforge.plantuml.filter;

import net.sourceforge.plantuml.common.Constants;

import javax.servlet.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by mriedel on 13/03/2017.
 */
public class DefaultRequestFilter implements Filter {

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        request.setCharacterEncoding(StandardCharsets.UTF_8.toString());

        request.setAttribute(Constants.DECODED_ATTRIBUTE_NAME, Constants.BOB_ALICE_HELLO_DEC);
        request.setAttribute(Constants.ENCODED_ATTRIBUTE_NAME, Constants.BOB_ALICE_HELLO_ENC);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
