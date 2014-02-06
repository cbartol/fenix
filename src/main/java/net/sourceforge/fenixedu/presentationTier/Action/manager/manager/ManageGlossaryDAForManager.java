package net.sourceforge.fenixedu.presentationTier.Action.manager.manager;

import net.sourceforge.fenixedu.presentationTier.Action.manager.ManagerApplications.ManageFinanceApp;

import org.fenixedu.bennu.portal.StrutsFunctionality;

import pt.ist.fenixWebFramework.struts.annotations.Forward;
import pt.ist.fenixWebFramework.struts.annotations.Forwards;
import pt.ist.fenixWebFramework.struts.annotations.Mapping;

@StrutsFunctionality(application = ManageFinanceApp.class, descriptionKey = "title.manage.glossary", path = "glossary",
        titleKey = "title.manage.glossary")
@Mapping(module = "manager", path = "/manageGlossary", input = "/manageGlossary.do?method=prepare", attribute = "glossaryForm",
        formBean = "glossaryForm", scope = "request", parameter = "method")
@Forwards(value = { @Forward(name = "Manage", path = "/manager/manageGlossary_bd.jsp") })
public class ManageGlossaryDAForManager extends net.sourceforge.fenixedu.presentationTier.Action.manager.ManageGlossaryDA {
}