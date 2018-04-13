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
	<SCRIPT type="text/javascript" src="resources/js/sporringoppdrag_show_collireference.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
				<font class="tabLink">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.showOppdrag.collireference.tab"/></font>
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
				<td width="55%" valign="top">
					<table align="center" style="width:85%" cellspacing="1" cellpadding="0">
			 			<tr>
				 			<td colspan="10" class="text14">
				 				<img style="vertical-align: bottom" src="resources/images/appUserOg.gif" width="18" hight="18" border="0" alt="customer">
				 				<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.customerName"/></b>&nbsp;${searchFilter.knavn}
				 				&nbsp;&nbsp;/&nbsp;&nbsp;<b>Logged on:</b>&nbsp;${user.user}
				 			</td>
				    		</tr>
				    		<tr height="15"><td></td></tr>
				    		<tr>
				    			<td colspan="10" class="text12MediumBlue">
				 				&nbsp;<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.avdOpp"/></b>&nbsp;${searchFilter.heavd} / ${searchFilter.heopd}
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
				 				&nbsp;<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label"/></b>
				 			</td>
				    		</tr>
				    		<tr height="2"><td></td></tr>
       						<tr>
							<td>
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
					                    <td class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.colliId"/>&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.in"/>&nbsp;</td>
					                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.out"/>&nbsp;</td> 
					                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.length"/>&nbsp;</td>
					                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.width"/>&nbsp;</td>
					                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.height"/>&nbsp;</td> 
					                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.m3"/>&nbsp;</td>
					                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.lm"/>&nbsp;</td>
					                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.fvekt"/>&nbsp;</td> 
					                    <td align="right" class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.colliref.label.pic"/>&nbsp;</td> 
					               	</tr> 
					               	
						           	<c:forEach var="record" items="${model.container.dspcolli}" varStatus="counter">  
						           	<c:if test="${not empty record.koid}">  
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                       <tr class="tableRow" height="20" >
							                   </c:when>
							                   <c:otherwise>   
							                       <tr class="tableOddRow" height="20" >
							                   </c:otherwise>
							               </c:choose>
							               <td class="tableCellFirst">&nbsp;${record.koid}&nbsp;</td>
							               <td class="tableCell">
							               	<c:if test="${not empty record.inndt && fn:length(record.inndt)==8}">
							               		<fmt:parseDate type="date" value="${record.inndt}" var="innDate" pattern="yyyyMMdd" />
						    						&nbsp;<fmt:formatDate pattern="yyyy.MM.dd" value="${innDate}" />
						    						<c:if test="${not empty record.innti}">
							               			<c:choose>
								               			<c:when test="${fn:length(record.innti)==5}">
								               				<fmt:parseDate value="${record.innti}" var="innTime" pattern="hmmss" />
								               				&nbsp;&nbsp;<fmt:formatDate type="time" pattern="hh:mm" value="${innTime}" />
								               			</c:when>
								               			<c:otherwise>
								               				<fmt:parseDate value="${record.innti}" var="innTime" pattern="hhmmss" />
								               				&nbsp;&nbsp;<fmt:formatDate type="time" pattern="hh:mm" value="${innTime}" />
								               			</c:otherwise>
							               			</c:choose>
						    						</c:if>
							               	</c:if>
							               </td>
							               <td class="tableCell">
							               	<c:if test="${not empty record.utdt && fn:length(record.utdt)==8}">
							               		<fmt:parseDate value="${record.utdt}" var="utDate" pattern="yyyyMMdd" />
						    						&nbsp;<fmt:formatDate pattern="yyyy.MM.dd" value="${utDate}" />
						    						<c:if test="${not empty record.utti}">
							               			<c:choose>
								               			<c:when test="${fn:length(record.utti)==5}">
								               				<fmt:parseDate value="${record.utti}" var="utTime" pattern="hmmss" />
								               				&nbsp;&nbsp;<fmt:formatDate type="time" pattern="hh:mm" value="${utTime}" />
								               			</c:when>
								               			<c:otherwise>
								               				<fmt:parseDate value="${record.utti}" var="utTime" pattern="hhmmss" />
								               				&nbsp;&nbsp;<fmt:formatDate type="time" pattern="hh:mm" value="${utTime}" />
								               			</c:otherwise>
							               			</c:choose>
							               		</c:if>
							               	</c:if>
							               </td>
							               <td align="right" class="tableCell">&nbsp;${record.komlen}&nbsp;</td>
							               <td align="right" class="tableCell">&nbsp;${record.kombre}&nbsp;</td>
							               <td align="right" class="tableCell">&nbsp;${record.komhoy}&nbsp;</td>
							               <td align="right" class="tableCell">&nbsp;${record.komm3}&nbsp;</td>
							               <td align="right" class="tableCell">&nbsp;${record.komlm}&nbsp;</td>
							               <td align="right" class="tableCell">&nbsp;${record.fvekt}&nbsp;</td>
							               <td align="right" class="tableCell">&nbsp;${record.bilde1u}&nbsp;</td>
							               <%-- own variables for further processing down the line --%>
							               <c:set var="myCounter" value="${counter.count}" scope="request" />
							               <c:set var="mySumvkt" value="${record.sumvkt}" scope="request" />
							               <c:set var="mySumm3" value="${record.summ3}" scope="request" />
							               <c:set var="mySumlm" value="${record.sumlm}" scope="request" />
							               <c:set var="mySumfvekt" value="${record.sumfvekt}" scope="request" />
							            </tr> 
						            </c:if>
						            </c:forEach>
						            <c:if test="${myCounter>=1}">
							            <tr class="tableHeaderField" height="20" valign="left">
						                    <td class="tableHeaderFieldFirst">&nbsp;TOTALER&nbsp;</td> 
						                    <td align="right" class="tableHeaderField">&nbsp;&nbsp;</td>
						                    <td align="right" class="tableHeaderField">&nbsp;&nbsp;</td>
						                    <td align="right" class="tableHeaderField">&nbsp;&nbsp;</td> 
						                    <td align="right" class="tableHeaderField">&nbsp;&nbsp;</td> 
						                    <td align="right" class="tableHeaderField">&nbsp;&nbsp;</td> 
						                    <td align="right" class="tableHeaderField">&nbsp;${mySumm3}&nbsp;</td> 
						                    <td align="right" class="tableHeaderField">&nbsp;${mySumlm}&nbsp;</td> 
						                    <td align="right" class="tableHeaderField">&nbsp;${mySumfvekt}&nbsp;</td> 
						                    <td align="right" class="tableHeaderField">&nbsp;&nbsp;</td> 
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

