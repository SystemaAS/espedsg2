<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<!-- ======================= header ===========================-->
<jsp:include page="/WEB-INF/views/headerMainMaintenance.jsp" />
<!-- =====================end header ==========================-->
	<%-- specific jQuery functions for this JSP (must reside under the resource map since this has been
		specified in servlet.xml as static <mvc:resources mapping="/resources/**" location="WEB-INF/resources/" order="1"/> --%>
	<SCRIPT type="text/javascript" src="resources/js/mainmaintenanceavd_syfa14r_edit.js?ver=${user.versionEspedsg}"></SCRIPT>	
	
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
					<c:choose>
						<c:when test="${not empty model.updateId}">
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
							<td width="14%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdSyfa14r" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa14r.do">
									<font class="tabDisabledLink">&nbsp;Generelle avd.</font>&nbsp;
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Avd.</font>&nbsp;
								<font class="text11MediumBlue">(${model.avd})</font>
							</td>
							
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdFasteData" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa28r_edit.do?avd=${model.avd}&updateId=${model.avd}&avdnavn=${model.record.koanvn}">
									<font class="tabDisabledLink">&nbsp;Faste data</font>&nbsp;
									<font class="text11MediumBlue">(${model.avd})</font>
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkAvdHodeDok" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa63r.do?avd=${model.avd}&avdnavn=${model.record.koanvn}">
									<font class="tabDisabledLink">&nbsp;Hode på dok.</font>&nbsp;
									<font class="text11MediumBlue">(${model.avd})</font>
								</a>
							</td>
							<%-- for debugging purposes uncover this tabs since it is the obsolete version before these where merged in this view-JSP 
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkAvdListHode" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa68r.do?avd=${model.avd}&avdnavn=${model.record.koanvn}">
									<font class="tabDisabledLink">&nbsp;Listehode</font>&nbsp;
									<font class="text11MediumBlue">(${model.avd})</font>
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="12%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkAvdOppdTur" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa26r.do?avd=${model.avd}&avdnavn=${model.record.koanvn}">
									<font class="tabDisabledLink">&nbsp;Oppnr og tur</font>&nbsp;
									<font class="text11MediumBlue">(${model.avd})</font>
								</a>
							</td>
							--%> 
							<td width="24%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
						</c:when>
						<c:otherwise>
							<td width="14%" valign="bottom" class="tabDisabled" align="center" nowrap>
								<a id="alinkMainMaintGate" tabindex=-1 style="display:block;" href="mainmaintenancegate.do">
								<font class="tabDisabledLink">&nbsp;Vedlikehold</font>
								<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="14%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdGate" onClick="setBlockUI(this);" href="mainmaintenanceavdgate.do?id=${model.dbTable}">
									<font class="tabDisabledLink">&nbsp;Avdelinger</font>&nbsp;						
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. gate list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="14%" valign="bottom" class="tabDisabled" align="center">
								<a id="alinkMainMaintAvdSyfa14r" onClick="setBlockUI(this);" href="mainmaintenanceavd_syfa14r.do">
									<font class="tabDisabledLink">&nbsp;Generella avd.</font>&nbsp;
									<img style="vertical-align: middle;"  src="resources/images/list.gif" border="0" alt="avd. general list">
								</a>
							</td>
							<td width="1px" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
							<td width="14%" valign="bottom" class="tab" align="center">
								<font class="tabLink">&nbsp;Lage ny avd.</font>&nbsp;
								<img style="vertical-align: middle;"  src="resources/images/add.png" width="12" height="12" border="0" alt="new">
							</td>
							<td width="44%" class="tabFantomSpace" align="center" nowrap><font class="tabDisabledLink">&nbsp;</font></td>
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
					<form action="mainmaintenanceavd_syfa14r_edit.do" name="formRecord" id="formRecord" method="POST" >
						<input type="hidden" name="applicationUser" id="applicationUser" value="${user.user}">
						<input type="hidden" name="updateId" id=updateId value="${model.updateId}">
						<input type="hidden" name="action" id=action value="${model.action}">
						<table cellspacing="1" border="0" align="left">
				    	    <tr>
								<td class="text14" title="KOAAVD">&nbsp;<font class="text14RedBold" >*</font>Avd.</td>
								<td class="text14" title="KOANVN">&nbsp;<font class="text14RedBold" >*</font>Avd.navn</td>
								<td class="text14" title="KOAFIR">&nbsp;<font class="text14RedBold" >*</font>Firma</td>
								<td class="text14" title="KOAKNR">&nbsp;<font class="text14RedBold" >*</font>Kundenr.</td>
							</tr>
							<tr>
								
								<td >
									<c:choose>
										<c:when test="${not empty model.updateId}">
											<input readonly type="text" class="inputTextReadOnly" name="koaavd" id="koaavd" size="5" maxlength="4" value='${model.record.koaavd}'>
										</c:when>
										<c:otherwise>
											<input type="text" class="inputTextMediumBlueMandatoryField" name="koaavd" id="koaavd" size="5" maxlength="4" value='${model.record.koaavd}'>
										</c:otherwise>
									</c:choose>
								</td>
								<td ><input type="text" class="inputTextMediumBlueMandatoryField" name="koanvn" id="koanvn" size="35" maxlength="33" value='${model.record.koanvn}'></td>
								<td ><input readonly type="text" class="inputTextReadOnly" name="koafir" id="koafir" size="30" maxlength="59" value='${model.record.koafir}'></td>
								<td >
									<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlueMandatoryField" name="koaknr" id="koaknr" size="9" maxlength="8" value='${model.record.koaknr}'>
									<a tabindex="-1" id="koaknrIdLink">
										<img style="cursor:pointer;vertical-align: middle;" src="resources/images/find.png" width="16px" height="16px" border="0" alt="search" >
									</a>
								</td>
							</tr>
							<tr height="3"><td>&nbsp;</td></tr>
							<tr>
								<td colspan="20">
								<table width="100%" 	cellspacing="1" border="0" align="left">
					    	    <tr>
									<td class="text14" title="KOABÆR">&nbsp;Bærer</td>
									<td class="text14" title="KOAKON">&nbsp;Sted</td>
									<td class="text14" title="KOAIAT">&nbsp;IATA kode</td>
									<td class="text14" title="KOAIA2">&nbsp;IATA kode 2</td>
									<td class="text14" title="KOAPOS">&nbsp;Pnr.fraktb.</td>
									<td class="text14" title="KOAIE">&nbsp;Imp/Eks.</td>
									<td class="text14" title="KOALK">&nbsp;Kun land</td>
									<td class="text14" title="NAVSG">&nbsp;Attesterer</td>
	
								</tr>
								<tr>
									<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="koabaer" id="koabaer" size="5" maxlength="4" value='${model.record.koabaer}'></td>
									<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="koakon" id="koakon" size="5" maxlength="4" value='${model.record.koakon}'></td>
									<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="koaiat" id="koaiat" size="8" maxlength="7" value='${model.record.koaiat}'></td>
									<td ><input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="koaia2" id="koaia2" size="5" maxlength="4" value='${model.record.koaia2}'></td>
									
									<td ><input type="text" class="inputTextMediumBlue" name="koapos" id="koapos" size="2" maxlength="1" value='${model.record.koapos}'></td>
									<td ><input type="text" class="inputTextMediumBlue" name="koaie" id="koaie" size="2" maxlength="1" value='${model.record.koaie}'></td>
									<td ><input type="text" class="inputTextMediumBlue" name="koalk" id="koalk" size="3" maxlength="2" value='${model.record.koalk}'></td>
									<td ><input type="text" class="inputTextMediumBlue" name="navsg" id="navsg" size="4" maxlength="3" value='${model.record.navsg}'></td>
									<td>
										<input onClick="setBlockUI(this);" class="inputFormSubmit" type="submit" name="submit" id="submit" value='Lagre'/>
									</td>
								</tr>
								<tr height="5"><td >&nbsp;</td></tr>
								</table>
								</td>
							</tr>
							
							<tr>
								<td colspan="20">
								<table class="formFrameHeader" width="100%" cellspacing="1" border="0" align="left">
									<tr><td class="text14White" >&nbsp;Brukere som skal motta melding om overførte "DUP"-oppdrag(tr.modul)</td></tr>
								</table>
								</td>
							</tr>
							<tr>
								<td colspan="20">
								<table class="formFrame" width="100%" cellspacing="1" border="0" align="left">	
						    	    <tr>
										<td class="text14" title="KODUS1">&nbsp;Brukere1</td>
										<td class="text14" title="KODUS2">&nbsp;Brukere2</td>
										<td class="text14" title="KODUS3">&nbsp;Brukere3</td>
										<td class="text14" title="KODUS4">&nbsp;Brukere4</td>
										<td class="text14" title="KODUS5">&nbsp;Brukere5</td>
										<td class="text14" title="KODUS6">&nbsp;Brukere6</td>
									</tr>
									<tr>
										<td ><input type="text" class="inputTextMediumBlue" name="kodus1" id="kodus1" size="13" maxlength="10" value='${model.record.kodus1}'></td>
										<td ><input type="text" class="inputTextMediumBlue" name="kodus2" id="kodus2" size="13" maxlength="10" value='${model.record.kodus2}'></td>
										<td ><input type="text" class="inputTextMediumBlue" name="kodus3" id="kodus3" size="13" maxlength="10" value='${model.record.kodus3}'></td>
										<td ><input type="text" class="inputTextMediumBlue" name="kodus4" id="kodus4" size="13" maxlength="10" value='${model.record.kodus4}'></td>
										<td ><input type="text" class="inputTextMediumBlue" name="kodus5" id="kodus5" size="13" maxlength="10" value='${model.record.kodus5}'></td>
										<td ><input type="text" class="inputTextMediumBlue" name="kodus6" id="kodus6" size="13" maxlength="10" value='${model.record.kodus6}'></td>
									</tr>									
								</table>
								</td>
							</tr>

						</table>
						
						<table width="95%" cellspacing="1" border="0" align="left">
							<tr height="25"><td></td></tr>
						</table>
						<table class="formFrameHeader" width="95%" 	cellspacing="1" border="0" align="left">
							<tr><td colspan="15" class="text14White" >&nbsp;Listehode</td></tr>
						</table>
						<table class="formFrame"  width="95%" cellspacing="1" border="0" align="left">
							<tr>
								<td class="text14" align="left"><span title="kohfak">&nbsp;Faktura</span></td>
								<td class="text14" align="left"><span title="kohlas">&nbsp;Lasteliste</span></td>
								<td class="text14" align="left"><span title="kohgod">&nbsp;Godsliste</span></td>
								<td class="text14" align="left"><span title="kohbou">&nbsp;Bourderau</span></td>
								<td class="text14" align="left"><span title="kohkk">&nbsp;Kjørekvit.</span></td>
								<td class="text14" align="left"><span title="kohlos">&nbsp;Losselista</span></td>
								<td class="text14" align="left"><span title="kohman">&nbsp;Manifest</span></td>
								<td class="text14" align="left"><span title="kohls1">&nbsp;A4 L/S</span></td>
								<td class="text14" align="left"><span title="koh421">&nbsp;421</span></td>
								<td class="text14" align="left"><span title="kohls2">&nbsp;Godsliste</span></td>
								<td class="text14" align="left"><span title="koh422">&nbsp;422</span></td>
								<td class="text14" align="left"><span title="kohls3">&nbsp;Losselista</span></td>
								<td class="text14" align="left"><span title="koh423">&nbsp;423</span></td>
								
							</tr>		
	 	    				<tr>
								<input type="hidden" name="kohavd" id="kohavd" value='${model.record.listeHodeRecord.kohavd}'>
								<td class="text14" >
									<select name="kohfak" id="kohfak" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.listeHodeRecord.kohfak == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.listeHodeRecord.kohfak == 'N'}"> selected </c:if> >Nei</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="kohlas" id="kohlas" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.listeHodeRecord.kohlas == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.listeHodeRecord.kohlas == 'N'}"> selected </c:if> >Nei</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="kohgod" id="kohgod" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.listeHodeRecord.kohgod == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.listeHodeRecord.kohgod == 'N'}"> selected </c:if> >Nei</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="kohbou" id="kohbou" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.listeHodeRecord.kohbou == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.listeHodeRecord.kohbou == 'N'}"> selected </c:if> >Nei</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="kohkk" id="kohkk" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.listeHodeRecord.kohkk == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.listeHodeRecord.kohkk == 'N'}"> selected </c:if> >Nei</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="kohlos" id="kohlos" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.listeHodeRecord.kohlos == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.listeHodeRecord.kohlos == 'N'}"> selected </c:if> >Nei</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="kohman" id="kohman" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.listeHodeRecord.kohman == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.listeHodeRecord.kohman == 'N'}"> selected </c:if> >Nei</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="kohls1" id="kohls1" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="L"<c:if test="${ model.record.listeHodeRecord.kohls1 == 'L'}"> selected </c:if> >L</option>
					  					<option value="S"<c:if test="${ model.record.listeHodeRecord.kohls1 == 'S'}"> selected </c:if> >S</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="koh421" id="koh421" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.listeHodeRecord.koh421 == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.listeHodeRecord.koh421 == 'N'}"> selected </c:if> >Nei</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="kohls2" id="kohls2" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="L"<c:if test="${ model.record.listeHodeRecord.kohls2 == 'L'}"> selected </c:if> >L</option>
					  					<option value="S"<c:if test="${ model.record.listeHodeRecord.kohls2 == 'S'}"> selected </c:if> >S</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="koh422" id="koh422" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.listeHodeRecord.koh422 == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.listeHodeRecord.koh422 == 'N'}"> selected </c:if> >Nei</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="kohls3" id="kohls3" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="L"<c:if test="${ model.record.listeHodeRecord.kohls3 == 'L'}"> selected </c:if> >L</option>
					  					<option value="S"<c:if test="${ model.record.listeHodeRecord.kohls3 == 'S'}"> selected </c:if> >S</option>
					  					
								  	</select>
								</td>
								<td class="text14" >
									<select name="koh423" id="koh423" class="inputTextMediumBlueMandatoryField">
					  					<option value="">-velg-</option>
					  					<option value="J"<c:if test="${ model.record.listeHodeRecord.koh423 == 'J'}"> selected </c:if> >Ja</option>
					  					<option value="N"<c:if test="${ model.record.listeHodeRecord.koh423 == 'N'}"> selected </c:if> >Nei</option>
					  					
								  	</select>
								</td>	
			    	    	</tr>	
						</table>
						
						
						<table width="95%" cellspacing="1" border="0" align="left">
							<tr height="25"><td></td></tr>
						</table>
						<table width="28%" cellspacing="0" border="0" align="left">
						<tr>
							<td>
							<table class="formFrameHeader" width="100%" cellspacing="0" border="0" align="left">
								<tr><td class="text14White" >&nbsp;Oppdragsnr. og tur</td></tr>
							</table>
							</td>
						</tr>
						<tr>
							<td>
							<table class="formFrame" width="100%" cellspacing="0" border="0" align="left">
								<tr>
									<td class="text14" ><font class="text14RedBold" >*</font><span title="teopdn">&nbsp;Oppnr.</span></td>
									<td class="text14" ><font class="text14RedBold" >*</font><span title="teturn">&nbsp;Turnr.</span></td>
									<td class="text14" ><span title="tetmin">&nbsp;Minim.turnr.</span></td>
								</tr>		
		 	    				<tr>
									<input type="hidden" name="teavd" id="teavd" value='${model.record.oppnrTurRecord.teavd}'>
									<td class="text14" >
										<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="teopdn" id="teopdn" size="10" maxlength="7" value='${model.record.oppnrTurRecord.teopdn}'>
									</td>
									<td class="text14" >
										<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="teturn" id="teturn" size="10" maxlength="8" value='${model.record.oppnrTurRecord.teturn}'>
									</td>
									<td class="text14" >
										<input onKeyPress="return numberKey(event)" type="text" class="inputTextMediumBlue" name="tetmin" id="tetmin" size="10" maxlength="8" value='${model.record.oppnrTurRecord.tetmin}'>
									</td>								
				    	    	</tr>
				    	    	
								<tr height="20"><td></td></tr>
							</table>
							</td>
						</tr>
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

