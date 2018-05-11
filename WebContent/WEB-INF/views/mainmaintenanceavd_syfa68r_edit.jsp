<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavd_syfa68r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<td width="12%" valign="bottom" class="tab" align="center">
						<font class="tabLink">&nbsp;Listehode</font>&nbsp;
						<font class="text11MediumBlue">(${model.avd})</font>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkAvdOppdTur" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa26r.do?avd=${model.avd}&avdnavn=${model.avdnavn}">
							<font class="tabDisabledLink">&nbsp;Oppnr og tur</font>&nbsp;
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
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
								<th class="tableHeaderField" align="left" >Fak</th>
								<th class="tableHeaderField" align="left" >Las</th>
			                    <th class="tableHeaderField" align="center" >God</th>
			                    <th class="tableHeaderField" align="center" >Bou</th>
			                    <th class="tableHeaderField" align="center" >KK</th>
			                    <th class="tableHeaderField" align="center" >Los</th>
			                    <th class="tableHeaderField" align="center" >Man</th>
			                    <th class="tableHeaderField" align="center" >A4 (L/S)</th>
			                    <th class="tableHeaderField" align="center" >421</th>
			                    <th class="tableHeaderField" align="center" >God (L/S)</th>
			                    <th class="tableHeaderField" align="center" >422</th>
			                    <th class="tableHeaderField" align="center" >Los (L/S)</th>
			                    <th class="tableHeaderField" align="center" >423</th>
			                    			        
			                    <th class="tableHeaderField" align="center" >Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <%--
				               <td class="tableCellFirst" width="2%" id="recordUpdate_${record.kohavd}" onClick="getRecord(this);" align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
	               					<img src="resources/images/update.gif" border="0" alt="edit">
				               </td>
				                --%>
				               <td class="tableCellFirst" width="2%" id="recordUpdate_${record.kohavd}" align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
	    
				               </td>
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">${record.kohavd}</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.kohfak}&nbsp;</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.kohlas}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.kohgod}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.kohbou}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.kohkk}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.kohlos}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.kohman}&nbsp;</td>
		                       
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.kohls1}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.koh421}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.kohls2}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.koh422}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.kohls3}&nbsp;</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.koh423}&nbsp;</td>
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		                       		<a class="aLinkRemove" id="aLinkRemove${counter.count}" onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="mainmaintenanceavd_syfa68r_edit.do?action=doDelete&kohavd=${record.kohavd}&avdnavn=${model.avdnavn}">
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
				<form action="mainmaintenanceavd_syfa68r_edit.do" name="formRecord" id="formRecord" method="POST" onSubmit="enableDisabledFields();">
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
					<input type="hidden" name="action" id=action value="doUpdate">
					<input type="hidden" name="avdnavn" id=avdnavn value="${model.avdnavn}">
					
				<table width="95%" cellspacing="1" border="0" align="left">
						
						<tr height="20"><td></td></tr>
						<tr>
							<td class="text14" ><font class="text14RedBold" >*</font><span title="koaavd">Avd.&nbsp;</span></td>
							<td class="text14" align="center"><span title="kohfak">Faktura</span></td>
							<td class="text14" align="center"><span title="kohlas">Lasteliste</span></td>
							<td class="text14" align="center"><span title="kohgod">Godsliste</span></td>
							<td class="text14" align="center"><span title="kohbou">Bourderau</span></td>
							<td class="text14" align="center"><span title="kohkk">Kjørekvit.</span></td>
							<td class="text14" align="center"><span title="kohlos">Losselista</span></td>
							<td class="text14" align="center"><span title="kohman">Manifest</span></td>
							<td class="text14" align="center"><span title="kohls1">A4 L/S</span></td>
							<td class="text14" align="center"><span title="koh421">421</span></td>
							<td class="text14" align="center"><span title="kohls2">Godsliste</span></td>
							<td class="text14" align="center"><span title="koh422">422</span></td>
							<td class="text14" align="center"><span title="kohls3">Losselista</span></td>
							<td class="text14" align="center"><span title="koh423">423</span></td>
							
						</tr>		
 	    				<tr>
							<td class="text14" >
								<input readonly type="text" class="inputTextReadOnly" name="kohavd" id="kohavd" size="5" maxlength="4" value='${model.avd}'>
							</td>
							<td class="text14" >
								<select name="kohfak" id="kohfak" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.kohfak == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.kohfak == 'N'}"> selected </c:if> >Nei</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="kohlas" id="kohlas" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.kohlas == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.kohlas == 'N'}"> selected </c:if> >Nei</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="kohgod" id="kohgod" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.kohgod == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.kohgod == 'N'}"> selected </c:if> >Nei</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="kohbou" id="kohbou" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.kohbou == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.kohbou == 'N'}"> selected </c:if> >Nei</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="kohkk" id="kohkk" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.kohkk == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.kohkk == 'N'}"> selected </c:if> >Nei</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="kohlos" id="kohlos" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.kohlos == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.kohlos == 'N'}"> selected </c:if> >Nei</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="kohman" id="kohman" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.kohman == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.kohman == 'N'}"> selected </c:if> >Nei</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="kohls1" id="kohls1" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="L"<c:if test="${ model.record.kohls1 == 'L'}"> selected </c:if> >L</option>
				  					<option value="S"<c:if test="${ model.record.kohls1 == 'S'}"> selected </c:if> >S</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="koh421" id="koh421" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.koh421 == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.koh421 == 'N'}"> selected </c:if> >Nei</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="kohls2" id="kohls2" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="L"<c:if test="${ model.record.kohls2 == 'L'}"> selected </c:if> >L</option>
				  					<option value="S"<c:if test="${ model.record.kohls2 == 'S'}"> selected </c:if> >S</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="koh422" id="koh422" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.koh422 == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.koh422 == 'N'}"> selected </c:if> >Nei</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="kohls3" id="kohls3" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="L"<c:if test="${ model.record.kohls3 == 'L'}"> selected </c:if> >L</option>
				  					<option value="S"<c:if test="${ model.record.kohls3 == 'S'}"> selected </c:if> >S</option>
				  					
							  	</select>
							</td>
							<td class="text14" >
								<select name="koh423" id="koh423" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="J"<c:if test="${ model.record.koh423 == 'J'}"> selected </c:if> >Ja</option>
				  					<option value="N"<c:if test="${ model.record.koh423 == 'N'}"> selected </c:if> >Nei</option>
				  					
							  	</select>
							</td>	
		    	    	</tr>
		    	    	
		    	    	<tr>
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

