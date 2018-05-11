<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavdtdsnctsimport_svx053r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
							<font class="tabDisabledLink">&nbsp;Underhåll</font>
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
							</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="13%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;Avdelningar</font>&nbsp;						
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>

					<td width="18%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdSadiSyftaaar" onClick="setBlockUI(this);" href="mainmaintenanceavdtdsnctsimport_svx053r.do?id=${model.id}">
                  			<font class="tabDisabledLink">&nbsp;<spring:message code="systema.ncts.import.label"/></font>&nbsp;
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
								<font class="tabLink">&nbsp;Skapa ny avd.</font>&nbsp;
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
			<form action="mainmaintenanceavdtdsnctsimport_svx053r_edit.do?id=${model.id}" name="formRecord" id="formRecord" method="POST" >
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
									<td colspan="4" class="text14MediumBlue" title=".">Avd.navn&nbsp;&nbsp;&nbsp;
										<input readonly class="inputTextReadOnly" id="avdnavn" name="avdnavn" type="text" size="45" value="${model.avdnavn}">
									</td>
								</tr>
								<tr height="5"><td >&nbsp;</td></tr>
							</c:if>
							
				    	    <tr>
				    	    	<c:choose>
					    	    	<c:when test="${not empty model.updateId}">
					    	    		<input type="hidden" name="tiavd" id="tiavd" value="${model.avd}">
					    	    	</c:when>
					    	    	<c:otherwise>
					    	    		<td class="text14" title="tiavd"><font class="text14RedBold" >*</font>Avdelning
					    	    			<a tabindex="-1" id="tiavdIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
					    	    		</td>
										<td class="text14" >
										<select name="tiavd" id="tiavd" class="inputTextMediumBlueMandatoryField">
						  					<option value="">-vælg-</option>
						  					<c:forEach var="record" items="${model.avdGeneralList}" >
						 				  		<option value="${record.koaavd}"<c:if test="${ model.record.tiavd == record.koaavd}"> selected </c:if> >${record.koaavd}</option>
											  </c:forEach>  
											</select>
										</td>	
					    	    	</c:otherwise>
				    	    	</c:choose>
							</tr>
							<tr>	
								<td class="text14" title="tienkl"><font class="text14RedBold" >*</font>Prosedur</td>
								<td class="text14" >
									<select name="tienkl" id="tienkl" class="inputTextMediumBlueMandatoryField">
					  					<option value="J"<c:if test="${ model.record.tienkl == 'J' || empty model.record.tienkl}"> selected </c:if> >Forenklet</option>
					  					<option value="N"<c:if test="${ model.record.tienkl == 'N'}"> selected </c:if> >Normal</option>
								  	</select>
								</td>
								<td class="text14" title="titdn">&nbsp;&nbsp;<font class="text14RedBold" >*</font>Sist använda internnr.</td>
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
						<tr><td colspan="2" class="text14"><b>Kommunikation </b><font class="text11" >&nbsp;</font></td></tr>
						<tr height="5"><td></td></tr>
			    	    
						<tr>
							<td class="text14" title="s0004"><font class="text14RedBold" >*</font>UtväxlingsId Avdelning 
								<a tabindex="-1" id="s0004IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text14">
								<input type="text" class="inputTextMediumBlueMandatoryField" name="s0004" id="s0004" size="20" maxlength="35" value='${model.record.s0004}'>
							</td>
						
							<td class="text14" title="s0010"><font class="text14RedBold" >*</font>UtväxlingsId Tullverket 
								<a tabindex="-1" id="s0010IdLink">
									<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
								</a>
							</td>
							<td class="text14">
								<input type="text" class="inputTextMediumBlueMandatoryField" name="s0010" id="s0010" size="20" maxlength="35" value='${model.record.s0010}'>
							</td>
						</tr>
						<tr>	
							
							<td class="text14" title="s0026">&nbsp;&nbsp;Applikationsreference</td>
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
						</tr>
						
						<tr height="5"><td></td></tr>
					</table>
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
						<table width="95%" cellspacing="0" border="0" align="left">
						<tr>
					            <td >		
					 				<%-- Ansvarlig --%>
					 				<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
								 		<tr height="15">
								 			<td class="text14White">
												&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 			<b>&nbsp;</b>Näringsliv&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
									           		<b>Näringsliv</b>
										           	Todo
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
									 		<td width="95%" >
										 		<table width="95%" border="0" cellspacing="0" cellpadding="0">
											 		<tr height="10"><td ></td></tr>
											        
											        <tr>
											        	<%-- ================================================================================== --%>
											        	<%-- This hidden values are used when an AJAX event from within a dialog box is fired.  
											        		 These original values will be used when the user clicks "Cancel" buttons (puttting
											        		 back original value)																--%> 
											        	<%-- ================================================================================== --%>
											        	<input type="hidden" name="orig_thkns" id="orig_thkns" value='${XXmodel.record.thkns}'>
											        	<input type="hidden" name="orig_thnas" id="orig_thnas" value='${XXmodel.record.thnas}'>
											        	<input type="hidden" name="orig_thtins" id="orig_thtins" value='${XXmodel.record.thtins}'>
											        	<input type="hidden" name="orig_thads1" id="orig_thads1" value='${XXmodel.record.thads1}'>
											        	<input type="hidden" name="orig_thpns" id="orig_thpns" value='${XXmodel.record.thpns}'>
											        	<input type="hidden" name="orig_thpss" id="orig_thpss" value='${XXmodel.record.thpss}'>
											        	<input type="hidden" name="orig_thlks" id="orig_thlks" value='${XXmodel.record.thlks}'>
											        	<input type="hidden" name="orig_thsks" id="orig_thsks" value='${XXmodel.record.thsks}'>
											        	
											        	
											            <td class="text14" align="left" >&nbsp;&nbsp;<span title="tikn">Kundnr</span></td>
											            <td class="text14" align="left" >&nbsp;<span title="tina">Namn&nbsp;</span>
											            	<a tabindex="-1" id="tinaIdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
															</a>
											            </td>
											        </tr>
											        <tr>
											            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="tikn" id="tikn" size="8" maxlength="8" value="${model.record.tikn}"></td>
											            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="tina" id="tina" size="30" maxlength="35" value="${model.record.tina}"></td>
											        </tr>
											        
											        <tr>
											            <td class="text14" align="left" >&nbsp;<span title="titin">EORI</span></td>
											            <td class="text14" align="left" >&nbsp;&nbsp;</td>
											        </tr>
											        <tr>
											            <td align="left"><input type="text" class="inputTextMediumBlue" name="titin" id="titin" size="20" maxlength="17" value="${model.record.titin}"></td>
											            <td align="left">&nbsp;</td>
											        </tr>
											        <tr height="4"><td>&nbsp;</td></tr>
											        <tr>
											            <td class="text14" align="left" >&nbsp;<span title="tiad1">Adress</span></td>
											            <td class="text14" align="left" >&nbsp;<span title="tisk">Språkkod</span></td>
											        </tr>
											        <tr>
											            <td align="left"><input type="text" class="inputTextMediumBlue" name="tiad1" id="tiad1" size="30" maxlength="35" value="${model.record.tiad1}"></td>
											            <td align="left" >&nbsp;
											            	<input type="text" class="inputTextMediumBlue" name="tisk" id="tisk" size="3" maxlength="2" value="${model.record.tisk}">
														</td>
											        </tr>
											        <tr>
											        		<td>
												        		<table>
												        		<tr>
												            		<td class="text14" align="left" >&nbsp;<span title="tips">Postadress</span></td>
												            		<td align="left">&nbsp;</td>
												            	</tr>
												        		<tr>
												            		<td align="left">
												       				<input type="text" class="inputTextMediumBlue" name="tips" id="tips" size="30" maxlength="35" value="${model.record.tips}">
											            			</td> 
												            		<td align="left">&nbsp;</td>
												        		</tr>    	
												            	</table>
											            </td>
											            <td >
												            	<table>
												        		<tr>
												        			<td class="text14" align="left" >&nbsp;<span title="tipns">Postnummer</span></td>
												            		<td class="text14" align="left" >&nbsp;<span title="tilk">Land</span>
												            		
												            		</td>
												            	</tr>
												        		<tr >
												        			<td align="left"><input type="text" class="inputTextMediumBlue" name="tipn" id="tipn" size="10" maxlength="9" value="${model.record.tipn}"></td> 
												            		<td align="left">
												            			<input type="text" class="inputTextMediumBlue" name="tilk" id="tilk" size="3" maxlength="2" value="${model.record.tilk}">
												            			
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
							 					<td class="text14" align="left" ><span title="tign">Godsnr. - Egen ref.&nbsp;</span></td>
									            <td ><input type="text" class="inputTextMediumBlue" name="tign" id="tign" size="35" maxlength="35" value="${model.record.tign}"></td>
							 				</tr>
							 				<tr>
							 					<td class="text14" align="left" ><span title="tignsk">Språkkod&nbsp;</span></td>
									            <td ><input type="text" class="inputTextMediumBlue" name="tignsk" id="tignsk" size="3" maxlength="2" value="${model.record.tignsk}"></td>
							 				</tr>
							 				<tr>
							 					<td class="text14" align="left" >
									            <img onMouseOver="showPop('15_info');" onMouseOut="hidePop('15_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 					<span title="tialss">MRN-nr&nbsp;</span>
									            <td >
									            	<input type="text" class="inputTextMediumBlue" name="titrnr" id="titrnr" size="18" maxlength="18" value="${model.record.titrnr}">
												</td>
							 				</tr>
							 				
							 				<tr>
									            <td class="text14" align="left" >
									            <img onMouseOver="showPop('17_info');" onMouseOut="hidePop('17_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									            
									            <span title="tialk">Avs.land&nbsp;</span>
									            <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="17_info" class="popupWithInputText text11"  >
								           			Todo
												</span>	
												</div>
									            </td>
									            
									            <td >
									            	<input type="text" class="inputTextMediumBlue" name="tialk" id="tialk" size="3" maxlength="2" value="${model.record.tialk}">
									            
												</td>
									        </tr>
									        <tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
			 								<tr height="5"><td class="text">&nbsp;</td> </tr>
								            
								            <tr>
									            <td class="text14" align="left" >
									            <img onMouseOver="showPop('tialsk_info');" onMouseOut="hidePop('tialsk_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									            
									            <span title="tialsk">Avt.lagringsplats&nbsp;(kod)</span>
									            <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="tialsk_info" class="popupWithInputText text11"  >
								           			Todo
												</span>	
												</div>
									            </td>
									            
									            <td class="text14" align="left" >
									            	<input type="text" class="inputTextMediumBlue" name="tialsk" id="tialsk" size="18" maxlength="17" value="${model.record.tialsk}">
									            	&nbsp;&nbsp;&nbsp;
									            	<span title="tialss">Språkkod</span>
									            	&nbsp;
									            	<input type="text" class="inputTextMediumBlue" name="tialss" id="tialss" size="3" maxlength="2" value="${model.record.tialss}">
												</td>
									        </tr>
					        				<tr>
									            <td class="text14" align="left" >
									            <img onMouseOver="showPop('tials_info');" onMouseOut="hidePop('tials_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									            
									            <span title="tials">Avt.lagringsplats&nbsp;</span>
									            <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="tials_info" class="popupWithInputText text11"  >
								           			Todo
												</span>	
												</div>
									            </td>
									            
									            <td class="text14" align="left" >
									            	<input type="text" class="inputTextMediumBlue" name="tials" id="tials" size="30" maxlength="35" value="${model.record.tials}">
												</td>
									        </tr>
					        				
					        				<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text">&nbsp;</td> </tr>
			 								<tr height="5"><td class="text">&nbsp;</td> </tr>
								            
				 		       				<tr>
									            <td class="text14" align="left" >
									            <img onMouseOver="showPop('tiglsk_info');" onMouseOut="hidePop('tiglsk_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									            
									            <span title="tiglsk">Godk.lagringsplats (kod)</span>
									            <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="tiglsk_info" class="popupWithInputText text11"  >
								           			Todo
												</span>	
												</div>
									            </td>
									            <td class="text14" align="left" >
									            	<input type="text" class="inputTextMediumBlue" name="tiglsk" id="tiglsk" size="18" maxlength="17" value="${model.record.tiglsk}">
												</td>
									        </tr>
									        <tr>
									            <td class="text14" align="left" >
									            <img onMouseOver="showPop('tiacts_info');" onMouseOut="hidePop('tiacts_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									            
									            <span title="tiacts">Kontrollplats (kod)</span>
									            <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="tiacts_info" class="popupWithInputText text11"  >
								           			Todo
												</span>	
												</div>
									            </td>
									            <td class="text14" align="left" >
									            	<input type="text" class="inputTextMediumBlue" name="tiacts" id="tiacts" size="18" maxlength="17" value="${model.record.tiacts}">
												</td>
									        </tr>
					        				<tr>
									            <td class="text14" align="left" >
									            <img onMouseOver="showPop('titsb_info');" onMouseOut="hidePop('titsb_info');"style="vertical-align:top;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									            
									            <span title="titsb">Best.tullkontor&nbsp;(kod)</span>
									            <div class="text11" style="position: relative;" align="left">
												<span style="position:absolute;top:2px; width:250px;" id="titsb_info" class="popupWithInputText text11"  >
								           			Todo
												</span>	
												</div>
									            </td>
									            
									            <td class="text14" align="left" >
									            	<input type="text" class="inputTextMediumBlue" name="titsb" id="titsb" size="9" maxlength="8" value="${model.record.titsb}">
									            	<a tabindex="-1" id="titsbIdLink">
														<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
													</a>
									            	&nbsp;&nbsp;&nbsp;
									            	<span title="tiskb">Språkkod</span>
									            	&nbsp;
									            	<input type="text" class="inputTextMediumBlue" name="tiskb" id="tiskb" size="3" maxlength="2" value="${model.record.tiskb}">
												</td>
									        </tr>
									        <tr height="10"><td></td></tr>
					        				<tr>
									            <td class="text14" align="left">&nbsp;</td>
												<td class="text9BlueGreen" valign="bottom"  >
					 				    			<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spara' onClick="setBlockUI(this);"/>
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

			</table>
	 		</form>
	 		
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

