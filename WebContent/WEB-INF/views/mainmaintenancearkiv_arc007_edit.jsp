<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenancearkiv_arc007_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<c:choose>
						<c:when test="${not empty model.updateId}">
							<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
								<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
								<font class="tabDisabledLink">&nbsp;
									<spring:message code="systema.main.maintenance.label"/>
								</font>
								<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintArkivGate" onClick="setBlockUI(this);" href="mainmaintenancearkivgate.do?id=${model.dbTable}">
									<font class="tabDisabledLink">&nbsp;
										<spring:message code="systema.main.maintenance.arkiv"/>
									</font>&nbsp;						
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="arkiv gate list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintArkivArc007" onClick="setBlockUI(this);" href="mainmaintenancearkiv_arc007.do">
									<font class="tabDisabledLink">&nbsp;
										<spring:message code="systema.main.maintenance.arkiv.documents"/>	
									</font>&nbsp;
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="arkiv general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;
									<spring:message code="systema.main.maintenance.arkiv.documents"/>	
								</font>&nbsp;
								<font class="text14MediumBlue">(${model.record.artype})</font>
							</td>
							
							<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							
						</c:when>
						<c:otherwise>
							<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
								<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
								<font class="tabDisabledLink">&nbsp;
									<spring:message code="systema.main.maintenance.label"/>
								</font>
								<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintArkivGate" onClick="setBlockUI(this);" href="mainmaintenancearkivgate.do?id=${model.dbTable}">
									<font class="tabDisabledLink">&nbsp;
										<spring:message code="systema.main.maintenance.arkiv"/>
									</font>&nbsp;						
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintArkivArc007" onClick="setBlockUI(this);" href="mainmaintenancearkiv_arc007.do">
									<font class="tabDisabledLink">&nbsp;
										<spring:message code="systema.main.maintenance.arkiv.documents"/>	
									</font>&nbsp;
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="15%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;
									<spring:message code="systema.new"/>
								</font>&nbsp;
								<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
							</td>
							
							<td width="40%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						</c:otherwise>
					</c:choose>
				</tr>
		</table>
		</td>
	</tr>
	
	<tr>
		<td>
		<%-- space separator --%>
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
		                                  <li>${model.errorMessage}</li>                                    
		                              </ul>
				 			</td>
						</tr>
					</table>
				</td>
			</tr>
			</c:if>
	 	    
	 	    <tr>
				<td width="5%">&nbsp;</td>
				<td width="95%">
					<form action="mainmaintenancearkiv_arc007_edit.do" name="formRecord" id="formRecord" method="POST" >
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="updateId" id=updateId value="${model.updateId}">
						<input type="hidden" name="action" id=action value="${model.action}">
						<table  width="95%" cellspacing="1" border="0" align="left">
							<tr>
								<td> <!-- First row -->
									<table>
										<tr>
											<td class="text14" title="ARTYPE">&nbsp;<font class="text14RedBold" >*</font>
												<spring:message code="systema.main.maintenance.mainmaintenancearc007.artype"/>:	
											</td>
											<td >
												<c:choose>
													<c:when test="${not empty model.updateId}">
														<input readonly type="text" class="inputTextReadOnly" name="artype" id="artype" size="3" maxlength="2" value='${model.record.artype}'>
													</c:when>
													<c:otherwise>
														<input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueUPPERCASEMandatoryField" name="artype" id="artype" size="3" maxlength="2" value='${model.record.artype}'>
													</c:otherwise>
												</c:choose>
											</td>
											<td class="text14" title="ARTXT">&nbsp;<font class="text14RedBold" >*</font>
												<spring:message code="systema.main.maintenance.mainmaintenancearc007.artxt"/>:	
											</td>
											<td ><input type="text" required oninvalid="this.setCustomValidity('Obligatoriskt')" onchange="setCustomValidity('')" class="inputTextMediumBlueMandatoryField"  name="artxt" id="artxt" size="20" maxlength="15" value='${model.record.artxt}'></td>
											<td class="text14" title="ARKJN">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancearc007.arkjn"/>:	
											</td>
											<td class="text14" >
												<select name="arkjn" id="arkjn" class="inputTextMediumBlue">
								  					<option value="">-<spring:message code="systema.choose"/>-</option>
								  					<option value="J"<c:if test="${ model.record.arkjn == 'J'}"> selected </c:if> ><spring:message code="systema.yes"/></option>
								  					<option value="N"<c:if test="${ model.record.arkjn == 'N'}"> selected </c:if> ><spring:message code="systema.no"/></option>
								  					
											  	</select>
											</td>
											<td class="text14" title="ARKSND">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancearc007.arksnd"/>:	
											</td>
											<td class="text14" >
												<select name="arksnd" id="arksnd" class="inputTextMediumBlue">
								  					<option value="">-<spring:message code="systema.choose"/>-</option>
								  					<option value="J"<c:if test="${ model.record.arksnd == 'J'}"> selected </c:if> ><spring:message code="systema.yes"/></option>
								  					<option value="N"<c:if test="${ model.record.arksnd == 'N'}"> selected </c:if> ><spring:message code="systema.no"/></option>
								  					
											  	</select>
											</td>
											<td class="text14" title="ARKLAG">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancearc007.arklag"/>:	
											</td>
											<td><input type="text" class="inputTextMediumBlue"  name="arklag" id="arklag" size="2" maxlength="2" value='${model.record.arklag}'>
												<a tabindex="-1" id="arklagIdLink">
													<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
												</a>
											</td>
											<td><input readonly type="text" class="inputTextReadOnly" name="arklagDesc" id="arklagDesc" size="10" maxlength="10" value='${model.record.arklagDesc}'></td>
											<td class="text14" title="ARKDAG">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancearc007.arkdag"/>:	
											</td>
											<td><input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlue"  name="arkdag" id="arkdag" size="5" maxlength="5" value='${model.record.arkdag}'></td>
										</tr>		
									</table>
								</td> <!-- End first row -->
							</tr>
							
							<tr>
								<td>&nbsp; <!-- Second row -->
									<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="center">
										<tr>
											<td class="text14Bold">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancearc007.header.vedlegg"/>
											</td>
											<td align="right">&nbsp;
												<spring:message code="systema.ref"/>
												<a tabindex="-1" id="arkvedIdLink">
													<img style="cursor:pointer;vertical-align:middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
												</a>
												&nbsp;&nbsp;
											</td>	
											
										</tr>
									</table>
									<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
										<tr> 
											<td>
												<table>
													<tr>
														<td class="text14" title="arkved">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.arkved"/>:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved1" id="arkved1" size="2" maxlength="2" value='${model.record.arkved1}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved2" id="arkved2" size="2" maxlength="2" value='${model.record.arkved2}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved3" id="arkved3" size="2" maxlength="2" value='${model.record.arkved3}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved4" id="arkved4" size="2" maxlength="2" value='${model.record.arkved4}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved5" id="arkved5" size="2" maxlength="2" value='${model.record.arkved5}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved6" id="arkved6" size="2" maxlength="2" value='${model.record.arkved6}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved7" id="arkved7" size="2" maxlength="2" value='${model.record.arkved7}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved8" id="arkved8" size="2" maxlength="2" value='${model.record.arkved8}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved9" id="arkved9" size="2" maxlength="2" value='${model.record.arkved9}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved10" id="arkved10" size="2" maxlength="2" value='${model.record.arkved10}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved11" id="arkved11" size="2" maxlength="2" value='${model.record.arkved11}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved12" id="arkved12" size="2" maxlength="2" value='${model.record.arkved12}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved13" id="arkved13" size="2" maxlength="2" value='${model.record.arkved13}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved14" id="arkved14" size="2" maxlength="2" value='${model.record.arkved14}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved15" id="arkved15" size="2" maxlength="2" value='${model.record.arkved15}'></td>
													</tr>
													<tr>
														<td class="text14" title="arkved">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.arkved"/>:
														</td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved16" id="arkved16" size="2" maxlength="2" value='${model.record.arkved16}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved17" id="arkved17" size="2" maxlength="2" value='${model.record.arkved17}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved18" id="arkved18" size="2" maxlength="2" value='${model.record.arkved18}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved19" id="arkved19" size="2" maxlength="2" value='${model.record.arkved19}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved20" id="arkved20" size="2" maxlength="2" value='${model.record.arkved20}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved21" id="arkved21" size="2" maxlength="2" value='${model.record.arkved21}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved22" id="arkved22" size="2" maxlength="2" value='${model.record.arkved22}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved23" id="arkved23" size="2" maxlength="2" value='${model.record.arkved23}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved24" id="arkved24" size="2" maxlength="2" value='${model.record.arkved24}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved25" id="arkved25" size="2" maxlength="2" value='${model.record.arkved25}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved26" id="arkved26" size="2" maxlength="2" value='${model.record.arkved26}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved27" id="arkved27" size="2" maxlength="2" value='${model.record.arkved27}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved28" id="arkved28" size="2" maxlength="2" value='${model.record.arkved28}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved29" id="arkved29" size="2" maxlength="2" value='${model.record.arkved29}'></td>
														<td><input type="text" class="inputTextMediumBlue" name="arkved30" id="arkved30" size="2" maxlength="2" value='${model.record.arkved30}'></td>
													</tr>
													<tr>
														<td class="text14" title="arvedl">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.arkvedl"/>:
														</td>													
														<td colspan="8"><input type="text" class="inputTextMediumBlue" name="arvedl" id="arvedl" size="45" maxlength="40" value='${model.record.arvedl}'></td>
														<td colspan="3" class="text14" title="armrg">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.armrg"/>:
														</td>
														<td colspan="2">
															<select name="armrg" id="armrg" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.armrg == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${model.record.armrg == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
													</tr>
													
												</table>
											</td>
										</tr>
									</table>
								</td> <!-- End second row -->
							</tr>	
							
						<c:if test="${model.view_scanning == 'J'}"> 
						
							<tr>
								<td>&nbsp;  <!--  Third row --> 					
									<table class="formFrameHeaderPeachWithBorder" width="100%" 	cellspacing="0" border="0" align="center">
										<tr>
											<td class="text14Bold">&nbsp;
												<spring:message code="systema.main.maintenance.mainmaintenancearc007.header.scanning"/>
											</td>
										</tr>
									</table>
										<table class="formFramePeachGrayRoundBottom"  width="100%" cellspacing="0" border="0" align="center">
										<tr> 
											<td>
												<table>
													<tr>
														<td class="text14" title="arsban">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.arsban"/>:
														</td>													
														<td colspan="8"><input type="text" class="inputTextMediumBlue" name="arsban" id="arsban" size="55" maxlength="50" value='${model.record.arsban}'>
															<a tabindex="-1" id="arsbanIdLink">
																<img style="cursor:pointer;vertical-align:middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="Søk" >
															</a>													
														</td>
														<td  class="text14" title="arsfsk">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.arsfsk"/>:
														</td>
														<td>
															<select name="arsfsk" id="arsfsk" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.arsfsk == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${model.record.arsfsk == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
														<td  class="text14" title="arscts">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.arscts"/>:
														</td>
														<td>
															<select name="arscts" id="arscts" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.arscts == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${model.record.arscts == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
														<td  class="text14" title="arslab">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.arslab"/>:
														</td>
														<td>
															<select name="arslab" id="arslab" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.arslab == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${model.record.arslab == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
													</tr>
													<tr>
														<td  class="text14Gray">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.specialrules"/>
														</td>												
													</tr>
													<tr>
														<td colspan="3" class="text14" title="arsrle">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.arsrle"/>:
														</td>													
														<td><input type="text" class="inputTextMediumBlue" name="arsrle" id="arsrle" size="5" maxlength="2" value='${model.record.arsrle}'></td>
														<td class="text14" title="arsrpa">&nbsp;&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.arsrpa"/>:
														</td>													
														<td><input type="text" class="inputTextMediumBlue" name="arsrpa" id="arsrpa" size="7" maxlength="2" value='${model.record.arsrpa}'></td>
														<td class="text14" title="arsrst">&nbsp;&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.arsrst"/>:
														</td>													
														<td><input type="text" class="inputTextMediumBlue" name="arsrst" id="arsrst" size="5" maxlength="2" value='${model.record.arsrst}'></td>
														<td  class="text14" title="arsrno">&nbsp;
															<spring:message code="systema.main.maintenance.mainmaintenancearc007.arsrno"/>:
														</td>
														<td>
															<select name="arsrno" id="arsrno" >
						 					  					<option value="">-<spring:message code="systema.choose"/>-</option>
						 					  					<option value="J"<c:if test="${model.record.arsrno == 'J'}"> selected </c:if>><spring:message code="systema.yes"/></option>
											  					<option value="N"<c:if test="${model.record.arsrno == 'N'}"> selected </c:if>><spring:message code="systema.no"/></option>
											  				</select>
														</td>
													</tr>													
												</table>
											</td>
										</tr>
									</table>
								</td>  <!--  End third row -->
							</tr>	
					</c:if>		
							
							<tr height="3"><td>&nbsp;</td></tr>
							
							<tr> 
								<td align="right">
									<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.save"/>'/>
								</td>
							</tr>

							<tr height="3"><td>&nbsp;</td></tr>
							
						</table>
	 	    		</form>
				</td>
			</tr>	
	 	    <tr height="20"><td>&nbsp;</td></tr>
	 	    
	 	    
	 	    
	 		</table>
		</td>
	</tr>
</table>	

<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

