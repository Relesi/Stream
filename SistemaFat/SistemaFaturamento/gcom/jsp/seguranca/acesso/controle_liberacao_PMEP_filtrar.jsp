<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet" href="<bean:message key="caminho.css"/>EstilosCompesa.css" type="text/css">

<%@ page import="gcom.util.ConstantesSistema"%>

<script language="JavaScript" src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>

<html:javascript staticJavascript="false"  formName="FiltrarControleLiberacaoPMEPActionForm"/>

<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
<script language="JavaScript" src="<bean:message key="caminho.js"/>Calendario.js" ></script>

<script language="JavaScript">
	
	function validarForm(){
		
		var form = document.forms[0];

		if(validateFiltrarControleLiberacaoPMEPActionForm(form)){
			var atualizar = form.atualizar.value;
			form.action = 'filtrarControleLiberacaoPMEPAction.do?atualizar=' + atualizar;
			form.submit();
		}		
		
	}
	
	
function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {
	    
	    var form = document.forms[0];
	    
		if (tipoConsulta == 'funcionalidade') {

			form.idFuncionalidade.value = codigoRegistro;
		    form.funcionalidade.value = descricaoRegistro;
		    form.funcionalidade.style.color = "#000000";
	    
	    }
		if (tipoConsulta == 'permissaoEspecial') {

			form.idPermissaoEspecial.value = codigoRegistro;
		    form.permissaoEspecial.value = descricaoRegistro;
		    form.permissaoEspecial.style.color = "#000000";
	    
	    }	    
	    	
	}
	
	/* Chama Popup */ 
	function chamarPopup(url, tipo, objeto, codigoObjeto, altura, largura, msg,objetoRelacionado){
		
		if(objetoRelacionado.disabled != true){
			
			if (objeto == null || codigoObjeto == null){
				abrirPopup(url + "?" + "tipo=" + tipo, altura, largura);
			} else{
				if (codigoObjeto.length < 1 || isNaN(codigoObjeto)){
					alert(msg);
				} else{
					abrirPopup(url + "?" + "tipo=" + tipo + "&" + objeto + "=" + codigoObjeto, altura, largura);
				}
			}
		}
	}	
	

  	
	function reloadForm(){
  		var form = document.forms[0];
  	
  		form.action='exibirFiltrarControleLiberacaoPMEPAction.do';
	    form.submit();
  	}
  	
  	function limpar(){
  		var form = document.forms[0];
  		
  		form.idFuncionalidade.value = "";
		form.idPermissaoEspecial.value = "";
  		form.funcionalidade.value = "";
  		form.permissaoEspecial.value = "";
  		form.indicadorUso.value = "";
  		
  		form.action='exibirFiltrarControleLiberacaoPMEPAction.do?menu=sim';
	    form.submit();  

  	}

  	function limparFuncionalidade(){
  	  	
        var form = document.forms[0];
  		
  		form.idFuncionalidade.value = "";
  		form.funcionalidade.value = "";
  	}  		

  	function limparPermissaoEspecial(){

  	  	var form = document.forms[0];

  	    form.idPermissaoEspecial.value = "";
		form.permissaoEspecial.value = "";
  	  	
  	}

  	function verificarValorAtualizar() {
  		
  		var form = document.forms[0];
  		
  		if (form.atualizar.checked == true) {
  			form.atualizar.value = '1';
  		} else {
  			form.atualizar.value = '';
  		}
  	}

  	function checkAtualizar(valor) {
  		
  		var form = document.forms[0];
  			
  		if (valor == '1') {
  			form.atualizar.checked = true;
  			form.atualizar.value = '1';
  		} else {
  			form.atualizar.checked = false;
  			form.atualizar.value = '';
  		}
  			
  	}
  	
</script>

</head>

<body leftmargin="5" topmargin="5" onload="javascript:setarFoco('${requestScope.funcionalidade}');checkAtualizar(document.forms[0].atualizar.value);">

<html:form action="/filtrarControleLiberacaoPMEPAction.do"
	name="FiltrarControleLiberacaoPMEPActionForm"
	type="gcom.gui.seguranca.acesso.FiltrarControleLiberacaoPMEPActionForm"
	method="post">

