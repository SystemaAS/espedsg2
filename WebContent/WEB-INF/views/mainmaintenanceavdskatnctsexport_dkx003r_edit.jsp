<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavdskatnctsexport_dkx003r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
							<font class="tabDisabledLink">&nbsp;Vedligehold</font>
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
							</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="13%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;Afdelinger</font>&nbsp;						
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>

					<c:choose> 
		    			<c:when test="${model.id=='DKXSTD'}">
		    				<td width="18%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdSadiSyftaaar" onClick="setBlockUI(this);" href="mainmaintenanceavdskatnctsexport_dkx003r.do?id=${model.id}">
	                       			<font class="tabDisabledLink">&nbsp;<spring:message code="systema.skat.ncts.export.tab.description.avd"/></font>&nbsp;
								</a>
							</td>
	                    </c:when>
	                    <c:otherwise> <!--  TRUST_FHV = NCTS Forhåndsvarsling -->
							<td width="28%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdSadiSyftaaar" onClick="setBlockUI(this);" href="mainmaintenanceavdskatnctsexport_dkx003r.do?id=${model.id}">
	                        		<font class="tabLink">&nbsp;<spring:message code="systema.skat.ncts.import.tab.description.avd.forhandsvarsling"/></font>&nbsp;
								</a>
							</td>
	                    </c:otherwise>
			         </c:choose>

					<c:choose>
						<c:when test="${not empty model.updateId}">
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="16%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Afd.</font>&nbsp;
								<font class="text11MediumBlue">(${model.avd})</font>
							</td>
						</c:when>
						<c:otherwise>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="16%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Skabe ny afd.</font>&nbsp;
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
			<form action="mainmaintenanceavdskatnctsexport_dkx003r_edit.do?id=${model.id}" name="formRecord" id="formRecord" method="POST" >
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
						<input type="hidden" name="id" id=id value="${model.id}">
						
						<input type="hidden" name="action" id=action value="${model.action}">
						<table cellspacing="1" border="0" align="left">
						
							<c:if test="${not empty model.updateId}">
								<tr >
									<td colspan="4" class="text14MediumBlue" title=".">Afd.navn&nbsp;&nbsp;&nbsp;
										<input readonly class="inputTextReadOnly" id="avdnavn" name="avdnavn" type="text" size="45" value="${model.avdnavn}">
									</td>
								</tr>
								<tr height="5"><td >&nbsp;</td></tr>
							</c:if>
							
				    	    <tr>
				    	    	<c:choose>
					    	    	<c:when test="${not empty model.updateId}">
					    	    		<input type="hidden" name="thavd" id="thavd" value="${model.avd}">
					    	    	</c:when>
					    	    	<c:otherwise>
					    	    		<td class="text14" title="thavd"><font class="text14RedBold" >*</font>Afdeling
					    	    			<a tabindex="-1" id="thavdIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
					    	    		</td>
										<td class="text14" >
										<select name="thavd" id="thavd" class="inputTextMediumBlueMandatoryField">
						  					<option value="">-vælg-</option>
						  					<c:forEach var="record" items="${model.avdGeneralList}" >
						 				  		<option value="${record.koaavd}"<c:if test="${ model.record.thavd == record.koaavd}"> selected </c:if> >${record.koaavd}</option>
											  </c:forEach>  
											</select>
										</td>	
					    	    	</c:otherwise>
				    	    	</c:choose>
				    	    	
				    	    	<td class="text14" title="thnttd"><font class="text14RedBold" >*</font>Antal eksemplar følgeseddel</td>
								<td class="text14" >
									<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="thnttd" id="thnttd" size="2" maxlength="1" value='${model.record.thnttd}'>
								</td>
								<td class="text14" title="thntll"><font class="text14RedBold" >*</font>Antal eksemplar lasteliste</td>
								<td class="text14" >
									<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="thntll" id="thntll" size="2" maxlength="1" value='${model.record.thntll}'>
								</td>
								<td class="text14" title="thfmll"><font class="text14RedBold" >*</font>Trykk lastelisteformular</td>
								<td class="text14" >
									<select name="thfmll" id="thfmll" class="inputTextMediumBlueMandatoryField">
					  					<option value="J"<c:if test="${ model.record.thfmll == 'J' || empty model.record.thfmll}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.thfmll == 'N'}"> selected </c:if> >Nei</option>
								  	</select>
								</td>
							</tr>
							<tr>	
								<td class="text14" title="thenkl"><font class="text14RedBold" >*</font>Prosedyre</td>
								<td class="text14" >
									<select name="thenkl" id="thenkl" class="inputTextMediumBlueMandatoryField">
					  					<option value="J"<c:if test="${ model.record.thenkl == 'J' || empty model.record.thenkl}"> selected </c:if> >Forenklet</option>
					  					<option value="N"<c:if test="${ model.record.thenkl == 'N'}"> selected </c:if> >Normal</option>
								  	</select>
								</td>
								<td class="text14" title="thtdn">&nbsp;&nbsp;<font class="text14RedBold" >*</font>Nr.teller intern ref.</td>
								<td class="text14" >
									<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="thtdn" id="thtdn" size="10" maxlength="7" value='${model.record.thtdn}'>
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
							<td class="text14" title="s0004"><font class="text14RedBold" >*</font>UtvekslingsId Afdeling 
								<a tabindex="-1" id="s0004IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text14">
								<input type="text" class="inputTextMediumBlueMandatoryField" name="s0004" id="s0004" size="20" maxlength="35" value='${model.record.s0004}'>
							</td>
						
							<td class="text14" title="s0010"><font class="text14RedBold" >*</font>UtvekslingsId SKAT 
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
				  					<option value="1"<c:if test="${ model.record.s0035 == '1'}"> selected </c:if> >Test</option>
				  					<option value=""<c:if test="${ empty model.record.s0035}"> selected </c:if> >Prod</option>
							  	</select>
							</td>
							<td class="text14" title="thekst">Importeres eksterne data</td>
							<td class="text14">
								<select name="thekst" id="thekst" class="inputTextMediumBlue">
				  					<option value="">-vælg-</option>
				  					<option value="J"<c:if test="${ model.record.thekst == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.thekst == 'N'}"> selected </c:if> >Nei</option>
				  					<option value="R"<c:if test="${ model.record.thekst == 'R'}"> selected </c:if> >R</option>
							  	</select>
							</td>
						</tr>
						
						<tr height="5"><td></td></tr>
					</table>
				</td>
			</tr>
			<tr height="10"><td></td></tr>
			
			<tr>
				<td width="5%">&nbsp;</td>
	 			<td class="text14" title="thdk">&nbsp;&nbsp;Angivelsestype&nbsp;
	 				<select name="thdk" id="thdk" class="inputTextMediumBlue" TABINDEX=1>
	 				  <option value="">-vælg-</option>
	 				  	<c:forEach var="code" items="${model.deklarasjonsTypeCodeList}" >
                       	 	<option value="${code.tkkode}"<c:if test="${model.record.thdk == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
						</c:forEach> 
					</select>
 				</td>
			</tr>
			
			<tr height="10"><td></td></tr>
			
			
			<tr>
			<td width="5%">&nbsp;</td>
			<td>
				<table width="98%" cellspacing="1" border="0" align="left">
					<tr>
						<%-- LEFT SIDE  --%>
						<td width="50%" valign="top">
						<table width="100%" cellspacing="0" border="0" align="left">
						<tr>
					            <td >		
					 				<%-- SENDER --%>
					 				<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
								 		<tr height="15">
								 			<td class="text14White">
												&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 			<b>&nbsp;2.</b>Afsender&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
									           		<b>2. Afsender</b>
										           		<ul>
									           			<li>Kundenr. Overføres fra tolldekl./oppdrag hvis CVR/SE-nr basert på tolldeklarasjon/oppdrag.</li>
														<li>Avsenders CVR/SE-nr<br/>
														Benytt organisasjonsnr.,evt. bedriftsnummer for avdeling av sentralt registrert enhet.</li>
														</ul>
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
											        	<input type="hidden" name="orig_thkns" id="orig_thkns" value='${Xmodel.record.thkns}'>
											        	<input type="hidden" name="orig_thnas" id="orig_thnas" value='${Xmodel.record.thnas}'>
											        	<input type="hidden" name="orig_thtins" id="orig_thtins" value='${Xmodel.record.thtins}'>
											        	<input type="hidden" name="orig_thads1" id="orig_thads1" value='${Xmodel.record.thads1}'>
											        	<input type="hidden" name="orig_thpns" id="orig_thpns" value='${Xmodel.record.thpns}'>
											        	<input type="hidden" name="orig_thpss" id="orig_thpss" value='${Xmodel.record.thpss}'>
											        	<input type="hidden" name="orig_thlks" id="orig_thlks" value='${Xmodel.record.thlks}'>
											        	<input type="hidden" name="orig_thsks" id="orig_thsks" value='${Xmodel.record.thsks}'>
											        	
											        	
											            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thkns">Kundenummer</span></td>
											            <td class="text14" align="left" >&nbsp;<span title="thnas">Navn&nbsp;</span>
											            	<a tabindex="-1" id="thnasIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
															</a>
											            </td>
											        </tr>
											        <tr>
											            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="thkns" id="thkns" size="8" maxlength="8" value="${model.record.thkns}"></td>
											            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="thnas" id="thnas" size="30" maxlength="35" value="${model.record.thnas}"></td>
											            
											        </tr>
											        
											        <tr>
											            <td class="text14" align="left" >&nbsp;<span title="thtins">CVR/SE</span></td>
											            <td class="text14" align="left" >&nbsp;&nbsp;</td>
											        </tr>
											        <tr>
											            <td align="left"><input type="text" class="inputTextMediumBlue" name="thtins" id="thtins" size="20" maxlength="17" value="${model.record.thtins}"></td>
											            <td align="left">&nbsp;</td>
											        </tr>
											        <tr height="4"><td>&nbsp;</td></tr>
											        <tr>
											            <td class="text14" align="left" >&nbsp;<span title="thads1">Adresse</span></td>
											            <td class="text14" align="left" >&nbsp;<span title="thsks">Sprogkode</span>
											            </td>
											        </tr>
											        <tr>
											            <td align="left"><input type="text" class="inputTextMediumBlue" name="thads1" id="thads1" size="30" maxlength="35" value="${model.record.thads1}"></td>
											            <td align="left" >&nbsp;
											            	<input type="text" class="inputTextMediumBlue" name="thsks" id="thsks" size="3" maxlength="2" value="${model.record.thsks}">
									            		
														</td>
											        </tr>
											        <tr>
											        		<td>
												        		<table>
												        		<tr>
												            		<td class="text14" align="left" >&nbsp;<span title="thpss">By</span></td>
												            		<td align="left">&nbsp;</td>
												            	</tr>
												        		<tr>
												            		<td align="left">
												       				<input type="text" class="inputTextMediumBlue" name="thpss" id="thpss" size="30" maxlength="35" value="${model.record.thpss}">
											            			</td> 
												            		<td align="left">&nbsp;</td>
												        		</tr>    	
												            	</table>
											            </td>
											            <td >
												            	<table>
												        		<tr>
												        			<td class="text14" align="left" >&nbsp;<span title="thpns">Postnummer</span></td>
												            		<td class="text14" align="left" >&nbsp;<span title="thlks">Land</span>
												            		
												            		</td>
												            	</tr>
												        		<tr >
												        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpns" id="thpns" size="10" maxlength="9" value="${model.record.thpns}"></td> 
												            		<td align="left">
												            			<input type="text" class="inputTextMediumBlue" name="thlks" id="thlks" size="3" maxlength="2" value="${model.record.thlks}">
												            			
												            		</td> 
												        		</tr>  
												            	</table>
											            </td>
										            	</tr>
											        <tr height="15">
											            <td class="text14Bold" align="left" >&nbsp;</td> 
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
							 				<b>8.</b>Modtager&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="8_info" class="popupWithInputText text11"  >
												<b>8. Modtager</b>
							           			<br>
								           			<ul>
								           				<li>Kundenr.</li>
														<li>Modtagers EORI <br/>
														Dette er et valgfritt felt som benyttes dersom EORI til den autoriserte mottaker i bestemmelseslandet er kjent.</li>
									           		</ul>
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
										        	<input type="hidden" name="orig_thknk" id="orig_thknk" value='${Xmodel.record.thknk}'>
										        	<input type="hidden" name="orig_thnak" id="orig_thnak" value='${Xmodel.record.thnak}'>
										        	<input type="hidden" name="orig_thtink" id="orig_thtink" value='${Xmodel.record.thtink}'>
										        	<input type="hidden" name="orig_thadk1" id="orig_thadk1" value='${Xmodel.record.thadk1}'>
										        	<input type="hidden" name="orig_thpnk" id="orig_thpnk" value='${Xmodel.record.thpnk}'>
										        	<input type="hidden" name="orig_thpsk" id="orig_thpsk" value='${Xmodel.record.thpsk}'>
										        	<input type="hidden" name="orig_thlkk" id="orig_thlkk" value='${Xmodel.record.thlkk}'>
										        	<input type="hidden" name="orig_thskk" id="orig_thskk" value='${Xmodel.record.thskk}'>
										        	
										            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thknk">Kundenummer</span></td>
										            <td class="text14" align="left" >&nbsp;<span title="thnak">Navn&nbsp;</span>
										            	<a tabindex="-1" id="thnakIdLink">
															<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
														</a>
													</td>
										        </tr>
										        <tr>
										            <td align="left"><input type="text" class="inputTextMediumBlue" name="thknk" id="thknk" size="8" maxlength="8" value="${model.record.thknk}"></td>
										            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnak" id="thnak" size="30" maxlength="35" value="${model.record.thnak}"></td>
										        </tr>
			
										        <tr>
										            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thtink">EORI</span></td>
										            <td class="text14" align="left" >&nbsp;&nbsp;</td>
										        </tr>
										        <tr>
										            <td align="left"><input type="text" class="inputTextMediumBlue" name="thtink" id="thtink" size="20" maxlength="17" value="${model.record.thtink}"></td>
										            <td align="left">&nbsp;</td>
										        </tr>
										        <tr height="4"><td>&nbsp;</td></tr>
										        <tr>
										            <td class="text14" align="left" >&nbsp;<span title="thadk1">Adresse</span></td>
										            <td class="text14" align="left" >&nbsp;<span title="thskk">Sprogkode</span>
									            		
										            </td>
										        </tr>
										        <tr>
										            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadk1" id="thadk1" size="30" maxlength="35" value="${model.record.thadk1}"></td>
										            <td class="text14" align="left" >&nbsp;
										            	<input type="text" class="inputTextMediumBlue" name="thskk" id="thskk" size="3" maxlength="2" value="${model.record.thskk}">
										            
													</td>
										        </tr>
										        <tr>
										        		<td>
											        		<table>
											        		<tr>
											            		<td class="text14" align="left" >&nbsp;<span title="thpsk">By</span></td>
											            		<td align="left">&nbsp;</td>
											            	</tr>
											        		<tr>
											            		<td align="left">
											       				<input type="text" class="inputTextMediumBlue" name="thpsk" id="thpsk" size="30" maxlength="35" value="${model.record.thpsk}">
										            			</td> 
											            		<td align="left">&nbsp;</td>
											        		</tr>    	
											            	</table>
										            </td>
										            <td >
											            	<table>
											        		<tr>
											        			<td class="text14" align="left" >&nbsp;<span title="thpnk">Postnummer</span></td>
											            		<td class="text14" align="left" >&nbsp;<span title="thlkk">Land</span>
											            		
											            		</td>
											            	</tr>
											        		<tr >
											        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnk" id="thpnk" size="10" maxlength="9" value="${model.record.thpnk}"></td> 
											            		<td align="left">
											            			<input type="text" class="inputTextMediumBlue" name="thlkk" id="thlkk" size="3" maxlength="2" value="${model.record.thlkk}">
											            			
											            		</td> 
											        		</tr>  
											            	</table>
										            </td>
									            	</tr>
										        
										        <tr height="15">
										            <td class="text14Bold" align="left" >&nbsp;</td> 
										        </tr>
									        </table>
								        </td>
							        </tr>
								</table>          
			            	</td>
			           	</tr> 
			           	<tr height="10"><td></td></tr>
          
							<tr>
					            <td >
									<%-- Special section --%>
									<table align="left" class="formFrameHeader" width="95%" border="0" cellspacing="0" cellpadding="0">
								 		<tr height="15">
								 			<td class="text14White">
								 				&nbsp;Specifikke oplysninger om rejseruten og garanti&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
							 				</td>
						 				</tr>
					 				</table>
					 			</td>
				 			</tr>
				 			<tr>
					            <td >
					 				<%-- create record --%>
								 	<table align="left" class="formFrame" width="95%" border="0" cellspacing="0" cellpadding="0">
								 		<tr>
									 		<td>
										 		<table align="left" border="0" cellspacing="0" cellpadding="0">
											 		<tr height="15">
											            <td class="text14Bold" align="left" >&nbsp;</td> 
											            <td class="text14Bold" align="left" >&nbsp;</td> 
											            
											        </tr>
											        <tr>
											        	<td class="text14" align="left" >
											        	&nbsp;<img onMouseOver="showPop('51_info');" onMouseOut="hidePop('51_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											        
											        	<b>51.</b><span title="thtsd1/thtsd2...">Planlagte grænseovergangssteder&nbsp;</span>
											        	<div class="text11" style="position: relative;" align="left">
														<span style="position:absolute;top:2px; width:250px;" id="51_info" class="popupWithInputText text11"  >
										           			<b>51. Planlagte grænseovergangssteder</b>
										           			<p>
											           			Oppgi kodene for de planlagte innpasseringstollstedene for hver avtalepart som skal passeres under transporten, f.eks. SE603340 for Svinesund (SE).
																EU anses som én avtalepart. Hvis transporten skal passere andre områder enn det som tilhører avtalepartene, oppgis utpasseringstollstedet fra avtalepartenes område som
																transitteringstollsted.
															</p>
															<p>
																Hvis avgangstollsted og bestemmelsestollsted tilhører forskjellige avtaleparter, er det obligatorisk å deklarere minst ett transitteringstollsted.
																Oppgi inntil 8 transittsteder varene skal innom. (Utskriften har bare plass for 6 steder.)
															</p>
															<p>
																Det viser seg at når nye land/områder knyttes til NCTS, blir det endringer i koder og navn. Det kan også lønne seg å bruke beskrivende navn på tollstedene, for å vite hvilke man
																skal benytte til forskjellige lossesteder.
															</p>
											           		
														</span>	
														</div>	
											        	</td>
											            <td class="text14" align="left" >&nbsp;</td>
											        </tr>
											        
											        <tr>
											        	<td>&nbsp;&nbsp;
											            	<table align="left" border="0" cellspacing="0" cellpadding="0">
											            		<tr>
											            			
										            			<td>
										            				<input type="text" class="inputTextMediumBlue" name="thtsd1" id="thtsd1" size="10" maxlength="8" value="${model.record.thtsd1}">
											            			<a tabindex="-1" id="thtsd1IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
																</td>
																<td>
											            			&nbsp;
											            			&nbsp;
											            			<input type="text" class="inputTextMediumBlue" name="thtsd2" id="thtsd2" size="10" maxlength="8" value='${model.record.thtsd2}'>
											            			<a tabindex="-1" id="thtsd2IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
											            			</td>
											            			
											            			
											            			<td>
											            			&nbsp;
											            			&nbsp;
											            			<input type="text" class="inputTextMediumBlue" name="thtsd3" id="thtsd3" size="10" maxlength="8" value='${model.record.thtsd3}'>
											            			<a tabindex="-1" id="thtsd3IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
											            			</td>
											            		</tr>
											            		
											            		
											            		<tr>
											            			<td>
											            			<input type="text" class="inputTextMediumBlue" name="thtsd4" id="thtsd4" size="10" maxlength="8" value='${model.record.thtsd4}'>
											            			<a tabindex="-1" id="thtsd4IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
											            			</td>
											            			
											            			<td>
											            			&nbsp;
											            			&nbsp;
											            			<input type="text" class="inputTextMediumBlue" name="thtsd5" id="thtsd5" size="10" maxlength="8" value='${model.record.thtsd5}'>
											            			<a tabindex="-1" id="thtsd5IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
											            			</td>
											            			
											            			<td>
											            			&nbsp;
											            			&nbsp;
											            			<input type="text" class="inputTextMediumBlue" name="thtsd6" id="thtsd6" size="10" maxlength="8" value='${model.record.thtsd6}'>
											            			<a tabindex="-1" id="thtsd6IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
											            			</td>					            			
											            		</tr>
											            		<tr>
											            			<td>
											            			<input type="text" class="inputTextMediumBlue" name="thtsd7" id="thtsd7" size="10" maxlength="8" value='${model.record.thtsd7}'>
											            			<a tabindex="-1" id="thtsd7IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
											            			</td>
											            			
											            			
											            			<td>
											            			&nbsp;
											            			&nbsp;
											            			<input type="text" class="inputTextMediumBlue" name="thtsd8" id="thtsd8" size="10" maxlength="8" value='${model.record.thtsd8}'>
											            			<a tabindex="-1" id="thtsd8IdLink">
																		<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																	</a>
											            			</td>
											            		</tr>
											            	</table>
											            </td>     
											        </tr>
											        
											        <tr height="10"><td>&nbsp;</td></tr>
				
											        <tr>
											        	<td>&nbsp;&nbsp;
											        	<table align="left" border="0" cellspacing="0" cellpadding="0">
												        	<tr>
												        	<td class="text14" align="left" >
												        	
												        	&nbsp;<img onMouseOver="showPop('53_info');" onMouseOut="hidePop('53_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
												        	<b>53.</b><span title="thtsb">Bestemmelsestoldsted</span>&nbsp;
												        	
												        	<div class="text11" style="position: relative;" align="left">
															<span style="position:absolute;top:2px; width:250px;" id="53_info" class="popupWithInputText text11"  >
											           			<br/>
											           			<b>53. Bestemmelsestoldsted</b>
										           				<p>
											           			Oppgi kode for tollstedet hvor varen skal fremlegges for å fullføre transitteringen.<br/> 
											           			Hvis mottaker av varene er hjemmehørende i et land utenfor Transitteringskonvensjonens avtaleområde, oppgis
																utpasseringstollstedet fra avtaleområdet som bestemmelsestollsted.
																</p>
																<p>
																Det viser seg at når nye land/områder knyttes til <b>NCTS</b>, blir det endringer i koder og navn.<br/> 
																Det kan også lønne seg å bruke beskrivende navn på tollstedene, for å vite hvilke man skal benytte til forskjellige lossesteder.
																<p>
															</span>
															</div>
															</td>		
												        	
										            		<td >
										            			<input type="text" class="inputTextMediumBlue" name="thtsb" id="thtsb" size="10" maxlength="8" value="${model.record.thtsb}">
																<a tabindex="-1" id="thtsbIdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
																</a>
															</td>								            
												            </tr>
											            </table>
											            </td>
											            <td>&nbsp;</td>
											        </tr>
											        	<tr height="18"><td>&nbsp;</td></tr>
											        <tr >
											        	<td >&nbsp;&nbsp;
											            	<table width="90%" class="tableBorderWithRoundCornersGray" align="left" border="0" cellspacing="2" cellpadding="0">
											            		<tr height="2"><td ></td></tr>
													        	<tr>
														        	<td  colspan="3" class="text14" align="left" >
														        	&nbsp;<img onMouseOver="showPop('52_info');" onMouseOut="hidePop('52_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
														        	<b>52.</b><b>Garantikode</b>&nbsp;
														        	
																	<div class="text11" style="position: relative;" align="left">
																		<span style="position:absolute;top:2px; width:250px;" id="52_info" class="popupWithInputText text11"  >
														           			<br/>
														           			<b>52. Garantikode</b>
														           			<ul>
															           			<li>
															           				<b>0</b>&nbsp;Garantifritak.
															           			</li>
															           			<li>
															           				<b>1</b>&nbsp;Universalgaranti.
															           			</li>
															           			<li>
															           				<b>2</b>&nbsp;Enkelgaranti/Garantist.
															           			</li>
															           			<li>
															           				<b>3</b>&nbsp;Enkelgaranti/Kontant.
															           			</li>
															           			<li>
															           				<b>4</b>&nbsp;Enkelgarantiblanketter.
															           			</li>
															           			<li>
															           				<b>6</b>&nbsp;Unntak fra garanti (båt, fly, osv) i henhold til Transitteringskonvensjonens Vedlegg I, Artikkel 7.
															           			</li>
															           			<li>
															           				<b>7</b>&nbsp;Forhåndsvarsel
															           			</li>
															           		</ul>
															           		Ved bruk av forenklet prosedyre, kan bare garantitype 1 (Universalgaranti) brukes.
																	</span>	
																	</div>
																	</td>	
													        	</tr>
													        	<tr height="5"><td></td></tr>
											            		<tr>
											            			<td class="text14">&nbsp;<span title="thgkd">Kode</span></td>
											            			<td class="text14">&nbsp;<span title="thgft1">Garantinummer</span></td>
											            			<td class="text14">&nbsp;<span title="thgadk">Adgangskode</span></td>
											            		</tr>
											            		<tr>
											            			<td>
											            				<select class="inputTextMediumBlue" name="thgkd" id="thgkd" >
														 				  <option value="">-vælg-</option>
																		  <option value="0"<c:if test="${model.record.thgkd == '0'}"> selected </c:if> >0</option>
																		  <option value="1"<c:if test="${model.record.thgkd == '1'}"> selected </c:if> >1</option>
																		  <option value="2"<c:if test="${model.record.thgkd == '2'}"> selected </c:if> >2</option>
																		  <option value="3"<c:if test="${model.record.thgkd == '3'}"> selected </c:if> >3</option>
																		  <option value="4"<c:if test="${model.record.thgkd == '4'}"> selected </c:if> >4</option>
																		  <option value="6"<c:if test="${model.record.thgkd == '6'}"> selected </c:if> >6</option>
																		  <option value="7"<c:if test="${model.record.thgkd == '7'}"> selected </c:if> >7</option>
																		  
																		</select>
											            			</td>
											            			<td><input type="text" class="inputTextMediumBlue" name="thgft1" id="thgft1" size="24" maxlength="24" value="${model.record.thgft1}"></td>
											            			<td><input type="text" class="inputTextMediumBlue" name="thgadk" id="thgadk" size="5" maxlength="4" value="${model.record.thgadk}"></td>
											            		</tr>
											            		<tr>
											            			
											            			<td class="text14">&nbsp;<span title="thgbgi">Ikke gyl.EU</span></td>
											            			<td class="text14">&nbsp;<span title="thgbgu">Garant. gjelder ikke [land]</span></td>
											            			<td>&nbsp;</td>
											            		</tr>
											            		<tr>
											            			<td>
												            			<select name="thgbgi" id="thgbgi" >
													 				  <option value="0"<c:if test="${model.record.thgbgi == '0'}"> selected </c:if> >0</option>
																	  <option value="1"<c:if test="${model.record.thgbgi == '1'}"> selected </c:if> >1</option>
																	</select>
											            			</td>
											            			<td align="left">
											            				<input type="text" class="inputTextMediumBlue" name="thgbgu" id="thgbgu" size="3" maxlength="2" value="${model.record.thgbgu}">
												            			
													            	</td>
													            	<td>&nbsp;</td> 
											            		</tr>
											            		<tr height="5"><td></td></tr>
											            		<tr>
											            			<td colspan="3" class="text14">&nbsp;<span title="thgft2">Annen Garanti</span></td>
											            		</tr>
											            		<tr>
											            			<td colspan="3"><input type="text" class="inputTextMediumBlue" name="thgft2" id="thgft2" size="35" maxlength="35" value="${model.record.thgft2}"></td>
											            		</tr>
											            		<tr height="2"><td></td></tr>
											            	</table>
											            </td>  
											            <td>&nbsp;</td>
											        </tr>
											        <tr height="10">
											        <%-- Garantibeløp --%>
										            <tr>
											            <td colspan="2">
											                <table align="left" border="0" cellspacing="0" cellpadding="0">
														 		<tr>
														 			<td class="text14"><b><span title="thgpr">Garanti %&nbsp;</span></b></td>
														 			<td align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="thgpr" id="thgpr" size="6" maxlength="5" value="${model.record.thgpr}"></td>
														 			<td class="text14"><b>&nbsp;<span title="thgbl">Garantibeløb&nbsp;</span></b></td>
														 			<td align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="thgbl" id="thgbl" size="20" maxlength="20" value="${model.record.thgbl}"></td>
														 			<td class="text14">&nbsp;<span title="thgvk">Møntsort</span>
														 				<%-- Note: onChange event in jQuery for this currency list --%>
														 				<select name="thgvk" id="thgvk" >
														 				  <option value="">-vælg-</option>	
														 				  	<c:forEach var="record" items="${model.currencyCodeList}" >
										                                	 	<option value="${record.dkvk_kd}"<c:if test="${model.record.thgvk == record.dkvk_kd}"> selected </c:if> >${record.dkvk_kd}</option>
																			</c:forEach> 
																		</select>
													 				</td>
												 				</tr>
												 				<%--
												 				<tr>
													 				<td class="text14">&nbsp;Kurs&nbsp;</td>
														 			<td class="text14" align="left" ><input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" name="sveh_vaku" id="sveh_vaku" size="20" maxlength="20" value='${vaku}'></td>
														 			
												 				</tr>
												 				--%> 
												 				 
												 				<tr height="15"><td></td></tr>
															</table>
															</td>
													</tr>
											        <tr height="10">
											            <td class="text14Bold" align="left" >&nbsp;</td> 
											            <td class="text14Bold" align="left" >&nbsp;</td> 
											        </tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
						  	</tr>
						</table>
						</td>
						<%-- --------------- --%>
						<%-- RIGHT SIDE CELL --%>
						<%-- --------------- --%>
						<td width="50%" valign="top">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<tr>
									<td width="2">&nbsp;</td>
							 		<td>
							 			<table border="0" cellspacing="2" cellpadding="0">
							 				<tr>
							 				<td class="text14" align="left" >
									            <img onMouseOver="showPop('15_info');" onMouseOut="hidePop('15_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 					<b>15.</b><span title="thalk">Avs./utf.land&nbsp;</span>
							 					<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="15_info" class="popupWithInputText text11"  >
									           			<b>15. Avs./utf.land</b>
									           			<ul>
									           				<li>The name of the country from which goods are to be dispatched/exported shall be
																entered.
															</li>
									           			</ul>
												</span>	
												</div>	
									            </td>
									            <td >
									            	<input type="text" class="inputTextMediumBlue" name="thalk" id="thalk" size="3" maxlength="2" value="${model.record.thalk}">
									            	
												</td>
							 				</tr>
							 				
							 				<tr>
									            <td class="text14" align="left" >
									            <img onMouseOver="showPop('17_info');" onMouseOut="hidePop('17_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									            <b>17.</b><span title="thblk">Best.land&nbsp;</span>
									            <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="17_info" class="popupWithInputText text11"  >
								           			<b>17. Best.land</b>
								           			<p>
								           			The name of the country of destination shall be entered.
								           			</p>
												</span>	
												</div>
									            </td>
									            
									            <td >
									            	<input type="text" class="inputTextMediumBlue" name="thblk" id="thblk" size="3" maxlength="2" value="${model.record.thblk}">
									            	
												</td>
									        </tr>
									        <tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
			 								<tr height="5"><td class="text">&nbsp;</td> </tr>
								            
							 				<tr>
									            <td class="text14" align="left" >
									            <img onMouseOver="showPop('18_info');" onMouseOut="hidePop('18_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									            <b>18.</b><span title="thtaid">Tr.middelets identitet ved avgang </span>
									            <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="18_info" class="popupWithInputText text11"  >
									           			<b>18.Tr.middelets identitet ved avgang</b>
									           			<p>
														Oppgi transportmiddelets <b>identitet</b> ved avgang, f.eks. registreringsnr. for trekkenheten og eventuell tilhenger/semitrailer. 
														Oppgi med fastsatt landkode transportmiddelets <b>nasjonalitet</b> med tillhørende <b>språkkode</b>.
														</p>
														<p>
														I de tilfeller hvor f.eks. trekkenheten og tilhengeren/semitraileren har forskjellig nasjonalitet, oppgis
														trekkenhetens nasjonalitet i feltet.
														</p>
														<ol>
															<li><b>Identitet</b> = reg.nr., containernr. etc.</li>
															<li><b>Nasjonalitet</b> = Landkode</li>
															<li><b>Språk</b> = Språkkode</li>									
														</ol>
												</span>
												</div>
												</td>
												<td >
								            		<input type="text" class="inputTextMediumBlue" name="thtaid" id="thtaid" size="25" maxlength="35" value="${model.record.thtaid}">
								            	</td>
					        				</tr>
					        				<tr>
								            	<td class="text14" align="left">
								            		&nbsp;<span title="thtalk">Nasjonalitet</span>
								            		<input type="text" class="inputTextMediumBlue" name="thtalk" id="thtalk" size="3" maxlength="2" value="${model.record.thtalk}">
								            	
												</td>
												<td class="text14">	
													<span title="thtask">Språk</span>
													<input type="text" class="inputTextMediumBlue" name="thtask" id="thtask" size="3" maxlength="2" value="${model.record.thtask}">
													
								            	</td>
					        				</tr>
					        				<tr height="5"><td class="text">&nbsp;</td> </tr>

					        				<tr>
									            <td class="text14" align="left">
									            <img onMouseOver="showPop('21_info');" onMouseOut="hidePop('21_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									            <b>21.</b><span title="thtgid">Tr.middelets identitet ved grense</span>
									            <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="21_info" class="popupWithInputText text11"  >
									           			<b>21. Tr.middelets identitet ved grense</b>
									           			<p>
									           			Oppgi transportmiddelets identitet ved grensepassering, f.eks.registringsnr.for trekkenheten og eventuell tilhenger/semitrailer. Oppgi med fastsatt landkode transportmiddelets nasjonalitet med tillhørende språkkode.I de tilfeller hvor f.eks. trekkenheten og tilhengeren/semitraileren har forskjellig nasjonalitet, oppgis trekkenhetens nasjonalitet i feltet.<br/>
														</p>
														<p>
														Når det dreier seg om kombinert transport, eller det benyttes flere transportmidler, oppgis det transportmiddelet som sørger for fremdriften (f.eks. lastebil på ferge; fergen vil være det aktive transportmiddelet.
														Hvis første siffer i "Transportmåte ved grensepassering"(felt 25) er annet enn "2", "5" eller "7", er feltet obligatorisk.
														</p>
														<ol>
															<li><b>Identitet</b> = reg.nr., containernr. etc.</li>
															<li><b>Nasjonalitet</b> = Landkode</li>
															<li><b>Språk</b> = Språkkode</li>									
														</ol>
												
												</span>	
												</div>
												</td>
									            <td >
								            		<input type="text" class="inputTextMediumBlue" name="thtgid" id="thtgid" size="25" maxlength="35" value="${model.record.thtgid}">
								            	</td>
					        				</tr>
					        				<tr>
								            	<td class="text14" align="left">
								            		&nbsp;<span title="thtglk">Nasjonalitet</span>
								            		<input type="text" class="inputTextMediumBlue" name="thtglk" id="thtglk" size="3" maxlength="2" value="${model.record.thtglk}">
								            		
												</td>
												<td class="text14">	
													<span title="thtgsk">Språk</span>
													<input type="text" class="inputTextMediumBlue" name="thtgsk" id="thtgsk" size="3" maxlength="2" value="${model.record.thtgsk}">
										            
								            	</td>
					        				</tr>
					        				<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
			 								<tr height="5"><td class="text">&nbsp;</td> </tr>
								            
				 		       				<tr>
								            	<td class="text14">
								            		<img onMouseOver="showPop('thtrm_info');" onMouseOut="hidePop('thtrm_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						            				<b>25.</b><span title="thtrm">Transp.måte ved utpass.&nbsp;</span>
						            			</td>
						            			<td class="text14">	
								            		<select class="inputTextMediumBlue" name="thtrm" id="thtrm">
							 						<option value="">-vælg-</option>
								 				  	<c:forEach var="code" items="${model.transportmaterCodeList}" >
								 				  		<option value="${code.tkkode}"<c:if test="${model.record.thtrm == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
													</c:forEach> 
													</select>
									            	<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="thtrm_info" class="popupWithInputText text11"  >
									           			<b>25. Transp.måte ved utpass.</b>
									           			<p>
														Oppgi med kode transportmåten ved utpassering fra Norge.<br/>
														Koden kan legges inn som standardverdi for avdelingen.
														</p>		
														<ul>
									           				<c:forEach var="code" items="${model.transportmaterCodeList}" >
										 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxte}</li>
									 				  		</c:forEach>	
									           			</ul>
													</span>
													</div>
												</td>
											</tr>
											<tr>
								 				<td class="text14">
								 				<img onMouseOver="showPop('19_info');" onMouseOut="hidePop('19_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<b>19.</b>&nbsp;<span title="thkdc">Container&nbsp;&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="19_info" class="popupWithInputText text11"  >
								           			<b>19. Container</b>
								           			<p>
													Oppgi, med fastsatte koder, om varene blir transportert i containere ved utpassering fra Norge.
													</p>
								           			<ul>
									           			<li><b>0</b>&nbsp;-&nbsp;Varer som ikke transporteres i containere.</li>
									           			<li><b>1</b>&nbsp;-&nbsp;Varer som transporteres i containere.</li>
									           		</ul>
									           		<p>Kan defineres som standardverdier pr. avdeling.</p>
									           	</span>
									           	</div>
									           	</td>
								 				<td class="text14" >
						 							<select class="inputTextMediumBlue" name="thkdc" id="thkdc">
								 						<option value="0"<c:if test="${model.record.thkdc == 0}"> selected </c:if> >0</option>
												  		<option value="1"<c:if test="${model.record.thkdc == 1}"> selected </c:if> >1</option>
												  	</select>
						 						</td>
											</tr>
											
											<tr>
								            <td class="text14" align="left" >
								            <img onMouseOver="showPop('lastplats_info');" onMouseOut="hidePop('lastplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
								            <b>27.</b>&nbsp;<span title="thlsd">Lastested</span>
								            
								            <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="lastplats_info" class="popupWithInputText text11"  >
								           			<b>27. Lastested</b>
								           			<p>
													Dette er ikke et obligatorisk felt. Rent tekstfelt. Fyll ut med f.eks. terminalnavn etc.								
													</p>
											</span>
											</div>
											</td>
											<td class="text14" >
										    	<input type="text" class="inputTextMediumBlue" name="thlsd" id="thlsd" size="17" maxlength="17" value="${model.record.thlsd}">
										    </td>
										</tr>
				        				<tr height="10"><td class="text">&nbsp;</td> </tr>
					 					<tr>
								            <td class="text14" align="left">
								            <img onMouseOver="showPop('tullkontor_info');" onMouseOut="hidePop('tullkontor_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
								            <b>C.</b><span title="thcats">Avg.tollsted&nbsp;</span>
								            <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="tullkontor_info" class="popupWithInputText text11"  >
								           		<b>C. Avg.tollsted</b>
									           		<p>
									           		Kode for avgangstollsted.<br/>
													Merk at dette er tollstedskoden som er knyttet opp mot Ansvarlig's organisasjonsnr., ikke nødvendigvis stedet varene går ut fra.
													<br/><br/>
													Legges inn som standardverdi på avdelingen.
									           		</p>
											
											</span>	
											</div>
								            </td>
										    <td class="text">
										    <input type="text" class="inputTextMediumBlue" name="thcats" id="thcats" size="10" maxlength="8" value='${model.record.thcats}'>
										    <a tabindex="-1" id="thcatsIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
											</td>	
								        </tr>
								        <tr height="2"><td>&nbsp;</td></tr>
								        <tr>
								            <td class="text14" align="left">
								            <img onMouseOver="showPop('sprakkod_foljedok_info');" onMouseOut="hidePop('sprakkod_foljedok_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
								            <span title="thskfd">Sprogkode på følgedok.&nbsp;</span>
								            <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="status_info" class="popupWithInputText text11"  >
								           			<b>Sprogkode på følgedok</b>
								           			<p>
								           			Følgedokumenter (T-papir, lasteliste/vareliste)
								           			</p>
								           	</span>	
											</div>
											</td>
								            <td align="left">
								            	<input type="text" class="inputTextMediumBlue" name="thskfd" id="thskfd" size="3" maxlength="2" value="${model.record.thskfd}">
				            					
											</td> 
										</tr>
										<tr height="2"><td>&nbsp;</td></tr>
										
								        <tr>
								            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thtrdt">Transittdato</span></td>
								            <td class="text14" >
								            	<input readonly type="text" class="inputTextReadOnly" name="thtrdt" id="thtrdt" size="8" value="${model.record.thtrdt}">
								            	&nbsp;
								            </td>
								        </tr>
										<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
		 								<tr height="5"><td class="text">&nbsp;</td> </tr>
								            						
										<tr>
								            <td class="text14" align="left" >
								            <img onMouseOver="showPop('deklarantplats_info');" onMouseOut="hidePop('deklarantplats_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
								            <span title="thdst">Dekl.sted</span>
								            <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute;top:2px; width:250px;" id="deklarantplats_info" class="popupWithInputText text11"  >
								           			<b>Dekl.sted</b>
								           			<p>Plats (inklusive landkod)</p>
											</span>	
											</div>
											</td>
								            
								            <td class="text14">
								            	<input type="text" class="inputTextMediumBlue" name="thdst" id="thdst" size="10" maxlength="15" value="${model.record.thdst}">
								            </td>
								         </tr>
								         <tr>
								            <td class="text14" >
								            	&nbsp;&nbsp;<span title="thdsk">Dekl.språk</span>
								            </td>
								            <td class="text14" >
								            	<input type="text" class="inputTextMediumBlue" name="thdsk" id="thdsk" size="3" maxlength="2" value="${model.record.thdsk}">	
								            	
								            </td>
								        </tr>
										<tr height="15"><td>&nbsp;</td></tr>
										
										
										<tr>
									            <td class="text14" align="left" >
									            <img onMouseOver="showPop('6_info');" onMouseOut="hidePop('6_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
									            <b>6.&nbsp;</b><span title="thntk">Antal Kolli</span>
									            <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="6_info" class="popupWithInputText text11"  >
								           			<b>6. Antall Kolli</b>
								           			<p>
													Antall kolli. Feltet er sperret. Summeres fra varelinjer.<br/>
													Beregnes automatisk fra varelinjer.
													</p>
								           			Feltet er blokeret. 
												</span>
												</div>
												</td>	
									            <td ><input readonly onKeyPress="return numberKey(event)" style="text-align: right" type="text" class="inputTextReadOnly" name="thntk" id="thntk" size="8" maxlength="7" value="${model.record.thntk}"></td>
									        </tr>
								            <tr>
									            <td class="text14" align="left" >
									            <img onMouseOver="showPop('bruttovikt_info');" onMouseOut="hidePop('bruttovikt_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">	
									            <span title="thvkb">Bruttovekt</span>
									            <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="bruttovikt_info" class="popupWithInputText text11"  >
									           	
									           			<b>Bruttovekt</b>
									           			<p>
									           			Summeres fra varelinjer. 
									           			Feltet er blokeret. 
									           			</p>
												</span>	
												</div>
												</td>
									            
									            <td ><input readonly onKeyPress="return amountKey(event)" style="text-align: right" type="text" class="inputTextReadOnly" name="thvkb" id="thvkb" size="10" maxlength="9" value="${model.record.thvkb}"></td>
									        </tr>
									     	<tr height="10"><td>&nbsp;</td></tr>
						     	            	<tr>
									            <td class="text14" align="left">
									            <img onMouseOver="showPop('kontrollresultat_info');" onMouseOut="hidePop('kontrollresultat_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								<b>D.</b>&nbsp;<span title="thdkr">Kontrollresultat.&nbsp;</span>
				 								<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="kontrollresultat_info" class="popupWithInputText text11"  >
									           	
										           		<b>D. Kontrollresultat</b>
										           		<ul>
										           			<li><b>A1</b>&nbsp;TILLFREDSSTILLENDE</li>
										           			<li><b>A2</b>&nbsp;ANSETT TILFREDSSTILLENDE</li>
										           			<li><b>A3</b>&nbsp;FORENKLET PROSEDYRE</li>
										           			<li><b>A4</b>&nbsp;INGEN REAKSJON VED MINDRE UREGELMES</li>
										           			<li><b>A5</b>&nbsp;BELØP INNKREVD</li>
										           			<li><b>B1</b>&nbsp;IKKE TILLFREDSSTILLENDE</li>
										           		</ul>
										           		<p>
										           		Frist er dato godset senest må sendes for å omfattes av den gitte frigivelse.
										           		</p>
										           		<p>
										           		a) Ved <b>Forenklet Prosedyre</b> (Autorisert avsender) må feltet være fylt ut med <b>A3</b><br/>  
										           		Systemet foreslår frist til dagens dato + 8 dager.<br/>
										           		</p>
										           		<p>
														b) Ved <b>Normal Prosedyre</b> (ikke autorisert avsender), gis verdiene av tollvesenet (kommer i retur i <b>IE29</b>)
										           		</p>
										           		
												</span>
												</div>
												</td>
									           	<td class="text14" align="left">
									           		
									           		<select name="thdkr" id="thdkr" >
									           			<option value="">-vælg-</option>
									 				  <option value="A1"<c:if test="${model.record.thdkr == 'A1'}"> selected </c:if> >A1</option>
									 				  <option value="A2"<c:if test="${model.record.thdkr == 'A2'}"> selected </c:if> >A2</option>
													  <option value="A3"<c:if test="${model.record.thdkr == 'A3'}"> selected </c:if> >A3</option>
													  <option value="A4"<c:if test="${model.record.thdkr == 'A4'}"> selected </c:if> >A4</option>
													  <option value="A5"<c:if test="${model.record.thdkr == 'A5'}"> selected </c:if> >A5</option>
													  <option value="B1"<c:if test="${model.record.thdkr == 'B1'}"> selected </c:if> >B1</option>													  
													</select>
									           	</td>
								           	</tr>
								           	<tr>
									            <td class="text14" align="left">
									            <img onMouseOver="showPop('frist_info');" onMouseOut="hidePop('frist_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
				 								<b>D.</b>&nbsp;<span title="thddt">Frist&nbsp;</span>
				 								<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="frist_info" class="popupWithInputText text11"  >
									           			<b>Frist</b>
										           		<p>
										           		Frist er dato godset senest må sendes for å omfattes av den gitte frigivelse.
										           		</p>
										           		<p>
										           		a) Ved <b>Forenklet Prosedyre</b> (Autorisert avsender) må feltet være fylt ut med <b>A3</b><br/>  
										           		Systemet foreslår frist til dagens dato + 8 dager.<br/>
										           		</p>
										           		<p>
														b) Ved <b>Normal Prosedyre</b> (ikke autorisert avsender), gis verdiene av tollvesenet (kommer i retur i <b>IE29</b>)
										           		</p>
												</span>
												</div>
												</td>
									           	<td class="text14" align="left">
									           		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thddt" id="thddt" size="8" maxlength="6" value="${model.record.thddt}">
									           		&nbsp;
									           	</td>
								           	</tr>
								           	<tr>
									            <td class="text14" align="left">&nbsp;</td>
												<td class="text9BlueGreen" valign="bottom"  >
					 				    			<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spare' onClick="setBlockUI(this);"/>
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

			<tr>
			    <td colspan="2">		
				<%-- ------------------------------- --%>
				<%-- tab area container DIVISON AREA --%>
				<%-- ------------------------------- --%>
				<table border="0" cellspacing="0" cellpadding="0">
					<tr >
						<td height="10" class="tabNoBorderSeparatorWhite" align="left" > 
				   			 <table border="0" cellspacing="0" cellpadding="0">
							 	<tr >
							 		<%--
							 		<td class="divisionLine">
						    			&nbsp;
						    		</td>
						    		 --%>
						        </tr>
						     </table> 
						</td>
					</tr>
				</table>
				</td>
		 	</tr>
			<tr height="20"><td colspan="2">&nbsp;</td></tr>
			<tr>
				<td width="5%">&nbsp;</td>
			    <td colspan="2" >
				<%-- ---------------------------- --%>
				<%-- tab area container SECONDARY --%>
				<%-- ---------------------------- --%>
				<table width="98%" class="secondarySectionHeader" border="0" cellspacing="0" cellpadding="0">
			 		<tr height="18">
						<td class="text14WhiteBold">
			 				<b>&nbsp;&nbsp;&nbsp;&nbsp;Supplerende oplysninger&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						</td>
					</tr>
				</table>
				</td>
			</tr>
		
		<%-- ------------  --%>
		<%-- EXTRAORDINÄRA --%>
		<%-- ------------  --%>
				<tr>
					<td width="5%">&nbsp;</td>
					<td colspan="2" >
					<table width="98%" class="secondarySectionFrame" border="0" cellspacing="0" cellpadding="0">
						<tr height="10"><td colspan="2"></td></tr>
						<tr>
						<td width="50%" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
					 			<td width="5">&nbsp;</td>
					            <td >		
					 				<%-- ANSVARIG --%>
					 				<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
								 		<tr height="18px">
								 			<td class="text14White">
								 				&nbsp;
								 				<img onMouseOver="showPop('14_info');" onMouseOut="hidePop('14_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 						
						 						<b>&nbsp;50.</b>Hovedforpligtede&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
						 						
								 				<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="14_info" class="popupWithInputText text11"  >
							           	
								           			<br>
								           			Hovedforpligtede 
								           			<ul>
									           			<li>The principal’s name (full name of the person or company) and full address shall be
															entered as well as the identification number, if any, allocated by the competent
															authorities. 
															<br/><br/>If appropriate, the full name (person or company) of the authorised
															representative who signs on behalf of the principal shall be entered.
															Subject to any specific provisions on the use of computerised systems, the original of
															the handwritten signature of the person concerned must appear on the SAD copy no.1,
															which is to be kept at the office of departure. If this is a legal person, the signatory
															shall add after his signature his full name and the capacity in which he is signing.
									           			</li>
									           			
									           		</ul>
												</span>
												</div>
							 				</td>
						 				</tr>
					 				</table>
					 			</td>
					 		</tr>
					 		<tr>	
					 			<td width="5">&nbsp;</td>
					            <td >	
									<%-- create record --%>
								 	<table width="95%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
								 		<tr>
									 		<td>
										 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
											 		<tr height="15">
											            <td class="text14" align="left">&nbsp;</td> 
											        </tr>
											        <tr>
											        	<%-- ================================================================================== --%>
											        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
											        		 These original values will be used when the user clicks "Cancel" buttons (puttting
											        		 back original value)																--%> 
											        	<%-- ================================================================================== --%>
											        	<input type="hidden" name="orig_sveh_dkkn" id="orig_sveh_dkkn" value='${dkkn}'>
											        	<input type="hidden" name="orig_thnaa" id="orig_thnaa" value='${Xmodel.record.thnaa}'>
											        	<input type="hidden" name="orig_thtina" id="orig_thtina" value='${Xmodel.record.thtina}'>
											        	<input type="hidden" name="orig_thada1" id="orig_thada1" value='${Xmodel.record.thada1}'>
											        	<input type="hidden" name="orig_thpna" id="orig_thpna" value='${Xmodel.record.thpna}'>
											        	<input type="hidden" name="orig_thpsa" id="orig_thpsa" value='${Xmodel.record.thpsa}'>
											        	<input type="hidden" name="orig_thlka" id="orig_thlka" value='${Xmodel.record.thlka}'>
											        	<input type="hidden" name="orig_thska" id="orig_thska" value='${Xmodel.record.thska}'>
									        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="thtina">CVR/SE-nr</span></td>
											            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thnaa">Navn</span>
											            	<a tabindex="-1" id="thnaaIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
															</a>
											            </td>
											            
											        </tr>
											        <tr>
											        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtina" id="thtina" size="20" maxlength="17" value="${model.record.thtina}"></td>
											            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnaa" id="thnaa" size="30" maxlength="35" value="${model.record.thnaa}"></td>
											            
											        </tr>
		
											        <tr height="4"><td>&nbsp;</td></tr>
											        <tr>
											            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thada1">Adresse</span></td>
											            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thska">Sprogkode</span>
										            		
											            </td>
											        </tr>
											        <tr>
											            <td align="left"><input type="text" class="inputTextMediumBlue" name="thada1" id="thada1" size="30" maxlength="35" value="${model.record.thada1}"></td>
											            <td class="text14" align="left" >&nbsp;
											            	<input type="text" class="inputTextMediumBlue" name="thska" id="thska" size="3" maxlength="2" value="${model.record.thska}">
											            	
														</td>
											        </tr>
											        <tr>
											        		<td>
												        		<table>
												        		<tr>
												            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpsa">By</span></td>
												            		<td align="left">&nbsp;</td>
												            	</tr>
												        		<tr>
												            		<td align="left">
												       				<input type="text" class="inputTextMediumBlue" name="thpsa" id="thpsa" size="30" maxlength="35" value="${model.record.thpsa}">
											            			</td> 
												            		<td align="left">&nbsp;</td>
												        		</tr>    	
												            	</table>
											            </td>
											            <td >
												            	<table>
												        		<tr>
												        			<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpna">Postnummer</span></td>
												            		<td class="text14" align="left" >&nbsp;<span title="thlka">Land</span>
												            		
												            		</td>
												            	</tr>
												        		<tr >
												        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpna" id="thpna" size="10" maxlength="9" value="${model.record.thpna}"></td> 
												            		<td align="left">
												            			<input type="text" class="inputTextMediumBlue" name="thlka" id="thlka" size="3" maxlength="2" value="${model.record.thlka}">
												            			
												            		</td> 
												        		</tr>  
												            	</table>
											            </td>
										            	</tr>
											        
											        <tr height="15">
									            		<td class="text14Bold" align="left" >&nbsp;</td>
									            		<td class="text14Bold" align="left" >&nbsp;</td> 
											        </tr>  
											        
										        </table>
									        </td>
								        </tr>
									</table>          
				            		</td>
				           	</tr> 
				           	<tr height="20"><td></td></tr>
				           	</table>
						</td>
						<%-- --------------- --%>
						<%-- RIGHT SIDE CELL --%>
						<%-- --------------- --%>
						<td width="50%" align="center" valign="top">
							<table border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="2">&nbsp;</td>
									<td valign="top">
							 			<table border="0" cellspacing="0" cellpadding="0">
					                		<tr>
								 			<td class="text14" >
								 				<table align="left" border="0" cellspacing="2" cellpadding="0">
								 					<tr>
											            <td class="text14" align="left">
											            <img onMouseOver="showPop('forsegling_info');" onMouseOut="hidePop('forsegling_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 								&nbsp;<span title="thdfkd">Forseglings Id&nbsp;</span>
						 								<div class="text11" style="position: relative;" align="left">
														<span style="position:absolute;top:2px; width:250px;" id="forsegling_info" class="popupWithInputText text11"  >
							           						<br>
										           				Må angi alle verdier (Antall/kode/språk) eller ingen.
										           			<br/>
														</span>
														</div>
														</td>
											           	<td class="text14" align="left">
											           		<input type="text" class="inputTextMediumBlue" name="thdfkd" id="thdfkd" size="20" maxlength="20" value="${model.record.thdfkd}">
											           	</td>
										           	</tr>
										           	<tr>
											            <td class="text14" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											            		<span title="thdant">Forseglings-antal&nbsp;</span>
											            </td>
						 								<td class="text14" align="left">
											           		<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="thdant" id="thdant" size="4" maxlength="4" value="${model.record.thdant}">
											           	</td>
										           	</tr>
										           	<tr>
											            <td class="text14" align="left">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											            		<span title="thdfsk">Forseglings-sprogkode</span>
										            		</td>
						 								<td class="text14" align="left">
						 									<input type="text" class="inputTextMediumBlue" name="thdfsk" id="thdfsk" size="3" maxlength="2" value="${model.record.thdfsk}">
											           		&nbsp;
											           	</td>
										           	</tr>
													<tr height="20"><td></td></tr>						 				
									 				<tr>
											            <td class="text14" align="left">
											            <img onMouseOver="showPop('kontrolltullplats_info');" onMouseOut="hidePop('kontrolltullplats_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 								&nbsp;<span title="thdats/thddtk">Kontroll toldsted og dato&nbsp;</span>
						 								<div class="text11" style="position: relative;" align="left">
														<span style="position:absolute;top:2px; width:250px;" id="kontrolltullplats_info" class="popupWithInputText text11"  >
							           	
										           			<br>
										           				...
										           			<br/>
										           			<ul>
											           			<li>?</li>
											           		</ul>
											           	</span>
														</div>
														</td>
														
											           	<td class="text14" align="left">
											           		<input readonly type="text" class="inputTextReadOnly" name="thdats" id="thdats" size="20" maxlength="8" value="${model.record.thdats}">
											           	</td>
											           	<td class="text14" align="left">	
											           		<input readonly type="text" class="inputTextReadOnly" name="thddtk" id="thddtk" size="20" maxlength="8" value="${model.record.thddtk}">
											           		&nbsp;
											           	</td>
										           	</tr>
									 				<tr>
										 				<td nowrap class="text14">
										 				<img onMouseOver="showPop('returadress_info');" onMouseOut="hidePop('returadress_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											 			<b>&nbsp;9.</b>&nbsp;<span title="thtsn1/thtsn2/thtslk">Returadresse&nbsp;</span>
										 				<div class="text11" style="position: relative;" align="left">
														<span style="position:absolute;top:2px; width:250px;" id="returadress_info" class="popupWithInputText text11"  >
							           		           			Pending...
														</span>
														</div>
														</td>
								 			
										 				<td class="text14">
										 					<input readonly type="text" class="inputTextReadOnly" name="thtsn1" id="thtsn1" size="20" maxlength="35" value="${model.record.thtsn1}">
										 				</td>
										 				<td class="text14">
										 					<input readonly type="text" class="inputTextReadOnly" name="thtsn2" id="thtsn2" size="20" maxlength="35" value="${model.record.thtsn2}">
										 				</td>
									 				</tr>
									 				<tr>
										 				<td nowrap class="text14">&nbsp;</td>
							 							<td class="text14">
										 					<input readonly type="text" class="inputTextReadOnly" name="thtslk" id="thtslk" size="2" maxlength="2" value="${model.record.thtslk}">
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
					<%-- -------------------- --%>
					<%--   SIKKERHET  --%>
					<%-- -------------------- --%>
					<tr>
						<td colspan="2">&nbsp;&nbsp;
				            	<table width="98%" align="center" class="tableBorderWithRoundCornersDarkRed" border="0" cellspacing="2" cellpadding="0">
				            		<tr>
									<td width="50%" align="left" valign="top" class="text14" align="left" >
										<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="18px">
									 			<td class="text14">
									 				&nbsp;<img onMouseOver="showPop('sikkerhet_info');" onMouseOut="hidePop('sikkerhet_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											        	<b>Sikkerhed</b>&nbsp;Arbeid med forhåndsvarsling
											        <div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="sikkerhet_info" class="popupWithInputText text11"  >
							           					TODO
													</span>
													</div>	
								 				</td>
							 				</tr>
							 				<tr>
							 					
							            			<td class="text14">&nbsp;<span title="thsik">
							            				<img onMouseOver="showPop('sikkerhetIndicator_info');" onMouseOut="hidePop('sikkerhetIndicator_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            				Sikkerhed - Indikator</span>&nbsp;
							            				<div class="text11" style="position: relative;" align="left">
														<span style="position:absolute;top:2px; width:250px;" id="sikkerhetIndicator_info" class="popupWithInputText text11"  >
							           						Indikator for specifik omstendighet (0 eller 1)	
														</span>	
														</div>
													
							            				<select name="thsik" id="thsik" >
									 				  <option value="0"<c:if test="${model.record.sikkerhedChildRecord.thsik == '0'}"> selected </c:if> >0</option>
													  <option value="1"<c:if test="${model.record.sikkerhedChildRecord.thsik == '1'}"> selected </c:if> >1</option>
													</select>
													&nbsp;&nbsp;<span title="thdta">Ank.dato</span>&nbsp;<input onKeyPress="return numberKey(event) type="text" class="inputTextMediumBlue" name="thdta" id="thdta" size="9" maxlength="8" value="${model.record.sikkerhedChildRecord.thdta}">
													&nbsp;<span title="thtma">Tid</span>&nbsp;<input onKeyPress="return numberKey(event) type="text" class="inputTextMediumBlue" name="thtma" id="thtma" size="5" maxlength="4" value="${model.record.sikkerhedChildRecord.thtma}">
												</td>
							            		</tr>
							            		<tr>
							            			<td class="text14">&nbsp;<span title="thkref">Kommersielt ref.nr</span></td>
							            		</tr>
							            		<tr>
							            			<td><input type="text" class="inputTextMediumBlue" name="thkref" id="thkref" size="70" maxlength="70" value="${model.record.sikkerhedChildRecord.thkref}"></td>
							            		</tr>
							            		<tr>
							            			<td class="text14">&nbsp;<span title="thtref">Transportør ref.nr</span></td>
							            		</tr>
							            		<tr>
												<td><input type="text" class="inputTextMediumBlue" name="thtref" id="thtref" size="70" maxlength="35" value="${model.record.sikkerhedChildRecord.thtref}"></td>
							            		</tr>
							        		</table>
							        </td>
						            <td width="50%" align="left" valign="top" class="text14" align="left" >   
						            		<table width="99%" align="left" border="0" cellspacing="0" cellpadding="0">
						            			<tr height="18px">
							            			<td class="text14">&nbsp;</td>
							            		</tr>
				            					<tr>
							            			<td class="text14">
							            				<img onMouseOver="showPop('spesOmstand_info');" onMouseOut="hidePop('spesOmstand_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            				<span title="thspom">Spes.omstændighed</span>&nbsp;
							            				<select name="thspom" id="thspom">
							            				<option value="">-vælg-</option>
							 				  			<c:forEach var="code" items="${model.ncts096_SpecOmst_CodeList}" >
			                             	 				<option value="${code.tkkode}"<c:if test="${model.record.sikkerhedChildRecord.thspom == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
														</c:forEach> 
													</select>
													<div class="text11" style="position: relative;" align="left">
														<span style="position:absolute;top:2px; width:250px;" id="spesOmstand_info" class="popupWithInputText text11"  >
										           			<ul>
										           				<c:forEach var="code" items="${model.ncts096_SpecOmst_CodeList}" >
											 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
										 				  		</c:forEach>	
										           			</ul>
														</span>
													</div>
						            				</td>
							            			<td class="text14" nowrap>
							            				<img onMouseOver="showPop('betalmade_info');" onMouseOut="hidePop('betalmade_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							            				<span title="thtkbm">Transp.kost/Betal.måde</span>&nbsp;
							            				<select name="thtkbm" id="thtkbm">
							            				<option value="">-vælg-</option>
							 				  			<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
			                             	 				<option value="${code.tkkode}"<c:if test="${model.record.sikkerhedChildRecord.thtkbm == code.tkkode}"> selected </c:if> >${code.tkkode}</option>
														</c:forEach> 
													</select>
													<div class="text11" style="position: relative;" align="left">
														<span style="position:absolute;top:2px; width:250px;" id="betalmade_info" class="popupWithInputText text11"  >
										           			<ul>
										           				<c:forEach var="code" items="${model.ncts116_BetalningTransport_CodeList}" >
											 				  		<li><b>${code.tkkode}</b>&nbsp;${code.tktxtn}</li>
										 				  		</c:forEach>	
										           			</ul>
													</span>
													</div>
							            			</td>
							            		</tr>
							            		<tr>
							            			<td class="text14" nowrap>&nbsp;&nbsp;<span title="thlosd">Lossested</span>
							            				<input type="text" class="inputTextMediumBlue" name="thlosd" id="thlosd" size="30" maxlength="35" value="${model.record.sikkerhedChildRecord.thlosd}">
							            			</td>
							            			<td class="text14">&nbsp;&nbsp;<span title="thlosdsk">Lossested sprogkode</span>
							            				<input type="text" class="inputTextMediumBlue" name="thlosdsk" id="thlosdsk" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlosdsk}">
							            				
							            			</td>
							            		</tr>
							            		<tr>
							            			<td class="text14">&nbsp;
							            				<table>
							            				<tr>
							            					<td colspan="4" class="text14">&nbsp;
							            					<span title="thlkr1-8">Landkode for rejseplan</span>
							            					
							            					</td>
							            				</tr>
							            				<tr>
								            				<td>
									            				<input type="text" class="inputTextMediumBlue" name="thlkr1" id="thlkr1" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlkr1}">
															</td>
															<td>
																<input type="text" class="inputTextMediumBlue" name="thlkr2" id="thlkr2" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlkr2}">
															</td>
															<td>
																<input type="text" class="inputTextMediumBlue" name="thlkr3" id="thlkr3" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlkr3}">
															</td>
															<td>
																<input type="text" class="inputTextMediumBlue" name="thlkr4" id="thlkr4" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlkr4}">
								            				</td>
							            				</tr>
							            				<tr>	
								            				<td>
								            					<input type="text" class="inputTextMediumBlue" name="thlkr5" id="thlkr5" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlkr5}">
								            				</td>
								            				<td>
									            				<input type="text" class="inputTextMediumBlue" name="thlkr6" id="thlkr6" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlkr6}">
								            				</td>
								            				<td>
									            				<input type="text" class="inputTextMediumBlue" name="thlkr7" id="thlkr7" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlkr7}">
															</td>
															<td>
																<input type="text" class="inputTextMediumBlue" name="thlkr8" id="thlkr8" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlkr8}">
															</td>
													</tr>
													</table>
							            			</td>
							            		</tr>
					 					</table>
									</td>
								</tr>
				            		<tr height="3"><td>&nbsp;</td></tr>
						        	<tr>
						        		<td width="50%" align="center" valign="top">
										<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="18px">
									 			<td class="text14White">
									 				&nbsp;
							 						&nbsp;Afsender - Sikkerhed&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
								 				</td>
							 				</tr>
					 					</table>
					 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
									 		<tr>	
									 			<td width="5">&nbsp;</td>
									            <td >	
													<%-- create record --%>
												 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
												 		<tr>
													 		<td>
														 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
													 				<%-- ================================================================================== --%>
															        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
															        		 These original values will be used when the user clicks "Cancel" buttons (puttting
															        		 back original value)																--%> 
															        	<%-- ================================================================================== --%>
															        	<input type="hidden" name="orig_thknss" id="orig_thknss" value='${Xmodel.record.thknss}'>
															        	<input type="hidden" name="orig_thnass" id="orig_thnass" value='${Xmodel.record.thnass}'>
															        	<input type="hidden" name="orig_thtinss" id="orig_thtinss" value='${Xmodel.record.thtinss}'>
															        	<input type="hidden" name="orig_thadss1" id="orig_thadss1" value='${Xmodel.record.thadss1}'>
															        	<input type="hidden" name="orig_thpnss" id="orig_thpnss" value='${Xmodel.record.thpnss}'>
															        	<input type="hidden" name="orig_thpsss" id="orig_thpsss" value='${Xmodel.record.thpsss}'>
															        	<input type="hidden" name="orig_thlkss" id="orig_thlkss" value='${Xmodel.record.thlkss}'>
															        	<input type="hidden" name="orig_thskss" id="orig_thskss" value='${Xmodel.record.thskss}'>
															        	
															        	<tr height="10">
															            <td class="text14" align="left">&nbsp;</td> 
															        </tr>
															 		<tr height="15">
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thknss">Kundenummer</span></td>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thnass">Navn</span>
															            	<a tabindex="-1" id="thnassIdLink">
																				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																			</a>
													            		</td>
															        </tr>
															        <tr>
															        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknss" id="thknss" size="10" maxlength="8" value="${model.record.sikkerhedChildRecord.thknss}"></td>
															            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnass" id="thnass" size="30" maxlength="35" value="${model.record.sikkerhedChildRecord.thnass}"></td>
															        </tr>
															        <tr>
													        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="thtinss">CVR/SE-nr</span></td>
															        </tr>
															        <tr>
															        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtinss" id="thtinss" size="20" maxlength="17" value="${model.record.sikkerhedChildRecord.thtinss}"></td>
															        </tr>
						
															        <tr height="4"><td>&nbsp;</td></tr>
															        <tr>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thadss1">Adresse</span></td>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thskss">Sprogkode</span>
														            		
															            </td>
															        </tr>
															        <tr>
															            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadss1" id="thadss1" size="30" maxlength="30" value="${model.record.sikkerhedChildRecord.thadss1}"></td>
															            <td class="text14" align="left" >&nbsp;
														            		<input type="text" class="inputTextMediumBlue" name="thskss" id="thskss" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thskss}">
																		</td>
															        </tr>
															        <tr>
															        		<td>
																        		<table>
																        		<tr>
																            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpsss">By</span></td>
																            		<td align="left">&nbsp;</td>
																            	</tr>
																        		<tr>
																            		<td align="left">
																       				<input type="text" class="inputTextMediumBlue" name="thpsss" id="thpsss" size="30" maxlength="24" value="${model.record.sikkerhedChildRecord.thpsss}">
															            			</td> 
																            		<td align="left">&nbsp;</td>
																        		</tr>    	
																            	</table>
															            </td>
															            <td >
																            	<table>
																        		<tr>
																        			<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpnss">Postnummer</span></td>
																            		<td class="text14" align="left" >&nbsp;<span title="thlkss">Land</span>
																            		
																            		</td>
																            	</tr>
																        		<tr >
																        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnss" id="thpnss" size="10" maxlength="8" value="${model.record.sikkerhedChildRecord.thpnss}"></td> 
																            		<td align="left">
																            			<input type="text" class="inputTextMediumBlue" name="thlkss" id="thlkss" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlkss}">
																            		</td> 
																        		</tr>  
																            	</table>
															            </td>
														            	</tr>
															        
															        <tr height="15">
													            		<td class="text14Bold" align="left" >&nbsp;</td>
													            		<td class="text14Bold" align="left" >&nbsp;</td> 
															        </tr>  
															        
														        </table>
													        </td>
												        </tr>
													</table>          
								            		</td>
								           	</tr> 
				 						</table>
					 					<table width="99%" align="center" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="5"><td>&nbsp;</td></tr>
					 					</table>	
										<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="18px">
									 			<td class="text14White">
									 				&nbsp;
							 						&nbsp;Transportør - Sikkerhed&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
								 				</td>
							 				</tr>
					 					</table>
					 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
									 		<tr>	
									 			<td width="5">&nbsp;</td>
									            <td >	
													<%-- create record --%>
												 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
												 		<tr>
													 		<td>
														 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
													 				<%-- ================================================================================== --%>
															        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
															        		 These original values will be used when the user clicks "Cancel" buttons (puttting
															        		 back original value)																--%> 
															        	<%-- ================================================================================== --%>
															        	<input type="hidden" name="orig_thknt" id="orig_thknt" value='${Xmodel.record.thknt}'>
															        	<input type="hidden" name="orig_thnat" id="orig_thnat" value='${Xmodel.record.thnat}'>
															        	<input type="hidden" name="orig_thtint" id="orig_thtint" value='${Xmodel.record.thtint}'>
															        	<input type="hidden" name="orig_thadt1" id="orig_thadt1" value='${Xmodel.record.thadt1}'>
															        	<input type="hidden" name="orig_thpnt" id="orig_thpnt" value='${Xmodel.record.thpnt}'>
															        	<input type="hidden" name="orig_thpst" id="orig_thpst" value='${Xmodel.record.thpst}'>
															        	<input type="hidden" name="orig_thlkt" id="orig_thlkt" value='${Xmodel.record.thlkt}'>
															        	<input type="hidden" name="orig_thskt" id="orig_thskt" value='${Xmodel.record.thskt}'>
															        	
															        	<tr height="10">
															            <td class="text14" align="left">&nbsp;</td> 
															        </tr>
															 		<tr height="15">
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thknt">Kundenummer</span></td>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thnat">Navn</span>
															            	<a tabindex="-1" id="thnatIdLink">
																				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																			</a>
													            		</td>
															        </tr>
															        <tr>
															        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknt" id="thknt" size="10" maxlength="8" value="${model.record.sikkerhedChildRecord.thknt}"></td>
															            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnat" id="thnat" size="30" maxlength="35" value="${model.record.sikkerhedChildRecord.thnat}"></td>
															        </tr>
															        <tr>
													        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="thtint">EORI</span></td>
															        </tr>
															        <tr>
															        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtint" id="thtint" size="20" maxlength="17" value="${model.record.sikkerhedChildRecord.thtint}"></td>
															        </tr>
						
															        <tr height="4"><td>&nbsp;</td></tr>
															        <tr>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thadt1">Adresse</span></td>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thskt">Sprogkode</span>
														            		
															            </td>
															        </tr>
															        <tr>
															            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadt1" id="thadt1" size="30" maxlength="30" value="${model.record.sikkerhedChildRecord.thadt1}"></td>
															            <td class="text14" align="left" >&nbsp;
															            	<input type="text" class="inputTextMediumBlue" name="thskt" id="thskt" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thskt}">
																		</td>
															        </tr>
															        <tr>
															        		<td>
																        		<table>
																        		<tr>
																            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpst">By</span></td>
																            		<td align="left">&nbsp;</td>
																            	</tr>
																        		<tr>
																            		<td align="left">
																       				<input type="text" class="inputTextMediumBlue" name="thpst" id="thpst" size="30" maxlength="24" value="${model.record.sikkerhedChildRecord.thpst}">
															            			</td> 
																            		<td align="left">&nbsp;</td>
																        		</tr>    	
																            	</table>
															            </td>
															            <td >
																            	<table>
																        		<tr>
																        			<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpnt">Postnummer</span></td>
																            		<td class="text14" align="left" >&nbsp;<span title="thlkt">Land</span>
																            		
																            		</td>
																            	</tr>
																        		<tr >
																        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnt" id="thpnt" size="10" maxlength="8" value="${model.record.sikkerhedChildRecord.thpnt}"></td> 
																            		<td align="left">
																            			<input type="text" class="inputTextMediumBlue" name="thlkt" id="thlkt" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlkt}">
															            			</td> 
																        		</tr>  
																            	</table>
															            </td>
														            	</tr>
															        
															        <tr height="15">
													            		<td class="text14Bold" align="left" >&nbsp;</td>
													            		<td class="text14Bold" align="left" >&nbsp;</td> 
															        </tr>  
															        
														        </table>
													        </td>
												        </tr>
													</table>          
								            		</td>
								           	</tr> 
				 						</table>
					        			</td>
					        			<td width="50%" align="center" valign="top">
										<table width="99%" align="center" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
									 		<tr height="18px">
									 			<td class="text14White">
									 				&nbsp;
							 						&nbsp;Modtager - Sikkerhed&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
								 				</td>
							 				</tr>
					 					</table>
					 					<table width="99%" align="center" class="formFrame" border="0" cellspacing="0" cellpadding="0">
									 		<tr>	
									 			<td width="5">&nbsp;</td>
									            <td >	
													<%-- create record --%>
												 	<table width="100%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
												 		<tr>
													 		<td>
														 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
															 		<%-- ================================================================================== --%>
															        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
															        		 These original values will be used when the user clicks "Cancel" buttons (puttting
															        		 back original value)																--%> 
															        	<%-- ================================================================================== --%>
															        	<input type="hidden" name="orig_thknks" id="orig_thknks" value='${Xmodel.record.thknks}'>
															        	<input type="hidden" name="orig_thnaks" id="orig_thnaks" value='${Xmodel.record.thnaks}'>
															        	<input type="hidden" name="orig_thtinks" id="orig_thtinks" value='${Xmodel.record.thtinks}'>
															        	<input type="hidden" name="orig_thadks1" id="orig_thadks1" value='${Xmodel.record.thadks1}'>
															        	<input type="hidden" name="orig_thpnks" id="orig_thpnks" value='${Xmodel.record.thpnks}'>
															        	<input type="hidden" name="orig_thpsks" id="orig_thpsks" value='${Xmodel.record.thpsks}'>
															        	<input type="hidden" name="orig_thlkks" id="orig_thlkks" value='${Xmodel.record.thlkks}'>
															        	<input type="hidden" name="orig_thskks" id="orig_thskks" value='${Xmodel.record.thskks}'>
															 		<tr height="10">
															            <td class="text14" align="left">&nbsp;</td> 
															        </tr>
															        <tr height="15">
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thknks">Kundenummer</span></td>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thnaks">Navn</span>
															            	<a tabindex="-1" id="thnaksIdLink">
																				<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
																			</a>	
															            </td>
															        </tr>
															        <tr>
															        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thknks" id="thknks" size="10" maxlength="8" value="${model.record.sikkerhedChildRecord.thknks}"></td>
															            <td align="left"><input type="text" class="inputTextMediumBlue" name="thnaks" id="thnaks" size="30" maxlength="35" value="${model.record.sikkerhedChildRecord.thnaks}"></td>
															        </tr>
															        <tr>
													        				<td class="text14" align="left" >&nbsp;&nbsp;<span title="thtinks">EORI</span></td>
															        </tr>
															        <tr>
															        		<td align="left"><input type="text" class="inputTextMediumBlue" name="thtinks" id="thtinks" size="20" maxlength="17" value="${model.record.sikkerhedChildRecord.thtinks}"></td>
															        </tr>
						
															        <tr height="4"><td>&nbsp;</td></tr>
															        <tr>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thadks1">Adresse</span></td>
															            <td class="text14" align="left" >&nbsp;&nbsp;<span title="thskks">Sprogkode</span>
														            		
															            </td>
															        </tr>
															        <tr>
															            <td align="left"><input type="text" class="inputTextMediumBlue" name="thadks1" id="thadks1" size="30" maxlength="30" value="${model.record.sikkerhedChildRecord.thadks1}"></td>
															            <td class="text14" align="left" >&nbsp;
															            	<input type="text" class="inputTextMediumBlue" name="thskks" id="thskks" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thskks}">
																		</td>
															        </tr>
															        <tr>
															        		<td>
																        		<table>
																        		<tr>
																            		<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpsks">By</span></td>
																            		<td align="left">&nbsp;</td>
																            	</tr>
																        		<tr>
																            		<td align="left">
																       				<input type="text" class="inputTextMediumBlue" name="thpsks" id="thpsks" size="30" maxlength="24" value="${model.record.sikkerhedChildRecord.thpsks}">
															            			</td> 
																            		<td align="left">&nbsp;</td>
																        		</tr>    	
																            	</table>
															            </td>
															            <td >
																            	<table>
																        		<tr>
																        			<td class="text14" align="left" >&nbsp;&nbsp;<span title="thpnks">Postnummer</span></td>
																            		<td class="text14" align="left" >&nbsp;<span title="thlkks">Land</span>
																            		
																            		</td>
																            	</tr>
																        		<tr >
																        			<td align="left"><input type="text" class="inputTextMediumBlue" name="thpnks" id="thpnks" size="10" maxlength="8" value="${model.record.sikkerhedChildRecord.thpnks}"></td> 
																            		<td align="left">
																            			<input type="text" class="inputTextMediumBlue" name="thlkks" id="thlkks" size="3" maxlength="2" value="${model.record.sikkerhedChildRecord.thlkks}">
																            		</td> 
																        		</tr>  
																            	</table>
															            </td>
														            	</tr>
															        
															        <tr height="15">
													            		<td class="text14Bold" align="left" >&nbsp;</td>
													            		<td class="text14Bold" align="left" >&nbsp;</td> 
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
						        	<tr height="10"><td>&nbsp;</td></tr>
				            	</table>
				            </td>  
				            <td>&nbsp;</td>
				        </tr>
				        	<tr height="20"><td>&nbsp;</td></tr>
					</table> <%-- END to the wrapper table for EXTRAORDINARY data --%>	
					</td>		
				</tr>

	 	    
			</table>
	 		</form>
	 		
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

