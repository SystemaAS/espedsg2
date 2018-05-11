<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancesign_syfa60r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text12" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text12" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.main.maintenance.label"/></font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tab" align="center">
						<font class="tabLink">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesyfa60.signatures"/></font>&nbsp;
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="sign general list">
					</td>
					<%--
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="TODOmainmaintenancesign_syfa60r_edit.do">
							<font class="tabDisabledLink">&nbsp;Lage ny avd.</font>&nbsp;						
							<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
						</a>
					</td>
					 --%>
					<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
			<%-- list component --%>
			<tr>
				<td width="3%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="99%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text12">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
							    <th width="2%" class="tableHeaderFieldFirst" align="center" ><spring:message code="systema.main.maintenance.mainmaintenancesyfa60.update"/></th>                                                            
								<th width="2%" class="tableHeaderField" align="center" ><spring:message code="systema.main.maintenance.mainmaintenancesyfa60.signature"/></th>
								<th width="40%" class="tableHeaderField" align="center" ><spring:message code="systema.main.maintenance.mainmaintenancesyfa60.name"/></th>
								<th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesyfa60.parameters"/></th>
			                    <th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesyfa60.userid"/></th>
			                    <th class="tableHeaderField" align="center" ><spring:message code="systema.delete"/></th>			                    
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td id="recordUpdate_${record.kosfsi}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
					               	<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">${record.kosfsi}</td>
				               <td width="40%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.kosfnv}&nbsp;</td>
				               <td align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >
					               	<c:if test="${not empty record.syuser}">
					               		<a id="alinkMainMaintParamSyuser" tabindex=-1 style="display:block;" href="mainmaintenancesign_syfa60r_params.do?syuser=${record.syuser}">
					               			<img src="resources/images/textContent.gif" width="12px" height="14px" border="0" alt="edit">
					               		</a>
					               	</c:if>
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.syuser}&nbsp;</td>
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="mainmaintenancesign_syfa60r_edit.do?action=doDelete&kosfsi=${record.kosfsi}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>		    
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
	 	    <%-- ----------------- --%>
			<%-- Validation errors --%>
			<%-- ----------------- --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td >
		           	<table align="left" border="0" cellspacing="0" cellpadding="0">
		           	<tr >
		           	<td >					
			            <ul class="isa_error text14" >
			            <c:forEach var="error" items="${errors.allErrors}">
			                <li >
			                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>&nbsp;&nbsp;
			                </li>
			            </c:forEach>
			            </ul>
					</td>
					</tr>
					</table>
				</td>
			</tr>
			</spring:hasBindErrors>
			
			<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
				<td width="5%">&nbsp;</td>
				<td >
		           	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td >
				 				<ul class="isa_error text14" >
		                                  <li>[ERROR on Update] - Server return code: ${model.errorMessage}</li>                                    
		                              </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
	 	    
	 	    <tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<form action="mainmaintenancesign_syfa60r_edit.do" name="formRecord" id="formRecord" method="POST" onSubmit="enableDisabledFields();">
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
					<input type="hidden" name="action" id=action value="doUpdate">
					<%--
					<input type="hidden" name="hoavd" id=hoavd value="${model.avd}">
					<input type="hidden" name="avdnavn" id=avdnavn value="${model.avdnavn}">
					 --%>
					 
					<table width="95%" cellspacing="1" border="0" align="left">
						<tr height="5"><td></td></tr>
						<tr >
							<td class="text14" ><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" ><spring:message code="systema.new"/></button></td>
						</tr>
						<tr height="20"><td></td></tr>
						<tr>
							<td class="text14" ><font class="text14RedBold" >*</font><span title="kosfsi"><spring:message code="systema.main.maintenance.mainmaintenancesyfa60.signature"/>&nbsp;</span>
								<c:choose>
									<c:when test="${not empty model.updateId}">
										<input readonly type="text" class="inputTextReadOnly"  name="kosfsi" id="kosfsi" size="5" maxlength="3" value='${model.record.kosfsi}'>
									</c:when>
									<c:otherwise>
										<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField"  name="kosfsi" id="kosfsi" size="5" maxlength="3" value='${model.record.kosfsi}'>
									</c:otherwise>
								</c:choose>
								&nbsp;&nbsp;<font class="text14RedBold" >*</font><span title="kosfnv"><spring:message code="systema.main.maintenance.mainmaintenancesyfa60.name"/>&nbsp;</span>
								<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="kosfnv" id="kosfnv" size="20" maxlength="16" value='${model.record.kosfnv}'>
								
 	    						&nbsp;&nbsp;&nbsp;<span title="syuser"><spring:message code="systema.main.maintenance.mainmaintenancesyfa60.userid"/></span>
 	    						<input type="text" class="inputTextMediumBlue" name="syuser" id="syuser" size="11" maxlength="10" value="${model.record.syuser}" />
 	    						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.save"/>' />
						
 	    					</td>
		    	    	</tr>
		    	    	<tr height="20"><td></td></tr>
					</table>
				</form>
				</td>
			</tr>

	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