<%@ include file="/jsp/util/cabecalho.jsp"%>
<%@ include file="/jsp/util/menu.jsp" %>

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
			<td width="600" valign="top" class="centercoltext">
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
					<td class="parabg">Filtrar Controle de Libera��o de Permiss�o Especial</td>
					<td width="11"><img border="0"
						src="<bean:message key="caminho.imagens"/>parahead_right.gif" /></td>
				</tr>
			</table>
			<p>&nbsp;</p>
			<table width="100%" border="0">
				
				<tr>
					<td colspan="2">Para atualizar um controle, informe os dados abaixo:</td>
					<td align="right"><html:checkbox property="atualizar" value="1" onclick="javacript:verificarValorAtualizar();" /><strong>Atualizar</strong></td>
				</tr>
              	
				<tr>
					<td><strong>Funcionalidade:</strong></td>
					
					<td>
						
						<html:text maxlength="4" 
							tabindex="1"
							property="idFuncionalidade" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarControleLiberacaoPMEPAction.do?objetoConsulta=1','idFuncionalidade');
							            return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarFuncionalidadeAction.do?objetoConsulta=1', 'funcionalidade', null, null, 275, 480, '', document.forms[0].funcionalidade);">
						
							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Funcionalidade" /></a>
					</td>
				</tr>
				
				<tr>
				    <td> </td>
				    
				    <td>
						<logic:present name="funcionalidadeEncontrada" scope="request">
							<html:text property="funcionalidade" 
								size="60"
								maxlength="60" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="funcionalidadeEncontrada" scope="request">
							<html:text property="funcionalidade" 
								size="60"
								maxlength="60" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparFuncionalidade();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
					</td>
				</tr>
				
				<tr>
				    <td>
				    
				    </td>
				</tr>
				
				<tr>
					<td><strong>Permiss�o Especial:</strong></td>
					
					<td>
						
						<html:text maxlength="4" 
							tabindex="1"
							property="idPermissaoEspecial" 
							size="4"
							onkeypress="validaEnterComMensagem(event, 'exibirFiltrarControleLiberacaoPMEPAction.do?objetoConsulta=2','idPermissaoEspecial');
							            return isCampoNumerico(event);"/>
							
						<a href="javascript:chamarPopup('exibirPesquisarPermissaoEspecialAction.do?objetoConsulta=2', 'permissaoEspecial', null, null, 275, 480, '', document.forms[0].permissaoEspecial);">
						
							<img width="23" 
								height="21" 
								border="0"
								src="<bean:message key="caminho.imagens"/>pesquisa.gif"
								title="Pesquisar Permiss�o Especial" /></a> 
						
					</td>
				</tr>
				
				<tr>
				    <td>
				    
				    </td>
				    <td>
				        <logic:present name="permissaoEspecialEncontrada" scope="request">
							<html:text property="permissaoEspecial" 
								size="60"
								maxlength="60" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: #000000" />
						</logic:present> 

						<logic:notPresent name="permissaoEspecialEncontrada" scope="request">
							<html:text property="permissaoEspecial" 
								size="60"
								maxlength="60" 
								readonly="true"
								style="background-color:#EFEFEF; border:0; color: red" />
						</logic:notPresent>

						
						<a href="javascript:limparPermissaoEspecial();"> 
							<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
								border="0" 
								title="Apagar" /></a>
				    </td>
				</tr>
				
				<tr>
				    <td><strong>Indicador de Uso:</strong></td>
				    
				    <td>
				        <strong> <html:radio property="indicadorUso" value="1" />
					    <strong>Sim <html:radio property="indicadorUso" value="2" />
					    N&atilde;o</strong> </strong>
				    </td>
				    
				    
				</tr>
				
				<tr height="10px">
				    <td> </td>
				</tr>
				
				<tr>
					<td> 
					</td>
										
				</tr>
				
				<tr>
					<td height="24" >
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limpar();"/>
			          	<input type="button" 
			          		class="bottonRightCol" 
			          		value="Limpar" 
			          		onclick="javascript:limpar();"/>				          	
					</td>
				
					<td align="right">
						<input type="button" 
							name="Button" 
							class="bottonRightCol" 
							value="Filtrar" 
							onClick="javascript:validarForm()" />
					</td>
					
				</tr>
			</table>
			<p>&nbsp;</p>
			</td>
		</tr>
	</table>
<%@ include file="/jsp/util/rodape.jsp"%>	

</body>	

</html:form>
</html:html>
