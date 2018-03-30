<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancecundf_vareexp_no_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
							<td width="100" valign="bottom" class="tabSub" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;
									   <spring:message code="systema.main.maintenance.customer.vareregister.exp.no"/>
									</font>
							</td>
	
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
								<th align="center" width="2%" class="tableHeaderField" >&nbsp;Endre&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Varenr.&nbsp;</th>
			                    <th class="tableHeaderField" >&nbsp;Beskrivelse&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;R31&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;L/F&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Tariffnr.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Tn&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Pref.&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Vekt&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;PVA&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Tollsats&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;MF&nbsp;</th>
								<th class="tableHeaderField" >&nbsp;Avgift&nbsp;</th>
								<th align="center" class="tableHeaderField">Slett</th>
			                </tr>  
				             </thead> 
				             <tbody >  
					            <c:forEach var="record" items="${model.list}" varStatus="counter">   
					               <tr class="tableRow" height="20" >
						               <td id="recordUpdate_${record.slalfa}_${record.slknr}" onClick="getRecord(this);" align="center" width="2%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
				               				<img src="resources/images/update.gif" border="0" alt="edit">
						               </td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text12">&nbsp;${record.slalfa}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sltxt}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.r31}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sloppl}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sltanr}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.sltn}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.pref}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.slvekt}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.slpva}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.slsats}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.mf}&nbsp;</font></td>
						               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text12">&nbsp;${record.slkdae}&nbsp;${record.slkdse}</font></td>
						               
						               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
				               				<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="mainmaintenancecundf_vareexp_no_edit.do?action=doDelete&id=${model.dbTable}&slalfa=${record.slalfa}&slknr=${record.slknr}">
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
							<form action="mainmaintenancecundf_vareexp_no_edit.do" name="formRecord" id="formRecord" method="POST" >
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
									<tr>
										<td>
											<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="left">
												<tr>
													<td class="text12Bold">&nbsp;
														Artikelinfo
													</td>
												</tr>
											</table>			
											<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
												<tr> 
													<td width="50%" >
														<table border="0">
															<tr>
																<td class="text12" title="slalfa">
																	<font class="text14RedBold" >*</font>&nbsp;Varenr:
																</td>
																<td><input type="text"required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="slalfa" id="slalfa" size="30" maxlength="28" value='${model.record.slalfa}'></td>
																<td class="text12" title="sltxt">
																	<font class="text14RedBold" >*</font>&nbsp;Beskrivelse:
																</td>
																<td><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="sltxt" id="sltxt" size="20" maxlength="20" value='${model.record.sltxt}'></td>
															</tr>
															<tr>
																<td class="text12" title="r31">&nbsp;
																	R31:
																</td>
																<td>
																	<select name="r31" id="r31" >
								 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
								 					  					<option value="J"<c:if test="${model.record.r31 == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
													  					<option value="N"<c:if test="${ model.record.r31 == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
													  				</select>
																</td>
																<td class="text12" title="sloppl">
																	<font class="text14RedBold" >*</font>&nbsp;L/F:
																</td>
																<td><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="sloppl" id="sloppl" size="3" maxlength="2" value='${model.record.sloppl}'></td>
															</tr>
															<tr>
																<td class="text12" title="sltanr">
																	<font class="text14RedBold" >*</font>&nbsp;Tariffnr:
																</td>
																<td>
																	<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="sltanr" id="sltanr" size="10" maxlength="8" value='${model.record.sltanr}'>
																</td>
																<td class="text12" title="sltn">&nbsp;
																	Tn:
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="sltn" id="sltn" size="10" maxlength="7" value='${model.record.sltn}'></td>
					
															</tr>
															<tr>
																<td class="text12" title="SLTO">&nbsp;
																	Till.Opplysn.:
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="slto" id="slto" size="30" maxlength="45" value='${model.record.slto}'></td>
																<td class="text12" title="SLCREF">&nbsp;
																	Ref.:
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="slcref" id="slcref" size="4" maxlength="3" value='${model.record.slcref}'></td>
															</tr>
														</table>
													</td>
													<td width="50%" valign="top">
														<table border="0">
															<tr>
																<td class="text12" title="pref">
																	Pref:
																</td>
																<td>
																	<select name="pref" id="pref">
												 						<option value="">-velg-</option>
													 				  	<c:forEach var="pref" items="${model.kalle}" >
													 				  		<option value="${pref.ks6pre}"<c:if test="${model.record.pref == pref.ks6pre}"> selected </c:if> >${pref.ks6pre}</option>
																		</c:forEach>  
																	</select>						
																</td>
																<td class="text12" title="slvekt">&nbsp;
																	Vekt:
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="slvekt" id="slvekt" size="15" maxlength="12" value='${model.record.slvekt}'>
																</td>
															</tr>
															<tr>
																<td class="text12" title="slpva">
																	PVA:
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="slpva" id="slpva" size="2" maxlength="1" value='${model.record.slpva}'></td>
																<td class="text12" title="slsats">&nbsp;
																	Tollsats:
																</td>
																<td><input type="text" class="inputTextMediumBlue" name="slsats" id="slsats" size="8" maxlength="7" value='${model.record.slsats}'></td>
															</tr>
															<tr>
																<td class="text12" title="mf">
																	MF:
																</td>
																<td>
																	<select name="mf" id="mf" >
								 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
								 					  					<option value="F"<c:if test="${model.record.mf == 'F'}"> selected </c:if>>F</option>
													  				</select>
																</td>
																<td class="text12" title="slkdae/slkdse:">&nbsp;
																	Avgift(kode/sekv):
																</td>
																<td>
																	<input type="text" class="inputTextMediumBlue" name="slkdae" id="slkdae" size="3" maxlength="2" value='${model.record.slkdae}'>
																	<input type="text" class="inputTextMediumBlue" name="slkdse" id="slkdse" size="4" maxlength="3" value='${model.record.slkdse}'>
																</td>
															</tr>
															<tr>
																<td colspan="4">&nbsp;</td>
															</tr>
														</table>
													</td>
												</tr>
												
											</table>

										<tr><td>&nbsp;</td></tr>
										<tr> 
											<td align="right">
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

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

