<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTestersuite.jsp" />
<!-- =====================end header ==========================-->
	<SCRIPT type="text/javascript" src="resources/js/aespedsgtestersuite.js?ver=${user.versionEspedsg}"></SCRIPT>
 	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
 	<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a class="text14" onClick="setBlockUI(this);" href="aespedsgtestersuite.do" >
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.testersuite.label"/></font>&nbsp;
							<img style="vertical-align: top;" src="resources/images/testsuites.png" height="16px" width="16px" border="0" alt="general list test suites">
							
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">${model.moduleChild}&nbsp;<spring:message code="systema.testersuite.module.child.suffix.label"/></font>&nbsp;
						<%-- <img style="vertical-align: top;" src="resources/images/engines.png" height="14px" width="14px" border="0" alt="test suite"> --%>
						<img style="vertical-align: top;" src="resources/images/leaf.png" height="16px" width="16px" border="0" alt="test module">
						&nbsp;<font class="text12SkyBlue">(${model.listSize})</font>
						
					</td>
					
					
					<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
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
				<td width="2%">&nbsp;</td>
					
				<td width="100%">
				<table id="containerdatatableTable" width="90%" cellspacing="1" border="0" align="center">
			    	    <tr>
						<td class="text11">
						<table width="100%" id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th nowrap width="2%" class="tableHeaderFieldFirst" align="center" >&nbsp;<spring:message code="systema.testersuite.module.child.status"/>&nbsp;</th>
								<th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.testersuite.module.child.serviceName"/></th>
			                    <th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.testersuite.module.child.error.description"/></th>
			                    
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.listServices}" varStatus="counter">   
			               	<tr class="tableRow" height="20" >
				              	<td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;" align="center">
				               		<c:if test="${record.status == 'OK'}">
		                       			<img src="resources/images/bulletGreen.png" width="11px" height="11px" border="0">
		                       		</c:if>
		                       		<c:if test="${record.status == 'NOK'}">
		                       			<img src="resources/images/bulletRed.png" width="11px" height="11px" border="0">
		                       		</c:if>
		                       		<c:if test="${record.status == 'Y'}">
		                       			<img src="resources/images/bulletYellowModern.png" width="11px" height="11px" border="0">
		                       		</c:if>
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="left" >
				               		<a style="cursor:pointer" onClick="window.open('${record.serviceUrl}', 'testwindow', 'width=700,height=200,top=400,left=400,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,copyhistory=no,resizable=yes')" target="_blank">
				               			<font class="text14">&nbsp;${record.serviceName}</font>
				               		</a>
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="left" ><font class="text12">&nbsp;<c:if test="${not empty record.errMsg && record.errMsg != 'null'}">${record.errMsg}</c:if></font></td>
				               
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
	 		</table>
		</td>
	</tr>
</table>	
       
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

