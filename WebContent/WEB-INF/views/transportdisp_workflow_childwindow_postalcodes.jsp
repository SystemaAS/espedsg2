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
			<spring:message code="systema.transportdisp.workflow.childwindow.postalcodes.label.title"/>
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>
		<tr>
		<td valign="top">
		<form action="transportdisp_workflow_childwindow_postalcodes.do?action=doFind" name="searchPostalCodesForm" id="searchPostalCodesForm" method="post">
			<input type="hidden" name="direction" id="direction" value="${model.direction}">
			<input type="hidden" name="caller" id="caller" value="${model.record.caller}">
			
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
							<td class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.childwindow.postalcodes.label.postalcode"/></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="st2kod" id="st2kod" size="6" maxlength="5" value="${model.record.st2kod}"></td>
							
							<td class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.childwindow.postalcodes.label.city"/></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="st2nvn" id="st2nvn" size="20" maxlength="35" value="${model.record.st2nvn}"></td>
						
							<td class="text11">&nbsp;&nbsp;&nbsp;<spring:message code="systema.transportdisp.workflow.childwindow.postalcodes.label.country"/></td>
							<td class="text11">&nbsp;<input type="text" class="inputText" name="st2lk" id="st2lk" size="2" maxlength="2" value="${model.record.st2lk}"></td>
							
							<%--
							<td class="text11">&nbsp;</td>
							<td class="text11">Vis kun</td>
							<td class="text11">
								<select name="wskunpa" id="wskunpa">
		            					<option value="">-velg-</option>
		            					<option value="A" <c:if test="${model.record.wskunpa == 'A'}"> selected </c:if> >Alfakoder</option>
		            					<option value="P" <c:if test="${model.record.wskunpa == 'P'}"> selected </c:if> >PostNr.</option>
								</select>
							</td>
							<td class="text11">&nbsp;</td>
							 --%>
	           				<td align="right">&nbsp;<input class="inputFormSubmit" type="submit" name="submit" value='<spring:message code="systema.transportdisp.search"/>'>
		           		</tr>
		           		</table>
					</td>
					</tr>
					
					<tr><td><hr size="1" width="100%"/></td></tr>								           		
	           		<tr height="15"><td></td></tr>
					
					<tr class="text12" >
					<td class="ownScrollableSubWindowDynamicWidthHeight" width="100%" style="height:30em;">
						<%-- this is the datatables grid (content) --%>
						<c:choose>
							<c:when test="${model.direction == 'fra'}">
								<table id="postNrFromList" class="display compact cell-border" width="100%" >
							</c:when>
							<c:otherwise>
								<table id="postNrToList" class="display compact cell-border" width="100%" >
							</c:otherwise>
						</c:choose>
					
						<thead>
						<tr style="background-color:#EEEEEE">
						    <th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.childwindow.postalcodes.label.postalcode"/>&nbsp;</th>   
		                    <th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.childwindow.postalcodes.label.city"/>&nbsp;</th>
		                    <th class="text11">&nbsp;<spring:message code="systema.transportdisp.workflow.childwindow.postalcodes.label.country"/>&nbsp;</th>
		                    <%--<th class="text11">&nbsp;Via&nbsp;</th> --%>
		                </tr> 
		                </thead>
		                
		                <tbody>
		                <c:forEach var="record" items="${model.postalCodeList}" varStatus="counter">    
			               <c:choose>           
			                   <c:when test="${counter.count%2==0}">
			                   	   <tr class="text11">
			                   </c:when>
			                   <c:otherwise>
 		                   		   <tr class="text11">
			                   </c:otherwise>
			               </c:choose>
			               <td style="width:20%; cursor:pointer;" class="text11MediumBlue" id="postalcode_${record.st2kod}@country_${record.st2lk}@city_${record.st2nvn}@caller_${model.record.caller}@viapnr_${record.viapnr}@dt_postalcode_${counter.count}" >
			               	 &nbsp;<img title="select" valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
			               	 &nbsp;&nbsp;${record.st2kod}
		               	  </td>
			               <td class="text11">&nbsp;${record.st2nvn}</td>
			               <td class="text11">&nbsp;${record.st2lk}</td>
			               <%-- <td class="text11">&nbsp;${record.viapnr}</td> --%>
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
