/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.cobranca;

import gcom.batch.Processo;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.util.ConstantesSistema;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0911] - Gerar Cartas da Campanha de Solidariedade da Crian�a para Negocia��o a Vista
 * 
 * @author Vivianne Sousa
 * @data 15/06/2009
 */

public class GerarCartasCampanhaSolidariedadeCriancaNegociacaoAVistaAction extends
		ExibidorProcessamentoTarefaRelatorio {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping.findForward("telaSucesso");
        GerarCartasCampanhaSolidariedadeCriancaNegociacaoAVistaActionForm form = (GerarCartasCampanhaSolidariedadeCriancaNegociacaoAVistaActionForm) actionForm;

        //Este map levar� todos os par�metros para a inicializa��o do relat�rio
        Map parametros = new HashMap();
        
        
        if(form.getGrupoFaturamento() == null 
        		|| form.getGrupoFaturamento().equals("")
        		|| form.getGrupoFaturamento().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	throw new ActionServletException("atencao.campo.informado", null, "Grupo de Faturamento");
        }
        String idGrupoFaturamento  = form.getGrupoFaturamento();
        parametros.put("idGrupoFaturamento",idGrupoFaturamento);


        if(form.getAcao() == null 
        		|| form.getAcao().equals("")
        		|| form.getAcao().equals("" + ConstantesSistema.NUMERO_NAO_INFORMADO)){
        	
        	throw new ActionServletException("atencao.campo.informado", null, "A��o");
        }
        String acao  = form.getAcao();
        parametros.put("acao",acao);
        
        if(acao.equals("1")){
        	 Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
             		Processo.GERAR_CARTAS_CAMPANHA_SOLID_CRIANCA ,
             		this.getUsuarioLogado(httpServletRequest));
        }else{
        	 Fachada.getInstancia().inserirProcessoIniciadoParametrosLivres(parametros, 
             		Processo.EMITIR_CARTAS_CAMPANHA_SOLID_CRIANCA ,
             		this.getUsuarioLogado(httpServletRequest));
        }
        
        montarPaginaSucesso(httpServletRequest, "Processo Inserido.", "", "");
        
		return retorno;
	}
	
}
