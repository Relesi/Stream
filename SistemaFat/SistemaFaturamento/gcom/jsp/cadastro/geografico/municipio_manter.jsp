<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan" %>
<%@page import="gcom.cadastro.geografico.Municipio"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">


<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>/validacao/ManutencaoRegistro.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script>
<!--
function facilitador(objeto){

	if (objeto.id == "0" || objeto.id == undefined){
		objeto.id = "1";
		marcarTodos();
	}
	else{
		objeto.id = "0";
		desmarcarTodos();
	}
}

function remover(objeto){

	if (CheckboxNaoVazio(objeto)){
		if (confirm ("Confirma remo��o?")) {
			document.forms[0].action = "/gsan/removerMunicipioAction.do"
			document.forms[0].submit();
		 }
	}
}
-->
</script>
</head>

<body leftmargin="5" topmargin="5">

<html:form action="/removerMunicipioAction" 
	name="ManutencaoRegistroActionForm"
	type="gcom.gui.ManutencaoRegistroActionForm" method="post"
	onsubmit="return CheckboxNaoVazio(document.ManutencaoRegistroActionForm.idRegistrosRemocao) && confirm('Confirma remo��o?')">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp"%>

<table width="770" border="0" cellspacing="5" cellpadding="0">
	<tr>
		<td width="130" valign="top" class="leftcoltext">
			<div align="center">
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
	
			<%@ include file="/jsp/util/informacoes_usuario.jsp"%>
	
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
	
			<%@ include file="/jsp/util/mensagens.jsp"%>
	
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			</div>
		</td>
		<td width="625" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_left.gif" /></td>
					<td class="parabg">Manter Munic�pio</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
				<tr>
					<td height="5" colspan="3"></td>
				</tr>
			</table>
	
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" height="23">
						<font color="#000000" style="font-size:10px" face="Verdana, Arial, Helvetica, sans-serif">
						<strong>Munic�pios cadastrados:</strong>
						</font>
					</td>
					<logic:present scope="application" name="urlHelp">
							<td align="right"><a href="javascript:abrirPopupHelp('${applicationScope.urlHelp}cadastroGeograficoMunicipioManter', 500, 700);"><span style="font-weight: bold"><font color="#3165CE">Ajuda</font></span></a></td>									
					</logic:present>
					<logic:notPresent scope="application" name="urlHelp">
							<td align="right"><span style="font-weight: bold"><font color=#696969><u>Ajuda</u></font></span></td>									
					</logic:notPresent>	
					</tr>
	  		</table>
			
	  		<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4" bgcolor="#000000" height="2"></td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
							<!--header da tabela interna -->
							<tr bgcolor="#99CCFF">
								<td width="10%" align="center">
									<strong>
									<a onclick="this.focus();" id="0" href="javascript:facilitador(this);">Todos</a>
									</strong>
								</td>
								<td width="15%" align="center">
									<strong>C�digo</strong>
								</td>
								<td width="35%" align="center">
									<strong>Nome</strong>
								</td>
								<td width="10%" align="center">
									<strong>UF</strong>
								</td>
								<td width="15%" align="center">
									<strong>CEP Inicial</strong>
								</td>
								<td width="15%" align="center">
									<strong>CEP Final</strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" bgcolor="#99CCFF">
						<%--Esquema de pagina��o--%>
						<pg:pager isOffset="true" index="half-full" maxIndexPages="10"
							export="currentPageNumber=pageNumber;pageOffset"				
							maxPageItems="10" items="${sessionScope.totalRegistros}">
							<pg:param name="pg"/>
							<pg:param name="q"/>
							<logic:present name="colecaoMunicipio">
								<%int cont = 0;%>
								<logic:iterate name="colecaoMunicipio" id="municipio" type="Municipio">
									<pg:item>
										<%cont = cont + 1;
										if (cont % 2 == 0) {%>
										<tr bgcolor="#cbe5fe">
										<%} else {%>
										<tr bgcolor="#FFFFFF">
										<%}%>
											<td width="46" align="center">
												<input type="checkbox" name="idRegistrosRemocao" 
												value="<bean:write name="municipio" property="id"/>"/>
											</td>
											<td width="15%" align="center">
												<a href="/gsan/exibirAtualizarMunicipioAction.do?idMunicipio=<%=""+ municipio.getId()%>"><bean:write name="municipio" property="id"/></a>
											</td>
											<td width="35%" align="left">
												<bean:write name="municipio" property="nome"/>
											</td>
											<td width="10%" align="center">
												<bean:write name="municipio" property="unidadeFederacao.sigla"/>
											</td>
											<td width="15%" align="center">
											<bean:write name="municipio" property="cepInicio"/>
											</td>
											<td width="15%" align="center">
											<bean:write name="municipio" property="cepFim"/>
											</td>		
											
										</tr>
									</pg:item>
								</logic:iterate>
							</logic:present>
						<%-- Fim do esquema de pagina��o --%>
						</table>
						<table width="100%">
							<tr>
								<td>
									<input type="button" class="bottonRightCol" tabindex="1"
										value="Remover" name="removerCliente" onclick="remover(document.ManutencaoRegistroActionForm.idRegistrosRemocao);"/>
									<input name="button" type="button" class="bottonRightCol" tabindex="2" value="Voltar Filtro"
										onclick="window.location.href='<html:rewrite page="/exibirFiltrarMunicipioAction.do"/>'">
								</td>
								<td align="right" valign="top">
                                	<a href="javascript:toggleBox('demodiv',1);">
                                    	<img align="right" border="0" src="<bean:message key='caminho.imagens'/>print.gif"  title="Imprimir Clientes"/></a>
                                </td>
							</tr>
						</table>
						<table width="100%" border="0">
							<tr>
								<td align="center">
									<strong><%@ include file="/jsp/util/indice_pager_novo.jsp"%></strong>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>		
		</pg:pager>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
 <jsp:include page="/jsp/relatorio/escolher_tipo_relatorio.jsp?relatorio=gerarRelatorioMunicipioManterAction.do"/>
	<%@ include file="/jsp/util/rodape.jsp"%>
</body>
</html:form>
</html:html>