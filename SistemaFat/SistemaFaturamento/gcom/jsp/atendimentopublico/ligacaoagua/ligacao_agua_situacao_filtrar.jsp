
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="gcom.util.ConstantesSistema"%>

<html:html>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="FiltrarLigacaoAguaSituacaoActionForm" dynamicJavascript="true" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>
<script language="JavaScript">

	function validarForm(formulario){
		if(validateFiltrarLigacaoAguaSituacaoActionForm(formulario)){
    		submeterFormPadrao(formulario);
		}
	}


    function verificarChecado(valor){
		form = document.forms[0];
		if(valor == "1"){
		 	form.indicadorAtualizar.checked = true;
		 }else{
		 	form.indicadorAtualizar.checked = false;
		}
	}
</script>

</head>

<body leftmargin="5" topmargin="5">
<html:form action="/filtrarLigacaoAguaSituacaoAction"
	name="FiltrarLigacaoAguaSituacaoActionForm"
	type="gcom.gui.atendimentopublico.FiltrarLigacaoAguaSituacaoActionForm"
	method="post"
	onsubmit="return validateFiltrarLigacaoAguaSituacaoActionForm(this);">

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
			<td width="615" valign="top" class="centercoltext">
			<table height="100%">
				<tr>
					<td></td>
				</tr>
			</table>


			<!--In�cio Tabela Reference a P�gina��o da Tela de Processo-->
			<table>
				<tr>
					<td></td>

				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Filtrar Situa&ccedil;&atilde;o de Liga&ccedil;&atilde;o de &Aacute;gua</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="2">Para filtrar uma situa&ccedil;&atilde;o de liga&ccedil;&atilde;o de &aacute;gua, informe os dados
					abaixo:</td>
					<td width="100" align="left" colspan="2"><html:checkbox
						property="indicadorAtualizar" value="1" /><strong>Atualizar</strong></td>
				</tr>
				<tr>
					<td><strong>C&oacute;digo:</strong></td>
					<td><html:text property="id" size="2" maxlength="2" /><font
						size="1">&nbsp;(somente n�meros)</font> <br>
					<font color="red"><html:errors property="id" /></font></td>
					<td>&nbsp;</td>
				</tr>
		       <tr>
					<td><strong>Descri&ccedil;&atilde;o:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricao" size="30" maxlength="30" /> </span></td>
			   		<td width="15%">&nbsp;</td>
			   </tr>
				<tr>
					<td>&nbsp;</td>
					<td><html:radio property="tipoPesquisa" tabindex="5"
						value="<%=ConstantesSistema.TIPO_PESQUISA_INICIAL.toString()%>" />
					Iniciando pelo texto <html:radio property="tipoPesquisa"
						tabindex="6"
						value="<%=ConstantesSistema.TIPO_PESQUISA_COMPLETA.toString()%>" />
					Contendo o texto</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Descri&ccedil;&atilde;o Abreviada:</strong></td>
					<td colspan="2"><span class="style2"> <html:text
						property="descricaoAbreviada" size="5" maxlength="5" /> </span></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Consumo M&iacute;nimo: </strong></td>
					<td><strong> <html:text property="consumoMinimoFaturamento"
						size="10" maxlength="10" /> </strong></td>
				</tr>
				<tr>
					<td><strong>Indicador de Faturamento: </strong></td>
					<td><html:radio property="indicadorFaturamentoSituacao" tabindex="2" value="<%=ConstantesSistema.SIM.toString()%>" /><strong>Sim</strong>
					<html:radio property="indicadorFaturamentoSituacao" tabindex="3" value="<%=ConstantesSistema.NAO.toString()%>" /><strong>N&atilde;o</strong>
					<html:radio property="indicadorFaturamentoSituacao" tabindex="4" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Indicador de Exist&ecirc;ncia de Rede: </strong></td>
					<td><html:radio property="indicadorExistenciaRede" tabindex="2" value="<%=ConstantesSistema.SIM.toString()%>" /><strong>Sim</strong>
					<html:radio property="indicadorExistenciaRede" tabindex="3" value="<%=ConstantesSistema.NAO.toString()%>" /><strong>N&atilde;o</strong>
					<html:radio property="indicadorExistenciaRede" tabindex="4" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Indicador de Exist&ecirc;ncia de Liga&ccedil;&atilde;o: </strong></td>
					<td><html:radio property="indicadorExistenciaLigacao" tabindex="2" value="<%=ConstantesSistema.SIM.toString()%>" /><strong>Sim</strong>
					<html:radio property="indicadorExistenciaLigacao" tabindex="3" value="<%=ConstantesSistema.NAO.toString()%>" /><strong>N&atilde;o</strong>
					<html:radio property="indicadorExistenciaLigacao" tabindex="4" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong></td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Indicador de abastecimento: </strong></td>
					<td><html:radio property="indicadorAbastecimento" tabindex="2" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" /><strong>Sim</strong>
					<html:radio property="indicadorAbastecimento" tabindex="3" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" /><strong>N�o</strong>
					<html:radio property="indicadorAbastecimento" tabindex="4" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Indicador de �gua Ativa: </strong></td>
					<td><html:radio property="indicadorAguaAtiva" tabindex="2" value="<%=ConstantesSistema.SIM.toString()%>" /><strong>Sim</strong>
					<html:radio property="indicadorAguaAtiva" tabindex="3" value="<%=ConstantesSistema.NAO.toString()%>" /><strong>N�o</strong>
					<html:radio property="indicadorAguaAtiva" tabindex="4" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Indicador de �gua Desligada: </strong></td>
					<td><html:radio property="indicadorAguaDesligada" tabindex="2" value="<%=ConstantesSistema.SIM.toString()%>" /><strong>Sim</strong>
					<html:radio property="indicadorAguaDesligada" tabindex="3" value="<%=ConstantesSistema.NAO.toString()%>" /><strong>N�o</strong>
					<html:radio property="indicadorAguaDesligada" tabindex="4" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Indicador de �gua Cadastrada: </strong></td>
					<td><html:radio property="indicadorAguaCadastrada" tabindex="2" value="<%=ConstantesSistema.SIM.toString()%>" /><strong>Sim</strong>
					<html:radio property="indicadorAguaCadastrada" tabindex="3" value="<%=ConstantesSistema.NAO.toString()%>" /><strong>N�o</strong>
					<html:radio property="indicadorAguaCadastrada" tabindex="4" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Indicador de An�lize de �gua: </strong></td>
					<td><html:radio property="indicadorAnalizeAgua" tabindex="2" value="<%=ConstantesSistema.SIM.toString()%>" /><strong>Sim</strong>
					<html:radio property="indicadorAnalizeAgua" tabindex="3" value="<%=ConstantesSistema.NAO.toString()%>" /><strong>N�o</strong>
					<html:radio property="indicadorAnalizeAgua" tabindex="4" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Indicador de Permiss�o para Corte de Esgoto: </strong></td>
					<td><html:radio property="indicadorPermissaoCorteEsgoto" tabindex="2" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" /><strong>Sim</strong>
					<html:radio property="indicadorPermissaoCorteEsgoto" tabindex="3" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" /><strong>N�o</strong>
					<html:radio property="indicadorPermissaoCorteEsgoto" tabindex="4" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><strong>Indicador de uso: </strong></td>
					<td><html:radio property="indicadorUso" tabindex="2" value="<%=ConstantesSistema.INDICADOR_USO_ATIVO.toString()%>" /><strong>Ativo</strong>
					<html:radio property="indicadorUso" tabindex="3" value="<%=ConstantesSistema.INDICADOR_USO_DESATIVO.toString()%>" /><strong>Inativo</strong>
					<html:radio property="indicadorUso" tabindex="4" value="<%=ConstantesSistema.TODOS.toString()%>" /><strong>Todos</strong>
					</td>
					<td>&nbsp;</td>
				</tr>
				
				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Limpar" align="left"
						onclick="window.location.href='/gsan/exibirFiltrarLigacaoAguaSituacaoAction.do?menu=sim'"
						tabindex="8"></td>
					<td align="right" colspan="2">&nbsp;</td>
					<td width="65" align="right"><input name="Button2" type="button"
						class="bottonRightCol" value="Filtrar" align="right"
						onClick="javascript:validarForm(document.forms[0]);" tabindex="9"></td>
				</tr>

			</table>

			</td>
		</tr>
	</table>
	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</body>
</html:html>
