<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavdskatexport_dke051r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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

					<td width="18%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdSadiSyftaaar" onClick="setBlockUI(this);" href="mainmaintenanceavdskatexport_dke051r.do?id=${model.id}">
                  			<font class="tabLink">&nbsp;<spring:message code="systema.skat.export.tab.description.avd"/></font>&nbsp;
						</a>
					</td>
	                    
					<c:choose>
						<c:when test="${not empty model.updateId}">
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="16%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Afd/Angiv.</font>&nbsp;
								<font class="text11MediumBlue">(${model.avd} / ${model.opd})</font>
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
			<form action="mainmaintenanceavdskatexport_dke051r_edit.do?id=${model.id}" name="formRecord" id="formRecord" method="POST" >
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
						<table border="0" align="left">
						
				    	    <tr>
				    	    	<c:choose>
					    	    	<c:when test="${not empty model.updateId}">
					    	    		<input type="hidden" name="dkea_syav" id="dkea_syav" value="${model.avd}">
					    	    		<input type="hidden" name="avd" id="avd" value="${model.avd}">
					    	    		<input type="hidden" name="opd" id="opd" value="${model.opd}">
					    	    		
					    	    		<td colspan="4" class="text14MediumBlue" title=".">Afd.navn&nbsp;&nbsp;&nbsp;
											<input readonly class="inputTextReadOnly" id="avdnavn" name="avdnavn" type="text" size="45" value="${model.avdnavn}">
										</td>
										<td class="text14" title="dkea_syop">&nbsp;&nbsp;&nbsp;<font class="text14RedBold" >*</font>Angiv.nr - nr.teller</td>
										<td class="text14" >
											<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkea_syop" id="dkea_syop" size="10" maxlength="8" value='${model.record.dkea_syop}'>
										</td>
					    	    		
					    	    	</c:when>
					    	    	<c:otherwise>
					    	    		<td class="text14" title="dkea_syav"><font class="text14RedBold" >*</font>Afdeling
					    	    			<a tabindex="-1" id="avdIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
					    	    		</td>
										<td class="text14" >
										<select name="dkea_syav" id="dkea_syav" class="inputTextMediumBlueMandatoryField">
						  					<option value="">-vælg-</option>
						  					<c:forEach var="record" items="${model.avdGeneralList}" >
						 				  		<option value="${record.koaavd}"<c:if test="${ model.record.dkea_syav == record.koaavd}"> selected </c:if> >${record.koaavd}</option>
											  </c:forEach>  
											</select>
										</td>
										
										<td class="text14" title="dkea_syop">&nbsp;&nbsp;&nbsp;<font class="text14RedBold" >*</font>Angiv.nr - nr.teller</td>
										<td class="text14" >
											<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="dkea_syop" id="dkea_syop" size="10" maxlength="8" value='${model.record.dkea_syop}'>
										</td>
										
					    	    	</c:otherwise>
				    	    	</c:choose>
							</tr>
							<tr height="5"><td colspan="4" >&nbsp;</td></tr>
						</table>
				</td>
			</tr>
			
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
					
					<table width="95%" class="formFrameTitaniumWhite" cellspacing="1" border="0" align="left">
						<tr><td colspan="2" class="text14"><b>&nbsp;FTP</b><font class="text11" >&nbsp;</font></td></tr>
						<tr height="5"><td></td></tr>
			    	    
						<tr>
							<td class="text14" title="dkea_ftip"><font class="text14RedBold" >*</font>FTP-adresse</td>
							<td colspan="5" class="text14">
								<input type="text" class="inputTextMediumBlueMandatoryField" name="dkea_ftip" id="dkea_ftip" size="60" maxlength="64" value='${model.record.dkea_ftip}'>
							</td>
						</tr>
						<tr>
							<td class="text14" title="dkea_us">FTP-brukerid</td>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="dkea_us" id="dkea_us" size="11" maxlength="10" value='${model.record.dkea_us}'>
							</td>
							<td class="text14" title="dkea_pw">FTP-passord</td>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="dkea_pw" id="dkea_pw" size="11" maxlength="10" value='${model.record.dkea_pw}'>
							</td>
							<td class="text14" title="dkea_prtf">FTP Skriverfil</td>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="dkea_prtf" id="dkea_prtf" size="17" maxlength="16" value='${model.record.dkea_prtf}'>
							</td>
						</tr>
						<tr>	
							<td class="text14" title="dkea_0035">Testindikator</td>
							<td class="text14">
								<select name="dkea_0035" id="dkea_0035" class="inputTextMediumBlue">
				  					<option value="1"<c:if test="${ model.record.dkea_0035 == '1'}"> selected </c:if> >Test</option>
				  					<option value=""<c:if test="${ empty model.record.dkea_0035}"> selected </c:if> >Prod</option>
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
						<table width="100%" cellspacing="0" border="0" align="left">
						<tr>
					            <td >		
					 				<%-- Ansvarlig --%>
					 				<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
								 		<tr height="15">
								 			<td class="text14White">
												&nbsp;<img onMouseOver="showPop('2_info');" onMouseOut="hidePop('2_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
									 			<b>&nbsp;</b>Klarereren / Repræsentant&nbsp;<img valign="bottom" src="resources/images/update.gif" border="0" alt="edit">
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute;top:2px; width:250px;" id="2_info" class="popupWithInputText text11"  >
									           		<b>Klarereren / Repræsentant</b>
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
									 		<td width="100%">
										 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
											 		<tr height="10"><td ></td></tr>
											        
											        <tr>
											        	<td class="text14" align="left" >&nbsp;&nbsp;<span title="dkea_14a">CVR/SE-nr.</span></td>
											            <td class="text14" align="left" >&nbsp;<span title="dkea_14c">Navn&nbsp;</span>
											            	<a tabindex="-1" id="dkea_14c_tina_IdLink">
																<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
															</a>
											            </td>
											        </tr>
											        <tr>
											            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="dkea_14a" id="dkea_14a" size="19" maxlength="18" value="${model.record.dkea_14a}"></td>
											            <td class="text14" align="left"><input type="text" class="inputTextMediumBlue" name="dkea_14c" id="dkea_14c" size="30" maxlength="35" value="${model.record.dkea_14c}"></td>
											        </tr>
											        
											        <tr height="4"><td>&nbsp;</td></tr>
											        <tr>
											            <td class="text14" align="left" >&nbsp;<span title="dkea_14d">Adresse</span></td>
											            <td class="text14" align="left" >&nbsp;<span title="dkea_14e">Postnr.</span></td>
											        </tr>
											        <tr>
											            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkea_14d" id="dkea_14d" size="30" maxlength="35" value="${model.record.dkea_14d}"></td>
											            <td align="left"><input type="text" class="inputTextMediumBlue" name="dkea_14e" id="dkea_14e" size="10" maxlength="9" value="${model.record.dkea_14e}"></td> 
												            		
											        </tr>
											        <tr>
											        		<td>
												        		<table>
												        		<tr>
												            		<td class="text14" align="left" >&nbsp;<span title="dkea_14f">By</span></td>
												            		<td align="left">&nbsp;</td>
												            	</tr>
												        		<tr>
												            		<td align="left">
												       				<input type="text" class="inputTextMediumBlue" name="dkea_14f" id="dkea_14f" size="30" maxlength="35" value="${model.record.dkea_14f}">
											            			</td> 
												            		<td align="left">&nbsp;</td>
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

			</table>
	 		</form>
	 		
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

