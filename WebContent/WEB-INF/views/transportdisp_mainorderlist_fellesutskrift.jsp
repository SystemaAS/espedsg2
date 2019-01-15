<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>
<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTransportDisp.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_mainorderlist_fellesutskrift.js?ver=${user.versionEspedsg}"></SCRIPT>
	<%--<SCRIPT type="text/javascript" src="resources/js/trorFkeys_landimport.js?ver=${user.versionEspedsg}"></SCRIPT> --%>
	
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	.ui-datepicker { font-size:9pt;}
	</style>
	


<table width="100%"  class="text14" cellspacing="0" border="0" cellpadding="0">
	<tr>
	<td>
	<%-- tab container component --%>
	<table width="100%"  class="text14" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25">
			<td width="20%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkOrderListId" style="display:block;" id="ordersOpen" href="transportdisp_mainorderlist.do?action=doFind&avd=${searchFilter.wssavd}">
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink"><spring:message code="systema.transportdisp.workflow.trip.all.openorders.tab"/></font>&nbsp;<font class="text10Orange">F3</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<%--<a id="alinkTripListId" style="display:block;" href="transportdisp_workflow_getTrip.do?user=${user.user}&tuavd=${searchFilter.avd}&tupro=${searchFilter.tur}">--%>
				<a id="alinkTripListId" style="display:block;" href="transportdisp_workflow_getTrip.do?user=${user.user}&tuavd=${searchFilter.wssavd}&tupro=">
					<img style="vertical-align:bottom;" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink"><spring:message code="systema.transportdisp.workflow.trip.tab"/></font>&nbsp;<font class="text10Orange">F9</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="18%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align:middle;" src="resources/images/printer2.png" width="12px" height="12px" border="0" alt="fellesutskrift">
				<font class="tabLink"><spring:message code="systema.transportdisp.fellesutskrift.tab"/></font>
			</td>
			<td width="70%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>

		 	<%--
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a class="text14" onClick="setBlockUI(this);" href="tror_mainorderlist.do?action=doFind&avd=${model.avd}&sign=${model.sign}" > 	
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.tror.orderlist.tab"/></font>&nbsp;<font class="text10Orange">F2</font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a tabindex=-1 id="createNewOrderTabIdLink" style="display:block;" runat="server" href="#">
					<img style="vertical-align:middle;" src="resources/images/add.png" width="12px" height="12px" border="0" alt="create new">
					<font class="tabDisabledLink"><spring:message code="systema.tror.createnew.order.tab"/></font>
				</a>
				
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="15%" valign="bottom" class="tab" align="center" nowrap>
				<img style="vertical-align:middle;" src="resources/images/printer2.png" width="12px" height="12px" border="0" alt="fellesutskrift">
				<font class="tabLink"><spring:message code="systema.tror.fellesprint.tab"/></font>
			</td>
			<td width="80%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			 --%>	
		</tr>
	</table>
	</td>
	</tr>
	
	<%-- --------------------------- --%>
	<%-- Validation errors FRONT END --%>
	<%-- --------------------------- --%>
	<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
	<tr height="5"><td></td></tr>
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
	
	<tr>
		<td>
			<form action="transportdisp_mainorderlist_fellesutskrift.do" name="transportDispFellesutskriftForm" id="transportDispFellesutskriftForm" method="post">
			<%-- this table wrapper is necessary to apply the css class with the thin border --%>
			<table style="width:100%" id="wrapperTable" class="tabThinBorderWhite" cellspacing="0" border="0" cellpadding="0">
			<%--for F-Keys shortcuts. Used only in trorFkeys_...js 
			<input type="hidden" name="fkeysavd" id="fkeysavd" value='${model.avd}'>
			<input type="hidden" name="fkeysopd" id="fkeysopd" value='${model.opd}'>
			<input type="hidden" name="fkeyssign" id="fkeyssign" value='${model.sign}'>
			<%-- --%>
			<input type="hidden" name="action" id="action" value='${model.action}'>
			
 	   	 	<tr>
				<td align="center" width="100%">
					<table width="100%" cellspacing="0" border="0">
 	   	 				<tr height="15px"><td ></td></tr>
 	   	 				<tr>
							<td  >
							<table width="50%" border="0">
								<tr>
									<td class="text14" title="wsavd">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.avd"/></td>
									<td class="text14" title="wssg">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.sign"/></td>
									<td class="text14" title="wspro">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.turnr"/></td>
									<td class="text14" title="wsopd">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.oppdragnr"/></td>
									<td class="text14" title="wsgn">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.godsnr"/></td>
									<td class="text14" title="wsdt1">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.godsnr"/> fra dato</td>
									
								</tr>
								<tr>
									<td class="text14" ><input type="text" class="inputTextMediumBlue"  name="wsavd" id="wsavd" size="5" maxlength="4" value='${model.record.wsavd}'></td>
									<td class="text14" ><input type="text" class="inputTextMediumBlue"  name="wssg" id="wssg" size="5" maxlength="3" value='${model.record.wssg}'></td>
									<td class="text14" ><input type="text" class="inputTextMediumBlue"  name="wspro" id="wspro" size="10" maxlength="8" value='${model.record.wspro}'></td>
									<td class="text14" ><input type="text" class="inputTextMediumBlue"  name="wsopd" id="wsopd" size="10" maxlength="7" value='${model.record.wsopd}'></td>
									<td class="text14" ><input type="text" class="inputTextMediumBlue"  name="wsgn" id="wsgn" size="17" maxlength="15" value='${model.record.wsgn}'></td>
									<td class="text14" ><input type="text" class="inputTextMediumBlue"  name="wsdt1" id="wsdt1" size="10" maxlength="8" value='${model.record.wsdt1}'></td>
								</tr>	
							</table>
							</td>
						</tr>
						<tr height="5px"><td ></td></tr>

						<tr>
						<td>	
							<table width="95%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="15">
						 			<td class="text12White" align="left" >
						 				<b>&nbsp;&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.parameter"/></b>
					 				</td>
				 				</tr>
			 				</table>
							<table width="95%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
						 		<tr height="15"><td class="text" align="left"></td></tr>
						 		<tr>
							 		<td>
								 		<table width="100%" border="0" cellspacing="0" cellpadding="0">
									 		<tr>
									 			<td class="text14" align="left" title="of/wsdt2">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.oppdformular"/>&nbsp;og fra dato</td>
									            <td class="text14" align="left" title="vf">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.styckkfraktbrev"/></td>
									            <td class="text14" align="left" title="tpfb">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.tpassfraktbrev"/></td>
									            <td class="text14" align="left" title="todo">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.fsad"/></td>
									            <td class="text14" align="left" title="iffb">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.inlandflyfraktbrev"/></td>
									            <td class="text14" align="left" title="loss">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.losselista"/></td>
									            <td class="text14" align="left" title="ffak">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.ffaktura"/></td>
									       		<td class="text14" align="left" title="cm">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.cmr"/></td>
									       		<td class="text14" align="left" title="sakode/satype">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.sArk"/></td>
									       		<td class="text14" align="left" title="wssum">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.belopLabels"/></td>
									       		
									        </tr>
									        <tr>
									 			<td align="left">
								        			<select class="inputTextMediumBlue" name="of" id="of">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${model.record.of == 'J'}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${model.record.of == 'N'}"> selected </c:if> >Nei</option>
													</select>
													<input type="text" class="inputTextMediumBlue"  name="wsdt2" id="wsdt2" size="8" maxlength="6" value='${model.record.wsdt2}'>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="vf" id="vf">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${model.record.vf == 'J'}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${model.record.vf == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="tpfb" id="tpfb">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${model.record.tpfb == 'J'}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${model.record.tpfb == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="todo" id="todo">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${Xmodel.record.fakdm == 'J' || empty Xmodel.record.fakdm}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${Xmodel.record.fakdm == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="iffb" id="iffb">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${model.record.iffb == 'J'}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${model.record.iffb == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="loss" id="loss">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${model.record.loss == 'J'}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${model.record.loss == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="ffak" id="ffak">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${model.record.ffak == 'J'}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${model.record.ffak == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<select class="inputTextMediumBlue" name="cm" id="cm">
								 						<option value="">-velg-</option>
									 				  	<option value="J"<c:if test="${model.record.cm == 'J'}"> selected </c:if> >Ja</option>
														<option value="N"<c:if test="${model.record.cm == 'N'}"> selected </c:if> >Nei</option>
													</select>
												</td>
												<td align="left">
								        			<input type="text" class="inputTextMediumBlue"  name="sakode" id="sakode" size="4" maxlength="2" value='${model.record.sakode}'>
								        			<input type="text" class="inputTextMediumBlue"  name="satype" id="satype" size="2" maxlength="1" value='${model.record.satype}'>
												</td>	
												<td align="left">
								        			<select class="inputTextMediumBlue" name="wssum" id="wssum">
								 						<option value="J"<c:if test="${model.record.wssum == 'J' || empty model.record.wssum}"> selected </c:if> >Beløp</option>
														<option value="L"<c:if test="${model.record.wssum == 'L'}"> selected </c:if> >Labels</option>
													</select>
												</td>
												
																			
									        </tr>
									        <tr height="10px"><td class="text" align="left"></td></tr>
									        <tr>
									 			<td valign="top" colspan="3">
									 				<table >
									 					<tr>
									 						<td colspan="3" class="text14" align="left" title="wsms1-6">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.faktoppdtyper"/></td>
									 					</tr>
									 					<tr>
												        	<td align="left">
											        			<input type="text" class="inputTextMediumBlue"  name="wsms1" id="wsms1" size="4" maxlength="2" value='${model.record.wsms1}'>
											        			<input type="text" class="inputTextMediumBlue"  name="wsms2" id="wsms2" size="4" maxlength="2" value='${model.record.wsms2}'>
											        			<input type="text" class="inputTextMediumBlue"  name="wsms3" id="wsms3" size="4" maxlength="2" value='${model.record.wsms3}'>
											        			<input type="text" class="inputTextMediumBlue"  name="wsms4" id="wsms4" size="4" maxlength="2" value='${model.record.wsms4}'>
											        			<input type="text" class="inputTextMediumBlue"  name="wsms5" id="wsms5" size="4" maxlength="2" value='${model.record.wsms5}'>
											        			<input type="text" class="inputTextMediumBlue"  name="wsms6" id="wsms6" size="4" maxlength="2" value='${model.record.wsms6}'>
											        		</td>		
												        </tr>
									 				</table>
									 			</td>
									 			<td valign="top" colspan="3">
									 				<table >
									 					<tr>
									 						<td colspan="3" class="text14" align="left" title="todo">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.utelatoppdtyper"/></td>
									 					</tr>
									 					<tr>
												        	<td align="left">
											        			<input type="text" class="inputTextMediumBlue"  name="wsot1" id="wsot1" size="4" maxlength="2" value='${model.record.wsot1}'>
											        			<input type="text" class="inputTextMediumBlue"  name="wsot2" id="wsot2" size="4" maxlength="2" value='${model.record.wsot2}'>
											        			<input type="text" class="inputTextMediumBlue"  name="wsot3" id="wsot3" size="4" maxlength="2" value='${model.record.wsot3}'>
											        			<input type="text" class="inputTextMediumBlue"  name="wsot4" id="wsot4" size="4" maxlength="2" value='${model.record.wsot4}'>
											        			<input type="text" class="inputTextMediumBlue"  name="wsot5" id="wsot5" size="4" maxlength="2" value='${model.record.wsot5}'>
											        			
											        		</td>		
												        </tr>
									 				</table>
									 			</td>
									 			
									 			<td valign="top" colspan="3">
									 				<table class="tableBorderWithRoundCornersLightGray"	>
									 					<tr>
									 						<td class="text14MediumBlue" align="left" title="jbk">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.jobbko"/></td>
									            			<td class="text14MediumBlue" align="left" title="wsprt">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.avskriver"/></td>
									            			<td class="text14MediumBlue" align="left" title="wsprt2">&nbsp;<spring:message code="systema.transportdisp.fellesutskrift.label.avskriver.mlapp"/></td>
									            		</tr>
									            		<tr>
									            			<td align="left">
											        			<select class="inputTextMediumBlue" name="jbk" id="jbk">
											 						<option value="">-velg-</option>
												 				  	<option value="J"<c:if test="${model.record.jbk == 'J' || empty model.record.jbk}"> selected </c:if> >Ja</option>
																	<option value="N"<c:if test="${model.record.jbk == 'N'}"> selected </c:if> >Nei</option>
																</select>
															</td>
															<td align="left">
											        			<input type="text" class="inputTextMediumBlue"  name="wsprt" id="wsprt" size="12" maxlength="10" value='${model.record.wsprt}'>
											        		</td>
											        		<td align="left">
											        			<input type="text" class="inputTextMediumBlue"  name="wsprt2" id="wsprt2" size="12" maxlength="10" value='${model.record.wsprt2}'>
											        		</td>		
									            		</tr>
									 				</table>
									 			</td>
									 			<td valign="bottom">
									 				<%---TODO COVI after test on OPPDR.REG
									 				<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.transportdisp.submit.save"/>'>
									 				--%>
									 			</td>
									        </tr>
									        
									        
								        </table>
							        </td>
						        </tr>
						        <tr height="10px"><td class="text" align="left"></td></tr>
		        	        </table>
						</td>
						</tr>	
						<tr height="10px"><td class="text" align="left"></td></tr>
					</table>
				</td>
			</tr>											
 	   	 	<tr height="3"><td></td></tr>
 			</table>
 			</form>
		</td>
	</tr>
	
	<tr height="10"><td ></td></tr>
		
		
	<%-- Pop-up window 
		<tr>
		<td>
			<div id="dialogCreateNewOrder" title="Dialog">
				<form  action="Xtror_mainordergate.do" name="createNewOrderForm" id="createNewOrderForm" method="post">
				 	<input type="hidden" name="actionGS" id="actionGS" value='doUpdate'/>
					<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
					
					<p class="text14" >&nbsp;<spring:message code="systema.tror.order.suborder.title.types"/></p>
					 				
					<table>
						<tr>
							<td class="text14MediumBlue">Type&nbsp;
								<%--
								<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlue11MandatoryField" name="selectedType" id="selectedType">
									<option value="A"><spring:message code="systema.tror.order.suborder.landimport"/></option>
									<option value="B"><spring:message code="systema.tror.order.suborder.landexport"/></option>
									<option value="C"><spring:message code="systema.tror.order.suborder.flyimport"/></option>
									<option value="D"><spring:message code="systema.tror.order.suborder.flyexport"/></option>
								</select>
								&nbsp;&nbsp;<div style="display:inline;" id="imagePreview"></div>
								 
							</td>
						</tr>
						<tr>
							<td class="text14MediumBlue">Avd&nbsp;&nbsp;
								<select required oninvalid="this.setCustomValidity('Obligatorisk')" oninput="setCustomValidity('')" class="inputTextMediumBlueMandatoryField" name="heavd" id="heavd" >
			 						<c:forEach var="record" items="${model.avdList}" >
				 				  		<option value="${record.koakon}"<c:if test="${model.avd == record.koakon}"> selected </c:if> >${record.koakon}</option>
									</c:forEach>  
								</select>
							</td>
						</tr>
					</table>
						
				</form>
			</div>
		</td>
		</tr>	
		--%>
			
	</table>
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->
