<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerEspedsg.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/skatglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="20%" valign="bottom" class="tab" align="center" nowrap>
						<font class="tabLink">&nbsp;eSpedsg Roadmap</font>
						<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
						
					</td>
					<%--
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
		               		<a style="display:block;" id="norskImportLink" runat="server" href="skatadmin_norskimport.do">
								<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.admin.norsk.import.list.tab"/></font>
								<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
		               		<a style="display:block;" id="norskExportLink" runat="server" href="skatadmin_norskexport.do">
								<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.admin.norsk.export.list.tab"/></font>
								<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
							</a>
					</td>
					 --%>
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
			<c:if test="${not empty model.list}">
			<tr>
				<td width="2%">&nbsp;</td>
					
				<td width="98%">
				<table cellspacing="0" border="0" cellpadding="0">
			    	    <tr>
						<td >
						<table cellspacing="0" border="0" cellpadding="0">
							<tr class="tableHeaderField" height="20" valign="left">
								<td class="tableHeaderFieldFirst" align="center" >&nbsp;ID&nbsp;</td>
			                    <td width="20%" class="tableHeaderField" align="left" >&nbsp;ISSUE&nbsp;</td>
			                    <td class="tableHeaderField" align="left" >&nbsp;RESOURCE&nbsp;</td>
			                    <td class="tableHeaderField" align="center" >&nbsp;DESCRIPTION&nbsp;</td>
			                    <td class="tableHeaderField" align="center" >&nbsp;STATUS&nbsp;</td>
			                </tr>  
			                   
				            <c:forEach var="record" items="${model.list}" varStatus="counter">    
				               <c:choose>           
				                   <c:when test="${counter.count%2==0}">
				                       <tr class="tableRow" height="20" >
				                   </c:when>
				                   <c:otherwise>   
				                       <tr class="tableOddRow" height="20" >
				                   </c:otherwise>
				               </c:choose>
				               <td class="tableCellFirst" align="center" ><font class="text12MediumBlue">&nbsp;${record.id}&nbsp;</font></td>
				               <td width="20%" class="tableCell" nowrap ><font class="text12MediumBlue">&nbsp;${record.subject}&nbsp;</font></td>
		                       <td class="tableCell" nowrap ><font class="text12MediumBlue">&nbsp;${record.text}&nbsp;</font></td>
		                       <td class="tableCell" ><font class="text12MediumBlue">&nbsp;${record.description}&nbsp;</font></td>
		                       
		                       <td class="tableCell" align="center">
		                       		<c:if test="${record.status == 'G'}">
		                       			<img src="resources/images/bulletGreen.png" width="12px" height="12px" border="0">
		                       		</c:if>
		                       		<c:if test="${record.status == 'R'}">
		                       			<img src="resources/images/bulletRed.png" width="12px" height="12px" border="0">
		                       		</c:if>
		                       		<c:if test="${record.status == 'Y'}">
		                       			<img src="resources/images/bulletYellowModern.png" width="11px" height="11px" border="0">
		                       		</c:if>
		                       		
		                       </td>
				              
				            </tr> 
				            </c:forEach>
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>
		    </c:if>
		    
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 		</table>
		</td>
	</tr>
 			
	 
    
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

