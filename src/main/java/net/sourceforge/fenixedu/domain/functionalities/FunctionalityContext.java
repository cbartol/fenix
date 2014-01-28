package net.sourceforge.fenixedu.domain.functionalities;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sourceforge.fenixedu.domain.contents.Container;
import net.sourceforge.fenixedu.domain.contents.Content;

public final class FunctionalityContext {

    public List<Content> getSelectedContents() {
        throw new UnsupportedOperationException();
    }

    public Container getSelectedContainer() {
        throw new UnsupportedOperationException();
    }

    public String getSelectedContainerPath() {
        throw new UnsupportedOperationException();
    }

    public Container getSelectedTopLevelContainer() {
        throw new UnsupportedOperationException();
    }

    public Content getSelectedContent() {
        throw new UnsupportedOperationException();
    }

    public List<Content> getPathBetween(Container container, Content content) {
        throw new UnsupportedOperationException();
    }

    public void addAllContent(List<Content> contents) {
        throw new UnsupportedOperationException();
    }

    public Content getLastContentInPath(Class type) {
        throw new UnsupportedOperationException();
    }

    public static FunctionalityContext getCurrentContext(HttpServletRequest request) {
        throw new UnsupportedOperationException();
    }

}
