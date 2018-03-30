<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<script type="text/javascript" src="resources/js/mainmaintenancecundf_vareimp_no_edit.js?ver=${user.versionEspedsg}"></script>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>

<table id="parentTab" width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
			<%-- tab container component --%>
			<table id="tabContainer"  width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
				<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
						<font class="tabDisabledLink">&nbsp;
							<spring:message code="systema.main.maintenance.label"/>
						</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainCundfGate" tabindex=-1 style="display:block;" href="mainmaintenancecundf_vkund.do">
						<font class="tabDisabledLink">&nbsp;
							<spring:message code="systema.main.maintenance.customerreg"/>
						</font>&nbsp;
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
						</a>
					</td>
					<c:choose>
						
						<c:when test="${not empty kundeSessionParams.kundnr}">
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;
									<spring:message code="systema.main.maintenance.customer"/>
								</font>&nbsp;
								<font class="text11MediumBlue">[${tab_knavn_display}]</font>
							</td>
							<td width="55%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						</c:when>
						<c:otherwise>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;
									<spring:message code="systema.main.maintenance.customer.new"/>
								</font>&nbsp;						
								<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
							</td>
							<td width="55%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</td>
	</tr>
	
	<tr>
		<td>
			<%-- space separator --%>
	 		<table id="tabRows" width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
			
<!-- 	 	    
	 	    <tr>
	 	   		<td width="25%">&nbsp;</td> 
	 	   		<td>
					<table id="firstTabRow" class="formFrameHeaderTransparent" width="1000" cellspacing="0" border="0" cellpadding="0">
						<tr height="20"> 
							<td width="20">&nbsp;</td>
							<td width="80" valign="bottom" class="tabDisabled" align="center" title="Sköna  personer...">
								<a id="alinkMainMaintMavgGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Miljöavgift</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabled" align="center" title="xXx...">
								<a id="alinkMainMaintMavgGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Fane xxx</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Fane xyz</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Fane xyz</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Fane xyz</font>&nbsp;						
								</a>
							</td>

							<td width="80" valign="bottom" class="tabDisabledTrailingAbove" align="center">
								<a id="alinkMainMaintxxxGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;Fane xyz</font>&nbsp;						
								</a>
							</td>

						   <td width="500"></td>
						</tr>
					</table>
				</td>
 	   	 	</tr>
 	
  -->   	 	
 	   	 	<tr> 
 	   	 		<td>&nbsp;</td>
 	   	 	    <td>
 					<table id= "secondTabRow" class="formFrameHeaderTransparent" style="width:1000px" cellspacing="0" border="0" cellpadding="0">
						<tr height="20"> 
							<td width="110" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintKundeGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_kunde_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer"/>
									</font>
								</a>
							</td>
							<td width="110" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintKontaktGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_kontaktpersoner_list.do">
									<font class="tabDisabledLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer.contacts"/>
									</font>&nbsp;						
								</a>
							</td>
							
							<td width="110" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintFritextGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_fritekst_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer.text"/>
									</font>&nbsp;						
								</a>
							</td>
							<td width="110" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_params_list.do">
									<font class="tabDisabledLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer.params"/>
									</font>&nbsp;						
								</a>
							</td>							
							<td width="110" valign="bottom" class="tabSub" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer.vareregister"/>
									</font>&nbsp;						
							</td>
							
<!--  

							<td width="110" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<a id="alinkMainMaintMavgGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_xxx_edit.do">
									<font class="tabDisabledLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer.envfee"/>
									</font>&nbsp;						
								</a>
							</td>							
