package net.sourceforge.fenixedu.presentationTier.Action.person;

import org.fenixedu.bennu.portal.StrutsApplication;

@StrutsApplication(bundle = "resources.ApplicationResources", descriptionKey = "label.app.personal.description",
        path = "personal", titleKey = "label.app.personal", accessGroup = "role(PERSON)")
public class PersonApplication {

    @StrutsApplication(descriptionKey = "label.navheader.person", path = "personal-area", titleKey = "label.navheader.person",
            parent = PersonApplication.class)
    public static class PersonalAreaApp {

    }

    @StrutsApplication(descriptionKey = "label.homepage", path = "homepage", titleKey = "label.homepage",
            parent = PersonApplication.class)
    public static class HomepageApp {

    }

    @StrutsApplication(descriptionKey = "oauthapps.label", path = "external-applications", titleKey = "oauthapps.label",
            parent = PersonApplication.class)
    public static class ExternalApplicationsApp {

    }

}
