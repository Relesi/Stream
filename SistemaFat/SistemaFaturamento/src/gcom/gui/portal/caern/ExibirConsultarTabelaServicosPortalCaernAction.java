package gcom.gui.portal.caern;

import gcom.atendimentopublico.ordemservico.FiltroServicoTipo;
import gcom.atendimentopublico.ordemservico.ServicoTipo;
import gcom.atendimentopublico.portal.AcessoLojaVirtual;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.Maior;
import gcom.util.filtro.ParametroSimples;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1531] Consultar Tabela de servi�os
 * 
 * @author Rafael Pinto
 * @since 22/07/2013
 *
 */
public class ExibirConsultarTabelaServicosPortalCaernAction extends GcomAction {

	public ActionForward execute(ActionMapping mapping, ActionForm actionForm, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward retorno = mapping.findForward("exibirConsultarTabelaServicosPortalCaernAction");
		request.setAttribute("voltarInformacoes", true);
	
		FiltroServicoTipo filtroServicoTipo = new FiltroServicoTipo(FiltroServicoTipo.ID);
		filtroServicoTipo.adicionarParametro(new Maior(FiltroServicoTipo.VALORSERVICO, 0));
		filtroServicoTipo.adicionarParametro(new ParametroSimples(FiltroServicoTipo.INDICADOR_USO, ConstantesSistema.INDICADOR_USO_ATIVO));
		
		Collection<ServicoTipo> colecaoServicoTipo = Fachada.getInstancia().pesquisar(filtroServicoTipo, ServicoTipo.class.getName());
		Collection<ConsultarTabelaServicosPortalCaernHelper> colecaoTabelaServicos = new ArrayList<ConsultarTabelaServicosPortalCaernHelper>();
		
		if ( colecaoServicoTipo != null && !colecaoServicoTipo.isEmpty() ) {
		
			Iterator iteratorServicoTipo = colecaoServicoTipo.iterator();
			while ( iteratorServicoTipo.hasNext() ) {
				ConsultarTabelaServicosPortalCaernHelper helper = new ConsultarTabelaServicosPortalCaernHelper();
				ServicoTipo servicoTipo = (ServicoTipo) iteratorServicoTipo.next();
				
				helper.setCodigo(servicoTipo.getId().toString() );
				helper.setDescricao(servicoTipo.getDescricao());
				helper.setValor(Util.formatarMoedaReal(servicoTipo.getValor()));
				colecaoTabelaServicos.add(helper);
			}
			request.setAttribute("colecaoServicoTipoHelper", colecaoTabelaServicos);
			
		}
		
		String ip = request.getRemoteAddr();
		Fachada.getInstancia().verificarExistenciaAcessoLojaVirtual(ip, AcessoLojaVirtual.TABELA_SERVICOS, AcessoLojaVirtual.INDICADOR_SERVICO_EXECUTADO);
		
		return retorno;
	}
}