package gcom.gui.portal.caer;

import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Classe Responsável por exibir o formulário de cadastro para solicitação de
 * contas por e-mail na Loja Virtual da Caer
 * </p>
 * 
 * @author Davi Menezes
 * @date 05/09/2012
 */
public class ExibirInserirCadastroEmailClientePortalCaerAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
				.findForward("exibirInserirCadastroEmailClientePortalCaerAction");
		
		HttpSession sessao = this.getSessao(httpServletRequest);
		
		httpServletRequest.setAttribute("voltarServicos", true);
		
		String ip = httpServletRequest.getRemoteAddr(); 
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.RECEBIMENTO_FATURA_EMAIL, AcessoLojaVirtual.INDICADOR_SERVICO_NAO_EXECUTADO);
		
		InserirCadastroEmailClientePortalCaerActionForm 
			form = (InserirCadastroEmailClientePortalCaerActionForm) actionForm;
		
		form.setMatricula(String.valueOf(sessao.getAttribute("matricula")));
		
		if ((httpServletRequest.getParameter("voltar") != null && 
			httpServletRequest.getParameter("voltar").equals("sim")) || 
			(httpServletRequest.getParameter("ok") == null || 
			!httpServletRequest.getParameter("ok").equals("sim"))) {
			
			form.setCpfSolicitante("");
			form.setEmail("");
			form.setIdCliente(null);
			form.setMatricula("");
			form.setNomeSolicitante("");
			form.setTelefoneContato("");
			
		}
		
		return retorno;
	}
	
}