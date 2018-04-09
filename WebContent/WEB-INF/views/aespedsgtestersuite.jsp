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
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.testersuite.label"/></font>&nbsp;
						<img style="vertical-align: top;" src="resources/images/testsuites.png" height="16px" width="16px" border="0" alt="general list">
						&nbsp;<font class="text11SkyBlue">(${model.listSize})</font>
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
					
				<td width="100%">
				<table id="containerdatatableTable" width="90%" cellspacing="1" border="0" align="center">
			    	    <tr>
						<td class="text11">
						<table width="100%" id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th width="2%" class="tableHeaderFieldFirst" align="center" >&nbsp;<spring:message code="systema.testersuite.id"/>&nbsp;</th>
								<th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.testersuite.moduleName"/></th>
			                    <th nowrap width="2%" class="tableHeaderField" align="center" >&nbsp;<spring:message code="systema.testersuite.testEngine"/>&nbsp;</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				              
				               <td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;" align="center" >
				               		<%-- <font class="text12">&nbsp;${record.id}&nbsp;</font>  --%>
				               		<img src="resources/images/leaf.png" height="16px" width="16px" border="0" alt="test module">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="left" >
				               		<c:choose>
					               		<c:when test="${not empty record.serviceUrl}">
					               			<a onClick="setBlockUI(this);" href="${record.serviceUrl}.do?tm=${record.text}" >
					               				<font class="text12SkyBlue">&nbsp;${record.moduleName}</font>
					               			</a>
					               		</c:when>
					               		<c:otherwise>
					               			<font class="text12">&nbsp;${record.moduleName}</font>
					               		</c:otherwise>
				               		</c:choose>
				               </td>
				               	
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">
				               		<img src="resources/images/engines.png" height="16px" width="16px" border="0" alt="in process">
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
	 		</table>
		</td>
	</tr>
</table>	
       
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

