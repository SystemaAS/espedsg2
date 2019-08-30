<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerAltinnRunnersuite.jsp" />
<!-- =====================end header ==========================-->
	<SCRIPT type="text/javascript" src="resources/js/altinnrunnersuite.js?ver=${user.versionEspedsg}"></SCRIPT>
 	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>
	
 	<table width="100%" class="text14" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text14" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;<spring:message code="systema.altinnrunnersuite.label"/></font>&nbsp;
						<img style="vertical-align: top;" src="resources/images/testsuites.png" height="16px" width="16px" border="0" alt="general list">
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
					
				<td width="100%">
				<table id="containerdatatableTable" width="90%" cellspacing="1" border="0" align="center">
			    	    <tr>
						<td class="text14">
						<table width="100%" id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th width="2%" class="tableHeaderFieldFirst" align="center" >&nbsp;<spring:message code="systema.altinnrunnersuite.id"/>&nbsp;</th>
								<th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.altinnrunnersuite.customer"/></th>
								<th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.altinnrunnersuite.module.inbox"/></th>
								<th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.altinnrunnersuite.module.log1"/></th>
								<th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.altinnrunnersuite.module.log2"/></th>
								
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				              
				               <td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;" align="center" >
				               		<%-- <font class="text12">&nbsp;${record.id}&nbsp;</font>  --%>
				               		<img src="resources/images/leaf.png" height="18px" width="18px" border="0" alt="test module">
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="left" >
				               		<font class="text16">&nbsp;${record.name}</font>
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="left" >
				               		<a target="_blank"  href="${record.url}altinn-proxy/readInnboks.do?user=SYSTEMA" >
					               		&nbsp;<img src="resources/images/checkmarkOK.png" height="18px" width="18px" border="0" alt="inbox">
					               		&nbsp;<font class="text16SkyBlue">&nbsp;.../readInnboks</font>
					               	</a>
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="left" >
				               		<a target="_blank"  href="${record.url}altinn-proxy/showHistory.do?user=SYSTEMA&filename=log4j_altinn-proxy-history.log" >
					               		&nbsp;<img src="resources/images/log-iconLOG.png" height="22px" width="22px" border="0" alt="history today">
					               		&nbsp;<font class="text16SkyBlue">&nbsp;.../showHistory</font>
					               	</a>
					               		
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="left" >
				               		<a target="_blank"  href="${record.url}altinn-proxy/showHistory.do?user=SYSTEMA&filename=log4j_altinn-proxy-history.log.${model.yesterday}" >
					               		&nbsp;<img src="resources/images/log-iconLOG.png" height="22px" width="22px" border="0" alt="inbox">
					               		&nbsp;<font class="text16SkyBlue">&nbsp;.../showHistory-2</font>
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
	 		</table>
		</td>
	</tr>
</table>	
       
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

