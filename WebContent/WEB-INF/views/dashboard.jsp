<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ================== special login header ==================-->
<jsp:include page="/WEB-INF/views/headerDashboard.jsp" />
<!-- =====================end header ==========================-->
	<style type = "text/css">
	/*.ui-dialog{font-size:11pt;}*/
	/* this is in order to customize a SPECIFIC ui dialog in the .js file ...dialog() */
	.main-dialog-class .ui-widget-header{ background-image: none; background-color:#CCCCCC } 
	.main-dialog-class .ui-widget-content{ background-image:none;background-color:lemonchiffon }

	</style>

		<%-- Applications' menu --%>
		<tr height="400" >
			<td width="100%" align="center" valign="top" > <%-- height="300" width="500" --%>
    			 <table width="100%"  border="0" cellspacing="0" cellpadding="0"> <%-- class="dashboardFrameMain" --%>
   			 		<tr class="text" height="1"><td></td></tr>
   			 		<tr>
   			 		<td class="text12" align="center" >	
   			 		<table width="100%"  border="0" cellspacing="0" cellpadding="0" align="center">
   			 		
   			 		<%--
   			 		<tr>
			 			<td class="text12" align="center" >		
			 				<table width="98%" align="center" class="dashboardFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="20">
						 			<td class="text14White">
						 				<b>&nbsp;Modul<a href="asyjservices_mainlist.do" ><font class="text14White">e</font></a>r - espedSg&nbsp;</b>
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 		</tr>
			 		 --%>
			 		<tr >
			    		<td class="text12" align="center" >
			    			<table width="100%" align="center" class="dashboardFrameMainE2" border="0">
						 		<tr >
						 		<td class="text12" align="center" >
			    				<input type="hidden" name="usrLang" id="usrLang" value="${user.usrLang}" />
			    				
			    				<table align="center" border="0" style="border-spacing: 10px;border-collapse: separate;">
						 		
						 		 <tr>	
						 		 	<%-- this counter is required in order to catch ONLY "TOMCAT" strings from service. The list has other strings that must be excluded --%>
						 		 	<c:set var="counterTOMCATAPPS" value="0" scope="page" />
			 						<c:forEach items="${list}" var="record" varStatus="counter"> 
			 							
						 				<c:if test="${ fn:contains(record.prog, 'TOMCAT') }">
						 					<c:set var="counterTOMCATAPPS" value="${counterTOMCATAPPS + 1}" scope="page"/> 
						 					
						 					<c:set var="imgSrcTomcat" scope="session" value="resources/images/bulletGreen.png"/>
						 					<c:set var="imgSrcTomcatRed" scope="session" value="resources/images/bulletRed.png"/>
						 					
						 					<c:if test="${fn:contains(record.prog,'-RAPPORTER') }">
					 							<td id="dashItem_StatsFortolling" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
													<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
								 					<form id="dashForm_StatsFortolling" method="post" action="/espedsgstats/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													</form>
		 										</td>
		 										<c:if test="${counter.count%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>
 
					 						<c:if test="${fn:contains(record.prog,'-TRAFIKKREGNSKAP') }">
					 							<td id="dashItem_StatsTrafikk" class="dashboardElementsFrameE2" align="center" width="250px" height="150px" >
					 								<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
				 									<form id="dashForm_StatsTrafikk" method="post" action="/espedsgstats/logonDashboard.do?trafikk=1" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													</form>
													</font>
												</td>												
		 										<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>

					 						<c:if test="${fn:contains(record.prog,'-WRKTRIPS') }">
					 								<c:choose>
							 							<c:when test="${not empty user.userAS400}">
							 								<td id="dashItem_Transpdisp" class="dashboardElementsFrameE2" align="center" width="250px" height="150px" >
							 									<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
							 									<br/>
							 									<font class="text18">${record.prTxt}</font>
															</td>
														</c:when>
														<c:otherwise>
															<%-- userAS400 = ASUSR parameter i AS400 is mandatory in order to use -WRKTRIPS --%>
															<td class="dashboardElementsFrameE2" align="center" width="250px" height="150px" >
																<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 												<br/>
				 												<font class="text14SlateGray" onMouseOver="showPop('wrktrips_info');" onMouseOut="hidePop('wrktrips_info');">
											 						${record.prTxt}
								 								</font>
								 								<div class="text11" style="position: relative;" align="left" >
																<span style="position:absolute;top:1px;" id="wrktrips_info" class="popupWithInputText text12"  >		
													           		Din <b>esped User er IKKE koblet</b> mot en Server-userId (<b>ASUSR</b>-parameter). Kontakt systemansvarlig
																</span>		
											 					</div>
										 					</td>
										 					
														</c:otherwise>
													</c:choose>
													<c:if test="${counterTOMCATAPPS%5==0}">
			 										 	</tr>
				 										 <tr>
				 									</c:if>
											</c:if>
											<c:if test="${fn:contains(record.prog,'-EBOOKING') }">
												<td id="dashItem_Ebooking" class="dashboardElementsFrameE2" align="center" width="250px" height="150px" >
													<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
				 									<c:if test="${not empty record.veiledning}">
														<span style="white-space: nowrap;">
			 											<a href="${record.veiledning}" target="_blank">
			 												<img title="Brukerveiledning" style="vertical-align:middle;" src="resources/images/pdf2.png" border="0" width="14px"; height="14px">
			 											</a>
			 											</span>
			 										</c:if>
								 					<form id="dashForm_Ebooking" method="post" action="/espedsgebook/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <input type="hidden" name="lang" value="${user.usrLang}" />
													</form>
													
		 										</td>
		 										<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>
											<c:if test="${fn:contains(record.prog,'-TROR') }">
												<td id="dashItem_Tror" class="dashboardElementsFrameE2" align="center" width="250px" height="150px" >
													<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
								 					<form id="dashForm_Tror" method="post" action="/espedsgtror/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <input type="hidden" name="lang" value="${user.usrLang}" />
													</form>
							 					</td>
							 					<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 	
											</c:if>

											<c:if test="${fn:contains(record.prog,'-TESTSUITES') }">
												<td id="dashItem_Testsuites" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
							 						<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>
											<c:if test="${fn:contains(record.prog,'-GODSREGNO') }">
												<td id="dashItem_GodsReg" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
							 						<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="Godsreg. module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
				 									<form id="dashForm_GodsReg" method="post" action="/espedsggodsno/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <input type="hidden" name="lang" value="${user.usrLang}" />
													</form>
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>
											
											<c:if test="${fn:contains(record.prog,'-eFaktura') }">
												<td id="dashItem_Efaktura" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
													<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
								 					<form id="dashForm_Efaktura" method="post" action="/espedsgefaktura/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <input type="hidden" name="lang" value="${user.usrLang}" />
													</form>
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>
						 					<c:if test="${fn:contains(record.prog,'-SPORROPP') }">
						 						<td id="dashItem_Sporroppdrag" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
							 						<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>
											<c:if test="${fn:contains(record.prog,'-PRISKALK') }">
												<td id="dashItem_Priskalk" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
						 							<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
						 							<form id="dashForm_Priskalk" method="post" action="/espedsgpkalk/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    <input type="hidden" name="lang" value="${user.usrLang}" />
													</form>
												</td>
					 							<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
						 					</c:if>
						 					<c:if test="${fn:contains(record.prog,'-VEDLIKEHOLD') }">
						 						<td id="dashItem_Vedlikehold" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
							 						<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>
											<%-- ONLY for external customers --%>
								 			<c:if test="${fn:contains(record.prog,'-TAVGG') }">
								 				<td id="dashItem_Tvinnavgg" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
								 					<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
								 					<form id="dashForm_Tvinnavgg" method="post" action="/espedsgtvinnavgg/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    
													</form>
												
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
								 			</c:if>
								 			<c:if test="${fn:contains(record.prog,'-TBRREG') }">
								 				<td id="dialogRunKundedatakontrollLink" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
								 					<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
				 								</td>
				 								<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
								 			</c:if>
						 					<c:if test="${fn:contains(record.prog,'-TVINN') }">
						 						<td id="dashItem_Tvinn" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
						 							<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
								 					<form id="dashForm_Tvinn"  method="post" action="/espedsgtvinnsad/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													</form>
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
												
											</c:if>
						 					<c:if test="${fn:contains(record.prog,'-SKAT') }">
						 						<td id="dashItem_Skat" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
						 							<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
								 					<form id="dashForm_Skat" method="post" action="/espedsgskat/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													    
													</form>
												
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
						 						
											</c:if>
				 							<c:if test="${fn:contains(record.prog,'-TDS') }">
				 								<td id="dashItem_Tds" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
				 									<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
								 					<form id="dashForm_Tds" method="post" action="/espedsgtds/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													</form>
																	
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
				 								
											</c:if>
											<c:if test="${fn:contains(record.prog,'-UFORTOPPD') }">
												<td id="dashItem_Ufortollede" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
													<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
								 					<form id="dashForm_Ufortollede" method="post" action="/espedsgoverview/logonDashboard.do?uopp=1" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													</form>
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>
											<c:if test="${fn:contains(record.prog,'-KVALITET') }">
												<td id="dashItem_Kvalitet" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
													<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
													<%-- Use a form disguised as a-link --%>
													<form id="dashForm_Kvalitet"  method="post" action="/espedsgoverview/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													</form>
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>
											<c:if test="${fn:contains(record.prog,'-VISMA-INT') }">
					 							<td id="dashItem_VismaInt" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
													<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
								 					<form id="dashForm_VismaInt" method="post" action="/visma-net-proxy/logonDashboard.do" >
													    <input type="hidden" name="user" value="${user.user}" />
													    <input type="hidden" name="password" value="${user.encryptedPassword}" />
													</form>
		 										</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>
											<c:if test="${fn:contains(record.prog,'-CUST_APP') }">
												<td id="dashItem_custMatrix" class="dashboardElementsFrameE2" align="center" width="250px" height="150px"  >
					 								<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 									<br/>
				 									<font class="text18">${record.prTxt}</font>
												</td>
												<c:if test="${counterTOMCATAPPS%5==0}">
		 										 </tr>
		 										 <tr>
		 										</c:if> 
											</c:if>
										

										</c:if>
										
						 			</c:forEach>
						 		
						 			<tr >
							 			<c:if test="${user.user == 'OSCAR'}">
							 				<td id="dashItem_roadmap" class="dashboardElementsFrameE2" align="center" width="250px" height="150px" >
							 					<img class="dashboardElementsImgCircleE2" src="resources/images/leaf.png" height="30px" width="30px" border="0" alt="test module">
				 								<br/>
				 								<font class="text18">eSpedsg Roadmap</font>
							 				</td>
							 			</c:if>
						 			</tr>
						 			    
								 		<tr >
								 			<c:forEach items="${list}" var="record" varStatus="counter"> 
											 <c:if test="${ !fn:contains(record.prog, 'TOMCAT') }">
								    		
								    			<c:choose>
						 							<c:when test="${not empty record.prog && fn:contains(record.prog,'UsrSpcName') }">
						 							<td class=dashboardElementsFrameE2_OldApps align="center" width="250px" height="150px">
										    			<a class="text14" style="display:block; width: 100%; text-align: center;" target="_blank" href="${record.progChunksUrl}" onclick="window.open(${record.progChunks}); return false" >
											    			<img class="dashboardElementsImgCircleE2" src="resources/images/leaf3.png" height="20px" width="20px" border="0" alt="test module">
							 								<br/>
							 								<font class="text18">
								 								<img src="${imgSrcNoneTomcat}"  width="10px" height="10px" border="0">&nbsp;
				 												<font class="text18SlateGray">${record.prTxt}</font>
				 											</font>
			 											</a>
			 										</td>		
			 										
						 							</c:when>
						 							<c:otherwise>
						 								<td class=dashboardElementsFrameE2_OldApps align="center" width="250px" height="150px">
										    				<img src="${imgSrcNoneTomcat}" width="10px" height="10px" border="0">&nbsp;
			 												<font class="text14GrayInactiveLinkOnDashbord">${record.prTxt}</font>
			 											</td>
						 							</c:otherwise>
					 							</c:choose>
					 						</c:if>
					 						</c:forEach>
				 						</tr>
				 						<%-- ORIGINAL
				 						<tr >
								 			<c:forEach items="${list}" var="record" varStatus="counter"> 
											 <c:if test="${ !fn:contains(record.prog, 'TOMCAT') }">
								    		<td class=dashboardElementsFrameE2_OldApps align="center" width="250px" height="150px">
								    			<img class="dashboardElementsImgCircleE2" src="resources/images/leaf3.png" height="20px" width="20px" border="0" alt="test module">
				 								<br/>
				 								<font class="text18">
								    			<c:choose>
						 							<c:when test="${not empty record.prog && fn:contains(record.prog,'UsrSpcName') }">
							 							<a class="text14" target="_blank" href="${record.progChunksUrl}" onclick="window.open(${record.progChunks}); return false" >
															<img src="${imgSrcNoneTomcat}"  width="10px" height="10px" border="0">&nbsp;
			 												<font class="text18SlateGray">${record.prTxt}</font>
			 											</a>	
						 							</c:when>
						 							<c:otherwise>
							 							<img src="${imgSrcNoneTomcat}" width="10px" height="10px" border="0">&nbsp;
			 											<font class="text14GrayInactiveLinkOnDashbord">${record.prTxt}</font>
						 							</c:otherwise>
					 							</c:choose>
					 							</font>
					 						</td>
					 						</c:if>
					 						</c:forEach>
				 						</tr>	
				 						 --%>
					 					<tr class="text" height="50"><td></td></tr>
								    
						 			</table>
						 			</td>	
				 				</tr>
			 				</table>
	      				</td>
	      				</tr>
	      				</table>
	      				</td>
			        </tr>
			        <%--
			        <tr class="text" height="50"><td></td></tr>
			        <tr>
			 			<td class="text12" align="center" >		
			 				<table width="98%" align="center" class="dashboardFrameHeader" border="0" cellspacing="0" cellpadding="0">
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
			    			<table width="99%" align="center" class="dashboardFrame" border="0" cellspacing="0" cellpadding="0">
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
			 --%>
			
		<%-- Pop-up window --%>
		<tr>
			<td>
				<div id="dialogRunKundedatakontroll" title="Dialog">
					<form  action="tvinnsad_brreg_kundekontroll.do" name="runKundedatakontrollForm" id="runKundedatakontrollForm" method="post">
						<p class="text16">
							Utførelse av denne funksjonen kan ta litt tid.
						</p>
					</form>
				</div>
			</td>
		</tr>
		
		<%-- --------------------- --%>
		<%-- DIALOG CHG PASSWORD   --%>
		<%-- --------------------- --%>
		<tr>
		<td>
			<div id="dialogChangePwd" title="Dialog">
					<form name="loginFormChgPwd" id="loginFormChgPwd" action="doChgPwd.do" method="POST" >
					<input type="hidden" name="validUser" id="validUser" value="${user.user}">
				 	<table>
							<tr>
								<td align="right" class="text16"><font class="text14RedBold" >*</font><spring:message code="login.user.label.password.new"/>&nbsp;</td>
								<td><input type="password"  class="inputTextMediumBlue"  name="passwordNew" id="passwordNew" size="11" maxlength="10" /></td>
							</tr>
							<tr>
								<td align="right" class="text16"><font class="text14RedBold" >*</font><spring:message code="login.user.label.password.confirm"/>&nbsp;</td>
								<td><input type="password"  class="inputTextMediumBlue"  name="passwordConfirm" id="passwordConfirm" size="11" maxlength="10" /></td>
							</tr>
							
							<tr>
								<td align="right" class="text14">&nbsp;</td>
								<td><label class="textError" id="validationLabelMessage"></label></td>
							</tr>
							
					</table>
					</form>
			</div>
		</td>
		</tr>
		
		<tr class="text" height="30"><td></td></tr>	
	
	