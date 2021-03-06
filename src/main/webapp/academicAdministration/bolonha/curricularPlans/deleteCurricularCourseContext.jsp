<%--

    Copyright © 2002 Instituto Superior Técnico

    This file is part of FenixEdu Core.

    FenixEdu Core is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FenixEdu Core is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FenixEdu Core.  If not, see <http://www.gnu.org/licenses/>.

--%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://fenixedu.org/taglib/jsf-portal" prefix="fp"%>
<%@ taglib uri="http://fenix-ashes.ist.utl.pt/taglib/jsf-fenix" prefix="fc"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>

<fp:select actionClass="net.sourceforge.fenixedu.presentationTier.Action.academicAdministration.AcademicAdministrationApplication$CurricularPlansManagement" />

<f:view>
	<f:loadBundle basename="resources/HtmlaltResources" var="htmlAltBundle"/>
	<f:loadBundle basename="resources/BolonhaManagerResources" var="bolonhaBundle"/>
	<f:loadBundle basename="resources/EnumerationResources" var="enumerationBundle"/>
	
	<h:outputText value="<em>#{AcademicAdministrationCurricularCourseManagement.degreeCurricularPlan.name}" escape="false"/>
	<h:outputText value=" (#{enumerationBundle[AcademicAdministrationCurricularCourseManagement.degreeCurricularPlan.curricularStage.name]})</em>" escape="false"/>
	<h:outputFormat value="<h2>#{bolonhaBundle['delete.param']} </h2>" escape="false">
		<f:param value="#{bolonhaBundle['context']}"/>
	</h:outputFormat>
	<h:messages infoClass="success0" errorClass="error0" layout="table" globalOnly="true"/>
	<h:form>
		<h:outputText escape="false" value="<input alt='input.degreeCurricularPlanID' id='degreeCurricularPlanID' name='degreeCurricularPlanID' type='hidden' value='#{AcademicAdministrationCurricularCourseManagement.degreeCurricularPlanID}'/>"/>
		<h:outputText escape="false" value="<input alt='input.curricularCourseID' id='curricularCourseID' name='curricularCourseID' type='hidden' value='#{AcademicAdministrationCurricularCourseManagement.curricularCourseID}'/>"/>
		<h:outputText escape="false" value="<input alt='input.contextIDToDelete' id='contextIDToDelete' name='contextIDToDelete' type='hidden' value='#{AcademicAdministrationCurricularCourseManagement.contextID}'/>"/>
		<h:outputText escape="false" value="<input alt='input.organizeBy' id='organizeBy' name='organizeBy' type='hidden' value='#{AcademicAdministrationCurricularCourseManagement.organizeBy}'/>"/>
		<h:outputText escape="false" value="<input alt='input.hideCourses' id='hideCourses' name='hideCourses' type='hidden' value='#{AcademicAdministrationCurricularCourseManagement.hideCourses}'/>"/>
		<h:outputText escape="false" value="<input alt='input.action' id='action' name='action' type='hidden' value='#{AcademicAdministrationCurricularCourseManagement.action}'/>"/>

		<h:outputText value="<p><strong>#{bolonhaBundle['name']}:</strong> " escape="false"/>
		<h:outputText value="#{AcademicAdministrationCurricularCourseManagement.curricularCourse.name}</p>" escape="false"/>		

		<fc:dataRepeater value="#{AcademicAdministrationCurricularCourseManagement.curricularCourse.parentContexts}" var="context">
			<h:panelGroup rendered="#{context.externalId == AcademicAdministrationCurricularCourseManagement.contextID}">								
				<h:outputText value="<p><strong>#{bolonhaBundle['courseGroup']}:</strong> " escape="false"/>
				<h:outputText value="#{context.parentCourseGroup.oneFullName}</p>" escape="false"/>			
				<h:outputText value="<p><strong>#{bolonhaBundle['curricularPeriod']}:</strong> " escape="false"/>
				<h:outputText value="#{context.curricularPeriod.fullLabel}</p>" escape="false"/>
			</h:panelGroup>
		</fc:dataRepeater>
		
		<h:panelGroup rendered="#{AcademicAdministrationCurricularCourseManagement.toDelete && !empty AcademicAdministrationCurricularCourseManagement.rulesLabels}">
			<h:outputText value="<br/><strong>#{bolonhaBundle['participating.curricularRules']}: </strong>" escape="false"/>
			<h:outputText value="<ul>" escape="false"/>
			<fc:dataRepeater value="#{AcademicAdministrationCurricularCourseManagement.rulesLabels}" var="curricularRule">
				<h:outputText value="<li>#{curricularRule}</li>" escape="false"/>
			</fc:dataRepeater>
			<h:outputText value="</ul>" escape="false"/>

		</h:panelGroup>

		<h:outputText value="<p class='mtop2 mbottom2'><span class='warning0'>#{bolonhaBundle['confirmDeleteMessage']}</span></p>" escape="false"/>
		
		<h:outputText value="<p>" escape="false"/>
		<h:commandButton alt="#{htmlAltBundle['commandButton.yes']}" styleClass="inputbutton" value="#{bolonhaBundle['yes']}"
			action="buildCurricularPlan"
			actionListener="#{AcademicAdministrationCurricularCourseManagement.deleteContext}" />
		<h:commandButton alt="#{htmlAltBundle['commandButton.no']}" immediate="true" styleClass="inputbutton" value="#{bolonhaBundle['no']}"
			action="buildCurricularPlan"/>
		<h:outputText value="</p>" escape="false"/>
	</h:form>
</f:view>