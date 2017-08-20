<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%@ page import="gcom.atendimentopublico.registroatendimento.RegistroAtendimento"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<title>GSAN - Sistema Integrado de Gest&atilde;o de Servi&ccedil;os de Saneamento</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">
<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>

<script>

 
</script>
</head>

<body leftmargin="0" topmargin="0" onload="window.focus();resizePageSemLink(680, 500);">

<html:form action="/pesquisarRegitrosAtendimentoPendentesLocalOcorrenciaAction" method="post">

<table width="652" border="0" cellspacing="5" cellpadding="0">
  <tr>
    <td width="652" valign="top" class="centercoltext">
    <table height="100%">
        <tr>
          <td></td>
        </tr>
      </table>
      <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
          <td class="parabg">Registros de Atendimento Pendentes do Local da Ocorrência</td>
          <td width="11"><img  border="0" src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
        </tr>
      </table>
      <p>&nbsp;</p>
      <table width="100%" border="0">
        <tr>
          <td colspan="4">
          	<table width="100%" border="0" bgcolor="#79bbfd">
			<tr height="18">
				<td width="10%" align="center"><strong>Dados Comuns</strong></td>
			</tr>
			</table>
          </td>
        </tr>
        <tr>
          <td width="14%"><strong>Tipo de Solicitação:</strong></td>
          <td width="86%" colspan="3">
          	<html:text property="idSolicitacaTipo" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          	<html:text property="descricaoSolicitacaTipo" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
          <td width="14%"><strong>Especificação: </strong></td>
          <td width="86%" colspan="3">
          	<html:text property="idSolicitacaTipoEspecificacao" size="4" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>&nbsp;
          	<html:text property="descricaoSolicitacaTipoEspecificacao" size="45" readonly="true" style="background-color:#EFEFEF; border:0; color: #000000"/>
          </td>
        </tr>
        <tr>
         <td colspan="4" height="50" valign="top">
			<table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td>
					<table width="100%" border="0" bgcolor="#90c7fc">
					<tr height="18">
						<td align="center"><strong>Endereço da Ocorrência</strong></td>
					</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" align="center" bgcolor="#99CCFF">
					<tr bgcolor="#FFFFFF" height="18">
						<td width="10%" align="center">
							<bean:write name="enderecoOcorrencia"/>
						</td>
					</tr>
					</table>
			  	</td>
			</tr>
			</table>
	   	 </td>
        </tr>
        <tr>
          <td colspan="4" height="20"></td>
        </tr>
        <tr>
          <td colspan="4">
          	<table width="100%" border="0" bgcolor="#79bbfd">
			<tr height="18">
				<td width="10%" align="center"><strong>Dados dos Registros de Atendimento</strong></td>
			</tr>
			</table>
          </td>
        </tr>
        
        
        <tr>
      	<td colspan="4">
      	
      		<table width="98%" border="0">
	  		<tr> 
          		<td colspan="4">
		  
					<table width="100%" cellpadding="0" cellspacing="0">
					<tr> 
                		<td> 
					
							<table width="100%" bgcolor="#90c7fc">
                    		<tr bgcolor="#90c7fc"> 

								<td align="center" width="5%"></td>
								<td align="center" width="20%"><FONT COLOR="#000000"><strong>R.A.</strong></FONT></td>
								<td align="center" width="25%"><FONT COLOR="#000000"><strong>Compl. Endereço</strong></FONT></td>
								<td align="center" width="25%"><FONT COLOR="#000000"><strong>Referência</strong></FONT></td>
								<td align="center" width="15%"><FONT COLOR="#000000"><strong>Data</strong></FONT></td>
								<td align="center" width="15%"><FONT COLOR="#000000"><strong>Hora</strong></FONT></td>
					
							</tr>
                    		</table>
					
						</td>
            		</tr>
            		
            		<tr> 
						<td> 
					
							<div style="width: 100%; height: 100; overflow: auto;">	
							
							<% String cor = "#cbe5fe";%>
							
							<table width="100%" align="center" bgcolor="#90c7fc">

								<logic:iterate name="colecaoRegistroAtendimento" id="ra" type="RegistroAtendimento">
                            
									
									
									<%	if (cor.equalsIgnoreCase("#FFFFFF")){
											cor = "#cbe5fe";%>
											<tr bgcolor="#cbe5fe">
									<%} else{
											cor = "#FFFFFF";%>
											<tr bgcolor="#FFFFFF">
									<%}%> 
									
									<td align="center" width="5%">
										<html:radio property="raSelected" value="<%ra.getId().toString();%>"/>
									</td>
									
									<td align="center" width="20%"><bean:write name="ra" property="id"/></td>
									
									<logic:present name="ra" property="complementoEndereco">
										<td align="center" width="25%"><bean:write name="ra" property="complementoEndereco"/></td>
									</logic:present>
									<logic:notPresent name="ra" property="complementoEndereco">
										<td align="center" width="25%">&nbsp;</td>
									</logic:notPresent>
									
									<logic:present name="ra" property="pontoReferencia">
										<td align="center" width="25%"><bean:write name="ra" property="pontoReferencia"/></td>
									</logic:present>
									<logic:notPresent name="ra" property="pontoReferencia">
										<td align="center" width="25%">&nbsp;</td>
									</logic:notPresent>
									
									<logic:present name="ra" property="registroAtendimento">
										<td align="center" width="15%"><bean:write name="ra" property="registroAtendimento" formatKey="date.format"/></td>
									</logic:present>
									<logic:notPresent name="ra" property="registroAtendimento">
										<td align="center" width="15%">&nbsp;</td>
									</logic:notPresent>
									
									<logic:present name="ra" property="registroAtendimento">
										<td align="center" width="15%"><bean:write name="ra" property="registroAtendimento" formatKey="hour.format"/></td>
									</logic:present>
									<logic:notPresent name="ra" property="registroAtendimento">
										<td align="center" width="15%">&nbsp;</td>
									</logic:notPresent>
										
									</tr>
									

								</logic:iterate>
								
								</table>

							</div>
						</td>
            		</tr>
            		
					</table>
				</td>
			</tr>
			
			</table>
			
      		</td>
      	</tr>
        
        <tr>
          <td colspan="4" height="20"></td>
        </tr>
      </table>
	</td>
  </tr>
</table>
</body>
</html:form>
</html:html>
