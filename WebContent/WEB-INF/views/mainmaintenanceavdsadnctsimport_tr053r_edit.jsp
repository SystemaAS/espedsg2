<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavdsadnctsimport_tr053r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<a id="alinkMainMaintAvdSadiSyftaaar" onClick="setBlockUI(this);" href="mainmaintenanceavdsadnctsimport_tr053r.do">
							<font class="tabDisabledLink">&nbsp;TVINN NCTS Import</font>&nbsp;
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
			<form action="mainmaintenanceavdsadnctsimport_tr053r_edit.do" name="formRecord" id="formRecord" method="POST" >
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
					    	    		<input type="hidden" name="tiavd" id="tiavd" value="${model.avd}">
					    	    	</c:when>
					    	    	<c:otherwise>
					    	    		<td class="text14" title="tiavd"><font class="text14RedBold" >*</font>Avdeling
					    	    			<a tabindex="-1" id="tiavdIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
					    	    		</td>
										<td class="text14" >
										<select name="tiavd" id="tiavd" class="inputTextMediumBlueMandatoryField">
						  					<option value="">-velg-</option>
						  					<c:forEach var="record" items="${model.avdGeneralList}" >
						 				  		<option value="${record.koaavd}"<c:if test="${ model.record.tiavd == record.koaavd}"> selected </c:if> >${record.koaavd}</option>
											  </c:forEach>  
											</select>
										</td>	
					    	    	</c:otherwise>
				    	    	</c:choose>
				    	    	
				    	    	
								<td class="text14" title="tienkl"><font class="text14RedBold" >*</font>Prosedyre</td>
								<td class="text14" >
									<select name="tienkl" id="tienkl" class="inputTextMediumBlueMandatoryField">
					  					<option value="J"<c:if test="${ model.record.tienkl == 'J' || empty model.record.tienkl}"> selected </c:if> >Forenklet</option>
					  					<option value="N"<c:if test="${ model.record.tienkl == 'N'}"> selected </c:if> >Normal</option>
								  	</select>
								</td>
								<td class="text14" title="titdn">&nbsp;&nbsp;<font class="text14RedBold" >*</font>Nr.teller intern ref.</td>
								<td class="text14" >
									<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="titdn" id="titdn" size="10" maxlength="7" value='${model.record.titdn}'>
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
						<tr><td colspan="2" class="text14"><b>Kommunikasjon </b><font class="text11" >&nbsp;</font></td></tr>
						<tr height="5"><td></td></tr>
			    	    
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
						</tr>
						<tr>	
							
							<td class="text14" title="s0026">&nbsp;&nbsp;Applikasjonsreferanse</td>
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
						
						<tr height="5"><td></td></tr>
					</table>
				</td>
			</tr>
			<tr height="20"><td></td></tr>
			
			<tr>
			<td width="5%">&nbsp;</td>
			<td>
				<table width="98%" cellspacing="1" border="0" align="left">
					<tr>
						<%-- LEFT SIDE  --%>
						<td width="50%" valign="top">
						<table width="100%" cellspacing="1" border="0" align="left">
						<tr>
				 			<td >		
				 				<%-- SENDER ---%>
				 				<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
							 		<tr height="15">
							 			<td class="text14White">
											&nbsp;<img onMouseOver="showPop('nliv_info');" onMouseOut="hidePop('nliv_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 			<b>&nbsp;</b>&nbsp;Næringsliv / Ansvarlig&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							 				<div class="text11" style="position: relative;" align="left">
							 				<span style="position:absolute; top:2px; width:250px;" id="nliv_info" class="popupWithInputText text11"  >
								           		<b>Næringsliv / Ansvarlig</b>
								           		<p>
								           		Næringslivets representant (Speditøren)Den som personlig eller gjennom en autorisert representant 
								           		søker tillatelse til å utføre en transittering og dermed stiller seg ansvarlig overfor tollmyndighetene for at transitteringen skjer i samsvar med bestemmelsene.
            									Legges vanligvis inn som standardverdi på avdelingen.
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
							 	<table width="95%" align="left" class="formFrame" border="0" cellspacing="2" cellpadding="1">
							 		<tr>
								 		<td width="100%">
									 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
										 		<tr >
										 			<td class="text14">&nbsp;<span title="tikn">Kundenr</span></td>
										 			<td class="text14">&nbsp;</td>
								 				</tr>
								 				<tr >
										 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="tikn" id="tikn" size="8" maxlength="8" value="${model.record.tikn}"></td>
										 			<td class="text14">&nbsp;</td>
								 				</tr>
								 				<tr >
										 			<td class="text14">&nbsp;<span title="titin">TIN</span></td>
										 			<td class="text14">&nbsp;<span title="tina">Navn</span>
											            <a tabindex="-1" id="tinaIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
														</a>
										 			</td>
								 				</tr>
								 				<tr >
										 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="titin" id="titin" size="17" maxlength="17" value="${model.record.titin}"></td>
										 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="tina" id="tina" size="30" maxlength="30" value="${model.record.tina}"></td>
								 				</tr>
								 				<tr >
										 			<td class="text14">&nbsp;<span title="tiad1">Adresse</span></td>
										 			<td class="text14">&nbsp;<span title="tisk">Språkkode</span></td>
								 				</tr>
								 				<tr >
										 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="tiad1" id="tiad1" size="30" maxlength="30" value="${model.record.tiad1}"></td>
										 			<td class="text14">
										 				<input type="text" class="inputText" name="tisk" id="tisk" size="3" maxlength="2" value="${model.record.tisk}">
										 			</td>
								 				</tr>
								 				<tr >
										 			<td class="text14">&nbsp;<span title="tips">Postadresse</span></td>
										 			<td class="text14">&nbsp;<span title="tipn">Postnr</span></td>
								 				</tr>
								 				<tr >
										 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="tips" id="tips" size="24" maxlength="24" value="${model.record.tips}"></td>
										 			<td class="text14"><input type="text" class="inputTextMediumBlue" name="tipn" id="tipn" size="9" maxlength="9" value="${model.record.tipn}"></td>
								 				</tr>
								 				<tr >
										 			<td class="text14">&nbsp;<span title="tilk">Landkode</span>
										 			
										 			</td>
										 			<td class="text14">&nbsp</td>
								 				</tr>
								 				<tr >
										 			<td>
										 				<input type="text" class="inputTextMediumBlue" name="tilk" id="tilk" size="3" maxlength="2" value="${model.record.tilk}">
									 				</td>
										 			<td class="text14">
										 				&nbsp;
										 			</td>
								 				</tr>
									        </table>
								        </td>
							        </tr>
								</table>         
			            	</td>
			           	</tr> 
			           	<tr height="10"><td></td></tr>
			           	
						</table>
						</td>
						
						<%-- RIGHT SIDE --%>
						<td width="50%" valign="top">
						<table width="100%" cellspacing="1" border="0" align="left">
							<tr>
								<td valign="top">
								<table border="0" cellspacing="1" cellpadding="0">
								<tr>
						            <td >&nbsp;</td>
						            <td class="text14" >
						            	<img onMouseOver="showPop('tign_info');" onMouseOut="hidePop('tign_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            	<span title="tign">Godsnr</span>
						            	<div class="text11" style="position: relative;" align="left">
					 					<span style="position:absolute; top:2px; width:250px;" id="tign_info" class="popupWithInputText text11"  >
							           			<b>Godsnr</b>
							           			<p>
							           			 	Legg inn sendingens godsnr.
										            Oppbygging av godsnr.:
										            <ul>
										            <li><b>1.- 4.</b> karakter: Årstall for registrering</li>
										            <li><b>5.- 6.</b> karakter: Tolldistriktskode</li>
										            <li><b>7.- 9.</b> karakter:Tollagerkode</li>
										            <li><b>10.-12.</b> karakter: Lossedag, kalenderens dagnr.</li>
										            <li><b>13.-15.</b> karakter: lossenr, fortløpende listenr, pr. tollager og dag.</li>
										            </ul>
							           			</p>
										</span>		
							            </div>
						            </td>
						            <td colspan="3" ><input type="text" class="inputTextMediumBlue" name="tign" id="tign" size="36" maxlength="35" value="${model.record.tign}"></td>
					            </tr>
								<tr>
						            <td >&nbsp;</td>
						            <td class="text14" >&nbsp;&nbsp;<span title="tignsk">Språkkode</span>&nbsp;</td>
						            <td >
							             <input type="text" class="inputText" name="tignsk" id="tignsk" size="3" maxlength="2" value="${model.record.tignsk}">
						            </td>
					            </tr>
					            <tr>
						            <td >&nbsp;</td>
						            <td class="text14" >
						            	<img onMouseOver="showPop('titrnr_info');" onMouseOut="hidePop('titrnr_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            	<span title="titrnr">MRN-nr</span>
						            	<div class="text11" style="position: relative;" align="left">
					 					<span style="position:absolute; top:2px; width:250px;" id="titrnr_info" class="popupWithInputText text11"  >
							           			<b>Oppgi MRN-nr. for sendingen.</b>
							           			<p> 			 	 
										            MRN-nr. (Movement Reference Number) er bygd opp av.:
										            årstallets siste 2 siffer (YY),
										            tollstedets referansenr. (eks.NO11091B)
										            et 7-sifret løpenr. og kontrollsiffer.
										            Dette er referansen som følger transitteringen helt til den er avsluttet.
							           			</p>
										</span>		
							            </div>
						            </td>
						            <td colspan="3" ><input type="text" class="inputTextMediumBlue" name="titrnr" id="titrnr" size="36" maxlength="18" value="${model.record.titrnr}"></td>
					            </tr>
					            <tr>
						            <td >&nbsp;</td>
						            <td class="text14" >&nbsp;&nbsp;<span title="tialk">Avs.land</span>&nbsp;</td>
						            <td >
							            <input type="text" class="inputText" name="tialk" id="tialk" size="3" maxlength="2" value="${model.record.tialk}">
						            </td>
					            </tr>
					            <tr height="2"><td>&nbsp;</td></tr>
					            <tr>
						            <td >&nbsp;</td>
						            <td class="text14" ><img onMouseOver="showPop('titsb_info');" onMouseOut="hidePop('titsb_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="titsb">Freml.tollsted</span>
						            <div class="text11" style="position: relative;" align="left">
					 					<span style="position:absolute; top:2px; width:250px;" id="titsb_info" class="popupWithInputText text11"  >
							           			<b>Fremleggings tollsted</b>
							           			<p> 			 	 
										             Fremleggings tollsted Kode for mottagende tollsted. 
										             Merk at dette er tollstedskoden som er knyttet opp mot Ansvarlig's organisasjonsnr., ikke nødvendigvis stedet varene kommer til.
							           			</p>
										</span>		
						            </div>
						            
						            
						            </td>
						            <td ><input type="text" class="inputTextMediumBlue" name="titsb" id="titsb" size="9" maxlength="8" value="${model.record.titsb}">
						            	<a tabindex="-1" id="titsbIdLink">
											<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
										</a>
							        </td>
						            <td class="text14" ><span title="tiskb">Språkkode</span>&nbsp;</td>
						            <td >
							            <input type="text" class="inputText" name="tiskb" id="tiskb" size="3" maxlength="2" value="${model.record.tiskb}">
						            </td>
					            </tr>
					            
					            <tr height="10"><td>&nbsp;</td></tr>
					            <tr>
						            <td >&nbsp;</td>
						            <td class="text14" ><img onMouseOver="showPop('tialsk_info');" onMouseOut="hidePop('tialsk_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="tialsk">Avt.lagringssted (kode)</span>
						            <div class="text11" style="position: relative;" align="left">
					 					<span style="position:absolute; top:2px; width:250px;" id="tialsk_info" class="popupWithInputText text11"  >
							           			<b>Avtalt lagringssted kode</b>
										</span>		
						            </div>
						            </td>
						            <td ><input type="text" class="inputText" name="tialsk" id="tialsk" size="17" maxlength="17" value="${model.record.tialsk}"></td>
						            <td class="text14" ><span title="tialss">Språkkode</span>&nbsp;</td>
						            <td >
						            	<input type="text" class="inputText" name="tialss" id="tialss" size="3" maxlength="2" value="${model.record.tialss}">
						            </td>
					            </tr>
					            <tr>
						            <td >&nbsp;</td>
						            <td class="text14" >&nbsp;&nbsp;<span title="tials">Avt.lagringssted</span></td>
						            <td ><input type="text" class="inputText" name="tials" id="tials" size="20" maxlength="35" value="${model.record.tials}"></td>
					            </tr>
					            <tr height="2"><td>&nbsp;</td></tr>
					            <tr>
						            <td >&nbsp;</td>
						            <td class="text14" ><img onMouseOver="showPop('tiglsk_info');" onMouseOut="hidePop('tiglsk_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="tiglsk">Godk.lagringssted (kode)</span>
						            <div class="text11" style="position: relative;" align="left">
					 					<span style="position:absolute; top:2px; width:250px;" id="tiglsk_info" class="popupWithInputText text11"  >
							           			<b>Godkjent lagringssted</b>
										</span>		
						            </div>
						            
						            </td>
						            <td ><input type="text" class="inputText" name="tiglsk" id="tiglsk" size="17" maxlength="17" value="${model.record.tiglsk}"></td>
					            </tr>
					            <tr>
						            <td >&nbsp;</td>
						            <td class="text14" ><img onMouseOver="showPop('tiacts_info');" onMouseOut="hidePop('tiacts_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            <span title="tiacts">Contr.sted (kode)</span>
						            <div class="text11" style="position: relative;" align="left">
					 					<span style="position:absolute; top:2px; width:250px;" id="tiacts_info" class="popupWithInputText text11"  >
							           			<b>Control sted</b>
							           			<p>
												Disse feltene er foreløpig ikke i bruk, men skal tas i bruk i en framtidig versjon av NCTS/TET.
												</p>
										</span>		
						            </div>
						            </td>
						            <td ><input type="text" class="inputText" name="tiacts" id="tiacts" size="17" maxlength="17" value="${model.record.tiacts}"></td>
					            </tr>
					            <tr>
						            <td >&nbsp;</td>
						            <td class="text14" ><span title="tidt">Registreringsdato</span></td>
						            <td ><input readonly type="text" class="inputTextReadOnly" name="tidt" id="tidt" size="8" maxlength="6" value="${model.record.tidtNO}"></td>
					            </tr>
					            <tr height="2"><td>&nbsp;</td></tr>
					            <tr >	
				            		<td class="text">&nbsp;</td>
				            		<td class="text">&nbsp;</td>

				 				    <td class="text9BlueGreen" valign="bottom"  >
					 				    	<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre' onClick="setBlockUI(this);"/>
					 				</td>    	 	
						        </tr>
						        </table>
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

