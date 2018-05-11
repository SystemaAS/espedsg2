<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavd_syfa63r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<td width="12%" valign="bottom" class="tab" align="center">
						<font class="tabLink">&nbsp;H.på dok.</font>
						<font class="text11MediumBlue">(${model.avd})</font>
					</td>
					<%--
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkAvdListHode" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa68r.do?avd=${model.avd}&avdnavn=${model.avdnavn}">
							<font class="tabDisabledLink">&nbsp;Listehode</font>&nbsp;
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="12%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkAvdOppdTur" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa26r.do?avd=${model.avd}&avdnavn=${model.avdnavn}">
							<font class="tabDisabledLink">&nbsp;Oppnr og tur</font>&nbsp;
							<font class="text11MediumBlue">(${model.avd})</font>
						</a>
					</td>
					 --%>
					<td width="24%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
								<th width="25%" class="tableHeaderField" align="left" >Navn</th>
								<th class="tableHeaderField" align="left" >Språk</th>
			                    <th class="tableHeaderField" align="center" >FriTxt Kode</th>
			                    <th class="tableHeaderField" align="center" >Slett</th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            <c:forEach var="record" items="${model.list}" varStatus="counter">   
				               <tr class="tableRow" height="20" >
				               <c:choose>
				               <c:when test="${record.hoavd != 'null' && not empty record.hoavd}">
					               <td class="tableCellFirst" id="recordUpdate_${record.koaavd}_${record.hoavd}_${record.honet}" onClick="getRecord(this);" align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
			               				<img src="resources/images/update.gif" border="0" alt="edit">
					               </td>
				               </c:when>
				               <c:otherwise>
				               		<td class="tableCellFirst" width="2%" id="nonrecordUpdate_" align="center" width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
			               				<img src="resources/images/lock.gif" border="0" alt="edit">
					               </td>
				               </c:otherwise>
				               </c:choose>
				               
				               <td width="2%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" align="center">${record.koaavd}</td>
				               <td width="25%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;">&nbsp;${record.koanvn}&nbsp;</td>
				               <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;
				               		<c:choose>
					               		<c:when test="">
					               		</c:when>
					               		<c:otherwise>
					               		</c:otherwise>
				               		</c:choose>
				               		${record.honet}&nbsp;
				               	</td>
		                       <td class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" >&nbsp;${record.hostfr}&nbsp;</td>
		                       <td align="center" width="2%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
		                       		<c:if test="${record.hoavd != 'null' && not empty record.hoavd}">
		                       			<a class="aLinkRemove" id="aLinkRemove${counter.count}" onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="mainmaintenanceavd_syfa63r_edit.do?action=doDelete&koaavd=${record.koaavd}&hoavd=${record.hoavd}&honet=${record.honet}&avdnavn=${model.avdnavn}">
						               		<img valign="bottom" src="resources/images/delete.gif" border="0" width="15px" height="15px" alt="remove">
						               	</a>
					               	</c:if>
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
				<form action="mainmaintenanceavd_syfa63r_edit.do" name="formRecord" id="formRecord" method="POST" onSubmit="enableDisabledFields();">
					<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
					<input type="hidden" name="updateId" id=updateId value="${model.updateId}"> <%-- this value is set in AJAX in order to know if the SAVE = ADD or UPDATE --%>
					<input type="hidden" name="action" id=action value="doUpdate">
					<input type="hidden" name="hoavd" id=hoavd value="${model.avd}">
					<input type="hidden" name="avdnavn" id=avdnavn value="${model.avdnavn}">
					
				<table width="95%" cellspacing="1" border="0" align="left">
						<tr height="5"><td></td></tr>
						<tr >
							<td><button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button" >Lage ny</button></td>
						</tr>
						<tr height="20"><td></td></tr>
						
						<tr>
							<td class="text14" ><font class="text14RedBold" >*</font><span title="koaavd">Avd.&nbsp;</span>
								<input readonly type="text" class="inputTextReadOnly" name="koaavd" id="koaavd" size="5" maxlength="4" value='${model.avd}'>
							
								&nbsp;&nbsp;<font class="text14RedBold" >*</font><span title="honet">Språk&nbsp;</span>
								<select name="honet" id="honet" class="inputTextMediumBlueMandatoryField">
				  					<option value="">-velg-</option>
				  					<option value="N"<c:if test="${ model.record.honet == 'N'}"> selected </c:if> >Norsk</option>
				  					<option value="E"<c:if test="${ model.record.honet == 'E'}"> selected </c:if> >Engelsk</option>
				  					<option value="T"<c:if test="${ model.record.honet == 'T'}"> selected </c:if> >Tysk</option>
							  	</select>
								
 	    						&nbsp;&nbsp;&nbsp;<span title="hostfr">Std.faktura fritekstkode</span>
 	    						<input type="text" class="inputTextMediumBlue" name="hostfr" id="hostfr" size="3" maxlength="2" value="${model.record.hostfr}" />
 	    						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre' onClick="setBlockUI(this);"/>
						
 	    					</td>
		    	    	<tr>
		    	    	
						<tr height="10"><td></td></tr>
						<tr>
	 	    			<td class="text14" title="hoht1-hoht7">Topptekster</td>
	 	    			</tr>
			    	    <tr>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="hoht1" id="hoht1" size="85" maxlength="79" value="${model.record.hoht1}">
							</td>
						</tr>
						<tr>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="hoht2" id="hoht2" size="85" maxlength="79" value="${model.record.hoht2}">
							</td>
						</tr>
						<tr>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="hoht3" id="hoht3" size="85" maxlength="79" value="${model.record.hoht3}">
							</td>
						</tr>
						<tr>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="hoht4" id="hoht4" size="85" maxlength="79" value="${model.record.hoht4}">
							</td>
						</tr>
						<tr>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="hoht5" id="hoht5" size="85" maxlength="79" value="${model.record.hoht5}">
							</td>
						</tr>
						<tr>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="hoht6" id="hoht6" size="85" maxlength="79" value="${model.record.hoht6}">
							</td>
						</tr>
						<tr>
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="hoht7" id="hoht7" size="85" maxlength="79" value="${model.record.hoht7}">
							</td>
						</tr>
						
						<tr height="5"><td></td></tr>
						<tr>
		 	    			<td class="text14" title="hobt1-hobt3">Bunntekst på faktura</td>
		 	    		</tr>
		 	    		<tr>		
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="hobt1" id="hobt1" size="85" maxlength="79" value="${model.record.hobt1}">
							</td>
						</tr>
						<tr>	
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="hobt2" id="hobt2" size="85" maxlength="79" value="${model.record.hobt2}">
							</td>
						</tr>
						<tr>	
							<td class="text14">
								<input type="text" class="inputTextMediumBlue" name="hobt3" id="hobt3" size="85" maxlength="79" value="${model.record.hobt3}">
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

