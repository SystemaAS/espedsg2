<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header =====================================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenanceChildWindows.jsp" />
<!-- =====================end header ====================================-->

	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
	specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavd_syfa28r_edit_childwindow.js?ver=${user.versionEspedsg}"></SCRIPT>
	
	<table width="99%" height="200px" class="tableBorderWithRoundCorners3D_RoundOnlyOnBottom" cellspacing="1" border="0" cellpadding="0">
		<tr>
			<td colspan="10" class="text14Bold">&nbsp;&nbsp;&nbsp;
				<img title="search" valign="bottom" src="resources/images/update.gif" width="16px" height="16px" border="0" alt="search">
				Vedlikehold av avdelings faste data (Dokumenter)
			</td>
		</tr>
		<tr>
		<td valign="top">
		
  		<%-- this container table is necessary in order to separate the datatables element and the frame above, otherwise
	 	the cosmetic frame will not follow the whole datatable grid including the search field... --%>
		<table id="containerdatatableTable" cellspacing="1" align="left" width="100%" >
			<tr>
			<td>
				<table>
					<form name="syfa28ChildForm" id="syfa28ChildForm" action="mainmaintenanceavd_syfa28r_edit_childwindow.do" method="post">
					<input type="hidden" name="kopavd" id=kopavd value="${model.record.kopavd}">
					<input type="hidden" name="updateId" id="updateId" value="${model.updateId}">
					<input type="hidden" name="action" id=action value="${model.action}">
					<tr>
						<td class="text14" title="koplnr">&nbsp;Utskriftnr.</td>
						<td class="text14" title="utptxt">&nbsp;Beskrivelse</td>
						<td class="text14" title="kopty">&nbsp;Papirtype</td>
						<td class="text14" title="kopnvn">&nbsp;Printernavn</td>
						<td class="text14" title="kophea">&nbsp;Hode på dok.</td>
						<td class="text14" >
							<img onMouseOver="showPop('koplas_info');" onMouseOut="hidePop('koplas_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							<span title="koplas"></span>Laser
							<div class="text11" style="position: relative;" align="left">
							<span style="position:absolute;top:2px" id="koplas_info" class="popupWithInputText text11"  >	
			           			<ul>
				           			<li><b>J</b>&nbsp;=&nbsp;Justerer sidelengde</li>
			           				<li><b>A</b>&nbsp;=&nbsp; Avansert, kun faktura, legger inn PCL5-koder i utskriften. Krever at utkøen er definert med Systemas PCL5-driver.</li>
			           				<li><b>P</b>&nbsp;=&nbsp;PDF direkte. Krever at skriveren kan skrive ut PDF-dokumenter direkte</li>		           				
			           			</ul>
							</span>
							</div>
						</td>
						<td class="text14" title="koplpi">&nbsp;
							<img onMouseOver="showPop('koplpi_info');" onMouseOut="hidePop('koplpi_info');" width="12px" height="12px" src="resources/images/info3.png" border="0" alt="info">
							<span title="koplpi">8</span>
							<div class="text11" style="position: relative;" align="left">
							<span style="position:absolute;top:2px" id="koplpi_info" class="popupWithInputText text11"  >
								<p>	
			           			<b>8</b>&nbsp;=&nbsp;8 linjer pr tomme, <b>kun faktura</b>. Denne koden gir plass til flere fakturalinjer ved laserutskrift med kvitteringsdel.
			           			</p>           				
							</span>
							</div>
						</td>
	           		</tr>
	           		<tr>
						<td ><input readonly type="text" class="inputTextReadOnly" name="koplnr" id="koplnr" size="5" value='${model.record.koplnr}'></td>
						<td ><input readonly type="text" class="inputTextReadOnly" name="utptxt" id="utptxt" size="30" value='${model.record.utptxt}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="kopty" id="kopty" size="11" maxlength="10" value='${model.record.kopty}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="kopnvn" id="kopnvn" size="11" maxlength="10" value='${model.record.kopnvn}'></td>
						<td >
							<select name="kophea" id="kophea" >
			  					<option value="">-velg-</option>
			  					<option value="J"<c:if test="${ model.record.kophea == 'J'}"> selected </c:if> >Ja</option>
			  					<option value="N"<c:if test="${ model.record.kophea == 'N'}"> selected </c:if> >Nei</option>
			  				</select>							
						</td>
						<td >
							<select name="koplas" id="koplas" >
			  					<option value="">-velg-</option>
			  					<option value="J"<c:if test="${ model.record.koplas == 'J'}"> selected </c:if> >J</option>
			  					<option value="A"<c:if test="${ model.record.koplas == 'A'}"> selected </c:if> >A</option>
			  					<option value="P"<c:if test="${ model.record.koplas == 'P'}"> selected </c:if> >P</option>
			  					
			  				</select>
						</td>
						<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="koplpi" id="koplpi" size="2" maxlength="1" value='${model.record.koplpi}'></td>
	           		</tr>
	           		
	           		<tr>
						<td class="text14" title="kopfm">&nbsp;Stringk.</td>
						<td class="text14" title="kopdraw/kopoutb">&nbsp;Skuff I/U</td>
						<td class="text14" title="kopcopi">&nbsp;Kopier</td>
						<td class="text14" title="kopfov1">&nbsp;Ovl.</td>
						<td colspan="2" class="text14">&nbsp;</td>
	           		</tr>
	           		<tr>
						<td ><input type="text" class="inputTextMediumBlue" name="kopfm" id="kopfm" size="6" maxlength="5" value='${model.record.kopfm}'></td>
						<td >
							<input type="text" class="inputTextMediumBlue" name="kopdraw" id="kopdraw" size="4" maxlength="3" value='${model.record.kopdraw}'>
							<input type="text" class="inputTextMediumBlue" name="kopoutb" id="kopoutb" size="4" maxlength="3" value='${model.record.kopoutb}'>
						</td>
						<td ><input type="text" class="inputTextMediumBlue" name="kopcopi" id="kopcopi" size="3" maxlength="2" value='${model.record.kopcopi}'></td>
						<td ><input type="text" class="inputTextMediumBlue" name="kopfov1" id="kopfov1" size="10" maxlength="9" value='${model.record.kopfov1}'></td>
						
						<td colspan="2" ><input class="inputFormSubmit" type="submit" name="submit" id="submit"value='Lagre'/></td>
	           		</tr>
	           		
	           		</form>
           		</table>
			</td>
			</tr>           		
       		<tr height="10"><td></td></tr>
       		
       		<%-- Other errors (none validation errors) --%>
			<c:if test="${not empty model.errorMessage}">
			<tr>
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
       		
       		
   		</table>

		</td>
		</tr>
</table> 
