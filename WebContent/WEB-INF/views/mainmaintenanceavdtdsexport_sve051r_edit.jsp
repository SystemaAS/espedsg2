<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavdtdsexport_sve051r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
						<a id="alinkMainMaintAvdTdsSve051" onClick="setBlockUI(this);" href="mainmaintenanceavdtdsexport_sve051r.do?id=${model.id}">
                  			<font class="tabLink">&nbsp;<spring:message code="systema.tds.export.label"/></font>&nbsp;
						</a>
					</td>
	                    
					<c:choose>
						<c:when test="${not empty model.updateId}">
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="16%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Avd/Ärende</font>&nbsp;
								<font class="text11MediumBlue">(${model.avd} / ${model.opd})</font>
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
			<form action="mainmaintenanceavdtdsexport_sve051r_edit.do?id=${model.id}" name="formRecord" id="formRecord" method="POST" >
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
					    	    		<input type="hidden" name="svea_syav" id="svea_syav" value="${model.avd}">
					    	    		<input type="hidden" name="svea_syop" id="svea_syop" value="${model.opd}">
					    	    		<input type="hidden" name="avd" id="avd" value="${model.avd}">
					    	    		<input type="hidden" name="opd" id="opd" value="${model.opd}">
					    	    		
					    	    		<td colspan="4" class="text14MediumBlue" title="avdReadOnly">Avd&nbsp;&nbsp;&nbsp;
											<input readonly type="text" class="inputTextReadOnly" id="avdReadOnly" name="avdReadOnly" size="4" value="${model.avd}">
										</td>
										<td class="text14" title="opdReadOnly">&nbsp;&nbsp;&nbsp;Ärendenr.</td>
										<td class="text14" >
											<input readonly type="text" class="inputTextReadOnly" name="opdReadOnly" id="opdReadOnly" size="10" value='${model.record.svea_syop}'>
										</td>
					    	    		
					    	    	</c:when>
					    	    	<c:otherwise>
					    	    		<td class="text14" title="svea_syav"><font class="text14RedBold" >*</font>Avdelning
					    	    			<a tabindex="-1" id="avdIdLink">
												<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search" >
											</a>
					    	    		</td>
										<td class="text14" >
										<select name="svea_syav" id="svea_syav" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField">
						  					<option value="">-Välj-</option>
						  					<c:forEach var="record" items="${model.avdGeneralList}" >
						 				  		<option value="${record.koaavd}"<c:if test="${ model.record.svea_syav == record.koaavd}"> selected </c:if> >${record.koaavd}</option>
											  </c:forEach>  
											</select>
										</td>
										
										<td class="text14" title="svea_syop">&nbsp;&nbsp;&nbsp;<font class="text14RedBold" >*</font>Ärendenr.</td>
										<td class="text14" >
											<input onKeyPress="return numberKey(event)" type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svea_syop" id="svea_syop" size="10" maxlength="7" value='${model.record.svea_syop}'>
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
						<tr><td class="text14"></td></tr>
						<tr height="5"><td></td></tr>
			    	    
						<tr>
							<td class="text14" title="svea_omeo"><font class="text14RedBold" >*</font>EORI</td>
							<td class="text14" title="svea_omty"><font class="text14RedBold" >*</font>Typ</td>
							<td class="text14" title="svea_omha"><font class="text14RedBold" >*</font>Handläggare</td>
							<td class="text14" title="svea_omtl"><font class="text14RedBold" >*</font>Telefon</td>
							<td class="text14" title="svea_0035"><font class="text14RedBold" >*</font>Testindikator</td>
							
						</tr>
						<tr>
							<td class="text14">
								<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svea_omeo" id="svea_omeo" size="18" maxlength="17" value='${model.record.svea_omeo}'>
							</td>
							<td class="text14">
								<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svea_omty" id="svea_omty" size="2" maxlength="1" value='${model.record.svea_omty}'>
							</td>
							<td class="text14">
								<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svea_omha" id="svea_omha" size="36" maxlength="35" value='${model.record.svea_omha}'>
							</td>
							<td class="text14">
								<input type="text" required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="svea_omtl" id="svea_omtl" size="26" maxlength="25" value='${model.record.svea_omtl}'>
							</td>
							<td class="text14">
								<select name="svea_0035" id="svea_0035" class="inputTextMediumBlueMandatoryField">
				  					<option value="1"<c:if test="${ model.record.svea_0035 == '1'}"> selected </c:if> >Test</option>
				  					<option value=""<c:if test="${ empty model.record.svea_0035 }"> selected </c:if> >Prod</option>
				  					
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
						<%-- --------------- --%>
						<%-- LEFT SIDE CELL --%>
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
					 				    			<input tabindex=-1 class="inputFormSubmit" type="submit" name="submit" id="submit" value='Spara' />
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

