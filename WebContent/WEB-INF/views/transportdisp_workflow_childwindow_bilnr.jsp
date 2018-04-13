<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerTransportDispChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_workflow_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="90%" height="500px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="0" border="0" cellpadding="0">
		<tr height="5"><td colspan="2"></td></tr>
		<tr>
			<td colspan="3" class="text14Bold">&nbsp;&nbsp;&nbsp;
			<img title="search" valign="bottom" src="resources/images/search.gif" width="24px" height="24px" border="0" alt="search">
			<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.title"/>
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
		<form action="transportdisp_workflow_childwindow_bilnr.do?action=doFind" name="searchBilNrForm" id="searchBilNrForm" method="post">
			<%-- =====================================================  --%>
          	<%-- Here we have the search [Customer] popup window --%>
          	<%-- =====================================================  --%>
          		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
			 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
				<table id="containerdatatableTable" cellspacing="2" align="left" width="100%" >
					<tr height="5"><td></td></tr>
					<tr>
					<td>
						<table>
						<tr>
							<td class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.no"/></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="sokbnr" id="sokbnr" size="10" maxlength="10" value="${model.container.sokbnr}"></td>
						
							<td class="text11">&nbsp;&nbsp;&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.carrier"/></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="soktnr" id="soktnr" size="10" maxlength="8" value="${model.container.soktnr}"></td>

							<td class="text11">&nbsp;&nbsp;&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.truckType"/></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="sokut" id="sokut" size="5" maxlength="4" value="${model.container.sokut}"></td>
						
							<td class="text11">&nbsp;</td>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.transportdisp.search"/>'>
		           		</tr>
		           		
		           		</table>
					</td>
					</tr>
					<%-- Validation errors --%>
					<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
					<tr>
						<td colspan="20">
			            	<table align="left" border="0" cellspacing="0" cellpadding="0">
			            	<tr>
							<td class="textError">					
					            <ul>
					            <c:forEach var="error" items="${errors.allErrors}">
					                <li >
					                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
					                </li>
					            </c:forEach>
					            </ul>
							</td>
							</tr>
							</table>
						</td>
					</tr>
					</spring:hasBindErrors>

					<tr><td><hr size="1" width="100%"/></td></tr>								           		
	           		<tr height="15"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
					<%-- this is the datatables grid (content)--%>
					<table id="bilnrList" class="display compact cell-border" width="100%" >
						<thead>
						<tr style="background-color:#EEEEEE">
							<th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.no"/>&nbsp;</th>   
		                    <th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.henger"/>&nbsp;</th>
		                    <th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.countryCode"/>&nbsp;</th>
		                    <th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.weight"/>&nbsp;</th>
		                    <th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.tara"/>&nbsp;</th>
		                    <th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.m3"/>&nbsp;</th>
		                    <th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.lm"/>&nbsp;</th>
		                    <th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.truckType"/>&nbsp;</th>
		                    <th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.form.label.trucklic.carrier"/>&nbsp;</th>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.bilNrList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                       <tr class="text11">
			                   </c:when>
			                   <c:otherwise>   
			                       <tr class="text11">
			                   </c:otherwise>
			               </c:choose>
			               <td style="width:20%; cursor:pointer;" class="text11MediumBlue" id="bilnr_${record.unbiln}@heng_${record.untilh}@country_${record.unland}@trucktype_${record.untrme}@trucknr_${record.untran}@truckdriver_${record.vmnavn}@unretu_${record.unretu}@unretunavn_${record.unretunavn}@dt_${counter.count}" >
			               		&nbsp;<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
			               		&nbsp;&nbsp;${record.unbiln}
		               	   </td>
			               <td class="text11">&nbsp;${record.untilh}</td>
			               <td class="text11">&nbsp;${record.unland}</td>
			               <td class="text11">&nbsp;${record.unvekt}</td>
			               <td class="text11">&nbsp;${record.untara}</td>
			               <td class="text11">&nbsp;${record.unm3}</td>
			               <td class="text11">&nbsp;${record.unlm}</td>
			               <td class="text11">&nbsp;${record.ununty}</td>
			               <td class="text11">&nbsp;${record.vmnavn}</td>
			               
			            </tr> 
			            </c:forEach>
			            </tbody>
		            </table>
		            </td>
	           		</tr>
        			</table>
				
		</form>	
		</td>
		</tr>
	</table> 
