<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="Util.Data" %>
<%@ page import="java.util.Date" %>

<table>
	<tr>
		<logic:present name="infoDegreeCurricularPlan">
			<td>
				<h3><bean:message key="label.manager.degreeCurricularPlan.administrating"/></h3>
			</td>
			<td>
				<h2><b><bean:write name="infoDegreeCurricularPlan" property="name"/></b></h2>
			</td>
		</logic:present>		
	</tr>
</table>

<ul style="list-style-type: square;">
	<li><html:link page="<%="/editDegreeCurricularPlan.do?method=prepareEdit&degreeId="  + request.getParameter("degreeId") + "&amp;degreeCurricularPlanId=" + request.getParameter("degreeCurricularPlanId")%>"><bean:message key="label.manager.edit.degreeCurricularPlan"/></html:link></li>
	<li><html:link page="<%="/insertCurricularCourse.do?method=prepareInsert&degreeId=" + request.getParameter("degreeId") + "&amp;degreeCurricularPlanId=" + request.getParameter("degreeCurricularPlanId")%>"><bean:message key="label.manager.insert.curricularCourse"/></html:link></li>			
	<li><html:link page="<%="/insertExecutionDegree.do?method=prepareInsert&degreeId=" + request.getParameter("degreeId") + "&amp;degreeCurricularPlanId=" + request.getParameter("degreeCurricularPlanId")%>"><bean:message key="label.manager.insert.executionDegree"/></html:link></li>			
</ul>

<span class="error"><html:errors/></span>

<h3><bean:message key="label.manager.curricularCourses"/></h3>

<logic:empty name="curricularCoursesList">
<i><bean:message key="label.manager.curricularCourses.nonExisting"/></i>
</logic:empty>

<logic:present name="curricularCoursesList" scope="request">
<logic:notEmpty name="curricularCoursesList">
	
	<html:form action="/deleteCurricularCourses" method="get">
	
	 <bean:define id="onclick">
			return confirm('<bean:message key="message.confirm.delete.curricular.courses"/>')
		  </bean:define>
	
		<html:hidden property="degreeCurricularPlanId" value="<%= request.getParameter("degreeCurricularPlanId") %>"/>
		<html:hidden property="degreeId" value="<%= request.getParameter("degreeId") %>"/>
			<table width="100%" cellpadding="0" border="0">
				<tr>
					<td class="listClasses-header">
					</td>
					<td class="listClasses-header"><bean:message key="label.manager.curricularCourse.name" />
					</td>
					<td class="listClasses-header"><bean:message key="label.manager.curricularCourse.code" />
					</td>
				</tr>
				<logic:iterate id="curricularCourse" name="curricularCoursesList">
				<tr>	 
					<td class="listClasses">
						<html:multibox property="internalIds">
							<bean:write name="curricularCourse" property="idInternal"/>
						</html:multibox>
					</td>				
					<td class="listClasses"><p align="left"><html:link page="<%= "/readCurricularCourse.do?degreeId=" + request.getParameter("degreeId") + "&degreeCurricularPlanId=" + request.getParameter("degreeCurricularPlanId")%>" paramId="curricularCourseId" paramName="curricularCourse" paramProperty="idInternal"><bean:write name="curricularCourse" property="name"/></html:link></p>
					</td>
					<td class="listClasses"><html:link page="<%= "/readCurricularCourse.do?degreeId=" + request.getParameter("degreeId") + "&degreeCurricularPlanId=" + request.getParameter("degreeCurricularPlanId")%>" paramId="curricularCourseId" paramName="curricularCourse" paramProperty="idInternal"><bean:write name="curricularCourse" property="code"/></html:link>
					</td>
	 			</tr>
	 			</logic:iterate>			
			</table>
			
<br>

		<html:submit onclick='<%=onclick.toString() %>'><bean:message key="label.manager.delete.selected.curricularCourses"/></html:submit>
	</html:form> 
</logic:notEmpty>	 	
</logic:present>

<br>

<br>
<h3><bean:message key="label.manager.executionDegrees"/></h3>

<logic:empty name="executionDegreesList">
<i><bean:message key="label.manager.executionDegrees.nonExisting"/></i>
</logic:empty>

<logic:present name="executionDegreesList" scope="request">
<logic:notEmpty name="executionDegreesList">
	
	<html:form action="/deleteExecutionDegrees" method="get">
	
	<bean:define id="onclick">
			return confirm('<bean:message key="message.confirm.delete.execution.degrees"/>')
		  </bean:define>
		  
		<html:hidden property="degreeCurricularPlanId" value="<%= request.getParameter("degreeCurricularPlanId") %>"/>
		<html:hidden property="degreeId" value="<%= request.getParameter("degreeId") %>"/>
			<table width="70%" cellpadding="0" border="0">
				<tr>
					<td class="listClasses-header">
			
					</td>
					<td class="listClasses-header"><bean:message key="label.manager.executionDegree.executionYear" />
					</td>
					<td class="listClasses-header"><bean:message key="label.manager.executionDegree.coordinator" />
					</td>
					<td class="listClasses-header"><bean:message key="label.manager.executionDegree.temporaryExamMap" />
					</td>
				</tr>
				<logic:iterate id="executionDegree" name="executionDegreesList">
				<tr>	 
					<td class="listClasses">
						<html:multibox property="internalIds">
							<bean:write name="executionDegree" property="idInternal"/>
						</html:multibox>
					</td>
					<bean:define id="executionYear" name="executionDegree" property="infoExecutionYear"/>				
					<td class="listClasses"><bean:write name="executionYear" property="year"/>
					</td>
					<td class="listClasses">
						<logic:notPresent name="executionDegree" property="infoCoordinator">
							NA
						</logic:notPresent>
						<logic:present name="executionDegree" property="infoCoordinator">
							<bean:write name="executionDegree" property="infoCoordinator.infoPerson.nome"/>
						</logic:present>
					</td>
					<td class="listClasses">
						<logic:notEmpty name="executionDegree" property="temporaryExamMap">
							<bean:define id="tempExamMap" name="executionDegree" property="temporaryExamMap"/>
							<logic:present name="tempExamMap">
								<logic:equal name="tempExamMap" value="true">
									Sim
								</logic:equal> 
								<logic:notEqual name="tempExamMap" value="true">
									N�o
								</logic:notEqual>
							</logic:present>
						</logic:notEmpty>
						<logic:notPresent name="tempExamMap">
							---
						</logic:notPresent>	
					</td>
					<td>
						<html:link page="<%= "/editExecutionDegree.do?method=prepareEdit&degreeId=" + request.getParameter("degreeId") + "&degreeCurricularPlanId=" + request.getParameter("degreeCurricularPlanId")%>" paramId="executionDegreeId" paramName="executionDegree" paramProperty="idInternal"><bean:message key="label.edit"/></html:link>
					</td>
	 			</tr>
	 			</logic:iterate>						
			</table>
			
<br>	

		<html:submit onclick='<%=onclick.toString() %>'><bean:message key="label.manager.delete.selected.executionDegrees"/></html:submit>
	</html:form> 
</logic:notEmpty>	 	
</logic:present>