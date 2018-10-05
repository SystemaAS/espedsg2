<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerEspedsg.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/espedsgadmin.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	
	/* this line will align the datatable search field in the left */
	.dataTables_wrapper .custMatrixFilter .dataTables_filter{float:left}
	.dataTables_wrapper .custMatrixFilter .dataTables_info{float:right}

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
						<input type="hidden" name="language" id="language" value='${user.usrLang}'>
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
					
				<td width="100%">
				<table id="containerdatatableTable" width="99%" cellspacing="1" border="0" align="left" >
			    	    <tr>
						<td >
						<table id="mainList" class="display compact cell-border" >
							<thead style="width:100%"; >
							<tr class="tableHeaderField" height="20" valign="left">
			                    <th width="20%" class="text14" align="left" >&nbsp;CUSTOMER&nbsp;</th>
			                    <th nowrap width="10%" class="text14" align="left" >&nbsp;Java / TOMCAT / AS400&nbsp;</th>
			                    <th width="10%" class="text14" align="left" >&nbsp;URL&nbsp;</th>
			                    <th width="10%" class="text14" align="left" >&nbsp;URL - HTTPS&nbsp;</th>
			                    <%-- NOTE: as many columns as apps in custApps.db file. --%>
			                    <th class="text14" align="center" >1</th>
			                    <th class="text14" align="center" >2</th>
			                    <th class="text14" align="center" >3</th>
			                    <th class="text14" align="center" >4</th>
			                    <th class="text14" align="center" >5</th>
			                    <th class="text14" align="center" >6</th>
			                    <th class="text14" align="center" >7</th>
			                    <th class="text14" align="center" >8</th>
			                    <th class="text14" align="center" >9</th>
								<th class="text14" align="center" >10</th>
			                </tr>  
			                </thead> 
			                
			                <tbody> 
				            <c:forEach var="customer" items="${model.dbObjectList}" varStatus="counter">  
				               <c:if test="${customer != null}">   
					               <tr height="20" >
					               <td width="20%" class="text14" nowrap ><font class="text14MediumBlue">&nbsp;${customer.name}&nbsp;</font></td>
			                       <td width="10%" class="text14" nowrap ><font class="text14MediumBlue">&nbsp;${customer.version}&nbsp;</font></td>
			                       <c:choose>
					               		<c:when test="${'DSV' == customer.name || 'DHL' == customer.name || 'Schenker NO' == customer.name}">
					               			<td width="10%" class="text14" nowrap >
			                       	
		                       					<font class="text12Gray">&nbsp;&nbsp;${customer.url}&nbsp;&nbsp;</font>	
		                       				</td>
		                       				<td width="10%" class="text14" nowrap >
		                       					<c:if test="${not empty customer.urlHttps}">
			                       				<font class="text12Gray">&nbsp;&nbsp;${customer.urlHttps}&nbsp;&nbsp;</font>
			                       				</c:if>	
		                       				</td>	                       			
			                       		</c:when>
			                       		<c:otherwise>
			                       			<td width="10%" class="text14" nowrap >
				                       			<a tabindex="-1" class="text14" target="_blank" href="${customer.url}">
				                       				<font class="text14MediumBlue">&nbsp;${customer.url}&nbsp;</font>
				                       			</a>
			                       			</td>
			                       			<td width="10%" class="text14" nowrap >
			                       				<c:if test="${not empty customer.urlHttps}">
				                       			<a tabindex="-1" class="text14" target="_blank" href="${customer.urlHttps}">
				                       				<font class="text14MediumBlue" style="color:green">&nbsp;${customer.urlHttps}&nbsp;</font>
				                       			</a>
				                       			</c:if>
			                       			</td>
			                       		</c:otherwise>
	                       			</c:choose>
	                       		   
	                       		   <c:if test="${not empty customer.applicationList}"> 
			                       <c:forEach var="module" items="${customer.applicationList}"  >
			                        	<c:choose>
						               		<c:when test="${not empty module }">
						               			<c:choose>
							               			<c:when test="${ fn:startsWith(module, 'SKAT') || fn:startsWith(module, 'TDS')}">
							               				<c:choose>
								               				<c:when test="${ fn:startsWith(module, 'SKAT')}">
											               		<td  class="text14" nowrap >&nbsp;	
											               			<img style="vertical-align: text-baseline;" src="resources/images/countryFlags/Flag_DK.gif" height="11" border="0" alt="country">
											               			<font class="text14">&nbsp;${module}&nbsp;</font>
											               		</td>
										               		</c:when>
										               		<c:otherwise>
										               			<td  class="text14" nowrap >&nbsp;	
											               			<img style="vertical-align: text-baseline;" src="resources/images/countryFlags/Flag_SE.gif" height="11" border="0" alt="country">
											               			<font class="text14">&nbsp;${module}&nbsp;</font>
											               		</td>
										               		</c:otherwise>
									               		</c:choose>
									               	</c:when>
									               	<c:otherwise>
									               		<c:choose>
									               			<c:when test="${module == '-1'}">
											               		<td  class="text14"  ><font class="text14">&nbsp;&nbsp;</font></td>
										               		</c:when>
										               		<c:otherwise>
										               			<td  class="text14" nowrap >&nbsp;
											               			<img style="vertical-align: text-baseline;" src="resources/images/countryFlags/Flag_NO.gif" height="11" border="0" alt="country">
											               			<font class="text14">&nbsp;${module}&nbsp;</font>
											               		</td>
										               		</c:otherwise>
									               		</c:choose>
									               	</c:otherwise>
								               	</c:choose>
								               	 
								         	</c:when>
								         <c:otherwise>
								         	<td class="text14" >&nbsp;QUE?&nbsp;</td>
								         </c:otherwise>
							         </c:choose>     	
			                       </c:forEach>	
			                       </c:if>			             
					           </tr> 
					        </c:if>
		            	</c:forEach>
			            </tbody>
			            
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>
		    </c:if>
		    
	 	    <tr height="40"><td >&nbsp;</td></tr>
	 	    
	 	    <%-- list component --%>
			<c:if test="${not empty model.dbTomcatPortsObjectList}">
			<tr>
				<td width="2%">&nbsp;</td>
				<td colspan="8" class="text14"><b>&nbsp;SaaS TOMCAT [ server.xml ]</b> configuration ports</td>
			</tr>
			<tr height="5"><td>&nbsp;</td></tr>
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
				               <td class="tableCellFirst"  ><font class="text14MediumBlue">&nbsp;${record.aspCustomerName}&nbsp;</font></td>
		                       <td class="tableCell"  ><font class="text14MediumBlue">&nbsp;${record.connectorPort}&nbsp;</font></td>
		                       <td class="tableCell"  ><font class="text14MediumBlue">&nbsp;${record.shutdownPort}&nbsp;</font></td>
		                       <td class="tableCell"  ><font class="text14MediumBlue">&nbsp;${record.sslPort}&nbsp;</font></td>
		                       <td class="tableCell"  ><font class="text14MediumBlue">&nbsp;${record.ajpPort}&nbsp;</font></td>
		                       <td class="tableCell" align="right" ><font class="text14MediumBlue" style="color:green">&nbsp;<b>${record.systemaHttpPort}&nbsp;</b></font></td>
		                       
		                       
				            </tr> 
				            </c:forEach>
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>
		    </c:if>
	 	     
	 	    <tr height="5"><td>&nbsp;</td></tr>
	 	    
	 		</table>
		</td>
	</tr>
 			
	 
    
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

