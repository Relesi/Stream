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
package gcom.gui.micromedicao;

import gcom.cadastro.sistemaparametro.FiltroSistemaParametro;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.faturamento.FiltroFaturamentoGrupo;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.Util;
import gcom.util.filtro.FiltroParametro;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * < <Descri��o da Classe>>
 * 
 * @author Administrador
 */
public class ExibirConsistirLeiturasCalcularConsumosAction extends GcomAction {
    /**
     * < <Descri��o do m�todo>>
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o retorno
        ActionForward retorno = actionMapping
                .findForward("consistirLeiturasCalcularConsumos");

        //ConsistirLeiturasCalcularConsumosActionForm consistirLeiturasCalcularConsumosActionForm = (ConsistirLeiturasCalcularConsumosActionForm) actionForm;

        //Obt�m a inst�ncia da fachada
        Fachada fachada = Fachada.getInstancia();

        HttpSession sessao = httpServletRequest.getSession(false);

        String confirmacao = (String) httpServletRequest.getParameter("sim");

        if (confirmacao != null && confirmacao.equals("sim")) {

            //Filtro Sistema par�metro
            FiltroSistemaParametro filtroSistemaParametro = new FiltroSistemaParametro();
            //Pesquisa os param�tros do sistema
            Collection colecaoSistemaParametro = fachada.pesquisar(
                    filtroSistemaParametro, SistemaParametro.class.getName());
            SistemaParametro sistemaParametro = null;

            if (colecaoSistemaParametro != null
                    && !colecaoSistemaParametro.equals("")) {
                sistemaParametro = (SistemaParametro) Util
                        .retonarObjetoDeColecao(colecaoSistemaParametro);
            }

            //Cria��o do filtro
            FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
            //Par�metro que define o id do grupo de faturamento selecionado
           /* ParametroSimples parametroSimples = new ParametroSimples(
                    FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_FATURAMENTO_GRUPO_ID,
                    consistirLeiturasCalcularConsumosActionForm
                            .getIdFaturamentoGrupo(),
                    FiltroParametro.CONECTOR_AND);*/

            //Par�metro que define o m�s de faturamento
            filtroFaturamentoAtividadeCronograma
                    .adicionarParametro(new ParametroSimples(
                            FiltroFaturamentoAtividadeCronograma.FATURAMENTO_GRUPO_CRONOGRAMA_MENSAL_ANO_MES_REFERENCIA,
                            new Integer(sistemaParametro.getAnoMesArrecadacao()),
                            FiltroParametro.CONECTOR_AND));
            //Par�metro que define o faturamento atividade registrar leitura e
            // anormalidade
            filtroFaturamentoAtividadeCronograma
                    .adicionarParametro(new ParametroSimples(
                            FiltroFaturamentoAtividadeCronograma.FATURAMENTO_ATIVIDADE_ID,
                            FaturamentoAtividade.REGISTRAR_LEITURA_ANORMALIDADE));

            //Pesquisar faturamento atividade cronograma
            Collection colecaoFaturamentoAtividadeCronograma = fachada
                    .pesquisar(filtroFaturamentoAtividadeCronograma,
                            FaturamentoAtividadeCronograma.class.getName());

            if (colecaoFaturamentoAtividadeCronograma != null
                    && !colecaoFaturamentoAtividadeCronograma.equals("")) {

                FaturamentoAtividadeCronograma faturamentoAtividadeCronograma = (FaturamentoAtividadeCronograma) Util
                        .retonarObjetoDeColecao(colecaoFaturamentoAtividadeCronograma);

                //Caso n�o tenha sido realizado a atividade de faturamento do
                // cronograma
                if (faturamentoAtividadeCronograma.getDataRealizacao() == null) {
                    httpServletRequest.setAttribute("confirmacao",
                            "confirmacao");
                } else {
                    //Foi realizado
                    httpServletRequest.removeAttribute("confirmacao");
                }
                sessao.setAttribute("sistemaParametro", sistemaParametro);
            } else {
                //N�o existe nada cadastro na tabela de faturamento atividade
                // cronograma conforme os par�metros passados
                throw new ActionServletException(
                        "atencao.inexistente.faturamento_atividade_cronograma");
            }
        }

        //Cria��o das cole��es
        Collection faturamentoGrupos = null;

        FiltroFaturamentoGrupo filtroFaturamentoGrupo = new FiltroFaturamentoGrupo(
                FiltroFaturamentoGrupo.DESCRICAO);
        /*ParametroSimples parametroSimples = new ParametroSimples(
                FiltroFaturamentoGrupo.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO);*/

        //Realiza as pesquisas
        faturamentoGrupos = fachada.pesquisar(filtroFaturamentoGrupo,
                FaturamentoGrupo.class.getName());

        if (faturamentoGrupos == null || faturamentoGrupos.isEmpty()) {
            //Nenhuma grupo de faturamento cadastrado
            throw new ActionServletException("erro.sistema");
            //reportarErrosMensagem(httpServletRequest, "erro.naocadastrado",
            // "grupo de faturamento");
        } else {
            //Envia os objetos no request
            httpServletRequest.setAttribute("faturamentoGrupos",
                    faturamentoGrupos);
        }

        return retorno;
    }
}
