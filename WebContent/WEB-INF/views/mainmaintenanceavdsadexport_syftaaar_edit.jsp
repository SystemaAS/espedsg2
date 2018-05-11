<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavdsadexport_syftaaar_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<td width="18%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdSadiSyftaaar" onClick="setBlockUI(this);" href="mainmaintenanceavdsadexport_syftaaar.do">
							<font class="tabDisabledLink">&nbsp;TVINN SAD Eksport</font>&nbsp;
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
					<td width="41%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</tr>
		</table>
		</td>
	</tr>
	

	<tr>
		<td>
		<%-- space separator --%>
			<form action="mainmaintenanceavdsadexport_syftaaar_edit.do" name="formRecord" id="formRecord" method="POST" >
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
					    	    		<input type="hidden" name="seavd" id=seavd value="${model.avd}">
					    	    	</c:when>
					    	    	<c:otherwise>
					    	    		<td class="text14" title="seavd"><font class="text14RedBold" >*</font>Avdeling
					    	    			<a tabindex="-1" id="seavdIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
					    	    		</td>
										<td class="text14" >
										<select name="seavd" id="seavd" class="inputTextMediumBlueMandatoryField">
						  					<option value="">-velg-</option>
						  					<c:forEach var="record" items="${model.avdGeneralList}" >
						 				  		<option value="${record.koaavd}"<c:if test="${ model.record.seavd == record.koaavd}"> selected </c:if> >${record.koaavd}</option>
											  </c:forEach>  
											</select>
											
									  	</select>
										</td>	
					    	    	</c:otherwise>
				    	    	</c:choose>
				    	    	
				    	    	
								<td class="text14" title="sedl"><font class="text14RedBold" >*</font>Bruk dataliste</td>
								<td class="text14" >
									<select name="sedl" id="sedl" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.sedl == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.sedl == 'N'}"> selected </c:if> >Nei</option>
					  					<option value="1"<c:if test="${ model.record.sedl == '1'}"> selected </c:if> >1</option>
								  	</select>
								</td>
								<td class="text14" title="sea4">&nbsp;&nbsp;Format A4</td>
								<td class="text14" >
									<select name="sea4" id="sea4" class="inputTextMediumBlue">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.sea4 == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.sea4 == 'N'}"> selected </c:if> >Nei</option>
								  	</select>
								</td>	
								<td class="text14" title="setdn">&nbsp;&nbsp;<font class="text14RedBold" >*</font>Nrt.teller intern ref.</td>
								<td class="text14" >
									<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="setdn" id="setdn" size="10" maxlength="7" value='${model.record.setdn}'>
								</td>
								
								<td class="text14" title="seekst">&nbsp;&nbsp;Importere eksterne data</td>
								<td class="text14" >
									<select name="seekst" id="seekst" class="inputTextMediumBlue">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.seekst == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.seekst == 'N'}"> selected </c:if> >Nei</option>
					  					<option value="R"<c:if test="${ model.record.seekst == 'R'}"> selected </c:if> >R</option>
								  	</select>
								</td>
							</tr>
							<tr>
								<td class="text14" title="setolk"><font class="text14RedBold" >*</font>Bruk kommunikasjon</td>
								<td class="text14" >
									<select name="setolk" id="setolk" class="inputTextMediumBlueMandatoryField">
					  					<option value="J"<c:if test="${ model.record.setolk == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.setolk == 'N' || empty model.record.setolk}"> selected </c:if> >Nei</option>
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
			    	    	<%-- OBSOLETE ? AS400 SCREEN ... ta en titt
							<td class="text14" title="todo">Vente med å skrive ut melding </td>
							<td class="text14">
								<select name="todo" id="todo" class="inputTextMediumBlue">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ XXmodel.record.X == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ XXmodel.record.X == 'N'}"> selected </c:if> >Nei</option>
							  	</select>
							</td>
							 --%>
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
								<select name="selv2" id="selv2" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.selv2 == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.selv2 == 'N'}"> selected </c:if> >Nei</option>
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
 					<b>1.1</b><span title="sedty">Dekl.type&nbsp;</span>
 					</td>
 					<td class="text14" >
 					<select class="inputTextMediumBlue" name="sedty" id="sedty" >
		 				  <option value="">-velg-</option>
						  <option value="EU"<c:if test="${model.record.sedty == 'EU'}"> selected </c:if> >EU</option>
						  <option value="EX"<c:if test="${model.record.sedty == 'EX'}"> selected </c:if> >EX</option>
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
		 				<b>1.2</b><span title="sedp">&nbsp;Eksped.type</span>
		 			</td>
		 			<td>	
		 				<select class="inputTextMediumBlue" name="sedp" id="sedp" >
		 				  <option value="">-velg-</option>
			 				  	<c:forEach var="record" items="${model.ekspedisjonstyperExportCodeList}" >
			 				  		<option value="${record.ks9typ}"<c:if test="${model.record.sedp == record.ks9typ}"> selected </c:if> >${record.ks9typ}</option>
								</c:forEach>  
						</select>
			 			<div class="text11" style="position: relative;display:inline;" align="left">
		 				<span style="position:absolute; top:5px; left:-130px;" id="prosedyr_info" class="popupWithInputText text11">
			           		<ul>
			           			<c:forEach var="record" items="${model.ekspedisjonstyperExportCodeList}" >
			           			<li><b>${record.ks9typ}</b>&nbsp;${record.ks9ftx}</li>
			           			</c:forEach>
			           		</ul>
						</span>
						</div>
	 				</td>
	 			</tr>
 				<tr>
 					<td class="text14">&nbsp;&nbsp;&nbsp;
		 				<span title="seski" id="v_seski" class="validation">Toll/Mva</span>
		 			</td>
		 			<td class="text14" >	
		 				<select name="seski" id="seski" class="inputTextMediumBlue">
	 						<option value="">-velg-</option>
					  		<option value="S"<c:if test="${ model.record.seski == 'S'}"> selected </c:if> >S</option>
					  		<option value="K"<c:if test="${ model.record.seski == 'K'}"> selected </c:if> >K</option>
					  		<option value="I"<c:if test="${ model.record.seski == 'I'}"> selected </c:if> >I</option>
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
	 					<span title="sekddk">&nbsp;Dagsopp./Kontant</span>
	 				</td>
	 				<td class="text14" >	
		 				<select name="sekddk" id="sekddk" >
		 				  <option selected value="">-velg-</option>
						  <option value="D"<c:if test="${model.record.sekddk == 'D'}"> selected </c:if> >D</option>
						  <option value="K"<c:if test="${model.record.sekddk == 'K'}"> selected </c:if> >K</option>
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
										        	<input type="hidden" name="orig_sikns" id="orig_sikns" value='${XXmodel.record.sikns}'>
										        	<input type="hidden" name="orig_sinas" id="orig_sinas" value='${XXmodel.record.sinas}'>
										        	<input type="hidden" name="orig_siads1" id="orig_siads1" value='${XXmodel.record.siads1}'>
										        	<input type="hidden" name="orig_siads2" id="orig_siads2" value='${XXmodel.record.siads2}'>
										        	<input type="hidden" name="orig_siads3" id="orig_siads3" value='${XXmodel.record.siads3}'>
										        	
										            <td class="text14" align="left" >&nbsp;&nbsp;
											            <span title="sekns">Kundenummer</span>
											       
										            </td>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="senak" id="v_senak" class="validation">Navn&nbsp;</span>
										            	<a tabindex="-1" id="senakIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
														</a>				
										            </td>
										        </tr>
										        <tr>
										            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="seknk" id="seknk" size="9" maxlength="8" value="${model.record.seknk}"></td>
										            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue"  name="senak" id="senak" size="31" maxlength="30" value="${model.record.senak}"></td>
										        </tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="serg" id="v_serg" class="validation">Regnr.</span></td>
										            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue"  name="serg" id="serg" size="12" maxlength="11" value="${model.record.serg}"></td>
			    							            
										        </tr>
										        <tr height="4"><td>&nbsp;</td></tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="seadk1" id="v_siadk1" class="validation">Adresse-1</span></td>
										            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue"  name="seadk1" id="seadk1" size="40" maxlength="30" value="${model.record.seadk1}"></td>
			    							            
										        </tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="seadk2" id="v_seadk2" class="validation">Adresse-2</span></td>
			    							            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="seadk2" id="seadk2" size="40" maxlength="30" value="${model.record.seadk2}"></td>
			   							            
										        </tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="seadk3" id="v_seadk3" class="validation">Adresse-3</span></td>
			   							            <td>&nbsp;</td>							            
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="seadk3" id="seadk3" size="40" maxlength="30" value="${model.record.seadk3}"></td>
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
										        	<input type="hidden" name="orig_siknk" id="orig_siknk" value='${XXmodel.record.siknk}'>
										        	<input type="hidden" name="orig_sinak" id="orig_sinak" value='${XXmodel.record.sinak}'>
										        	<input type="hidden" name="orig_sirg" id="orig_sirg" value='${XXmodel.record.sirg}'>
										        	<input type="hidden" name="orig_siadk1" id="orig_siadk1" value='${XXmodel.record.siadk1}'>
										        	<input type="hidden" name="orig_siadk2" id="orig_siadk2" value='${XXmodel.record.siadk2}'>
										        	<input type="hidden" name="orig_siadk3" id="orig_siadk3" value='${XXmodel.record.siadk3}'>
										        	
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="sekns">Kundenummer</span>
										            	
										            </td>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="senas" id="v_senas" class="validation">Navn&nbsp;</span>
										            	<a tabindex="-1" id="senasIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
														</a>			
										            </td>
										        </tr>
										        <tr>
										            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="sekns" id="sekns" size="9" maxlength="8" value="${model.record.sekns}"></td>
										            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue"  name="senas" id="senas" size="31" maxlength="30" value="${model.record.senas}"></td>
										        </tr>
										        <tr height="10"><td></td></tr>
										        
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="seads1" id="v_seads1" class="validation">Adresse-1</span></td>
										            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="seads1" id="seads1" size="40" maxlength="30" value="${model.record.seads1}"></td>
			    							            
										        </tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="seads2" id="v_seads2" class="validation">Adresse-2</span></td>
			    							            <td>&nbsp;</td>
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="seads2" id="seads2" size="40" maxlength="30" value="${model.record.seads2}"></td>
			   							            
										        </tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;
										            <span title="seads3" id="v_seads3" class="validation">Adresse-3</span></td>
			   							            <td>&nbsp;</td>							            
										        </tr>
										        <tr>
										            <td colspan="2" align="left"><input type="text" class="inputTextMediumBlue" name="seads3" id="seads3" size="40" maxlength="30" value="${model.record.seads3}"></td>
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
									            <span title="senad">Navn</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="setlf">Telefon</span></td>
									        </tr>
									        <tr>
									            <td align="left"><input  type="text" class="inputTextMediumBlue" name="senad" id="senad" size="35" maxlength="30" value="${model.record.senad}"></td>
									            <td align="left"><input  type="text" class="inputTextMediumBlue" name="setlf" id="setlf" size="15" maxlength="12" value="${model.record.setlf}"></td>
									        </tr>
									        <tr height="10"><td></td></tr>
									        <tr>
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="sedst">Sted</span></td>
									            <td class="text14" align="left" >&nbsp;&nbsp;
									            <span title="sedt">Reg.dato</span></td>
									        </tr>
									        <tr>
									            <td align="left"><input  type="text" class="inputTextMediumBlue" name="sedst" id="sedst" size="16" maxlength="15" value="${model.record.sedst}"></td>
									            <td align="left"><input readonly type="text" class="inputTextReadOnly" name="sedt" id="sedt" size="8" maxlength="6" value="${model.record.sedtNO}"></td>
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
							 				<span title="sebel1" id="v_sebel1" class="validation">Fakturasum&nbsp;</span>
							 			</td>
							 			<td align="left" >
							 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue"  name="sebel1" id="sebel1" size="20" maxlength="13" value="${model.record.sebel1NO}">				 				
							 			</td>
							 			<td class="text14" align="left">
							 				&nbsp;
							 				<span title="seval1" id="v_seval1" class="validation">Valuta</span>
							 				<%-- Note: onChange event in jQuery for this currency list --%>
							 				<select class="inputTextMediumBlue" name="seval1" id="seval1" >
							 				  <option value="">-velg-</option>	
							 				  <c:forEach var="record" items="${model.currencyCodeList}" >
						 				  		<option value="${record.kvakod}"<c:if test="${ model.record.seval1 == record.kvakod}"> selected </c:if> >${record.kvakod}</option>
											  </c:forEach>  
											</select>
											<%--
											<a tabindex="-1" id="seval1IdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
											 --%>
						 				</td>
					 				</tr>
					 				<tr>
						 				<td class="text14">
						 					<b>&nbsp;23.</b><span title="sevku">Kurs&nbsp;</span>
							 			</td>
							 			<td class="text14" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue"  name="sevku" id="sevku" size="10" maxlength="7" value="${model.record.sevkuNO}"></td>
							 			
							 			<td class="text14" align="left" >&nbsp;
							 				<img onMouseOver="showPop('24_info');" onMouseOut="hidePop('24_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<b>24.</b><span title="setst">Tr.type</span>
							 				<select class="inputTextMediumBlueMandatoryField" name="sitst" id="sitst" >
							 				  <option value="">-velg-</option>	
							 				  <option value="1" <c:if test="${model.record.setst == '1'}"> selected </c:if> >1</option>	
							 				  <option value="2" <c:if test="${model.record.setst == '2'}"> selected </c:if> >2</option>	
							 				  <option value="3" <c:if test="${model.record.setst == '3'}"> selected </c:if> >3</option>	
							 				  <option value="4" <c:if test="${model.record.setst == '4'}"> selected </c:if> >4</option>	
							 				  <option value="5" <c:if test="${model.record.setst == '5'}"> selected </c:if> >5</option>	
							 				  <option value="6" <c:if test="${model.record.setst == '6'}"> selected </c:if> >6</option>	
							 				  <option value="7" <c:if test="${model.record.setst == '7'}"> selected </c:if> >7</option>	
							 				  <option value="8" <c:if test="${model.record.setst == '8'}"> selected </c:if> >8</option>	
							 				  <option value="9" <c:if test="${model.record.setst == '9'}"> selected </c:if> >9</option>	
							 				</select>
							 				<div class="text11" style="position: relative;" align="left">
							 				<span style="position:absolute; top:2px; width:250px;" id="24_info" class="popupWithInputText text11"  >
								           		<b>24.&nbsp;Transaksjonstype</b><br/><br/>
												Oppgi med kode:
												<ul>
													<li><b>1</b>&nbsp;Kjøp i fast regning</li>
													<li><b>2</b>&nbsp;Konsignasjon/Kommisjon</li>
													<li><b>3</b>&nbsp;Leie (herunder leasing)Lån</li>
													<li><b>4-8</b>&nbsp;TODO</li>
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
							 			<td class="text14"><b>&nbsp;31.</b><span title="seft1/seft2" >Fritekst&nbsp;</span></td>
							 			<td align="left" ><input type="text" class="inputTextMediumBlue"  name="seft1" id="seft1" size="40" maxlength="45" value="${model.record.seft1}"></td>
						 			</tr>
						 			<tr>
							 			<td class="text14"><span title="seft2" >&nbsp;</span></td>
							 			<td align="left" ><input type="text" class="inputTextMediumBlue"  name="seft2" id="seft2" size="40" maxlength="45" value="${model.record.seft2}"></td>
						 			</tr>
						 			<tr height="3"><td></td></tr>
						 			<tr>
							 			<td class="text14"><b>&nbsp;44.</b><span title="seft3" >Fritekst&nbsp;</span></td>
							 			<td align="left" ><input type="text" class="inputTextMediumBlue"  name="seft3" id="seft3" size="40" maxlength="45" value="${model.record.seft3}"></td>
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
			 					<b>15.</b>&nbsp;<span title="selka">Avs/utf.land</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="15_info" class="popupWithInputText text11"  >
					           			<b>15. Avs/utf.land</b>
					           			<br/><br/>
					           			Med avsenderland forstås det land hvorfra varen er sendt til Norge uten mellomliggende handelstransaksjon (omlasting undderveis endrer ikke forholdet)
								</span>	
								</div>
									
					             </td>
					            <td >
					            	<input type="text" class="inputTextMediumBlue" name="selka" id="selka" size="4" maxlength="2" value="${model.record.selka}">
				            																										 			
								</td>
							</tr>
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('17_info');" onMouseOut="hidePop('17_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>17.</b>&nbsp;<span title="selkb">Best.land</span>&nbsp;
					            <div class="text11" style="position: relative;" align="left">
			 					<span style="position:absolute; top:2px; width:250px;" id="17_info" class="popupWithInputText text11"  >
					           			<b>17. Bestemmelsesland</b>
										<p>					           			
										Tast gyldig landkode. F.eks. DE=Tyskland. Oppgi det endelige bestemmelseslandet for varen.
										</p>
										<ul>
										<li>Ved overføring av varer til Svalbard og Jan Mayen samt ved utførsel til skip, luftfartøyer og oljeplattformer registrert i Norge i utenriks fart oppgis Norge som bestemmelsesland. 
										Det samme gjelder ved utførsel til norske stasjoner i utlandet (ambassader o.l.).</li>
										<li>Ved utførsel av varer til skip, luftfartøyer og oljeplattformer registrert i utlandet, blir bestemmelsesland det land hvor skipet, luftfartøyet eller oljeplattformen er registrert.</li>
										<li>Ved utførsel av varer til skip, luftfartøyer og oljeplattformer under bygging i utlandet for norsk regning, oppgis det land hvor byggingen finner sted som bestemmelsesland.</li>
										</ul>
								</span>	
								</div>
									
					             </td>
					            <td >
					            	<input type="text" class="inputTextMediumBlue" name="selkb" id="selkb" size="4" maxlength="2" value="${model.record.selkb}">
				            																										 			
								</td>
							</tr>
							<tr height="8"><td class="text"></td> </tr>
							
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('19_info');" onMouseOut="hidePop('19_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>19.</b><span title="sekdc" id="v_sekdc" class="validation">Container&nbsp;</span>
					            
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
			           				<select name="sekdc" id="sekdc">
				 						<option value="0" <c:if test="${model.record.sekdc == '0'}"> selected </c:if> >0</option>
				 						<option value="1" <c:if test="${model.record.sekdc == '1'}"> selected </c:if> >1</option>								 				  	  
									</select>
			           			</td>
							</tr>
							<tr height="10"><td class="text"></td> </tr>
							
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('21_1_info');" onMouseOut="hidePop('21_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>21.1</b><span title="setrid" id="v_setrid" class="validation">Transp.ID</span>
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
					            	<input type="text" class="inputTextMediumBlue" name="setrid" id="setrid" size="21" maxlength="20" value="${model.record.setrid}">
								</td>
							</tr>
							
							
							<tr>
					            <td class="text14" align="left">
					            <img onMouseOver="showPop('21_2_info');" onMouseOut="hidePop('21_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>21.2</b>
					            <span title="selkt">Aktive transp. nasjonalitet&nbsp;</span>
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
									<input type="text" class="inputTextMediumBlue" name="selkt" id="selkt" size="4" maxlength="2" value="${model.record.selkt}">
								</td>
					        </tr>
							
							
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('25_info');" onMouseOut="hidePop('25_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>25.</b>
					            <span title="setrm" id="v_setrm" class="validation">Transportmåte ved grensen</span>
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
					            	<select class="inputTextMediumBlue" name="setrm" id="setrm">
			 						<option value="">-velg-</option>
				 				  	<c:forEach var="code" items="${model.transportmaterCodeList}" >
				 				  		<option value="${code.ks4trm}"<c:if test="${model.record.setrm == code.ks4trm}"> selected </c:if> >${code.ks4trm}</option>
									</c:forEach> 
									</select>
					            	
			           			</td>
							</tr>
							
							
							<tr>
								<td class="text14" align="left" >
					            <img onMouseOver="showPop('49_info');" onMouseOut="hidePop('49_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>49.&nbsp;</b><span title="segn">Godsnr</span>
					            &nbsp;<input type="text" class="inputTextMediumBlue"  name="segn" id="segn" size="20" maxlength="15" value="${model.record.segn}">
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="49_info" class="popupWithInputText text11"  >
					           			<b>49. Godsnr</b><br/>
					           			<p>
						           			Skal oppgis ved utførsel av godsregistrerte varer.
						           			God snr og posisjonsnr overføres fra oppdraget.
					           			</p>
										<p>
											Oppbygging av godsnr:
											<b>1.- 4.</b> karakter: Årstall for registrering
											<b>5.- 6.</b> karakter: Tolldistriktskode
											<b>7.- 9.</b> karakter: Tollagerkode
											<b>10.-12.</b> karakter: Lossedag, kalenderens dagnr.
											<b>13.-15.</b> karakter: lossenr, fortløpende listenr, pr. tollager og dag
										</p>
								</span>	
								</div>
								
								</td>
								<td class="text14" align="left" ><span title="sepos">Posisjon</span>
					            	&nbsp;<input type="text" class="inputTextMediumBlue" name="sepos" id="sepos" size="15" maxlength="8" value="${model.record.sepos}">
					            </td>
					        </tr>
							<tr height="5"><td class="text"></td></tr>
							<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('29_info');" onMouseOut="hidePop('29_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					            <b>29.</b>
					            <span title="sekdh/sekdft" id="v_sekdh" class="validation"><font class="text14">Utpass.sted&nbsp;</font></span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute;top:2px; width:250px;" id="29_info" class="popupWithInputText text11"  >
					           			<b>29. Utpasseringsted</b>
					           			<p>
					           			Oppgi kode for utpasseringssted.
					           			</p>
								</span>
								</div>
								</td>		
					            <td class="text14" >
					            	<input onKeyPress="return numberKey(event)" type="text" class="inputText" name="sekdh" id="sekdh" size="5" maxlength="4" value="${model.record.sekdh}">
			           				<%--
			           				<select class="inputTextMediumBlue" name="sekdh" id="sekdh">
				 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${Xmodel.havnCodeList}" >
					 				  		<c:choose>
					 				  			<c:when test="${fn:contains(Xmodel.record.sekdh,'@')}">
					 				  				<%-- Usually when a validation error occur 
					 				  				<option value="${record.zkod}@${record.ztxt}"<c:if test='${fn:substringBefore(model.record.sekdh, "@") == record.zkod && fn:substringAfter(model.record.sekdh, "@") == record.ztxt}'> selected </c:if> >${record.ztxt}&nbsp;${record.zkod}</option>
					 				  			</c:when>
					 				  			<c:otherwise>
					 				  				<%-- Usually when a normal doFetch occur 
					 				  				<option value="${record.zkod}@${record.ztxt}"<c:if test='${model.record.sekdh == record.zkod && (model.record.sekdft == record.ztxt)}'> selected </c:if> >${record.ztxt}&nbsp;${record.zkod}</option>
					 				  			</c:otherwise>
					 				  		</c:choose>
										</c:forEach>  
									</select>
									 --%>
									&nbsp;<input type="text" class="inputText" name="sekdft" id="sekdft" size="17" maxlength="17" value="${model.record.sekdft}">
			           			</td>
							</tr>
							<tr height="5"><td class="text"></td></tr>
							
							<tr>
				            <td class="text14" align="left" >
				            <img onMouseOver="showPop('20_1_info');" onMouseOut="hidePop('20_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
		 					<b>20.1</b><span title="selv">Leveringksvilkår kode</span>
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
				            	<select class="inputTextMediumBlue" name="selv" id="selv">
			 						<option value="">-velg-</option>
					 				  	<c:forEach var="record" items="${model.incotermsCodeList}" >
					 				  		<option value="${record.klbkod}"<c:if test="${model.record.selv == record.klbkod}"> selected </c:if> >${record.klbkod}</option>
										</c:forEach>  
								</select>
							</td>
							</tr>
			 			
			 				<tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('20_2_info');" onMouseOut="hidePop('20_2_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 					<b>20.2</b>
			 					<span title="selvt">Leveringksvilkår sted</span>&nbsp;
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
					            	<input type="text" class="inputTextMediumBlue"  name="selvt" id="selvt" size="20" maxlength="17" value="${model.record.selvt}">
								</td>
							</tr>
							<tr height="5"><td></td></tr>
							
							
							<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
			 				<tr height="5"><td class="text">&nbsp;</td> </tr>
			 				<tr>
			            		<td class="text14" align="left" >
					            <img onMouseOver="showPop('30_1_info');" onMouseOut="hidePop('30_1_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>30.1&nbsp;</b><span title="sekdls">Lagringssted kode</span></td>
					            <td >
						          <input type="text" class="inputTextMediumBlue"  name="sekdls" id="sekdls" size="4" maxlength="2" value="${model.record.sekdls}">  
					            
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
					            <b>30.2&nbsp;</b><span title="sels">Lagringssted tekst</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="30_2_info" class="popupWithInputText text11"  >
					           			<b>30. Lagringssted tekst</b>
					           			<br/><br/>
					           			Selve tekstfeltet oppdateres automatisk når man taster / plukker en gyldig kode.
								</span>	
								</div>
								</td>
					            <td ><input type="text" class="inputTextMediumBlue" name="sels" id="sels" size="20" maxlength="16" value="${model.record.sels}"></td>

        					</tr>
        					<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
				            <tr height="20"><td class="text">&nbsp;</td> </tr>
							
							
					        <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('brut_info');" onMouseOut="hidePop('brut_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <span title="sevkb">Bruttovekt</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="brut_info" class="popupWithInputText text11"  >
					           			<b>Bruttovekt</b>
								</span>	
								</div>
								</td>
					            <td >
				            		<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue"  name="sevkb" id="sevkb" size="10" maxlength="9" value="${model.record.sevkb}">
					            </td>
					        </tr>
				            <tr>
					            <td class="text14" align="left" >
					            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
					            <b>&nbsp;6.&nbsp;</b><span title="sentk">Antall Kolli</span>
					            <div class="text11" style="position: relative;" align="left">
					            <span style="position:absolute; top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
					           			<b>6. Antall Kolli</b>
					           			<br/><br/>
					           			Opplysningene overføres fra oppdragsregistreringen. 
					           			
								</span>	
								</div>
								</td>
					            <td >
					            	<input onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextMediumBlue"  name="sentk" id="sentk" size="8" maxlength="7" value="${model.record.sentk}">
					            </td>
					        </tr>
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

