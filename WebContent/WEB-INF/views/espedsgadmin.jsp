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
						<font class="tabLink">&nbsp;Customer matrix</font>
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
			<c:if test="${not empty model.dbObjectList}">
			<tr>
				<td width="2%">&nbsp;</td>
					
				<td width="98%">
				<table cellspacing="0" border="0" cellpadding="0">
			    	    <tr>
						<td >
						<table cellspacing="0" border="0" cellpadding="0">
							<tr class="tableHeaderField" height="20" valign="left">
			                    <td width="20%" class="tableHeaderFieldFirst" align="left" >&nbsp;CUSTOMER&nbsp;</td>
			                    <td nowrap width="10%" class="tableHeaderField" align="left" >&nbsp;Java / TOMCAT / AS400&nbsp;</td>
			                    <td width="20%" class="tableHeaderField" align="left" >&nbsp;URL&nbsp;</td>
			                    <td colspan="10" class="tableHeaderField" align="center" >MODULES</td>
			                </tr>  
			                   
				            <c:forEach var="customer" items="${model.dbObjectList}" varStatus="counter">    
				               <c:choose>           
				                   <c:when test="${counter.count%2==0}">
				                       <tr class="tableRow" height="20" >
				                   </c:when>
				                   <c:otherwise>   
				                       <tr class="tableOddRow" height="20" >
				                   </c:otherwise>
				               </c:choose>
				               <td width="20%" class="tableCellFirst" nowrap ><font class="text11MediumBlue">&nbsp;${customer.name}&nbsp;</font></td>
		                       <td width="10%" class="tableCell" nowrap ><font class="text11MediumBlue">&nbsp;${customer.version}&nbsp;</font></td>
		                       <td width="20%" class="tableCell" nowrap >
		                       	<c:choose>
				               		<c:when test="${'DSV' == customer.name || 'DHL' == customer.name || 'Schenker NO' == customer.name}">
	                       				<font class="text11Gray">&nbsp;&nbsp;${customer.url}&nbsp;&nbsp;</font>		                       			
		                       		</c:when>
		                       		<c:otherwise>
		                       			<a tabindex="-1" class="text14" target="_blank" href="${customer.url}">
		                       				<font class="text12MediumBlue">&nbsp;&nbsp;${customer.url}&nbsp;&nbsp;</font>
		                       			</a>
		                       		</c:otherwise>
                       			</c:choose>
                       		   </td>
		                       <c:forEach var="module" items="${customer.applicationList}"  >    
				               	<c:choose>
					               <c:when test="${module!='-1'}">
						               <td class="tableCell" nowrap >&nbsp;
							               	<c:if test="${ fn:startsWith(module, 'SKAT')}">
							               		<img valign="bottom" src="resources/images/countryFlags/Flag_DK.gif" height="11" border="0" alt="country">
							               	</c:if>
							               	<c:if test="${ fn:startsWith(module, 'TDS')}">
							               		<img valign="bottom" src="resources/images/countryFlags/Flag_SE.gif" height="11" border="0" alt="country">
							               	</c:if>
							               	<c:if test="${ fn:startsWith(module, 'SAD') || fn:startsWith(module, 'UFORT') || fn:startsWith(module, 'SPØ') || fn:startsWith(module, 'AVG') }">
							               		<img valign="bottom" src="resources/images/countryFlags/Flag_NO.gif" height="11" border="0" alt="country">
							               	</c:if>
							               	<c:if test="${ fn:startsWith(module, 'PRIS') || fn:startsWith(module, 'WORK') || fn:startsWith(module, 'EFAK') || fn:startsWith(module, 'EBOOK') 
							               		|| fn:startsWith(module, 'ANALYSE') || fn:startsWith(module, 'ALTINN')  }">
							               		<img valign="bottom" src="resources/images/countryFlags/Flag_NO.gif" height="11" border="0" alt="country">
							               	</c:if>
																		               	
							               	<font class="text11">&nbsp;${module}&nbsp;</font>
						               </td>
    					               </c:when>
					               <c:otherwise>
					               	   <td class="tableCell" >&nbsp;&nbsp;</td>
					               </c:otherwise>
					             </c:choose>  
				               </c:forEach>
				              
				            </tr> 
				            </c:forEach>
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>
		    </c:if>
		    
	 	    <tr height="40"><td>&nbsp;</td></tr>
	 	    
	 	    <%-- list component --%>
			<c:if test="${not empty model.dbTomcatPortsObjectList}">
			<tr>
				<td width="2%">&nbsp;</td>
				<td colspan="8" class="text14"><b>&nbsp;SaaS Tomcat [ server.xml ]</b> configuration ports</td>
			</tr>
			<tr>
				<td width="2%">&nbsp;</td>
					
				<td width="98%">
				<table cellspacing="0" border="0" cellpadding="0">
			    	    <tr>
						<td >
						<table cellspacing="0" border="0" cellpadding="0">
							<tr class="tableHeaderField" height="20" valign="left">
			                    <td class="tableHeaderFieldFirst" align="left" >&nbsp;CUSTOMER&nbsp;</td>
			                    <td class="tableHeaderField" align="left" >&nbsp;CONNECTOR port&nbsp;</td>
			                    <td class="tableHeaderField" align="left" >&nbsp;SHUTDOWN port&nbsp;</td>
			                    <td class="tableHeaderField" align="left" >&nbsp;SSL port&nbsp;</td>
			                    <td class="tableHeaderField" align="left" >&nbsp;AJP port&nbsp;</td>
			                    <td class="tableHeaderField" align="left" ><font style="color:green">&nbsp;ASP HTTP-server port&nbsp;</font></td>
			                </tr>  
			                   
				            <c:forEach var="record" items="${model.dbTomcatPortsObjectList}" varStatus="counter">    
				               <c:choose>           
				                   <c:when test="${counter.count%2==0}">
				                       <tr class="tableRow" height="20" >
				                   </c:when>
				                   <c:otherwise>   
				                       <tr class="tableOddRow" height="20" >
				                   </c:otherwise>
				               </c:choose>
				               <td class="tableCellFirst"  ><font class="text12MediumBlue">&nbsp;${record.aspCustomerName}&nbsp;</font></td>
		                       <td class="tableCell"  ><font class="text12MediumBlue">&nbsp;${record.connectorPort}&nbsp;</font></td>
		                       <td class="tableCell"  ><font class="text12MediumBlue">&nbsp;${record.shutdownPort}&nbsp;</font></td>
		                       <td class="tableCell"  ><font class="text12MediumBlue">&nbsp;${record.sslPort}&nbsp;</font></td>
		                       <td class="tableCell"  ><font class="text12MediumBlue">&nbsp;${record.ajpPort}&nbsp;</font></td>
		                       <td class="tableCell" align="right" ><font class="text12MediumBlue" style="color:green">&nbsp;<b>${record.systemaHttpPort}&nbsp;</b></font></td>
		                       
		                       
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

