package gcom.gui.cobranca.cobrancaporresultado;

import gcom.gui.GcomAction;
import gcom.gui.StatusWizard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1169] Movimentar Ordens de Servi�o de Cobran�a por Resultado
 * 
 * Action respons�vel por exibir a p�gina de Gerar OS do processo 
 * de movimentar ordem de servi�o de cobran�a por resultado.
 * 
 * @author Mariana Victor
 * @date 10/05/2011
 */
public class ExibirMovimentarOrdemServicoGerarOSAction extends GcomAction {
    
    /**
     * @param actionMapping
     * @param actionForm
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    public ActionForward execute(ActionMapping actionMapping,
            					ActionForm actionForm, 
            					HttpServletRequest httpServletRequest,
            					HttpServletResponse httpServletResponse) {

    	//Seta o mapeamento de retorno para a tela de Gerar OS 
        ActionForward retorno = actionMapping.findForward("movimentarOrdemServicoGerarOS");

        MovimentarOrdemServicoActionForm form = (MovimentarOrdemServicoActionForm) actionForm;

		if (httpServletRequest.getParameter("confirmado") != null
				&& httpServletRequest.getParameter("confirmado").equalsIgnoreCase("ok")) {
			
			httpServletRequest.setAttribute("confirmacao", "true");
			
			retorno = actionMapping.findForward("gerarOSAction");
			
			return retorno;
			
		}
		
    	this.limparForm(form);
    	this.limparSessao(this.getSessao(httpServletRequest));
		
		if (this.getSessao(httpServletRequest).getAttribute("statusWizard") != null) {
	        //Monta o Status do Wizard
	        StatusWizard statusWizard = (StatusWizard) this.getSessao(httpServletRequest).getAttribute("statusWizard");
	        
	        statusWizard.setNomeBotaoConcluir("Gerar OS");
	        
	        //manda o statusWizard para a sess�o
	        this.getSessao(httpServletRequest).setAttribute("statusWizard", statusWizard);
		}
		
        //Retorna o mapemaneto na vari�vel "retorno"
        return retorno;
    }

    private void limparForm(MovimentarOrdemServicoActionForm form){

    	form.setNumeroOSInicial("");
    	form.setNumeroOSFinal("");
    	
    	form.setTipoDivEscolhida("");
    	form.setTipoPesquisa("");
    	
    	form.setIdsCategoria(null);
    	form.setIdsImovelPerfil(null);
    	form.setIdsGerenciaRegional(null);
    	form.setIdsUnidadeNegocio(null);
    	
    	form.setIdLocalidadeOrigem("");
    	form.setIdLocalidadeDestino("");
    	form.setNomeLocalidadeOrigem("");
    	form.setNomeLocalidadeDestino("");
    	
    	form.setIdSetorComercialDestino("");
    	form.setIdSetorComercialOrigem("");
    	form.setCodigoSetorComercialDestino("");
    	form.setCodigoSetorComercialOrigem("");
    	form.setDescricaoSetorComercialDestino("");
    	form.setDescricaoSetorComercialOrigem("");
    	
    	form.setCodigoQuadraInicial("");
    	form.setDescricaoQuadraInicial("");
    	form.setCodigoQuadraFinal("");
    	form.setDescricaoQuadraFinal("");
    	
    	form.setValorMinimo("");
    	form.setValorMaximo("");
    	form.setQtdContas("");
    	form.setQtdClientes("");
    	form.setValorTotalDivida("");
    	form.setQtdeTotalClientes("");
    	
    	form.setColecaoInformada("");
    	form.setTotalSelecionado("");
    	
    	form.setIdTipoServico("");
    	form.setMatriculasImoveis(new String[10]);
    	form.setIdsLigacaoAguaSituacao(null);
    	
    	form.setNumerosOS(new String[10]);
    	form.setIdMotivoEncerramento("");
    	form.setDataEncerramento("");
    	form.setHoraEncerramento("");
    	form.setObservacaoEncerramento("");
    }
    
    private void limparSessao(HttpSession sessao){

    	//1� aba
    	sessao.removeAttribute("colecaoQuantidadeContas");
    	sessao.removeAttribute("colecaoFaixa");
    	sessao.removeAttribute("colecaoQtdeContas");
    	sessao.removeAttribute("colecaoQtdeClientes");
    	sessao.removeAttribute("colecaoValorTotalDivida");
    	sessao.removeAttribute("colecaoOSEmpresaCobranca");
    	sessao.removeAttribute("colecaoOSRegistroAtendimento");
    	sessao.removeAttribute("quadraInicialInexistente");
    	sessao.removeAttribute("setorComercialOrigemInexistente");
    	sessao.removeAttribute("localidadeOrigemInexistente");
    	sessao.removeAttribute("quadraFinalInexistente");
    	sessao.removeAttribute("setorComercialDestinoInexistente");
    	sessao.removeAttribute("localidadeDestinoInexistente");

    	//3� aba
    	sessao.removeAttribute("motivoInformado");
    	sessao.removeAttribute("habilitaNumerosOS");
    	
    }
}
