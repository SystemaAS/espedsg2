<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancesign_syfa60r_edit_params.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
	<style type = "text/css">
	.ui-datepicker { font-size:9pt;}
	</style>


<table width="100%" class="text12" cellspacing="0" border="0" cellpadding="0">
	<tr height="15"><td>&nbsp;</td></tr>
	<tr>
		<td>
		<%-- tab container component --%>
		<table width="100%" class="text12" cellspacing="0" border="0" cellpadding="0">
			<tr height="2"><td></td></tr>
				<tr height="25"> 
					<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
						<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.main.maintenance.label"/></font>
						<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
						</a>
					</td>
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
					<td width="15%" valign="bottom" class="tabDisabled" align="center">
						<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancesign_syfa60r.do?id=KODTSF">
							<font class="tabDisabledLink">&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancesyfa60.signatures"/></font>&nbsp;
							<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="sign general list">
						</a>
					</td>
					
					<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabLink">&nbsp;</font></td>
					<td width="20%" valign="bottom" class="tab" align="center">
						<font class="tabLink">&nbsp;Parametere</font>&nbsp;(${model.syuser})						
						<%-- <img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">  --%>
					</td>
					 
					<td width="60%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<%-- space separator --%>
	 		<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
			<%-- list component --%>
			<tr>
				<td width="3%">&nbsp;</td>
				<td >
				<table id="containerdatatableTable" width="75%" cellspacing="1" border="0" align="left">
			    	    <tr>
						<td width="75%" class="text12">
						<table id="mainList" class="display compact cell-border" >
							<thead>
							<tr>
							    <th align="center" class="tableHeaderField"><spring:message code="systema.edit"/></th>
								<th class="tableHeaderField"><spring:message code="systema.main.maintenance.mainmaintenancecundf.syparf.sypaid"/></th>
								<th class="tableHeaderField"><spring:message code="systema.main.maintenance.mainmaintenancecundf.syparf.sypaidDesc"/></th>
								<th class="tableHeaderField"><spring:message code="systema.main.maintenance.mainmaintenancecundf.syparf.sysort"/></th>
			                    <th class="tableHeaderField"><spring:message code="systema.main.maintenance.mainmaintenancecundf.syparf.syvrdn"/></th>
								<th class="tableHeaderField"><spring:message code="systema.main.maintenance.mainmaintenancecundf.syparf.syvrda"/></th>
								<th class="tableHeaderField"><spring:message code="systema.delete"/></th>
			                </tr>  
			                </thead> 
			                <tbody >  
				            	<c:forEach var="record" items="${model.list}" varStatus="counter">   
					               <tr class="tableRow" height="20" >
					               <td id="recordUpdate_${record.syuser}_${record.syrecn}" onClick="getRecord(this);" align="center" width="5%" class="tableCellFirst" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
			               				<img src="resources/images/update.gif" border="0" alt="edit">
					               </td>
					               <td width="5%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text14">&nbsp;${record.sypaid}&nbsp;</font></td>
					               <td width="25%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 1px;border-color:#FAEBD7;"><font class="text14">&nbsp;${record.sypaidDesc}&nbsp;</font></td>
					               <td width="5%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.sysort}&nbsp;</font></td>
					               <td width="15%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.syvrdn}&nbsp;</font></td>
					               <td width="40%" class="tableCell" style="border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;" ><font class="text14">&nbsp;${record.syvrda}&nbsp;</font></td>
			                       <td align="center" width="5%" class="tableCell" style="cursor:pointer; border-style: solid;border-width: 0px 1px 1px 0px;border-color:#FAEBD7;">
			               				<a onclick="javascript:return confirm('<spring:message code="systema.delete.confirm"/>')" tabindex=-1 href="mainmaintenancesign_syfa60r_edit_params.do?action=doDelete&syuser=${record.syuser}&syrecn=${record.syrecn}&sykunr=0">
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
				<td width="3%">&nbsp;</td>
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
				<td width="3%">&nbsp;</td>
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
	 	    
 	   	 		 	<tr id="details">
 	   	 		 		<td width="3%">&nbsp;</td>
	   	 		 		<td>
						<form action="mainmaintenancesign_syfa60r_edit_params.do" name="formRecord" id="formRecord" method="POST" >
							<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
							<input type="hidden" name="updateId" id="updateId" value="${model.updateId}"> 
							<input type="hidden" name="action" id=action value="doUpdate">
							<input type="hidden" name="syuser" id="syuser" value='${model.syuser}'>
							<input type="hidden" name="syrecn" id="syrecn" value='${model.record.syrecn}'>
							<input type="hidden" name="sykunr" id="sykunr" value='0'>
							<input type="hidden" name="syavd" id="syavd" value='0'>
							
							<table id="paramsDetails" width="80%" cellspacing="0" border="0" align="left">
								<spring:hasBindErrors name="record"> 
									<tr>
										<td>
											<table align="left" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td>
														<ul class="isa_error text14">
															<c:forEach var="error" items="${errors.allErrors}">
																<li><spring:message code="${error.code}"
																		text="${error.defaultMessage}" />&nbsp;&nbsp;</li>
															</c:forEach>
														</ul>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</spring:hasBindErrors>
								<c:if test="${not empty model.errorMessage}">
								<tr>
									<td >
							           	<table align="left" border="0" cellspacing="0" cellpadding="0">
									 		<tr>
									 			<td >
									 				<ul class="isa_error text14" >
							                                  <li>${model.errorMessage}</li>                                    
							                              </ul>
									 			</td>
											</tr>
										</table>
									</td>
								</tr>
								</c:if>
							
								<tr>
									<td>
										<button name="newRecordButton" id="newRecordButton" class="inputFormSubmitStd" type="button">
											<spring:message code="systema.new"/>
										</button>&nbsp;&nbsp;
								</tr>
								<tr height="2">
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>
										<table class="formFrameHeaderPeachWithBorder" width="80%" 	cellspacing="0" border="0" align="left">
											<tr>
												<td class="text14Bold">&nbsp;
													<spring:message code="systema.main.maintenance.mainmaintenancecundf.syparf.param"/>
												</td>
											</tr>
										</table>			

										<table class="formFramePeachGrayRoundBottom"  width="80%" cellspacing="0" border="0" align="left">
											<tr> 
												<td width="50%" valign="top">
													<table>
														<tr>
															<td class="text14" title="sypaid">&nbsp;<font class="text14RedBold" >*</font>
																<spring:message code="systema.main.maintenance.mainmaintenancecundf.syparf.sypaid"/>:&nbsp;&nbsp;&nbsp;
															</td>
															<td><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="sypaid" id="sypaid" size="10" maxlength="5" value='${model.record.sypaid}'></td>
															<td class="text14" title="sypaid">
																<a tabindex="-1" id="sypaidIdLink">
																	<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
																</a>
															</td>
														</tr>

														<tr>
															<td class="text14" title="sysort">&nbsp;
																<spring:message code="systema.main.maintenance.mainmaintenancecundf.syparf.sysort"/>
															</td>
															<td><input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue"  name="sysort" id="sysort" size="10" maxlength="3" value='${model.record.sysort}'>
															</td>
														</tr>

													</table>
												</td>
												<td width="50%" valign="top">
													<table >
														<tr>
															<td colspan="2" class="text14" title="sypaidDesc">&nbsp;&nbsp;<spring:message code="systema.main.maintenance.mainmaintenancecundf.syparf.sypaidDesc"/>:&nbsp;&nbsp;</td>
															<td><input type="text" readonly class="inputTextReadOnly" name="sypaidDesc" id="sypaidDesc" size="51" maxlength="50" value='${model.record.sypaidDesc}'></td>
														</tr>	
														<tr>
															<td colspan="2" class="text14" title="syvrdn">&nbsp;<font class="text14RedBold" >*</font>
																<spring:message code="systema.main.maintenance.mainmaintenancecundf.syparf.syvrdn"/>:
															</td>
															<td><input type="text" onKeyPress="return numberKey(event)" required oninvalid="setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField"  name="syvrdn" id="syvrdn" size="16" maxlength="15" value='${model.record.syvrdn}'>
															</td>
														<tr>
															<td colspan="2" class="text14" title="syvrda">&nbsp;<font class="text14RedBold" >*</font>
																<spring:message code="systema.main.maintenance.mainmaintenancecundf.syparf.syvrda"/>:
															</td>
															<td><input type="text" required oninvalid="setCustomValidity('Obligatoriskt')" oninput="setCustomValidity('')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField"  name="syvrda" id="syvrda" size="51" maxlength="50" value='${model.record.syvrda}'>
															</td>
														</tr>
														<tr><td>&nbsp;</td></tr>
														<tr> 
															<td colspan="4" align="right">
																<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.save"/>'/>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								
							</table>
		 	    		</form>
	   	 		 		</td>
	   	 		 	</tr>

	    	    <tr height="20"><td></td></tr>
				</table>
			</td>
		</tr>

 		</table>


<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

