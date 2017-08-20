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
* Anderson Cabral do Nascimento
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.bean.DadosParcelamentoDividaAtivaBean;
import gcom.cobranca.bean.DadosParcelamentoDividaAtivaHelper;
import gcom.fachada.Fachada;
import gcom.relatorio.ConstantesRelatorios;
import gcom.relatorio.RelatorioDataSource;
import gcom.relatorio.RelatorioVazioException;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaException;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.agendadortarefas.AgendadorTarefas;

/**
 * [UC1586] - Emitir Relat�rio D�vida Ativa Parcelada.
 * 
 * 
 * @author Anderson Cabral
 * @created 19/02/2014
 * 
 */
public class RelatorioDividaAtivaParcelada extends TarefaRelatorio{
	
	private static final long serialVersionUID = 1L;
	
	public RelatorioDividaAtivaParcelada(Usuario usuario) {
		super(usuario, ConstantesRelatorios.RELATORIO_DIVIDA_ATIVA_PARCELADA);
	}
	
	@Override
	public Object executar() throws TarefaException {
		byte[]  retorno =  null;
		try {
			Collection<DadosParcelamentoDividaAtivaHelper> colecaoDadosParcelamentoDividaAtivaHelper = (Collection)  getParametro("colecaoDadosParcelamentoDividaAtivaHelper");
			List<DadosParcelamentoDividaAtivaBean> colecaoDadosParcelamentoDividaAtivaBean = new ArrayList<DadosParcelamentoDividaAtivaBean>();
			if(colecaoDadosParcelamentoDividaAtivaHelper != null && !colecaoDadosParcelamentoDividaAtivaHelper.isEmpty()){
				DadosParcelamentoDividaAtivaBean dadosParcelamentoDividaAtivaBean = null;
				for(DadosParcelamentoDividaAtivaHelper helper : colecaoDadosParcelamentoDividaAtivaHelper){
					dadosParcelamentoDividaAtivaBean = new DadosParcelamentoDividaAtivaBean();
					
					String enderecoFormatado = Fachada.getInstancia().pesquisarEnderecoFormatado(helper.getIdImovel());
					String inscricaoImovel 	 = Fachada.getInstancia().pesquisarInscricaoImovel(helper.getIdImovel());
					
					dadosParcelamentoDividaAtivaBean.setIdLocalidade(helper.getIdLocalidade());
					dadosParcelamentoDividaAtivaBean.setNomeLocalidade(helper.getNomeLocalidade());
					dadosParcelamentoDividaAtivaBean.setAnoMesReferenciaArrecadacao(helper.getAnoMesReferenciaArrecadacao());
					dadosParcelamentoDividaAtivaBean.setDataInscricao(helper.getDataInscricao());
//					dadosParcelamentoDividaAtivaBean.setDataAmortizacao(helper.getDataAmortizacao());
					dadosParcelamentoDividaAtivaBean.setMatriculaImovel(Util.retornaMatriculaImovelFormatada(helper.getIdImovel()));
					dadosParcelamentoDividaAtivaBean.setInscricaoImovel(inscricaoImovel);
					dadosParcelamentoDividaAtivaBean.setEnderecoFormatado(enderecoFormatado);
					dadosParcelamentoDividaAtivaBean.setValorPrestacaoEmDia(helper.getValorPrestacaoEmDia());
					dadosParcelamentoDividaAtivaBean.setValorPrestacaoEmAtraso(helper.getValorPrestacaoEmAtraso());
					dadosParcelamentoDividaAtivaBean.setValorPrestacaoQuitada(helper.getValorPrestacaoQuitada());
					
					colecaoDadosParcelamentoDividaAtivaBean.add(dadosParcelamentoDividaAtivaBean);
					
				}
			}else{
				throw new RelatorioVazioException("atencao.relatorio.vazio");
			}
			
			// Par�metros do relat�rio
			Map parametro = new HashMap();
			
			// adiciona os par�metros do relat�rio
			// adiciona o laudo da an�lise
			SistemaParametro sistemaParamntro = Fachada.getInstancia().pesquisarParametrosDoSistema();
			int tipoFormatoRelatorio = (Integer) getParametro("tipoformatoRelatorio");
			short indicadorIntra = (Short) getParametro("intra");
			
			parametro.put("imagem", sistemaParamntro.getImagemRelatorio());
			parametro.put("numeroRelatorio", "R1586");
			
			if(indicadorIntra == 1){
				parametro.put("intra", "CLIENTE INTRA");
			}else{
				parametro.put("intra", "");
			}
			
			// cria uma inst�ncia do dataSource do relat�rio
			RelatorioDataSource ds = new RelatorioDataSource(colecaoDadosParcelamentoDividaAtivaBean);
	
			retorno = this.gerarRelatorio(ConstantesRelatorios.RELATORIO_DIVIDA_ATIVA_PARCELADA, parametro, ds, tipoFormatoRelatorio);
		
		} catch (ControladorException e) {
			e.printStackTrace();
		}
		
		return retorno;
		
	}
	
	@Override
	public int calcularTotalRegistrosRelatorio(){
		
		int retorno = 1;
		
		return retorno;
	}
	
	@Override
	public void agendarTarefaBatch() {
		AgendadorTarefas.agendarTarefa("RelatorioDividaAtivaParcelada", this);
	}
}
