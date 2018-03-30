<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<script type="text/javascript" src="resources/js/mainmaintenancecundf_vareexp_se_edit.js?ver=${user.versionEspedsg}"></script>	
	<script type="text/javascript" src="resources/js/jquery.calculator.js"></script>	
	

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
							<c:if test="${kundeSessionParams.importNo == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMainMaintVareImpNoGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareimp_no.do">
											<font class="tabDisabledLinkMinor">&nbsp;
												  <spring:message code="systema.main.maintenance.customer.vareregister.imp.no"/>
											</font>&nbsp;						
									</a>
								</td>
							</c:if>
							<c:if test="${kundeSessionParams.exportDk == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<!-- 	<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareexp_dk.do">  -->
										<font class="tabDisabledLinkMinor">&nbsp;
											 <spring:message code="systema.main.maintenance.customer.vareregister.exp.dk"/>
										</font>&nbsp;						
								<!--  	</a> -->
								</td>							
							</c:if>
							<c:if test="${kundeSessionParams.importDk == true}">
								<td width="100" valign="bottom" class="tabDisabledSub" align="center" nowrap>
								<!--  	<a id="alinkMainMaintFritextGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareimp_dk.do"> -->
										<font class="tabDisabledLinkMinor">&nbsp;
											 <spring:message code="systema.main.maintenance.customer.vareregister.imp.dk"/>
										</font>&nbsp;						
								<!--  	</a> -->
								</td>
							</c:if>
							<td width="100" valign="bottom" class="tabSub" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;
										 <spring:message code="systema.main.maintenance.customer.vareregister.exp.se"/>
									</font>&nbsp;						
							</td>		
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
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Ändra&nbsp;</th>
								<th class="tableHeaderField" title="svew_knso">&nbsp;Sökbegrepp&nbsp;</th>
			                    <th class="tableHeaderField" title="svew_vasl">&nbsp;Varubeskrivning&nbsp;</th>
								<th class="tableHeaderField" title="svew_ulkd">&nbsp;Ursp.Land&nbsp;</th>
								<th class="tableHeaderField" title="svew_vata">&nbsp;Varukod&nbsp;</th>
								<th class="tableHeaderField" title="svew_brut">&nbsp;Bruttovikt&nbsp;</th>
								<th class="tableHeaderField" title="svew_neto">&nbsp;Nettovikt&nbsp;</th>
			                    <th align="center" class="tableHeaderField">&nbsp;Ta bort&nbsp;</th>
			                </tr>  
				             </thead> 
				             <tbody >  
					            <c:forEach var="record" items="${model.list}" varStatus="counter">   
					               <tr class="tableRow" height="20" >
						               <td id="recordUpdate_${record.svew_knnr}_${record.svew_knso}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
				               				<img src="resources/images/update.gif" border="0" alt="edit">
						               </td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.svew_knso}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_vasl}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_ulkd}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_vata}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_brut}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.svew_neto}&nbsp;</font></td>
						               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
				               				<a onclick="javascript:return confirm('Är du säker på att du vill ta  bort denna?')" tabindex=-1 href="mainmaintenancecundf_vareexp_se_edit.do?action=doDelete&svew_knnr=${record.svew_knnr}&svew_knso=${record.svew_knso}">
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
							<form action="mainmaintenancecundf_vareexp_se_edit.do" name="formRecord" id="formRecord" method="POST" >
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
												Skapa ny
											</button>&nbsp;&nbsp;
									</tr>
									<tr height="2">
										<td>&nbsp;</td>
									</tr>


									<tr> <!-- Huvudupplysningar -->
										<td colspan="2">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;
														Huvudupplysningar
													</td>
												</tr>
											</table>			

											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr> 
													<td width="74%" valign="top">
														<table border="0">
															<tr>
																<td class="text12" title="svew_knso">&nbsp;<font class="text14RedBold" >*</font>Sökbegrepp:</td>
																<td class="text12">
																	<input type="text"  required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svew_knso" id="svew_knso" size="35" maxlength="35" value='${model.record.svew_knso}'>
																	&nbsp;Ursprungsland:
																	<input type="text" class="inputTextMediumBlue" name="svew_ulkd" id="svew_ulkd" size="2" maxlength="2" value='${model.record.svew_ulkd}'>
																	<a tabindex="-1" id="svew_ulkdIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td class="text12" title="svew_vata">Varukod:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_vata" id="svew_vata" size="10" maxlength="8" value='${model.record.svew_vata}'>
																	<a tabindex="-1" id="svew_vataIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>	
															</tr>
															<tr>
																<td class="text12" title="svew_vasl">&nbsp;Varubeskr. 1:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vasl" id="svew_vasl" size="70" maxlength="70" value='${model.record.svew_vasl}'></td>
																<td class="text12" title="svew_eup1">Förf.(37:1):</td>
																<td>
																<input type="text" class="inputTextMediumBlue" name="svew_eup1" id="svew_eup1" size="5" maxlength="4" value='${model.record.svew_eup1}'>
																	<a tabindex="-1" id="svew_eup1IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
															</tr>
														</table>
													</td>
													
													<td width="26%" valign="top">
														<table border="0">
															<tr>
																<td class="text12" title="svew_brut">Bruttov.(kg):</td>
																<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="svew_brut" id="svew_brut" size="20" maxlength="18" value='${model.record.svew_brut}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_neto">Nettov.(kg):</td>
																<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="svew_neto" id="svew_neto" size="20" maxlength="18" value='${model.record.svew_neto}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_fabl">Varans pris:</td>
																<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="svew_fabl" id="svew_fabl" size="15" maxlength="14" value='${model.record.svew_fabl}'></td>
															</tr>
														</table>
													</td>
												</tr>

												<tr> 
													<td colspan="2" valign="top">
														<table border="0">
															<tr>
																<td colspan="2"  class="text12Gray">&nbsp;Bilagda handlingar</td>
															</tr>
															<tr>
																<td class="text12" title="svew_bit1">&nbsp;1. Kod:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
																<td class="text12">
																	<input type="text" class="inputTextMediumBlue" name="svew_bit1" id="svew_bit1" size="5" maxlength="4" value='${model.record.svew_bit1}'>
																	<a tabindex="-1" id="svew_bit1IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																	&nbsp;Identitet:
																	<input type="text" class="inputTextMediumBlue" name="svew_bii1" id="svew_bii1" size="35" maxlength="35" value='${model.record.svew_bii1}'>
																</td>
															</tr>
	
															<tr>
																<td colspan="4"  class="text12Gray">&nbsp;Särskilda upplysningar</td>
															</tr>															
															<tr>
																<td class="text12" title="svew_suko">1. Kod:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_suko" id="svew_suko" size="7" maxlength="5" value='${model.record.svew_suko}'></td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															<tr>
																<td class="text12" title="svew_sutx">1. Text:</td>
																<td colspan="2"><input type="text" class="inputTextMediumBlue" name="svew_sutx" id="svew_sutx" size="70" maxlength="70" value='${model.record.svew_sutx}'></td>
																<td>&nbsp;</td>
																<td>&nbsp;</td>
															</tr>
															
															<tr>
																<td colspan="4" class="text12Gray">&nbsp;Godsmärkning</td>
															</tr>
															<tr>	
																<td colspan="2" class="text12">&nbsp;1.
																	<input type="text" class="inputTextMediumBlue" name="svew_godm" id="svew_godm" size="45" maxlength="42" value='${model.record.svew_godm}'>
																	&nbsp;Kolli antal:
																	<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_kota" id="svew_kota" size="5" maxlength="5" value='${model.record.svew_kota}'>
																</td>
																<td class="text12">Kolli slag:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_kosl" id="svew_kosl" size="5" maxlength="4" value='${model.record.svew_kosl}'>
																	<a tabindex="-1" id="svew_koslIdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
															</tr>
	
															
														</table>
													</td>
												</tr>		
											</table>
										</td>
									</tr> <!-- End Huvudupplysningar -->

									<tr> <!-- Artikelinfo -->
										<td colspan="2">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;
														Artikelinfo
													</td>
												</tr>
											</table>			
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr> 
													<td width="55%" valign="top">
														<table border="0">
															<tr>
																<td class="text12" title="svew_vas2">&nbsp;Varubeskr. 2:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vas2" id="svew_vas2" size="70" maxlength="70" value='${model.record.svew_vas2}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_vas3">&nbsp;Varubeskr. 3:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vas3" id="svew_vas3" size="70" maxlength="70" value='${model.record.svew_vas3}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_vas4">&nbsp;Varubeskr. 4:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vas4" id="svew_vas4" size="70" maxlength="70" value='${model.record.svew_vas4}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_vas5">&nbsp;Varubeskr. 5:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vas5" id="svew_vas5" size="70" maxlength="70" value='${model.record.svew_vas5}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_komr">&nbsp;Komm. ref.nr:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_komr" id="svew_komr" size="70" maxlength="70" value='${model.record.svew_komr}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_call">&nbsp;Call me:</td>
																<td>
																	<select name="svew_call" id="svew_call" > 
						 							  					<option value="">-välj-</option>
										 								<option value="00" <c:if test="${model.record.svew_call == '00'}"> selected </c:if> >00-CM Inga exportrestr.</option>
														  				<option value="01" <c:if test="${model.record.svew_call == '01'}"> selected </c:if> >01-CM Exportrestr. villkor uppfyllda</option>
														  				<option value="10" <c:if test="${model.record.svew_call == '10'}"> selected </c:if> >10-CM Inga exportrestr. Call me</option>
														  				<option value="11" <c:if test="${model.record.svew_call == '11'}"> selected </c:if> >11-CM Exportrestr. villkor uppfyllda Call me</option>
														  				<option value="12" <c:if test="${model.record.svew_call == '12'}"> selected </c:if> >12-CM Exportrestr. villkor ej uppfyllda</option>
														  				<option value="13" <c:if test="${model.record.svew_call == '13'}"> selected </c:if> >13-CM Exportlicens</option>
														  				<option value="14" <c:if test="${model.record.svew_call == '14'}"> selected </c:if> >14-CM Exportlicens Call me</option>
														  				<option value="15" <c:if test="${model.record.svew_call == '15'}"> selected </c:if> >15-CM Ansökan om vissa Tullverkets tillstånd</option>
														  				<option value="16" <c:if test="${model.record.svew_call == '16'}"> selected </c:if> >16-CM Exp.bidragsärende som ska kompletteras. Kontakta exp.bidragsgrp. för klarering</option>
																  	</select>
																</td>
															</tr>
													</table>
													</td>
													
													<td width="45%" valign="top">
														<table border="0">
															<tr>
																<td class="text12" title="svew_betk">Betalkod:</td>
																<td>
													 				<select name="svew_betk" id="svew_betk" >
													 				  <option value="">-välj-</option>
																	  <option value="A"<c:if test="${model.record.svew_betk == 'A'}"> selected </c:if> >A</option>
																	  <option value="B"<c:if test="${model.record.svew_betk == 'B'}"> selected </c:if> >B</option>
																	  <option value="C"<c:if test="${model.record.svew_betk == 'C'}"> selected </c:if> >C</option>
																	  <option value="D"<c:if test="${model.record.svew_betk == 'D'}"> selected </c:if> >D</option>
																	  <option value="H"<c:if test="${model.record.svew_betk == 'H'}"> selected </c:if> >H</option>
																	  <option value="Y"<c:if test="${model.record.svew_betk == 'Y'}"> selected </c:if> >Y</option>
																	  <option value="Z"<c:if test="${model.record.svew_betk == 'Z'}"> selected </c:if> >Z</option>
																	</select>
																</td>
																<td class="text12" title="svew_ankv">&nbsp;Extra mängd:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_ankv" id="svew_ankv" size="10" maxlength="10" value='${model.record.svew_ankv}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_kono">Kont. nr.:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_kono" id="svew_kono" size="5" maxlength="3" value='${model.record.svew_kono}'></td>
																<td class="text12" title="svew_stva">&nbsp;Stat. värde:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_stva" id="svew_stva" size="12" maxlength="11" value='${model.record.svew_stva}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_atin">Indikator:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_atin" id="svew_atin" size="5" maxlength="3" value='${model.record.svew_atin}'></td>
																<td class="text12" title="svew_stva2">&nbsp;Tullvärde:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_stva2" id="svew_stva2" size="12" maxlength="11" value='${model.record.svew_stva2}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_vati">Varukod (33:3):</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vati" id="svew_vati" size="5" maxlength="4" value='${model.record.svew_vati}'></td>
																<td class="text12" title="svew_vano">&nbsp;Varupost nr:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_vano" id="svew_vano" size="5" maxlength="5" value='${model.record.svew_vano}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_vat4">Varukod (33:4):</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vat4" id="svew_vat4" size="5" maxlength="4" value='${model.record.svew_vat4}'></td>
																	<td class="text12" title="svew_fnkd">&nbsp;FN-kod:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_fnkd" id="svew_fnkd" size="5" maxlength="4" value='${model.record.svew_fnkd}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_vat5">Varukod (33:5):</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_vat5" id="svew_vat5" size="5" maxlength="4" value='${model.record.svew_vat5}'></td>
																<td class="text12" title="svew_eup2">&nbsp;Förf.(37:2):</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_eup2" id="svew_eup2" size="5" maxlength="4" value='${model.record.svew_eup2}'>
																	<a tabindex="-1" id="svew_eup2IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>

															</tr>
														</table>
													</td>
												</tr>
												<tr height="10">
													<td>&nbsp;</td>
												</tr>
												<tr>
													<td colspan="2"  class="text12">&nbsp;&nbsp;Identifiering av lager:
														<select name="svew_lagt" id="svew_lagt" > 
			 							  					<option value="">-välj-</option>
											  				<option value="C" <c:if test="${model.record.svew_lagt == 'C'}"> selected </c:if> >C-Privat tullager, typ C, där lagerhav har ansv för lagret.</option>
											  				<option value="D" <c:if test="${model.record.svew_lagt == 'D'}"> selected </c:if> >D-Privat tulllager, typ D, där lagerhav h ansv för lagret. Taxeringsgr fastställda</option>
											  				<option value="E" <c:if test="${model.record.svew_lagt == 'E'}"> selected </c:if> >E-Privat tulllager, typ E, där lagerhav har ansv för lagret. Kvalitetsäkrad förvar</option>
											  				<option value="F" <c:if test="${model.record.svew_lagt == 'F'}"> selected </c:if> >F-Tullager, typ F, drivs av Tullverket. Beslag - hävs/utlämnas från Tullv tulllage</option>
														</select>					
														&nbsp;Id:
														<input type="text" class="inputTextMediumBlue" name="svew_lagi" id="svew_lagi" size="15" maxlength="14" value='${model.record.svew_lagi}'>
														&nbsp;Landkod:
														<input type="text" class="inputTextMediumBlue" name="svew_lagl" id="svew_lagl" size="3" maxlength="2" value='${model.record.svew_lagl}'>
														<a tabindex="-1" id="svew_laglIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>
													</td>
												</tr>
												
												
												
											</table>
										</td>
									</tr>  <!-- End Artikelinfo -->

									<tr> <!-- Bilagda handlingar -->
										<td colspan="2">
										<div id="accordion">  
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;
														Bilagda handlingar
													</td>
												</tr>
											</table>			
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr> 
													<td width="50%" valign="top">
														<table border="0">
															<tr>
																<td class="text12" title="svew_bit2">2. Kod:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_bit2" id="svew_bit2" size="5" maxlength="4" value='${model.record.svew_bit2}'>
																	<a tabindex="-1" id="svew_bit2IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td class="text12" title="svew_bii2">&nbsp;Identitet:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_bii2" id="svew_bii2" size="35" maxlength="35" value='${model.record.svew_bii2}'></td>
															</tr>
															
															<tr>
																<td class="text12" title="svew_bit3">3. Kod:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_bit3" id="svew_bit3" size="5" maxlength="4" value='${model.record.svew_bit3}'>
																	<a tabindex="-1" id="svew_bit3IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td class="text12" title="svew_bii3">&nbsp;Identitet:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_bii3" id="svew_bii3" size="35" maxlength="35" value='${model.record.svew_bii3}'></td>
															</tr>
															
															<tr>
																<td class="text12" title="svew_bit4">4. Kod:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_bit4" id="svew_bit4" size="5" maxlength="4" value='${model.record.svew_bit4}'>
																	<a tabindex="-1" id="svew_bit4IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td class="text12" title="svew_bii4">&nbsp;Identitet:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_bii4" id="svew_bii4" size="35" maxlength="35" value='${model.record.svew_bii4}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_bit5">5. Kod:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_bit5" id="svew_bit5" size="5" maxlength="4" value='${model.record.svew_bit5}'>
																	<a tabindex="-1" id="svew_bit5IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td class="text12" title="svew_bii5">&nbsp;Identitet:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_bii5" id="svew_bii5" size="35" maxlength="35" value='${model.record.svew_bii5}'></td>
															</tr>

														</table>
													</td>
													<td width="50%" valign="top">
														<table border="0">
															<tr>
																<td class="text12" title="svew_bit6">6. Kod:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_bit6" id="svew_bit6" size="5" maxlength="4" value='${model.record.svew_bit6}'>
																	<a tabindex="-1" id="svew_bit6IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td class="text12" title="svew_bii6">&nbsp;Identitet:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_bii6" id="svew_bii6" size="35" maxlength="35" value='${model.record.svew_bii6}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_bit7">7. Kod:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_bit7" id="svew_bit7" size="5" maxlength="4" value='${model.record.svew_bit7}'>
																	<a tabindex="-1" id="svew_bit7IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td class="text12" title="svew_bii7">&nbsp;Identitet:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_bii7" id="svew_bii7" size="35" maxlength="35" value='${model.record.svew_bii7}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_bit8">8. Kod:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_bit8" id="svew_bit8" size="5" maxlength="4" value='${model.record.svew_bit8}'>
																	<a tabindex="-1" id="svew_bit8IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																
																</td>
																<td class="text12" title="svew_bii8">&nbsp;Identitet:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_bii8" id="svew_bii8" size="35" maxlength="35" value='${model.record.svew_bii8}'></td>
															</tr>
															
															<tr>
																<td class="text12" title="svew_bit9">9. Kod:</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_bit9" id="svew_bit9" size="5" maxlength="4" value='${model.record.svew_bit9}'>
																	<a tabindex="-1" id="svew_bit9IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td class="text12" title="svew_bii9">&nbsp;Identitet:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_bii9" id="svew_bii9" size="35" maxlength="35" value='${model.record.svew_bii9}'></td>
															</tr>

														</table>
													</td>
												</tr>
											</table>
					  					</div>  	
										</td>
									</tr>  <!-- End Bilagda handlingar -->
									
									<tr> <!-- Särskilda upplysningar -->
										<td colspan="2">
										<div id="accordion2">  
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">
														&nbsp;
														Särskilda upplysningar
													</td>
												</tr>
											</table>			
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr>
													<td width="50%" valign="top">
														<table>
															<tr>
																<td class="text12" title="svew_sut2">2. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sut2" id="svew_sut2" size="70" maxlength="70" value='${model.record.svew_sut2}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sut3">3. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sut3" id="svew_sut3" size="70" maxlength="70" value='${model.record.svew_sut3}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sut4">4. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sut4" id="svew_sut4" size="70" maxlength="70" value='${model.record.svew_sut4}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sut5">5. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sut5" id="svew_sut5" size="70" maxlength="70" value='${model.record.svew_sut5}'></td>
															</tr>
															<tr>
																<td colspan="4" class="text12" height="83">&nbsp;</td>
															</tr>
															<tr>
																<td class="text12" title="svew_suok">Övrig kostnad:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_suok" id="svew_suok" size="12" maxlength="11" value='${model.record.svew_suok}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sukr">Kassa:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_sukr" id="svew_sukr" size="12" maxlength="11" value='${model.record.svew_sukr}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_suar">Annan kostnad:</td>
																<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_suar" id="svew_suar" size="12" maxlength="11" value='${model.record.svew_suar}'></td>
															</tr>
														</table>
													</td>
													<td width="50%" valign="top">
														<table>
															<tr>
																<td class="text12" title="svew_suk6">2. Kod:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_suk6" id="svew_suk6" size="7" maxlength="5" value='${model.record.svew_suk6}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sut6">1. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sut6" id="svew_sut6" size="70" maxlength="70" value='${model.record.svew_sut6}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sut7">2. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sut7" id="svew_sut7" size="70" maxlength="70" value='${model.record.svew_sut7}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sut8">3. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sut8" id="svew_sut8" size="70" maxlength="70" value='${model.record.svew_sut8}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sut9">4. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sut9" id="svew_sut9" size="70" maxlength="70" value='${model.record.svew_sut9}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_suta">5. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_suta" id="svew_suta" size="70" maxlength="70" value='${model.record.svew_suta}'></td>
															</tr>
															<tr>
																<td colspan="4" class="text12" height="25">&nbsp;</td>
															</tr>
															<tr>
																<td class="text12" title="svew_sukb">3. Kod:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sukb" id="svew_sukb" size="7" maxlength="5" value='${model.record.svew_sukb}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sutb">1. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sutb" id="svew_sutb" size="70" maxlength="70" value='${model.record.svew_sutb}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sutc">2. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sutc" id="svew_sutc" size="70" maxlength="70" value='${model.record.svew_sutc}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sutd">3. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sutd" id="svew_sutd" size="70" maxlength="70" value='${model.record.svew_sutd}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sute">4. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sute" id="svew_sute" size="70" maxlength="70" value='${model.record.svew_sute}'></td>
															</tr>
															<tr>
																<td class="text12" title="svew_sutf">5. Text:</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_sutf" id="svew_sutf" size="70" maxlength="70" value='${model.record.svew_sutf}'></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>	
										</td>
									</tr>  <!-- End Särskilda upplysningar -->
		
									<tr> <!-- Tidigare handlingar -->
										<td colspan="2">
										<div id="accordion3">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">
														&nbsp;
														Tidigare handlingar
													</td>
												</tr>
											</table>			
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr>
													<td width="50%" valign="top">
														<table>
															<tr>
																<td class="text12" title="svew_tik1..9">&nbsp;&nbsp;&nbsp;Kategori:</td>
																<td class="text12" title="svew_tit1..9">Type:</td>
																<td class="text12" title="svew_tix1..9">Identitet:</td>
															</tr>
															<tr>
											           			<td class="text12">1.
											           				<select name="svew_tik1" id="svew_tik1" >
												 						<option value="">-välj-</option>
																  		<option value="X" <c:if test="${model.record.svew_tik1 == 'X'}"> selected </c:if> >X</option>
																  		<option value="Y" <c:if test="${model.record.svew_tik1 == 'Y'}"> selected </c:if> >Y</option>
																  		<option value="Z" <c:if test="${model.record.svew_tik1 == 'Z'}"> selected </c:if> >Z</option>
																  	</select>										           			
											           			</td>
																<td>
																<input type="text" class="inputTextMediumBlue" name="svew_tit1" id="svew_tit1" size="3" maxlength="3" value='${model.record.svew_tit1}'>
																	<a tabindex="-1" id="svew_tit1IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_tix1" id="svew_tix1" size="35" maxlength="35" value='${model.record.svew_tix1}'></td>
															</tr>
															<tr>
																<td class="text12">2.
											           				<select name="svew_tik2" id="svew_tik2" >
												 						<option value="">-välj-</option>
																  		<option value="X" <c:if test="${model.record.svew_tik2 == 'X'}"> selected </c:if> >X</option>
																  		<option value="Y" <c:if test="${model.record.svew_tik2 == 'Y'}"> selected </c:if> >Y</option>
																  		<option value="Z" <c:if test="${model.record.svew_tik2 == 'Z'}"> selected </c:if> >Z</option>
																  	</select>										           			
																</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_tit2" id="svew_tit2" size="3" maxlength="3" value='${model.record.svew_tit2}'>
																	<a tabindex="-1" id="svew_tit2IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_tix2" id="svew_tix2" size="35" maxlength="35" value='${model.record.svew_tix2}'></td>
															</tr>
															<tr>
																<td class="text12">3.
											           				<select name="svew_tik3" id="svew_tik3" >
												 						<option value="">-välj-</option>
																  		<option value="X" <c:if test="${model.record.svew_tik3 == 'X'}"> selected </c:if> >X</option>
																  		<option value="Y" <c:if test="${model.record.svew_tik3 == 'Y'}"> selected </c:if> >Y</option>
																  		<option value="Z" <c:if test="${model.record.svew_tik3 == 'Z'}"> selected </c:if> >Z</option>
																  	</select>										           			
																</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_tit3" id="svew_tit3" size="3"  maxlength="3" value='${model.record.svew_tit3}'>
																	<a tabindex="-1" id="svew_tit3IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_tix3" id="svew_tix3" size="35" maxlength="35" value='${model.record.svew_tix3}'></td>
															</tr>
															<tr>
																<td class="text12">4.
												           			<select name="svew_tik4" id="svew_tik4" >
												 						<option value="">-välj-</option>
																  		<option value="X" <c:if test="${model.record.svew_tik4 == 'X'}"> selected </c:if> >X</option>
																  		<option value="Y" <c:if test="${model.record.svew_tik4 == 'Y'}"> selected </c:if> >Y</option>
																  		<option value="Z" <c:if test="${model.record.svew_tik4 == 'Z'}"> selected </c:if> >Z</option>
																  	</select>										           			
																</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_tit4" id="svew_tit4" size="3" maxlength="3" value='${model.record.svew_tit4}'>
																	<a tabindex="-1" id="svew_tit4IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_tix4" id="svew_tix4" size="35" maxlength="35" value='${model.record.svew_tix4}'></td>
															</tr>
															<tr>
																<td class="text12">5.
												           			<select name="svew_tik5" id="svew_tik5" >
												 						<option value="">-välj-</option>
																  		<option value="X" <c:if test="${model.record.svew_tik5 == 'X'}"> selected </c:if> >X</option>
																  		<option value="Y" <c:if test="${model.record.svew_tik5 == 'Y'}"> selected </c:if> >Y</option>
																  		<option value="Z" <c:if test="${model.record.svew_tik5 == 'Z'}"> selected </c:if> >Z</option>
																  	</select>										           			
																
																
																</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_tit5" id="svew_tit5" size="3" maxlength="3" value='${model.record.svew_tit5}'>
																	<a tabindex="-1" id="svew_tit5IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_tix5" id="svew_tix5" size="35" maxlength="35" value='${model.record.svew_tix5}'></td>
															</tr>
																	
														</table>
													</td>
													<td width="50%" valign="top">
														<table>
															<tr>
																<td class="text12" title="svew_tik1..9">&nbsp;&nbsp;&nbsp;Kategori:</td>
																<td class="text12" title="svew_tit1..9">Type:</td>
																<td class="text12" title="svew_tix1..9">Identitet:</td>
															</tr>
															<tr>
																<td class="text12">6.
												           			<select name="svew_tik6" id="svew_tik6" >
												 						<option value="">-välj-</option>
																  		<option value="X" <c:if test="${model.record.svew_tik6 == 'X'}"> selected </c:if> >X</option>
																  		<option value="Y" <c:if test="${model.record.svew_tik6 == 'Y'}"> selected </c:if> >Y</option>
																  		<option value="Z" <c:if test="${model.record.svew_tik6 == 'Z'}"> selected </c:if> >Z</option>
																  	</select>										           			
																</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_tit6" id="svew_tit6" size="3" maxlength="3" value='${model.record.svew_tit6}'>
																	<a tabindex="-1" id="svew_tit6IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_tix6" id="svew_tix6" size="35" maxlength="35" value='${model.record.svew_tix6}'></td>
															</tr>
															<tr>
																<td class="text12">7.
												           			<select name="svew_tik7" id="svew_tik7" >
												 						<option value="">-välj-</option>
																  		<option value="X" <c:if test="${model.record.svew_tik7 == 'X'}"> selected </c:if> >X</option>
																  		<option value="Y" <c:if test="${model.record.svew_tik7 == 'Y'}"> selected </c:if> >Y</option>
																  		<option value="Z" <c:if test="${model.record.svew_tik7 == 'Z'}"> selected </c:if> >Z</option>
																  	</select>										           			
																</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_tit7" id="svew_tit7" size="3" maxlength="3" value='${model.record.svew_tit7}'>
																	<a tabindex="-1" id="svew_tit7IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_tix7" id="svew_tix7" size="35" maxlength="35" value='${model.record.svew_tix7}'></td>
															</tr>
															<tr>
																<td class="text12">8.
												           			<select name="svew_tik8" id="svew_tik8" >
												 						<option value="">-välj-</option>
																  		<option value="X" <c:if test="${model.record.svew_tik8 == 'X'}"> selected </c:if> >X</option>
																  		<option value="Y" <c:if test="${model.record.svew_tik8 == 'Y'}"> selected </c:if> >Y</option>
																  		<option value="Z" <c:if test="${model.record.svew_tik8 == 'Z'}"> selected </c:if> >Z</option>
																  	</select>										           			
																</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_tit8" id="svew_tit8" size="3" maxlength="3" value='${model.record.svew_tit8}'>
																	<a tabindex="-1" id="svew_tit8IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_tix8" id="svew_tix8" size="35" maxlength="35" value='${model.record.svew_tix8}'></td>
															</tr>
															<tr>
																<td class="text12">9.
											           				<select name="svew_tik9" id="svew_tik9" >
												 						<option value="">-välj-</option>
																  		<option value="X" <c:if test="${model.record.svew_tik9 == 'X'}"> selected </c:if> >X</option>
																  		<option value="Y" <c:if test="${model.record.svew_tik9 == 'Y'}"> selected </c:if> >Y</option>
																  		<option value="Z" <c:if test="${model.record.svew_tik9 == 'Z'}"> selected </c:if> >Z</option>
																  	</select>										           			
																</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="svew_tit9" id="svew_tit9" size="3" maxlength="3" value='${model.record.svew_tit9}'>
																	<a tabindex="-1" id="svew_tit9IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																	</a>
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="svew_tix9" id="svew_tix9" size="35" maxlength="35" value='${model.record.svew_tix9}'></td>
															</tr>
															<tr>
																<td colspan="3" class="text12" height="25">&nbsp;</td>
															</tr>
														
														</table>
													</td>
												</tr>	
											</table>
										</div>	
										</td>
									</tr>  <!-- End Tidigare handlingar -->

									<tr> <!-- Godsmärkning / container -->
										<td width="50%"  valign="top">
										<div id="accordion-half1">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">
														&nbsp;
														Godsmärkning
													</td>
												</tr>
											</table>			

											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr>
													<td class="text12" title="svew_god1..5">&nbsp;&nbsp;&nbsp;Godsmärkning:</td>
													<td class="text12" title="svew_kot1..5">Kolli antal:</td>
													<td class="text12" title="svew_kos1..5">Kolli slag [kod]:</td>
												</tr>
												<tr>
													<td class="text12">2.<input type="text" class="inputTextMediumBlue" name="svew_god2" id="svew_god2" size="45" maxlength="42" value='${model.record.svew_god2}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_kot2" id="svew_kot2" size="5" maxlength="5" value='${model.record.svew_kot2}'></td>
													<td>
														<input type="text" class="inputTextMediumBlue" name="svew_kos2" id="svew_kos2" size="5" maxlength="4" value='${model.record.svew_kos2}'>
														<a tabindex="-1" id="svew_kos2IdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>
													</td>
												</tr>
												<tr>
													<td class="text12">3.<input type="text" class="inputTextMediumBlue" name="svew_god3" id="svew_god3" size="45" maxlength="42" value='${model.record.svew_god3}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_kot3" id="svew_kot3" size="5" maxlength="5" value='${model.record.svew_kot3}'></td>
													<td>
														<input type="text" class="inputTextMediumBlue" name="svew_kos3" id="svew_kos3" size="5" maxlength="4" value='${model.record.svew_kos3}'>
														<a tabindex="-1" id="svew_kos3IdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>
													</td>
												</tr>
												<tr>
													<td class="text12">4.<input type="text" class="inputTextMediumBlue" name="svew_god4" id="svew_god4" size="45" maxlength="42" value='${model.record.svew_god4}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_kot4" id="svew_kot4" size="5" maxlength="5" value='${model.record.svew_kot4}'></td>
													<td>
														<input type="text" class="inputTextMediumBlue" name="svew_kos4" id="svew_kos4" size="5" maxlength="5" value='${model.record.svew_kos4}'>
														<a tabindex="-1" id="svew_kos4IdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>
													</td>
												</tr>
												<tr>
													<td class="text12">5.<input type="text" class="inputTextMediumBlue" name="svew_god5" id="svew_god5" size="45" maxlength="42" value='${model.record.svew_god5}'></td>
													<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="svew_kot5" id="svew_kot5" size="5" maxlength="5" value='${model.record.svew_kot5}'></td>
													<td>
														<input type="text" class="inputTextMediumBlue" name="svew_kos5" id="svew_kos5" size="5" maxlength="5" value='${model.record.svew_kos5}'>
														<a tabindex="-1" id="svew_kos5IdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
														</a>
													</td>
												</tr>
												<tr>
													<td colspan="3" class="text12" height="76">&nbsp;</td>
												</tr>
											</table>
										</div>
										</td>
										<td width="50%" valign="top">
											<div id="accordion-half2">
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">
													&nbsp;
													Container
													</td>
												</tr>
											</table>			
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr>
													<td class="text12" title="svew_co01..20">&nbsp;&nbsp;&nbsp;&nbsp;Container nr:</td>
												</tr>
												<tr>
													<td class="text12">&nbsp;&nbsp;1.<input type="text" class="inputTextMediumBlue" name="svew_co01" id="svew_co01" size="20" maxlength="17" value='${model.record.svew_co01}'></td>
													<td class="text12">&nbsp;&nbsp;2.<input type="text" class="inputTextMediumBlue" name="svew_co02" id="svew_co02" size="20" maxlength="17" value='${model.record.svew_co02}'></td>
													<td class="text12">&nbsp;&nbsp;3.<input type="text" class="inputTextMediumBlue" name="svew_co03" id="svew_co03" size="20" maxlength="17" value='${model.record.svew_co03}'></td>
												</tr>
												<tr>
													<td class="text12">&nbsp;&nbsp;4.<input type="text" class="inputTextMediumBlue" name="svew_co04" id="svew_co04" size="20" maxlength="17" value='${model.record.svew_co04}'></td>
													<td class="text12">&nbsp;&nbsp;5.<input type="text" class="inputTextMediumBlue" name="svew_co05" id="svew_co05" size="20" maxlength="17" value='${model.record.svew_co05}'></td>
													<td class="text12">&nbsp;&nbsp;6.<input type="text" class="inputTextMediumBlue" name="svew_co06" id="svew_co06" size="20" maxlength="17" value='${model.record.svew_co06}'></td>
												</tr>
												<tr>
													<td class="text12">&nbsp;&nbsp;7.<input type="text" class="inputTextMediumBlue" name="svew_co07" id="svew_co07" size="20" maxlength="17" value='${model.record.svew_co07}'></td>
													<td class="text12">&nbsp;&nbsp;8.<input type="text" class="inputTextMediumBlue" name="svew_co08" id="svew_co08" size="20" maxlength="17" value='${model.record.svew_co08}'></td>
													<td class="text12">&nbsp;&nbsp;9.<input type="text" class="inputTextMediumBlue" name="svew_co09" id="svew_co09" size="20" maxlength="17" value='${model.record.svew_co09}'></td>
												</tr>
												<tr>
													<td class="text12">10.<input type="text" class="inputTextMediumBlue" name="svew_co10" id="svew_co10" size="20" maxlength="17" value='${model.record.svew_co10}'></td>
													<td class="text12">11.<input type="text" class="inputTextMediumBlue" name="svew_co11" id="svew_co11" size="20" maxlength="17" value='${model.record.svew_co11}'></td>
													<td class="text12">12.<input type="text" class="inputTextMediumBlue" name="svew_co12" id="svew_co12" size="20" maxlength="17" value='${model.record.svew_co12}'></td>
												</tr>
												<tr>
													<td class="text12">13.<input type="text" class="inputTextMediumBlue" name="svew_co13" id="svew_co13" size="20" maxlength="17" value='${model.record.svew_co13}'></td>
													<td class="text12">14.<input type="text" class="inputTextMediumBlue" name="svew_co14" id="svew_co14" size="20" maxlength="17" value='${model.record.svew_co14}'></td>
													<td class="text12">15.<input type="text" class="inputTextMediumBlue" name="svew_co15" id="svew_co15" size="20" maxlength="17" value='${model.record.svew_co15}'></td>
												</tr>
												<tr>
													<td class="text12">16.<input type="text" class="inputTextMediumBlue" name="svew_co16" id="svew_co16" size="20" maxlength="17" value='${model.record.svew_co16}'></td>
													<td class="text12">17.<input type="text" class="inputTextMediumBlue" name="svew_co17" id="svew_co17" size="20" maxlength="17" value='${model.record.svew_co17}'></td>
													<td class="text12">18.<input type="text" class="inputTextMediumBlue" name="svew_co18" id="svew_co18" size="20" maxlength="17" value='${model.record.svew_co18}'></td>
												</tr>
												<tr>
													<td class="text12">19.<input type="text" class="inputTextMediumBlue" name="svew_co19" id="svew_co19" size="20" maxlength="17" value='${model.record.svew_co19}'></td>
													<td class="text12">20.<input type="text" class="inputTextMediumBlue" name="svew_co20" id="svew_co20" size="20" maxlength="17" value='${model.record.svew_co20}'></td>
												</tr>
											</table>
										</div>
										</td>
									</tr>  <!-- End Godsmärkning / container -->

									<tr><td>&nbsp;</td></tr>
									
									<tr> 
										<td colspan="2" align="right">
											<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spara'/>
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

