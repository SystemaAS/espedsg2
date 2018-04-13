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
	<SCRIPT type="text/javascript" src="resources/js/sporringoppdrag_show.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
			<td width="20%" valign="bottom" class="tab" align="center" nowrap>
				<font class="tabLink">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.showOppdrag.tab"/>&nbsp;
					<font style="font-style:normal;font-weight:normal;">[</font>
			  		<font class="text12MediumBlue" style="font-style:italic;font-weight:normal;">${model.record.heopd}</font>
					<font style="font-style:normal;font-weight:normal;">]</font>
				</font>
			</td>
			<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>	
		</tr>
	</table>
	</td>
	</tr>
	
	<tr>
		<td>
			<table style="width:100%" class="tabThinBorderWhiteWithSideBorders" border="0" cellspacing="0" cellpadding="0">
			<tr height="15"><td></td></tr>
		    <%-- FORM HEADER --%>
			<tr>
				<td>
		       		<table style="width:85%;" align="center" class="formFrameHeader" cellspacing="0" cellpadding="0">
			 		<tr height="15">
			 			<td class="text12White">
							&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.showOppdrag.tab"/>&nbsp;<img valign="bottom" src="resources/images/textContent.gif" width="14" height="15" border="0" alt="edit">
		 				</td>
						</tr>
					</table>
	        		</td>
	        </tr>
	        <%-- FORM DETAIL --%>
		    <tr>
		    		<td>
	    			<table style="width:85%;" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
			 		<tr height="18"><td >&nbsp;</td></tr>
			 		<tr>
						<td width="99%" valign="top">
							<table class="dashboardFrameHeader" align="center" style="width:85%" cellspacing="1" cellpadding="0">
					 			<tr height="20">
						 			<td class="text14">
							 			<img style="vertical-align: bottom" src="resources/images/appUserOg.gif" width="18" hight="18" border="0" alt="customer">
							 			<spring:message code="systema.sporringoppdrag.mainlist.topic.header.customerName"/>
							 			<font class="text14White">&nbsp;${model.container.knavn}&nbsp;&nbsp;/&nbsp;&nbsp;</font>
							 			Logged on:<font class="text14White">&nbsp;${user.user}</font>
						 			</td>
						    		</tr>
					 		</table>
						</td>
					</tr>	
					<tr>
						<td width="99%" valign="top">
						<table align="center" style="width:85%" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="1" cellpadding="0">
						<tr>
							<td>
							<table align="center" style="width:98%" cellspacing="1" cellpadding="0"> 
					 			<tr height="10"><td >&nbsp;</td></tr>
				 				<tr>
					 				<td colspan="10">
					 				<table class="tableBorderWithRoundCorners">
					 				<tr>
								    		<td class="text14MediumBlue" title="henas"><spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.avsender"/></td>
								    		<td class="text12">
								    			<div>
								    				<label>
								    					<b>${model.record.henas}</b>&nbsp;
								    				</label>
								    			</div>
								    		</td>
								    		<td class="text12">
								    			<div>
								    				<label>
								    					&nbsp;<img style="vertical-align: bottom" src="resources/images/addressIcon.png" width="12" hight="12" border="0" alt="address">
						 							<font class="text12" style="color:#666666;">[${model.record.heads1}&nbsp;&nbsp;${model.record.heads2}&nbsp;&nbsp;${model.record.heads3}]</font>
								    				</label>
								    			</div>
								    		</td>
								    	</tr>
							    		<tr>
								    		<td class="text14MediumBlue" title="henak"><spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.mottaker"/></td>
								    		<td class="text12">
								    			<div>
								    				<label>
								    					<b>${model.record.henak}</b>&nbsp;
								    				</label>
								    			</div>
						    				</td>
								    		<td class="text12">
								    			<div>
								    				<label>
								    					&nbsp;<img style="vertical-align: bottom" src="resources/images/addressIcon.png" width="12" hight="12" border="0" alt="address">
						 							<font class="text12" style="color:#666666;">[${model.record.headk1}&nbsp;&nbsp;${model.record.headk2}&nbsp;&nbsp;${model.record.headk3}]</font>
								    				</label>
								    			</div>
						    				</td>
								    	</tr>
								    	</table>
								    	</td>
								</tr>							    	
							    <tr height="5"><td >&nbsp;</td></tr>
					 			<tr>
						 			<td class="text12" title="heavd/heopd/hesg">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.orderRef.orderNr"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
							    					&nbsp;<b>${model.record.heavd}</b> / <b>${model.record.heopd}</b> / ${model.record.hesg}
							    				</label>	
							    			</div>
						    			</td>
						 			<td class="text12" title="hedtop">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.orderRef.orderDate"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												<c:if test="${not empty model.record.hedtop}">
							    						<%-- Convert the raw strings to a date primitive --%>
							    						<c:catch var="invalid">
														    <fmt:parseDate value="${model.record.hedtop}" var="dateOrderDate" pattern="yyyyMMdd" />
														    <fmt:formatDate pattern="yyyy.MM.dd" value="${dateOrderDate}" />
														</c:catch>
														<c:if test="${not empty invalid}">
														    ${model.record.hedtop}
														</c:if>
							    					</c:if>
							    					&nbsp;
							    				</label>
							    			</div>
							    		</td>
							    		<td class="text12" title="hepro">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.orderRef.tripNr"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												&nbsp;${model.record.hepro}
							    				</label>
							    			</div>
							    		</td>
						    		</tr>
						    		<tr>
						    			<td class="text12" title="wsetd">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.etdEtaAta.etd"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
							    					<c:if test="${not empty model.record.wsetd}">
							    						<c:catch var="invalid">
														    <fmt:parseDate value="${model.record.wsetd}" var="dateEtdDate" pattern="yyyyMMdd" />
							    							<fmt:formatDate pattern="yyyy.MM.dd" value="${dateEtdDate}" />
														</c:catch>
														<c:if test="${not empty invalid}">
														    ${model.record.wsetd}
														</c:if>
							    						
							    					</c:if>
							    					&nbsp;
							    				</label>	
							    			</div>
						    			</td>
							    		<td class="text12" title="wseta">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.etdEtaAta.eta"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
							    					<c:if test="${not empty model.record.wseta}">
							    						<c:catch var="invalid">
														    <fmt:parseDate value="${model.record.wseta}" var="dateEtaDate" pattern="yyyyMMdd" />
							    							<fmt:formatDate pattern="yyyy.MM.dd" value="${dateEtaDate}" />
														</c:catch>
														<c:if test="${not empty invalid}">
														    ${model.record.wseta}
														</c:if>
							    						
							    					</c:if>
							    					&nbsp;
							    				</label>
							    			</div>
							    		</td>
							    		<td class="text12" title="wsata">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.etdEtaAta.ata"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
							    					<c:if test="${not empty model.record.wsata}">
							    						<c:catch var="invalid">
														    <fmt:parseDate value="${model.record.wsata}" var="dateAtaDate" pattern="yyyyMMdd" />
							    							<fmt:formatDate pattern="yyyy.MM.dd" value="${dateAtaDate}" />
														</c:catch>
														<c:if test="${not empty invalid}">
														    ${model.record.wsata}
														</c:if>
							    						
							    					</c:if>
							    					&nbsp;
							    				</label>
							    			</div>
							    		</td>
						    		</tr>
						    		<tr height="5"><td >&nbsp;</td></tr>
						    		<tr>
						    			<td class="text12" title="hegn or herfa">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.awbNr.awbNr"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
							    					<c:choose>
							    					<c:when test="${not empty model.record.heur && model.record.heur=='C'}">
							    						<c:choose>
									    					<c:when test="${not empty model.record.tracking}">
									    						<a href="${model.record.tracking}" target="_blank">${model.record.herfa}</a>
									    					</c:when>
									    					<c:otherwise>${model.record.herfa}</c:otherwise>
								    					</c:choose>	
						    						</c:when>
						    						<c:otherwise>
							    						<c:choose>
									    					<c:when test="${not empty model.record.tracking && model.record.fitdvi != 'J'}">
									    						<a href="${model.record.tracking}" target="_blank">${model.record.hegn}</a>
									    					</c:when>
									    					<c:otherwise>${model.record.hegn}</c:otherwise>
								    					</c:choose>
						    						</c:otherwise>
						    						</c:choose>
						    						&nbsp;
							    				</label>	
							    			</div>
						    			</td>
							    		<td class="text12" title="hegn or herfa">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.awbNr.godsNr"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
							    					<c:choose>
							    					<c:when test="${not empty model.record.heur && model.record.heur=='C'}">
							    						<c:choose>
									    					<c:when test="${not empty model.record.tracking}">
									    						<a href="${model.record.tracking}" target="_blank">${model.record.hegn}</a>
									    					</c:when>
									    					<c:otherwise >${model.record.hegn}</c:otherwise>
								    					</c:choose>	
						    						</c:when>
						    						<c:otherwise>
							    						<c:choose>
									    					<c:when test="${not empty model.record.tracking && model.record.fitdvi != 'J'}">
									    						<a href="${model.record.tracking}" target="_blank">${model.record.herfa}</a>
									    					</c:when>
									    					<c:otherwise >${model.record.herfa}</c:otherwise>
								    					</c:choose>
						    						</c:otherwise>
						    						</c:choose>
							    					&nbsp;
							    				</label>
							    			</div>
							    		</td>
							    		<td class="text12" title="hehawb">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.awbNr.hawb"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												${model.record.hehawb}
												&nbsp;
							    				</label>
							    			</div>
							    		</td>
							    	</tr>
							    	<tr>	
							    		<td class="text12" title="hetrcn">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.awbNr.container"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												${model.record.hetrcn}
												&nbsp;
							    				</label>
							    			</div>
							    		</td>
						    		</tr>	
						    		<tr height="5"><td >&nbsp;</td></tr>
						    		<tr>
						    			<td class="text12" title="hesdff-hesdf">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.fraTilSted.from"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
						    						${model.record.hesdff}-${model.record.hesdf}
						    						&nbsp;
							    				</label>	
							    			</div>
						    			</td>
							    		<td class="text12" title="hesdt-hesdvt">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.fraTilSted.to"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												${model.record.hesdt}-${model.record.hesdvt}
												&nbsp;
							    				</label>
							    			</div>
							    		</td>
						    		</tr>
						    		
						    		<tr>
						    			<td class="text12" title="hent">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.antalVekt.antall"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												${model.record.hent}&nbsp;
							    				</label>
							    			</div>
							    		</td>
							    		<td class="text12" title="hevs1">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.antalVekt.godsbesk"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												${model.record.hevs1}&nbsp;
							    				</label>
							    			</div>
							    		</td>
						    		</tr>
						    		<tr>
						    			<td class="text12" title="hevkt">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.antalVekt.vekt"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												${model.record.hevkt}&nbsp;kg
							    				</label>
							    			</div>
							    		</td>
							    		<td class="text12" title="hefbv">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.antalVekt.frVekt"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												${model.record.hefbv}&nbsp;kg
							    				</label>
							    			</div>
							    		</td>
							    	</tr>
							    	<tr>	
							    		<td class="text12" title="hem3">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.antalVekt.m3"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												${model.record.hem3}&nbsp;
							    				</label>
							    			</div>
							    		</td>
							    		<td class="text12" title="helm">&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.antalVekt.lm"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												${model.record.helm}&nbsp;
							    				</label>
							    			</div>
							    		</td>
							    		
						    		</tr>
						    		<tr>
							    		<td class="text12" title="clis">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.antalVekt.colliId"/></td>
							    		<td class="text12">
							    			<div class="inputTextForShowLabel">
							    				<label>
												<c:if test="${not empty model.record.clis}">
						    							<a href="sporringoppdrag_show_collireference.do?action=doShow&user=${user.user}&knavn=${model.container.knavn}&heavd=${model.record.heavd}&heopd=${model.record.heopd}">${model.record.clis}</a>
						    						</c:if>
						    						&nbsp;
							    				</label>
							    			</div>
							    		</td>
						    		</tr>
						    		<tr height="10"><td >&nbsp;</td></tr>
					    		</table>
					    		</td>
					    		</tr>
				 		</table>
						</td>
					</tr>	
					<tr height="5"><td >&nbsp;</td></tr>
										
			 		<tr>
			 			<td width="99%" valign="top" >
			 			 	<table align="center" style="width:85%" class="tableBorderWithRoundCorners" cellspacing="1" cellpadding="0">
					 		<tr height="5"><td></td></tr>
						    	<tr>
						    		<td class="text12" title="hegm1/hegm2">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.godsmerking"/></td>
						    		<td class="text12" width="120px">&nbsp;</td>
						    		<td align="center" class="text12">
						    			<div class="inputTextForShowLabel" style="width:80%">
						    				<div align="left">
						    					&nbsp;${model.record.hegm1}
						    					<c:if test="${not empty model.record.hegm2}">
						    						,&nbsp;${model.record.hegm2}
						    					</c:if>
						    				</div>
						    			</div>
						    		</td>
						    	</tr>
						    	<tr>
						    		<td class="text12" title="hesgm/hedtmo">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.kvitMottat"/></td>
						    		<td class="text12" width="120px">&nbsp;</td>
						    		<td align="center" class="text12">
						    			<div class="inputTextForShowLabel" style="width:80%">
						    				<div align="left">
						    					&nbsp;${model.record.hesgm}
						    					<c:if test="${not empty model.record.hedtmo}">
						    						,&nbsp;${model.record.hedtmo}
						    					</c:if>
						    				</div>
						    			</div>
						    		</td>
						    	</tr>
						    	<tr>
						    		<td class="text12" title="doclnk">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.doc"/></td>
						    		<td class="text12" width="120px">&nbsp;</td>
						    		<td align="center" class="text12">
						    			<div class="inputTextForShowLabel" style="width:80%">
						    				<div align="left">
						    					<c:forEach var="record" items="${model.docList}" >
                             					<a href="sporringoppdrag_renderArchive.do?fp=${record.doclnk}" target="_blank">
                             						${record.doctxt}&nbsp;<img src="resources/images/pdf.png" align="bottom" border="0" width="12px" height="15px" alt="Visa arkivdokument" >
                             					</a>&nbsp;&nbsp;
											</c:forEach>
											&nbsp;
						    				</div>
						    			</div>
						    		</td>
						    	</tr>
						    	<tr>
						    		<td class="text12" title="frttxt">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.fritekst"/></td>
						    		<td class="text12" width="120px">&nbsp;</td>
						    		<td align="center" class="text12">
						    			<div class="inputTextForShowLabel" style="width:80%;">
						    				<div align="left">
						    					<c:forEach var="record" items="${model.freeTextList}" >
                             					${record.frttxt}
											</c:forEach>
											&nbsp;
						    				</div>
						    			</div>
						    		</td>
						    	</tr>
						    	<tr>
						    		<td class="text12" title="fafakt">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.fakturaNr"/></td>
						    		<td class="text12" width="120px">&nbsp;</td>
						    		<td align="center" class="text12">
						    			<div class="inputTextForShowLabel" style="width:80%">
						    				<div align="left">
						    					<c:forEach var="record" items="${model.invoiceList}" >
                             					<a href="sporringoppdrag_show_invoice.do?action=doShow&knavn=${model.container.knavn}&heavd=${model.record.heavd}&heopd=${model.record.heopd}&docnr=${record.fafakt}">${record.fafakt}</a>&nbsp;
											</c:forEach>
											&nbsp;
						    				</div>
						    			</div>
						    		</td>
						    	</tr>
						    	<tr height="5"><td ></td></tr>
						 	</table>	
		 				</td>
					</tr>
					<tr height="2"><td></td></tr>	
					<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td></tr>
					<tr>
						<td width="99%" valign="top">
							<table align="center" style="width:85%" cellspacing="1" cellpadding="0">
					 			<tr>
						 			<td colspan="2" class="text12">
						 			<img style="vertical-align: middle" src="resources/images/bulletGreen.png" width="8" hight="8" border="0" alt="show log">
						 			&nbsp;<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.label.external.trackAndTrace.links"/></b></td>
						    		</tr>
					 		</table>
						</td>
					</tr>
					<tr>
			 			<td width="99%" valign="top" >
			 			 	<table align="center" style="width:85%" class="tableBorderWithRoundCorners" cellspacing="1" cellpadding="0">
					 		<tr height="5"><td></td></tr>
					 		<c:forEach var="record" items="${model.friesokeVeierList}" >
				 			<tr>
						    		<td class="text12">&nbsp;${record.kfsotx}</td>
						    		<td class="text12">
						    			<div class="inputTextForShowLabel" style="width:80%">
						    				<div>
						    					<c:choose>
								    			<c:when test="${not empty record.wssokurl}">
							    					<a href="${record.wssokurl}" target="_blank">${record.wssok}</a>
						    					</c:when>
						    					<c:otherwise>
												${record.wssok}
						    					</c:otherwise>
						    					</c:choose>
											&nbsp;
						    				</div>
						    			</div>
						    		</td>
						    	</tr>
					 		</c:forEach>
					 		
						    	<tr height="5"><td ></td></tr>
						 	</table>	
		 				</td>
					</tr>
					<tr height="10"><td></td></tr>	
					<tr>
						<td width="99%" valign="top">
							<table align="center" style="width:85%" cellspacing="1" cellpadding="0">
					 			<tr>
						 			<td class="text12">
						 				<img style="vertical-align: bottom" src="resources/images/log-iconLOG.png" width="21" hight="23" border="0" alt="show log">
						 				&nbsp;<b><spring:message code="systema.sporringoppdrag.mainlist.topic.header.hendelseslogg"/></b>
						 			</td>
						    		</tr>
						    		<tr height="2"><td></td></tr>
	        						<tr>
									<td>
										<table width="100%" cellspacing="0" border="0" cellpadding="0">
											<tr class="tableHeaderField" height="20" valign="left">
							                    <td class="tableHeaderFieldFirst">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.hendelseslogg.column.label.date"/>&nbsp;</td> 
							                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.hendelseslogg.column.label.time"/>&nbsp;</td>
							                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.hendelseslogg.column.label.type"/>&nbsp;</td>
							                    <td class="tableHeaderField">&nbsp;<spring:message code="systema.sporringoppdrag.mainlist.topic.header.hendelseslogg.column.label.signatur"/>&nbsp;</td> 
							                    <td class="tableHeaderField">&nbsp;&nbsp;</td> 
							               </tr> 
							              
							               	<c:forEach var="record" items="${model.hendelseslogList}" varStatus="counter">    
								               <c:choose>           
								                   <c:when test="${counter.count%2==0}">
								                       <tr class="tableRow" height="20" >
								                   </c:when>
								                   <c:otherwise>   
								                       <tr class="tableOddRow" height="20" >
								                   </c:otherwise>
								               </c:choose>
								               <td class="tableCellFirst">&nbsp;
								               		<c:if test="${not empty record.wttd}">
								               			<c:catch var="invalid">
														    <fmt:parseDate value="${record.wttd}" var="dateHendelseslogDate" pattern="yyyyMMdd" />
						    				       	  		<fmt:formatDate pattern="yyyy.MM.dd" value="${dateHendelseslogDate}" />
														</c:catch>
														<c:if test="${not empty invalid}">
														    ${record.wttd}
														</c:if>
								               		
									                  	
					    				       	  	</c:if>
								               </td>
								               <td class="tableCell">&nbsp;
								               		<c:if test="${not empty record.wttt}">
								               			<c:catch var="invalid">
														    <fmt:parseDate type="time" value="${record.wttt}" var="timeHendelseslogDate" pattern="HHmmss" />
									               			<fmt:formatDate pattern="HH:mm:ss" value="${timeHendelseslogDate}" />
														</c:catch>
														<c:if test="${not empty invalid}">
														    ${record.wttt}
														</c:if>
									                  	
								               		</c:if>
								               </td>
								               <td class="tableCell">&nbsp;${record.wttx}&nbsp;</td>
								               <td class="tableCell" >&nbsp;
								               		<c:if test="${not empty record.wttg}">
								               			<img style="vertical-align: bottom" src="${record.wttg}" border="0" alt="show sign">
								               		</c:if>
								               </td>
								               <td align="center" class="tableCell" >&nbsp;
								               		<c:if test="${not empty record.wttg2}">
								               			<a href="${record.wttg2}" target="_blank">
								               				<img style="vertical-align: bottom" src="${record.wttg2}" Hspace="5" Vspace="5" width="128px" height="80px" border="0" alt="show image">
								               			</a>
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
					<tr height="5"><td></td></tr>	
					
					</table>
	       		</td>
	          </tr>
	          <tr height="10"><td></td></tr>
	         </table>
         </td>
    </tr>
    
    
	<%-- Validation errors --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
	<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" style="width:99%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td class="textError">					
	            <ul>
	            <c:forEach var="error" items="${errors.allErrors}">
	                <li >
	                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
	                </li>
	            </c:forEach>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
	</tr>
	</spring:hasBindErrors>	
	<tr>
		<td>
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table id="wrapperTable" class="tabThinBorderWhite" width="100%" cellspacing="1">
			<tr height="15"><td></td></tr> 
			</table>
		</td>
	</tr>
		
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

