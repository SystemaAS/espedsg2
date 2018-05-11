<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavdsadimport_syftaaar_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text11" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="13%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
						<font class="tabDisabledLink">&nbsp;Vedlikehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="13%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;Avdelinger</font>&nbsp;						
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="16%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdSadiSyftaaar" onClick="setBlockUI(this);" href="mainmaintenanceavdsadimport_syftaaar.do">
							<font class="tabDisabledLink">&nbsp;TVINN SAD Import.</font>&nbsp;
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
						</a>
					</td>
					<c:choose>
						<c:when test="${not empty model.updateId}">
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="16%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Avd.</font>&nbsp;
								<font class="text11MediumBlue">(${model.avd})</font>
							</td>
						</c:when>
						<c:otherwise>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="16%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Lage ny avd.</font>&nbsp;
								<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
							</td>
							
						</c:otherwise>
					</c:choose>
					<td width="45%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</tr>
		</table>
		</td>
	</tr>
	
	<tr>
		<td>
		<%-- space separator --%>
			<form action="mainmaintenanceavdsadimport_syftaaar_edit.do" name="formRecord" id="formRecord" method="POST" >
	 	    <table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 		<tr height="20"><td>&nbsp;</td></tr>
	 	    
	 	    <%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td width="5%">&nbsp;</td>
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
				<td width="5%">&nbsp;</td>
				<td >
		           	<table align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
				 			<td >
				 				<ul class="isa_error text14" >
		                                  <li>[ERROR on Update] - Server return code: ${model.errorMessage}</li>                                    
		                              </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
	 	     
	 	    <tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="updateId" id=updateId value="${model.updateId}">
						<input type="hidden" name="action" id=action value="${model.action}">
						<table cellspacing="1" border="0" align="left">
							<tr >
								<td colspan="4" class="text14MediumBlue" title=".">Avd.navn&nbsp;&nbsp;&nbsp;
									<input readonly class="inputTextReadOnly" id="avdnavn" name="avdnavn" type="text" size="45" value="${model.avdnavn}">
								</td>
							</tr>
							<tr height="5"><td >&nbsp;</td></tr>
				    	    <tr>
				    	    	<c:choose>
					    	    	<c:when test="${not empty model.updateId}">
					    	    		<input type="hidden" name="siavd" id=siavd value="${model.avd}">
					    	    	</c:when>
					    	    	<c:otherwise>
					    	    		<td class="text14" title="siavd"><font class="text14RedBold" >*</font>Avdeling
					    	    			<a tabindex="-1" id="siavdIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
					    	    		</td>
										<td class="text14" >
										<select name="siavd" id="siavd" class="inputTextMediumBlueMandatoryField">
						  					<option value="">-velg-</option>
						  					<c:forEach var="record" items="${model.avdGeneralList}" >
						 				  		<option value="${record.koaavd}"<c:if test="${ model.record.siavd == record.koaavd}"> selected </c:if> >${record.koaavd}</option>
											  </c:forEach>  
											</select>
											
									  	</select>
										</td>	
					    	    	</c:otherwise>
				    	    	</c:choose>
				    	    	
				    	    	
								<td class="text14" title="sidl"><font class="text14RedBold" >*</font>Bruk dataliste</td>
								<td class="text14" >
									<select name="sidl" id="sidl" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.sidl == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.sidl == 'N'}"> selected </c:if> >Nei</option>
					  					<option value="1"<c:if test="${ model.record.sidl == '1'}"> selected </c:if> >1</option>
								  	</select>
								</td>
								<td class="text14" title="sia4">&nbsp;&nbsp;Format A4</td>
								<td class="text14" >
									<select name="sia4" id="sia4" class="inputTextMediumBlue">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.sia4 == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.sia4 == 'N'}"> selected </c:if> >Nei</option>
								  	</select>
								</td>	
								<td class="text14" title="sitdn">&nbsp;&nbsp;<font class="text14RedBold" >*</font>Nrt.teller intern ref.</td>
								<td class="text14" >
									<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="sitdn" id="sitdn" size="10" maxlength="7" value='${model.record.sitdn}'>
								</td>
								
								<td class="text14" title="siekst">&nbsp;&nbsp;Importere eksterne data</td>
								<td class="text14" >
									<select name="siekst" id="siekst" class="inputTextMediumBlue">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.siekst == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.siekst == 'N'}"> selected </c:if> >Nei</option>
					  					<option value="R"<c:if test="${ model.record.siekst == 'R'}"> selected </c:if> >R</option>
								  	</select>
								</td>
							</tr>
							<tr>
								<td class="text14" title="sitolk"><font class="text14RedBold" >*</font>Bruk kommunikasjon</td>
								<td class="text14" >
									<select name="sitolk" id="sitolk" class="inputTextMediumBlueMandatoryField">
					  					<option value="J"<c:if test="${ model.record.sitolk == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.sitolk == 'N' || empty model.record.sitolk}"> selected </c:if> >Nei</option>
								  	</select>
								</td>
							</tr>
							<tr height="5"><td></td></tr>
						</table>
				</td>
			</tr>
			
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
					
					<table width="95%" class="formFrameTitaniumWhite" cellspacing="1" border="0" align="left">
						<tr><td colspan="2" class="text14"><b>Kommunikasjon </b><font class="text11" >&nbsp;( Kun hvis kommunikasjonen er valgt )</font></td></tr>
						<tr height="5"><td></td></tr>
			    	    <tr>
							<td class="text14" title="sist">Vente med å skrive ut melding </td>
							<td class="text14">
								<select name="sist" id="sist" class="inputTextMediumBlue">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.sist == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.sist == 'N'}"> selected </c:if> >Nei</option>
							  	</select>
							</td>
							<td class="text14" title="s3039e"><font class="text14RedBold" >*</font>Til ekspedisjonsenhet</td>
							<td class="text14">
								<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlueMandatoryField" name="s3039e" id="s3039e" size="6" maxlength="6" value='${model.record.s3039e}'>
							</td>	
							<td class="text14" title="s3039eo1">Statistisk Oppretting:</td>
							<td class="text14">
								<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="s3039eo1" id="s3039eo1" size="6" maxlength="6" value='${model.record.s3039eo1}'>
							</td>
							<td class="text14" title="s3039eo2">Etterberegning/Refusjon:</td>
							<td class="text14">
								<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" name="s3039eo2" id="s3039eo2" size="6" maxlength="6" value='${model.record.s3039eo2}'>
							</td>
						</tr>
						<tr>	
							<td class="text14" title="syrg"><font class="text14RedBold" >*</font>Foretaksnummer</td>
							<td class="text14">
								<input readonly type="text" class="inputTextReadOnly" name="syrg" id="syrg" size="11" maxlength="10" value='${model.record.syrg}'>
							</td>
							<td class="text14" title="s0026">Applikasjonsreferanse</td>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="s0026" id="s0026" size="11" maxlength="10" value='${model.record.s0026}'>
							</td>
							<td class="text14" title="s0035">Testindikator</td>
							<td class="text14">
								<select name="s0035" id="s0035" class="inputTextMediumBlue">
				  					<option value="">-velg-</option>
				  					<option value="1"<c:if test="${ model.record.s0035 == '1'}"> selected </c:if> >1</option>
				  					<option value="2"<c:if test="${ model.record.s0035 == '2'}"> selected </c:if> >2</option>
							  	</select>
							</td>
						</tr>
						<tr>
							<td class="text14" title="s0004"><font class="text14RedBold" >*</font>UtvekslingsId Avdeling 
								<a tabindex="-1" id="s0004IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text14">
								<input type="text" class="inputTextMediumBlueMandatoryField" name="s0004" id="s0004" size="20" maxlength="35" value='${model.record.s0004}'>
							</td>
						
							<td class="text14" title="s0010"><font class="text14RedBold" >*</font>UtvekslingsId Tollvesenet 
								<a tabindex="-1" id="s0010IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text14">
								<input type="text" class="inputTextMediumBlueMandatoryField" name="s0010" id="s0010" size="20" maxlength="35" value='${model.record.s0010}'>
							</td>
						
							<td colspan="2" class="text14" title="silv2"><font class="text14RedBold" >*</font>Skriv tollkvittering ved utleveringsattest</td>
							<td class="text14">
								<select name="silv2" id="silv2" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.silv2 == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.silv2 == 'N'}"> selected </c:if> >Nei</option>
							  	</select>
							</td>
						</tr>
						<tr height="5"><td></td></tr>
					</table>
				</td>
			</tr>
			<tr height="20"><td></td></tr>
			
			<tr>
				<td width="5%">&nbsp;</td>
				<td>
				<table width="45%" cellspacing="1" border="0" align="left">
				<tr>
	 				<td class="text14" >
	 				<img onMouseOver="showPop('1_1_info');" onMouseOut="hidePop('1_1_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
 					<b>1.1</b><span title="sidty">Dekl.type&nbsp;</span>
 					</td>
 					<td class="text14" >
 					<select class="inputTextMediumBlue" name="sidty" id="sidty" >
	 				  <option value="">-velg-</option>
					  <option value="EU"<c:if test="${model.record.sidty == 'EU'}"> selected </c:if> >EU</option>
					  <option value="IM"<c:if test="${model.record.sidty == 'IM'}"> selected </c:if> >IM</option>
					</select>
 					
 					<div class="text11" style="position: relative;" align="left">
 					<span style="position:absolute; top:2px; width:250px;" id="1_1_info" class="popupWithInputText text11"  >
		           			<b>1.1 Dekl.type</b>
		           			<ul>
		           				<li><b>EU</b> innførsel fra et EØS,EFTA eller EU-land</li>
		           				<li><b>IM</b> innførsel fra land som ikke er tilknyttet EØS, EFTA eller EU (tredjeland).
		           				</li>
		           			</ul>
		           			
					</span>	
					</div>	
	 				
	 				</td>
	 				
	 				<td class="text14">
		 				<img onMouseOver="showPop('prosedyr_info');" onMouseOut="hidePop('prosedyr_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 				<b>1.2</b><span title="sidp">&nbsp;Eksped.type</span>
		 			</td>
		 			<td>
		 				<select class="inputTextMediumBlue" name="sidp" id="sidp" >
		 				  <option value="">-velg-</option>
			 				  	<c:forEach var="record" items="${model.ekspedisjonstyperImportCodeList}" >
			 				  		<option value="${record.ks1typ}"<c:if test="${model.record.sidp == record.ks1typ}"> selected </c:if> >${record.ks1typ}</option>
								</c:forEach>  
						</select>	
		 				
			 			<div class="text11" style="position: relative;display:inline;" align="left">
		 				<span style="position:absolute; top:5px; left:-130px; width:250px;" id="prosedyr_info" class="popupWithInputText text11">
			           		<ul>
			           			<c:forEach var="record" items="${model.ekspedisjonstyperImportCodeList}" >
			           			<li><b>${record.ks1typ}</b>&nbsp;${record.ks1ftx}</li>
			           			</c:forEach>
			           		</ul>
						</span>
						</div>
	 				</td>
	 			</tr>
 				<tr>
 					<td class="text14">&nbsp;&nbsp;&nbsp;
		 				<span title="siski" id="v_siski" class="validation">Toll/Mva</span>
		 			</td>
		 			<td class="text14" >	
		 				<select class="inputTextMediumBlue" name="siski" id="siski" >
	 						<option value="">-velg-</option>
					  		<option value="S"<c:if test="${ model.record.siski == 'S'}"> selected </c:if> >S</option>
					  		<option value="K"<c:if test="${ model.record.siski == 'K'}"> selected </c:if> >K</option>
					  		<option value="I"<c:if test="${ model.record.siski == 'I'}"> selected </c:if> >I</option>
						</select>
		 				<div class="text11" style="position: relative;" align="left">
		 				<span style="position:absolute; top:2px; width:250px;" id="tollMva_info" class="popupWithInputText text11" >
		           			<b>Toll/Mva</b>
		           			<br/><br/>
		           			Dersom systemet ikke finner tollkreditt på det tastede kundenummeret, så må man angi hvem som skal betale TOLL/MVA.
			           		<ul>
			           			<li><b>S</b> = på faktura mot selger/avsender siden (flyttes autom. over på faktura)</li>
		           				<li><b>K</b> = på faktura mot kjøper</li>
			           			<li><b>I</b> = Ingen (Kunden kommer selv med sjekk på utlegget</li>
			           		</ul>
			
						</span>
						</div>
					</td>
 				
	 				<td class="text14" >
	 					<img onMouseOver="showPop('ens_flag_info');" onMouseOut="hidePop('ens_flag_info'); "style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
	 					<span title="sikddk">&nbsp;Dagsopp./Kontant</span>
	 				</td>
	 				<td class="text14" >	
		 				<select class="inputTextMediumBlue" name="sikddk" id="sikddk" >
		 				  <option selected value="">-velg-</option>
						  <option value="D"<c:if test="${model.record.sikddk == 'D'}"> selected </c:if> >D</option>
						  <option value="K"<c:if test="${model.record.sikddk == 'K'}"> selected </c:if> >K</option>
						</select>
		 				
		 				<div class="text11" style="position: relative;" align="left">
		 				<span style="position:absolute; top:2px; width:250px;" id="ens_flag_info" class="popupWithInputText text11"  >
				           		<b>Dagsoppgjør/Kontant</b>
				           		<p>
				           		Kodevalg for hvilke tekst man ønsker på deklarasj. dersom S/K/I er brukt.
				           		</p>
				           		<ul>
				           			<li><b>D</b> = Dagsoppgjør</li>
				           			<li><b>K</b> = Kontant</li>						           			
				           		</ul>
						</span>
						</div>
	 				</td>
 				</tr>
				</table>
				</td>
			</tr>
			
			<tr>
			<td width="5%">&nbsp;</td>
			<td>
				<table width="98%" cellspacing="1" border="0" align="left">
					<tr>
						<%-- LEFT SIDE  --%>
						<td width="50%" >
						<table width="100%" cellspacing="1" border="0" align="left">
						<tr>
				 			<td >		
				 				<%-- SENDER --%>
				 				<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							 			<td class="text14White">
											&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 			<b>&nbsp;2.</b>&nbsp;Avsender / Eksportør&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							 				<div class="text11" style="position: relative;" align="left">
							 				<span style="position:absolute; top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
								           		<b>Avsender / Eksportør</b>
								           		<p>
								           		Oppgi selgerens navn og adresse. 
								           		Dersom ekspedisjonen omfatter fakturaer fra flere selgere enn det er plass til i rubrikken, skrives "se vedlagte fakturaer". 
								           		</p>
								           		
											</span>
											</div>
											
						 				</td>
					 				</tr>
				 				</table>
				 			</td>
				 		</tr>
					 		
				 		<tr>	
				 			<td >	
								<%-- create record --%>
							 	<table width="95%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
								 		<td width="100%">
									 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
										 		<tr height="10"><td ></td></tr>
										        
										        <tr>
										        	<%-- ================================================================================== --%>
										        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
										        		 These original values will be used when the user clicks "Cancel" buttons (puttting
										        		 back original value)																--%> 
										        	<%-- ================================================================================== --%>
										        	<input type="hidden" name="orig_sikns" id="orig_sikns" value='${Xmodel.record.sikns}'>
										        	<input type="hidden" name="orig_sinas" id="orig_sinas" value='${Xmodel.record.sinas}'>
										        	<input type="hidden" name="orig_siads1" id="orig_siads1" value='${Xmodel.record.siads1}'>
										        	<input type="hidden" name="orig_siads2" id="orig_siads2" value='${Xmodel.record.siads2}'>
										        	<input type="hidden" name="orig_siads3" id="orig_siads3" value='${Xmodel.record.siads3}'>
										        	
										            <td class="text14" align="left" >&nbsp;&nbsp;
											            <span title="sikns">Kundenummer</span>
											            
										            </td>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="sinas" id="v_sinas" class="validation">Navn&nbsp;</span>
										            	<a tabindex="-1" id="sinasIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
														</a>				
										            </td>
										        </tr>
										        <tr>
										            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="sikns" id="sikns" size="9" maxlength="8" value="${model.record.sikns}"></td>
										            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue"  name="sinas" id="sinas" size="31" maxlength="30" value="${model.record.sinas}"></td>
										        </tr>
										        <tr height="4"><td>&nbsp;</td></tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="siads1" id="v_siads1" class="validation">Adresse-1</span></td>
										            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue"  name="siads1" id="siads1" size="40" maxlength="30" value="${model.record.siads1}"></td>
			    							            
										        </tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="siads2" id="v_siads2" class="validation">Adresse-2</span></td>
			    							            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siads2" id="siads2" size="40" maxlength="30" value="${model.record.siads2}"></td>
			   							            
										        </tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="siads3" id="v_siads3" class="validation">Adresse-3</span></td>
			   							            <td>&nbsp;</td>							            
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siads3" id="siads3" size="40" maxlength="30" value="${model.record.siads3}"></td>
			   							        </tr>
										        <tr height="15">
										            <td class="text14Bold" align="left" >&nbsp;</td> 
			   							            <td>&nbsp;</td>							            
										        </tr>
									        </table>
								        </td>
							        </tr>
								</table>          
			            	</td>
			           	</tr> 
			           	<tr height="10"><td></td></tr>
			           	
			           	<%-- RECEIVER --%>
				 		<tr>
				 			<td >		
				 				<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							 			<td class="text14White">
							 				&nbsp;<img onMouseOver="showPop('8_info');" onMouseOut="hidePop('8_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<b>&nbsp;8.</b>&nbsp;Mottaker&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							 				<div class="text11" style="position: relative;" align="left">
							 				<span style="position:absolute; top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
									           		<b>Mottaker</b>
									           		<p>
									           		Oppgi mottakers/vareeiers navn og adresse.
									           		</p>
									           		<p> 
									           		I henhold til tollovens paragraf 1, pkt 3, er vareeier den som overfor tollvesenet er legitimert til å råde over varene. 
									           		Her oppgis også vareeierens 8-sifrede organisasjonsnr.
									           		</p>
											</span>
											</div>
											
						 				</td>
					 				</tr>
				 				</table>
				 			</td>
				 		</tr>
				 		<tr>	
				 			<td >	
								<%-- create record --%>
							 	<table width="95%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
								 		<td width="100%">
									 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
										 		<tr height="10"><td ></td></tr>
										        
										        <tr>
										        	<%-- ================================================================================== --%>
										        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
										        		 These original values will be used when the user clicks "Cancel" buttons (puttting
										        		 back original value)																--%> 
										        	<%-- ================================================================================== --%>
										        	<input type="hidden" name="orig_siknk" id="orig_siknk" value='${Xmodel.record.siknk}'>
										        	<input type="hidden" name="orig_sinak" id="orig_sinak" value='${Xmodel.record.sinak}'>
										        	<input type="hidden" name="orig_sirg" id="orig_sirg" value='${Xmodel.record.sirg}'>
										        	<input type="hidden" name="orig_siadk1" id="orig_siadk1" value='${Xmodel.record.siadk1}'>
										        	<input type="hidden" name="orig_siadk2" id="orig_siadk2" value='${Xmodel.record.siadk2}'>
										        	<input type="hidden" name="orig_siadk3" id="orig_siadk3" value='${Xmodel.record.siadk3}'>
										        	
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="siknk">Kundenummer</span>
										            	
										            </td>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="sinak" id="v_sinak" class="validation">Navn&nbsp;</span>
										            	<a tabindex="-1" id="sinakIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
														</a>			
										            </td>
										        </tr>
										        <tr>
										            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="siknk" id="siknk" size="9" maxlength="8" value="${model.record.siknk}"></td>
										            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue"  name="sinak" id="sinak" size="31" maxlength="30" value="${model.record.sinak}"></td>
										        </tr>
										        <tr height="10"><td></td></tr>
										        
										        <tr>
										            <td class="text14" align="left" >&nbsp;<span title="sirg" id="v_sirg" class="validation">Regnr</span></td>
										            <td class="text14" >
									 					<img onMouseOver="showPop('48_info');" onMouseOut="hidePop('48_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										 				&nbsp;<b>48.&nbsp;</b><span title="siktc/sikta/siktb">Kontonr.Tollkredit&nbsp;</span>
										 				<div class="text11" style="position: relative;" align="left">
										 				<span style="position:absolute; top:2px; width:250px;" id="48_info" class="popupWithInputText text11"  >
										           			<b>48. Kontonr.Tollkredit</b>&nbsp;
															<p>
										           			Hentes fra kunderegister. Dersom det er en kunde som tidligere har vært uten kred, så kan man taste inn krednr.
										           			</p>
														</span>
														</div>
													</td>
										        </tr>
										        <tr>
										            <td align="left"><input type="text" class="inputTextMediumBlue"  name="sirg" id="sirg" size="20" maxlength="11" value="${model.record.sirg}"></td>
										            <td align="left">
										            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="siktc" id="siktc" size="1" maxlength="1" value="${model.record.siktc}">
														<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sikta" id="sikta" size="5" maxlength="5" value="${model.record.sikta}">
														<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="siktb" id="siktb" size="2" maxlength="2" value="${model.record.siktb}">
													</td>	
										        </tr>
										         
										        <tr height="4"><td>&nbsp;</td></tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="siadk1" id="v_siadk1" class="validation">Adresse-1</span></td>
										            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siadk1" id="siadk1" size="40" maxlength="30" value="${model.record.siadk1}"></td>
			    							            
										        </tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="siadk2" id="v_siadk2" class="validation">Adresse-2</span></td>
			    							            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siadk2" id="siadk2" size="40" maxlength="30" value="${model.record.siadk2}"></td>
			   							            
										        </tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="siadk3" id="v_siadk3" class="validation">Adresse-3</span></td>
			   							            <td>&nbsp;</td>							            
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="siadk3" id="siadk3" size="40" maxlength="30" value="${model.record.siadk3}"></td>
			   							        </tr>
										        <tr height="15">
										            <td class="text14Bold" align="left" >&nbsp;</td> 
			   							            <td>&nbsp;</td>							            
										        </tr>
									        </table>
								        </td>
							        </tr>
								</table>          
			            	</td>
			           	</tr> 
			           	<tr height="10"><td></td></tr>
			           			 		
					 	<%-- DEKLARANT --%>
					 	<tr>
			            <td >		
			 				<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="18px">
						 			<td class="text14White">
						 				&nbsp;<img onMouseOver="showPop('14_b_info');" onMouseOut="hidePop('14_b_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 						<b>&nbsp;14.</b>&nbsp;Deklarant&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 				<div class="text11" style="position: relative;" align="left">
						 				<span style="position:absolute; top:2px; width:250px;" id="14_b_info" class="popupWithInputText text11"  >
							           		<b>14.Deklarant</b> 
						           			<p>
						           				Hentes automatisk fra standardverdiene for AVDELINGEN
						           			</p>
										</span>
										</div>
						 				
					 				</td>
				 				</tr>
			 				</table>
			 			</td>
			 			</tr>
			 			<tr>
			            <td >	
							<%-- create record --%>
						 	<table width="95%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr>
							 		<td>
								 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="5"><td></td></tr>
									        <tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="sinad">Navn</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="sitlf">Telefon</span></td>
									        </tr>
									        <tr>
									            <td align="left"><input  type="text" class="inputTextMediumBlue" name="sinad" id="sinad" size="35" maxlength="30" value="${model.record.sinad}"></td>
									            <td align="left"><input  type="text" class="inputTextMediumBlue" name="sitlf" id="sitlf" size="15" maxlength="12" value="${model.record.sitlf}"></td>
									        </tr>
									        <tr height="10"><td></td></tr>
									        <tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="sidst">Sted</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="sidt">Reg.dato</span></td>
									        </tr>
									        <tr>
									            <td align="left"><input  type="text" class="inputTextMediumBlue" name="sidst" id="sidst" size="16" maxlength="15" value="${model.record.sidst}"></td>
									            <td align="left"><input readonly type="text" class="inputTextReadOnly" name="sidt" id="sidt" size="8" maxlength="6" value="${model.record.sidtNO}"></td>
									        </tr>
									        
									        
								        </table>
							      	</td>
								 </tr>
								 <tr height="15"><td></td></tr>
						 	</table>
	            		</td>
		           		</tr>
		           		<tr height="10"><td></td></tr>
		           		
		           		<tr>
				            <td >
				                <table width="95%" align="left" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td class="text14">
							 				<b>&nbsp;22.</b>
							 				<span title="sibel3" id="v_sibel3" class="validation">Fakturasum&nbsp;</span>
							 			</td>
							 			<td align="left" >
							 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue"  name="sibel3" id="sibel3" size="20" maxlength="13" value="${model.record.sibel3NO}">				 				
							 			</td>
							 			<td class="text14" align="left">
							 				&nbsp;
							 				<span title="sival3" id="v_sival3" class="validation">Valuta</span>
							 				<%-- Note: onChange event in jQuery for this currency list --%>
							 				<select class="inputTextMediumBlue" name="sival3" id="sival3" >
							 				  <option value="">-velg-</option>	
							 				  <c:forEach var="record" items="${model.currencyCodeList}" >
						 				  		<option value="${record.kvakod}"<c:if test="${ model.record.sival3 == record.kvakod}"> selected </c:if> >${record.kvakod}</option>
											  </c:forEach>  
											</select>
											<%--
											<a tabindex="-1" id="sival3IdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
											 --%>
						 				</td>
					 				</tr>
					 				<tr>
						 				<td class="text14">
						 					<b>&nbsp;23.</b><span title="sivku">Kurs&nbsp;</span>
							 			</td>
							 			<td class="text14" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue"  name="sivku" id="sivku" size="10" maxlength="7" value="${model.record.sivkuNO}"></td>
							 			
							 			<td class="text14" align="left" >&nbsp;
							 				<img onMouseOver="showPop('24_info');" onMouseOut="hidePop('24_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<b>24.</b><span title="sitst">Tr.type</span>
							 				<select class="inputTextMediumBlue" name="sitst" id="sitst" >
							 				  <option value="">-velg-</option>	
							 				  <option value="1" <c:if test="${model.record.sitst == '1'}"> selected </c:if> >1</option>	
							 				  <option value="2" <c:if test="${model.record.sitst == '2'}"> selected </c:if> >2</option>	
							 				  <option value="3" <c:if test="${model.record.sitst == '3'}"> selected </c:if> >3</option>	
							 				  <option value="9" <c:if test="${model.record.sitst == '9'}"> selected </c:if> >9</option>	
							 				</select>
							 				<div class="text11" style="position: relative;" align="left">
							 				<span style="position:absolute; top:2px; width:250px;" id="24_info" class="popupWithInputText text11"  >
								           		<b>24.&nbsp;Transaksjonstype</b><br/><br/>
												Oppgi med kode:
												<ul>
													<li><b>1</b>&nbsp;Kjøp i fast regning</li>
													<li><b>2</b>&nbsp;Konsignasjon/Kommisjon</li>
													<li><b>3</b>&nbsp;Leie (herunder leasing)Lån</li>
													<li><b>9</b>&nbsp;Annet</li>
												</ul>
											</span>
											</div>
										</td>
					 				</tr>
					 				<tr height="5"><td></td></tr>
					 				<tr>
						 				<td class="text14">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 					<span title="factor">Faktor&nbsp;</span>
							 			</td>
							 			<%-- this field is only used via Ajax since there is no database field. It is used to disclosed a factor when changing the currency --%>
							 			<td class="text14Grey" align="left" ><input readonly type="text" class="inputTextReadOnly" name="factor" id="factor" size="6" value=""></td>
						 			</tr>
									<tr height="5"><td></td></tr>
								</table>
								</td>
						</tr>
						
						<tr height="2">
						<td>
						<table width="95%" align="left" border="0" cellspacing="0" cellpadding="0">
				 		<tr>
							<td style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> 
						
						</tr>
						<tr height="10"><td class="text">&nbsp;</td> </tr>
						</table>
						</td>
						</tr>
						
	        			<tr>
							<td >
				                <table width="95%" align="left" border="0" cellspacing="0" cellpadding="0">
							 		<tr>
							 			<td class="text14"><b>&nbsp;31.</b><span title="sift1/sift2" >Fritekst&nbsp;</span></td>
							 			<td align="left" ><input type="text" class="inputTextMediumBlue"  name="sift1" id="sift1" size="40" maxlength="45" value="${model.record.sift1}"></td>
						 			</tr>
						 			<tr>
							 			<td class="text14"><span title="sift2" >&nbsp;</span></td>
							 			<td align="left" ><input type="text" class="inputTextMediumBlue"  name="sift2" id="sift2" size="40" maxlength="45" value="${model.record.sift2}"></td>
						 			</tr>
						 			<tr height="3"><td></td></tr>
						 			<tr>
							 			<td class="text14"><b>&nbsp;44.</b><span title="sift3/sift4" >Fritekst&nbsp;</span></td>
							 			<td align="left" ><input type="text" class="inputTextMediumBlue"  name="sift3" id="sift3" size="40" maxlength="45" value="${model.record.sift3}"></td>
						 			</tr>
						 			<tr>
							 			<td class="text14"><span title="sift4" >&nbsp;</span></td>
							 			<td align="left" ><input type="text" class="inputTextMediumBlue"  name="sift4" id="sift4" size="40" maxlength="45" value="${model.record.sift4}"></td>
						 			</tr>
							 	</table>		
							</td>						
						</tr>
		           		<tr height="20"><td></td></tr>

						</table>
						</td>
						
						<%-- RIGHT SIDE  --%>
						<td width="50%" valign="top">
						<table width="100%" cellspacing="1" border="0" align="left">
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('15_info');" onMouseOut="hidePop('15_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>15.</b>&nbsp;<span title="silka">Avs/utf.land</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="15_info" class="popupWithInputText text11"  >
					           			<b>15. Avs/utf.land</b>
					           			<br/><br/>
					           			Med avsenderland forstås det land hvorfra varen er sendt til Norge uten mellomliggende handelstransaksjon (omlasting undderveis endrer ikke forholdet)
								</span>	
								</div>
									
					             </td>
					            <td >
					            	<input type="text" class="inputTextMediumBlue" name="silka" id="silka" size="4" maxlength="2" value="${model.record.silka}">
				            																										 			
								</td>
							</tr>
							<tr height="8"><td class="text"></td> </tr>
							
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('19_info');" onMouseOut="hidePop('19_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>19.</b><span title="sikdc" id="v_sikdc" class="validation">Container&nbsp;</span>
					            
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="19_info" class="popupWithInputText text11">
					           			<b>19. Container</b>
					           			<br/><br/>
					           			Oppgi,med fastsatte koder, om varene blir transportert i containere ved innpassering til Norge.
										<br>
										<ul>
										<li><b>0</b> = Varer som ikke transporteres i containere.</li>
										<li><b>1</b> = Varer som transporteres i containere.</li>
										</ul>
										Kan defineres som standardverdier pr. avdeling. 
										
								</span>	
								</div>
								</td>
									
					            <td class="text14" >
			           				<select class="inputTextMediumBlue" name="sikdc" id="sikdc">
				 						<option value="0" <c:if test="${model.record.sikdc == '0'}"> selected </c:if> >0</option>
				 						<option value="1" <c:if test="${model.record.sikdc == '1'}"> selected </c:if> >1</option>								 				  	  
									</select>
			           			</td>
							</tr>
							<tr height="10"><td class="text"></td> </tr>
							
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('21_1_info');" onMouseOut="hidePop('21_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>21.1</b><span title="sitrid" id="v_sitrid" class="validation">Transp.ID</span>
			 					<div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="21_1_info" class="popupWithInputText text11"  >
					           			<b>21.1 Det aktive Transportmidlets identitet</b>
					           			<p>
					           			Oppgi det aktive transportmidlets identitet ved innpassering til Norge. 
					           			</p>
										
								</span>		
					            </div>
					            </td>
					            
				                 <td >
					            	<input type="text" class="inputTextMediumBlue" name="sitrid" id="sitrid" size="21" maxlength="20" value="${model.record.sitrid}">
								</td>
							</tr>
							
							
							<tr>
					            <td class="text14" align="left">
					            <img onMouseOver="showPop('21_2_info');" onMouseOut="hidePop('21_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>21.2</b>
					            <span title="silkt">Aktive transp. nasjonalitet&nbsp;</span>
					          	<div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="21_2_info" class="popupWithInputText text11"  >
					           			<b>21.2 Aktive transp. nasjonalitet ved grense</b>
					           			<p>
					           			Oppgi det aktive transportmidlets nasjonalitet ved innpassering til Norge. 
					           			Feks. Norge = NO Tyskland = DE.
					           			</p>
					           			
								</span>
								</div>
								</td>
									
								<td>
									<input type="text" class="inputTextMediumBlue" name="silkt" id="silkt" size="4" maxlength="2" value="${model.record.silkt}">
								</td>
					        </tr>
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('25_info');" onMouseOut="hidePop('25_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>25.</b>
					            <span title="sitrm" id="v_sitrm" class="validation">Transportmåte ved grensen</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="25_info" class="popupWithInputText text11"  >
			           			<b>25. Transp.måte ved utpass.</b>
				           			<p>
									Oppgi med kode transportmåten ved utpassering fra Norge.<br/>
									Koden kan legges inn som standardverdi for avdelingen.
									</p>		
									<ul>
				           				<c:forEach var="code" items="${model.transportmaterCodeList}" >
					 				  		<li><b>${code.ks4trm}</b>&nbsp;${code.ks4ftx}</li>
				 				  		</c:forEach>	
				           			</ul>
								</span>	
								</div>
								
								</td>	
					            <td class="text14" >
					            	<select class="inputTextMediumBlue" name="sitrm" id="sitrm">
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="code" items="${model.transportmaterCodeList}" >
				 				  		<option value="${code.ks4trm}"<c:if test="${model.record.sitrm == code.ks4trm}"> selected </c:if> >${code.ks4trm}</option>
									</c:forEach> 
									</select>
			           			</td>
							</tr>
							
							
							<tr>
								<td class="text14" align="left" >
					            <img onMouseOver="showPop('49_info');" onMouseOut="hidePop('49_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>49.&nbsp;</b><span title="sign">Godsnr</span>
					            &nbsp;<input type="text" class="inputTextMediumBlue"  name="sign" id="sign" size="20" maxlength="15" value="${model.record.sign}">
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="49_info" class="popupWithInputText text11"  >
					           			<b>49. Godsnr</b><br/>
					           			Godsnr og posisjonsnr/konnossementnr overføres fra oppdraget.
										<br/><br/>
										Oppbygging av godsnr.:
										<ul>
											<li><b>1.- 4.</b> karakter: Årstall for registrering</li>
											<li><b>5.- 6.</b> karakter: Tolldistriktskode</li>
											<li><b>7.- 9.</b> karakter: Tollagerkode</li>
											<li><b>10.-12.</b> karakter: Lossedag, kalenderens dagnr.<li>
											<li><b>13.-15.</b> karakter: lossenr, fortløpende listenr, pr. tollager og dag</li>
										</ul>
										NB: Disse godsnummerkodene er definert i systemet med følgende funksjon:
										<ul>
											<li><b>P</b> = Postsending. Hvis denne koden benyttes vil rubrikk 25 Transportmåte automatisk bli    oppdatert med kode 50. Dette gjelder selv om annen kode allerede er tastet.
											Hvis Transportmåte = 50 er tastet og annen godsnummerkode enn P
											er benyttet, vil systemet gi feilmelding om dette.
											Ved godsnummerkode P er det ikke tillatt å taste noe i felt for posisjonsnr/konnossementnr.</li>
											<li><b>R</b> = Reisegods. Ved godsnummerkode R er det ikke tillatt å taste noe i felt for posisjonsnr/konnossementnr.
											<li><b>D</b> = Grensefortollet. Ved godsnummerkode D er det ikke tillatt å taste noe i felt for posisjonsnr/konnossementnr.
											<br/><br/>
											Andre gyldige koder:
											<li><b>J</b> = Jernbane.</li>
											<li><b>E</b> = Jernbane Express.</li>
											<li><b>F</b> = Fortolling fra frilager.</li>
											<li><b>T</b> = Fortolling fra transittlager.</li>
										</ul>
										Ved disse kodene er det valgfritt for bruker å taste noe i felt for posisjonsnr/konnossementnr.
								</span>	
								</div>
								
								</td>
								<td class="text14" align="left" ><span title="sipos">Posisjon</span>
					            	&nbsp;<input type="text" class="inputTextMediumBlue" name="sipos" id="sipos" size="15" maxlength="9" value="${model.record.sipos}">
					            </td>
					        </tr>
							<tr height="5"><td class="text"></td></tr>
							
							<tr>
				            <td class="text14" align="left" >
				            <img onMouseOver="showPop('20_1_info');" onMouseOut="hidePop('20_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 					<b>20.1</b><span title="silv">Leveringksvilkår kode</span>
				            <div class="text11" style="position: relative;" align="left">
				            <span style="position:absolute; top:2px; width:250px;" id="20_1_info" class="popupWithInputText text11"  >
				           			<b>Leveringksvilkår kode</b>
				           			<p>
				           			Kode for leveringsvilkår:
				           			</p>
				           			<ul>
				           				<c:forEach var="record" items="${model.incotermsCodeList}" >
				           					<li><b>${record.klbkod}</b>&nbsp;${record.klbkt}</li>
				           				</c:forEach>				           									           									           									           									           									           				
				           			</ul>
							</span>
							</div>	
							</td>	
				            <td >
				            	<select class="inputTextMediumBlue" name="silv" id="silv">
			 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.incotermsCodeList}" >
					 				  		<option value="${record.klbkod}"<c:if test="${model.record.silv == record.klbkod}"> selected </c:if> >${record.klbkod}</option>
										</c:forEach>  
								</select>
							</td>
							</tr>
			 			
			 				<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('20_2_info');" onMouseOut="hidePop('20_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>20.2</b>
			 					<span title="silvt">Leveringksvilkår sted</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="20_2_info" class="popupWithInputText text11"  >
					           			<b>Leveringksvilkår sted</b>
					           			<p>
					           			Stedet leveringsvilkåret gjelder til/fra. 
					           			</p>
								</span>	
								</div>
								</td>
					            <td >
					            	<input type="text" class="inputTextMediumBlue"  name="silvt" id="silvt" size="20" maxlength="17" value="${model.record.silvt}">
								</td>
							</tr>
							<tr height="5"><td></td></tr>
							
							
							<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
			 				<tr height="5"><td class="text">&nbsp;</td> </tr>
			 				<tr>
			            		<td class="text14" align="left" >
					            <img onMouseOver="showPop('30_1_info');" onMouseOut="hidePop('30_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>30.1&nbsp;</b><span title="sikdls">Lagringssted kode</span></td>
					            <td >
						          <input type="text" class="inputTextMediumBlue"  name="sikdls" id="sikdls" size="4" maxlength="2" value="${model.record.sikdls}">  
					            
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="30_1_info" class="popupWithInputText text11"  >
				           			<b>30. Lagringssted kode</b>
				           			<br/><br/>
				           			Varenes lagringssted oppgis med kode. Rubrikken skal fylles ut med gyldig kode for lagringssted.
				           			<br/><br/>
				           			
								</span>	
								</div>
								</td>
        					</tr>
	        					
        					<tr>
			            		<td class="text14" align="left" >
			            		<img onMouseOver="showPop('30_2_info');" onMouseOut="hidePop('30_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>30.2&nbsp;</b><span title="sils">Lagringssted tekst</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="30_2_info" class="popupWithInputText text11"  >
					           			<b>30. Lagringssted tekst</b>
					           			<br/><br/>
					           			Selve tekstfeltet oppdateres automatisk når man taster / plukker en gyldig kode.
								</span>	
								</div>
								</td>
					            <td ><input type="text" class="inputTextMediumBlue" name="sils" id="sils" size="20" maxlength="16" value="${model.record.sils}"></td>

        					</tr>
        					<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
				            <tr height="20"><td class="text">&nbsp;</td> </tr>
							
							
							<tr>
			 					<td class="text14" colspan="2" >
			 					<img onMouseOver="showPop('12_info');" onMouseOut="hidePop('12_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<b>12.&nbsp;Verdiopplysninger&nbsp;</b>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute; top:2px; width:250px;" id="12_info" class="popupWithInputText text11"  >
					           			<b>12. Verdiopplysninger</b>&nbsp;
					           			<br/><br/>
					           			Oppgi fraktomkostningene (til norsk grense) for hele ekspedisjonen når disse ikke inngår i fakturaprisen.
										<br/><br/>
										OVERFØRES AUTOMATISK FRA OPPDRAGET DERSOM DET ER KRAV TIL FRAKT PÅ
										TASTET FRANKATUR. 
										<br/><br/>
										BELØPET KAN TASTES I ØNSKET VALUTA. OMREGNES OG SKRIVES UT I NOK
								</span>
								</div>
								</td>
							</tr> 
							
							<tr>
								<td class="text14" align="left" >
								<img onMouseOver="showPop('andraKostPM_info');" onMouseOut="hidePop('andraKostPM_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="siftg2">+/-&nbsp;(P/M)</span>
				 				<div class="text11" style="position: relative;" align="left">
				 				<span style="position:absolute; top:2px; width:250px;" id="andraKostPM_info" class="popupWithInputText text11"  >
					           			<b>Andre kostnader +/- (P/M)</b>&nbsp;
					           			<p>
											HVIS MAN IKKE ØNSKER AT RUBR. 22 SKAL PÅVIRKES KAN MAN BENYTTE KODENE
											<b>M</b>(-) ELLER <b>P</b>(+)
											<br/><br/>
											Når man taster M eller P i feltet vil totalt fakturabeløp i rubr. 22 ikke påvirkes / endres, men det blir gjort fratrekk i "Statistisk verdi" som er grunnlaget for beregning av toll og avgifter.
										</p>
								</span>
								</div>
				 				</td>
			 					<td class="text14">
			 						<input type="text" class="inputTextMediumBlue" name="siftg2" id="siftg2" size="1" maxlength="1" value="${model.record.siftg2}">
			 					</td>
							</tr>
							<tr>
								<td class="text14" ><span title="sibel1/sival1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Beløp tollb.frakt</span></td>
			 					<td class="text14">
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sibel1" id="sibel1" size="12" maxlength="11" value="${model.record.sibel1NO}">
			 						<select class="inputTextMediumBlue" name="sival1" id="sival1">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.currencyCodeList}" >
					 				  		<option value="${record.kvakod}"<c:if test="${ model.record.sival1 == record.kvakod}"> selected </c:if> >${record.kvakod}</option>
									  	</c:forEach>  
									</select>
									<a tabindex="-1" id="sival1IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									
			 					</td>
							</tr>	
							<tr>
								<td class="text14" >
								<img onMouseOver="showPop('andraKost_info');" onMouseOut="hidePop('andraKost_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 				<span title="sibel2/sival2">Beløp andre kost.</span>
				 				
								<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute; top:2px; width:250px;" id="andraKost_info" class="popupWithInputText text11">
					           			<b>Beløp andre kostnader +/- (P/M)</b>&nbsp;
					           			<br/><br/>
										Oppgi summen av eventuelle tillegg og/eller fradrag. Dersom det er mer å trekke fra enn å legge til fakturabeløpet, blir det et negativt beløp her.
										<br/><br/>
										<b>Eksempel på tillegg:</b> Provisjon,royalty,Innpakk..
										<br/><br/>
										<b>Eksempel på fradrag:</b> Diverse rabatter som er gitt i tilknyttning til de innførte varer.
										<br/><br/>
										HER KAN DET TASTES I ØNSKET VALUTA MED <b>+</b> ELLER <b>-</b>. DET TASTEDE BELØP
										GJELDER FOR HELE SENDINGEN. SYSTEMET BESØRGER FORDELINGEN PR.VARELINJE.
										<p>	
											Vær oppmerksom på at verdien i rubr. 22 "Fakturert valuta og totalbeløp" vil bli justert tilsvarende når man benytter +/-.
											Dette vises ikke i selve skjermbildet, men vil være den verdien som blir skrevet i rubr. 22 ved sending til TVINN og også ved utskrift av deklarasjonen.
										</p>
										<p>	
											Verdien man taster i rubr. 22 i SKJERMBILDET vil være grunnlaget man deklarer mot på varelinjenivå (i neste skjermbilde ).
											For å unngå differanse mellom vareverdi og deklarert verdi ved avsluttning av deklarasjonen, justeres ikke rubr. 22 med andre kostnader før ved sending/utskrift.
										</p>
										<p>
											HVIS MAN IKKE ØNSKER AT RUBR. 22 SKAL PÅVIRKES KAN MAN BENYTTE KODENE
											<b>M</b>(-) ELLER <b>P</b>(+)
											<br/><br/>
											Når man taster M eller P i feltet vil totalt fakturabeløp i rubr. 22 ikke påvirkes / endres, men det blir gjort fratrekk i "Statistisk verdi" som er grunnlaget for beregning av toll og avgifter.
										</p>
								</span>
								</div>
								</td>
			 					
			 					<td class="text14">
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sibel2" id="sibel2" size="12" maxlength="11" value="${model.record.sibel2NO}">
			 						<select class="inputTextMediumBlue" name="sival2" id="sival2">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.currencyCodeList}" >
					 				  		<option value="${record.kvakod}"<c:if test="${ model.record.sival2 == record.kvakod}"> selected </c:if> >${record.kvakod}</option>
									  	</c:forEach> 
									</select>
									<a tabindex="-1" id="sival2IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
									</a>
									
			 					</td>
							</tr>
							
							<tr>						
								<td class="text14" ><span title="si07">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Rabatt</span></td>
			 					<td class="text14">
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="si07" id="si07" size="12" maxlength="5" value="${model.record.si07}">
								</td>
							</tr>					        
							<tr height="25"><td class="text">&nbsp;</td> </tr>	
				            
					        <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('brut_info');" onMouseOut="hidePop('brut_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="sivkb">Bruttovekt</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="brut_info" class="popupWithInputText text11"  >
					           			<b>Bruttovekt</b>
								</span>	
								</div>
								</td>
					            <td >
				            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue"  name="sivkb" id="sivkb" size="10" maxlength="9" value="${model.record.sivkb}">
					            </td>
					        </tr>
				            <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>&nbsp;6.&nbsp;</b><span title="sintk">Antall Kolli</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
					           			<b>6. Antall Kolli</b>
					           			<br/><br/>
					           			Opplysningene overføres fra oppdragsregistreringen. 
					           			
								</span>	
								</div>
								</td>
					            <td >
					            	<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue"  name="sintk" id="sintk" size="8" maxlength="7" value="${model.record.sintk}">
					            </td>
					        </tr>
   					        <tr height="10"><td class="text">&nbsp;</td> </tr>
					        
					        <tr>						
								<td class="text14" ><span title="sikdh">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Havn</span></td>
			 					<td class="text14">
			 						<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sikdh" id="sikdh" size="5" maxlength="4" value="${model.record.sikdh}">
								</td>
							</tr>
							
							<%-- N/A (ref. samtal med Trond )
							<tr>						
								<td class="text14" ><span title="sidtg">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Dato</span></td>
			 					<td class="text14">
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sidtg" id="sidtg" size="12" maxlength="10" value="${model.record.sidtg}">
								</td>
							</tr>	
							<tr>						
								<td class="text14" ><span title="sitll">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Løpenr</span></td>
			 					<td class="text14">
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sitll" id="sitll" size="6" maxlength="5" value="${model.record.sitll}">
								</td>
							</tr>	
							<tr>						
								<td class="text14" ><span title="sitle">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Eksp.</span></td>
			 					<td class="text14">
			 						<input onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue" name="sitle" id="sitle" size="5" maxlength="4" value="${model.record.sitle}">
								</td>
							</tr>	
							 --%>
						 	<tr height="15"><td class="text">&nbsp;</td> </tr>
							<tr>
							<td class="text14">&nbsp;</td>
							<td class="text14" >
		    	    			<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre' onClick="setBlockUI(this);"/>
							</td>
							</tr>
							 
						</table>
						</td>
					</tr>
				</table>
			</td>
			</tr>			
				
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
			</table>
	 		</form>
	 		
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

