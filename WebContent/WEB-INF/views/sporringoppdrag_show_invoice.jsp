<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<c:choose>           
     <c:when test="${not empty cw}">
		<!-- ======================= header ===========================-->
		<jsp:include page="/WEB-INF/views/headerSporringOppdragChildWindow.jsp" />
		<!-- =====================end header ==========================-->
     </c:when>
     <c:otherwise>   
		<!-- ======================= header ===========================-->
		<jsp:include page="/WEB-INF/views/headerSporringOppdrag.jsp" />
		<!-- =====================end header ==========================-->
     </c:otherwise>
 </c:choose>

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/sporringoppdragglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/sporringoppdrag_show_invoice.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>

<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25">
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" id="mainList" href="sporringoppdrag_mainlist.do?action=doFind&fs=1">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.tab"/></font>
					<img valign="bottom" src="resources/images/list.gif" border="0" alt="general list">
				</a>
			</td>
			<%--
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="10%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" id="mainList" href="sporringoppdrag_mainlist_filter.do?action=doShow&fs=1">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.filter.tab"/></font>
					<img valign="bottom" src="resources/images/find.png" border="0" alt="general list filter">
				</a>
			</td>
			 --%> 
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a style="display:block;" id="showOppdrag" href="sporringoppdrag_show.do?action=doShow&knavn=${searchFilter.knavn}&heavd=${searchFilter.heavd}&heopd=${searchFilter.heopd}">
					<font class="tabDisabledLink">
						&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.showOppdrag.tab"/>
						&nbsp;[<font class="text12MediumBlue" style="font-style:italic;">${searchFilter.heopd}</font>]
					</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.showOppdrag.invoice.tab"/></font>
			</td>
			<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>
	<tr>
		<td>
			<table style="width:100%" class="tabThinBorderWhiteWithSideBorders" border="0" cellspacing="0" cellpadding="0">
			<tr height="40"><td></td></tr>
			<tr>
				<td width="99%" valign="top">
					<table align="center" style="width:85%" border="0" cellspacing="1" cellpadding="0">
			 			<tr>
				 			<td colspan="10" class="text14">
				 				<img style="vertical-align: bottom" src="resources/images/appUserOg.gif" width="18" hight="18" border="0" alt="customer">
				 				<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.invoice.label.customerName"/></b>&nbsp;${searchFilter.knavn}
				 				&nbsp;&nbsp;/&nbsp;&nbsp;<b>Logged on:</b>&nbsp;${user.user}
				 			</td>
				    		</tr>
				    		<tr height="15"><td></td></tr>
				    		<tr>
				    			<td colspan="10" class="text12MediumBlue">&nbsp;
				 				<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.invoice.label.avdOpp"/></b>&nbsp;${searchFilter.heavd} / ${searchFilter.heopd}
				 			</td>
			 			</tr>
			 			<tr>
				 			<td class="text12MediumBlue">&nbsp;
				 				<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.invoice.label.invoiceNr"/></b>&nbsp;${searchFilter.docnr}
				 			</td>
				 			<td align="right" class="text12MediumBlue">&nbsp;
				 				<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.invoice.label.invoiceDate"/></b>
				 				<c:if test="${not empty model.container.fakdt}">
			    						<%-- Convert the raw strings to a date primitive --%>
			    						<fmt:parseDate value="${model.container.fakdt}" var="dateInvoiceDate" pattern="yyyyMMdd" />
			    						&nbsp;<fmt:formatDate pattern="yyyy.MM.dd" value="${dateInvoiceDate}" />
			    					</c:if>
				 				
				 			</td>
				 			<td align="right" class="text12MediumBlue">&nbsp;
				 				<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.invoice.label.dueDate"/></b>
				 				<c:if test="${not empty model.container.fakdtff}">
			    						<%-- Convert the raw strings to a date primitive --%>
			    						<fmt:parseDate value="${model.container.fakdtff}" var="dateDueDate" pattern="yyyyMMdd" />
			    						&nbsp;<fmt:formatDate pattern="yyyy.MM.dd" value="${dateDueDate}" />
			    					</c:if>
				 			</td>
				    		</tr>
			 		</table>
				</td>
			</tr>	
			<tr height="10"><td></td></tr>
			<tr>
				<td width="99%" valign="top">
					<table align="center" style="width:85%" cellspacing="1" cellpadding="0">
			 			<tr>
				 			<td class="text12">
				 				<img style="vertical-align: bottom" src="resources/images/invoice.png" width="20" height="20" border="0" alt="show log">
				 				&nbsp;<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.invoice.label"/></b>
				 			</td>
				    		</tr>
				    		<tr height="2"><td></td></tr>
       						<tr>
							<td>
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
					                    <td class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.invoice.column.label.itemDescription"/>&nbsp;</td> 
					                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.invoice.column.label.net"/>&nbsp;</td>
					                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.invoice.column.label.vat"/>&nbsp;</td>
					                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.invoice.column.label.gross"/>&nbsp;</td> 
					               	</tr> 
					               	<c:forEach var="record" items="${model.container.invlindet}" varStatus="counter">    
						               <c:choose>           
						                   <c:when test="${counter.count%2==0}">
						                       <tr class="tableRow" height="20" >
						                   </c:when>
						                   <c:otherwise>   
						                       <tr class="tableOddRow" height="20" >
						                   </c:otherwise>
						               </c:choose>
						               <td class="tableCellFirst">&nbsp;${record.vtxt}</td>
						               <td align="right" class="tableCell">&nbsp;${record.beln}&nbsp;</td>
						               <td align="right" class="tableCell">&nbsp;${record.belm}&nbsp;</td>
						               <td align="right" class="tableCell">&nbsp;${record.belb}&nbsp;</td>
						               <%-- own variables for further processing down the line --%>
						               <c:set var="myCounter" value="${counter.count}" scope="request" />
						               <c:set var="myTotn" value="${record.totn}" scope="request" />
						               <c:set var="myTotm" value="${record.totm}" scope="request" />
						               <c:set var="myTotb" value="${record.totb}" scope="request" />
						               
						            </tr> 
						            </c:forEach>
						            <c:if test="${myCounter>=1}">
							            <tr class="tableHeaderField" height="20" valign="left">
						                    <td class="tableHeaderFieldFirst">&nbsp;TOTALER&nbsp;</td> 
						                    <td align="right" class="tableHeaderField">&nbsp;${myTotn}</td>
						                    <td align="right" class="tableHeaderField">&nbsp;${myTotm}</td>
						                    <td align="right" class="tableHeaderField">&nbsp;${myTotb}</td> 
						               	</tr>
								 		<tr height="25"><td></td></tr>
								 		<tr>
								 			<td colspan="10" class="text12">
								 			<img style="vertical-align: middle" src="resources/images/bulletGreen.png" width="8" hight="8" border="0" alt="show log">
								 			&nbsp;<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.external.trackAndTrace.links"/></b></td>
								    		</tr>
						               	<tr>
								 			<td colspan="10" valign="top" >
								 			 	<table align="left" style="width:65%" class="tableBorderWithRoundCorners" cellspacing="1" cellpadding="0">
										 		<tr height="5"><td></td></tr>
										 		<c:forEach var="record" items="${model.friesokeVeierList}" >
									 			<tr>
											    		<td class="text12">&nbsp;${record.kfsotx}</td>
											    		
											    		<td class="text12">
											    			<div class="inputTextForShowLabel" style="width:80%">
											    				<label>
											    					<c:choose>
													    			<c:when test="${not empty record.wssokurl}">
												    					<a href="${record.wssokurl}" target="_blank">${record.wssok}</a>
											    					</c:when>
											    					<c:otherwise>
																	${record.wssok}
											    					</c:otherwise>
											    					</c:choose>
																&nbsp;
											    				</label>
											    			</div>
											    		</td>
											    	</tr>
										 		</c:forEach>
										 		
											    	<tr height="5"><td ></td></tr>
											 	</table>	
							 				</td>
										</tr>
					               	</c:if>
					            </table>
							</td>
							</tr>
			 		</table>
				</td>
			</tr>
			<tr height="5"><td></td></tr>	
			</table>
         </td>
    </tr>
	<tr>
		<td>
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table id="wrapperTable" class="tabThinBorderWhite" width="100%" cellspacing="1">
			<tr height="25"><td></td></tr> 
			</table>
		</td>
	</tr>	
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

