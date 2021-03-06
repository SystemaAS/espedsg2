<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancearkivgate.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkSadMaintImportGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
						<font class="tabDisabledLink">&nbsp;
							<spring:message code="systema.main.maintenance.label"/>
						</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tab" align="center">
						<font class="tabLink">&nbsp;
							<spring:message code="systema.main.maintenance.arkiv"/>
						</font>&nbsp;
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="arkiv general list">
					</td>
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
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="90%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text14">
						<table width="100%" id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
							    <th width="2%" class="tableHeaderFieldFirst" align="center" >&nbsp;<spring:message code="systema.edit"/></th>
			                    <th width="80%" class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.main.maintenance.main.gate.description"/>&nbsp;</th>
			                    <%--
			                    <th class="tableHeaderField" align="left" >&nbsp;<spring:message code="systema.main.maintenance.main.gate.text"/>&nbsp;</th>
			                    <th class="tableHeaderField" align="center" >&nbsp;<spring:message code="systema.main.maintenance.main.gate.status"/>&nbsp;</th>
			                     --%>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				              
				               <%-- <td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;" align="center" ><font class="text12">&nbsp;${record.id}&nbsp;</font></td>--%>
				               <td width="2%" class="tableCellFirst" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">
				               	<c:choose>
				               		<c:when test="${record.status == 'G' || record.status == 'Y'}">
					               		<a id="alinkRecordId_${counter.count}" onClick="setBlockUI(this);" href="${record.code}_${record.pgm}.do?id=${record.dbTable}">
		               						<img src="resources/images/update.gif" border="0" alt="edit">
					               		</a>
				               		</c:when>
				               		<c:otherwise>
											<img src="resources/images/lock.gif" border="0" alt="edit">				               		
				               		</c:otherwise>
				               	</c:choose>	
				               </td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" width="80%" >
				               		<c:choose>
					               		<c:when test="${record.status == 'G' || record.status == 'Y'}">
					               			<a id="alinkRecordDesc_${counter.count}" onClick="setBlockUI(this);" href="${record.code}_${record.pgm}.do?id=${record.dbTable}">
		               							<font class="text16SkyBlue">&nbsp;&nbsp;${record.subject}&nbsp;</font>
					               			</a>
					               		</c:when>
					               		<c:otherwise>
					               			<font class="text16">&nbsp;&nbsp;${record.subject}&nbsp;</font>
					               		</c:otherwise>
				               		</c:choose>
				               </td>
				               <%--
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.text}&nbsp;</font></td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;"align="center">
		                       		<c:if test="${empty record.status}">
	                       				<img src="resources/images/bulletRed.png" width="12px" height="12px" border="0">
		                       		</c:if>
		                       		<c:if test="${record.status == 'G'}">
		                       			<img src="resources/images/bulletGreen.png" width="12px" height="12px" border="0">
		                       		</c:if>
		                       		<c:if test="${record.status == 'Y'}">
		                       			<img src="resources/images/bulletYellowModern.png" width="11px" height="11px" border="0">
		                       		</c:if>
				              </td>
				               --%>
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