-->							
<!-- 
							<td width="50" class="tabDisabledTrailingEnd"></td>
 -->
						 	<td width="540" class="tabFantomSpace" align="center" nowrap></td>
						</tr>
					</table>
				</td>
 	   	 	</tr> <!-- End second tab row  -->  

	   	 	<tr> 
 	   	 		<td>&nbsp;</td>
 	   	 	    <td>
 					<table id= "thirdTabRow" class=formFrameHeaderTransparentThirdTabRow style="width:1000px" cellspacing="0" border="0" cellpadding="0">
 					   <tr height="20"><td>&nbsp;</td>
 					   </tr>
					   <tr height="20"> 
							<c:if test="${kundeSessionParams.exportNo == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMainMaintVareExpNoGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareexp_no.do">
											<font class="tabDisabledLinkMinor">&nbsp;
												  <spring:message code="systema.main.maintenance.customer.vareregister.exp.no"/>
											</font>&nbsp;						
									</a>
								</td>
							</c:if>
							<td width="100" valign="bottom" class="tabSub" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;
									   <spring:message code="systema.main.maintenance.customer.vareregister.imp.no"/>
									</font>
							</td>
							<c:if test="${kundeSessionParams.exportDk == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<!-- 	<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareexp_dk_edit.do">  -->
										<font class="tabDisabledLinkMinor">&nbsp;
											 <spring:message code="systema.main.maintenance.customer.vareregister.exp.dk"/>
										</font>&nbsp;						
								<!--  	</a> -->
								</td>							
							</c:if>
							<c:if test="${kundeSessionParams.importDk == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<!--  	<a id="alinkMainMaintFritextGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareimp_dk_edit.do"> -->
										<font class="tabDisabledLinkMinor">&nbsp;
											 <spring:message code="systema.main.maintenance.customer.vareregister.imp.dk"/>
										</font>&nbsp;						
								<!--  	</a> -->
								</td>
							</c:if>
							<c:if test="${kundeSessionParams.exportSe == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMainMaintVareExpSeGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareexp_se.do"> 
											<font class="tabDisabledLinkMinor">&nbsp;
												 <spring:message code="systema.main.maintenance.customer.vareregister.exp.se"/>
											</font>&nbsp;						
									</a> 
								</td>		
							</c:if>
							<c:if test="${kundeSessionParams.importSe == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMainMaintVareImpSeGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareimp_se.do">
										<font class="tabDisabledLinkMinor">&nbsp;
											 <spring:message code="systema.main.maintenance.customer.vareregister.imp.se"/>
										</font>&nbsp;						
									</a>
								</td>
							</c:if>
							
						 	<td width="${kundeSessionParams.fantomSpaceWidth}" class="tabFantomSpace" align="center" nowrap></td>

						</tr>
					</table>
				</td>
 	   	 	</tr> <!-- End third tab row -->

 	   	 	<tr height="30">
 	   	 		<td>&nbsp;</td>
 	   	 		<td width="100%">
 	   	 		 <table id="mainArea" class="tabThinBorderWhite" width="100%" cellspacing="0" border="0" align="left">
 	   	 		 	<tr id="list">
 	   	 		 		<td>&nbsp;
							<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;<spring:message code="systema.edit"/>&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.varenr"/>&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.varebe"/>&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2vf"/>&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2lk"/>&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2vnti"/>&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2tn"/>&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2pre"/>&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2pva"/>&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2as"/>&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2mfr"/>&nbsp;</th>
			                    <th align="center" class="tableHeaderField"><spring:message code="systema.delete"/></th>
			                </tr>  
				             </thead> 
				             <tbody >  
					            <c:forEach var="record" items="${model.list}" varStatus="counter">   
					               <tr class="tableRow" height="20" >
						               <td id="recordUpdate_${record.levenr}_${record.varenr}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
				               				<img src="resources/images/update.gif" border="0" alt="edit">
						               </td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.varenr}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.varebe}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2vf}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2lk}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2vnti}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2tn}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2pre}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2pva}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2as}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.w2mfr}&nbsp;</font></td>
						               
						               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
				               				<a onclick="javascript:return confirm('<spring:message code="systema.delete.confirm"/>')" tabindex=-1 href="mainmaintenancecundf_vareimp_no_edit.do?action=doDelete&varenr=${record.varenr}&levenr=${record.levenr}">
							               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
							               	</a>
						               </td>
					               </tr> 
					            </c:forEach>
					          </tbody>
				            </table>
 	   	 		 		</td>
 	   	 		 	</tr>

					<tr height="25">
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>

 	   	 		 	<tr id="details">
 	   	 		 		<td>
							<form action="mainmaintenancecundf_vareimp_no_edit.do" name="formRecord" id="formRecord" method="POST" >
								<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
								<input type="hidden" name="updateId" id="updateId" value="${model.updateId}"> 
								<input type="hidden" name="action" id=action value="doUpdate">
								<table id="paramsDetails" width="100%" cellspacing="0" border="0" align="left">
									<spring:hasBindErrors name="record"> 
										<tr>
											<td>
												<table align="left" border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td>
															<ul class="isa_error text12">
																<c:forEach var="error" items="${errors.allErrors}">
																	<li><spring:message code="${error.code}"
																			text="${error.defaultMessage}" />&nbsp;&nbsp;</li>
																</c:forEach>
															</ul>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</spring:hasBindErrors>
									<c:if test="${not empty model.errorMessage}">
									<tr>
										<td >
								           	<table align="left" border="0" cellspacing="0" cellpadding="0">
										 		<tr>
										 			<td >
										 				<ul class="isa_error text12" >
								                                  <li>${model.errorMessage}</li>                                    
								                              </ul>
										 			</td>
												</tr>
											</table>
										</td>
									</tr>
									</c:if>
								
									<tr>
										<td>
											<button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button">
												<spring:message code="systema.new"/>
											</button>&nbsp;&nbsp;
									</tr>
									<tr height="2">
										<td>&nbsp;</td>
									</tr>
									<tr> <!-- Artikelinfo -->
										<td colspan="2">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;
														<spring:message code="systema.main.maintenance.mainmaintenancesadvare.header.productinfo"/>
													</td>
												</tr>
											</table>			

											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr> 
													<td width="50%" valign="top">
														<table border="0">
															<tr>
																<td class="text12" title="varenr">&nbsp;<font class="text14RedBold" >*</font><spring:message code="systema.main.maintenance.mainmaintenancesadvare.varenr"/>:</td>
																<td><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="varenr" id="varenr" size="30" maxlength="28" value='${model.record.varenr}'></td>
																<td class="text12" title="w2vf">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2vf"/>:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2vf" id="w2vf" size="1" maxlength="1" value='${model.record.w2vf}'>
																	<a tabindex="-1" id="w2vfIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
															</tr>
															
															<tr>
																<td class="text12" title="varebe">&nbsp;<font class="text14RedBold" >*</font><spring:message code="systema.main.maintenance.mainmaintenancesadvare.varebe"/>:</td>
																<td><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField"  name="varebe" id="varebe" size="40" maxlength="50" value='${model.record.varebe}'></td>
															
																<td class="text12" title="w2lk">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2lk"/>:</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2lk" id="w2lk" size="2" maxlength="2" value='${model.record.w2lk}'>
																	<a tabindex="-1" id="w2lkIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
															</tr>
															
															<tr>
																<td class="text12" title="w2vnti">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2vnti"/>:</td>
																<td><input type="text"  onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2vnti" id="w2vnti" size="30" maxlength="30" value='${model.record.w2vnti}'>
																	<a tabindex="-1" id="w2vntiIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td class="text12" title="w2pre">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2pre"/>:</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2pre" id="w2pre" size="1" maxlength="1" value='${model.record.w2pre}'>
																	<a tabindex="-1" id="w2preIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
															</tr>
															
															<tr>
																<td class="text12" title="w2belt">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2belt"/>:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2belt" id="w2belt" size="15" maxlength="15" value='${model.record.w2belt}'></td>
																<td class="text12" title="w2vktb">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2vktb"/>:</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2vktb" id="w2vktb" size="15" maxlength="15" value='${model.record.w2vktb}'></td>
															</tr>
															
															<tr>
																<td class="text12" title="w2vktn">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2vktn"/>:</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2vktn" id="w2vktn" size="15" maxlength="15" value='${model.record.w2vktn}'></td>
																<td class="text12" title="w2ntm">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2ntm"/>:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2ntm" id="w2ntm" size="10" maxlength="9" value='${model.record.w2ntm}'></td>
															</tr>
														</table>
													</td>
													
													<td width="50%" valign="top">
														<table border="0">
															<tr>
																<td class="text12" title="w2mfr">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2mfr"/>:</td>
																<td>								
																	<select name="w2mfr" id="w2mfr" > 
								 					  					<option value="">-<spring:message code="systema.choose"/>-</option>  
								 					  					<option value="F"<c:if test="${model.record.wmfr == 'F'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2mfr"/></option>
													  					<option value="1"<c:if test="${ model.record.w2mfr == '1'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancesadvare.vathigh"/></option>
													  					<option value="2"<c:if test="${ model.record.w2mfr == '2'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancesadvare.vatlow"/></option>
													  				</select>
																</td>																
																<td class="text12" title="w2beln">&nbsp;&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2beln"/>:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2beln" id="w2beln" size="15" maxlength="15" value='${model.record.w2beln}'></td>
															</tr>
															<tr>
																<td class="text12" title="w2bel">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2bel"/>:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2bel" id="w2bel" size="15" maxlength="13" value='${model.record.w2bel}'></td>
																<td class="text12" title="w2pros">&nbsp;&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2pros"/>:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2pros" id="w2pros" size="6" maxlength="6" value='${model.record.w2pros}'>%</td>
															</tr>
															<tr>
																<td class="text12" title="w2val">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2val"/>:</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2val" id="w2val" size="4" maxlength="3" value='${model.record.w2val}'>
																	<a tabindex="-1" id="w2valIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td class="text12" title="w2tn">&nbsp;&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2tn"/>:</td>
																<td><input type="text" class="inputTextMediumBlue" name="w2tn" id="w2tn" size="1" maxlength="1" value='${model.record.w2tn}'>
																	<a tabindex="-1" id="w2tnIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
															</tr>
															
															<tr>
																<td class="text12" title="w2pva">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2pva"/>:</td>
																<td>								
																	<select name="w2pva" id="w2pva" >
								 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
								 					  					<option value="P"<c:if test="${model.record.w2pva == 'P'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2pva.proc"/></option>
													  					<option value="V"<c:if test="${ model.record.w2pva == 'V'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2pva.prkg"/></option>
													  					<option value="W"<c:if test="${ model.record.w2pva == 'W'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2pva.pr100kg"/></option>
													  					<option value="A"<c:if test="${ model.record.w2pva == 'A'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2pva.prliter"/></option>
													  					<option value="F"<c:if test="${ model.record.w2pva == 'F'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2pva.fri"/></option>
													  				</select>
																</td>
																<td class="text12" title="w2as">&nbsp;&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2as"/>:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2as" id="w2as" size="10" maxlength="9" value='${model.record.w2as}'></td>
															</tr>
															
															<tr>
																<td colspan="4">&nbsp;</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</td>
									</tr>  <!-- End Artikelinfo -->
									
									
									<tr> <!-- Artikelbesk o avgift -->
										<td width="50%" valign="top">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.header.productdesc"/></td>
												</tr>
											</table>			

											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr>
													<td  class="text12" colspan="4" align="right">
														<a tabindex="-1" id="enhetIdLink">Ref.
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>&nbsp;&nbsp;&nbsp;
													</td>
												</tr>
												<tr> 
													<td class="text12" title="w2vt01"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2vt01"/></td>
													<td class="text12" title="w2ft01"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2ft01"/></td>
													<td class="text12" title="w2nt01"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2nt01"/></td>
													<td class="text12" title="w2eh01"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2eh01"/></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt01" id="w2vt01" size="31" maxlength="30" value='${model.record.w2vt01}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft01" id="w2ft01" size="29" maxlength="28" value='${model.record.w2ft01}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt01" id="w2nt01" size="10" maxlength="6" value='${model.record.w2nt01}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh01" id="w2eh01" size="5" maxlength="4" value='${model.record.w2eh01}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt02" id="w2vt02" size="31" maxlength="30" value='${model.record.w2vt02}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft02" id="w2ft02" size="29" maxlength="28" value='${model.record.w2ft02}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt02" id="w2nt02" size="10" maxlength="6" value='${model.record.w2nt02}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh02" id="w2eh02" size="5" maxlength="4" value='${model.record.w2eh02}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt03" id="w2vt03" size="31" maxlength="30" value='${model.record.w2vt03}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft03" id="w2ft03" size="29" maxlength="28" value='${model.record.w2ft03}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt03" id="w2nt03" size="10" maxlength="6" value='${model.record.w2nt03}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh03" id="w2eh03" size="5" maxlength="4" value='${model.record.w2eh03}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt04" id="w2vt04" size="31" maxlength="30" value='${model.record.w2vt04}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft04" id="w2ft04" size="29" maxlength="28" value='${model.record.w2ft04}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt04" id="w2nt04" size="10" maxlength="6" value='${model.record.w2nt04}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh04" id="w2eh04" size="5" maxlength="4" value='${model.record.w2eh04}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2vt05" id="w2vt05" size="31" maxlength="30" value='${model.record.w2vt05}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft05" id="w2ft05" size="29" maxlength="28" value='${model.record.w2ft05}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt05" id="w2nt05" size="10" maxlength="6" value='${model.record.w2nt05}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh05" id="w2eh05" size="5" maxlength="4" value='${model.record.w2eh05}'></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft06" id="w2ft06" size="29" maxlength="28" value='${model.record.w2ft06}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt06" id="w2nt06" size="10" maxlength="6" value='${model.record.w2nt06}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2eh06" id="w2eh06" size="5" maxlength="4" value='${model.record.w2eh06}'></td>
												</tr>
												<tr>
													<td>&nbsp;</td>
													<td><input type="text" class="inputTextMediumBlue" name="w2ft07" id="w2ft07" size="29" maxlength="28" value='${model.record.w2ft07}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2nt07" id="w2nt07" size="10" maxlength="6" value='${model.record.w2nt07}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2eh07" id="w2eh07" size="5" maxlength="4" value='${model.record.w2eh07}'></td>
												</tr>												
												<tr>
													<td colspan="4" class="text12" height="25">&nbsp;</td>
												</tr>
											</table>
										</td>
										
										<td width="50%" valign="top">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.header.fee"/></td>
												</tr>
											</table>			
									
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="left">
												<tr>
													<td  class="text12" colspan="4" align="left">
														<a tabindex="-1" id="avgkodeIdLink">Ref.
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>
													</td>
												</tr>
												<tr>
													<td class="text12" title="w2akd"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2akd"/></td>
													<td class="text12" title="w2asv"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2asv"/></td>
													<td class="text12" title="w2asa"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2asa"/></td>
													<td class="text12" title="w2agr"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2agr"/></td>
													<td class="text12" title="w2abl"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2abl"/></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd1" id="w2akd1" size="2" maxlength="2" value='${model.record.w2akd1}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv1" id="w2asv1" size="3" maxlength="3" value='${model.record.w2asv1}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa1" id="w2asa1" size="15" maxlength="13" value='${model.record.w2asa1}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2agr1" id="w2agr1" size="15" maxlength="13" value='${model.record.w2agr1}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2abl1" id="w2abl1" size="6" maxlength="6" value='${model.record.w2abl1}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd2" id="w2akd2" size="2" maxlength="2" value='${model.record.w2akd2}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv2" id="w2asv2" size="3" maxlength="3" value='${model.record.w2asv2}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa2" id="w2asa2" size="15" maxlength="13" value='${model.record.w2asa2}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2agr2" id="w2agr2" size="15" maxlength="13" value='${model.record.w2agr2}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2abl2" id="w2abl2" size="6" maxlength="6" value='${model.record.w2abl2}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd3" id="w2akd3" size="2" maxlength="2" value='${model.record.w2akd3}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv3" id="w2asv3" size="3" maxlength="3" value='${model.record.w2asv3}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa3" id="w2asa3" size="15" maxlength="13" value='${model.record.w2asa3}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2agr3" id="w2agr3" size="15" maxlength="13" value='${model.record.w2agr3}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2abl3" id="w2abl3" size="6" maxlength="6" value='${model.record.w2abl3}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd4" id="w2akd4" size="2" maxlength="2" value='${model.record.w2akd4}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv4" id="w2asv4" size="3" maxlength="3" value='${model.record.w2asv4}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa4" id="w2asa4" size="15" maxlength="13" value='${model.record.w2asa4}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2agr4" id="w2agr4" size="15" maxlength="13" value='${model.record.w2agr4}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2abl4" id="w2abl4" size="6" maxlength="6" value='${model.record.w2abl4}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd5" id="w2akd5" size="2" maxlength="2" value='${model.record.w2akd5}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv5" id="w2asv5" size="3" maxlength="3" value='${model.record.w2asv5}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa5" id="w2asa5" size="15" maxlength="13" value='${model.record.w2asa5}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2agr5" id="w2agr5" size="15" maxlength="13" value='${model.record.w2agr5}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2abl5" id="w2abl5" size="6" maxlength="6" value='${model.record.w2abl5}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd6" id="w2akd6" size="2" maxlength="2" value='${model.record.w2akd6}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv6" id="w2asv6" size="3" maxlength="3" value='${model.record.w2asv6}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa6" id="w2asa6" size="15" maxlength="13" value='${model.record.w2asa6}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2agr6" id="w2agr6" size="15" maxlength="13" value='${model.record.w2agr6}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2abl6" id="w2abl6" size="6" maxlength="6" value='${model.record.w2abl6}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd7" id="w2akd7" size="2" maxlength="2" value='${model.record.w2akd7}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv7" id="w2asv7" size="3" maxlength="3" value='${model.record.w2asv7}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa7" id="w2asa7" size="15" maxlength="13" value='${model.record.w2asa7}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2agr7" id="w2agr7" size="15" maxlength="13" value='${model.record.w2agr7}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2abl7" id="w2abl7" size="6" maxlength="6" value='${model.record.w2abl7}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2akd8" id="w2akd8" size="2" maxlength="2" value='${model.record.w2akd8}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2asv8" id="w2asv8" size="3" maxlength="3" value='${model.record.w2asv8}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2asa8" id="w2asa8" size="15" maxlength="13" value='${model.record.w2asa8}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2agr8" id="w2agr8" size="15" maxlength="13" value='${model.record.w2agr8}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="w2abl8" id="w2abl8" size="6" maxlength="6" value='${model.record.w2abl8}'></td>
												</tr>
											</table>
										
										</td>
										
									</tr>  <!-- End Artikelbesk / Avgift -->
		
									<tr> <!-- Tilleggsopplysningar -->
										<td width="50%"  valign="top">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.header.supplementsa"/></td>
												</tr>
											</table>			

											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr>
													<td>&nbsp;</td>
													<td  class="text12" align="left">
														<a tabindex="-1" id="kommref1IdLink">Ref.
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>
													</td>
												</tr>
												<tr> 
													<td class="text12" title="w2top"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2top"/></td>
													<td class="text12" title="w2cre"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2cre"/></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top1" id="w2top1" size="20" maxlength="17" value='${model.record.w2top1}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre1" id="w2cre1" size="3" maxlength="3" value='${model.record.w2cre1}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top2" id="w2top2" size="20" maxlength="17" value='${model.record.w2top2}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre2" id="w2cre2" size="3" maxlength="3" value='${model.record.w2cre2}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top3" id="w2top3" size="20" maxlength="17" value='${model.record.w2top3}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre3" id="w2cre3" size="3" maxlength="3" value='${model.record.w2cre3}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top4" id="w2top4" size="20" maxlength="17" value='${model.record.w2top4}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre4" id="w2cre4" size="3" maxlength="3" value='${model.record.w2cre4}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top5" id="w2top5" size="20" maxlength="17" value='${model.record.w2top5}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre5" id="w2cre5" size="3" maxlength="3" value='${model.record.w2cre5}'></td>
												</tr>
											</table>
										</td>
										
										<td width="50%" valign="top">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesadvare.header.supplementsb"/></td>
												</tr>
											</table>			

											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr>
													<td>&nbsp;</td>
													<td  class="text12" align="left">
														<a tabindex="-1" id="kommref2IdLink">Ref.
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>
													</td>
												</tr>
												<tr> 
													<td class="text12" title="w2top"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2top"/></td>
													<td class="text12" title="w2cre"><spring:message code="systema.main.maintenance.mainmaintenancesadvare.w2cre"/></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top6" id="w2top6" size="20" maxlength="17" value='${model.record.w2top6}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre6" id="w2cre6" size="3" maxlength="3" value='${model.record.w2cre6}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top7" id="w2top7" size="20" maxlength="17" value='${model.record.w2top7}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre7" id="w2cre7" size="3" maxlength="3" value='${model.record.w2cre7}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top8" id="w2top8" size="20" maxlength="17" value='${model.record.w2top8}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre8" id="w2cre8" size="3" maxlength="3" value='${model.record.w2cre8}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top9" id="w2top9" size="20" maxlength="17" value='${model.record.w2top9}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre9" id="w2cre9" size="3" maxlength="3" value='${model.record.w2cre9}'></td>
												</tr>
												<tr>
													<td><input type="text" class="inputTextMediumBlue" name="w2top10" id="w2top10" size="20" maxlength="17" value='${model.record.w2top10}'></td>
													<td><input type="text" class="inputTextMediumBlue" name="w2cre10" id="w2cre10" size="3" maxlength="3" value='${model.record.w2cre10}'></td>
												</tr>
											</table>
										
										</td>
										
									</tr>  <!-- End Tilleggsopplysningar -->


									<tr><td>&nbsp;</td></tr>
									<tr> 
										<td colspan="2" align="right">
											<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.save"/>'/>
										</td>
									</tr>
								</table>
			 	    		</form>
 	   	 		 		</td>
 	   	 		 	</tr>
 
 	   	 		 </table>
 	   	 		
 	   	 		</td>
 
 	   	 		<td width="30">
 	   	 			&nbsp;
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

