package net.sourceforge.fenixedu.presentationTier.servlets.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.fenixedu.domain.Item;
import net.sourceforge.fenixedu.domain.Section;
import net.sourceforge.fenixedu.domain.contents.Attachment;
import net.sourceforge.fenixedu.domain.contents.Content;
import net.sourceforge.fenixedu.domain.contents.FunctionalityCall;
import net.sourceforge.fenixedu.domain.contents.Redirect;
import net.sourceforge.fenixedu.domain.functionalities.Functionality;
import net.sourceforge.fenixedu.domain.functionalities.FunctionalityContext;
import net.sourceforge.fenixedu.domain.functionalities.FunctionalityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContentFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(ContentFilter.class);

    private static final String SECTION_PATH = "/publico/viewGenericContent.do?method=viewSection";

    private static final String ITEM_PATH = "/publico/viewGenericContent.do?method=viewItem";

    public static String FUNCTIONALITY_PARAMETER = "_f";

    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
        // nothing
    }

    @Override
    public void doFilter(ServletRequest initialRequest, ServletResponse initialResponse, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) initialRequest;
        HttpServletResponse response = (HttpServletResponse) initialResponse;

        final FunctionalityContext functionalityContext =
                (FunctionalityContext) getContextAttibute((HttpServletRequest) initialRequest);

        if (functionalityContext == null || functionalityContext.getSelectedContents().isEmpty()
                || functionalityContext.hasBeenForwarded()) {
            chain.doFilter(request, response);
        } else {
            contentsForward((HttpServletRequest) initialRequest, (HttpServletResponse) initialResponse, functionalityContext);
        }

    }

    private void dispatchTo(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse,
            final FunctionalityContext functionalityContext, String path) throws ServletException, IOException {
        httpServletRequest.setAttribute(ContentInjectionRewriter.CONTEXT_ATTRIBUTE_NAME,
                functionalityContext.getCurrentContextPath());
        final RequestDispatcher requestDispatcher = httpServletRequest.getRequestDispatcher(path);
        functionalityContext.setHasBeenForwarded();
        requestDispatcher.forward(httpServletRequest, httpServletResponse);
    }

    private void contentsForward(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse,
            final FunctionalityContext functionalityContext) throws ServletException, IOException {

        Content content = functionalityContext.getSelectedContent();

        if (logger.isDebugEnabled()) {
            if (content == null) {
                logger.debug(httpServletRequest.getRequestURI() + " ---> null");
            } else {
                logger.debug(httpServletRequest.getRequestURI() + " ---> " + content.getClass().getName());
            }
        }

        if (content instanceof Redirect) {
            final Redirect redirect = (Redirect) content;
            sendRedirect(httpServletResponse, redirect.getUrl());
        } else if (content instanceof Section) {
            dispatchTo(httpServletRequest, httpServletResponse, functionalityContext, SECTION_PATH);

        } else if (content instanceof Item) {
            dispatchTo(httpServletRequest, httpServletResponse, functionalityContext, ITEM_PATH);

        } else if (content instanceof Attachment) {
            Attachment attachment = (Attachment) content;
            httpServletResponse.sendRedirect(attachment.getFile().getDownloadUrl());
        } else if (content instanceof FunctionalityCall) {
            Functionality functionality = ((FunctionalityCall) content).getFunctionality();
            dispatchTo(httpServletRequest, httpServletResponse, functionalityContext, functionality.getPath());

        } else if (content instanceof Functionality) {
            Functionality functionality = ((Functionality) content);
            dispatchTo(httpServletRequest, httpServletResponse, functionalityContext, functionality.getPath());
        }
    }

    protected void dispatch(final HttpServletRequest request, final HttpServletResponse response, final String path)
            throws IOException, ServletException {
        final RequestDispatcher dispatcher = request.getRequestDispatcher(path);

        if (dispatcher == null) {
            sendRedirect(response, request.getContextPath() + path);
        } else {
            dispatcher.forward(request, response);
        }
    }

    protected void sendRedirect(final HttpServletResponse httpServletResponse, final String url) throws IOException {
        httpServletResponse.sendRedirect(url);
    }

    private FunctionalityContext getContextAttibute(final HttpServletRequest httpServletRequest) {
        return (FunctionalityContext) httpServletRequest.getAttribute(FunctionalityContext.CONTEXT_KEY);
    }

}
