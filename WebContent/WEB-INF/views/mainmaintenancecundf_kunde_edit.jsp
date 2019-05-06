
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
<script type="text/javascript" src="resources/js/mainmaintenancecundf_kunde_edit.js?ver=${user.versionEspedsg}"></script>	
	
<style type = "text/css">

.ui-datepicker { font-size:9pt;}

#accordionVADR .ui-accordion-header {
	display: block;
	cursor: pointer;
	position: relative;
	margin: 2px 0 0 0;
	padding: .0em .0em .0em .0em;
	font-size: 100%;	
	width: 96%;
}

#accordionVADR .ui-state-default  {
	border: 1px solid #cccccc;
	background: white;
	font-size: 12px; 
}

#accordionVADR .ui-state-active {
	border:1px solid #F3D5BD;
	background-color:#EEEEEE;
	
}


#accordionVADR .ui-accordion-content  {
	padding: 0em 0.0em;
    width: 100%;
  	color: #777;
 	font-size: 12px; 
}


</style>
	
	
<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
			<tr height="25"> 
				<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkMainMaintGate" onClick="setBlockUI(this);" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
					<font class="tabDisabledLink">&nbsp;
						<spring:message code="systema.main.maintenance.label"/>
					</font>
					<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
					</a>
				</td>
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="15%" valign="bottom" class="tabDisabled" align="center">
					<a id="alinkMainCundfGate" onClick="setBlockUI(this);" tabindex=-1 style="display:block;" href="mainmaintenancecundf_vkund.do">
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
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
			
 	   	 	<tr> <!-- Second tab row... -->
 	   	 		<td>&nbsp;</td>
 	   	 	    <td>
 					<table class="formFrameHeaderTransparent" style="width:100%" cellspacing="0" border="0" cellpadding="0">
						<tr height="20"> 
								<td width="120" valign="bottom" class="tabSub" align="center" nowrap>
									<font class="tabLinkMinor">&nbsp;
										<spring:message code="systema.main.maintenance.customer"/>
									</font>
								</td>
	
						 <c:if test="${not empty kundeSessionParams.kundnr}">
	
								<td width="120" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMainMaintKontaktGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_kontaktpersoner_list.do">
										<font class="tabDisabledLinkMinor">&nbsp;
											<spring:message code="systema.main.maintenance.customer.contacts"/>
										</font>&nbsp;						
									</a>
								</td>
								
								<td width="120" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMainMaintFritextGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_fritekst_edit.do">
										<font class="tabDisabledLinkMinor">&nbsp;
											<spring:message code="systema.main.maintenance.customer.text"/>
										</font>&nbsp;						
									</a>
								</td>
								<td width="120" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMainMaintParamsGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_params_list.do">
										<font class="tabDisabledLinkMinor">&nbsp;
											<spring:message code="systema.main.maintenance.customer.params"/>
										</font>&nbsp;						
									</a>
								</td>
								<td width="120" valign="bottom" class="tabDisabledSub" align="center" nowrap>
									<a id="alinkMainMaintVareRegGate" onClick="setBlockUI(this);" href="mainmaintenancecundf_vareregister.do">
										<font class="tabDisabledLinkMinor">&nbsp;
											<spring:message code="systema.main.maintenance.customer.vareregister"/>
										</font>&nbsp;						
									</a>
								</td>
		
						 </c:if>
		
							 	<td width="590" class="tabFantomSpace" align="center" nowrap></td>

						</tr>
	
	
					</table>
				</td>
 	   	 	</tr> <!-- End second tab row -->
 
 	    	<tr>
				<td>&nbsp;</td>
				<td width="100%">
					<form action="mainmaintenancecundf_kunde_edit.do" name="formRecord" id="formRecord" method="POST" >
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="action" id=action value="${model.action}">
						<input type="hidden" name="dirty" id=dirty value="">
						<table class="tabThinBorderWhite" width="100%" cellspacing="0" border="0" align="left">
							<tr>
								<td width="50%" valign="top">
									<table>
								<c:if test="${model.action == 'doCreate'}">
									<c:if test="${model.invoiceCustomerAllowed == 'J'}">
										<tr>
											<td class="text14" title="knavn">&nbsp;<font class="text14RedBold" >*</font>
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.customertype"/>:
											</td>
											<td>
												<select required name="kundetype" id="kundetype" class="inputTextMediumBlueMandatoryField">
			  										<option value="F"<c:if test="${model.record.kundetype == 'F'}"> selected </c:if>>
			  											<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.customertype.invoice"/>
			  										</option>
				  									<option value="A"<c:if test="${model.record.kundetype == 'A'}"> selected </c:if>>
				  										<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.customertype.address"/>
				  									</option>
								  				</select>
											</td>
										</tr>
									</c:if>		
								</c:if>
										<tr>
											<td class="text14" title="kundnr">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.customernr"/>:
											</td>
											<c:choose>
												<c:when test="${model.action == 'doCreate'}">
													<td><input type="text" class="inputTextMediumBlue"  name="kundnr" id="kundnr" size="10" maxlength="8" value='${model.record.kundnr}'></td>
												</c:when>
												<c:otherwise>
													<td>
														<input readonly type="text" class="inputTextReadOnly"  name="kundnr" id="kundnr" size="10" maxlength="8" value='${model.record.kundnr}'>
														<c:if test="${model.isAdressCustomer == 'J'}">
										 					&nbsp;<img onMouseOver="showPop('kundnr_info');" onMouseOut="hidePop('kundnr_info');"style="vertical-align:center;" width="12px" height="12px" src="resources/images/info4.png" border="0" alt="info">
											 				<div class="text11" style="position: relative;" align="left">
											 				<span style="position:absolute; top:2px; width:250px;" id="kundnr_info" class="popupWithInputText text11"  >
											           		<b>
											           			<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.customernr"/>
											 	          	</b><br><br>
																Kunde er registrert som addressekunde.
															<br><br>
															</span>
															</div>
														</c:if>												
													</td>
												</c:otherwise>
											</c:choose>
											
											<td class="text14" title="syrg">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.orgnr"/>:
												<c:if test="${user.filand == 'NO'}">
								 					&nbsp;<img onMouseOver="showPop('orgnr_info');" onMouseOut="hidePop('orgnr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 				<div class="text11" style="position: relative;" align="left">
									 				<span style="position:absolute; top:2px; width:250px;" id="orgnr_info" class="popupWithInputText text11"  >
											           		<b>
											           			<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.orgnr"/>
											 	          	</b><br><br>
											           			Hvis nedlastningknappen till høyre brukes , og addressinformation mangler, lastes information om adresse fra Brønnøysundregistrene.
															<br><br>
																Hvis Org.nr finnes i ELMA, settes <b>Faktura sendes som EHF</b> til Ja.
															<br><br>
											           		<b>
											           			<img style="vertical-align:bottom;" width="150px" height="25px" src="http://scf.brreg.no/bilder/brreg_logo.svg">
											 	          	</b><br><br>
											 	          		Fylles altid med data direkte fra Brønnøysundregistrene hvis nedlastningknappen till høyre brukes.<br>
											 	          	<br>
											 	          		(stenges ved og klikke)
											 	          	<br>
													</span>
													</div>
		
													<div id="existOrgnrDialog" title="Org.nr">
													  <p>Finnes registret på annen kunde. <br><br>
													  Det går fortsatt og lagre.</p>
													</div>
													
												</c:if>
											</td>
											<td>
												<input type="text" class="inputTextMediumBlue" onBlur="validateOrgnr();" name="syrg" id="syrg" size="15" maxlength="14" value='${model.record.syrg}'>
												<c:if test="${model.orgNrMulti == 'J'}">
								 					&nbsp;<img onMouseOver="showPop('syrg_info');" onMouseOut="hidePop('syrg_info');"style="vertical-align:center;" width="12px" height="12px" src="resources/images/warning.png" border="0" alt="info">
									 				<div class="text11" style="position: relative;" align="left">
									 				<span style="position:absolute; top:2px; width:250px;" id="syrg_info" class="popupWithInputTextGrayBg text11"  >
									           		<b>
									           			<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.orgnr"/>
									 	          	</b><br><br>
														Finnes registret på annen kunde.
													<br><br>
													</span>
													</div>
												</c:if>
												
											</td>
											<td>
												<c:if test="${user.filand == 'NO'}">
													<a tabindex="-1" id="brregLink" onClick="getDataFromBrreg(this);">
														&nbsp;<img style="cursor:pointer;vertical-align:center;" src="resources/images/request.png" width="22px" height="22px" border="0" title="Last ned data fra Brønnøysundregistrene." >
													</a> 
												</c:if>
											</td>
											
										</tr>
										<tr>
											<td class="text14" title="knavn">&nbsp;<font class="text14RedBold" >*</font>
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.name"/>:
											</td>
											<td><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="knavn" id="knavn" size="30" maxlength="30" value='${model.record.knavn}'></td>
											<td class="text14" title="sonavn">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.sonavn"/>:
											</td>
											<td>
												<input type="text" class="inputTextMediumBlue" name="sonavn" id="sonavn" size="12" maxlength="10" value='${model.record.sonavn}'>
											</td>
										</tr>
										<tr>
											<td class="text14" title="adr1">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.address"/>:
								 					&nbsp;<img onMouseOver="showPop('adr1_info');" onMouseOut="hidePop('adr1_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 				<div class="text11" style="position: relative;" align="left">
									 				<span style="position:absolute; top:2px; width:250px;" id="adr1_info" class="popupWithInputText text11"  >
											           		<b>
											           			<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.address"/>
											 	          	</b><br><br>
											Merk at adresse 2 IKKE overføres til stykkgodsfraktbrev.<br>
											På norske kunder bør derfor gateadresse alltid ligge i adresse 1.<br>
											Om ønskelig kan en legge postadresse i adresse 2 (når denne avviker fra gate adresse - Se også felt "Postnr v/postboks").<br>
											På den måten sparer en arbeidet med å opprette en egen varaadresse å kunden.<br>
											(Vareadresse nr 1 = kundens hente/leveringsadresse - vil overstyre i oppdrag  hvis den finnes)<br> 
													</span>
													</div>
											</td>
											<td><input type="text" class="inputTextMediumBlue" name="adr1" id="adr1" size="30" maxlength="30" value='${model.record.adr1}'></td>
											<td class="text14" title="adr2">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.postbox"/>:
							 					&nbsp;<img onMouseOver="showPop('adr2_info');" onMouseOut="hidePop('adr2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<div class="text11" style="position: relative;" align="left">
								 				<span style="position:absolute; top:2px; width:250px;" id="adr2_info" class="popupWithInputText text11"  >
										           		<b>
										           			<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.postbox"/>
										 	          	</b><br><br>
											Merk at adresse 2 IKKE overføres til stykkgodsfraktbrev. <br>
											På norske kunder bør derfor gateadresse alltid ligge i adresse 1.<br>
											Om ønskelig kan en legge postadresse i adresse 2 (når denne avviker fra gate adresse - Se også felt "Postnr v/postboks").<br>
											På den måten sparer en arbeidet med å opprette en egen varaadresse å kunden.<br>
											(Vareadresse nr 1 = kundens hente/leveringsadresse - vil overstyre i oppdrag  hvis den finnes)<br> 
												</span>
												</div>
											</td>
											<td><input type="text" class="inputTextMediumBlue" name="adr2" id="adr2" size="25" maxlength="30" value='${model.record.adr2}'>
											</td>
										</tr>
										<tr>
											<td class="text14" title="postnr">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.postnr"/>:
											</td>
											<td class="text14">
												<input type="text" onBlur="setPoststed();" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="postnr" id="postnr" size="5" maxlength="4" value='${model.record.postnr}'>
												&nbsp;&nbsp;(utl.):&nbsp;
							 					<img style="vertical-align:center;" width="12px" height="12px" src="resources/images/info3.png" title="Postnummer ved utenlandsk kunde">
												<input type="text" class="inputTextMediumBlue" name="sypoge" id="sypoge" size="10" maxlength="9" value='${model.record.sypoge}'>
												<c:if test="${model.hasSypogeAndNO == 'J'}">
								 					&nbsp;<img onMouseOver="showPop('sypoge_info');" onMouseOut="hidePop('sypoge_info');"style="vertical-align:center;" width="12px" height="12px" src="resources/images/warning.png" border="0" alt="info">
									 				<div class="text11" style="position: relative;" align="left">
									 				<span style="position:absolute; top:2px; width:250px;" id="sypoge_info" class="popupWithInputTextGrayBg text11"  >
									           		<b>
									           			<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.sypoge"/>
									 	          	</b><br><br>
														Ved norsk kunde kan ikke utenlandsk postnummer benyttes.
													<br><br>
													</span>
													</div>
												</c:if>			
											</td>

											<td class="text14">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.postboxnr"/>:
							 					&nbsp;<img onMouseOver="showPop('pnpbku_info');" onMouseOut="hidePop('pnpbku_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<div class="text11" style="position: relative;" align="left">
								 				<span style="position:absolute; top:2px; width:250px;" id="pnpbku_info" class="popupWithInputText text11"  >
										           		<b>
														Postnummer ved postboks:
										 	          	</b><br><br>
														Hvis utfylt benyttes dette kun ved print av faktura og lignende dokumenter. <br>
														Kan benyttes når en ønsker avvikende postnummer på postadresse i forhold til gateadresse.<br> 
														(uansett om årsaken er postboks eller annet - Så lenge poststed er samme) 
														<br><br>
												</span>
												</div>
											</td>
											<td>
												<input type="text" class="inputTextMediumBlue" name="pnpbku" id="pnpbku" size="10" maxlength="10" value='${model.record.pnpbku}'>
											</td>
										</tr>

										<tr>
											<td class="text14">&nbsp;<font class="text14RedBold" >*</font>
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.adr3"/>:
											</td>

											<td><input type="text" required class="inputTextMediumBlueMandatoryField" name="adr3" id="adr3" size="25" maxlength="24" value='${model.record.adr3}'></td>

											<td class="text14">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.landcode"/>:

							 					&nbsp;<img onMouseOver="showPop('landkode_info');" onMouseOut="hidePop('landkode_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<div class="text11" style="position: relative;" align="left">
								 				<span style="position:absolute; top:2px; width:250px;" id="landkode_info" class="popupWithInputText text11">
								           		<b>
								           			<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.landcode"/>
								 	          	</b><br><br>
													Er obligatorisk hvis Visma.net Financials brukes.<br><br>
													(settes til NO hvis feltet ikke fylles i og <spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.postnr"/> er norsk.)
												<br><br>
												</span>
												</div>
											</td>
											
											<td class="text14">								
											    <input type="text" class="inputTextMediumBlue" name="syland" id="syland" size="4" maxlength="2" value='${model.record.syland}'>
												<a tabindex="-1" id="sylandIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
												</a>
												
												&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.language"/>:
												&nbsp;<select name="spraak" id="spraak" > 
			 					  					<option value="N"<c:if test="${model.record.spraak == ''}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.language.norway"/></option>
								  					<option value="E"<c:if test="${ model.record.spraak == 'E'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.language.england"/></option>
								  					<option value="T"<c:if test="${ model.record.spraak == 'T'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.language.germany"/></option>
								  				</select>
												
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<div id="accordionVADR">
													<table style="padding-left: 1.0em">
														<tr>
															<td class="text14" width="50%">
																&nbsp;&nbsp;&nbsp;Vareadresse nr 1 
															</td>
															<td width="35%"></td>
															<td width="15%" align="right" style="text-decoration: underline">
																<c:choose>
																	<c:when test="${model.hasVareAddresseNr1 == 'J'}">
																		Vis&nbsp;frem
																	</c:when>
																	<c:otherwise>
																		Lag&nbsp;ny
																	</c:otherwise>
																</c:choose>
															</td>
														</tr>
													</table>			
													<table>
														<tr>
															<td class="text14" title="knavn">
																<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.name"/>:
															</td>
															<td><input type="text" class="inputTextMediumBlue" name="vadrna" id="vadrna" size="32" maxlength="30" value='${model.record.vadrna}'></td>
														</tr>
														<tr>
															<td class="text14">Adresse 1:</td>
															<td><input type="text" class="inputTextMediumBlue" name="vadrn1" id="vadrn1" size="32" maxlength="30" value='${model.record.vadrn1}'></td>
														</tr>
														<tr>
															<td class="text14">Adresse 2:</td>
															<td><input type="text" class="inputTextMediumBlue" name="vadrn2" id="vadrn2" size="32" maxlength="30" value='${model.record.vadrn2}'></td>
														</tr>
														<tr>
															<td class="text14">Adresse 3:</td>
															<td><input type="text" class="inputTextMediumBlue" name="vadrn3" id="vadrn3" size="32" maxlength="30" value='${model.record.vadrn3}'></td>
														</tr>
														<tr>
															<td class="text14">Land:</td>
															<td>
																<input type="text" class="inputTextMediumBlue" name="valand" id="valand" size="4" maxlength="2" value='${model.record.valand}'>
																<a tabindex="-1" id="valandIdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																</a>
															</td>
														</tr>
													</table>
												</div>	
											</td>

										</tr>

									</table>
								</td>
								<td width="50%" valign="top">
									<table>
										<tr>
											<td colspan="4" class="text14">
												<table>
												<tr>
													<td class="text14" title="syfr06">
														Faktura sendes som EHF:
														<c:if test="${user.filand == 'NO'}">
										 					&nbsp;<img onMouseOver="showPop('ehf_info');" onMouseOut="hidePop('ehf_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											 				<div class="text11" style="position: relative;" align="left">
											 				<span style="position:absolute; top:2px; width:250px;" id="ehf_info" class="popupWithInputText text11"  >
													           		<b>
													           			Faktura sendes som EHF
													 	          	</b><br><br>
																	Hvis krysset av sendes EDIfaktura på EHF-formatet. <br>
																	Merk. EHF er alltid "Single-faktura". <br>
																	Forutsetning er at kunden er registrert med organisasjonsnummer/foretaksnummer <br>
																	(som er registrert i det sentrale "ELMA-registeret" som mulig mottaker av EHF.)   <br>  
																	Om kunden er registrert med et bedriftsnummer (av fortollingshensyn eller annet), <br>
																	kan en parameter med kode "EHFON" benyttes for å overstyre til det Org.nr som EHF skal sendes til.
		
																	<br><br>
															</span>
															</div>
														</c:if>
													</td>
													<td class="text12BlueGreen">
														<select name="syfr06" id="syfr06" >
										  					<option value=""<c:if test="${ model.record.syfr06 == ''}"> selected </c:if>><spring:message code="systema.no"/></option>
					 					  					<option value="J"<c:if test="${model.record.syfr06 == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
										  				</select>
		
														<c:if test="${user.filand == 'NO'}">
															<c:if test="${model.record.syfr06 == ''}">
																<c:if test="${model.record.elma == 'J'}">
																	&nbsp;merk: Org.nr er registrert i ELMA.
																</c:if>
															</c:if>
														</c:if>
		
													</td>
													
												</tr>
												</table>
											</td>
										</tr>

										<tr>
											<td class="text14">
												Kan sendes som epost:
							 					&nbsp;<img onMouseOver="showPop('epost_mott_info');" onMouseOut="hidePop('epost_mott_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<div class="text11" style="position: relative;" align="left">
								 				<span style="position:absolute; top:2px; width:250px;" id="epost_mott_info" class="popupWithInputText text11"  >
									           		<b>
									           			Kan sendes som epost
									 	          	</b><br><br>
														Hvis utfylt sendes faktura som epost til gitte adresse.<br>
														Det skapes automatisk 2 "Kontakter"(se fanen Kontakter) - <br>
														EN med funksjonen *SAMLEFAKTURA og en med *SINGELFAKTURA <br>
														Straks disse er opprettet skjer senere vedlikehold via fanen "Kontakter" <br>
														(Avvik mht hvilke vedlegg som skal medtas/endring av epostadresse / sletting  mm )
													<br><br>
												</span>
												</div>
											</td>
											<td colspan="3">
												<input type="text" class="inputTextMediumBlue" name="epostmott" id="epostmott" size="60" maxlength="70" value="${model.record.epostmott}">
											</td>

										</tr>

										<tr>
											<td class="text14" title="kpers">
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.contact"/>:
											</td>
											<td><input type="text" class="inputTextMediumBlue" name="kpers" id="kpers" size="25" maxlength="30" value='${model.record.kpers}'></td>
											<td class="text14" title="tlf">
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.phone"/>:
											</td>
											<td><input type="text" class="inputTextMediumBlue" name="tlf" id="tlf" size="15" maxlength="15" value='${model.record.tlf}'></td>
										</tr>
										<tr>
											<td class="text14" title="syepos">
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.email"/>:
											</td>
											<td colspan="4"><input type="email" class="inputTextMediumBlue" name="syepos" id="syepos" size="60" maxlength="70" value='${model.record.syepos}'></td>
										</tr>
										<tr>
											<td class="text14" title="valkod">
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.currency"/>:
											</td>
											<td>
												<input type="text" class="inputTextMediumBlue" name="valkod" id="valkod" size="4" maxlength="3" value='${model.record.valkod}'>
												<a tabindex="-1" id="valkodIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
												</a>
											</td>
											<td class="text14" title="adr21">
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.eori"/>:
											</td>
											<td colspan="3">
												<input type="text" class="inputTextMediumBlue" name="eori" id="eori" size="15" maxlength="17" value='${model.record.eori}'>
											</td>
										</tr>

										<tr>
											<td colspan="2"></td>
											<td>&nbsp;</td>
											<td valign="top">&nbsp;
												<c:if test="${user.filand == 'NO'}">
								 					&nbsp;<img onMouseOver="showPop('brreg_info');" onClick="hidePop('brreg_info');"style="vertical-align:bottom;" width="150px" height="25px" src="http://scf.brreg.no/bilder/brreg_logo.svg" alt="info">
									 				<div class="text11" style="position: relative;" align="left">
									 				<span style="position:absolute; top:2px; width:300px; left:-60px" id="brreg_info" class="popupWithInputText text11">
									 					<textarea cols="46" id="ehp">&nbsp;Ikke nedlastet.</textarea>
													</span>
													</div>
												</c:if>
											</td>	
										</tr>		
									</table>
								</td>
							</tr>

							<tr> 
								<td colspan="2" >&nbsp;
									<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="center">
										<tr>
											<td class="text14Bold">
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.invoice"/>:
											</td>
										</tr>
									</table>
									<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
										<tr> 
											<td width="50%" >
												<table border="0">
													<tr>
														<td class="text14" title="bankg">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.bank"/>:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="bankg" id="bankg" size="20" maxlength="15" value='${model.record.bankg}'></td>
														<td class="text14" title="betbet">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.betbet"/>
										 					&nbsp;<img onMouseOver="showPop('betbet_info');" onMouseOut="hidePop('betbet_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											 				<div class="text11" style="position: relative;" align="left">
											 				<span style="position:absolute; top:2px; width:250px;" id="betbet_info" class="popupWithInputText text11"  >
											           		<b>
											           			<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.betbet"/>
											 	          	</b><br><br>
																Er obligatorisk hvis kundetype er Fakturakunde.
															<br><br>
															</span>
															</div>

														</td>
														<td>
															<select name="betbet" id="betbet" class="inputTextMediumBlue">
						 					  					<option value="NOT_SET">-<spring:message code="systema.choose"/>-</option>
											  					<c:forEach var="record" items="${model.betbetList}" >
											 				  		<option title="${record.bettxt}" value="${record.betbet}"<c:if test="${model.record.betbet == record.betbet}"> selected </c:if> >${record.betbet}</option>
																  </c:forEach>  
																</select>
														</td>
													</tr>
													<tr>
														<td class="text14" title="postg">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.post"/>:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="postg" id="postg" size="20" maxlength="20" value='${model.record.postg}'></td>
														<td class="text14" title="betmat">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.payment"/>:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="betmat" id="betmat" size="15" maxlength="17" value='${model.record.betmat}'></td>
													</tr>
													<tr>
														<td class="text14" title="sykont">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.account"/>:
														</td>
														<td>
															<input type="text" class="inputTextMediumBlue" name="syfr02" id="syfr02" size="2" maxlength="4" value='${model.record.syfr02}'>
															<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="sykont" id="sykont" size="10" maxlength="7" value='${model.record.sykont}'>
														</td>
														<td class="text14" title="kgrens">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.acclimit"/>:
														</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="kgrens" id="kgrens" size="10" maxlength="7" value='${model.record.kgrens}'></td>
			
													</tr>
													<tr>
														<td class="text14" title="fmot">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.invoicerec"/>:
														</td>
														<td colspan="3">
															<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="fmot" id="fmot" size="10" maxlength="8" value='${model.record.fmot}'>
															<a tabindex="-1" id="fmotIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
															</a>&nbsp;	
															<input type="text" readonly class="inputTextReadOnly" name="fmotname" id="fmotname" size="30" maxlength="30" value='${model.record.fmotname}'>
														
														</td>
													</tr>
												</table>
											</td>
											<td width="50%" valign="top">
												<table border="0">
													<tr>
														<td class="text14" title="sysalu">
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.saldo"/>:
														</td>
														<td><input type="text" readonly class="inputTextReadOnly" name="sysalu" id="sysalu" size="15" maxlength="13" value='${model.record.sysalu}'></td>
														<td class="text14" title="syfr03">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.invoicetext"/>:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="syfr03" id="syfr03" size="5" maxlength="2" value='${model.record.syfr03}'>
															<a tabindex="-1" id="syfr03IdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
															</a>
														</td>
													</tr>
													<tr>
														<td class="text14" title="cum3lm:m3m3">
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.invoicem3"/>:
														</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="m3m3" id="m3m3" size="6" maxlength="5" value='${model.record.m3m3}'></td>
														<td class="text14" title="cum3lm:mllm">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.invoicelm"/>:
														</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="mllm" id="mllm" size="6" maxlength="5" value='${model.record.mllm}'></td>
													</tr>
													<tr>
														<td class="text14" title="rnraku">
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.mva"/>:
														</td>
														<td>
															<input type="text" class="inputTextMediumBlue" name="rnraku" id="rnraku" size="20" maxlength="20" value='${model.record.rnraku}'>
														</td>
														<td class="text14" title="sfakt">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.invoicecoll"/>:
											 				&nbsp;<img onMouseOver="showPop('ic_info');" onMouseOut="hidePop('ic_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											 				<div class="text11" style="position: relative;" align="left">
												 				<span style="position:absolute; top:2px; width:250px;" id="ic_info" class="popupWithInputText text11"  >
												           		<b>
												           			<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.invoicecoll"/>
												 	          	</b><br><br>
																	Blank = singel-faktura, J=Samlefaktura <br>
																	Om ønskelig kan en gruppere i stedet for bare J=Ja. <br>
																	F.eks: U=Uke , M= Måned  og lignende. <br>
																	Dette kan forenkle kjøring av samlefaktura
																<br><br>
																</span>
															</div>														
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="sfakt" id="sfakt" size="2" maxlength="1" value='${model.record.sfakt}'></td>
													</tr>
													<tr>
														<td class="text14" title="symvjn">
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.mvareg"/>:
														</td>
														<td>
															<select name="symvjn" id="symvjn" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.symvjn == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${ model.record.symvjn == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
														<td class="text14" title="symvsp">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.mvaspec"/>:
														</td>
														<td>
															<select name="symvsp" id="symvsp" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.symvsp == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${ model.record.symvsp == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>	

							<tr>
								<td colspan="2" >&nbsp;
									<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="center">
										<tr>
											<td class="text14Bold">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.terms"/>
											</td>
										</tr>
									</table>
									<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
										<tr> 
											<td width="50%" >
												<table>
													<tr>
														<td class="text14" title="syutlp">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.provision"/>:
															 &nbsp;<img onMouseOver="showPop('opdt_info');" onMouseOut="hidePop('opdt_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											 				<div class="text11" style="position: relative;" align="left">
											 				<span style="position:absolute; top:2px; width:250px;" id="opdt_info" class="popupWithInputText text11"  >
													           		<b>
													           			<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.provision"/>
													           		</b><br><br>
																	<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.provisioninfo"/>
															</span>
															</div>															
														</td>
														<td>
															<input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="syutlp" id="syutlp" size="5" maxlength="4" value='${model.record.syutlp}'>%
														<td class="text14" title="syopdt">	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.mission"/>:
														</td>
														<td>
															<input type="text" class="inputTextMediumBlue" name="syopdt" id="syopdt" size="5" maxlength="2" value='${model.record.syopdt}'>
															<a tabindex="-1" id="syopdtIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
															</a>				
														</td>
													</tr>
													<tr>
														<td class="text14" title="syminu">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.provisionmin"/>:
														</td>
														<td>
														 <input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="syminu" id="syminu" size="5" maxlength="4" value='${model.record.syminu}'>%
														</td>
														<td class="text14" title="sylikv">	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.sylikv"/>:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="sylikv" id="sylikv" size="1" maxlength="1" value='${model.record.sylikv}'>
															<a tabindex="-1" id="sylikvIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
															</a>
														</td>
													</tr>
												</table>
											</td>
											<td width="50%" valign="top">
												<table>
													<tr>
														<td class="text14" title="golk">
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.golk"/>:
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="golk" id="golk" size="5" maxlength="4" value='${model.record.golk}'></td>
														<td class="text14" title="aktkod">	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.aktkod"/>:
														</td>
														<td class="text14" >

														<c:choose>
															<c:when test="${model.isAdressCustomer == 'N'}">
																<select name="aktkod" id="aktkod" >
							 					  					<option value="A"<c:if test="${model.record.aktkod == 'A'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.aktkod.active"/></option>
												  					<option value="I"<c:if test="${model.record.aktkod == 'I'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.aktkod.inactive"/></option>
												  				</select>
															</c:when>
															<c:when test="${model.action == 'doCreate'}">
																<select name="aktkod" id="aktkod" >
							 					  					<option value="A"<c:if test="${model.record.aktkod == 'A'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.aktkod.active"/></option>
												  					<option value="I"<c:if test="${model.record.aktkod == 'I'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.aktkod.inactive"/></option>
												  				</select>
															</c:when>
															<c:otherwise>
																<select disabled name="aktkod" id="aktkod" >
							 					  					<option value="A"<c:if test="${model.record.aktkod == 'A'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.aktkod.active"/></option>
												  					<option value="I"<c:if test="${model.record.aktkod == 'I'}"> selected </c:if> ><spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.aktkod.inactive"/></option>
												  				</select>
															</c:otherwise>
														</c:choose>

														</td>
													</tr>
													<tr>
														<td class="text14" title="dkund">
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.dkund"/>:
														</td>
														<td>
															<select name="dkund" id="dkund" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="D"<c:if test="${model.record.dkund == 'D'}"> selected </c:if>><spring:message code="systema.yes"/></option>		
						 					  				</select>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>	

							<tr> 
								<td colspan="2" >&nbsp;
									<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="center">
										<tr>
											<td class="text14Bold">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.misc"/>
											</td>
										</tr>
									</table>
									<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
										<tr> 
											<td width="50%" >
												<table border="0">
													<tr>
														<td class="text14" title="vatkku">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.vat"/>:
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="vatkku" id="vatkku" size="20" maxlength="14" value='${model.record.vatkku}'></td>
														<td class="text14" title="syfr04">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.secured"/>:
															&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td>
													    	<input type="text" class="inputTextMediumBlue" name="syfr04" id="syfr04" size="5" maxlength="3" value='${model.record.syfr04}'>
														</td>
													</tr>
													<tr>
														<td class="text14" title="aknrku">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.deal"/>:
														</td>
														<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="aknrku" id="aknrku" size="10" maxlength="8" value='${model.record.aknrku}'></td>
														<td class="text14" title="syfr05">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.syfr05"/>:
														</td>
														<td>
															<select name="syfr05" id="syfr05" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.syfr05 == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${ model.record.syfr05 == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>														
														</td>
													</tr>
													<tr>
														<td class="text14" title="syselg">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.sell"/>:
														</td>
														<td >
															<input type="text" class="inputTextMediumBlue" name="syselg" id="syselg" size="5" maxlength="3" value='${model.record.syselg}'>												
														</td>
														<td class="text14" title="kundgr">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.kundgr"/>:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="kundgr" id="kundgr" size="2" maxlength="2" value='${model.record.kundgr}'></td>
													</tr>
												</table>
											</td>
											<td width="50%" valign="top">
												<table>
													<tr>
														<td class="text14" title="syiat1">
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.syiat1"/>:
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
														</td>
														<td>
															<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="syiat1" id="syiat1" size="10" maxlength="7" value='${model.record.syiat1}'>
														</td>
														<td class="text14" title="xxbre">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																																					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.xxbre"/>:
														</td>
														<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="xxbre" id="xxbre" size="10" maxlength="8" value='${model.record.xxbre}'></td>
													</tr>
	
													<tr>
														<td class="text14" title="syiat2">
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.syiat2"/>:
														</td>
														<td>
															<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="syiat2" id="syiat2" size="5" maxlength="4" value='${model.record.syiat2}'>
														</td>
														<td class="text14" title="xxlen">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.xxlen"/>:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="xxlen" id="xxlen" size="10" maxlength="9" value='${model.record.xxlen}'></td>
													</tr>
			
													<tr>
														<td class="text14" title="systat">
															<spring:message code="systema.main.maintenance.mainmaintenancecundf.customer.systat"/>:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="systat" id="systat" size="3" maxlength="3" value='${model.record.systat}'></td>
													</tr>
													
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr><td colspan="2">&nbsp;</td></tr>
							<%-- Validation errors --%>
							<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
							<tr>
								<td >
						           	<table align="left" border="0" cellspacing="0" cellpadding="0">
						           	<tr >
						           	<td >					
							            <ul class="isa_error text14" >
							            <c:forEach var="error" items="${errors.allErrors}">
							                <li >
							                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>&nbsp;&nbsp;
							                </li>
							            </c:forEach>
							            </ul>
									</td>
									</tr>
									</table>
								</td>
							</tr>
							</spring:hasBindErrors>
				
							<%-- Other errors (none validation errors) --%>
							<c:if test="${not empty model.errorMessage}">
							<tr>
								<td >
						           	<table align="left" border="0" cellspacing="0" cellpadding="0">
								 		<tr>
								 			<td >
								 				<ul class="isa_error text14" >
						                                  <li>${model.errorMessage}</li>                                    
						                              </ul>
								 			</td>
										</tr>
									</table>
								</td>
							</tr>
							</c:if>

							<tr> 
								<td >&nbsp;</td>
								<td align="right">
									<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.save"/>'/>
								</td>
							</tr>

							<tr height="3">
								<td>&nbsp;</td>
							</tr>
						</table>
						
	 	    		</form>
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

