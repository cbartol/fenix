package net.sourceforge.fenixedu.presentationTier.Action.student;

import org.fenixedu.bennu.portal.StrutsApplication;

public class StudentApplication {

    @StrutsApplication(descriptionKey = "consult", path = "consult", titleKey = "consult", bundle = "resources.StudentResources",
            accessGroup = "role(STUDENT)")
    public static class StudentViewApp {

    }

    @StrutsApplication(descriptionKey = "participate", path = "participate", titleKey = "participate",
            bundle = "resources.StudentResources", accessGroup = "role(STUDENT)")
    public static class ParticipateApp {

    }

    @StrutsApplication(descriptionKey = "submit", path = "submit", titleKey = "submit", bundle = "resources.StudentResources",
            accessGroup = "role(STUDENT)")
    public static class SubmitApp {

    }

    @StrutsApplication(descriptionKey = "enroll", path = "enroll", titleKey = "enroll", bundle = "resources.StudentResources",
            accessGroup = "role(STUDENT)")
    public static class EnrollApp {

    }

    @StrutsApplication(descriptionKey = "link.student.seniorTitle", path = "finalists", titleKey = "link.student.seniorTitle",
            bundle = "resources.StudentResources", accessGroup = "role(STUDENT)")
    public static class FinalistsApp {

    }
}
