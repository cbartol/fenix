package net.sourceforge.fenixedu.domain.contents;

import org.fenixedu.bennu.core.domain.Bennu;

import pt.ist.fenixframework.DomainObject;

public class Portal extends Portal_Base {

    public Portal() {
        super();
    }

    public DomainObject getContext() {
        return null;
    }

    public static Portal getRootPortal() {
        return Bennu.getInstance().getRootPortal();
    }

    public void setFirstParent(Container container) {
        container.addChild(this);
    }

    @Override
    public void appendReversePathPart(StringBuilder stringBuilder) {
        if (this != getRootPortal()) {
            super.appendReversePathPart(stringBuilder);
        }
    }

    @Override
    protected Node createChildNode(Content childContent) {
        return new ExplicitOrderNode(this, childContent);
    }

    @Override
    protected void disconnect() {
        setPortalRootDomainObject(null);
        super.disconnect();
    }

}
