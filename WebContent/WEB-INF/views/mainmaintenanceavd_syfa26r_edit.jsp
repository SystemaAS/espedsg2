<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavd_syfa26r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<td width="12%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
						<font class="tabDisabledLink">&nbsp;Vedlikehold</font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
							<font class="tabDisabledLink">&nbsp;Avdelinger</font>&nbsp;						
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdSyfa14r" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa14r.do">
							<font class="tabDisabledLink">&nbsp;Gen. avd.</font>&nbsp;
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvd" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa14r_edit.do?avd=${model.avd}&updateId=${model.avd}">
							<font class="tabDisabledLink">&nbsp;Avd.</font>&nbsp;
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintAvdFasteData" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa28r_edit.do?avd=${model.avd}&updateId=${model.avd}&avdnavn=${model.avdnavn}">
							<font class="tabDisabledLink">&nbsp;Faste data</font>&nbsp;
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkAvdHodeDok" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa63r.do?avd=${model.avd}&avdnavn=${model.avdnavn}">
							<font class="tabDisabledLink">&nbsp;H.på dok.</font>
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkAvdListHode" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa68r.do?avd=${model.avd}&avdnavn=${model.avdnavn}">
							<font class="tabDisabledLink">&nbsp;Listehode</font>&nbsp;
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tab" align="center">
						<font class="tabLink">&nbsp;Oppnr og tur</font>&nbsp;
						<font class="text11MediumBlue">(${model.avd})</font>
					</td>
					<td width="1%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</tr>
		</table>
		</td>
	</tr>
	
	
	
	
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    <tr >
	 	    	<td width="5%">&nbsp;</td>
				<td colspan="4" class="text14MediumBlue" title=".">Avd.navn&nbsp;&nbsp;&nbsp;<input readonly class="inputTextReadOnly" type="text" size="40" value="${model.avdnavn}">
				</td>
			</tr>
			<tr height="5"><td >&nbsp;</td></tr>
	 	    
	 	    <%-- list component --%>
			<tr>
				<td width="5%">&nbsp;</td>
				<td width="100%">
				<table id="containerdatatableTable" width="95%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td class="text11">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
							    <th width="2%" class="tableHeaderFieldFirst" align="center" >Endre</th>                                                            
								<th width="2%" class="tableHeaderFieldFirst" align="center" >Avd</th>                                                            
								<th class="tableHeaderField" align="left" >Oppd.nr</th>
								<th class="tableHeaderField" align="left" >Turnr.</th>
			                    <th class="tableHeaderField" align="center" >Minim.tur</th>
			                    <th class="tableHeaderField" align="center" >Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <td class="tableCellFirst" width="2%" id="recordUpdate_${record.teavd}" onClick="getRecord(this);" align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
	               					<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">${record.teavd}</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.teopdn}&nbsp;</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.teturn}&nbsp;</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.tetmin}&nbsp;</td>
				               
				               <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		                       		<a class="aLinkRemove" id="aLinkRemove${counter.count}" onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="mainmaintenanceavd_syfa26r_edit.do?action=doDelete&teavd=${record.teavd}&avdnavn=${model.avdnavn}">
					               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
					               	</a>
				               </td>
				            </tr> 
				            </c:forEach>
				            </tbody>
			            </table>
					</td>	
					</tr>
				</table>
				</td>
			</tr>		    
	 	    <tr height="20"><td>&nbsp;</td></tr>
			
			<%-- ----------------- --%>
			<%-- Validation errors --%>
			<%-- ----------------- --%>
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
				<form action="mainmaintenanceavd_syfa26r_edit.do" name="formRecord" id="formRecord" method="POST" onSubmit="enableDisabledFields();">
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
					<input type="hidden" name="action" id=action value="doUpdate">
					<input type="hidden" name="avdnavn" id=avdnavn value="${model.avdnavn}">
					
				<table cellspacing="1" border="0" align="left">
						
						<tr height="20"><td></td></tr>
						<tr>
							<td class="text14" ><font class="text14RedBold" >*</font><span title="teavd">&nbsp;Avd.</span></td>
							<td class="text14" ><font class="text14RedBold" >*</font><span title="teopdn">&nbsp;Oppnr.</span></td>
							<td class="text14" ><font class="text14RedBold" >*</font><span title="teturn">&nbsp;Turnr.</span></td>
							<td class="text14" ><span title="tetmin">&nbsp;Minim.turnr.</span></td>
							
						</tr>		
 	    				<tr>
							<td class="text14" >
								<input readonly type="text" class="inputTextReadOnly" name="teavd" id="teavd" size="5" maxlength="4" value='${model.avd}'>
							</td>
							<td class="text14" >
								<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="teopdn" id="teopdn" size="10" maxlength="7" value='${model.record.teopdn}'>
							</td>
							<td class="text14" >
								<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="teturn" id="teturn" size="10" maxlength="8" value='${model.record.teturn}'>
							</td>
							<td class="text14" >
								<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="tetmin" id="tetmin" size="10" maxlength="8" value='${model.record.tetmin}'>
							</td>
							<td class="text14" >
		    	    			<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre' onClick="setBlockUI(this);"/>
							</td>
		    	    	</tr>
		    	    	
		    	    	
						<tr height="20"><td></td></tr>
				</table>
				</form>
				</td>
			</tr>
					
	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

