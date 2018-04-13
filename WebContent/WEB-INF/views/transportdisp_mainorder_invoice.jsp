<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerTransportDisp.jsp" />
<!-- =====================end header ==========================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/jquery.calculator.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/jquery-ui-timepicker-addon.js"></SCRIPT>
	<SCRIPT type="text/javascript" src="resources/js/transportdispglobal_edit.js?ver=${user.versionEspedsg}"></SCRIPT>			
	<SCRIPT type="text/javascript" src="resources/js/transportdisp_mainorder_invoice.js?ver=${user.versionEspedsg}"></SCRIPT>
	<%-- for dialog popup --%>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
	
	<style type = "text/css">
	.ui-dialog{font-size:10pt;}
	
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
	
	</style>
	
	<%-- tab container component --%>
	<table width="100%"  class="text11" cellspacing="0" border="0" cellpadding="0">
		<tr height="2"><td></td></tr>
		<tr height="25">
			<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkOrderListId" style="display:block;" href="transportdisp_mainorderlist.do?action=doFind&avd=${recordOrderTransportDisp.heavd}">
					<img style="vertical-align:middle;" src="resources/images/bulletGreen.png" width="6px" height="6px" border="0" alt="open orders">
					<font class="tabDisabledLink"><spring:message code="systema.transportdisp.workflow.trip.all.openorders.tab"/></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<%-- <a id="alinkTripListId" style="display:block;" id="ordersOpen" href="transportdisp_workflow_getTrip.do?user=${user.user}&tuavd=${recordOrderTransportDisp.heavd}&tupro=${model.parentTrip}"> --%>
				<a id="alinkTripListId" style="display:block;" id="ordersOpen" href="transportdisp_workflow_getTrip.do?user=${user.user}&tuavd=${recordOrderTransportDisp.heavd}&tupro=">
					<img style="vertical-align:bottom;" src="resources/images/list.gif" border="0" alt="general list">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.transportdisp.workflow.trip.tab"/></font>
				</a>
			</td>
			<%-- Only present this option with there is a trip behind the order, otherwise this is a brand new order without any previous trip connection --%>
			<c:if test="${not empty model.parentTrip}">
				<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
				<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
					<a id="alinkParentTripId" style="display:block;" id="orderList" href="transportdisp_mainorderlist.do?action=doFind&wssavd=${recordOrderTransportDisp.heavd}&wstur=${model.parentTrip}">
						<img title="Add" style="vertical-align:bottom;" src="resources/images/addOrder.png" width="14" hight="15" border="0" alt="add">
						<font class="tabDisabledLink">&nbsp;<spring:message code="systema.transportdisp.open.orderlist.trip.label"/>&nbsp;${model.parentTrip}</font>
					</a>
				</td>
			</c:if>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="18%" valign="bottom" class="tabDisabled" align="center" nowrap>
				<a id="alinkOrderId" style="display:block;" id="order" href="transportdisp_mainorder.do?hepro=${model.parentTrip}&heavd=${recordOrderTransportDisp.heavd}&heopd=${recordOrderTransportDisp.heopd}">
					<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">
					<font class="tabDisabledLink">&nbsp;<spring:message code="systema.transportdisp.update.ourref.tab"/><font class="text12MediumBlue">&nbsp;${recordOrderTransportDisp.heavd}/${recordOrderTransportDisp.heopd}</font></font>
				</a>
			</td>
			<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
			<td width="18%" valign="bottom" class="tab" align="center" nowrap>
				<img title="Add" style="vertical-align:bottom;" src="resources/images/invoice2.png" width="16" hight="16" border="0" alt="invoice">
				<font class="tabLink">&nbsp;<spring:message code="systema.transportdisp.orders.invoice.tab"/>&nbsp;</font>
			</td>
			<c:choose>
				<c:when test="${not empty model.parentTrip || not empty recordOrderTransportDisp.heopd}">
					<%-- Since there will be an extra tab above we must compensate the phantom space here (when applicable) --%>
					<td width="30%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>		
				</c:when>
				<c:otherwise>
					<td width="50%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>		
				</c:otherwise>
			</c:choose>
		</tr>
	</table>
	<%-- -------------------------------- --%>	
 	<%-- tab area container MASTER TOPIC  --%>
	<%-- -------------------------------- --%>
 	<table width="100%" class="tabThinBorderWhite" border="0" cellspacing="0" cellpadding="0">
		<tr height="15"><td colspan="2">&nbsp;</td></tr>	
		
		<tr>
		<td >
		<table border="0" width="95%" align="center">
			<tr>
	 			<td >		
	 				<%-- MASTER Topic header --%>
	 				<table width="80%" align="left" class="formFrameHeaderTransparent" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12MediumBlue">
				 				&nbsp;Avd./Oppd.nr:&nbsp;<b>${recordOrderTransportDisp.heavd}/${recordOrderTransportDisp.heopd}</b>
				 				&nbsp;&nbsp;Tur.:&nbsp;<b>${model.parentTrip}</b>
				 				<%--&nbsp;&nbsp;Prod.kode:&nbsp;<b>${Xmodel.todo}</b> --%>
				 				&nbsp;&nbsp;Fvekt:&nbsp;<b>${recordOrderTransportDisp.hefbv}</b>
			 				</td>
		 				</tr>
		 				
	 				</table>
					<%-- MASTER Topic information [it is passed through a session object: XX] --%>
				 	<table height="50" width="99%" align="left" class="formFrameTitaniumWhite" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="2"><td class="text" align="left" colspan="2"></td></tr>
				 		<tr>
					 		<td width="45%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="1">
						 			<tr>
							            <td class="text11Bold" align="left" >Selger</td>
							            <td class="text11" align="left">Fakturapart&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTransportDisp.heknsf}&nbsp;${recordOrderTransportDisp.henasf}</td>
							        </tr>
							 		<tr>
										<td class="text11" align="left" >&nbsp;&nbsp;</td>
							            <td class="text11" align="left">Avt.kunr.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTransportDisp.avtknrs}&nbsp;${recordOrderTransportDisp.avtknavs}</td>
							        </tr>
							        <tr>
							        	<td class="text11Bold" align="left" >Kjøpers</td>
							            <td class="text11" align="left">Fakturapart&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTransportDisp.heknkf}&nbsp;${recordOrderTransportDisp.henakf}</td>
							        </tr>
							       <tr>
										<td class="text11" align="left" >&nbsp;&nbsp;</td>
							            <td class="text11" align="left">Avt.kunr&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTransportDisp.avtknrk}&nbsp;${recordOrderTransportDisp.avtknavk}</td>
							        </tr>
			        	        </table>
					        </td>
					        <td width="27%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
							 		<tr>
							            <td width="30%" class="text11" align="left">Sign.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTransportDisp.hesg}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Dato/Per&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordTopicTODO.sinak}</td>
							        </tr>
									<tr>
							            <td width="30%" class="text11" align="left">Godsnr.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${XrecordTopicTODO.siadk1}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Oppd.type / Frank.&nbsp;&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTransportDisp.heot} / ${recordOrderTransportDisp.hefr}</td>
							        </tr>
							        
			        	        </table>
					        </td>
					        <td width="27%">
						 		<table width="100%" border="0" cellspacing="1" cellpadding="0">
						 			<tr>
							            <td width="30%" class="text11" align="left">Avs.ref.&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTransportDisp.herfa}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Mott.ref&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTransportDisp.herfk}</td>
							        </tr>
							 		<tr>
							            <td width="30%" class="text11" align="left">Vekt/M3/Lm&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTransportDisp.hevkt}/${recordOrderTransportDisp.hem3}/${recordOrderTransportDisp.helm}</td>
							        </tr>
							        <tr>
							            <td width="30%" class="text11" align="left">Tilleggsg&nbsp;</td>
							           	<td class="text11MediumBlue" align="left">${recordOrderTransportDisp.hestn1}${recordOrderTransportDisp.hestn2}${recordOrderTransportDisp.hestn3}${recordOrderTransportDisp.hestn4}${recordOrderTransportDisp.hestn5}
							     			&nbsp;&nbsp;&nbsp;<c:if test="${recordOrderTransportDisp.hestn6 == 'P'}">Protected</c:if>
							     		</td>      	
							        </tr>
							        
			        	        </table>
					        </td>
				        </tr>
					</table>          
            	</td>
           	</tr> 
           	<%-- ---------------------- --%>
           	<%-- LIST of existent ITEMs --%>
           	<%-- ---------------------- --%>
           	<tr>
				<td>
					<table width="100%" cellspacing="0" border="0" cellpadding="0">
	    				<%-- separator --%>
	        			<tr height="10"><td></td></tr> 
						<tr >
							<td>
							<form name="createNewItemLine" id="createNewItemLine" method="post" action="TODO.do">
								<input type="hidden" name="applicationUser" id="applicationUser" value='${user.user}'>
								<input type="hidden" name="avd" id="avd" value='${recordOrderTransportDisp.heavd}'>
		 						<input type="hidden" name="opd" id="opd" value='${recordOrderTransportDisp.heopd}'>
		 						<input type="hidden" name="hepro" id="hepro" value='${recordOrderTransportDisp.hepro}'>
		 						<input type="hidden" name="action" id="action" value='doFetch'>
				 				<input type="hidden" name="sign" id="sign" value='${recordOrderTransportDisp.hesg}'>
								
								<table width="80%" cellspacing="0" border="0" cellpadding="0">
									
									<tr>
										<td class="text12" valign="bottom">
											<b>&nbsp;Antall varelinjer&nbsp;&nbsp;</b><font class="text12MediumBlue"><b>${model.container.totalNumberOfItemLines}</b></font>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<img onMouseOver="showPop('lineTypes_info');" onMouseOut="hidePop('lineTypes_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
											Type
							 				<select class="text11" id="linType" name="linType">
						           				<option value="A" <c:if test="${model.container.type == 'A'}"> selected </c:if> >Alle</option>
						           				<option value="I" <c:if test="${model.container.type == 'I'}"> selected </c:if> >Inntektslinj.</option>
						           				<option value="O" <c:if test="${model.container.type == 'O'}"> selected </c:if> >Åpne inntektslinj.</option>
							           		</select>
							           		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							           		<button name="readyMarkButton" id="readyMarkButton" class="buttonGrayWithGreenFrame" type="button" >&nbsp;Ferdigmelde</button>
							           		<c:choose>
								           		<c:when test="${not empty model.container.readyMarkStatus}">
								           			<font id="readyMarkInvoice" class="text12MediumBlue">&nbsp;Status:&nbsp;${model.container.readyMarkStatus}</font>
								           		</c:when>
								           		<c:otherwise>
								           			<%-- this id must always be present since AJAX call must fill upp with text... --%>
								           			<font id="readyMarkInvoice" class="text12MediumBlue"></font>
								           		</c:otherwise>
							           		</c:choose>
							           		<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:0px; top:0px;" id="lineTypes_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Linjetyper</b>
							           			<div>
							           				<ul>
								           				<li><b>Alle</b>=Vis ALLE linjer (vis inntektslinjer uavhengig av status OG også kostnadslinjer...)</li>
								           				<li><b>Inntektslinj</b>=Vis kun Inntektslinjer=kun linjer for utgående faktura vises (ikke kostnader / ikke slettemerkedede)</li>
		    											<li><b>Åpne inntektslinj.</b>= Open = Vis kun ÅPNE inntekstlinjer = Linjer som ennå ikke er fakturert / kan endres.. </li>
	    											</ul>
	    										</div>	 
						           			</font>
											</span>
											</div>
					            		</td>
					            		<td></td>
									</tr>
									<tr height="2"><td></td></tr>
								</table>
							</form>
							</td>
						</tr> 
						<tr>
							<td class="ownScrollableSubWindow" style="width:99%; height:15em;">
								<table width="100%" cellspacing="0" border="0" cellpadding="0">
									<tr class="tableHeaderField" height="20" valign="left">
										<td class="tableHeaderFieldFirst" align="center">&nbsp;<span title="...">Lin&nbsp;</span></td>
										<td class="tableHeaderField" align="center">&nbsp;<span title="fali">Modus&nbsp;</span></td>
									    <td class="tableHeaderField">&nbsp;<span title="fask">SK&nbsp;</span></td>   
					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="favk">VK&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="faVT/stdVt">Gebyrtekst&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="faval">Valuta&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap align="right">&nbsp;<span title="fabelv">Beløp&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap align="right">&nbsp;<span title="fabeln">Beløp NOK&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="fakdm">M&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="fakda">A&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="faopko">S&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="fafakt">Fakt.nr.&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="fadato">Fakt.dato&nbsp;</span></td>
   					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="peri">Periode&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="fajour">Journal&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="faaccn">Konto&nbsp;</span></td>
					                    <td class="tableHeaderField" colspan="2" nowrap>&nbsp;<span title="fakunr/knavn">Kunde&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap>&nbsp;<span title="fabelu">Budsjett&nbsp;</span></td>
					                    <td class="tableHeaderField" nowrap>&nbsp;Slett&nbsp;</td>
					               </tr> 
					               
				 					  <c:forEach items="${model.list}" var="record" varStatus="counter">    
							               <c:choose>           
							                   <c:when test="${counter.count%2==0}">
							                   	   <%--highlight cost lines --%>	
							                       <tr class="tableRow" height="20" <c:if test="${record.fakda == 'K'}"> style="background-color:#FFBABA;" </c:if> >
							                   </c:when>
							                   <c:otherwise> 
							                       <tr class="tableOddRow" height="20" <c:if test="${record.fakda == 'K'}"> style="background-color:#FFBABA;" </c:if> >
							                   </c:otherwise>
							               </c:choose>
							               <td class="tableCellFirst" align="center">${record.fali}</td>
							               <td class="tableCell" align="center" >&nbsp;
							               
							               		<c:choose>
								               		<c:when test="${record.faopko==' ' || record.faopko=='A' || record.faopko=='B' || record.faopko=='C'}">
								               			<a id="recordUpdate_${record.fali}" href="#" onClick="getInvoiceItemData(this);">
							               				<img title="Update" style="vertical-align:bottom;" src="resources/images/update.gif" border="0" alt="update">&nbsp;
								               			</a>
								               		</c:when>
								               		<c:otherwise>
								               			<c:if test="${not empty record.fali}">
							               					<img style="display:inline-block; cursor:pointer;" onClick="showPop('economyMatrixInfo_${counter.count}');" title="Show" style="vertical-align:bottom;" src="resources/images/info4.png" height="11px" width="11px" border="0" alt="show">
							               					
							               					<%-- ------------------------------- --%>
															<%-- READ ONLY view of specific line --%>
															<%-- ------------------------------- --%>
															<span style="position:absolute; left:150px; top:350px; height:310px; width:750px;" id="economyMatrixInfo_${counter.count}" class="popupWithInputText tableBorderWithRoundCorners3D "  >
												           		<div class="text10" align="left">
												           		<table align="left" class="tableBorderWithRoundCornersLightGray">
																    <tr height="2"><td></td></tr>
																    <tr>
																    	<td class="text14Bold" colspan="5">&nbsp;Varelinje:&nbsp;<font class="text12OrangeBold">${record.fali}</font>
																    	&nbsp;&nbsp;&nbsp;&nbsp;Kunde&nbsp;<font class="text12OrangeBold">${record.knavn}</font>
																    	</td>
																    </tr>
																    <tr height="5"><td></td></tr>
																    <tr>
															    		<td class="tableHeaderFieldFirst12" title="fask">SK</td>
															    		<td class="tableHeaderField12" title="favk">Geb</td>
															    		<td class="tableHeaderField12" title="todo">Tnr.</td>
															    		<td class="tableHeaderField12" title="faval">Valuta</td>
															    		<td class="tableHeaderField12" title="fabelv">Val.beløp</td>
															    		<td class="tableHeaderField12" title="fabeln">Beløp NOK</td>
															    		<td class="tableHeaderField12" title="fakdm">MVA</td>
															    		<td class="tableHeaderField12" title="faVT/stdVt">Friteskt / Gebyrtekst</td>
															    		<td class="tableHeaderField12" title="fakduk/facu33/fabelu">Utgift</td>
															    		
															    	</tr>
															    	<tr class="tableRow" <c:if test="${record.fakda == 'K'}"> style="background-color:#FFBABA;" </c:if>>
															    		<td class="tableCellFirst14" >&nbsp;${record.fask}</td>
															    		<td class="tableCell14" >&nbsp;${record.favk}</td>
															    		<td class="tableCell14" >&nbsp;${Xrecord.todo}</td>
															    		<td class="tableCell14" >&nbsp;${record.faval}</td>
															    		<td class="tableCell14" <c:if test="${record.fakda == 'K' }"> style="color:#D8000C;" </c:if>>&nbsp;${record.fabelv}</td>
															    		<td class="tableCell14" <c:if test="${record.fakda == 'K' }"> style="color:#D8000C;" </c:if>>&nbsp;${record.fabeln}</td>
															    		<td class="tableCell14" >&nbsp;${record.fakdm}</td>
															    		<td class="tableCell14" >&nbsp;${record.stdVt}
														               		<c:if test="${not empty record.faVT}">&nbsp;/&nbsp;${record.faVT}</c:if>
														               	</td>
													               		<td class="tableCell14" >&nbsp;${record.fakduk}&nbsp;${record.facu33}&nbsp;${record.fabelu}</td>
															    		
															    	</tr>
															    	<tr height="10"><td></td></tr>
															    	<tr>
															    	<td colspan="20">
																    	<table> 
																	    	<tr>	
																	    		<td class="text14" ><span title="falevn">Leverandør</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;" align="left"><label name="v_falevn" id="v_falevn">&nbsp;${record.falevn}&nbsp;</label></td>
																    			<td colspan="2" class="tableCell14" style="background-color:#FFFFFF;" align="left"><label name="v_lnavn" id="v_lnavn">&nbsp;${record.lnavn}&nbsp;</label></td>
																    			
																    			<td class="text14" ><span title="fakunr/knavn">&nbsp;&nbsp;Kunde</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_fakunr" id="v_fakunr">&nbsp;${record.fakunr}&nbsp;</label></td>
																    			<td colspan="2" class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_knavn" id="v_knavn">&nbsp;${record.knavn}&nbsp;</label></td>
																	    	</tr>
																	    	<tr>	
																    			<td class="text14" align="left"><span title="faopko">Status</span></td>
															    				<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_faopko" id="v_faopko">&nbsp;${record.faopko}&nbsp;</label></td>
															    				<td class="text14" align="left"><span title="facd11">&nbsp;&nbsp;Samm.</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_facd11" id="v_facd11">&nbsp;${record.facd11}&nbsp;</label></td>
																	    		<td class="text14" ><span title="peri">&nbsp;&nbsp;Periode</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_peri" id="v_peri">&nbsp;${record.peri}&nbsp;</label></td>
																	    	</tr>
																	    	<tr height="15"><td></td></tr>
																	    	<tr>	
																	    		<td class="text14" ><span title="fafakt">Faktnr/Bilg.nr.</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_fafakt" id="v_fafakt">&nbsp;${record.fafakt}&nbsp;</label></td>
																    			<td class="text14" ><pan title="fadato">&nbsp;&nbsp;Faktdato</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_fadato" id="v_fadato">&nbsp;${record.fadato}&nbsp;</label></td>
																    			<td class="text14" ><span title="fadaff">&nbsp;&nbsp;Forfallsdato</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_fadaff" id="v_fadaff">&nbsp;${record.fadaff}&nbsp;</label></td>
																    			<td class="text14" ><span title="todo">&nbsp;&nbsp;Kurs</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_todo" id="v_todo">&nbsp;${Xrecord.todo}&nbsp;</label></td>
																	    	</tr>
																	    	<tr>	
																	    		<td class="text14" ><span title="fajour">Journal</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_fajour" id="v_fajour">&nbsp;${record.fajour}&nbsp;</label></td>
																    			<td class="text14" ><span title="fafrbn">&nbsp;&nbsp;Fraktbrev.</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_fafrbn" id="v_fafrbn">&nbsp;${record.fafrbn}&nbsp;</label></td>
																    			<td class="text14" ><span title="faaccn/faavdr">&nbsp;&nbsp;Kto</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_faavdr" id="v_faavdr">&nbsp;${record.faaccn}&nbsp;</label></td>
																    			<td class="text14" ><span title="faaccn/faavdr">&nbsp;&nbsp;Kst</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_faavdr" id="v_faavdr">&nbsp;${record.faavdr}&nbsp;</label></td>
																    			
																	    	</tr>
																	    	<tr>
																	    		<td class="text14" ><span title="fabær">Bærer</span></td>
																    			<td class="tableCell14" style="background-color:#FFFFFF;"  align="left"><label name="v_todo" id="v_fabær">&nbsp;${record.fabær}&nbsp;</label></td>
															    			</tr>
																    	<tr height="10"><td></td></tr>
																    	</table>
															    	</td>
															    	</tr>
															    	</table>
																	<table width="92%">
																		<tr height="10"><td></td></tr>
																		<tr align="left" >
																			<td class="text12">
																				<button name="economyMatrixButtonClose_${counter.count}" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('economyMatrixInfo_${counter.count}');">&nbsp;Close</button> 
																			</td>
																		</tr>
																	</table>
																  </div>
															</span>  	
							               				</c:if>
								               		</c:otherwise>
							               		</c:choose>
							               				
								            </td>
							               <td class="tableCell" >&nbsp;${record.fask}</td>
							               <td class="tableCell" >&nbsp;${record.favk}</td>
							               <td class="tableCell" <c:if test="${empty record.fali}"> style="font-weight: bold;" </c:if> >&nbsp;
							               		<c:choose>
								               		<c:when test="${not empty record.faVT}">${record.faVT}</c:when>
								               		<c:otherwise>${record.stdVt}</c:otherwise>
							               		</c:choose>
						               		</td>
							               <td class="tableCell" >&nbsp;${record.faval}</td>
							               <td class="tableCell" align="right" <c:if test="${record.fakda == 'K'}"> style="color:#D8000C;" </c:if>>&nbsp;${record.fabelv}&nbsp;</td>
							               
							               <%--START fabeln --%>
							               <c:choose>
								               <c:when test="${empty record.fali}">
								               		<c:choose>
									               		<c:when test="${fn:contains(record.fabeln, '-')}">
										               		<td class="tableCell" align="right" style="font-weight: bold; color:#D8000C;" >&nbsp;${record.fabeln}&nbsp;</td>
									               		</c:when>
									               		<c:otherwise>
									               			<td class="tableCell" align="right" style="font-weight: bold;" >&nbsp;${record.fabeln}&nbsp;</td>
									               		</c:otherwise>
								               		</c:choose>
								               </c:when>
								               <c:otherwise>
								               		<td class="tableCell" align="right" <c:if test="${record.fakda == 'K'}"> style="color:#D8000C;" </c:if>>&nbsp;${record.fabeln}&nbsp;</td>
								               </c:otherwise>
							               </c:choose>
							               <%-- END fabeln --%>
							               
							               <td class="tableCell" >&nbsp;${record.fakdm}</td>
							               <td class="tableCell" >&nbsp;${record.fakda}</td>
							               <td class="tableCell" >&nbsp;${record.faopko}</td>
							               <td class="tableCell" >&nbsp;${record.fafakt}</td>
							               <td class="tableCell" >&nbsp;${record.fadato}</td>
							               <td class="tableCell" >&nbsp;${record.peri}</td>
							               <td class="tableCell" >&nbsp;${record.fajour}</td>
							               <td class="tableCell" >&nbsp;${record.faaccn}</td>
							               <td class="tableCellOnlyBottomBorder" align="right"><font class="text11MediumBlue">${record.fakunr}</font>&nbsp;</td>
					               		   <td class="tableCell" align="left">&nbsp;${record.knavn}</td>
							               	
							               <td class="tableCell" >&nbsp;${record.fabelu}</td>
											<%-- DELETE cell --%>							           
							               <td class="tableCell" align="center" nowrap>
							                   <c:if test="${record.faopko==' ' || record.faopko=='A' || record.faopko=='B' || record.faopko=='C'}">
							                   		<c:if test="${not empty record.fali}">
								                   		<a style="cursor:pointer;" id="heavd_${recordOrderTransportDisp.heavd}@heopd_${recordOrderTransportDisp.heopd}@fali_${record.fali}" onClick="doPermanentlyDeleteInvoiceLine(this);" tabindex=-1 >
										               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
										               	</a>&nbsp;
									               	</c:if>
									               	<%--
									               	<a onclick="javascript:return confirm('Er du sikker på at du vil slette denne?')" tabindex=-1 href="transportdisp_mainorder_invoice_edit.do?action=doDelete&heavd=${recordOrderTransportDisp.heavd}&heopd=${recordOrderTransportDisp.heopd}&fali=${record.fali}">
									               		<img valign="bottom" src="resources/images/delete.gif" border="0" alt="remove">
									               	</a>&nbsp;
									               	--%>
								               	</c:if>
							               
							               
							            </tr>
								        <%-- this param is used ONLY in this JSP 
								        <c:set var="totalNumberOfItemLines" value="${counter.count}" scope="request" />
								        --%> 
								        <%-- this param is used throughout the Controller --%>
								        <c:set var="numberOfItemLinesInTopic" value="${Xrecord.svln}" scope="request" /> 
								        </c:forEach>
						           
						        </table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="5"><td></td></tr>
			<%-- Validation errors --%>
			<spring:hasBindErrors name="record"> <%-- name must equal the command object name in the Controller --%>
			<tr>
				<td>
	            	<table width="100%" align="left" border="0" cellspacing="0" cellpadding="0">
	            	<tr>
					<td class="textError">					
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
			<%-- ------------------------------------------------- --%>
           	<%-- DETAIL Section - Create Item line PRIMARY SECTION --%>
           	<%-- ------------------------------------------------- --%>
           	<%--
           	<tr>
           		<td class="text12Bold">
					<input tabindex=-1 class="inputFormSubmitStd" type="button" name="initNewLineButton" id="initNewLineButton" onclick="javascript:return alert('Not yet implemented');"  value="<spring:message code="systema.tvinn.sad.import.item.line.init.createnew.submit"/>">
				</td>
			</tr>
			 --%>
           	<tr>
	 			<td >
	 				<form action="transportdisp_mainorder_invoice_edit.do" name="transportDispInvoiceUpdateItemForm" id="transportDispInvoiceUpdateItemForm" method="post">
				 	<%--Required key parameters from the Topic parent --%>
				 	<input type="hidden" name="action" id="action" value='doUpdate'/>
				 	<input type="hidden" name="heopd" id="heopd" value="${recordOrderTransportDisp.heopd}"/>
				 	<input type="hidden" name="heavd" id="heavd" value="${recordOrderTransportDisp.heavd}"/>
				 	<input type="hidden" name="sign" id="sign" value="${recordOrderTransportDisp.hesg}"/>
				 	<input type="hidden" name="hepro" id="hepro" value="${recordOrderTransportDisp.hepro}"/>
				 	<input type="hidden" name="fali" id="fali" value="${model.record.fali}">
				 	
				 	<%-- <input type="hidden" name="numberOfItemLinesInTopic" id="numberOfItemLinesInTopic" value="${numberOfItemLinesInTopic}" /> --%>
				 	
				 	<%-- Topic ITEM CREATE --%>
	 				<table width="99%" align="left" class="formFrameHeader" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="15">
				 			<td class="text12White" align="left" >
				 				<b>&nbsp;&nbsp;V<label onClick="showPop('debugPrintlnAjaxItemFetchAdmin');" >a</label>relinje&nbsp;</b>
				 				
		 									<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="debugPrintlnAjaxItemFetchAdmin" class="popupWithInputText"  >
								           		<div class="text11" align="left">
								           			<label id="debugPrintlnAjaxItemFetchInfo"></label>
								           			<br/>
								           			&nbsp;&nbsp;
								           			<button name="specialInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('debugPrintlnAjaxItemFetchAdmin');">
								           			Close
								           			</button> 
								           		</div>
								        		</span>
		 				
				 				
				 				<img onClick="showPop('updateInfo');" src="resources/images/update.gif" border="0" alt="edit">&nbsp;&nbsp;<font id="editLineNr"></font>
				 				<span style="position:absolute; left:150px; top:200px; width:800px; height:400px;" id="updateInfo" class="popupWithInputText"  >
		           		   			<div class="text12" align="left" style="display:block;width:700px;word-break:break-all;">
		           		   				${XactiveUrlRPGUpdate_TvinnSad}<br/><br/>
		           		   				<button name="updateInformationButtonClose" class="buttonGrayInsideDivPopup" type="button" onClick="hidePop('updateInfo');">Close</button> 
		           		   			</div>
						        </span>  
			 				</td>
		 				</tr>
	 				</table>
					<table width="99%" align="left" class="formFrame" border="0" cellspacing="0" cellpadding="0">
				 		<tr height="12"><td class="text" align="left"></td></tr>
				 		<tr>
					 		<td>
						 		<table  class="tableBorderWithRoundCornersGray" width="100%" border="0" cellspacing="0" cellpadding="0">
						 			<tr height="5"><td class="text" align="left"></td></tr>
						 			<tr >
							 			<td class="text12" align="left">&nbsp;&nbsp;
							 			<img onMouseOver="showPop('sk_info');" onMouseOut="hidePop('sk_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 			<span title="fask">SK</span>
							           	</td>
							           	<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:10px; top:30px; width=350px" id="sk_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>SK</b>
							           			<div>
							           				<p>
							           				Selger(S) /Kjøper(K) .Tast S eller K for å styre denne linjen mot selgers eller kjøpers faktura.
													</p>
													<p>
													<ul>
														<li><b>A</b>=Belastning mot agent. (Gjelder kun for Landmodulen). <br/>
															Fanges opp av ametasystemet og erstattes av linje med X (se under) ved den endelige belastning.
														</li>
														<li><b>X</b>=Fakturalinje mot "fri part". Ikke S eller K. Kundenummer tastes i felt KUNR (X).
														</li>
														<li><b>I</b>=Intern avdelingsbelastning. (Gjelder kun for Transport -modulen.)(Se også felt AVD (I)).
														</li>
														<li><b>F</b>=Flyfraktbrev. Fakturalinjene legges mot mottagersiden på flyfraktbrevet.
														</li>
													</ul>
													</p>
													<p>NB: Ved samlast export vil fakturalinjene ha status "K".</p>
													
	    										</div>	 
						           			</font>
											</span>
										</div>
							           	
							            <td class="text12" align="left"><span title="favk">&nbsp;Geb.</span>
							            	<a tabindex=-1 id="favkIdLink" >
	 											<img id="imgGebyrCodesSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
							            </td>
							            <td class="text12" align="left"><span title="wtnr">
							            	<img onMouseOver="showPop('tnr_info');" onMouseOut="hidePop('tnr_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				Tnr.</span>
							 			</td>
							            <div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:100px; top:20px; width=350px" id="tnr_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Tnr-Tabellnr</b>
							           			<div>
							           				<p>Man kan her, ved manuell oppretting av fakturalinje, angi hvilken bruttotabell gebyrprisen skal hentes fra.</p>
							           				<p>Det er ikke krav til feltet.</p>
	    										</div>	 
						           			</font>
											</span>
											</div>
							            
							            <td class="text12" align="left"><span title="fabelv">&nbsp;Val.beløp</span></td>
					            		<td class="text12" align="left"><span title="faval">&nbsp;Valuta</span></td>
					            		<td class="text12" align="left"><span title="fakdm">&nbsp;MVA&nbsp;</span></td>
						            	<td class="text12" align="left"><span title="faVT/stdVt">&nbsp;Fritekst&nbsp;</span></td>
						            	<td class="text12" align="left"><span title="wantall">&nbsp;Antall&nbsp;</span></td>
						            	<td class="text12" align="left"><span title="fakunr">&nbsp;Kunde</span>
							            	<a tabindex=-1 href="javascript:void(0);" onClick="window.open('transportdisp_workflow_childwindow_customer.do?action=doInit&ctype=il','customerWin','top=300px,left=50px,height=800px,width=900px,scrollbars=no,status=no,location=no')">
	 											<img id="imgAgentSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
							            </td>
						            	
						            	<td class="text12" align="left"><span title="wkurs">&nbsp;Kurs&nbsp;</span></td>
						            	<td class="text12" align="left"><span title="faavdr">&nbsp;&nbsp;&nbsp;Avd(I)</span></td>
						            	<td class="text12" align="left" ><span title="fadocnB">&nbsp;Opd(I)</span></td>
							        </tr>
							        <tr>
						        		<td align="left">&nbsp;
						        			<select class="inputTextMediumBlueMandatoryField" id="fask" name="fask">
						        				<option value="">-select-</option>
						        				<c:choose>
								            		<c:when test="${not empty model.record.fask}">
								           				<option value="S" <c:if test="${model.record.fask == 'S'}"> selected </c:if>>S</option>
								           				<option value="K" <c:if test="${model.record.fask == 'K'}"> selected </c:if>>K</option>
								           				<option value="A" <c:if test="${model.record.fask == 'A'}"> selected </c:if>>A</option>
								           				<option value="X" <c:if test="${model.record.fask == 'X'}"> selected </c:if>>X</option>
								           				<option value="I" <c:if test="${model.record.fask == 'I'}"> selected </c:if>>I</option>
								           				<option value="F" <c:if test="${model.record.fask == 'F'}"> selected </c:if>>F</option>
						           					</c:when>
						           					<c:otherwise>
						           						<option value="S" selected>S</option>
								           				<option value="K" >K</option>
								           				<option value="A" >A</option>
								           				<option value="X" >X</option>
								           				<option value="I" >I</option>
								           				<option value="F" >F</option>
						           					</c:otherwise>
						           				</c:choose>
							           		</select>
										</td>
										<td align="left">
						        			<select class="inputTextMediumBlueMandatoryField" name="favk" id="favk">
						 						<option value="">-select-</option>
							 				  	<c:forEach var="record" items="${model.gebyrCodesList}" >
							 				  		<option value="${record.kgekod}"<c:if test="${model.record.favk == record.kgekod}"> selected </c:if> >${record.kgekod}</option>
												</c:forEach>
											</select>
										</td>
										<td class="text12" align="left">
						            		<input type="text" class="inputText" name="wtnr" id="wtnr" size="5" maxlength="5" value="${model.record.wtnr}">
							            </td>
							            <td class="text12" align="left">
						            		<input type="text" onKeyPress="return amountKey(event)" class="inputTextMediumBlueMandatoryField" name="fabelv" id="fabelv" size="8" maxlength="8" value="${model.record.fabelv}">
							            </td>
										<td align="left" nowrap valign="middle">
							            	<select class="inputTextMediumBlueMandatoryField" name="faval" id="faval">
						 						<option value="">-valuta-</option>
							 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
							 				  		<option value="${currency}"<c:if test="${model.record.faval == currency || (empty model.record.faval && currency=='NOK')}"> selected </c:if> >${currency}</option>
												</c:forEach> 
											</select>
										</td>
						        		<td class="text12" align="left" valign="middle">
						            		<select class="inputTextMediumBlueMandatoryField" id="fakdm" name="fakdm">
						            			<option value="">-select-</option>
						            			<c:choose>
								            		<c:when test="${not empty model.record.fakdm}">
								           				<option value="J" <c:if test="${model.record.fakdm == 'J'}"> selected </c:if> >J</option>
								           				<option value="N" <c:if test="${model.record.fakdm == 'N'}"> selected </c:if> >N</option>
							           				</c:when>
						           					<c:otherwise>
								           				<option value="J" selected>J</option>
								           				<option value="N" >N</option>
						           					</c:otherwise>
						           				</c:choose>
							           		</select>
						            	</td>
							            <td class="text12" align="left" >
							            	<input type="text" class="inputText" name="faVT" id="faVT" size="21" maxlength="20" value="${model.record.faVT}">
							            	<%--
							            	<input type="hidden" name="stdVt" id="stdVt" value="${model.record.stdVt}">
							            	<c:choose>
							            	<c:when test="${not empty model.record.faVT}">
							            		<input type="text" class="inputText" name="freeText" id="freeText" size="21" maxlength="20" value="${model.record.faVT}">
							            	</c:when>
							            	<c:otherwise>
							            		<input type="text" class="inputText" name="freeText" id="freeText" size="21" maxlength="20" value="${model.record.stdVt}">
							            	</c:otherwise>
							            	</c:choose>
							            	 --%>
						            	</td>
							            
							            <td class="text12" align="left" ><input type="text" class="inputText" onKeyPress="return numberKey(event)" name="wantall" id="wantall" size="5" maxlength="5" value="${model.record.wantall}"></td>
							            <td class="text12" align="left">
						            		<input type="text" class="inputText" onKeyPress="return numberKey(event)" name="fakunr" id="fakunr" size="9" maxlength="8" value="${model.record.fakunr}">
							            </td>
							            <td class="text12" align="left" ><input type="text" class="inputText" onKeyPress="return amount(event)" name="wkurs" id="wkurs" size="6" maxlength="8" value="${model.record.wkurs}"></td>
							            <td class="text12">&nbsp;
						        			<input type="text" class="inputText" name="faavdr" id="faavdr" size="5" maxlength="4" value="${model.record.faavdr}">
						        		</td>
						        		<td class="text12" align="left" >
						        			<input type="text" class="inputText" name="fadocnB" id="fadocnB" size="7" maxlength="7" value="${model.record.fadocnB}">
						        		</td>
						        		
							        </tr>
							        <tr height="12"><td class="text" align="left" colspan="20"><hr></td></tr>
							        <tr height="3"><td class="text" align="left"></td></tr>
							        <tr>
							 			<td class="text12" align="left" >&nbsp;
							            	<img onMouseOver="showPop('utgift_info');" onMouseOut="hidePop('utgift_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							 				<span title="fakduk/facu33/fabelu">Utgift</span>
							 				
							            </td>
							            <td class="text12" align="left" >&nbsp;Utgift-Valuta</td>
							            <td class="text12" align="left" >&nbsp;Utgift-Kost.beløp</td>
							            <td class="text12" align="left"><span title="falevn">&nbsp;Leverandør</span>
						            		<a tabindex=-1 id="falevnIdLink" >
	 											<img id="imgSupplierSearch" align="bottom" style="cursor:pointer;" src="resources/images/find.png" height="13px" width="13px" border="0" alt="search">
	 										</a>
						            	</td>
						            	<td class="text12" align="left" ><span title="lnavn">&nbsp;Lev.navn</span></td>
							            <td>
							            	<table>
							            	<tr>
							            		<td class="text12" align="left"><span title="wkomp">&nbsp;
								            		<img onMouseOver="showPop('komp_info');" onMouseOut="hidePop('komp_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								            		Komp.</span>
							            		</td>
							            		<td class="text12" align="left"><span title="facd11">&nbsp;&nbsp;&nbsp;
								            		<img onMouseOver="showPop('sam_info');" onMouseOut="hidePop('sam_info');"style="vertical-align:middle;" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
								            		Sam.</span>
								            	</td>
					            			</tr>
					            			<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; top:20px; width:350px" id="komp_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Komplett</b>
							           			<div>
							           				<p>Man kan her, ved manuell oppretting av fakturalinje, angi hvilken bruttotabell gebyrprisen skal hentes fra.</p>
							           				Det er ikke krav til feltet.
							           				<p>Dette feltet er kun i bruk for de som benytter "Frakttabeller/Land og sjø-LCL (ikke postal)" (MENU MAINT3, punkt 3 og MENU MAINT4, punkt 4). Skal frakt beregnes etter komplett enhet ? </p>
							           				<p>Har kunden booket en komplett enhet kan fraktprisen hentes ved å taste 1,2 eller 3 her.</p>
							           				<ul>
														<li><b>1</b> = Hent pris for hele forvognen.</li> 
														<li><b>2</b> = Hent pris for hele hengeren.</li>
														<li><b>3</b> = Hent pris for hele vogntoget.</li>
													</ul>
													<p>
													OBS! Om en glemmer å taste noe her, så vil likevel aldri en vektberegnet pris bli større enn tallet gitt i "Komplett" i fraktavtalen.
							           				</p>
	    										</div>	 
						           			</font>
											</span>
											</div>
											
											<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; top:20px; width:350px" id="sam_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Samm</b>
							           			<div>
							           				<p>Hvis gebyret i fakturautskriften skal slås sammen med en eller flere andre gebyrlinjer under en felles gebyrtekst,
							           				 skal det her ligge en kode som forteller hvilke linjer som skal slås sammen og med hvilken gebyrtekst.</p>
							           				<p>Denne sammenslåingen vil kun påvirke fakturautskriften.
							           				Systemet vil ta vare på og overføre til regnskap hver enkelt fakturalinje som vanlig</p>
													
	    										</div>	 
						           			</font>
											</span>
											</div>
											
					            			</table>
					            		</td>
					            		
					            		<div class="text11" style="position: relative;" align="left">
											<span style="position:absolute; left:10px; top:90px; width:400px;" id="utgift_info" class="popupWithInputText"  >
												<font class="text11">
							           			<b>Utgift</b>
							           			<div>
							           				<p>Felt 1: fast kod</p>
							           				<ul>
														<li><b>blank</b>=ikke forventet utgift/budsjett</li>
														<li><b>B</b>=Budsjettering obligatorisk</li>
														<li><b>N</b>=Nei / obligatorisk er overstyrt</li>
														<li><b>S</b>=?</li>
														<li><b>L</b>=?</li>
														<li><b>H</b>=?</li>
													</ul>
													<p>Felt 2: valuta kode</p>
													
													
													<p>Alle gebyr/varekoder som har felt "SPLITT" utfylt (vedlikehold gebyrkoder MENU MAINT2, punkt 3), krever at et netto kostnadsbeløp i feltet UTGIFT er utfyllt. Avhengig av "splittkoden" benyttes beløpet til ulike formål:</p>
													<ul>
														<li>Splitt = <b>B</b>-Budsjett. Netto kostnad flyttes automatisk til budsjett/rekvisisjonssystemet som en
														forventet inngående faktura. Det er også mulig å angi valutakode for utenlands valuta ved
														budsjettering av kostnad.
														</li>
														<li>Splitt = <b>L</b>-Leverandørføring. Netto kostnad føres automatisk i Leverandørreskontro. Felt LENR(L)
														må da være utfylt.</li>
														<li>Splitt = <b>J</b> Netto kostnad føres til reskontro for utleggskontroll.</li>
 													</ul>
													<p> 													
													Dersom det i gebyr/varekoderegisteret også er utfylt felt %SATS (% av bruttobeløp som trekkes ut som ren inntekt) vil feltet UTGIFT bli automatisk fylt ut med det resterende som er kostnad.
													Denne verdien kan overstyres.
													</p>
													<p>Ved å sette kode N i feltet kan man overstyre kravet til å taste utgift.</p>
	    										</div>	
	    										 
						           			</font>
											</span>
											</div>
					            			
							        </tr>
							        <tr>
							        	<td class="text12">&nbsp;
						        			<select name="fakduk" id="fakduk">
						 						<option value=" ">-blank-</option>
							 				  	<option value="B" <c:if test="${model.record.fakduk == 'B'}"> selected </c:if> >B</option>
							 				  	<option value="N" <c:if test="${model.record.fakduk == 'N'}"> selected </c:if> >N</option>
							 				  	<option value="S" <c:if test="${model.record.fakduk == 'S'}"> selected </c:if> >S</option>
							 				  	<option value="L" <c:if test="${model.record.fakduk == 'L'}"> selected </c:if> >L</option>
							 				  	<option value="H" <c:if test="${model.record.fakduk == 'H'}"> selected </c:if> >H</option>
											</select>
										</td>
										<td class="text12">	
						        			<select name="facu33" id="facu33">
						 						<option value="">-valuta-</option>
							 				  	<c:forEach var="currency" items="${model.currencyCodeList}" >
							 				  		<option value="${currency}"<c:if test="${model.record.facu33 == currency || (empty model.record.facu33 && currency=='NOK')}"> selected </c:if> >${currency}</option>
												</c:forEach> 
											</select>
										</td>
										<td class="text12">	
						        			<input type="text" class="inputText" onKeyPress="return amountKey(event)" name="fabelu" id="fabelu" size="15" maxlength="14" value="${model.record.fabelu}">
										</td>
										<td class="text12" align="left" >
											<input type="text" class="inputText" name="falevn" id="falevn" size="9" maxlength="8" value="${model.record.falevn}">
										</td>
							            <td class="text12" align="left" >
											<input type="text" class="inputText" name="lnavn" id="lnavn" size="20" maxlength="35" value="${model.record.lnavn}">
										</td>
							            <td>
							            	<table>
							            	<tr>
												<td class="text12" align="left" nowrap>
									            	<input type="text" class="inputText" name="wkomp" id="wkomp" size="5" maxlength="5" value="${model.record.wkomp}">
												</td>
								        		<td class="text12" align="left">
								            		<input type="text" class="inputText" name="facd11" id="facd11" size="5" maxlength="5" value="${model.record.facd11}">
								            	</td>
					            			</tr>
					            			</table>
					            		</td>
										

							        </tr>
							        <tr height="8"><td class="text" align="left"></td></tr>
						        </table>
					        </td>
				        </tr>
					    <tr height="10"><td colspan="2" ></td></tr>
					    <tr>	
						    <td align="left" colspan="5">
								<input class="inputFormSubmit" type="submit" name="submit" id="submit" value='<spring:message code="systema.transportdisp.submit.save"/>'>
								&nbsp;&nbsp;<input class="inputFormSubmitGray" type="button" name="updCancelButton" id="updCancelButton" value='<spring:message code="systema.transportdisp.cancel"/>'>
							</td>							        	
				        </tr>
        	        </table>
       	         	</form>
		        </td>
		       
		    </tr>
			<tr height="20"><td colspan="2" ></td></tr>
			<tr height="30"><td></td></tr>
			
		</table>
		</td>
		</tr>
	</table>    
	
	
		
<!-- ======================= footer ===========================-->
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- =====================end footer ==========================-->

