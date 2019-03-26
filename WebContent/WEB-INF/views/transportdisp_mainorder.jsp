<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTransportDisp.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_mainorder.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	
	<style type = "text/css">
	.ui-dialog{font-size:11pt;}
	.ui-datepicker { font-size:9pt;}
	.ui-timepicker-div .ui-widget-header { margin-bottom: 8px; }
	.ui-timepicker-div dl { text-align: left; }
	.ui-timepicker-div dl dt { float: left; clear:left; padding: 0 0 0 5px; }
	.ui-timepicker-div dl dd { margin: 0 10px 10px 40%; }
	.ui-timepicker-div td { font-size: 90%; }
	.ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
	
	.ui-timepicker-rtl{ direction: rtl; }
	.ui-timepicker-rtl dl { text-align: right; padding: 0 5px 0 0; }
	.ui-timepicker-rtl dl dt{ float: right; clear: right; }
	.ui-timepicker-rtl dl dd { margin: 0 40% 10px 10px; }
	
	/* this is in order to customize a SPECIFIC ui dialog in the .js file ...dialog() */
	.print-dialog-class .ui-widget-content{ background-color:lightsteelblue } 
	.main-dialog-class .ui-widget-content{ background-image:none;background-color:lemonchiffon }

	</style>

<table id="topTableLocal" width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25">
			<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkOrderListId" style="display:block;" href="transportdisp_mainorderlist.do?action=doFind&avd=${model.record.heavd}">
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink"><spring:message code="systema.transportdisp.workflow.trip.all.openorders.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<%-- <a id="alinkTripListId" style="display:block;" id="ordersOpen" href="transportdisp_workflow_getTrip.do?user=${user.user}&tuavd=${model.record.heavd}&tupro=${model.parentTrip}"> --%>
				<a id="alinkTripListId" style="display:block;" id="ordersOpen" href="transportdisp_workflow_getTrip.do?user=${user.user}&tuavd=${model.record.heavd}&tupro=">
					<img style="vertical-align:bottom;" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.tab"/></font>&nbsp;<font class="text10Orange">F9</font>
				</a>
			</td>
			<%-- Only present this option with there is a trip behind the order, otherwise this is a brand new order without any previous trip connection --%>
			<c:if test="${not empty model.parentTrip}">
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkParentTripId" style="display:block;" id="orderList" href="transportdisp_mainorderlist.do?action=doFind&wssavd=${model.record.heavd}&wstur=${model.parentTrip}">
						<img title="Add" style="vertical-align:bottom;" src="resources/images/addOrder.png" width="14" hight="15" border="0" alt="add">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.transportdisp.open.orderlist.trip.label"/>&nbsp;${model.parentTrip}</font>&nbsp;<font class="text10Orange">F10</font>
					</a>
				</td>
			</c:if>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="18%" valign="bottom" class="tab" align="center" nowrap>
				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">
				<font class="tabLink">&nbsp;<spring:message code="systema.transportdisp.update.ourref.tab"/><font class="text14MediumBlue">&nbsp;${model.record.heavd}/${model.record.heopd}</font></font>
				&nbsp;&nbsp;
				<div title="FileUpload" style="display:inline-block; cursor:pointer;" onClick="showDialogFileUploadDraggable();" >
					<font class="text14OrangeBold">e</font>
				</div>	
			</td>
			<c:if test="${not empty model.record.heopd}">
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkInvoice" style="display:block;" href="transportdisp_mainorder_invoice.do?action=doFind&hepro=${model.parentTrip}&heavd=${model.record.heavd}&heopd=${model.record.heopd}&itemsType=O">
						<img title="Add" style="vertical-align:bottom;" src="resources/images/invoice2.png" width="16" hight="16" border="0" alt="invoice">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.transportdisp.orders.invoice.tab"/>&nbsp;</font>&nbsp;<font class="text10Orange">F4</font>
					</a>
				</td>
			</c:if>
			<c:choose>
				<c:when test="${not empty model.parentTrip || not empty model.record.heopd}">
					<%-- Since there will be an extra tab above we must compensate the phantom space here (when applicable) --%>
					<td width="30%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>		
				</c:when>
				<c:otherwise>
					<td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>		
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
	</td>
	</tr>
	
	<%-- --------------------------- --%>
	<%-- Validation errors FRONT END --%>
	<%-- --------------------------- --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
	<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
	            <ul>
	            <c:forEach var="error" items="${errors.allErrors}">
	                <li >
	                	<spring:message code="${error.code}" text="${error.defaultMessage}"/>
	                </li>
	            </c:forEach>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
	</tr>
	</spring:hasBindErrors>	
	
	<%-- -------------------------- --%>
	<%-- Validation errors BACK END --%>
	<%-- -------------------------- --%>
	<c:if test="${not empty model.containerValidationBackend && not empty model.containerValidationBackend.errMsgListFromValidationBackend}">
		<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
	            <ul>
	            <c:forEach var="errMsg" items="${model.containerValidationBackend.errMsgListFromValidationBackend}">
	                <li >${errMsg}</li>
	            </c:forEach>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
		</tr>		
	</c:if>
	
	<%-- -------------------------------------- --%>
	<%-- Validation errors BACK END independent --%>
	<%-- Usually Frie sokveier eller DUP        --%>
	<%-- -------------------------------------- --%>
	<c:if test="${not empty model.containerValidationBackend && not empty model.containerValidationBackend.errMsg}">
		<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">
				<p>BACK-END error</p>					
	            <ul>
	            	<li >${model.containerValidationBackend.errMsg}</li>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
		</tr>		
	</c:if>
	<%-- -------------------------- --%>
	<%-- Validation errors on model --%>
	<%-- -------------------------- --%>
	<c:if test="${not empty model.errorMessage}">
		<tr>
		<td>
           	<table class="tabThinBorderWhiteWithSideBorders" width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
           	<tr>
			<td valign="bottom" class="textError">					
	            <ul>
	            	<li >${model.errorMessage}</li>
	            </ul>
			</td>
			</tr>
			</table>
		</td>
		</tr>		
	</c:if>
	
	
	
	<%-- ------------------------------- --%>
	<%-- Floating window for file upload --%>
	<%-- ------------------------------- --%>
		<c:if test="${not empty model.record.heopd}">
			<tr>
				<td valign="top" >
					<!-- <span style="position:absolute; left:1550px; top:160px; width:550px; height:800px;" id="economyMatrixInfo" class="popupFloating"  -->
						<div id="dialogDraggableFileUpload" title="File Upload">
		           		<table class="popupFloatingWithRoundCorners3D">
					    	<tr>
							<td valign="top">
							<form name="uploadFileForm" id="uploadFileForm" method="post" enctype="multipart/form-data">
								<input type="hidden" name="applicationUserUpload" id="applicationUserUpload" value='${user.user}'>
								<input type="hidden" name="userHttpJQueryDocRoot" id="userHttpJQueryDocRoot" value='${user.httpJQueryDocRoot}'>
								<input type="hidden" name="wsavd" id="wsavd" value='${model.record.heavd}'>
								<input type="hidden" name="wsopd" id="wsopd" value='${model.record.heopd}'>
								<input type="hidden" name="userDate" id="userDate" value=''>
								<input type="hidden" name="userTime" id="userTime" value=''>
								
									<table id="containerdatatableTable" cellspacing="2" align="left">
										<tr>
											<td colspan="3" class="text14Bold">&nbsp;
												<img style="vertical-align:bottom;" src="resources/images/upload.png" border="0" width="15" height="15" alt="upload">
												&nbsp;File Upload&nbsp;							
											</td>
										</tr>
										<tr>
										<tr>
										<td>
											<table>
											<tr>
												<td class="text11">&nbsp;Archive type</td>
												<td class="text11">&nbsp;
													<select class="inputTextMediumBlue" tabindex=-1 name="wstype" id="wstype">
														<c:forEach var="record" items="${user.arkivKodOpdList}" >
								                       	 	<option value="${record.arkKod}">${record.arkKod}-${record.arkTxt}</option>
														</c:forEach> 
													</select>	
												</td>
											</tr>
											<tr>	
												<td class="text11">&nbsp;File</td>
												<td class="text11">
					           						&nbsp;<input ondragenter="myFileUploadDragEnter(event)" ondragleave="myFileUploadDragLeave(event)" tabindex=-1 class="tableBorderWithRoundCornersLightYellow3D noFileChosenTransparent" style="width:150px;height:80px;display:block;" type="file" name="file" id="file" />
					       						</td>
							           		</tr>
							           		</table>
										</td>
										</tr>
					       			</table>
							</form>	
							</td>
							</tr>
						</table>
					  </div>	
				</td>
			</tr>
		</c:if>
		
		
		
		<tr>
		<td>
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<form name="transportdispForm" id="transportdispForm" method="post">
			<input type="hidden" name="parentTrip" id="parentTrip" value="${model.parentTrip}">
			<input type="hidden" name="hedtr" id="hedtr" value="${model.record.hedtr}">
		
			<table style="width:100%" id="wrapperTable" class="tabThinBorderWhite" cellspacing="0">
			<tr height="10"><td>&nbsp;</td></tr> 
			<%-- FORM HEADER --%>
	 		<tr>
            		<td>
	        			<table style="width:99%;" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White" >
								&nbsp;<spring:message code="systema.transportdisp.orders.form.update.label.header.edit"/>	
								&nbsp;&nbsp;<b>${model.record.heavd}/${model.record.heopd}</b>
								<c:if test="${not empty model.record.trslag}">
									&nbsp;<b>${model.record.trslag}</b>
								</c:if>
								<img style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
								<c:if test="${not empty model.record.heopd}">
									<font style="color:yellow;font-size:12px">&nbsp;Reg.date:&nbsp;${model.record.hedtr}</font>
								</c:if>
								
								<c:if test="${not empty model.record.hepro}">
									&nbsp;&nbsp;&nbsp;&nbsp;Turnr&nbsp;${model.record.hepro}
								</c:if>
			 				</td>
		 				</tr>
	 				</table>
            		</td>
            </tr>
            <%-- FORM DETAIL --%>
            <tr >
            		<td>
            		<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
					<input type="hidden" name="tripNr" id="tripNr" value='${model.record.hepro}'>
					<input type="hidden" name="heopd" id="heopd" value='${model.record.heopd}'>
					
					<input type="hidden" name="fkeysavd" id="fkeysavd" value='${model.record.heavd}'>
					<input type="hidden" name="fkeystur" id="fkeystur" value='${model.record.hepro}'>
					<input type="hidden" name="fkeysturParent" id="fkeysturParent" value='${model.parentTrip}'>
					<input type="hidden" name="fkeysopd" id="fkeysopd" value='${model.record.heopd}'>
					
					<input type="hidden" name="messageNoteConsigneeOriginal" id="messageNoteConsigneeOriginal" value='${model.record.messageNoteConsignee}'>
					<input type="hidden" name="messageNoteCarrierOriginal" id="messageNoteCarrierOriginal" value='${model.record.messageNoteCarrier}'>
					<input type="hidden" name="messageNoteInternalOriginal" id="messageNoteInternalOriginal" value='${model.record.messageNoteInternal}'>
		
					<table style="width:99%;" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="10"><td ></td></tr>
				 		<tr>
							<td colspan="2" valign="top" >
								<table border="0" >
								<tr>
									<td colspan="6" class="text14" >
								 		<c:choose>
								 		<c:when test="${not empty model.record.heopd}">
								 			&nbsp;<span title="heavd"><font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.update.label.dept"/></span>&nbsp;		
								 			<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="heavd" id="heavd" size="5" value='${model.record.heavd}'>
								 			&nbsp;&nbsp;&nbsp;<span title="hesg"><spring:message code="systema.transportdisp.orders.form.update.label.sign"/></span>&nbsp;		
								 			<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="hesg" id="hesg" size="5" value='${model.record.hesg}'>
								 			<%-- Drag and drop handle (when being source) --%>
								 			&nbsp;<img title="Drag to target..." style="vertical-align:middle;cursor:pointer;" src="resources/images/icon_drag_drop.png" width="30px" height="30px" border="0" alt="edit" draggable="true" ondragstart="drag(event)" id="avd_${model.record.heavd}@opd_${model.record.heopd}@tripnr_${model.record.hepro}">
								 		
								 		</c:when>
								 		<c:otherwise>
											&nbsp;<span title="heavd"><font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.update.label.dept"/></span>&nbsp;		
								 			<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="heavd" id="heavd" size="5" maxlength="4" value="${model.record.heavd}">
								 			&nbsp;&nbsp;&nbsp;<span title="hesg"><spring:message code="systema.transportdisp.orders.form.update.label.sign"/></span>&nbsp;		
								 			<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="hesg" id="hesg" size="5" value='${user.signatur}'>
								 			<%--
								 			<select name="hesg" id="hesg">
						 						<option value="">-select-</option>
							 				  	<c:forEach var="record" items="${model.signList}" >
							 				  		<option value="${record.sign}"<c:if test="${model.record.hesg == record.sign || (empty model.record.hesg && record.sign==user.signatur)}"> selected </c:if> >${record.sign}</option>
												</c:forEach>  
											</select>
											 --%>
								 		</c:otherwise>	
								 		</c:choose>
								 		
								 		<c:if test="${not empty model.record.heopd}">
								 			&nbsp;<button name="frisokveiButton" id="frisokveiButton" class="buttonGrayWithGreenFrame" type="button" >Frie søkeveier</button>
								 			
								 		</c:if>
								 		<c:if test="${not empty model.record.hesgm}">
								 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font title="hesgm" class="inputText11" style="background-color: #DFF2BF;color: #4F8A10;">Levert:&nbsp;${model.record.hesgm}&nbsp;-&nbsp;${model.record.hedtmo}:${model.record.heklmo}</font>
								 		</c:if>	
								 		
								 	</td>
								 	<c:if test="${not empty model.record.heopd}">
									 	<td>&nbsp;
									 		<%--
				 							<a onClick="setBlockUI(this)" tabindex=-1 href="transpdisp_mainorder_printout.do?avd=${model.record.heavd}&opd=${model.record.heopd}">
				 								<img title="Print" style="vertical-align: bottom;cursor:pointer;" src="resources/images/printer3.png" width="28px" height="28px" border="0" alt="Print">
				 							</a>
				 							 --%>
				 							<img id="printImg" name="printImg" title="Print" style="vertical-align: bottom;cursor:pointer;" src="resources/images/printer3.png" width="28px" height="28px" border="0" alt="Print"> 
				 						</td>
			 						</c:if>
			 						<td width="25"></td>
			 						<td class="text14">
				 						<c:if test="${not empty model.record.heopd}">
					 						<button name="smsButton" id="smsButton" class="buttonGrayWithGreenFrame" type="button" >Send SMS</button>
					 						<button name="emailButton" id="emailButton" class="buttonGrayWithGreenFrame" type="button" >Send Mail</button>
					 						<button name="budgetButton" id="budgetButton" class="buttonGrayWithGreenFrame" type="button" >Budsjett/rekv.</button>
									   		&nbsp;<button name="planleggingButton" id="planleggingButton" class="buttonGrayWithGreenFrame" type="button" >Til planlegging</button>
									   		&nbsp;&nbsp;<button tabindex=-1 name="trackAndTraceButton" id="trackAndTraceButton" class="buttonGrayWithGreenFrame" type="button" >Hend.logg</button>
									   		<c:if test="${ not empty model.record.heavd && not empty model.record.heopd }">
						 				    	<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit2" id="submit2" onClick="javascript: form.action='transportdisp_mainorder_update.do';" value='<spring:message code="systema.transportdisp.submit.save"/>'/>
						 				    	<input title="Lagre og Lukk" onMouseOver="style='cursor:pointer;box-shadow: 0 0 5px rgba(66, 148, 255, 1);border: 5px solid rgba(66, 148, 255, 1);'" onMouseOut="style=''" type="checkbox" name="savecloseFlag2" id="savecloseFlag2" value="1" <c:if test="${model.record.savecloseFlag2 == '1'}"> checked </c:if>></span>						 	
						 				    	
						 				    </c:if>
										</c:if>
				 					</td>
			 						
			 						<td width="15"></td>
			 						<td class="text14">
				 						<img onMouseOver="showPop('iu_info');" onMouseOut="hidePop('iu_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="travd1-tropd1/travd2-tropd2"><spring:message code="systema.transportdisp.orders.form.update.label.iu.order"/>&nbsp;</span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:250px" id="iu_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Innh./Utkj.oppdrag</b>
							           			<div>
							           			<p>Dersom utfylt peker det på avdeling og oppdragsnummer for evt. <b>innhenting</b> (foran streken)
													og/eller <b>utkjøring</b> som er "satt bort" til annen avdeling.
							           			</p>
							           			</div>
						           			</font>
										</span>
										</div>
				 					</td>
				 					<td class="text14">
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="travd1" id="travd1" size="4" value="${model.record.travd1}">
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tropd1" id="tropd1" size="7" value="${model.record.tropd1}">
				 						&nbsp;/&nbsp;
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="travd2" id="travd2" size="3" value="${model.record.travd2}">
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tropd2" id="tropd2" size="7" value="${model.record.tropd2}">
				 						<span title="travd0-tropd0" >&nbsp;Opphav.</span>
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="travd0" id="travd0" size="3" value="${model.record.travd0}">
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tropd0" id="tropd0" size="7" value="${model.record.tropd0}">
			 						</td>

							 	</tr>
							 	</table>	
							 	
							</td>
							
							<%--
							<td valign="top">
								<table >
								<tr>
				 					<td class="text14">
				 						<img onMouseOver="showPop('iu_info');" onMouseOut="hidePop('iu_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="travd1-tropd1/travd2-tropd2"><spring:message code="systema.transportdisp.orders.form.update.label.iu.order"/>&nbsp;</span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:250px" id="iu_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Innh./Utkj.oppdrag</b>
							           			<div>
							           			<p>Dersom utfylt peker det på avdeling og oppdragsnummer for evt. <b>innhenting</b> (foran streken)
													og/eller <b>utkjøring</b> som er "satt bort" til annen avdeling.
							           			</p>
							           			</div>
						           			</font>
										</span>
										</div>
				 					</td>
				 					<td class="text14">
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="travd1" id="travd1" size="4" value="${model.record.travd1}">
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tropd1" id="tropd1" size="7" value="${model.record.tropd1}">
				 						&nbsp;/&nbsp;
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="travd2" id="travd2" size="3" value="${model.record.travd2}">
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tropd2" id="tropd2" size="7" value="${model.record.tropd2}">
				 						<span title="travd0-tropd0" >&nbsp;Opphav.</span>
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="travd0" id="travd0" size="3" value="${model.record.travd0}">
				 						<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="tropd0" id="tropd0" size="7" value="${model.record.tropd0}">
			 						</td>
			 						
			 						
				 				</tr>
				 				<tr>
				 					<td colspan="6" class="text14">
				 						<c:if test="${not empty model.record.heopd}">
					 						<button name="smsButton" id="smsButton" class="buttonGrayWithGreenFrame" type="button" >Send SMS</button>
					 						<button name="emailButton" id="emailButton" class="buttonGrayWithGreenFrame" type="button" >Send Mail</button>
					 						<button name="budgetButton" id="budgetButton" class="buttonGrayWithGreenFrame" type="button" >Budsjett/rekv.</button>
									   		&nbsp;<button name="planleggingButton" id="planleggingButton" class="buttonGrayWithGreenFrame" type="button" >Til planlegging</button>
									   		&nbsp;&nbsp;<button tabindex=-1 name="trackAndTraceButton" id="trackAndTraceButton" class="inputFormSubmitStd" type="button" >Hend.logg</button>
									   		<c:if test="${ not empty model.record.heavd && not empty model.record.heopd }">
						 				    	<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit2" id="submit2" onClick="javascript: form.action='transportdisp_mainorder_update.do';" value='<spring:message code="systema.transportdisp.submit.save"/>'/>
						 				    	<input title="Lagre og Lukk" onMouseOver="style='cursor:pointer;box-shadow: 0 0 5px rgba(66, 148, 255, 1);border: 5px solid rgba(66, 148, 255, 1);'" onMouseOut="style=''" type="checkbox" name="savecloseFlag2" id="savecloseFlag2" value="1" <c:if test="${model.record.savecloseFlag2 == '1'}"> checked </c:if>></span>						 	
						 				    	
						 				    </c:if>
										</c:if>
				 					</td>
				 				</tr>
				 			
				 				</table>
							</td>
							 --%>
						</tr>
						
						<tr height="5"><td ></td></tr>
						<tr>
							<td colspan="2">
							<table border="0">
							 	<tr>
							 		<td class="text14">
							 			<img onMouseOver="showPop('principalCode_info');" onMouseOut="hidePop('principalCode_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="trkdak"><spring:message code="systema.transportdisp.orders.form.update.label.principalCode"/>&nbsp;</span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:250px" id="principalCode_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Oppdragsgiver</b>
							           			<div>
							           			<p><b>Kode</b> foran angir om oppdragsgiver er Selger/eller Kjøper (Avsender/mottaker). Dette letter automatisk utfylling av felt lenger ned på bildet.
							           			</p>
							           			</div>
						           			</font>
										</span>
										</div>
							 		</td>
							 		<td class="text14">
										<c:choose>
								 			<c:when test="${not empty model.record.heavd && empty model.record.trknfa}">
									 			<select class="inputTextMediumBlue" name="trkdak" id="trkdak">
							 						<option value="S" <c:if test="${model.record.trkdak == 'S'}"> selected </c:if> >S</option>
							 						<option value="K" <c:if test="${model.record.trkdak == 'K'}"> selected </c:if> >K</option>
							 						<option value="">-blank-</option>
												</select>
											</c:when>
											<c:otherwise>
												<select class="inputTextMediumBlue" name="trkdak" id="trkdak">
							 						<option value="">-blank-</option>
							 						<option value="K" <c:if test="${model.record.trkdak == 'K'}"> selected </c:if> >K</option>
							 						<option value="S" <c:if test="${model.record.trkdak == 'S'}"> selected </c:if> >S</option>
												</select>
											</c:otherwise>
										</c:choose>
							 		</td>
							 	
						 		 	<td class="text14">&nbsp;&nbsp;
							 			<img onMouseOver="showPop('principalNo_info');" onMouseOut="hidePop('principalNo_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="trknfa"><font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.update.label.principalNo"/>&nbsp;</span>
						 				
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:250px" id="principalNo_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Oppdragsgiver</b>
							           			<div>
							           			<p><b>Kundenr</b>kan fylles ut direkte.
							           			</p>
							           			<p><b>Navn</b> første del av navn (søkebegrep) fylles ut.<br> 
							           				Dersom en (og bare en) forekomst av det aktuelle søkebegrepet(del av..), hetes kundenr, automatisk.
													Dersom flere forekomster finnes, vises "plukk-liste".
							           			</p>
							           			</div>
						           			</font>
										</span>
										</div>
				 					</td>
				 					<td class="text14">
					 					<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="trknfa" id="trknfa" size="10" maxlength="8" value="${model.record.trknfa}">
					 					<a tabindex=-1 href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_customer.do?action=doInit&ctype=a','customerWin','top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
	 										<img id="imgAgentSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 									</a>
					 				</td>
					 				<td class="text14">&nbsp;&nbsp;
					 					<img onMouseOver="showPop('principalNo_info');" onMouseOut="hidePop('principalNo_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="henaa"><font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.update.label.principalName"/></span>
						 			</td>
						 			<td>	
						 				<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="henaa" id="henaa" size="30" value="${model.record.henaa}">
						 				<input type="hidden" name="varFakknr" id="varFakknr" value="">
						 				
				 					</td>
				 					
				 					
				 					<td class="text14"><img onMouseOver="showPop('oType_info');" onMouseOut="hidePop('oType_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										<span title="heot"><font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.update.label.orderType"/>&nbsp;</span>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:350px" id="oType_info" class="popupWithInputText"  >
											<font class="text11">
							           			<b>Oppdragstype</b>
							           			<div>
							           			<p>Intern klassifisering av oppdraget/tjenesten. Avhengig av oppbygging kan det brukes for forskjellige formål:
							           			</p>
							           			<ul>
							           				<li><b>Splitting</b> av oppdragsmassen i stykkgods, bulkgods, tankgods... (sentralt begrep for valg av hvilke oppdrag en vil ha fram i lasteplanleggingen.)</li>
							           				<li><b>Ulik prising</b> av ulike tjenester (inngår som begrep i prisavtaleoppbyggingen).</li>
							           				<li><b>Provisjonssatser</b> for transportøravregning kan nyanseres på oppdragstypenivå.</li>
							           			</ul>
							           			</div>
						           			</font>
										</span>
										</div>
									</td>
						 			<td class="text14">
						 				<select class="inputTextMediumBlueMandatoryField" name="heot" id="heot">
						            		<option value="">-select-</option>
						 				  	<c:forEach var="record" items="${model.oppdragstypeList}" >
					                       	 	<option title="${record.beskr}" value="${record.opdTyp}"<c:if test="${model.record.heot == record.opdTyp}"> selected </c:if> >${record.opdTyp}</option>
											</c:forEach> 
										</select>	
										<a tabindex=0 id="heotIdLink" >	
											<img id="heotImg" tabindex=-1 style="vertical-align: middle;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
										</a>
						 			</td>
						 			
						 												
									<td class="text14">&nbsp;&nbsp;
										<img onMouseOver="showPop('incoterms_info');" onMouseOut="hidePop('incoterms_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="hefr"><font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.update.label.incoterms"/>&nbsp;</span>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:350px" id="incoterms_info" class="popupWithInputText"  >
											<font class="text11">
							           			<b>Frankatur/Leveringsbetingelser</b>
							           			<div>
							           			<p>Vesentlig begrep i prisavtalesystem. Overføres til SAD. <br>
							           			På trafikker hvor en IKKE er opptatt av frankatur må en eller annen "dummyverdi" opprettes/benyttes.
							           			</p>
							           			</div>
						           			</font>
										</span>
										</div>
									</td>
						 			<td class="text14" align="left">
						 				<select class="inputTextMediumBlueMandatoryField" name="hefr" id="hefr">
							            		<option value="">-select-</option>
							            		<c:choose>
							            		<c:when test="${not empty model.record.hefr}">
								            		<c:forEach var="record" items="${model.incotermsList}" >
								            			<option title="${record.beskr}" value="${record.franka}"<c:if test="${model.record.hefr == record.franka}"> selected </c:if> >${record.franka}</option>
													</c:forEach> 
												</c:when>
												<c:otherwise>
													<c:forEach var="record" items="${model.incotermsList}" >
								            			<option title="${record.beskr}" value="${record.franka}"<c:if test="${record.franka=='S'}"> selected </c:if> >${record.franka}</option>
													</c:forEach>
												</c:otherwise>
												</c:choose>
										</select>
										<a tabindex=0 id="hefrIdLink" >
 											<img id="imgIncotermsSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
 										</a>	
						 			</td>
						 			<td align="right" class="text14">&nbsp;<span title="hebodt"><spring:message code="systema.transportdisp.orders.form.update.label.booking.date"/></span></td>
						 			<td class="text14">
					 					<input type="text" class="inputTextMediumBlue" name="hebodt" id="hebodt" size="9" maxlength="8" value="${model.record.hebodt}">
						    				
										&nbsp;<span title="wsbotm"><spring:message code="systema.transportdisp.orders.form.update.label.booking.time"/></span>
						 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="wsbotm" id="wsbotm" size="4" maxlength="4" value="${model.record.wsbotm}">
						 				&nbsp;<span title="hestn7"><font <c:if test="${model.record.hestn7 == 'P'}"> style="font-weight:bold;"   </c:if>><spring:message code="systema.transportdisp.orders.form.update.label.prebooking"/></font></span>
						 				<input style="width:14px; height:14px;" type="checkbox" id="hestn7" name="hestn7" value="P" <c:if test="${model.record.hestn7 == 'P'}"> checked </c:if>>
						 			</td>	
				 				</tr>
				 				
				 				<%--
				 				<tr>	
									<td class="text14"><img onMouseOver="showPop('oType_info');" onMouseOut="hidePop('oType_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
										<span title="heot"><font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.update.label.orderType"/>&nbsp;</span>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:350px" id="oType_info" class="popupWithInputText"  >
											<font class="text11">
							           			<b>Oppdragstype</b>
							           			<div>
							           			<p>Intern klassifisering av oppdraget/tjenesten. Avhengig av oppbygging kan det brukes for forskjellige formål:
							           			</p>
							           			<ul>
							           				<li><b>Splitting</b> av oppdragsmassen i stykkgods, bulkgods, tankgods... (sentralt begrep for valg av hvilke oppdrag en vil ha fram i lasteplanleggingen.)</li>
							           				<li><b>Ulik prising</b> av ulike tjenester (inngår som begrep i prisavtaleoppbyggingen).</li>
							           				<li><b>Provisjonssatser</b> for transportøravregning kan nyanseres på oppdragstypenivå.</li>
							           			</ul>
							           			</div>
						           			</font>
										</span>
										</div>
									</td>
						 			<td class="text14">
						 				<select class="inputTextMediumBlueMandatoryField" name="heot" id="heot">
						            		<option value="">-select-</option>
						 				  	<c:forEach var="record" items="${model.oppdragstypeList}" >
					                       	 	<option title="${record.beskr}" value="${record.opdTyp}"<c:if test="${model.record.heot == record.opdTyp}"> selected </c:if> >${record.opdTyp}</option>
											</c:forEach> 
										</select>	
										<a tabindex=0 id="heotIdLink" >	
											<img id="heotImg" tabindex=-1 style="vertical-align: middle;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
										</a>
						 			</td>
						 			
						 												
									<td class="text14">&nbsp;&nbsp;
										<img onMouseOver="showPop('incoterms_info');" onMouseOut="hidePop('incoterms_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="hefr"><font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.update.label.incoterms"/>&nbsp;</span>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:350px" id="incoterms_info" class="popupWithInputText"  >
											<font class="text11">
							           			<b>Frankatur/Leveringsbetingelser</b>
							           			<div>
							           			<p>Vesentlig begrep i prisavtalesystem. Overføres til SAD. <br>
							           			På trafikker hvor en IKKE er opptatt av frankatur må en eller annen "dummyverdi" opprettes/benyttes.
							           			</p>
							           			</div>
						           			</font>
										</span>
										</div>
									</td>
						 			<td class="text14" align="left">
						 				<select class="inputTextMediumBlueMandatoryField" name="hefr" id="hefr">
							            		<option value="">-select-</option>
							            		<c:choose>
							            		<c:when test="${not empty model.record.hefr}">
								            		<c:forEach var="record" items="${model.incotermsList}" >
								            			<option title="${record.beskr}" value="${record.franka}"<c:if test="${model.record.hefr == record.franka}"> selected </c:if> >${record.franka}</option>
													</c:forEach> 
												</c:when>
												<c:otherwise>
													<c:forEach var="record" items="${model.incotermsList}" >
								            			<option title="${record.beskr}" value="${record.franka}"<c:if test="${record.franka=='S'}"> selected </c:if> >${record.franka}</option>
													</c:forEach>
												</c:otherwise>
												</c:choose>
										</select>
										<a tabindex=0 id="hefrIdLink" >
 											<img id="imgIncotermsSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
 										</a>	
						 			</td>
						 			<td align="right" class="text14">&nbsp;<span title="hebodt"><spring:message code="systema.transportdisp.orders.form.update.label.booking.date"/></span></td>
						 			<td class="text14">
					 					<input type="text" class="inputTextMediumBlue" name="hebodt" id="hebodt" size="9" maxlength="8" value="${model.record.hebodt}">
						    				
										&nbsp;<span title="wsbotm"><spring:message code="systema.transportdisp.orders.form.update.label.booking.time"/></span>
						 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="wsbotm" id="wsbotm" size="4" maxlength="4" value="${model.record.wsbotm}">
						 				&nbsp;<span title="hestn7"><font <c:if test="${model.record.hestn7 == 'P'}"> style="font-weight:bold;"   </c:if>><spring:message code="systema.transportdisp.orders.form.update.label.prebooking"/></font></span>
						 				<%-- <input type="text" class="inputTextMediumBlue tableCell" <c:if test="${not empty model.record.hestn7}">style="background-color:#FEEFB3; color:red; font-weight:bold;"</c:if> name="hestn7" id="hestn7" size="1" maxlength="1" value="${model.record.hestn7}"> 
						 				<input style="width:14px; height:14px;" type="checkbox" id="hestn7" name="hestn7" value="P" <c:if test="${model.record.hestn7 == 'P'}"> checked </c:if>>
						 			</td>	
						 		</tr>
						 		 --%>
							</table>
							</td>
						</tr>
						
						<tr height="5"><td ></td></tr>
						<tr height="3"><td colspan="2" style="border-bottom:1px solid;border-color:#DDDDDD;" class="text"></td></tr>
						<tr height="5"><td ></td></tr>
						<tr>
							<td class="text14Bold">&nbsp;<font class="text16RedBold" >*</font>
								<spring:message code="systema.transportdisp.orders.form.update.label.shipper"/>
							</td>
							<td class="text14Bold">&nbsp;<font class="text16RedBold" >*</font>
								<spring:message code="systema.transportdisp.orders.form.update.label.consignee"/>
							</td>
						</tr>
						<tr height="5"><td ></td></tr>
						
						<tr>
							<td >
							<table class="tableBorderWithRoundCornersLightGray">
								<tr>
									<td class="text14Bold"><spring:message code="systema.transportdisp.orders.form.update.label.shippingDates"/></td>
									<td class="text14">&nbsp;<font class="text14RedBold" >*</font><span title="wsetdd/wsetdk"><spring:message code="systema.transportdisp.orders.form.update.label.shippingDates.etd"/></span></td>
									<td class="text14">
										<input type="text" class="inputTextMediumBlueMandatoryField" name="wsetdd" id="wsetdd" size="9" maxlength="8" value="${model.record.wsetdd}">
									</td>
									<td class="text14"><input type="text" class="inputTextMediumBlueMandatoryField" name="wsetdk" id="wsetdk" size="4" maxlength="4" value="${model.record.wsetdk}"></td>
									
									<td class="text14">&nbsp;<span title="wsatdd/wsatdk"><spring:message code="systema.transportdisp.orders.form.update.label.shippingDates.atd"/></span></td>
									<td class="text14">
										<input type="text" readonly class="inputTextReadOnly" name="wsatdd" id="wsatdd" size="9" maxlength="8" value="${model.record.wsatdd}">
						    			</td>
									<td class="text14"><input onKeyPress="return numberKey(event)" type="text" readonly class="inputTextReadOnly" name="wsatdk" id="wsatdk" size="4" maxlength="4" value="${model.record.wsatdk}"></td>
									<td class="text14"><input type="checkbox" id="ownATDcb" name="ownATDcb" value="1" >
									
									<c:if test="${not empty model.record.etdWarning}">
										<td>
											<font class="inputText11" style="background-color: #FFBABA;color: #D8000C;">&nbsp;<spring:message code="systema.transportdisp.orders.form.warning.label.ETD.abovelimit.30" /></font>
										</td>
									</c:if>
								</tr>
							</table>
							</td>
							
							<td >
							<table class="tableBorderWithRoundCornersLightGray">
								<tr>
									<td class="text14Bold"><spring:message code="systema.transportdisp.orders.form.update.label.arrivalDates"/></td>
									<td class="text14">&nbsp;<font class="text14RedBold" >*</font><span title="wsetad/wsetak"><spring:message code="systema.transportdisp.orders.form.update.label.arrivalDates.eta"/></span></td>
									<td class="text14">
										<input type="text" class="inputTextMediumBlueMandatoryField" name="wsetad" id="wsetad" size="9" maxlength="8" value="${model.record.wsetad}">
									</td>
									<td class="text14"><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="wsetak" id="wsetak" size="4" maxlength="4" value="${model.record.wsetak}"></td>
									
									<td class="text14">&nbsp;<span title="wsatad/wsatak"><spring:message code="systema.transportdisp.orders.form.update.label.arrivalDates.ata"/></span></td>
									<td class="text14">
										<input type="text" readonly class="inputTextReadOnly" name="wsatad" id="wsatad" size="9" maxlength="8" value="${model.record.wsatad}">
									</td>
									<td class="text14"><input onKeyPress="return numberKey(event)" type="text" readonly class="inputTextReadOnly" name="wsatak" id="wsatak" size="4" maxlength="4" value="${model.record.wsatak}"></td>
									<td class="text14"><input type="checkbox" id="ownATAcb" name="ownATAcb" value="1" >
									<c:if test="${not empty model.record.etaWarning}">
										<td>
											<font class="inputText11" style="background-color: #FFBABA;color: #D8000C;">&nbsp;<spring:message code="systema.transportdisp.orders.form.warning.label.ETA.abovelimit.30" /></font>
										</td>
									</c:if>
									
								</tr>
							</table>
							</td>		
						</tr>
						<tr height="5"><td ></td></tr>
						
						<tr>
				 			<td width="50%" valign="top" >
				 			 <table style="width:99%" class="tableBorderWithRoundCornersGray" cellspacing="1" cellpadding="0">
						 		<tr height="10"><td ></td></tr>
						 		<tr>
					 				<td class="text14">
					 					&nbsp;<span title="hekns"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.id"/>&nbsp;</span>
					 					<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_customer.do?action=doInit&ctype=s','customerWin','top=300px,left=50px,height=800px,width=1000px,scrollbars=no,status=no,location=no')">
	 										<img id="imgShipperSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 									</a>
					 				</td>
					 				<td class="text14">
					 					&nbsp;<span title="whenas"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.seller"/>&nbsp;</span>
					 				</td>
					 			</tr>
					 			<tr>	
				 					<td class="text14" ><input type="text" class="inputTextMediumBlueUPPERCASE" name="hekns" id="hekns" size="10" maxlength="8" value="${model.record.hekns}"></td>
								 	<td class="text14" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenas" id="whenas" size="35" value="${model.record.whenas}"></td>
				 				</tr>
								<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text14">&nbsp;<font class="text16RedBold" >*</font><span title="henas"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.name"/></span></td>
					 				<td class="text14">&nbsp;<font class="text16RedBold" >*</font><span title="heads1"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.pickup.address"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="henas" id="henas" size="25" maxlength="30" value="${model.record.henas}"></td>
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="heads1" id="heads1" size="25" maxlength="30" value="${model.record.heads1}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text14">&nbsp;<span title="heads2"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.address2"/></span></td>
					 				<td class="text14">&nbsp;<span title="heads3"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.postalcode.city"/></span></td>
					 			</tr>
								<tr>	
				 					
				 					<td class="text14" >
				 					<input type="text" class="inputTextMediumBlueUPPERCASE" name="heads2" id="heads2" size="25" maxlength="30" value="${model.record.heads2}">
				 					</td>
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" name="heads3" id="heads3" size="25" maxlength="30" value="${model.record.heads3}"></td>
				 				</tr>
				 				<tr height="15"><td ></td></tr>	
				 				<tr>	
				 					<td class="text14">
										<img onMouseOver="showPop('herfa_info');" onMouseOut="hidePop('herfa_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 					<span title="herfa"><spring:message code="systema.transportdisp.orders.form.update.label.avsRef"/></span>						 				
										<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:250px" id="herfa_info" class="popupWithInputText"  >
											<font class="text11">
							           			<b>Søk Avs.</b>
							           			<div>
							           			<p>Avsenders søkereferanse Fritt felt for utfylling. <br>
							           				Begrep for senere søk/gjenfinning.</p>
							           			</div>
						           			</font>
										</span>
										</div>
									</td>
				 					<td class="text14">
				 						<span title="hesdla">
				 							<img style="vertical-align:middle;" src="resources/images/loading.png" width="15px" height="15px" border="0" alt="load/unload">
				 							<spring:message code="systema.transportdisp.orders.form.update.label.load"/>
				 							<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_loadunloadplaces.do?action=doInit&&caller=hesdla','postalcodeWin','top=300px,left=50px,height=600px,width=800px,scrollbars=no,status=no,location=no')">						 				
						 						<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
						 					</a>
				 						</span>
				 					</td>
				 				</tr>
				 				<tr>	
				 					<td class="text14" >
				 						<input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="herfa" id="herfa" size="20" maxlength="35" value="${model.record.herfa}">
								 	</td>
				 					<td class="text14" >
						 				<input type="text" class="inputTextMediumBlue" name="hesdla" id="hesdla" size="21" maxlength="20" value="${model.record.hesdla}">
										
						 			</td>
				 				</tr>
				 				<tr height="5"><td ></td></tr>
								<tr>
					 				<td class="text14">&nbsp;<span title="wsscont"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.contactName"/></span></td>
					 				<td class="text14">&nbsp;<span title="wsstlf"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.telephone"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" name="wsscont" id="wsscont" size="25" maxlength="30" value="${model.record.wsscont}"></td>
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" name="wsstlf" id="wsstlf" size="25" maxlength="30" value="${model.record.wsstlf}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text14" colspan="2">&nbsp;<span title="wssmail"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.email"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text14" colspan="2"><input type="text" class="inputTextMediumBlue" name="wssmail" id="wssmail" size="50" maxlength="70" value="${model.record.wssmail}"></td>
				 				</tr>
				 				
				 				<tr height="8"><td ></td></tr>													 				
								<tr>
				 					<td class="text14Bold">&nbsp;
				 						<img style="vertical-align: bottom;" width="24px" height="24px" src="resources/images/invoice.png" border="0" alt="invoice">
				 						<spring:message code="systema.transportdisp.orders.form.update.label.shipper.invoicee"/>
			 						</td>
								</tr>
				 				<tr>
				 					<td colspan="2">
				 					<table class="tableBorderWithRoundCornersLightGray">
					 					<tr>
							 				<td class="text14">
							 					&nbsp;<span title="heknsf"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.invoicee.id"/>&nbsp;</span>
							 				</td>
							 				<td class="text14">
							 					&nbsp;<span title="henasf"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.invoicee.name"/>&nbsp;</span>
							 				</td>
							 				<td class="text14">
							 					<img onMouseOver="showPop('shipperCurr_info');" onMouseOut="hidePop('shipperCurr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<span title="hevals"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.invoicee.currencyCode"/>&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:0px; top:0px; width:250px" id="shipperCurr_info" class="popupWithInputText"  >
														<font class="text11">
									           			<b>Valuta</b>
									           			<div>
									           			<p>Valuta for fakturautstedelse - hentes fra kunderegister, kan overstyres. 
									           				Ved ulik NOK går fremmedvaluta inn i reskontro.
									           			</p>
									           			</div>
								           			</font>
												</span>
												</div>
							 				</td>
							 				<td class="text14">
												<img onMouseOver="showPop('shipperInvCode_info');" onMouseOut="hidePop('shipperInvCode_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<span title="hekdfs"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.invoicee.invoiceCode"/>&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:0px; top:0px; width:250px" id="shipperInvCode_info" class="popupWithInputText"  >
														<font class="text11">
									           			<b>Fakturakode</b>
									           			<div>
									           			<ul>
									           				<li><b>X</b>&nbsp;Fakturaforslag skal IKKE lages automatisk -  (OBS! Gjelder kun GEBYR-avtaledelen av evt. faktura.)</li>
									           				<li><b>E</b>&nbsp;Egen betyr at vareeiers (avsender/mottaker) avtale skal benyttes ikke fakturamottakers.</li>
									           				<li><b>B</b>&nbsp;Bytt betyr at "motpartens" avtale skal benyttes.</li>
									           			</ul>
									           			<p>Kodene E/B er selvsagt kun fornuftige dersom det er benyttet ulike kundenr for fakturamottaker og avsender/mottaker.
									           			</p>
									           			</div>
								           			</font>
												</span>
												</div>
							 					
							 				</td>
							 				
							 				<td class="text14">
							 					<img onMouseOver="showPop('shipperContract_info');" onMouseOut="hidePop('shipperContract_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<span title="heans"><spring:message code="systema.transportdisp.orders.form.update.label.shipper.invoicee.contractNumber"/>&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:0px; top:0px; width:250px" id="shipperContract_info" class="popupWithInputText"  >
														<font class="text11">
									           			<b>Avt.nr</b>
									           			<div>
									           			<p>Avtalenummer (i gebyravtalesystemet)=<b>0 - 9</b></br>
										           			Benyttes kun når en kunde på ellere helt identiske forhold har ulike GEBYR-avtaler (f.eks særskilte prosjekter??)
															(operatøren bør da ha veiledning om dette via info lagt inn på kundenotatblokk eller "likv.koder".)
									           			</p>
									           			</div>
								           			</font>
												</span>
												</div>
							 					
							 					
							 				</td>
						 				</tr>
						 				<tr>	
						 					<td class="text14" ><input type="text" class="inputTextMediumBlueUPPERCASE" name="heknsf" id="heknsf" size="10" maxlength="8" value="${model.record.heknsf}"></td>
										 	<td class="text14" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="henasf" id="henasf" size="31" maxlength="30"value="${model.record.henasf}"></td>
						 					<td class="text14" >
						 						<select class="inputTextMediumBlue" name="hevals" id="hevals">
							 						<option value="">-valuta-</option>
								 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
								 				  		<option value="${currency}"<c:if test="${model.record.hevals == currency || (empty model.record.hevals && currency=='NOK')}"> selected </c:if> >${currency}</option>
													</c:forEach>  
												</select>
						 					</td>
						 					<td class="text14" >
						 						<select class="inputTextMediumBlue" name="hekdfs" id="hekdfs">
							 						<option value="">-select-</option>
								 				  	<option value="B" <c:if test="${model.record.hekdfs == 'B'}"> selected </c:if> >B</option>
								 				  	<option value="E" <c:if test="${model.record.hekdfs == 'E'}"> selected </c:if> >E</option>
								 				  	<option value="X" <c:if test="${model.record.hekdfs == 'X'}"> selected </c:if> >X</option>
								 				  	  
												</select>
						 					</td>
						 					
						 					<td class="text14" ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueUPPERCASE" name="heans" id="heans" size="1" maxlength="1" value="${model.record.heans}"></td>
					 					</tr>
									</table>
									</td>				 				
					 			</tr>	
				 				<tr height="10"><td ></td></tr>
							 </table>
						 	</td>
						 	<td valign="top" >
				 			 <table style="width:99%" class="tableBorderWithRoundCornersGray" cellspacing="1" cellpadding="0">
					 			<tr height="10"><td ></td></tr>
						 		<tr>
					 				<td class="text14">
					 					&nbsp;<span title="heknk"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.id"/>&nbsp;</span>
					 					<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_customer.do?action=doInit&ctype=c','customerWin','top=300px,left=50px,height=800px,width=1000px,scrollbars=no,status=no,location=no')">
	 										<img id="imgConsigneeSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 									</a>	
					 				</td>
					 				<td class="text14">
					 					&nbsp;<span title="whenak"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.buyer"/>&nbsp;</span>
					 				</td>	
					 			</tr>
					 			<tr>	
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" name="heknk" id="heknk" size="10" maxlength="8" value="${model.record.heknk}"></td>
				 					<td class="text14" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="whenak" id="whenak" size="35" value="${model.record.whenak}"></td>
				 				</tr>
				 				<tr height="5"><td ></td></tr>
						 		<tr>
					 				<td class="text14">&nbsp;<font class="text16RedBold" >*</font><span title="henak"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.name"/></span></td>
					 				<td class="text14">&nbsp;<font class="text16RedBold" >*</font><span title="headk1"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.delivery.address"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="henak" id="henak" size="25" maxlength="30" value="${model.record.henak}"></td>
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASEMandatoryField" name="headk1" id="headk1" size="25" maxlength="30" value="${model.record.headk1}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text14">&nbsp;<span title="headk2"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.address2"/></span></td>
					 				<td class="text14">&nbsp;<span title="headk3"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.postalcode.city"/></span></td>
					 			</tr>
								<tr>	
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" name="headk2" id="headk2" size="25" maxlength="30" value="${model.record.headk2}"></td>
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" name="headk3" id="headk3" size="25" maxlength="30" value="${model.record.headk3}"></td>
				 				</tr>
				 				<tr height="15"><td ></td></tr>
				 				<tr>	
				 					<td class="text14"><img onMouseOver="showPop('herfk_info');" onMouseOut="hidePop('herfk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
					 					&nbsp;&nbsp;<span title="herfk"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.ref"/></span>
						 				<div class="text11" style="position: relative;" align="left">
										<span style="position:absolute; left:0px; top:0px; width:250px" id="herfk_info" class="popupWithInputText"  >
											<font class="text11">
							           			<b>Søk Mott.</b>
							           			<div>
							           			<p>Mottakers søkereferanse Fritt felt for utfylling. Begrep for senere søk/gjenfinning.</p>
							           			</div>
						           			</font>
										</span>
										</div>
				 					</td>
				 					<td class="text14">
				 						<img style="vertical-align:middle;" src="resources/images/loading.png" width="15px" height="15px" border="0" alt="load/unload">
				 						<span title="hesdl"><spring:message code="systema.transportdisp.orders.form.update.label.unload"/></span>
				 						<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_loadunloadplaces.do?action=doInit&caller=hesdl','postalcodeWin','top=300px,left=50px,height=600px,width=800px,scrollbars=no,status=no,location=no')">						 				
						 					<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
						 				</a>
				 					</td>
				 				</tr>
				 				<tr>	
				 					<td class="text14" >
				 						<input type="text" class="inputTextMediumBlueUPPERCASE" name="herfk" id="herfk" size="20" maxlength="35" value="${model.record.herfk}">
				 					</td>
				 					<td class="text14" >
						 				<input type="text" class="inputTextMediumBlue" name="hesdl" id="hesdl" size="21" maxlength="20" value="${model.record.hesdl}">
										
						 			</td>
				 				</tr>
				 				<tr height="5"><td ></td></tr>
				 				<tr>
					 				<td class="text14">&nbsp;<span title="wskcont"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.contactName"/></span></td>
					 				<td class="text14">&nbsp;<span title="wsktlf"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.telephone"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" name="wskcont" id="wskcont" size="25" maxlength="30" value="${model.record.wskcont}"></td>
				 					<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" name="wsktlf" id="wsktlf" size="25" maxlength="30" value="${model.record.wsktlf}"></td>
				 				</tr>
					 			<tr>	
					 				<td class="text14" colspan="2">&nbsp;<span title="wskmail"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.email"/></span></td>
					 			</tr>
					 			<tr>	
				 					<td class="text14" colspan="2"><input type="text" class="inputTextMediumBlue" name="wskmail" id="wskmail" size="50" maxlength="70" value="${model.record.wskmail}"></td>
				 				</tr>
				 				
				 				<tr height="8"><td ></td></tr>
								<tr>
				 					<td class="text14Bold">&nbsp;
				 						<img style="vertical-align: bottom;" width="24px" height="24px" src="resources/images/invoice.png" border="0" alt="invoice">
				 						<spring:message code="systema.transportdisp.orders.form.update.label.consignee.invoicee"/>
				 					</td>
								</tr>
				 																	 				
				 				<tr>
				 					<td colspan="2">
				 					<table class="tableBorderWithRoundCornersLightGray">
					 					<tr>
							 				<td class="text14">
							 					&nbsp;<span title="heknkf"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.invoicee.id"/>&nbsp;</span>
							 				</td>
							 				<td class="text14">
							 					&nbsp;<span title="henakf"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.invoicee.name"/>&nbsp;</span>
							 				</td>
							 				<td class="text14">
							 					<img onMouseOver="showPop('consigneeCurr_info');" onMouseOut="hidePop('consigneeCurr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<span title="hevalk"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.invoicee.currencyCode"/>&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:0px; top:0px; width:250px" id="consigneeCurr_info" class="popupWithInputText"  >
														<font class="text11">
									           			<b>Valuta</b>
									           			<div>
									           			<p>Valuta for fakturautstedelse - hentes fra kunderegister, kan overstyres. 
									           				Ved ulik NOK går fremmedvaluta inn i reskontro.
									           			</p>
									           			</div>
								           			</font>
												</span>
												</div>
							 				</td>
											<td class="text14">
							 					<img onMouseOver="showPop('consigneeInvCode_info');" onMouseOut="hidePop('consigneeInvCode_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<span title="hekdfk"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.invoicee.invoiceCode"/>&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:0px; top:0px; width:250px" id="consigneeInvCode_info" class="popupWithInputText"  >
														<font class="text11">
									           			<b>Fakturakode</b>
									           			<div>
									           			<ul>
									           				<li><b>X</b>&nbsp;Fakturaforslag skal IKKE lages automatisk -  (OBS! Gjelder kun GEBYR-avtaledelen av evt. faktura.)</li>
									           				<li><b>E</b>&nbsp;Egen betyr at vareeiers (avsender/mottaker) avtale skal benyttes ikke fakturamottakers.</li>
									           				<li><b>B</b>&nbsp;Bytt betyr at "motpartens" avtale skal benyttes.</li>
									           			</ul>
									           			<p>Kodene E/B er selvsagt kun fornuftige dersom det er benyttet ulike kundenr for fakturamottaker og avsender/mottaker.
									           			</p>
									           			</div>
								           			</font>
												</span>
												</div>
							 				</td>							 				
							 				<td class="text14">
							 					<img onMouseOver="showPop('consigneeContract_info');" onMouseOut="hidePop('consigneeContract_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<span title="heank"><spring:message code="systema.transportdisp.orders.form.update.label.consignee.invoicee.contractNumber"/>&nbsp;</span>
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:0px; top:0px; width:250px" id="consigneeContract_info" class="popupWithInputText"  >
														<font class="text11">
									           			<b>Avt.nr</b>
									           			<div>
									           			<p>Avtalenummer (i gebyravtalesystemet)=<b>0 - 9</b></br>
										           			Benyttes kun når en kunde på ellere helt identiske forhold har ulike GEBYR-avtaler (f.eks særskilte prosjekter??)
															(operatøren bør da ha veiledning om dette via info lagt inn på kundenotatblokk eller "likv.koder".)
									           			</p>
									           			</div>
								           			</font>
												</span>
												</div>
							 				</td>
						 				</tr>
						 				<tr>	
						 					<td class="text14" ><input type="text" class="inputTextMediumBlueUPPERCASE" name="heknkf" id="heknkf" size="10" maxlength="8" value="${model.record.heknkf}"></td>
										 	<td class="text14" ><input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="henakf" id="henakf" size="31" maxlength="30"value="${model.record.henakf}"></td>
						 					<td class="text14" >
						 						<select class="inputTextMediumBlue" name="hevalk" id="hevalk">
							 						<option value="">-valuta-</option>
								 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
								 				  		<option value="${currency}"<c:if test="${model.record.hevalk == currency || (empty model.record.hevalk && currency=='NOK')}"> selected </c:if> >${currency}</option>
													</c:forEach>  
												</select>
						 					</td>
						 					<td class="text14" >
						 						<select class="inputTextMediumBlue" name="hekdfk" id="hekdfk">
							 						<option value="">-select-</option>
								 				  	<option value="B" <c:if test="${model.record.hekdfk == 'B'}"> selected </c:if> >B</option>
								 				  	<option value="E" <c:if test="${model.record.hekdfk == 'E'}"> selected </c:if> >E</option>
								 				  	<option value="X" <c:if test="${model.record.hekdfk == 'X'}"> selected </c:if> >X</option>
								 				  	  
												</select>
						 					</td>
						 					<td class="text14" ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueUPPERCASE" name="heank" id="heank" size="1" maxlength="1" value="${model.record.heank}"></td>
					 					</tr>
									</table>
									</td>				 				
					 			</tr>								 
				 				<tr height="10"><td ></td></tr>
			 				</table>
						 	</td>
					 	</tr>
					 	<tr height="10"><td ></td></tr>
					 </table>
				</td>
			</tr>
			<tr>
            		<td colspan="4">
	        			<table border="0" style="width:99%;" align="left" class="tableBorderWithRoundCornersGray" cellspacing="0" cellpadding="0">
				 		<tr height="5"><td colspan="2" ></td></tr>
				 		<tr>
							<td valign="top" style="width:50%;border-right:1px solid;border-color:#FFFFFF;" >
								<table>
						 		<tr height="2"><td ></td></tr>
							 	<tr>	
						 			<td class="text14">
						 				<img onMouseOver="showPop('helka_info');" onMouseOut="hidePop('helka_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<font class="text14RedBold" >*</font><span title="helka/hesdf/OWNwppns1"><spring:message code="systema.transportdisp.orders.form.update.label.from"/>&nbsp;</span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:250px" id="helka_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Fra</b>
							           			<div>
							           			<p>Landkode + postnr / kode for "kundefraktens" frasted. Ved IKKE postnr.basert er det kun ett kodefelt (5 langt)
												</p>
							           			</div>
						           			</font>
										</span>
										</div>
				 					</td>
					 				<td class="text14">
						 				<select class="inputTextMediumBlueMandatoryField" name="helka" id="helka">
					 						<option value="">-landkode-</option>
						 				  	<c:forEach var="country" items="${model.countryCodeList}" >
						 				  		<option value="${country.zkod}"<c:if test="${model.record.helka == country.zkod}"> selected </c:if> >${country.zkod}</option>
											</c:forEach>  
										</select>
					 				</td>
						 			<td class="text14" nowrap>
						 				<input type="text" class="inputTextMediumBlueMandatoryField" name="hesdf" id="hesdf" size="6" maxlength="5" value="${model.record.hesdf}">
						 				<a tabindex=0 id="hesdfIdLink">
	 										<img id="imgFromSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
	 									</a>
					 				</td>
					 				<td class="text14" colspan="2">
						 				<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="OWNwppns1" id="OWNwppns1" size="20" maxlength="14" value="${model.record.wppns1}">
					 				</td>
					 				
					 			</tr>
					 			<tr>	
					 				<td class="text14" align="left">
					 					<img onMouseOver="showPop('helks_info');" onMouseOut="hidePop('helks_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				&nbsp;<span title="helks/hesdff/trstaf/OWNwppns3"><spring:message code="systema.transportdisp.orders.form.update.label.from.via"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:350px" id="helks_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Via</b>
							           			<div>
							           			<p>Landkode + postnr/kode for hovedfraktens frasted (teminal).<br> 
							           				Når via (1) er utfylt betyr det at det skal foretas en innhenting (fra "kundefraktens frasted" - til terminal). 
							           				En må da ved avslutning av oppdraget ta stilling til hvordan denne skal ordnes.<br> 
								           			Se feltet "Innhentingskode".
												</p>
												<b>Innhentingskode</b>
												<p>Når VIA 1 er fylt ut vil denne koden fortelle om/hvordan den aktuelle innhenting er effektuert.</p>
												<ul>
													<li><b>A</b> = Avventer</li>
													<li><b>R</b> = Rekvireres (ordnes uten å involvere andre avdelinger)</li>
													<li><b>F</b> = Ferdig rekvirert</li>
													<li><b>O</b> = Overført til annen avdeling (da vil også "Innh/utkj.oppd:" i bunnen av skjermbildet være utfylt.)</li>
												</ul>
							           			</div>
						           			</font>
										</span>
										</div>
					 				
					 				</td>
						 			<td class="text14" >
						 				<c:choose>
										<c:when test="${empty model.record.trstaf}">
						 				<select class="inputTextMediumBlue" name="helks" id="helks">
					 						<option value="">-landkode-</option>
						 				  	<c:forEach var="country" items="${model.countryCodeList}" >
						 				  		<option value="${country.zkod}"<c:if test="${model.record.helks == country.zkod}"> selected </c:if> >${country.zkod}</option>
											</c:forEach>  
										</select>
										</c:when>
										<c:otherwise>
											<input  type="text" readonly class="inputTextReadOnly" size="4" maxlength="2" name="helks" id="helks" value="${model.record.helks}">
										</c:otherwise>
										</c:choose>
						 			</td>
						 			<td class="text14" >
						 				<c:choose>
										<c:when test="${empty model.record.trstaf}">
							 				<input type="text" class="inputTextMediumBlue" name="hesdff" id="hesdff" size="5" maxlength="5" value="${model.record.hesdff}">
							 				<a tabindex=0 id="hesdffIdLink">
		 										<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
		 									</a>
	 									</c:when>
	 									<c:otherwise>
	 										<input type="text" readonly class="inputTextReadOnly" name="hesdff" id="hesdff" size="5" maxlength="5" value="${model.record.hesdff}">
	 									</c:otherwise>
	 									</c:choose>
						 			</td>
						 			<td class="text14" >
						 				<input type="text" readonly class="inputTextReadOnly" name="trstaf" id="trstaf" size="5" maxlength="1" value="${model.record.trstaf}">
						 				
						 			</td>
						 			<td class="text14" >
						 				<input readonly tabindex=-1 type="text" class="inputTextReadOnly" size="20" maxlength="14" name="OWNwppns3" id="OWNwppns3" value="${model.record.wppns3}">
						 				<c:choose>
						 				<c:when test="${empty model.record.trstaf}">
						 					<img tabindex="0" title="DUP/Rekvisisjon" id="viaFromDialogImg" style="vertical-align:middle; cursor:pointer;" width="14px" height="14px" src="resources/images/add.png" border="0" alt="Via ekstra">
						 				</c:when>
						 				<c:otherwise>
						 					<img tabindex="0" title="DUP/Rekvisisjon - Read-only" id="viaFromDialogImgReadOnly" style="vertical-align:middle; cursor:pointer;" width="12px" height="12px" src="resources/images/info4.png" border="0" alt="Via ekstra">
						 				</c:otherwise>
						 				</c:choose>
						 			</td>
						 			<c:if test="${not empty model.record.trstaf}">
							 			<td class="text14" >
							 				&nbsp;
							 				<label style="color:green;"><b>Innhent.</b>
							 				<c:choose>
								 				<c:when test= "${not empty model.record.ffavd}">
								 					<b>Dup</b>&nbsp;${model.record.ffavd}&nbsp;/&nbsp;${model.record.tropd1}&nbsp;Bel.&nbsp;${model.record.ffbel}
								 				</c:when>
								 				<c:otherwise>
								 					<b>Rekv.</b>&nbsp;${model.record.ffbnr}&nbsp;Trans.&nbsp;${model.record.fftran}&nbsp;Bel.&nbsp;${model.record.ffbel}
								 				</c:otherwise>
							 				</c:choose>
							 				</label>
							 				<c:if test="${not empty model.record.ffmerkn ||not empty model.record.vfmerkn }">
							 					<label style="color:green;">${model.record.ffmerkn}&nbsp;${model.record.vfmerkn}</label>
							 				</c:if>
							 			</td>
						 			</c:if>
					 			</tr>
					 			<tr>	
					 				<td class="text14" align="left">
					 					<img onMouseOver="showPop('helkk_info');" onMouseOut="hidePop('helkk_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				&nbsp;<span title="helkk/hesdvt/trstae/OWNwppns4"><spring:message code="systema.transportdisp.orders.form.update.label.to.via"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:350px" id="helkk_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Via</b>
							           			<div>
							           			<p>Landkode + postnr/kode for hovedfraktens frasted (teminal).<br> 
							           				Når via (2) er utfylt betyr det at det skal foretas en utkjøring (fra terminal til "kundefraktens tilsted").
							           				En må da ved avslutning av oppdraget ta stilling til hvordan denne skal ordnes.<br> 
								           			Se feltet "Utkjøringskode".
												</p>
												<b>Utkjøringskode</b>
												<p>Når VIA 2 er fylt ut vil denne koden fortelle om/hvordan den aktuelle utkjøring er effektuert.</p>
												<ul>
													<li><b>A</b> = Avventer</li>
													<li><b>R</b> = Rekvireres (ordnes uten å involvere andre avdelinger)</li>
													<li><b>F</b> = Ferdig rekvirert</li>
													<li><b>O</b> = Overført til annen avdeling (da vil også "Innh/utkj.oppd:" i bunnen av skjermbildet være utfylt.)</li>
												</ul>
							           			</div>
						           			</font>
										</span>
										</div>
					 				</td>
						 			<td class="text14" >
						 				<c:choose>
										<c:when test="${empty model.record.trstae}">
									 		<select class="inputTextMediumBlue" name="helkk" id="helkk">
						 						<option value="">-landkode-</option>
							 				  	<c:forEach var="country" items="${model.countryCodeList}" >
							 				  		<option value="${country.zkod}"<c:if test="${model.record.helkk == country.zkod}"> selected </c:if> >${country.zkod}</option>
												</c:forEach>  
											</select>
										</c:when>
										<c:otherwise>
											 <input  type="text" class="inputTextReadOnly" size="4" maxlength="2" name="helkk" id="helkk" value="${model.record.helkk}">
										</c:otherwise>
										</c:choose>
						 			</td>
						 			<td class="text14" >
						 				<c:choose>
										<c:when test="${empty model.record.trstae}">
									 		<input  type="text" class="inputTextMediumBlueUPPERCASE" size="5" maxlength="5" name="hesdvt" id="hesdvt" value="${model.record.hesdvt}">
							 				<a tabindex=0 id="hesdvtIdLink">
		 										<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
		 									</a>
						 				</c:when>
						 				<c:otherwise>
											 <input  type="text" class="inputTextReadOnly" size="5" maxlength="5" name="hesdvt" id="hesdvt" value="${model.record.hesdvt}">
										</c:otherwise>
										</c:choose>
						 			</td>
						 			<td class="text14" >
						 				<input  readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="trstae" id="trstae" value="${model.record.trstae}">
						 				
						 			</td>
						 			<td class="text14" >
						 				<input readonly tabindex=-1 type="text" class="inputTextReadOnly" size="20" maxlength="14" name="OWNwppns4" id="OWNwppns4" value="${model.record.wppns4}">
						 				<c:choose>
						 				<c:when test="${empty model.record.trstae}">
						 					<img tabindex="0" title="DUP/Rekvisisjon" id="viaFrom2DialogImg" style="vertical-align:middle; cursor:pointer;" width="14px" height="14px" src="resources/images/add.png" border="0" alt="Via ekstra">
						 				</c:when>
						 				<c:otherwise>
						 					<img tabindex="0" title="DUP/Rekvisisjon - Read-only" id="viaFrom2DialogImgReadOnly" style="vertical-align:middle; cursor:pointer;" width="12px" height="12px" src="resources/images/info4.png" border="0" alt="Via ekstra">
						 				</c:otherwise>
						 				</c:choose>
						 			</td>
						 			<c:if test="${not empty model.record.trstae}">
							 			<td class="text14" >
							 				&nbsp;&nbsp;&nbsp;
							 				<label style="color:green;"><b>Utkjør.</b>
							 				<c:choose>
								 				<c:when test= "${not empty model.record.vfavd}">
								 					&nbsp;<b>Dup</b>&nbsp;${model.record.vfavd}&nbsp;/&nbsp;${model.record.tropd2}&nbsp;Bel.&nbsp;${model.record.vfbel}
								 				</c:when>
								 				<c:otherwise>
								 					&nbsp;<b>Rekv.</b>&nbsp;${model.record.vfbnr}&nbsp;Trans.&nbsp;${model.record.vftran}&nbsp;Bel.&nbsp;${model.record.vfbel}
								 				</c:otherwise>
							 				</c:choose>
							 				</label>
							 			</td>
						 			</c:if>
					 			</tr>
					 			<tr>	
						 			<td class="text14">
						 				
						 				<img onMouseOver="showPop('hetri_info');" onMouseOut="hidePop('hetri_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<font class="text14RedBold" >*</font><span title="hetri/hesdt/OWNwppns2"><spring:message code="systema.transportdisp.orders.form.update.label.to"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:250px" id="hetri_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Til</b>
							           			<div>
							           			<p>Landkode + postnr / kode for "kundefraktens" tilsted. Ved IKKE postnr.basert er det kun ett kodefelt (5 langt).
												</p>
							           			</div>
						           			</font>
										</span>
										</div>
					 				</td>
					 				<td class="text14">
						 				<select class="inputTextMediumBlueMandatoryField" name="hetri" id="hetri">
					 						<option value="">-landkode-</option>
						 				  	<c:forEach var="country" items="${model.countryCodeList}" >
						 				  		<option value="${country.zkod}"<c:if test="${model.record.hetri == country.zkod}"> selected </c:if> >${country.zkod}</option>
											</c:forEach>  
										</select>
					 				</td>
						 			<td class="text14" nowrap>
						 				<input type="text" class="inputTextMediumBlueMandatoryField" name="hesdt" id="hesdt" size="6" maxlength="5" value="${model.record.hesdt}">
						 				<a tabindex=0 id="hesdtIdLink" >
	 										<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
	 									</a>
					 				</td>
									<td class="text14" colspan="2">
						 				<input readonly tabindex=-1 type="text" class="inputTextReadOnly" name="OWNwppns2" id="OWNwppns2" size="20" maxlength="14" value="${model.record.wppns2}">
					 				</td>
					 				
					 			</tr>					 			
							 	</table>
							</td>
							
							<td valign="top" align="left">
								<table >
						 		<tr>	
									<td nowrap class="text14">&nbsp;
										<img onMouseOver="showPop('frakt_info');" onMouseOut="hidePop('frakt_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="trfrak/trfrav/trfrab"><spring:message code="systema.transportdisp.orders.form.update.label.freight"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:400px" id="frakt_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Frakt</b>
							           			<div>
							           			<p>Kode/Valuta/beløp</p>
							           			<ul>
							           				<li><b>blankt</b>=Frakt er ennå ikke kalkulert</li>
							           			    <li><b>M</b>=Maskinellt beregnet frakt.<br>
							           			    		Ved kode blank og M vil systemet alltid rekalkulere frakten hver gang<br>
							           			    		en er inne på oppdragsbildet (Enter / avslutning).
			           			    				 	</li>
							           			    <li><b>A</b>=Avtalt fraktbeløp (meglet)</li>
							           			    <li><b>E</b>=Estimert fraktbeløp - kun info for senere prising</li>
							           			</ul>	
							           			<p>Valutakode - <b>NOK</b> foreslås (Dette er IKKE valutaen for fakturautskrift<br> 
							           				(se lenger opp på bildet) dersom valuta for beløpsangivelse er ulik <br>
							           				fakturavaluta skjer omregning.)
							           			</p>
							           			</div>
						           			</font>
										</span>
										</div>
									
									</td>
						 			<td class="text14">
						 				<select class="inputTextMediumBlue" name="trfrak" id="trfrak">
					 						<option value="">-select-</option>
					 						<option value="M" <c:if test="${model.record.trfrak == 'M'}"> selected </c:if> >M</option>
					 						<option value="A" <c:if test="${model.record.trfrak == 'A'}"> selected </c:if> >A</option>
					 						<option value="E" <c:if test="${model.record.trfrak == 'E'}"> selected </c:if> >E</option>
										</select>
					 				</td>									
						 			<td class="text14">
						 				<select class="inputTextMediumBlue" name="trfrav" id="trfrav">
					 						<option value="">-valuta-</option>
						 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
						 				  		<option value="${currency}"<c:if test="${model.record.trfrav == currency}"> selected </c:if> >${currency}</option>
											</c:forEach>  
										</select>
						 			</td>									
									<td nowrap class="text14">
										<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" size="15" maxlength="14" name="trfrab" id="trfrab" value="${model.record.trfrab}">
										<input readonly tabindex=-1 type="text" class="inputTextReadOnly" size="1" name="xkda" id="xkda" value="${model.record.xkda}">
									</td>
									<td nowrap class="text14">&nbsp;
							 			<img onMouseOver="showPop('moms_info');" onMouseOut="hidePop('moms_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="trmva"><spring:message code="systema.transportdisp.orders.form.update.label.vatCode"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:350px" id="moms_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Momskode</b>
							           			<div>
							           			<p>Dersom en ikke selv setter den vil systemet sette <br>
							           				momskode etter landkode på fra/tilsted. <br>
							           				Dersom begge er NO settes <b>J</b> i momskode, ellers <b>N</b>.
												</p>
							           			</div>
						           			</font>
										</span>
										</div>
									</td>
										
						 			<td class="text14">
						 				<input type="text" class="inputTextMediumBlueUPPERCASE" size="1" name="trmva" id="trmva" value="${model.record.trmva}">
						 			</td>									
						 			<td nowrap class="text14">&nbsp;
						 				<img onMouseOver="showPop('prodkod_info');" onMouseOut="hidePop('prodkod_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="hekdpl"><spring:message code="systema.transportdisp.orders.form.update.label.prodCode"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:-50px; top:10px; width:450px" id="prodkod_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Produktkode</b>
							           			<div>
							           			<p>
							           			Hovedhensikten med bruk av produktkode er at en, under ellers like vilkår,<br> 
							           			skal kunne skille fraktpris. Blank i kode er typisk normalfrakt. <br>
							           			Om en fyller ut kode i oppdragsbildet, vil systemet KUN lete <br>
							           			etter frakt rate for denne koden.F.eks  Kode <b>E</b>= Ekspress, og da vil en ha en annen<br>
							           			pris til tross for at alle andre vilkår (strekning/frankatur mm) er identiske.<br> 
							           			(kodene/betydningene er egendefinerte, her er det bare fantasien som setter grenser.) 
							           			</p>
							           			</div>
						           			</font>
											</span>
										</div>
						 			</td>									
						 			<td class="text11"><input type="text" class="inputTextMediumBlueUPPERCASE" size="1" maxlength="1" name="hekdpl" id="hekdpl" value="${model.record.hekdpl}"></td>									
						 		</tr>
						 		<tr>	
						 			<td nowrap class="text14">&nbsp;
							 			<img onMouseOver="showPop('die_info');" onMouseOut="hidePop('die_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="hevalt"><spring:message code="systema.transportdisp.orders.form.update.label.dieVal"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:350px" id="die_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Prosentuelt frakttillegg. (Oljetillegg / Valutatillegg...)</b>
							           			<div>
							           			<p>Tast inn gyldig varekode (gebyrkode) samt prosentsats.</p>
							           			<p>
												 	F.eks OIL 7,5 vil automatisk legge opp en seperat linje<br>
												 	på fakturaforslaget med 7,5 % av fraktprisen, varekode OIL.<br>
												</p>
							           			</div>
						           			</font>
										</span>
										</div>
						 			</td>									
						 			<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" size="3" name="hevalt" id="hevalt" value="${model.record.hevalt}">&nbsp;</td>									
						 			<td nowrap class="text14">&nbsp;
						 				<img onMouseOver="showPop('sats_info');" onMouseOut="hidePop('sats_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="hevalp"><spring:message code="systema.transportdisp.orders.form.update.label.dieVal.sats"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:350px" id="sats_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Sats(%): Prosentuelt frakttillegg. (Oljetillegg / Valutatillegg...)</b>
							           			<div>
							           			<p>Tast inn gyldig varekode (gebyrkode) samt prosentsats.</p>
							           			<p>
												 	F.eks OIL 7,5 vil automatisk legge opp en seperat linje <br>
												 	på fakturaforslaget med 7,5 % av fraktprisen, varekode OIL.<br>
												</p>
							           			</div>
						           			</font>
										</span>
										</div>
						 			</td>									
																 			
						 			<td class="text14">
						 				<fmt:parseNumber scope="request" var="tmpnumber" type="number" value="${model.record.hevalp}" />
						 				<c:set scope="request" var="formattedPercentHevalp"><fmt:formatNumber type="number" minFractionDigits="0" maxFractionDigits="1" value="${tmpnumber * 100}" /></c:set>
						 				<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlueUPPERCASE" size="5" maxlength="4" name="hevalp" id="hevalp" value="${formattedPercentHevalp}">&nbsp;
						 				
						 			</td>									
						 			<td nowrap class="text14">&nbsp;
						 				<img onMouseOver="showPop('tilleg_info');" onMouseOut="hidePop('tilleg_info');" style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="hestn1...hestn5"><spring:message code="systema.transportdisp.orders.form.update.label.tillegg"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:450px" id="tilleg_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Tillegg</b>
							           			<div>
							           			<p>Oppgi inntil 5 en/bokstavskoder for å styre oppdragsrelaterte tillegg.<br>
							           				Kan benyttes  som et tillegg til oppdragstype
							           				<ul>
							           				 	<li><b>B</b> = Breddetillegg</li>                                                                          
									   					<li><b>H</b> = Høydetillegg</li>                                    
													    <li><b>L</b> = Lengdetillegg</li>
													    <li><b>F</b> = Følgebil</li> 
							           				</ul>
							           			</p>
							           			<p>
												Vil medføre ekstra gebyrlinjer (forutsetter at koden er definert som tillegg<br>
												 i kundens gebyravtale). Avhengig av oppsett kan systemet autoforeslå koder<br>
												 for f.eks, ekspress / oversize  /ekstra sjåfør / lengde/høyde følgebil mm.
												</p>
							           			</div>
						           			</font>
										</span>
										</div>
						 			</td>									
						 			<td class="text14" colspan="3" nowrap>
						 				<input type="text" class="inputTextMediumBlueUPPERCASE tableCell" size="1" maxlength="1" name="hestn1" id="hestn1" value="${model.record.hestn1}">
						 				<input type="text" class="inputTextMediumBlueUPPERCASE tableCell" size="1" maxlength="1" name="hestn2" id="hestn2" value="${model.record.hestn2}">
						 				<input type="text" class="inputTextMediumBlueUPPERCASE tableCell" size="1" maxlength="1" name="hestn3" id="hestn3" value="${model.record.hestn3}">
						 				<input type="text" class="inputTextMediumBlueUPPERCASE tableCell" size="1" maxlength="1" name="hestn4" id="hestn4" value="${model.record.hestn4}">
						 				<input type="text" class="inputTextMediumBlueUPPERCASE tableCell" size="1" maxlength="1" name="hestn5" id="hestn5" value="${model.record.hestn5}">
						 				&nbsp;&nbsp;&nbsp;<img onMouseOver="showPop('p_info');" onMouseOut="hidePop('p_info');" style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="hestn6"><spring:message code="systema.transportdisp.orders.form.update.label.p"/></span> 
						 				<input type="checkbox" id="hestn6" name="hestn6" value="P" <c:if test="${model.record.hestn6 == 'P'}"> checked </c:if>>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:350px" id="p_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>P (Protect)</b>
							           			<div>
							           			<p>Protect tilleggskodene ved å hake av i boksen<br/> 
													dersom du ønsker å sette andre koder en autoforeslått.<br/> 
													(inne i 'grønskjerm' blir dette en P bak Tilleggskodene). 
												</p>
							           			</div>
						           			</font>
										</span>
										</div>
										
						 			</td>									
						 		</tr>
						 		<tr>	
						 			<td class="text14" >&nbsp;
										<img onMouseOver="showPop('godsnr_info');" onMouseOut="hidePop('godsnr_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="hegn"><spring:message code="systema.transportdisp.orders.form.update.label.godsnr"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:350px" id="godsnr_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Godsnr</b>
							           			<div>
							           			<p>Godsnummer ved import, overføres til fortollingsmodulen</p>
							           			</div>
						           			</font>
										</span>
										</div>
									</td>
						 			<td class="text14" colspan="3"><input type="text" class="inputTextMediumBlueUPPERCASE" size="30" maxlength="15" name="hegn" id="hegn" value="${model.record.hegn}"></td>									
									<td class="text14">&nbsp;
										<img onMouseOver="showPop('pos1_info');" onMouseOut="hidePop('pos1_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="hepos1"><spring:message code="systema.transportdisp.orders.form.update.label.pos1"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:350px" id="pos1_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Posisjon</b>
							           			<div>
							           			<p>Hovedposisjon + evt. underposisjon innen det aktuelle<br>
							           				godsregistreringsnummer</p>
							           			</div>
						           			</font>
										</span>
										</div>
									</td>
						 			<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" size="7" name="hepos1" id="hepos1" value="${model.record.hepos1}"></td>									
									<td class="text14">&nbsp;
										<img onMouseOver="showPop('pos1_info');" onMouseOut="hidePop('pos1_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="todo"><spring:message code="systema.transportdisp.orders.form.update.label.pos2"/></span>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px; width:350px" id="pos1_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Posisjon</b>
							           			<div>
							           			<p>Hovedposisjon + evt. underposisjon innen det aktuelle <br>
							           				godsregistreringsnummer</p>
							           			</div>
						           			</font>
										</span>
										</div>
									
									</td>
						 			<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" size="4" name="hepos2" id="hepos2" value="${model.record.hepos2}">&nbsp;&nbsp;</td>
								</tr>
								
							</table>
							</td>
						</tr>

						<tr height="8"><td colspan="2" ></td></tr>
						<tr height="1"><td colspan="2" style="border-bottom:1px solid;border-color:#FFFFFF;" class="text"></td></tr>
						<tr height="8"><td colspan="2" ></td></tr>
						<tr>
							<td colspan="2">
							<table style="width:99%;" >	
								<tr>
									<td>
									<table>
						 			<tr>		
										<td nowrap class="text14">&nbsp;
											<img onMouseOver="showPop('arbord_info');" onMouseOut="hidePop('arbord_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="hepk3/hepk5"><spring:message code="systema.transportdisp.orders.form.update.label.workorder"/></span>
							 				<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute; left:0px; top:0px; width:480px" id="arbord_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>Arbeidsordre</b>
								           			<div>
								           			<p>En J her betyr at arbeidsordre skal skrives ut. <br>
								           				OBS! Det er 2 koder - den første for Intern arb. ordre (fakturaordre), <br>
								           				den andre for Ekstern arb.ordre (kjøreordre).<br>
														Ordrene skrives ikke ut før oppdraget er på tur.<br>
														(Standarkoder hentes fra oppdr.type: Intern fra "KK" (KjøreKvitt/ank.melding).
													</p>
														
													<p>Ekstern fra <b>FI</b>(Fly Innland).
													</p>
													
													<p>Den interne ordren er det få som bruker.<br> 
														Derimot er det mange som ved import foretrekker å skrive ut<br>
														Dokumentet "Kjørekvittering / Ankomstmelding" som benyttes<br>
														i spedisjonsmodulene. Dette bruker samme koden som intern arbeidsordre!!
													</p>
								           			</div>
							           			</font>
											</span>
											</div>
										</td>
							 			<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" size="1" maxlength="1" name="hepk3" id="hepk3" value="${model.record.hepk3}"></td>
							 			<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" size="1" maxlength="1" name="hepk5" id="hepk5" value="${model.record.hepk5}"></td>

							 			<td nowrap class="text14">&nbsp;
							 				<img onMouseOver="showPop('tolldek_info');" onMouseOut="hidePop('tolldek_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="hepk4/hepk6"><spring:message code="systema.transportdisp.orders.form.update.label.tolldekl"/></span>
							 				<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute; left:0px; top:0px; width:380px" id="tolldek_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>Tolldeklarasjon</b>
								           			<div>
								           			<p>Tolldeklarasjon <b>J</b>/<b>N</b>. 1.felt=<b>import</b>, 2.felt=<b>eksport</b>.<br>
														Om man skal lage import eller eksportdeklarasjon senere påvirkes <br>
														ikke av valgene her, men av fra – til verdiene øverst i bildet.<br>
														Når en tolldeklarasjon er laget, endres koden i respektive felt til <b>P</b>,<br>
														etter om det er lagd import eller eksportdeklarasjon.<br>
														Dermed kan man, ved å bruke <b>J</b> for planlagte fortollinger, <br>
														få oversikt over fortollinger som ikke er utført.
													</p>
								           			</div>
							           			</font>
											</span>
											</div>
							 			</td>
							 			<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" size="1" maxlength="1" name="hepk4" id="hepk4" value="${model.record.hepk4}"></td>
							 			<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" size="1" maxlength="1" name="hepk6" id="hepk6" value="${model.record.hepk6}"></td>
							 			<td class="text14">&nbsp;
							 				<span title="hepk7"><spring:message code="systema.transportdisp.orders.form.update.label.billOfLading"/></span></td>
							 			<td class="text14">
							 				<%-- <input type="text" class="inputTextMediumBlueUPPERCASE" size="1" maxlength="1" name="hepk7" id="hepk7" value="${model.record.hepk7}"> --%>
							 				<input type="checkbox" id="hepk7" name="hepk7" value="C" <c:if test="${model.record.hepk7 == 'C'}"> checked </c:if>>
								 			<input readonly type="text" class="inputText11ReadOnly" size="1" maxlength="1" name="hepk7RO" id="hepk7RO" value="${model.record.hepk7}">
								 				
							 			</td>
							 			
							 			<td style="width:50px">&nbsp;</td>
							 			<%-------------------------- --%>
							 			<%-- START Fraktbrev section --%>
							 			<%-- ONLY for UPDATES        --%>
							 			<%-------------------------- --%>
							 			<c:if test="${not empty model.record.heopd}">
								 			<td nowrap class="text14">&nbsp;
								 				<img onMouseOver="showPop('fraktbrev_info');" onMouseOut="hidePop('fraktbrev_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								 				<span title="hepk1"><b><spring:message code="systema.transportdisp.orders.form.update.label.fraktbrev"/></b></span>
								 				<div class="text11" style="position: relative;" align="left">
													<span style="position:absolute; left:0px; top:0px; width:450px" id="fraktbrev_info" class="popupWithInputText"  >
														<font class="text11">
									           			<b>Fraktbrev/tollpass</b>
									           			<div>
									           			<p>Tast en <b>J</b> her for å lage bilde (trenger ikke å gå innom det) for fraktbrev / tollpass.<br>
									           			 Dersom det lille (2 tegns) feltet bak koden fylles ut med gyldig<br>
															tollsted er det et tollpass som opprettes (men felles bilde).<br>
															Dersom en taster F4-Videre kommer en innom bildet og kan endre /splitte fraktbrevet.
														</p>
									           			</div>
								           			</font>
												</span>
												</div>
								 			</td>
								 			<td class="text14">
								 				<a tabindex=-1 id="fraktbrevRenderPdfLink" target="_blank" href="transportdisp_mainorderlist_renderFraktbrev.do?user=${user.user}&wsavd=${model.record.heavd}&wsopd=${model.record.heopd}&wstoll=${model.record.dftoll}">
			    									<img id="imgFraktbrevPdfStdAlone" title="Fraktbr.PDF" style="vertical-align:middle;" src="resources/images/pdf.png" width="20" height="20" border="0" alt="Fraktbr. PDF">
												</a>
											</td>
								 			<td nowrap class="text14">
								 				<input type="checkbox" id="hepk1" name="hepk1" value="J" <c:if test="${model.record.hepk1 == 'J'}"> checked </c:if>>
								 				<input readonly type="text" class="inputText11ReadOnly" size="1" maxlength="1" name="hepk1RO" id="hepk1RO" value="${model.record.hepk1}">
								 				&nbsp;&nbsp;&nbsp;
								 			</td>
							 			</c:if>
							 			
							 			<td nowrap class="text14"><span title="dftoll"><spring:message code="systema.transportdisp.orders.form.update.label.fraktbrev.tollpass"/></span>
							 				<a tabindex=0 id="dftollIdLink" >
	 											<img id="imgToSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" width="13px" height="13px" border="0" alt="search">
	 										</a>
							 			</td>
							 			<td class="text14">
							 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" size="5" maxlength="4" name="dftoll" id="dftoll" value="${model.record.dftoll}">
							 			</td>
							 			<td class="text14"><span title="dfkdme"><spring:message code="systema.transportdisp.orders.form.update.label.fraktbrev.merkelapp"/></span></td>
							 			<td nowrap class="text14">
							 				<input type="checkbox" id="dfkdme" name="dfkdme" value="S" <c:if test="${model.record.dfkdme == 'S'}"> checked </c:if>>
							 				<input readonly type="text" class="inputText11ReadOnly" size="1" maxlength="1" name="dfkdmeRO" id="dfkdmeRO" value="${model.record.dfkdme}">
						 				</td>
							 			<td nowrap class="text14" align="left"><span title="dftran"><spring:message code="systema.transportdisp.orders.form.update.label.fraktbrev.transp"/></span>
							 				<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_transpcarrier.do?action=doInit','transpcarrierWin','top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
		 										<img id="imgTruckersNrSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="12px" height="12px" border="0" alt="search">
		 									</a>
							 			</td>
							            <td class="text14">
							 				<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlue" size="8" maxlength="8" name="dftran" id="dftran" value="${model.record.dftran}">
						 				</td>
							 			<td style="width:50px">&nbsp;</td>
							 			<%------------------------ --%>
							 			<%-- END Fraktbrev section --%>
							 			<%------------------------ --%>
							 			
							 			<%-- OBSOLETE (by 21-Oct-2015)
							 			<td nowrap class="text14">&nbsp;
							 				<img onMouseOver="showPop('ek_info');" onMouseOut="hidePop('ek_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="w2kde"><spring:message code="systema.transportdisp.orders.form.update.label.ek"/></span>
							 				<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute; left:0px; top:0px; width:450px" id="ek_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>Ek</b>
								           			<div>
								           			<p>Speditøretterkrav - overføres til fraktbrevsbildet.<br>
								           				Tilgjengelig her kun for å slippe å gå innom tillegsbildet<br>
														<b>X</b>=Alle fakturalinjer mot kjøper (mottakerside) skrives/summeres på fraktbrevet.
													</p>
								           			</div>
							           			</font>
											</span>
											</div>
							 			</td>
							 			<td class="text11"><input type="text" class="inputTextMediumBlueUPPERCASE" name="w2kde" id="w2kde" size="1" maxlength="1" value="${model.record.w2kde}"></td>	
							 			 --%>
							 			<td nowrap class="text14">&nbsp;
							 				<img onMouseOver="showPop('forpass_info');" onMouseOut="hidePop('forpass_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="hepk2/hentvs"><spring:message code="systema.transportdisp.orders.form.update.label.forpassing"/></span>
							 				<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute; left:0px; top:0px; width:450px" id="forpass_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>Forpassing</b>
								           			<div>
								           			<p>Forpassing på SAD-blankett.<br>
														Når en setter <b>J</b> her + antall vareposter i feltet bak,<br> 
														er det tilstrekkelig informasjon i bildet til å utstede "Forpassing på SAD-blankett"<br> 
														(Bestilles via menyen TRUCKI ("Bil import"))
													</p>
								           			</div>
							           			</font>
											</span>
											</div>
							 			</td>
							 			<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" size="1" maxlength="1" name="hepk2" id="hepk2" value="${model.record.hepk2}"></td>
							 			<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" size="3" maxlength="1" name="hentvs" id="hentvs" value="${model.record.hentvs}"></td>	
							 			<td nowrap class="text14">&nbsp;
								 			<img onMouseOver="showPop('fly_info');" onMouseOut="hidePop('fly_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="hepk8"><spring:message code="systema.transportdisp.orders.form.update.label.fly"/></span>
							 				<div class="text11" style="position: relative;" align="left">
												<span style="position:absolute; left:0px; top:0px; width:450px" id="fly_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>Fly</b>
								           			<div>
								           			<p>Kode J eller S</p>
								           			<ul>
								           				<li><b>J</b> for flyfraktbrev.</li>
								           			    <li><b>S</b> for spredning (flere fraktbrev) med fly.</li>
								           			</ul>	
								           			</div>
							           			</font>
											</span>
											</div>	
							 			</td>
							 			<td class="text14"><input type="text" class="inputTextMediumBlueUPPERCASE" name="hepk8" id="hepk8" size="1" maxlength="1" value="${model.record.hepk8}"></td>
						 			</tr>
						 			</table>
						 			</td>
						 		</tr>
					 		</table>
					 		</td>
				 		</tr>
						<tr height="5"><td ></td></tr>	
	 				</table>
            		</td>
            </tr>
            <tr height="2"><td></td></tr>
            
            <%-- only with update order mode --%>
            <c:if test="${not empty model.record.heopd}">
            	<tr>
            		<td>
	        			<table width="99%" align="left" class="tableBorderWithRoundCornersGray" border="0" cellspacing="0" cellpadding="0">
	        			<input type="hidden" name="updateMode" id="updateMode" value="1" >   
							 		
				 		<tr height="6"><td></td></tr>
				 		<c:if test="${not empty model.record.fraktbrevList}" >
				 		<%-- UPDATE LINEs SECTION --%>
				 		<tr>
							<td colspan="2" valign="top" style="width:100%;" >
								<table id="tblItemLines" class="display compact cell-border" >
						 		<thead>
						 		<tr class="tableHeaderField" >
						 			<th width="2%" align="left" valign="bottom" class="text14">&nbsp;</th>
							 		<th width="2%" align="left" valign="bottom" class="text14"><span title="fvlinr">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.linenr"/></span></th>
							 		<th width="5%" align="left" valign="bottom" class="text14"><span title="fmmrk1/hegm1(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.marks"/></span></th>
						 			<th width="5%" align="right" valign="bottom" class="text14"><span title="fvant/hent(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.antal"/>&nbsp;</span></th>
						 			<th width="7%" align="left" valign="bottom" class="text14"><span title="fvpakn">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.forpak"/></span></th>
						 			<th width="7%" align="left" valign="bottom" class="text14"><span title="fvvt/hevs1(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.goodsDesc"/></span></th>
						 			<th width="7%" align="right" valign="bottom" class="text14"><span title="fvvkt/hevkt(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.weight"/>&nbsp;</span></th>
						 			<th width="5%" align="right" valign="bottom" class="text14"><span title="fvlen">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.length"/>&nbsp;</span></th>
						 			<th width="5%" align="right" valign="bottom" class="text14"><span title="fvbrd">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.width"/>&nbsp;</span></th>
						 			<th width="5%" align="right" valign="bottom" class="text14"><span title="fvhoy">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.height"/>&nbsp;</span></th>
						 			<th width="5%" align="right" valign="bottom" class="text14"><span title="fvvol/hem3(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.m3"/>&nbsp;</span></th>
						 			<th width="5%" align="right" valign="bottom" class="text14"><span title="fvlm/helm(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.lm.fa"/>&nbsp;</span></th>
						 			<th width="5%" align="right" valign="bottom" class="text14"><span title="fvlm2/helmla(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.lm.la"/>&nbsp;</span></th>
						 			<th width="6%" align="left" valign="bottom" class="text12"><span title="ffunnr">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unNr"/>&nbsp;</span></th>
						 			<th width="4%" align="right" valign="bottom" class="text12"><span title="ffembg">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.emg"/>&nbsp;</span></th>
						 			<th width="4%" align="right" valign="bottom" class="text12"><span title="ffindx">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.emg.index"/>&nbsp;</span></th>
						 			
						 			<th width="2%"  align="right" valign="bottom" class="text12"><span title="ffantk">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.ant2"/>&nbsp;</span></th>
						 			<th width="2%" align="right" valign="bottom" class="text12"><span title="ffante">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.mengd"/>&nbsp;</span></th>
						 			<th width="2%" align="right" valign="bottom" class="text12"><span title="ffenh">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unit"/>&nbsp;</span></th>
						 			<th width="2%" align="right" valign="bottom" class="text12"><span title="ffpoen/hepoen(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.adr"/>&nbsp;</span></th>
						 			<th width="2%" align="center" valign="bottom" class="text14"><span title=""><spring:message code="systema.transportdisp.orders.form.detail.update.label.remove"/></span></th>										 			
						 		</tr>
						 		</thead>
						 		<tbody>
						 		
						 		<c:forEach items="${model.record.fraktbrevList}" var="fraktbrevRecord" varStatus="counter">
						 			<c:if test="${not empty fraktbrevRecord.fvlinr}">
						 				<c:set var="upperCurrentItemlineNr" scope="request" value="${fraktbrevRecord.fvlinr}"/>
						 				<c:set var = "lineCounter" scope = "request" value = "${counter.count}"/>
						 			</c:if>
						 			<input type="hidden" name="lineNr_${counter.count}" id="lineNr_${counter.count}" value="${counter.count}" >   
							 		<input type="hidden" name="fvlinr_${counter.count}" id="fvlinr_${counter.count}" value="${fraktbrevRecord.fvlinr}" >
							 		
							 		<tr class="tableRow" >
							 			<td title="Endre" width="2%" align="center" class="tableCell" style="cursor:pointer;" id="fvlinr_${fraktbrevRecord.fvlinr}@fbn_${fraktbrevRecord.fvfbnr}@counter_${counter.count}">
							 				<img style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="edit">
							 			</td>
							 			<td width="2%" align="center" class="tableCell" >${fraktbrevRecord.fvlinr}</td>
					               		<td width="5%" align="left" class="tableCell" >${fraktbrevRecord.fmmrk1}</td>
						 				<td width="5%" align="right" class="tableCell" >${fraktbrevRecord.fvant}</td>
						 				<td width="7%" align="left" class="tableCell" >${fraktbrevRecord.fvpakn}</td>
						 				<td width="7%" align="left" class="tableCell" >${fraktbrevRecord.fvvt}</td>
						 				<td width="7%" align="right" class="tableCell" >${fraktbrevRecord.fvvkt}</td>
							 				
						 				<td width="5%" align="right" class="tableCell" >${fraktbrevRecord.fvlen}</td>
						 				<td width="5%" align="right" class="tableCell" >${fraktbrevRecord.fvbrd}</td>
						 				<td width="5%" align="right" class="tableCell" >${fraktbrevRecord.fvhoy}</td>
						 				<td width="5%" align="right" class="tableCell" >${fraktbrevRecord.fvvol}</td>
						 				<td width="5%" align="right" class="tableCell" >${fraktbrevRecord.fvlm}</td>
						 				<td width="5%" align="right" class="tableCell" >${fraktbrevRecord.fvlm2}</td>
						 				<td width="6%" align="left" class="tableCellDangerousGoods" >${fraktbrevRecord.ffunnr}</td>
						 				<td width="4%" align="right" class="tableCellDangerousGoods" >${fraktbrevRecord.ffembg}</td>
						 				<td width="4%" align="right" class="tableCellDangerousGoods" >${fraktbrevRecord.ffindx}</td>
						 				
						 				<td width="2%" align="right" class="tableCellDangerousGoods" >${fraktbrevRecord.ffantk}</td>
						 				<td width="2%" align="right" class="tableCellDangerousGoods" >${fraktbrevRecord.ffante}</td>
						 				<td width="2%" align="right" class="tableCellDangerousGoods">${fraktbrevRecord.ffenh}</td>
						 				<td width="2%" align="right" class="tableCellDangerousGoods" >${fraktbrevRecord.ffpoen}</td>
					 					<td width="2%" align="center" class="tableCell" >
						               		<c:if test="${not empty fraktbrevRecord.fvlinr}">
						               			<%--
						               			<c:if test="${ model.record.singleLine == 'N' }">
							               			<a id="deleteLine_${counter.count}" onClick="deleteOrderLine(this);">
							               				<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
								               		</a>
							               		</c:if>
							               		--%>
							               		<a id="deleteLine_${counter.count}" onClick="deleteOrderLine(this);">
						               				<img src="resources/images/delete.gif" border="0" alt="remove">
							               		</a>
						               		</c:if> 	
							 			</td>
						 			</tr>
						 			
					 			</c:forEach>
					 			</tbody>
					 			<input type="hidden" name="lineCounterNum" id="lineCounterNum" value="${lineCounter}" >   
							 		
				 				<%--
			 					<tr class="tableHeaderField" style="background-color: #F2F5EF ;border-width: 0px 0px 0px 0px;">	
									<td width="2%" align="left" class="tableCell">&nbsp;</td>
									<td width="2%" align="left" class="tableCell">
										<%--this hidden field is crucial for ADD NEW line functionality. We will send the new line = upperCurrentItemlineNr + 1 
										<input type="hidden" id="upperCurrentItemlineNr" name="upperCurrentItemlineNr" value="${upperCurrentItemlineNr}">
										<input type="hidden" id="hent" name="hent" value="${model.record.hent}">
										<input type="hidden" id="hevkt" name="hevkt" value="${model.record.hevkt}">
										<input type="hidden" id="hem3" name="hem3" value="${model.record.hem3}">
										<input type="hidden" id="helm" name="helm" value="${model.record.helm}">
										<input type="hidden" id="helmla" name="helmla" value="${model.record.helmla}">
										
										<b>TOT</b>
									</td>
									
									<td  width="7%" align="left" class="tableCell">
										<input class="inputTextMediumBlue" style="font-weight:bold;"type="text" name="hegm1" id="hegm1" value="${model.record.hegm1}"> 
									</td>
						
						 			<td width="5%" align="right" class="tableCell"><b>${model.record.hent}</b></td>
						 			<td width="7%" align="left" class="tableCell">&nbsp;</td>
						 			<td width="7%" align="left" class="tableCell">
						 				<input class="inputTextMediumBlue" style="font-weight:bold;" type="text" name="hevs1" id="hevs1" value="${model.record.hevs1}">
						 			</td>
						 			<td width="7%" align="right" class="tableCell"><b>${model.record.hevkt}</b></td>
						 			<td width="5%" align="left" class="tableCell">&nbsp;</td>
						 			<td width="5%" align="left" class="tableCell">&nbsp;</td>
						 			<td width="5%" align="left" class="tableCell">&nbsp;</td>
						 			<td width="5%" align="right" class="tableCell"><span title="hem3"><b>${fn:replace(model.record.hem3,'.',',')}</b></span></td>
						 			<td width="5%" align="right" class="tableCell"><span title="helm"><b>${fn:replace(model.record.helm,'.',',')}</b></span></td>
						 			<td width="5%" align="right" class="tableCell"><span title="helmla"><b>${fn:replace(model.record.helmla,'.',',')}</b></span></td>
						 			<td width="6%" align="right" class="tableCellDangerousGoods">&nbsp;</td>
						 			<td width="4%" align="right" class="tableCellDangerousGoods">&nbsp;</td>
						 			<td width="4%" align="right" class="tableCellDangerousGoods">&nbsp;</td>
						 			<td width="2%" align="right" class="tableCellDangerousGoods">&nbsp;</td>
						 			<td width="2%" align="right" class="tableCellDangerousGoods">&nbsp;</td>
						 			<td width="2%" align="right" class="tableCellDangerousGoods">&nbsp;</td>
						 			<td width="2%" align="right" class="tableCellDangerousGoods"><span title="hepoen"><b>${model.record.hepoen}</b></span></td>
						 			
						 			<td width="2%" align="left" class="tableCell">${model.record.hestl4}</td>
						 			<%--
						 				<img onMouseOver="showPop('psum_info');" onMouseOut="hidePop('psum_info');" style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
						 				<span title="hestl4">P</span>
						 				<input type="checkbox" id="hestl4" name="hestl4" value="P" <c:if test="${model.record.hestl4 == 'P'}"> checked </c:if>>
						 				<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:-50px; top:10px; width:200" id="psum_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>P (Protect)</b>
							           			<div>
							           			<p> Protect sumlinje<br/> 
												</p>
							           			</div>
						           			</font>
											</span>
										</div>
						 			</td>
						 			  
						 		</tr>
					 			 --%>
					 			 
					 			 
						 		</table>
						 		
							</td>
						</tr>
						
						</c:if>
						
						
						
						<tr height="1"><td ></td></tr>
						
						 
						
				 			<tr height="10"><td ></td></tr>
					 		<%-- Create new Line / Update Line SECTION --%> 
					 		<tr>
								<td colspan="2" style="padding: 3px;">
									<table width="100%" border="0" style="background-color:#778899">
									<tr class="tableHeaderField" >	
										<td align="center" valign="bottom" class="tableHeaderFieldFirst"><span title="linNr"><spring:message code="systema.transportdisp.orders.form.detail.update.label.linenr"/></span></td>
							 			<td align="center" valign="bottom" class="tableHeaderField"><span title="fmmrk1">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.marks"/></span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField"><span title="fvant/hent(Tot)">&nbsp;<font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.detail.update.label.antal"/>&nbsp;</span></td>
							 			<td align="center" valign="bottom" class="tableHeaderField"><span title="fvpakn">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.forpak"/></span></td>
							 			<td align="center" valign="bottom" class="tableHeaderField"><span title="fvvt">&nbsp;<font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.detail.update.label.goodsDesc"/></span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField"><span title="fvvkt/hevkt(Tot)">&nbsp;<font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.detail.update.label.weight"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField"><span title="fvlen">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.length"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField"><span title="fvbrd">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.width"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField"><span title="fvhoy">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.height"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField"><span title="fvvol/hem3(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.m3"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField"><span title="fvlm/helm(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.lm.fa"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField"><span title="fvlm2/helmla(Tot)">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.lm.la"/>&nbsp;</span></td>
							 			<td align="center" valign="bottom" class="tableHeaderField" style="font-size: 12px"><span title="ffunnr">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unNr"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField" style="font-size: 12px"><span title="ffembg">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.emg"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField" style="font-size: 12px"><span title="ffindx">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.emg.index"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField" style="font-size: 12px"><span title="ffantk">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.ant2"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField" style="font-size: 12px"><span title="ffante">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.mengd"/>&nbsp;</span></td>
							 			<td align="right" valign="bottom" class="tableHeaderField" style="font-size: 12px"><span title="ffenh">&nbsp;<spring:message code="systema.transportdisp.orders.form.detail.update.label.unit"/>&nbsp;</span></td>
							 			
							 		</tr>
							 		<tr >
							 			<td align="center" class="text14">
							 				<input type="hidden" id="updateLinNr" name="updateLinNr" value="${model.record.fraktbrevRecord.fvlinr}">
							 				<label style="color:yellow; font-weight:bold" id="linNr" >${model.record.fraktbrevRecord.fvlinr}</label>
							 			</td>	
							 			<td align="center" class="text14" nowrap>
						 					<input type="text" class="inputTextMediumBlue" name="fmmrk1" id="fmmrk1" size="15" maxlength="35" value="${model.record.fraktbrevRecord.fmmrk1}">
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onBlur="calculateVolume(fvvol);" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" style="text-align:right;" name="fvant" id="fvant" size="7" maxlength="7" value="${model.record.fraktbrevRecord.fvant}">
						 				</td>
						 				<td align="center" class="text14" nowrap>
							 				<input type="text" onBlur="searchPackingCodesNewLineOnBlur(this);" class="inputTextMediumBlue" name="fvpakn" id="fvpakn" size="8" maxlength="7" value="${model.record.fraktbrevRecord.fvpakn}">
							 				<a tabindex=0 id="fvpaknIdLinkNewLine" onClick="searchPackingCodesNewLine(this);">
	 											<img id="imgfvpaknSearch" align="bottom" style="cursor:pointer;" src="resources/images/find2.png" height="11px" width="11px" border="0" alt="search">
	 										</a>
						 				</td>
						 				<td align="center" class="text14" nowrap>
							 				<input type="text" class="inputTextMediumBlueMandatoryField" name="fvvt" id="fvvt" size="20" maxlength="35" value="${model.record.fraktbrevRecord.fvvt}">
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" style="text-align:right;" name="fvvkt" id="fvvkt" size="9" maxlength="9" value="${model.record.fraktbrevRecord.fvvkt}">
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onBlur="calculateVolume(fvvol);validateNewItemLine();" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" style="text-align:right;" name="fvlen" id="fvlen" size="4" maxlength="4" value="${model.record.fraktbrevRecord.fvlen}">
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onBlur="calculateVolume(fvvol);validateNewItemLine();" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" style="text-align:right;" name="fvbrd" id="fvbrd" size="4" maxlength="4" value="${model.record.fraktbrevRecord.fvbrd}">
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onBlur="calculateVolume(fvvol);validateNewItemLine();" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" style="text-align:right;" name="fvhoy" id="fvhoy" size="4" maxlength="4" value="${model.record.fraktbrevRecord.fvhoy}">
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onFocus="calculateVolume(this);" onBlur="checkVolumeNewLine(this);" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" style="text-align:right;" name="fvvol" id="fvvol" size="6" maxlength="8" value="${model.record.fraktbrevRecord.fvvol}">
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onBlur="checkLmNewLine(this);validateNewItemLine();" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" style="text-align:right;" name="fvlm" id="fvlm" size="4" maxlength="5" value="${model.record.fraktbrevRecord.fvlm}">
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onBlur="checkLm2NewLine(this);validateNewItemLine();" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" style="text-align:right;" name="fvlm2" id="fvlm2" size="4" maxlength="5" value="${model.record.fraktbrevRecord.fvlm2}">
						 				</td>
						 				<td align="center" class="text14" nowrap>
							 				<input onBlur="validateDangerousGoodsUnnrNewLine();"  type="text" class="inputTextMediumBlue" style="text-align:right;" name="ffunnr" id="ffunnr" size="4" maxlength="4" value="${model.record.fraktbrevRecord.ffunnr}">
							 				<a tabindex=0 id="ffunnrIdLinkNewLine" onClick="searchDangerousGoodsNewLine(this);">
	 											<img id="imgUnnrSearch" align="bottom" style="cursor:pointer;" src="resources/images/find2.png" height="11px" width="11px" border="0" alt="search">
	 										</a>
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onBlur="validateDangerousGoodsUnnrNewLine();" type="text" class="inputTextMediumBlue" style="text-align:right;" name="ffembg" id="ffembg" size="3" maxlength="3" value="${model.record.fraktbrevRecord.ffembg}">
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onBlur="validateDangerousGoodsUnnrNewLine();" type="text" class="inputTextMediumBlue" style="text-align:right;" name="ffindx" id="ffindx" size="2" maxlength="2" value="${model.record.fraktbrevRecord.ffindx}">
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onBlur="validateDangerousGoodsUnnrNewLine();" onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" style="text-align:right;" name="ffantk" id="ffantk" size="5" maxlength="5" value="${model.record.fraktbrevRecord.ffantk}">
						 				</td>
						 				<td align="right" class="text14" nowrap>
							 				<input onBlur="validateDangerousGoodsUnnrNewLine();" onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" style="text-align:right;" name="ffante" id="ffante" size="9" maxlength="11" value="${model.record.fraktbrevRecord.ffante}">
						 				</td>
						 				<input type="hidden" id="ownAdrFaktNewLine" name="ownAdrFaktNewLine" value="">
						 				<td align="right" class="text14" nowrap>
							 				<select class="inputTextMediumBlue" onChange="validateDangerousGoodsUnnrNewLine();" name="ffenh" id="ffenh">
					 							<option value="">?</option>
					 							<option value="KG" <c:if test="${model.record.fraktbrevRecord.ffenh == 'KG'}"> selected </c:if> >KG</option>
					 							<option value="LTR" <c:if test="${model.record.fraktbrevRecord.ffenh == 'LTR'}"> selected </c:if> >LTR</option>
					 							<option value="GR" <c:if test="${model.record.fraktbrevRecord.ffenh == 'GR'}"> selected </c:if> >GR</option>
					 							
											</select>
						 				</td>
						 				
						 				<td align="center"><input onClick="clearItemLineValues();" class="inputFormSubmitStd11" type="button" value='<spring:message code="systema.transportdisp.orders.form.detail.update.removeItemLineValues"/>'></td>
						 			</tr>
						 			<tr></tr>
						 			</table>
					 			</td>
				 			</tr>	
						
					
						
						<tr height="5"><td ></td></tr>
						<tr>
							<td colspan="2" valign="top" style="width:100%;">
								<table border="0">
							 		<tr height="2">
							 		<td >
							 			<input type="hidden" id="upperCurrentItemlineNr" name="upperCurrentItemlineNr" value="${upperCurrentItemlineNr}">
							 			<input type="hidden" id="upperCurrentItemlineNr" name="upperCurrentItemlineNr" value="${upperCurrentItemlineNr}">
							 		</td>
							 		</tr>
							 		
							 		<tr >
							 			<td class="text14" ><b>Oppd.TOTAL:</b></td>
							 			<%-- separator --%>
						 				<td class="text14" width="2px">&nbsp;</td>
						 				
							 			<td class="text14"><span title="hegm1/hegm2"><spring:message code="systema.transportdisp.orders.form.update.label.marks"/>1/2</span></td>
							 			<td class="text14" >	
							 				<input type="text" class="inputTextMediumBlueUPPERCASE" name="hegm1" id="hegm1" size="20" maxlength="35" value="${model.record.hegm1}">
							 				<input type="text" class="inputTextMediumBlueUPPERCASE" name="hegm2" id="hegm2" size="10" maxlength="10" value="${model.record.hegm2}">					 				
						 				</td>
						 				
						 				<td class="text14"><span title="hevs1"><font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.update.label.goodsDesc"/>1/2</span></td>
						 				<td class="text14">
						 					<input type="text" class="inputTextMediumBlueMandatoryFieldUPPERCASE" name="hevs1" id="hevs1" size="20" maxlength="25" value="${model.record.hevs1}">
							 				
						 				</td>
						 				<%-- separator --%>
						 				<td class="text14" width="5px">&nbsp;</td>
						 				
						 				<td class="text14"><span title="hent"><font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.detail.update.label.antal"/></span></td>
						 				<td class="text14">
						 					<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlueMandatoryField" name="hent" id="hent" size="7" maxlength="7" value="${model.record.hent}">
						 				</td>
						 				<td class="text14"><span title="hent"><font class="text14RedBold" >*</font><spring:message code="systema.transportdisp.orders.form.detail.update.label.weight"/></span></td>
						 				<td class="text14">
						 					<input type="text" onKeyPress="return numberKey(event)" class="inputTextMediumBlueMandatoryField" name="hevkt" id="hevkt" size="9" maxlength="9" value="${model.record.hevkt}">
						 				</td>
						 				<td class="text14"><span title="hem3"><spring:message code="systema.transportdisp.orders.form.detail.update.label.m3"/></span></td>
						 				<td class="text14">
						 					<input type="text" onBlur="checkHem3(this);" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="hem3" id="hem3" size="7" maxlength="8" value="${fn:replace(model.record.hem3,'.',',')}">						 					
						 				</td>
						 				<td class="text14"><span title="helm"><spring:message code="systema.transportdisp.orders.form.detail.update.label.lm.fa"/></span></td>
						 				<td class="text14">
						 					<input type="text" onBlur="checkHelm(this);" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="helm" id="helm" size="5" maxlength="5" value="${fn:replace(model.record.helm,'.',',')}">						 					
						 				</td>
						 				<td class="text14"><span title="helmla"><spring:message code="systema.transportdisp.orders.form.detail.update.label.lm.la"/></span></td>
						 				<td class="text14">
						 					<input type="text" onBlur="checkHelmla(this);" onKeyPress="return amountKey(event)" class="inputTextMediumBlue" name="helmla" id="helmla" size="5" maxlength="5" value="${fn:replace(model.record.helmla,'.',',')}">						 					
						 				</td>							 			
						 				<td class="text14"><span title="hefbv"><spring:message code="systema.transportdisp.orders.form.update.label.fraktberakningsVekt"/></span></td>
						 				<td class="text14">
						 					<%-- <input readonly type="text" class="inputTextReadOnly" size="10" value="${model.record.hefbv}">  --%>
						 					
						 					<label>${model.record.hefbv}</label>
						 				</td>
						 				
						 				<td align="left" class="text14" style="white-space: nowrap;" >
						 					<input type="checkbox" id="hestl4" name="hestl4" value="P" <c:if test="${model.record.hestl4 == 'P'}"> checked </c:if>>
							 				<span title="hestl4"><b>P</b></span>
							 				<img onMouseOver="showPop('psum_info');" onMouseOut="hidePop('psum_info');" style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<div class="text11" style="position: relative; display:inline;" align="left">
												<span style="position:absolute; left:-80px; top:10px;" id="psum_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>P(Protect)</b>
								           			<div>
								           			<p> Protect sumlinje<br/> 
													</p>
								           			</div>
							           			</font>
												</span>
											</div>
						 				</td>
						 				
						 				<td align="left" class="text14" style="white-space: nowrap;" >
						 					&nbsp;&nbsp;
						 					<input type="checkbox" id="hestn8" name="hestn8" value="X" <c:if test="${model.record.hestn8 == 'X'}"> checked </c:if>>
							 				<span title="hestn8">Ikke ADR samlastkontr.</span>
							 				<img onMouseOver="showPop('adrkontr_info');" onMouseOut="hidePop('adrkontr_info');" style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<div class="text11" style="position: relative; display:inline;" align="left">
												<span style="position:absolute; left:-80px; top:10px;" id="adrkontr_info" class="popupWithInputText"  >
													<font class="text11">
								           			<b>Ikke ADR samlastkontr.</b>
							           			</font>
												</span>
											</div>
						 				</td>
						 			</tr>
						 		</table>
					 		</td>
						</tr>
						<tr height="5"><td ></td></tr>
							
	 				</table>
            		</td>
            	</tr>
            </c:if>
            
            <tr height="2"><td></td></tr>
            
            <tr>
				<td colspan="2">
					<table style="width:99%;">
					<tr>
						<td align="right">
		 				    <label class="text14Red" id="orderLineErrMsgPlaceHolder"></label>
	 				    </td>
						<td align="right">
		 				    <c:choose>
			 				    <c:when test="${ not empty model.record.heavd && not empty model.record.heopd }">
		 				    		<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submit" id="submit" onClick="javascript: form.action='transportdisp_mainorder_update.do';" value='<spring:message code="systema.transportdisp.submit.save"/>'/>
		 				    		<input title="Lagre og Lukk" onMouseOver="style='cursor:pointer;box-shadow: 0 0 5px rgba(66, 148, 255, 1);border: 5px solid rgba(66, 148, 255, 1);'" onMouseOut="style=''" type="checkbox" name="savecloseFlag" id="savecloseFlag" value="1" <c:if test="${model.record.savecloseFlag == '1'}"> checked </c:if>>
			 				    </c:when>
			 				    <c:otherwise>
		 				    		<input tabindex=-1 class="inputFormSubmit submitSaveClazz" type="submit" name="submitnew" id="submitnew" onClick="javascript: form.action='transportdisp_mainorder_update.do';" value='<spring:message code="systema.transportdisp.submit.createnew.order"/>'/>
			 				    </c:otherwise>	
		 				    </c:choose>
	 				    </td>
				    </tr>
				    </table>
			    </td>
			</tr> 	
            <%-- HEADER --%>
	 		<tr>
            		<td>
	        			<table style="width:99%;" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text14White" colspan="5">
							<img onMouseOver="showPop('messageNote_info');" onMouseOut="hidePop('messageNote_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
			 				<span title="messageNoteConsignee/Carrier/Internal"><spring:message code="systema.transportdisp.workflow.trip.form.label.messageNotes"/></span>
			 				<div class="text11" style="position: relative;" align="left">
								<span style="position:absolute; left:0px; top:0px;" id="messageNote_info" class="popupWithInputText"  >
									<font class="text11">
				           			<b>Message/Notes</b>
				           			<div>
				           			<p>The message will be printed as shown in screen.</p>
				           			<ul>
				           				<li>Max.character per line: 70-characters</li>
				           			    <li>Max.number of lines per message area: 2</li>
				           			</ul>	
				           			</div>
			           			</font>
							</span>
							</div>	
			 				</td>
		 				</tr>
	 				</table>
            		</td>
            </tr>
            <tr>
				<td valign="top" colspan="10">
					<table style="width:99%" class="formFrame" > 		
			 		<tr>
			 			<td valign="top" align="left" style="width:50%" >
				 			<table>
								<tr>
						 			<td class="text14"><spring:message code="systema.transportdisp.orders.form.update.label.messageConsignee"/></td>
						 			<td class="text14">
						 				<textarea class="text14UPPERCASE" id="messageNoteConsignee" name="messageNoteConsignee" limit='70,2' cols="75" rows="2">${model.record.messageNoteConsignee}</textarea>
					 				</td>
				 				</tr>
								<tr>
						 			<td class="text14"><spring:message code="systema.transportdisp.orders.form.update.label.messageCarrier"/></td>
						 			<td class="text14">
						 				<textarea class="text14UPPERCASE" id="messageNoteCarrier" name="messageNoteCarrier" limit='70,2' cols="75" rows="2">${model.record.messageNoteCarrier}</textarea>
					 				</td>
				 				</tr>
				 				<tr>
						 			<td class="text14"><spring:message code="systema.transportdisp.orders.form.update.label.messageInternal"/></td>
						 			<td class="text14">
						 				<textarea class="text14UPPERCASE" id="messageNoteInternal" name="messageNoteInternal" limit='70,2' cols="75" rows="2">${model.record.messageNoteInternal}</textarea>
					 				</td>
				 				</tr>
			 				</table>
		 				</td>
		 				<td valign="top" align="left" style="width:50%" >
							<table> 		
					 		<tr>
					 			<td align="left">
					 			<table class="tableBorderWithRoundCorners" >
									<tr>
							 			<td valign="top" class="text14">
						 					<spring:message code="systema.transportdisp.workflow.trip.form.label.uploadedDocs"/>&nbsp;
						 					<table>
						 						<tr class="tableHeaderField" >
						 						<th align="left" class="text14">Dok.type</th>
						 						<th align="left" class="text14">Dok.navn</th>
						 						<th align="left" class="text14">Dato/kl</th>
						 						
						 						</tr>
						 					<c:forEach items="${model.record.archivedDocsRecord}" var="record" varStatus="counter">
						 						<tr class="text14 tableRow">
						 							<td class="tableCellFirst" style="white-space:nowrap">${record.doctyp}</td>
					   								<td class="tableCell" style="white-space:nowrap">
							 						<a target="_blank" href="transportdisp_workflow_renderArchivedDocs.do?doclnk=${record.doclnk}">
							 							<c:choose>
				    		    							<c:when test="${fn:contains(record.doclnk, '.pdf')}">
				    		    								<img title="Archive" style="vertical-align:middle;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="PDF arch.">
				    		    							</c:when>
				    		    							<c:otherwise>
				    		    								<img title="Archive" style="vertical-align:middle;" src="resources/images/jpg.png" width="14" height="14" border="0" alt="Other arch.">
				    		    							</c:otherwise>
			    		    							</c:choose>
			    		    							${record.doctxt}
					   								</a>
					   								</td>
					   								<td class="tableCell" style="white-space:nowrap">${record.docdat}&nbsp;${record.doctim}</td>
				   								</tr>
						 					</c:forEach>
						 					</table>
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
			<tr height="10"><td ></td></tr>
			
			<%-- -------------------------- --%>	
	 		<%-- DUP dialog    --%>	
	 		<%-- -------------------------- --%>	
			 <tr>
				<td>
					<div id="dialogDup" title="Dialog">
								<table border="0">
							 		<tr ><td class="text14" colspan="3"><b>Innhenting&nbsp;&nbsp;DUP-Oppdrag</b></td></tr>
									<tr>
										<td class="text14"><span title="ffavd">Via avd</span></td>
								 		<td class="text14">
								 			<c:choose>
											<c:when test="${empty model.record.travd1 && empty model.record.tropd1}">
								 				<%-- <input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" size="5" maxlength="4" name="ffavd" id="ffavd" value="${model.record.ffavd}"> --%>
								 				<select class="inputTextMediumBlue" name="ffavd" id="ffavd">
								            		<option value="">-velg-</option>
								 				  	<c:forEach var="record" items="${model.avdList}" >
								 				  		<option title="${record.avd}" value="${record.avd}"<c:if test="${model.record.ffavd == record.avd}"> selected </c:if> >${record.avd}</option>
													</c:forEach> 
												</select>
												<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_avd.do?action=doFind&ctype=ffavd','avdWin','top=100px,left=300px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
				 									<img id="imgFfAvdSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="14px" width="14px" border="0" alt="search">
				 								</a>
										
								 			</c:when>
								 			<c:otherwise>
								 				<input readonly type="text" class="inputTextReadOnly" size="5" maxlength="4" name="ffavd" id="ffavd" value="${model.record.ffavd}">
								 			</c:otherwise>
								 			</c:choose>
								 		</td>
								 		<td class="text14"><span title="ffoty">Opd.type</span></td>
								 		<td class="text14">
								 			<c:choose>
											<c:when test="${empty model.record.travd1 && empty model.record.tropd1}">
												<select class="inputTextMediumBlue" name="ffoty" id="ffoty">
								            		<option value="">-select-</option>
								 				  	<c:forEach var="record" items="${model.oppdragstypeList}" >
								 				  		<c:choose>
								 				  		<c:when test="${not empty model.record.ffoty}">
							                       	 		<option title="${record.beskr}" value="${record.opdTyp}"<c:if test="${model.record.ffoty == record.opdTyp}"> selected </c:if> >${record.opdTyp}</option>
							                       	 	</c:when>
							                       	 	<c:otherwise>
							                       	 		<option title="${record.beskr}" value="${record.opdTyp}"<c:if test="${model.record.heot == record.opdTyp}"> selected </c:if> >${record.opdTyp}</option>
							                       	 	</c:otherwise>
							                       	 	</c:choose>
													</c:forEach> 
												</select>
											</c:when>
											<c:otherwise>
												<input readonly type="text" class="inputTextReadOnly" size="3" maxlength="2" name="ffoty" id="ffoty" value="${model.record.ffoty}">
											</c:otherwise>
											</c:choose>
											
								 		</td>
								 		<td class="text14"><span title="fffrank">Frankatur</span></td>
								 		<td class="text14">
								 			<c:choose>
											<c:when test="${empty model.record.travd1 && empty model.record.tropd1}">
								 			<select class="inputTextMediumBlue" name="fffrank" id="fffrank">
								 				<option value="">-select-</option>
							            		<c:forEach var="record" items="${model.incotermsList}" >
						 				  		<c:choose>
						 				  		<c:when test="${not empty model.record.fffrank}">
							 				  		<option title="${record.beskr}" value="${record.franka}"<c:if test="${model.record.fffrank == record.franka}"> selected </c:if> >${record.franka}</option>
												</c:when>
												<c:otherwise>
													<option title="${record.beskr}" value="${record.franka}"<c:if test="${model.record.hefr == record.franka}"> selected </c:if> >${record.franka}</option>
												</c:otherwise>
												</c:choose>
												</c:forEach>
											</select>
											</c:when>
											<c:otherwise>
												<input readonly type="text" class="inputTextReadOnly" size="4" maxlength="3" name="fffrank" id="fffrank" value="${model.record.fffrank}">
											</c:otherwise>
											</c:choose>
											
								 		</td>
								 	</tr>
								 	<tr>		
							 			<td class="text14"><span title="ffftxt">Fritext</span></td>
							 			<td class="text14">
							 				<c:choose>
											<c:when test="${empty model.record.travd1 && empty model.record.tropd1}">
							 					<input type="text" class="inputTextMediumBlue" size="2" maxlength="1" name="ffftxt" id="ffftxt" value="${model.record.ffftxt}">
							 				</c:when>
							 				<c:otherwise>
							 					<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="ffftxt" id="ffftxt" value="${model.record.ffftxt}">
							 				</c:otherwise>
							 				</c:choose>
							 			</td>
							 			<td class="text14"><span title="ffmodul">Modul</span></td>
							 			<td class="text14">
							 				<c:choose>
											<c:when test="${empty model.record.travd1 && empty model.record.tropd1}">
							 					<input type="text" class="inputTextMediumBlue" size="2" maxlength="1" name="ffmodul" id="ffmodul" value="${model.record.ffmodul}">
							 				</c:when>
							 				<c:otherwise>
							 					<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="ffmodul" id="ffmodul" value="${model.record.ffmodul}">
							 				</c:otherwise>
							 				</c:choose>
							 			</td>
							 			<td class="text14"><span title="ffpkod">Prod.kode</span></td>
							 			<td class="text14">
							 				<c:choose>
											<c:when test="${empty model.record.travd1 && empty model.record.tropd1}">
							 					<input type="text" class="inputTextMediumBlue" size="2" maxlength="1" name="ffpkod" id="ffpkod" value="${model.record.ffpkod}">
							 				</c:when>
							 				<c:otherwise>
							 					<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="ffpkod" id="ffpkod" value="${model.record.ffpkod}">
							 				</c:otherwise>
							 				</c:choose>
							 			</td>
								 	</tr>
								 	<tr height="10"><td class="text14" ></td></tr>
								 	<tr>
								 		<td colspan="10">
								 		<table class="tableBorderWithRoundCorners3D">
								 			<tr height="3"><td></td></tr>
								 			<tr height="10"><td colspan="3" class="text14" ><b>...Eller lag Rekvisisjon</b></td></tr>
									 		<tr>		
								 			<td class="text14"><span title="ffbnr">Bilnr</span></td>
								 			<td class="text14" colspan="2" >
								 				<c:choose>
												<c:when test="${empty model.record.travd1 && empty model.record.tropd1}">
								 					<input type="text" class="inputTextMediumBlue" size="9" maxlength="8" name="ffbnr" id="ffbnr" value="${model.record.ffbnr}">
								 					<a tabindex=0 id="ffbnrIdLink" >
										    			<img id="imgTruckLicSearch" style="cursor:pointer; vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
				 									</a>
								 				</c:when>
								 				<c:otherwise>
								 					<input readonly type="text" class="inputTextReadOnly" size="9" maxlength="8" name="ffbnr" id="ffbnr" value="${model.record.ffbnr}">
								 				</c:otherwise>
								 				</c:choose>
								 			</td>
								 			<td class="text14"><span title="fftran">Transportør</span></td>
								 			<td class="text14" colspan="2" >
								 				<c:choose>
												<c:when test="${empty model.record.travd1 && empty model.record.tropd1}">
								 					<input type="text" class="inputTextMediumBlue" size="9" maxlength="8" name="fftran" id="fftran" value="${model.record.fftran}">
								 					<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_transpcarrier.do?action=doInit&ctype=fftran','transpcarrierWin','top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
				 										<img id="imgTruckersNrSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
				 									</a>
								 				</c:when>
								 				<c:otherwise>
								 					<input readonly type="text" class="inputTextReadOnly" size="9" maxlength="8" name="fftran" id="fftran" value="${model.record.fftran}">
								 				</c:otherwise>
								 				</c:choose>
								 			</td>
								 			</tr>
								 			<tr>
								 			<td class="text14"><span title="ffkomm">Kommentar</span></td>
								 			<td class="text14" colspan="6" >
								 				<c:choose>
												<c:when test="${empty model.record.travd1 && empty model.record.tropd1}">
								 					<input type="text" class="inputTextMediumBlue" size="36" maxlength="35" name="ffkomm" id="ffkomm" value="${model.record.ffkomm}">
								 				</c:when>
								 				<c:otherwise>
								 					<input readonly type="text" class="inputTextReadOnly" size="36" maxlength="35" name="ffkomm" id="ffkomm" value="${model.record.ffkomm}">
								 				</c:otherwise>
								 				</c:choose>
								 			</td>
								 			</tr>
								 			<tr height="3"><td></td></tr>
							 			</table>
							 			</td>
							 		</tr>
								 	<tr height="5"><td class="text14" ></td></tr>
								 	<tr>		
							 			<td class="text14"><span title="ffbel">Beløp</span></td>
							 			<td class="text14" colspan="3" >
							 				<c:choose>
											<c:when test="${empty model.record.travd1 && empty model.record.tropd1}">
							 					<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" size="15" maxlength="15" name="ffbel" id="ffbel" value="${model.record.ffbel}">
							 				</c:when>
							 				<c:otherwise>
							 					<input readonly type="text" class="inputTextReadOnly" size="15" maxlength="15" name="ffbel" id="ffbel" value="${model.record.ffbel}">
							 				</c:otherwise>
							 				</c:choose>
							 				&nbsp;&nbsp;<span title="ffbelk"><font class="text14">A/E</font></span>
							 				<c:choose>
											<c:when test="${empty model.record.travd1 && empty model.record.tropd1}">
							 					<input type="text" class="inputTextMediumBlue" size="3" maxlength="2" name="ffbelk" id="ffbelk" value="${model.record.ffbelk}">
							 				</c:when>
							 				<c:otherwise>
							 					<input readonly type="text" class="inputTextReadOnly" size="3" maxlength="2" name="ffbelk" id="ffbelk" value="${model.record.ffbelk}">
							 				</c:otherwise>
							 				</c:choose>
							 			</td>
								 	</tr>
								 	
								 	<tr height="12"><td colspan="10" ></td></tr>
									<tr height="1"><td colspan="10" style="border-bottom:2px solid;border-color:#FFFFFF;" class="text"></td></tr>
									<tr height="12"><td colspan="10" ></td></tr>
								 	
								 	<tr ><td class="text14" colspan="3"><b>Utkjøring&nbsp;&nbsp;DUP-Oppdrag</b></td></tr>
								 	<tr>
										<td class="text14"><span title="vfavd">Via avd</span></td>
								 		<td class="text14">
								 			<c:choose>
											<c:when test="${empty model.record.travd2 && empty model.record.tropd2}">
								 				<%-- <input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" size="5" maxlength="4" name="vfavd" id="vfavd" value="${model.record.vfavd}"> --%>
								 				<select class="inputTextMediumBlue" name="vfavd" id="vfavd">
								            		<option value="">-velg-</option>
								 				  	<c:forEach var="record" items="${model.avdList}" >
								 				  		<option title="${record.avd}" value="${record.avd}"<c:if test="${model.record.vfavd == record.avd}"> selected </c:if> >${record.avd}</option>
													</c:forEach> 
												</select>
												<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_avd.do?action=doFind&ctype=vfavd','avdWin','top=100px,left=300px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
				 									<img id="imgVfAvdSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="14px" width="14px" border="0" alt="search">
				 								</a>
								 			</c:when>
								 			<c:otherwise>
								 				<input readonly type="text" class="inputTextReadOnly" size="5" maxlength="4" name="vfavd" id="vfavd" value="${model.record.vfavd}">
								 			</c:otherwise>
								 			</c:choose>
								 		</td>
								 		<td class="text14"><span title="vfoty">Opd.type</span></td>
								 		<td class="text14">
								 			<c:choose>
											<c:when test="${empty model.record.travd2 && empty model.record.tropd2}">
								 			<select class="inputTextMediumBlue" name="vfoty" id="vfoty">
							            		<option value="">-select-</option>
							 				  	<c:forEach var="record" items="${model.oppdragstypeList}" >
							 				  		<c:choose>
													<c:when test="${not empty model.record.vfoty}">
							 				  	 		<option title="${record.beskr}" value="${record.opdTyp}"<c:if test="${model.record.vfoty == record.opdTyp}"> selected </c:if> >${record.opdTyp}</option>
							 				  	 	</c:when>
							 				  	 	<c:otherwise>
							 				  	 		<option title="${record.beskr}" value="${record.opdTyp}"<c:if test="${model.record.heot == record.opdTyp}"> selected </c:if> >${record.opdTyp}</option>
							 				  	 	</c:otherwise>
							 				  	 	</c:choose>
												</c:forEach> 
											</select>
											</c:when>
											<c:otherwise>
												<input readonly type="text" class="inputTextReadOnly" size="4" maxlength="2" name="vfoty" id="vfoty" value="${model.record.vfoty}">
											</c:otherwise>
											</c:choose>
								 		</td>
								 		<td class="text14"><span title="vffrank">Frankatur</span></td>
								 		<td class="text14">
								 			<c:choose>
											<c:when test="${empty model.record.travd2 && empty model.record.tropd2}">
								 			<select class="inputTextMediumBlue" name="vffrank" id="vffrank">
							            		<option value="">-select-</option>
												<c:forEach var="record" items="${model.incotermsList}" >
							 				  		<c:choose>						 				  	
							 				  		<c:when test="${not empty model.record.vffrank}">
							 				  			<option title="${record.beskr}" value="${record.franka}"<c:if test="${model.record.vffrank == record.franka}"> selected </c:if> >${record.franka}</option>
													</c:when>
													<c:otherwise>
														<option title="${record.beskr}" value="${record.franka}"<c:if test="${model.record.hefr == record.franka}"> selected </c:if> >${record.franka}</option>
													</c:otherwise>
													</c:choose>
												</c:forEach> 
											</select>
											</c:when>
											<c:otherwise>
												<input readonly type="text" class="inputTextReadOnly" size="4" maxlength="3" name="vffrank" id="vffrank" value="${model.record.vffrank}">
											</c:otherwise>
											</c:choose>
								 		</td>
								 		
								 		
								 	</tr>
								 	<tr>		
							 			<td class="text14"><span title="vfftxt">Fritext</span></td>
							 			<td class="text14">
							 				<c:choose>
											<c:when test="${empty model.record.travd2 && empty model.record.tropd2}">
							 					<input type="text" class="inputTextMediumBlue" size="2" maxlength="1" name="vfftxt" id="vfftxt" value="${model.record.vfftxt}">
							 				</c:when>
							 				<c:otherwise>
							 					<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="vfftxt" id="vfftxt" value="${model.record.vfftxt}">
							 				</c:otherwise>
							 				</c:choose>
							 				
							 			</td>
							 				
							 			<td class="text14"><span title="vfmodul">Modul</span></td>
							 			<td class="text14">
							 				<c:choose>
											<c:when test="${empty model.record.travd2 && empty model.record.tropd2}">
							 					<input type="text" class="inputTextMediumBlue" size="2" maxlength="1" name="vfmodul" id="vfmodul" value="${model.record.vfmodul}">
							 				</c:when>
							 				<c:otherwise>
							 					<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="vfmodul" id="vfmodul" value="${model.record.vfmodul}">
							 				</c:otherwise>
							 				</c:choose>
							 				
							 			</td>
							 			<td class="text14"><span title="vfpkod">Prod.kode</span></td>
							 			<td class="text14">
							 				<c:choose>
											<c:when test="${empty model.record.travd2 && empty model.record.tropd2}">
							 					<input type="text" class="inputTextMediumBlue" size="2" maxlength="1" name="vfpkod" id="vfpkod" value="${model.record.vfpkod}">
							 				</c:when>
							 				<c:otherwise>
							 					<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="vfpkod" id="vfpkod" value="${model.record.vfpkod}">
							 				</c:otherwise>
							 				</c:choose>
							 			</td>
								 	</tr>
								 	<tr height="10"><td class="text14" ></td></tr>	
								 	<tr>	
								 		<td colspan="10">
								 		<table class="tableBorderWithRoundCorners3D">
								 			<tr height="3"><td></td></tr>
								 			<tr height="10"><td colspan="3" class="text14" ><b>...Eller lag Rekvisisjon</b></td></tr>
										 	<tr>			
								 			<td class="text14"><span title="vfbnr">Bilnr</span></td>
								 			<td class="text14" colspan="2" >
								 				<c:choose>
												<c:when test="${empty model.record.travd2 && empty model.record.tropd2}">
								 					<input type="text" class="inputTextMediumBlue" size="9" maxlength="8" name="vfbnr" id="vfbnr" value="${model.record.vfbnr}">
								 					<a tabindex=0 id="vfbnrIdLink" >
										    			<img id="imgTruckLicSearch" style="cursor:pointer; vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
				 									</a>
								 				</c:when>
								 				<c:otherwise>
								 					<input readonly type="text" class="inputTextReadOnly" size="9" maxlength="8" name="vfbnr" id="vfbnr" value="${model.record.vfbnr}">
								 				</c:otherwise>
								 				</c:choose>
								 			</td>
								 			<td class="text14"><span title="vftran">Transportør</span></td>
								 			<td class="text14" colspan="2" >
								 				<c:choose>
												<c:when test="${empty model.record.travd2 && empty model.record.tropd2}">
								 					<input type="text" class="inputTextMediumBlue" size="9" maxlength="8" name="vftran" id="vftran" value="${model.record.vftran}">
								 					<a href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_transpcarrier.do?action=doInit&ctype=vftran','transpcarrierWin','top=300px,left=350px,height=600px,width=800px,scrollbars=no,status=no,location=no')">
				 										<img id="imgvftranSearch" style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="14px" height="14px" border="0" alt="search">
				 									</a>
								 				</c:when>
								 				<c:otherwise>
								 					<input readonly type="text" class="inputTextReadOnly" size="9" maxlength="8" name="vftran" id="vftran" value="${model.record.vftran}">
								 				</c:otherwise>
								 				</c:choose>
								 			</td>
								 			</tr>
								 			<tr>		
								 			<td class="text14"><span title="vfkomm">Kommentar</span></td>
								 			<td class="text14" colspan="6" >
								 				<c:choose>
												<c:when test="${empty model.record.travd2 && empty model.record.tropd2}">
								 					<input type="text" class="inputTextMediumBlue" size="36" maxlength="35" name="vfkomm" id="vfkomm" value="${model.record.vfkomm}">
								 				</c:when>
								 				<c:otherwise>
								 					<input readonly type="text" class="inputTextReadOnly" size="36" maxlength="35" name="vfkomm" id="vfkomm" value="${model.record.vfkomm}">
								 				</c:otherwise>	
								 				</c:choose>
								 			</td>
									 		</tr>
									 		<tr height="3"><td></td></tr>
							 			</table>
							 			</td>
								 	</tr>
								 	
								 	<tr height="5"><td class="text14" ></td></tr>
								 	<tr>		
							 			<td class="text14"><span title="vfbel">Beløp</span></td>
							 			<td class="text14" colspan="4" nowrap>
							 				<c:choose>
											<c:when test="${empty model.record.travd2 && empty model.record.tropd2}">
							 					<input onKeyPress="return amountKey(event)" type="text" class="inputTextMediumBlue" size="15" maxlength="15" name="vfbel" id="vfbel" value="${model.record.vfbel}">
							 				</c:when>
							 				<c:otherwise>
							 					<input readonly type="text" class="inputTextReadOnly" size="15" maxlength="15" name="vfbel" id="vfbel" value="${model.record.vfbel}">
							 				</c:otherwise>
							 				</c:choose>
							 				&nbsp;&nbsp;<span title="vfbelk"><font class="text14">A/E</font></span>
							 				<c:choose>
											<c:when test="${empty model.record.travd2 && empty model.record.tropd2}">
							 					<input type="text" class="inputTextMediumBlue" size="3" maxlength="2" name="vfbelk" id="vfbelk" value="${model.record.vfbelk}">
							 				</c:when>
							 				<c:otherwise>
							 					<input readonly type="text" class="inputTextReadOnly" size="3" maxlength="2" name="vfbelk" id="vfbelk" value="${model.record.vfbelk}">
							 				</c:otherwise>
							 				</c:choose>
							 			</td>
							 			
								 	</tr>	
								</table>
							</div>
						</td>
					</tr> 
			
			</table>
			</form>
		</td>
		</tr>
		
		<%-- ---------------- --%>
		<%-- DIALOG timestamp --%>
		<%-- ---------------- --%>
		<tr>
		<td>
			<div id="dialogTimestamp" title="Dialog">
				 	<p class="text14" >Dato og klokkeslett som fraktbrevet ble underskrevet på er obligatoriska for POD fraktbrev. (ZP – dokument)</p>
					<table>
						<tr>
							<td class="text14" align="left" >&nbsp;Dato</td>
   							<td class="text14" align="left" >&nbsp;Klokkeslett</td>
   						</tr>
						<tr>
							<td class="text14MediumBlue">
								<input type="text" class="inputText" onKeyPress="return numberKey(event)" id="selectedDate" name="selectedDate" size="10" maxlength="8" value=''>&nbsp;
							</td>
							<td class="text14MediumBlue">
								<input type="text" class="inputText" onKeyPress="return numberKey(event)" id="selectedTime" name="selectedTime" size="5" maxlength="4" value=''>&nbsp;
							</td>
						</tr>
					</table>
			</div>
		</td>
		</tr>
		
		
		<%-- ---------------- --%>
		<%-- DIALOG SMS		  --%>
		<%-- ---------------- --%>
		<tr>
		<td>
			<div id="dialogSMS" title="Dialog">
					<form id="smsForm">
				 	<table>
						
   						<tr height="3"><td></td></tr>
   						
						<tr>
							<td class="text14" align="left" ><b>SMS-nummer</b>&nbsp;</td>
							<td class="text14" align="left" >
								<input type="text" class="inputTextMediumBlue" onKeyPress="return numberKey(event)" id="smsnr" name="smsnr" size="20" maxlength="15" value=''>
							</td>
   						</tr>
   						<c:if test="${not empty model.record.wsstlf || not empty model.record.wsktlf}">
   						<tr>
   							<td class="text14" >Hente fra&nbsp;</td>
							<td class="text14" align="left" >
								<select class="inputTextMediumBlue" name="smsPhonePart" id="smsPhonePart" >
		 							<option value="X" selected>velg</option>
		 							<option value="S" >Avsender</option>
		 							<option value="R" >Mottaker</option>
								</select>
							</td>
   						</tr>
   						</c:if>
   						
   						<tr>
   							<td class="text14" align="left" >Språk&nbsp;</td>
							<td class="text14" align="left" >
		   						<select class="inputTextMediumBlue" name="smslang" id="smslang">
		 							<option value="NO" selected>Norsk</option>
		 							<option value="EN" >Engelsk</option>
								</select>
							</td>
						</tr>
						<tr height="10"><td></td></tr>
						<tr>
							<td colspan="3" class="text14" align="left" >
								<input type="radio" name="smsType" id="smsType" value="grabber">Send SMS med lenke til TKeventGrabber
							</td>
   						</tr>
   						<tr>
							<td colspan="3" class="text14" align="left" >
								<input type="radio" name="smsType" id="smsType" value="general">Send generell SMS med melding/evt lenke
							</td>
   						</tr>
   						<tr height="5"><td></td></tr>
   						
   						<tr>
	   						<td class="text14" colspan="3">
	   						<div id="divFreeTextElements" style="display:none;position: relative;height:10em;" align="left" >
		 						<table id="tblFreeTextElements">
									<tr>
										<td class="text14">Merknad</td>
									</tr>
									<tr>
										<td ><input type="text" class="inputTextMediumBlue" id="smsFreeText1" name="smsFreeText1" size="40" maxlength="70" value=''></td>
									</tr>
									<tr>
										<td ><input type="text" class="inputTextMediumBlue" id="smsFreeText2" name="smsFreeText2" size="40" maxlength="70" value=''></td>
									</tr>
									<tr>
										<td ><input type="checkbox" id="smsUrlLink" name="smsUrlLink" value="J">Send også Url-lenke til spørring på sendingen</td>
									</tr>
									
								</table>	
							</div>	
   							</td>
   						</tr>
						<tr height="10"><td></td></tr>
						<tr>
							<td colspan="3" class="text14MediumBlue" align="left">
								<label id="smsStatus"></label>
							</td>
						</tr>
						
					</table>
					</form>
			</div>
		</td>
		</tr>
		
		<%-- ---------------- --%>
		<%-- DIALOG EMAIL		  --%>
		<%-- ---------------- --%>
		<tr>
		<td>
			<div id="dialogEmail" title="Dialog">
				 	<table>
						<tr>
							<td colspan="3" class="text14" align="left" ><spring:message code="systema.transportdisp.workflow.trip.email.manifest.label.on.order"/></td>
   						</tr>
   						<tr height="10"><td></td></tr>
   						
						<tr>
							<td class="text14" align="left" ><b><spring:message code="systema.transportdisp.workflow.trip.email.email.label"/></b>&nbsp;</td>
							<td class="text14" align="left" >
								<input type="text" class="inputText" id="email" name="email" size="25" maxlength="20" value=''>
							</td>
   						</tr>
   						<%-- TODO
   						<c:if test="${not empty model.record.wssmail || not empty model.record.wskmail}">
   						<tr>
   							<td class="text14" >Hente fra&nbsp;</td>
							<td class="text14" align="left" >
								<select class="inputTextMediumBlue" name="smsMailPart" id="smsMailPart" >
		 							<option value="X" selected>velg</option>
		 							<option value="S" >Avsender</option>
		 							<option value="R" >Mottaker</option>
								</select>
							</td>
   						</tr>
   						</c:if>
   						 --%>
   						
						<tr>
   							<td class="text14" align="left" ><spring:message code="systema.transportdisp.workflow.trip.email.mark.label"/></td>
							<td class="text12" align="left" >
		   						<textarea id="emailText" name="emailText" limit='48,4' cols="50" rows="4"></textarea>
							</td>
						</tr>
						
						<tr>
   							<td class="text14" align="left" ><spring:message code="systema.transportdisp.workflow.trip.email.language.label"/></td>
							<td class="text14" align="left" >
		   						<select class="inputTextMediumBlue" name="emailLang" id="emailLang">
		 							<option value="NO" selected><spring:message code="systema.transportdisp.workflow.trip.email.language.no"/></option>
		 							<option value="EN"><spring:message code="systema.transportdisp.workflow.trip.email.language.en"/></option>
								</select>
							</td>
						</tr>
						<tr height="10"><td></td></tr>
						<tr>
							<td colspan="3" class="text14MediumBlue" align="left">
								<label id="emailStatus"></label>
							</td>
						</tr>
						
					</table>
			</div>
		</td>
		</tr>
		
		
		<%-- -------------------------- --%>	
 		<%-- DUP dialog    --%>	
 		<%-- -------------------------- --%>	
			 <tr>
				<td>
					<div id="dialogDupReadOnly" title="Dialog">
								<table border="0">
							 		<tr ><td class="text14" colspan="3"><b>Innhenting&nbsp;&nbsp;DUP-Oppdrag</b></td></tr>
									<tr>
										<td class="text14"><span title="ffavd">Via avd</span></td>
								 		<td class="text14">
								 			<input readonly type="text" class="inputTextReadOnly" size="5" maxlength="4" name="ffavd" id="ffavd" value="${model.record.ffavd}">
								 		</td>
								 		<td class="text14"><span title="ffoty">Opd.type</span></td>
								 		<td class="text14">
								 			<input readonly type="text" class="inputTextReadOnly" size="3" maxlength="2" name="ffoty" id="ffoty" value="${model.record.ffoty}">
								 		</td>
								 		<td class="text14"><span title="fffrank">Frankatur</span></td>
								 		<td class="text14">
								 			<input readonly type="text" class="inputTextReadOnly" size="4" maxlength="3" name="fffrank" id="fffrank" value="${model.record.fffrank}">
								 		</td>
								 	</tr>
								 	<tr>		
							 			<td class="text14"><span title="ffftxt">Fritext</span></td>
							 			<td class="text14">
							 				<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="ffftxt" id="ffftxt" value="${model.record.ffftxt}">
							 				
							 			</td>
							 			<td class="text14"><span title="ffmodul">Modul</span></td>
							 			<td class="text14">
							 				<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="ffmodul" id="ffmodul" value="${model.record.ffmodul}">
							 				
							 			</td>
							 			<td class="text14"><span title="ffpkod">Prod.kode</span></td>
							 			<td class="text14">
							 				<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="ffpkod" id="ffpkod" value="${model.record.ffpkod}">
							 				
							 			</td>
								 	</tr>
								 	<tr height="10"><td class="text14" ></td></tr>
								 	<tr>
								 		<td colspan="10">
								 		<table class="tableBorderWithRoundCorners3D">
								 			<tr height="3"><td></td></tr>
								 			<tr height="10"><td colspan="3" class="text14" ><b>...Eller lag Rekvisisjon</b></td></tr>
									 		<tr>		
								 			<td class="text14"><span title="ffbnr">Bilnr</span></td>
								 			<td class="text14" colspan="2" >
								 				<input readonly type="text" class="inputTextReadOnly" size="9" maxlength="8" name="ffbnr" id="ffbnr" value="${model.record.ffbnr}">
								 				
								 			</td>
								 			<td class="text14"><span title="fftran">Transportør</span></td>
								 			<td class="text14" colspan="2" >
								 				<input readonly type="text" class="inputTextReadOnly" size="9" maxlength="8" name="fftran" id="fftran" value="${model.record.fftran}">
								 				
								 			</td>
								 			</tr>
								 			<tr>
								 			<td class="text14"><span title="ffkomm">Kommentar</span></td>
								 			<td class="text14" colspan="6" >
								 				<input readonly type="text" class="inputTextReadOnly" size="36" maxlength="35" name="ffkomm" id="ffkomm" value="${model.record.ffkomm}">
								 				
								 			</td>
								 			</tr>
								 			<tr height="3"><td></td></tr>
							 			</table>
							 			</td>
							 		</tr>
								 	<tr height="5"><td class="text14" ></td></tr>
								 	<tr>		
							 			<td class="text14"><span title="ffbel">Beløp</span></td>
							 			<td class="text14" colspan="3" >
							 				<input readonly type="text" class="inputTextReadOnly" size="15" maxlength="15" name="ffbel" id="ffbel" value="${model.record.ffbel}">
							 				
							 				&nbsp;&nbsp;<span title="ffbelk"><font class="text14">A/E</font></span>
							 				<input readonly type="text" class="inputTextReadOnly" size="3" maxlength="2" name="ffbelk" id="ffbelk" value="${model.record.ffbelk}">
							 				
							 			</td>
								 	</tr>
								 	
								 	<tr height="12"><td colspan="10" ></td></tr>
									<tr height="1"><td colspan="10" style="border-bottom:2px solid;border-color:#FFFFFF;" class="text"></td></tr>
									<tr height="12"><td colspan="10" ></td></tr>
								 	
								 	<tr ><td class="text14" colspan="3"><b>Utkjøring&nbsp;&nbsp;DUP-Oppdrag</b></td></tr>
								 	<tr>
										<td class="text14"><span title="vfavd">Via avd</span></td>
								 		<td class="text14">
								 			<input readonly type="text" class="inputTextReadOnly" size="5" maxlength="4" name="vfavd" id="vfavd" value="${model.record.vfavd}">
								 			
								 		</td>
								 		<td class="text14"><span title="vfoty">Opd.type</span></td>
								 		<td class="text14">
								 			<input readonly type="text" class="inputTextReadOnly" size="4" maxlength="2" name="vfoty" id="vfoty" value="${model.record.vfoty}">
											
								 		</td>
								 		<td class="text14"><span title="vffrank">Frankatur</span></td>
								 		<td class="text14">
								 			<input readonly type="text" class="inputTextReadOnly" size="4" maxlength="3" name="vffrank" id="vffrank" value="${model.record.vffrank}">
								 		</td>
								 	
								 	</tr>
								 	<tr>		
							 			<td class="text14"><span title="vfftxt">Fritext</span></td>
							 			<td class="text14">
							 				<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="vfftxt" id="vfftxt" value="${model.record.vfftxt}">
							 				
							 			</td>
							 				
							 			<td class="text14"><span title="vfmodul">Modul</span></td>
							 			<td class="text14">
							 				<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="vfmodul" id="vfmodul" value="${model.record.vfmodul}">
							 				
							 			</td>
							 			<td class="text14"><span title="vfpkod">Prod.kode</span></td>
							 			<td class="text14">
							 				<input readonly type="text" class="inputTextReadOnly" size="2" maxlength="1" name="vfpkod" id="vfpkod" value="${model.record.vfpkod}">
							 				
							 			</td>
								 	</tr>
								 	<tr height="10"><td class="text14" ></td></tr>	
								 	<tr>	
								 		<td colspan="10">
								 		<table class="tableBorderWithRoundCorners3D">
								 			<tr height="3"><td></td></tr>
								 			<tr height="10"><td colspan="3" class="text14" ><b>...Eller lag Rekvisisjon</b></td></tr>
										 	<tr>			
								 			<td class="text14"><span title="vfbnr">Bilnr</span></td>
								 			<td class="text14" colspan="2" >
								 				<input readonly type="text" class="inputTextReadOnly" size="9" maxlength="8" name="vfbnr" id="vfbnr" value="${model.record.vfbnr}">
								 				
								 			</td>
								 			<td class="text14"><span title="vftran">Transportør</span></td>
								 			<td class="text14" colspan="2" >
								 				<input readonly type="text" class="inputTextReadOnly" size="9" maxlength="8" name="vftran" id="vftran" value="${model.record.vftran}">
								 				
								 			</td>
								 			</tr>
								 			<tr>		
								 			<td class="text14"><span title="vfkomm">Kommentar</span></td>
								 			<td class="text14" colspan="6" >
								 				<input readonly type="text" class="inputTextReadOnly" size="36" maxlength="35" name="vfkomm" id="vfkomm" value="${model.record.vfkomm}">
								 				
								 			</td>
									 		</tr>
									 		<tr height="3"><td></td></tr>
							 			</table>
							 			</td>
								 	</tr>
								 	
								 	<tr height="5"><td class="text14" ></td></tr>
								 	<tr>		
							 			<td class="text14"><span title="vfbel">Beløp</span></td>
							 			<td class="text14" colspan="4" nowrap>
							 				<input readonly type="text" class="inputTextReadOnly" size="15" maxlength="15" name="vfbel" id="vfbel" value="${model.record.vfbel}">
							 				
							 				&nbsp;&nbsp;<span title="vfbelk"><font class="text14">A/E</font></span>
							 				<input readonly type="text" class="inputTextReadOnly" size="3" maxlength="2" name="vfbelk" id="vfbelk" value="${model.record.vfbelk}">
							 				
							 			</td>
							 			
								 	</tr>	
						</table>
					</div>
				</td>
			</tr> 
			
			
			
		<%-- ---------------- --%>
		<%-- DIALOG PRINT     --%>
		<%-- ---------------- --%>
		<tr>
		<td>
			<div id="dialogPrint" title="Dialog Print">
					<form id="printForm">
						<input type="hidden" id="avd" name="avd" value="${model.record.heavd}">
						<input type="hidden" id="opd" name="opd" value="${model.record.heopd}">
						<input type="hidden" id="tur" name="tur" value="${model.record.hepro}">
						
				 	<table>
						
   						<tr height="3"><td></td></tr>
   						
   						<tr>
							<td class="text14" align="left" >
								<c:choose>
								<c:when test="${model.record.hepk1 == 'J'}">
									<input type="checkbox" name="fbType" id="fbType" value="fb">
								</c:when>
								<c:otherwise>
									&nbsp;<img onMouseOver="showPop('fbType_info');" onMouseOut="hidePop('fbType_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/darkbluesquare.png" border="0" alt="info">&nbsp;
					 				<div class="text11" style="position: relative; display:inline;" align="left">
										<span style="position:absolute; left:0px; top:0px;width:200px;" id="fbType_info" class="popupWithInputText"  >
											<font class="text11">
						           			<b style="color:red;">Direct print out not available</b>
						           			<div>
						           			<p>Fraktbrev must be checked (and saved) in order to allow for direct print out</p>
						           			
						           			</div>
					           			</font>
										</span>
									</div>
								</c:otherwise>
								</c:choose>
								<span id="alinkFraktbrevPdf" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">Fraktbrev</span>
							</td>	
							<td class="text14" align="left" >	
								<img id="imgFraktbrevPdf" title="Fraktbr.PDF" style="vertical-align:middle;cursor:pointer;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="Fraktbr. PDF">
							</td>
   						</tr>
   						<tr>
							<td class="text14" align="left" >
								<c:choose>
								<c:when test="${model.record.hepk7 == 'C'}">
									<input type="checkbox" name="cmrType" id="cmrType" value="cmr">
								</c:when>
								<c:otherwise>
									&nbsp;<img onMouseOver="showPop('cmrType_info');" onMouseOut="hidePop('cmrType_info');"style="vertical-align:bottom;" width="12px" height="12px" src="resources/images/darkbluesquare.png" border="0" alt="info">&nbsp;
					 				<div class="text11" style="position: relative; display:inline;" align="left">
										<span style="position:absolute; left:0px; top:0px;width:200px;" id="cmrType_info" class="popupWithInputText"  >
											<font class="text11">
						           			<b style="color:red;">Direct print out not available</b>
						           			<div>
						           			<p>CMR-Fraktbrev must be checked (and saved) in order to allow for direct print out</p>
						           			
						           			</div>
					           			</font>
										</span>
									</div>
								</c:otherwise>
								</c:choose>
								
								
								<span id="alinkCmrFraktbrevPdf" style="text-decoration: underline;" onMouseOver="style='color:lemonchiffon;cursor:pointer;text-decoration: underline;'" onMouseOut="style='color:black;text-decoration: underline;'">CMR-Fraktbrev</span>
							</td>
							<td class="text14" align="left" >	
								<img id="imgCmrFraktbrevPdf" title="CMR.PDF" style="vertical-align:middle;cursor:pointer;" src="resources/images/pdf.png" width="14" height="14" border="0" alt="CMR. PDF">
							</td>
   						</tr>
   						<tr>
							<td class="text14" align="left" >
								<input type="checkbox" name="ffType" id="ffType" value="ff">
								<span id="alinkFFaktPdf" >Ferdigmeldte-fakturaer</span>
							</td>
							
   						</tr>
   						<tr>
							<td colspan="2" class="text14" align="left" >
								<input type="checkbox" name="aordType" id="aordType" value="aord">
								<span id="alinkAordPdf" >Arbeidsordre</span>
								<c:choose>
								<c:when test="${not empty model.record.hepro}">
									<select id="aordDocumentType" name="aordDocumentType" class="inputTextMediumBlue" style="font-size:11px;background-color:#EEEEEE;"  >
										<option value="S">Single</option>
			 							<option value="I">Intern</option>
			 							<option value="E">Ekstern</option>
									</select>
								</c:when>
								<c:otherwise>
									<input type="hidden" id="aordDocumentType" name="aordDocumentType" value="S">
								</c:otherwise>
								</c:choose>
							</td>
							
   						</tr>
   						<tr height="15"><td></td></tr>
						<tr>
							<td colspan="3" class="text14MediumBlue" align="left">
								<label id="printStatus"></label>
							</td>
						</tr>
					</table>
					</form>
			</div>
		</td>
		</tr>
			
			
			
		
	
</table>	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
