<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ================== special login header ==================-->
<jsp:include page="/WEB-INF/views/headerDashboard.jsp" />
<!-- =====================end header ==========================-->

		<%-- Applications' menu --%>
		<tr height="400" >
			<td height="300" width="500" align="center" valign="top" > 
    			 <table width="96%" class="dashboardFrameMain" border="0" cellspacing="0" cellpadding="0">
    			 		<tr class="text" height="1"><td></td></tr>
    			 		<tr>
			 			<td class="text12" align="center" >		
			 				<table width="96%" align="center" class="dashboardFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="20">
						 			<td class="text14White">
						 				<b>&nbsp;Modul<a href="asyjservices_mainlist.do" ><font class="text14White">e</font></a>r - espedSg&nbsp;</b>
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		<tr >
			    		<td class="text12" align="center" >
			    			
			    			<table width="96%" align="center" class="dashboardFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr >
						 			<%--<td style="border-left: 2px solid #3F9E4D; padding: 5px;" align="left" height="60px" class="text14"> --%>
						 			<td align="left" height="60px" class="text14">
						 			<ol>
						 			
						 			<c:if test="${user.user == 'OSCAR'}">
						 				
										<li style="line-height:20px;">
						 				<font class="text14">
							 				<a class="text14" href="aespedsg_roadmap.do" > 	
			 									<img src="resources/images/bulletGreen.png" width="10px" height="10px" border="0">&nbsp;
			 									<font class="text14NavyBlue">eSpedsg Roadmap</font>
	 										</a>
										</font>
										</li>
										
						 			</c:if>
			 						<c:forEach items="${list}" var="record" varStatus="counter"> 
						 				<c:if test="${ fn:contains(record.prog, 'TOMCAT') }">
						 					<c:set var="imgSrcTomcat" scope="session" value="resources/images/bulletGreen.png"/>
						 					<c:set var="imgSrcTomcatRed" scope="session" value="resources/images/bulletRed.png"/>
						 					
											<li style="line-height:20px;">
					 						<font class="text14">
					 						
					 						<c:if test="${fn:contains(record.prog,'-RAPPORTER') }">
					 							<font class="text14">
								 					<form onMouseOver="showPop('infoRAPP');" onMouseOut="hidePop('infoRAPP');" onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgstats/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
													</form>
												</font>
												<%--
							 					<font class="text14">
							 						<a class="text14" href="report_dashboard.do?report=report_fortolling_no" onMouseOver="showPop('infoRAPP');" onMouseOut="hidePop('infoRAPP');" onClick="setBlockUI(this);" > 	
					 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
					 									<font class="text14NavyBlue">${record.prTxt}</font>
			 										</a>
			 									</font>
			 									 --%>	
		 										<%-- ====================================================  --%>
								            	<%-- Here we have the info popup window                    --%>
								            	<%-- ====================================================  --%>
								            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoRAPP" class="popupPlain"  >
								           		<div align="center">
								           			<table>
								           				<tr>
															<td align="left" class="text12" ><b>${record.prTxt}</b> 
															</td>
														</tr>
														<tr class="text" height="10"><td></td></tr>
														<tr>
															<td align="center" >
																<c:choose>
						 											<c:when test="${not empty record.infoImg}">
																		<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																	</c:when>
																	<c:otherwise>
																		<%--
																		<img src="resources/images/miniTodo.png" border="0" width="350px"; height="210px">
																		 --%>
																	</c:otherwise>
																</c:choose>	
															</td>
														</tr>
									           		</table>
												</div>
												</span>
											</c:if>
 
					 						<c:if test="${fn:contains(record.prog,'-TRAFIKKREGNSKAP') }">
					 							<font class="text14">
								 					<form onMouseOver="showPop('infoRAPP_TR');" onMouseOut="hidePop('infoRAPP_TR');" onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgstats/logonDashboard.do?trafikk=1" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
													</form>
												</font>
												
												<%--
							 					<font class="text14">
							 						<a class="text14" href="report_dashboard.do?report=report_trafikkregnskap_overview" onMouseOver="showPop('infoRAPP_TR');" onMouseOut="hidePop('infoRAPP_TR');" onClick="setBlockUI(this);" > 	
					 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
					 									<font class="text14NavyBlue">${record.prTxt}</font>
			 										</a>
			 									</font>
			 									 --%>	
		 										<%-- ====================================================  --%>
								            	<%-- Here we have the info popup window                    --%>
								            	<%-- ====================================================  --%>
								            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoRAPP_TR" class="popupPlain"  >
								           		<div align="center">
								           			<table>
								           				<tr>
															<td align="left" class="text12" ><b>${record.prTxt}</b> 
															</td>
														</tr>
														<tr class="text" height="10"><td></td></tr>
														<tr>
															<td align="center" >
																<c:choose>
						 											<c:when test="${not empty record.infoImg}">
																		<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																	</c:when>
																	<c:otherwise>
																		<%--
																		<img src="resources/images/miniTodo.png" border="0" width="350px"; height="210px">
																		 --%>
																	</c:otherwise>
																</c:choose>	
															</td>
														</tr>
									           		</table>
												</div>
												</span>
												
											</c:if>

					 						<c:if test="${fn:contains(record.prog,'-WRKTRIPS') }">
					 							<c:choose>
						 							<c:when test="${not empty user.userAS400}">
									 					<font class="text14">
									 						<%-- uncomment this line IF more menu choices appear...
											 					<a class="text14" href="transportdispgate.do" >
											 				 --%>
											 				<a class="text14" href="transportdisp_mainorderlist.do?lang=${user.usrLang}&action=doFind" onMouseOver="showPop('infoWRKTRIPS');" onMouseOut="hidePop('infoWRKTRIPS');" onClick="setBlockUI(this);" > 	
							 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
							 									<font class="text14NavyBlue">${record.prTxt}</font>
					 										</a>
					 										<%-- ===========================================  --%>
											            	<%-- Here we have the info popup window wrkTrips --%>
											            	<%-- ===========================================  --%>
											            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoWRKTRIPS" class="popupPlain"  >
											           		<div align="center">
											           			<table>
											           				<tr>
																		<td align="left" class="text12" ><b>Work with trips / Lastetorg</b> 
																		</td>
																	</tr>
																	<tr class="text" height="10"><td></td></tr>
																	<tr>
																		<td align="center" >
																			<c:choose>
								 											<c:when test="${not empty record.infoImg}">
																				<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																			</c:when>
																			<c:otherwise>
																				<img src="resources/images/miniLastetorg.png" border="0" width="350px"; height="210px">
																			</c:otherwise>
																			</c:choose>
																			
																		</td>
																	</tr>
												           		</table>
															</div>
															</span>
														</font>
													</c:when>
													<c:otherwise>
														<%-- userAS400 = ASUSR parameter i AS400 is mandatory in order to use -WRKTRIPS --%>
														<font class="text14SlateGray" onMouseOver="showPop('wrktrips_info');" onMouseOut="hidePop('wrktrips_info');">
									 						<img src="${imgSrcTomcatRed}" width="10px" height="10px" border="0">&nbsp;
						 									${record.prTxt}
						 								</font>
						 								<div class="text11" style="position: relative;" align="left" >
														<span style="position:absolute;top:1px;" id="wrktrips_info" class="popupWithInputText text12"  >		
											           		Din <b>esped User er IKKE koblet</b> mot en Server-userId (<b>ASUSR</b>-parameter). Kontakt systemansvarlig
														</span>		
									 					</div>
													</c:otherwise>
												</c:choose>
											</c:if>
											<c:if test="${fn:contains(record.prog,'-EBOOKING') }">
												<font class="text14">
								 					<form onMouseOver="showPop('infoEBOOKING');" onMouseOut="hidePop('infoEBOOKING');" onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgebook/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
													</form>
												</font>
												<c:if test="${not empty record.veiledning}">
		 											<a class="text14" href="${record.veiledning}" target="_blank">	
		 												<img title="Brukerveiledning" style="vertical-align:middle;" src="resources/images/pdf2.png" border="0" width="14px"; height="14px">
		 											</a>
		 										</c:if>
				 									<%--				
								 					<font class="text14">
								 						<a class="text14" href="ebooking_mainorderlist.do?lang=${user.usrLang}&action=doFind" onMouseOver="showPop('infoEBOOKING');" onMouseOut="hidePop('infoEBOOKING');" onClick="setBlockUI(this);" > 	
						 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
						 									<font class="text14NavyBlue">${record.prTxt}</font>
				 										</a>
				 									</font>	
				 									 --%>	
			 										<%-- ===========================================  --%>
									            	<%-- Here we have the info popup window eBooking --%>
									            	<%-- ===========================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoEBOOKING" class="popupPlain"  >
									           		<div align="center">
									           			<table>
									           				<tr>
																<td align="left" class="text12" ><b>eBooking</b></td>
															</tr>
															<tr class="text" height="10"><td></td></tr>
															<tr>
																<td align="center" >
																	<c:choose>
							 											<c:when test="${not empty record.infoImg}">
																			<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																		</c:when>
																		<c:otherwise>
																			<img src="resources/images/miniEbooking.png" border="0" width="350px"; height="210px">
																		</c:otherwise>
																	</c:choose>	
																</td>
															</tr>
										           		</table>
													</div>
													</span>
												
											</c:if>
											<c:if test="${fn:contains(record.prog,'-TROR') }">
							 						<font class="text14">
									 					<form onMouseOver="showPop('infoTROR');" onMouseOut="hidePop('infoTROR');" onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgtror/logonDashboard.do" >
														    <input type="hidden" name="user" value="${user.user}" />
														    <input type="hidden" name="password" value="${user.encryptedPassword}" />
														    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
														    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
														</form>
													</font>
							 						<%--
								 					<font class="text14">
								 						<a class="text14" href="tror_mainorderlist.do?lang=${user.usrLang}&action=doFind&sign=${user.signatur}<c:if test="${not empty user.asavd}">&avd=${user.asavd}</c:if>" onMouseOver="showPop('infoTROR');" onMouseOut="hidePop('infoTROR');" onClick="setBlockUI(this);" > 	
						 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
						 									<font class="text14NavyBlue">${record.prTxt}</font>
				 										</a>
				 									</font>
			 										 --%>
			 										<%-- ====================================================  --%>
									            	<%-- Here we have the info popup window TROR - Oppdragsreg --%>
									            	<%-- ====================================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoTROR" class="popupPlain"  >
									           		<div align="center">
									           			<table>
									           				<tr>
																<td align="left" class="text12" ><b>${record.prTxt}</b> 
																</td>
															</tr>
															<tr class="text" height="10"><td></td></tr>
															<tr>
																<td align="center" >
																	<c:choose>
							 											<c:when test="${not empty record.infoImg}">
																			<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																		</c:when>
																		<c:otherwise>
																			<img src="resources/images/miniEbooking.png" border="0" width="350px"; height="210px">
																		</c:otherwise>
																	</c:choose>	
																</td>
															</tr>
										           		</table>
													</div>
													</span>
											</c:if>
											
											<c:if test="${fn:contains(record.prog,'-TESTSUITES') }">
							 					<font class="text14">
							 						<a class="text14" href="aespedsgtestersuite.do?lang=${user.usrLang}" onMouseOver="showPop('infoTESTSUITES');" onMouseOut="hidePop('infoTESTSUITES');" onClick="setBlockUI(this);" > 	
					 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
					 									<font class="text14NavyBlue">${record.prTxt}</font>
			 										</a>
			 										<%-- ====================================================  --%>
									            	<%-- Here we have the info popup window TROR - Oppdragsreg --%>
									            	<%-- ====================================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoTESTSUITES" class="popupPlain"  >
									           		<div align="center">
									           			<table>
									           				<tr>
																<td align="left" class="text12" ><b>${record.prTxt}</b> 
																</td>
															</tr>
															<tr class="text" height="10"><td></td></tr>
															<tr>
																<td align="center" >
																	<c:choose>
							 											<c:when test="${not empty record.infoImg}">
																			<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																		</c:when>
																		<c:otherwise>
																			<img src="resources/images/miniTestSuites.png" border="0" width="350px"; height="210px">
																		</c:otherwise>
																	</c:choose>	
																</td>
															</tr>
										           		</table>
													</div>
													</span>
												</font>
											</c:if>
											
											<c:if test="${fn:contains(record.prog,'-eFaktura') }">
												<font class="text14">
								 					<form onMouseOver="showPop('infoEFAKTURA');" onMouseOut="hidePop('infoEFAKTURA');" onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgefaktura/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
													</form>
												</font>
							 					
		 										<%-- ===========================================  --%>
								            	<%-- Here we have the info popup window eFaktura --%>
								            	<%-- ===========================================  --%>
								            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoEFAKTURA" class="popupPlain"  >
								           		<div align="center">
								           			<table>
								           				<tr>
															<td align="left" class="text12" ><b>eFaktura</b> 
															</td>
														</tr>
														<tr class="text" height="10"><td></td></tr>
														<tr>
															<td align="center" >
																<c:choose>
						 											<c:when test="${not empty record.infoImg}">
																		<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																	</c:when>
																	<c:otherwise>
																		<img src="resources/images/miniEfaktura.png" border="0" width="350px"; height="210px">
																	</c:otherwise>
																</c:choose>	
															</td>
														</tr>
									           		</table>
												</div>
												</span>
			 									
											</c:if>
						 					<c:if test="${fn:contains(record.prog,'-SPORROPP') }">
							 					<font class="text14">
									 				<a class="text14" href="sporringoppdraggate.do?lang=${user.usrLang}" onMouseOver="showPop('infoSPORROPPD');" onMouseOut="hidePop('infoSPORROPPD');" onClick="setBlockUI(this);" >
					 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
					 									<font class="text14NavyBlue">${record.prTxt}</font>
			 										</a>
			 										
			 										<%-- ==============================================  --%>
									            	<%-- Here we have the info popup window SporringOpp. --%>
									            	<%-- ==============================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoSPORROPPD" class="popupPlain"  >
									           		<div align="center">
									           			<table>
									           				<tr>
																<td align="left" class="text12" ><b>Spørring på Oppdrag</b> 
																</td>
															</tr>
															<tr class="text" height="10"><td></td></tr>
															<tr>
																<td align="center" >
																	<c:choose>
							 											<c:when test="${not empty record.infoImg}">
																			<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																		</c:when>
																		<c:otherwise>
																			<img src="resources/images/miniSporringOppdrag.png" border="0" width="350px"; height="210px">
																		</c:otherwise>
																	</c:choose>
																</td>
															</tr>
										           		</table>
													</div>
													</span>
												</font>
											</c:if>
						 					<c:if test="${fn:contains(record.prog,'-PRISKALK') }">
						 						<font class="text14">
						 							<form onMouseOver="showPop('infoPRISKALK');" onMouseOut="hidePop('infoPRISKALK');" onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgpkalk/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
													</form>
												</font>
					 							<%--	
								 				<a class="text14" href="fraktkalkulatorgate.do?lang=${user.usrLang}" onMouseOver="showPop('infoPRISKALK');" onMouseOut="hidePop('infoPRISKALK');" onClick="setBlockUI(this);" >
				 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													<font class="text14NavyBlue">${record.prTxt}</font>
		 										</a>
		 										 --%>
		 										<%-- ===========================================  --%>
								            	<%-- Here we have the info popup window Priskalk. --%>
								            	<%-- ===========================================  --%>
								            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoPRISKALK" class="popupPlain" >
								           		<div align="center">
								           			<table>
								           				<tr>
															<td align="left" class="text12" ><b>Priskalkulator</b> 
															</td>
														</tr>
														<tr class="text" height="10"><td></td></tr>
														<tr>
															<td align="center" >
																<c:choose>
						 											<c:when test="${not empty record.infoImg}">
																		<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																	</c:when>
																	<c:otherwise>
																		<img src="resources/images/miniPriskalkulator.png" border="0" width="350px"; height="210px">
																	</c:otherwise>
																</c:choose>
															</td>
														</tr>
									           		</table>
												</div>
												</span>
						 					</c:if>
						 					<c:if test="${fn:contains(record.prog,'-VEDLIKEHOLD') }">
							 					<font class="text14">
								 				<a class="text14" href="mainmaintenancegate.do?lang=${user.usrLang}" onMouseOver="showPop('infoVEDLIKEHOLD');" onMouseOut="hidePop('infoVEDLIKEHOLD');" onClick="setBlockUI(this);" > 	
				 									<img src="resources/images/bulletGreen.png" width="10px" height="10px" border="0">&nbsp;
				 									<font class="text14NavyBlue">${record.prTxt}</font>
		 										</a>
		 											<%-- ===========================================  --%>
									            	<%-- Here we have the info popup window Vedlik. --%>
									            	<%-- ===========================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoVEDLIKEHOLD" class="popupPlain"  >
									           		<div align="center">
									           			<table>
									           				<tr>
																<td align="left" class="text12" ><b>Vedlikehold Firmanivå</b> 
																</td>
															</tr>
															<tr class="text" height="10"><td></td></tr>
															<tr>
																<td align="center" >
																	<c:choose>
							 											<c:when test="${not empty record.infoImg}">
																			<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																		</c:when>
																		<c:otherwise>
																			<img src="resources/images/miniVedlikehold.png" border="0" width="350px"; height="210px">
																		</c:otherwise>
																	</c:choose>
																	
																</td>
															</tr>
										           		</table>
													</div>
													</span>
		 										
												</font>
											</c:if>
											<%-- ONLY for external customers --%>
								 			<c:if test="${fn:contains(record.prog,'-TAVGG') }">
								 				<font class="text14">
								 					<form onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgtvinnavgg/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
													</form>
												</font>
												<%--
								 				<font class="text14">
									 				<a class="text14" href="tvinnsadadmin_avggrunnlag_external.do?lang=${user.usrLang}" > 	
					 									<img src="resources/images/bulletGreen.png" width="10px" height="10px" border="0">&nbsp;
					 									<font class="text14NavyBlue">${record.prTxt}</font>
			 										</a>
												</font>
												 --%>
								 			</c:if>
								 			<c:if test="${fn:contains(record.prog,'-TBRREG') }">
				 								<a id="dialogRunKundedatakontrollLink" class="text14" style="display:block;" runat="server" href="#" onMouseOver="showPop('infoTBRREG');" onMouseOut="hidePop('infoTBRREG');" >
				 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
	 												<font class="text14NavyBlue">${record.prTxt}</font>
		 										</a>
				 								<%-- ===========================================  --%>
									            	<%-- Here we have the info popup window TBRREG --%>
									            	<%-- ===========================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoTBRREG" class="popupPlain"  >
									           		<div align="center">
									           			<table border = "0">
															<tr>
																<td align="left" class="text10"><i>Inneholder data under Norsk lisens for offentlige data (NLOD) tilgjengeliggjort av:</i><br/>
																	<img src="http://scf.brreg.no/bilder/brreg_logo.svg" width="150px" height="25px" align="left">
																</td>
															</tr>
									           				<tr>
																<td align="left" class="text12" >
																	<br/><b>Kundedata kontroll</b>, validerer alle kunder mot åpne data fra Enhetsregisteret.<br/>
																	Resultatet er en liste over kunder, hvor:
																	<br/>- Organisasjonsnummer ikke finnes
																	<br/>- Er i konkurs
																	<br/>- Ikke registrert i Merverdiavgiftsregisteret
																	<br/>- Er under avvikling
																	<br/>- Er under tvangsavvikling/tvangsoppløsning
																</td>
															</tr>
															<tr>
																<td align="left" class="text12">
																	<br/><b>Note:</b> Utførelse av denne funksjonen kan ta litt tid.
																</td>
															</tr>
									           		</table>
													</div>
												</span>
	

								 			</c:if>
						 					<c:if test="${fn:contains(record.prog,'-TVINN') }">
						 						<font class="text14">
								 					<form onMouseOver="showPop('infoTVINN');" onMouseOut="hidePop('infoTVINN');" onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgtvinnsad/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
													</form>
												</font>
												
												<%--
				 								<a class="text14" href="tvinnsadgate.do" onMouseOver="showPop('infoTVINN');" onMouseOut="hidePop('infoTVINN');" onClick="setBlockUI(this);" >
				 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
	 												<font class="text14NavyBlue">${record.prTxt}</font>
		 										</a>
		 										 --%>
				 									<%-- ===========================================  --%>
									            	<%-- Here we have the info popup window TVINN --%>
									            	<%-- ===========================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoTVINN" class="popupPlain"  >
									           		<div align="center">
									           			<table>
									           				<tr>
																<td align="left" class="text12" ><b>TVINN</b>, Tollsystemet 
																</td>
															</tr>
															<tr class="text" height="10"><td></td></tr>
															<tr>
																<td align="center" >
																	<c:choose>
							 											<c:when test="${not empty record.infoImg}">
																			<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																		</c:when>
																		<c:otherwise>
																			<img src="resources/images/miniTVINN.png" border="0" width="350px"; height="210px">
																		</c:otherwise>
																	</c:choose>
																	
																</td>
															</tr>
										           		</table>
													</div>
												</span>
											</c:if>
						 					<c:if test="${fn:contains(record.prog,'-SKAT') }">
						 						<font class="text14">
								 					<form onMouseOver="showPop('infoSKAT');" onMouseOut="hidePop('infoSKAT');" onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgskat/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
													</form>
												</font>
										
						 						<%--
				 								<a class="text14" href="skatgate.do" onMouseOver="showPop('infoSKAT');" onMouseOut="hidePop('infoSKAT');" onClick="setBlockUI(this);" >
				 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
	 												<font class="text14NavyBlue">${record.prTxt}</font>
		 										</a>
		 										 --%>
				 									<%-- ===========================================  --%>
									            	<%-- Here we have the info popup window SKAT --%>
									            	<%-- ===========================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoSKAT" class="popupPlain"  >
									           		<div align="center">
									           			<table>
									           				<tr>
																<td align="left" class="text12" ><b>SKAT</b>, Toldsystemet. 
																</td>
															</tr>
															<tr class="text" height="10"><td></td></tr>
															<tr>
																<td align="center" >
																	<c:choose>
							 											<c:when test="${not empty record.infoImg}">
																			<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																		</c:when>
																		<c:otherwise>
																			<img src="resources/images/miniSKAT.png" border="0" width="350px"; height="210px">
																		</c:otherwise>
																	</c:choose>
																	
																</td>
															</tr>
										           		</table>
													</div>
												</span>
											</c:if>
				 							<c:if test="${fn:contains(record.prog,'-TDS') }">
				 								<font class="text14">
								 					<form onMouseOver="showPop('infoTDS');" onMouseOut="hidePop('infoTDS');" onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgtds/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
													</form>
												</font>
												
				 								<%--
				 								<a class="text14" href="tdsgate.do" onMouseOver="showPop('infoTDS');" onMouseOut="hidePop('infoTDS');" onClick="setBlockUI(this);" >
				 									<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
	 												<font class="text14NavyBlue">${record.prTxt}</font>
		 										</a>
				 								 --%>
				 									<%-- ===========================================  --%>
									            	<%-- Here we have the info popup window TDS --%>
									            	<%-- ===========================================  --%>
									            	<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoTDS" class="popupPlain"  >
									           		<div align="center">
									           			<table>
									           				<tr>
																<td align="left" class="text11" >
																	<b>TDS</b>, Tulldatasystemet, är det datasystem som används i Sverige för klarering av import-och exportärenden och för att debitera tull och moms.
																	TDS inkluderar också Transitering som kallas för NCTS.<br/>
																	<b>NCTS</b> är en förkortning av New Computerised Transit System och är en datorisering av gemensam och gemenskapstransitering.
																</td>
															</tr>
															<tr class="text" height="5"><td></td></tr>
															<tr>
																<td align="center" >
																	<c:choose>
							 											<c:when test="${not empty record.infoImg}">
																			<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																		</c:when>
																		<c:otherwise>
																			<img src="resources/images/miniTds.png" border="0" width="350px"; height="210px">
																		</c:otherwise>
																	</c:choose>
																	
																</td>
															</tr>
										           		</table>
													</div>
												</span>
											</c:if>
											<c:if test="${fn:contains(record.prog,'-UFORTOPPD') }">
												<font class="text14">
								 					<form onMouseOver="showPop('infoUOpp');" onMouseOut="hidePop('infoUOpp');" onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgoverview/logonDashboard.do?uopp=1" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
													</form>
												</font>
												<%--
				 								<a class="text14" href="uoppdraggate.do?deepSubmit=do" onMouseOver="showPop('infoUOpp');" onMouseOut="hidePop('infoUOpp');" onClick="setBlockUI(this);" >
	 												<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													<font class="text14NavyBlue">${record.prTxt}</font>
													</a>
													--%>
				 									<%-- ======================================================  --%>
									            	<%-- Here we have the info popup window Ufortollede Oppdrag  --%>
									            	<%-- ======================================================  --%>
									            		<span style="position:absolute; left:720px; top:180px; width:390px; height:300px;" id="infoUOpp" class="popupPlain"  >
										           		<div align="center">
										           			<table>
										           				<tr>
																	<td align="left" class="text12" ><b>Ufortollede Oppdrag</b><br/>
																	</td>
																</tr>
																<tr class="text" height="10"><td></td></tr>
																<tr>
																	<td align="center" >
																		<c:choose>
							 											<c:when test="${not empty record.infoImg}">
																			<img src="${record.infoImg}" border="0" width="350px"; height="210px">
																		</c:when>
																		<c:otherwise>
																			<img src="resources/images/miniUoppdChart.jpg" border="0" width="350px"; height="210px">
																		</c:otherwise>
																		</c:choose>
																		
																	</td>
																</tr>
											           		</table>
														</div>
													</span>
											</c:if>
											<c:if test="${fn:contains(record.prog,'-KVALITET') }">
												<font class="text14">
								 					<form onClick="setBlockUI(this);" style="display: inline;" method="post" action="/espedsgoverview/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													    <button style="border: 0; padding: 0; display: inline; background: none; cursor: pointer;"><font class="text14NavyBlue">${record.prTxt}</font></button>
													</form>
												</font>
												<%--
				 								<a class="text14" href="sendingerlevtidgatefilter.do" >
	 												<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													<font class="text14NavyBlue">${record.prTxt}</font>
												</a>
												 --%>
											</c:if>
											<c:if test="${fn:contains(record.prog,'-CUST_APP') }">
				 								<a class="text14" href="espedsgadmin.do" >
	 												<img src="${imgSrcTomcat}" width="10px" height="10px" border="0">&nbsp;
													<font class="text14NavyBlue">${record.prTxt}</font>
													</a>
											</c:if>
						 					</font>
					 						</li>
										</c:if>
						 			</c:forEach>	
						 			</ol>
						 			</td>
				 				</tr>
			 				</table>
	      				</td>
			        </tr>
			        <tr class="text" height="50"><td></td></tr>
			        <tr>
			 			<td class="text12" align="center" >		
			 				<table width="96%" align="center" class="dashboardFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="20">
						 			<td class="text14White">
						 				<b>&nbsp;Andre moduler&nbsp;</b>
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		<tr >
			    		<td class="text12" align="center" >
			    			<table width="96%" align="center" class="dashboardFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr >
						 			<td align="left" height="60px" class="text14">
						 			<ul>
					 				<c:forEach items="${list}" var="record" varStatus="counter"> 
						 				<c:if test="${ !fn:contains(record.prog, 'TOMCAT') }">
						 					<c:set var="imgSrcNoneTomcat" scope="session" value="resources/images/bulletGrey.png"/>	
						 					<li style="line-height:20px; list-style-type: none;">
						 					<font class="text14">
											<c:choose>
					 							<c:when test="${not empty record.prog && fn:contains(record.prog,'UsrSpcName') }">
						 							<a class="text14" target="_blank" href="${record.progChunksUrl}" onclick="window.open(${record.progChunks}); return false" >
														<img src="${imgSrcNoneTomcat}"  width="10px" height="10px" border="0">&nbsp;
		 												<font class="text14SlateGray">${record.prTxt}</font>
		 											</a>	
					 							</c:when>
					 							<c:otherwise>
						 							<img src="${imgSrcNoneTomcat}" width="10px" height="10px" border="0">&nbsp;
		 											<font class="text14GrayInactiveLinkOnDashbord">${record.prTxt}</font>
					 							</c:otherwise>
				 							</c:choose>
				 							</font>
				 							</li>
			 							</c:if> 
		 							</c:forEach>	 
						 			</ul>	
					 				</td>
				 				</tr>
			 				</table>
	      				</td>
			        </tr>
			        <tr class="text" height="100"><td></td></tr>
			     </table> 
			</td>
			<td height="500" width="500" align="center" valign="top" > 
    			 <table width="96%" class="dashboardFrameMain" border="0" cellspacing="0" cellpadding="0">
    			 	<tr class="text" height="1"><td></td></tr>
    			 	<tr>
			 			<td class="text12" align="center" >		
			 				<table width="96%" align="center" class="dashboardFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="20">
						 			<td class="text14White">
						 				<b>&nbsp;Info&nbsp;&nbsp;</b>
							    			<font class="text12LightGreen">${user.versionSpring}</font>
						    				<img style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" onClick="showPop('versionInfo');" > 
									        <span style="position:absolute; left:700px; top:105px; width:150px; height:50px;" id="versionInfo" class="popupWithInputText"  >
									           		<div class="text11" align="left">
									           			${user.versionEspedsg}</br></br>
									           			<button name="versionInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('versionInfo');">Close</button> 
									           		</div>
									        </span>  
						 				 
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		
				 	<tr >
			    		<td class="text12" align="center" valign="top" >
			    			<table width="96%" align="center" class="dashboardFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr >
						 			<td height="300px" class="text14" valign="top" >
						 				&nbsp;&nbsp;
					 				</td>
				 				</tr>
				 				<tr class="text" height="50"><td></td></tr>
			 				</table>
	      				</td>
			        </tr>
   				 	<tr class="text" height="100"><td></td></tr>
			        
			     </table> 
			</td>
		</tr>
		<%-- Pop-up window --%>
		<tr>
			<td>
				<div id="dialogRunKundedatakontroll" title="Dialog">
					<form  action="tvinnsad_brreg_kundekontroll.do" name="runKundedatakontrollForm" id="runKundedatakontrollForm" method="post">
						<p class="text12">
							Utførelse av denne funksjonen kan ta litt tid.
						</p>
					</form>
				</div>
			</td>
		</tr>
		
		<tr class="text" height="30"><td></td></tr>	
	
	