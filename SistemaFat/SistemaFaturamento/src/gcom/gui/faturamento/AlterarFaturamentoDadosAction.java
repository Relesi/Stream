/* Feito por Felipe e Rafael na data 16/12/2005*/

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
package gcom.gui.faturamento;

import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.leitura.FiltroLeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraAnormalidade;
import gcom.micromedicao.leitura.LeituraSituacao;
import gcom.micromedicao.medicao.FiltroMedicaoTipo;
import gcom.micromedicao.medicao.MedicaoHistorico;
import gcom.micromedicao.medicao.MedicaoTipo;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.seguranca.acesso.usuario.UsuarioAcao;
import gcom.util.ConstantesSistema;
import gcom.util.ControladorException;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Action para exibir a p�gina de endere�o
 * 
 * @author Felipe
 */
public class AlterarFaturamentoDadosAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws ControladorException {

		// Seta o retorno
		ActionForward retorno = null;

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		if (sessao.getAttribute("telaMedicaoConsumoDadosAnt") != null
				&& !sessao.getAttribute("telaMedicaoConsumoDadosAnt")
						.equals("")) {
			retorno = actionMapping.findForward("exibirDadosAnalise");
		} else {
			retorno = actionMapping.findForward("telaSucesso");
		}
		
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");

		// Obt�m a inst�ncia da Fachada
		Fachada fachada = Fachada.getInstancia();
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();

		AlterarFaturamentoDadosActionForm alterarFaturamentoDadosActionForm = (AlterarFaturamentoDadosActionForm) actionForm;

		String idImovel = alterarFaturamentoDadosActionForm.getIdImovel();
		String leituraAnterior = alterarFaturamentoDadosActionForm
				.getLeituraAnterior();
		String dataLeituraAnteriorInformada = alterarFaturamentoDadosActionForm
				.getDataLeituraAnterior();
		String leituraAtual = alterarFaturamentoDadosActionForm
				.getLeituraAtual();
		String dataLeituraAtualInformada = alterarFaturamentoDadosActionForm
				.getDataLeituraAtual();
		String indicadorConfirmacao = alterarFaturamentoDadosActionForm
				.getIndicadorConfirmacao();
		String idAnormalidadeLeitura = alterarFaturamentoDadosActionForm
				.getIdAnormalidade();
		String consumoInformado = alterarFaturamentoDadosActionForm
				.getConsumoInformado();

		Integer tipoMedicao = new Integer(alterarFaturamentoDadosActionForm.getTipoMedicao());

		String tipoMedicaoSelecionada = alterarFaturamentoDadosActionForm
				.getTipoMedicaoSelecionada();

		Imovel imovel = (Imovel) sessao.getAttribute("imovel");

		MedicaoHistorico medicaoHistorico = (MedicaoHistorico) sessao
				.getAttribute("medicaoHistorico");

		LeituraSituacao leituraSituacao = new LeituraSituacao();

		/*Imovel imovelFiltro = null;
		
		if (idImovel != null && !idImovel.equals("") ) {
			//obtem dados de sistema parametros
			
			// Rota e Seq de Rota
			FiltroImovel filtroImovel = new FiltroImovel();
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("quadra.rota.faturamentoGrupo");
			filtroImovel.adicionarCaminhoParaCarregamentoEntidade("rotaAlternativa");
			
			filtroImovel.adicionarParametro(new ParametroSimples(FiltroImovel.ID, new Integer(idImovel)));
			Collection<Imovel> imoveis = fachada.pesquisar(filtroImovel, Imovel.class.getName());
			
			if(!imoveis.isEmpty()){
				
				imovelFiltro = (Imovel) imoveis.iterator().next();
				
			}
		}*/
		
		Integer anoMesReferenciaGrupo = imovel.getRotaAlternativa() != null ? imovel.getRotaAlternativa().getFaturamentoGrupo()
				.getAnoMesReferencia() : imovel.getQuadra().getRota().getFaturamentoGrupo().getAnoMesReferencia();
		
		//boolean desabilitaAtualizarImovel = true;
		
//		String anoMesFormatado = new String();
//		anoMesFormatado = anoMesReferenciaGrupo.toString();
//		String ano = anoMesFormatado.substring(0, 4);
//		String mesAux = anoMesFormatado.substring(4, 6);
//		anoMesFormatado = mesAux +"/"+ano;
		
		fachada.validarAlterarDadosFaturamento(imovel.getId(), anoMesReferenciaGrupo);
		/*desabilitaAtualizarImovel = fachada.validaAtualizarDadosFaturamento(imovelFiltro, imovelFiltro, new Integer(idImovel), anoMesReferenciaGrupo, new Integer(tipoMedicao));
		
		if ( desabilitaAtualizarImovel  ) {
			throw new ActionServletException("atencao.imovel_ja_possui_conta_gerada", null, Util.formatarAnoMesParaMesAno(anoMesReferenciaGrupo));
		}*/
		
		

		// testa para ver se o tipo de medicao foi alterado
		if (tipoMedicao != null && tipoMedicaoSelecionada != null) {
			FiltroMedicaoTipo filtroMedicaoTipo = new FiltroMedicaoTipo();
			filtroMedicaoTipo.adicionarParametro(new ParametroSimples(
					FiltroMedicaoTipo.ID, tipoMedicao));
			Collection colecaoTipomedicao = fachada.pesquisar(
					filtroMedicaoTipo, MedicaoTipo.class.getName());
			MedicaoTipo medicaoTipo = (MedicaoTipo) Util
					.retonarObjetoDeColecao(colecaoTipomedicao);

			if (!medicaoTipo.getDescricao().trim().equalsIgnoreCase(
					tipoMedicaoSelecionada.trim())) {
				throw new ActionServletException("atencao.tipoMedicao.alterado");
			}
		}

		if (imovel != null) {

			// Comparando o im�vel filtrado com o informado pelo usu�rio
			if (imovel.getId().toString().equals(idImovel)) {

				SimpleDateFormat dataFormatada = new SimpleDateFormat(
						"dd/MM/yyyy");

				Date dataLeituraAtual = null;
				Date dataLeituraAnterior = null;

				String anoMesReferencia = ""
						+ medicaoHistorico.getAnoMesReferencia();

				if (imovel.getHidrometroInstalacaoHistorico() != null
						|| (imovel.getLigacaoAgua() != null && imovel
								.getLigacaoAgua()
								.getHidrometroInstalacaoHistorico() != null)) {

					if (dataLeituraAtualInformada != null
							&& !dataLeituraAtualInformada.equals("")) {

						try {
							dataLeituraAtual = dataFormatada
									.parse(dataLeituraAtualInformada);
						} catch (ParseException ex) {
							throw new ActionServletException("erro.sistema");
						}
						Calendar dataAtual = new GregorianCalendar();
						dataAtual.setTime(dataLeituraAtual);

						String anoMesAtual = "" + dataAtual.get(Calendar.YEAR);
						String mes = "" + (dataAtual.get(Calendar.MONTH) + 1);

						if (!(mes.length() == 2)) {
							mes = "0" + mes;
						}

						anoMesAtual += mes;

						// Comparando a data atual informada no form com o ano
						// m�s
						// refer�ncia e com o ano m�s seguinte
						/*if (!((Util.compararAnoMesReferencia(new Integer(
								anoMesReferencia), new Integer(anoMesAtual),
								"=")) || (Util.compararAnoMesReferencia(
								new Integer(anoMesReferenciaMaisUmMes),
								new Integer(anoMesAtual), "=")))) {*/
						
						
						if ( !sistemaParametro.getNomeAbreviadoEmpresa().equals("COSAMA") ) {
							
							boolean mesAnoValido = fachada.validaDataFaturamentoIncompativel(anoMesReferencia, anoMesAtual);
							if(!mesAnoValido){
								sessao.setAttribute("nomeCampo",
										"dataLeituraAtual");
								sessao
										.setAttribute("nomeCampo",
												"dataLeituraAtual");
								throw new ActionServletException(
										"atencao.faturamento_data_incompativel",
										null, "Data de Leitura Atual Informada");
							}
						}
						if (dataLeituraAnteriorInformada != null
								&& !dataLeituraAnteriorInformada.equals("")) {
							try {
								dataLeituraAnterior = dataFormatada
										.parse(dataLeituraAnteriorInformada);
							} catch (ParseException ex) {
								throw new ActionServletException("erro.sistema");
							}
							Calendar dataAnterior = new GregorianCalendar();
							dataAnterior.setTime(dataLeituraAnterior);

							// Comparando a data atual informada com a data
							// anterior
							// informada
							// Garantindo que a data atual seja posterior a data
							// anterior
							if (dataAtual.compareTo(dataAnterior) < 0) {
								sessao.setAttribute("nomeCampo",
										"dataLeituraAtual");
								throw new ActionServletException(
										"atencao.faturamento_data_atual_inferior_data_anterior");
							}
						}
						medicaoHistorico
								.setDataLeituraAtualFaturamento(dataLeituraAtual);
						medicaoHistorico
								.setDataLeituraAtualInformada(dataLeituraAtual);
					}

					if (dataLeituraAnteriorInformada != null
							&& !dataLeituraAnteriorInformada.equals("")) {
						try {
							dataLeituraAnterior = dataFormatada
									.parse(dataLeituraAnteriorInformada);
						} catch (ParseException ex) {
							throw new ActionServletException("erro.sistema");
						}
						Calendar dataAnterior = new GregorianCalendar();
						dataAnterior.setTime(dataLeituraAnterior);
						String anoMesAnterior = ""
								+ dataAnterior.get(Calendar.YEAR);
						String mesAnterior = ""
								+ (dataAnterior.get(Calendar.MONTH) + 1);

						if (!(mesAnterior.length() == 2)) {
							mesAnterior = "0" + mesAnterior;
						}

						anoMesAnterior += mesAnterior;


						// Comparando a data anterior faturada no form com o ano
						// m�s
						// refer�ncia e com o ano m�s anterior
						/*if (!((Util.compararAnoMesReferencia(new Integer(
								anoMesReferencia), new Integer(anoMesAnterior),
								"=")) || (Util.compararAnoMesReferencia(
								new Integer(anoMesReferenciaMenosUmMes),
								new Integer(anoMesAnterior), "=")))) {*/
						if ( !sistemaParametro.getNomeAbreviadoEmpresa().equals("COSAMA") ) {
							boolean anoMesInferiorValido = fachada.validaDataFaturamentoIncompativelInferior(anoMesReferencia, anoMesAnterior);
							if(!anoMesInferiorValido){
								sessao.setAttribute("nomeCampo",
										"dataLeituraAnterior");
	
								sessao.setAttribute("nomeCampo",
										"dataLeituraAnterior");
	
								throw new ActionServletException(
										"atencao.faturamento_data_incompativel",
										null,
										"Data de Leitura Anterior de Faturamento");
							}
						}			
						medicaoHistorico
								.setDataLeituraAnteriorFaturamento(dataLeituraAnterior);

					}
					// Testando se o n�mero de d�gitos do hidrometro � menor que
					// o
					// n�mero de d�gitos da leitura
					/*if ((imovel.getHidrometroInstalacaoHistorico() == null ? imovel
							.getLigacaoAgua()
							.getHidrometroInstalacaoHistorico().getHidrometro()
							.getNumeroDigitosLeitura().intValue()
							: imovel.getHidrometroInstalacaoHistorico()
									.getHidrometro().getNumeroDigitosLeitura()
									.intValue()) <= leituraAtual.length()) {

						medicaoHistorico.setLeituraAtualInformada(leituraAtual
								.equals("") ? null : new Integer(leituraAtual));

					} else {

						sessao.setAttribute("nomeCampo", "leituraAtual");

						throw new ActionServletException(
								"atencao.digitos.leitura.maior.hidrometro");
					}*/
					
					if (((tipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA)  
						? imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumeroDigitosLeitura().intValue()
						: imovel.getHidrometroInstalacaoHistorico().getHidrometro().getNumeroDigitosLeitura().intValue())
						>= leituraAtual.length())&& ((tipoMedicao.equals(MedicaoTipo.LIGACAO_AGUA) 
						? imovel.getLigacaoAgua().getHidrometroInstalacaoHistorico().getHidrometro().getNumeroDigitosLeitura().intValue()
						: imovel.getHidrometroInstalacaoHistorico().getHidrometro().getNumeroDigitosLeitura().intValue()) 
						  >= leituraAnterior.length())) {

						medicaoHistorico.setLeituraAtualInformada(leituraAtual
								.equals("") ? null : new Integer(leituraAtual));
						
						if (!("" + medicaoHistorico
								.getLeituraAnteriorFaturamento())
								.equals(leituraAnterior)) {
							LeituraSituacao leituraSituacaoAnterior = new LeituraSituacao();
							leituraSituacaoAnterior
									.setId(LeituraSituacao.LEITURA_ALTERADA);
							medicaoHistorico
									.setLeituraSituacaoAnterior(leituraSituacaoAnterior);
						}

						if (leituraAnterior != null && !leituraAnterior.equals("")) {
							medicaoHistorico.setLeituraAnteriorFaturamento(new Integer(leituraAnterior));
						} else {
							medicaoHistorico.setLeituraAnteriorFaturamento(null);
						}

					} else {
						
						sessao.setAttribute("nomeCampo", "leituraAtual");
						sessao.setAttribute("nomeCampo", "leituraAnterior");

						throw new ActionServletException(
								"atencao.digitos.leitura.maior.hidrometro");
					}	
					
					///////////////////////////////////////////////////////////////////////////////////
					//[FS0007] Validar Consumo Informado do M�s					
					
					  String confirmado = httpServletRequest.getParameter("confirmado");
					
					  if (confirmado == null || !confirmado.trim().equalsIgnoreCase("ok")){
					  
					
						int consumo = 0;
						int consumoMedioMes = 0;
						if(alterarFaturamentoDadosActionForm.getConsumoMedido() != null && !alterarFaturamentoDadosActionForm.getConsumoMedido().equals("")){
							consumoMedioMes =  new Integer(alterarFaturamentoDadosActionForm.getConsumoMedido());	
						}						
						
						//SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
						imovel.setId(new Integer(idImovel));
						
						int qtdeEconomias = fachada
								.obterQuantidadeEconomias(imovel);
						
						int[] consumoMedioImovel = fachada
								.obterVolumeMedioAguaEsgoto(imovel.getId(),
										sistemaParametro.getAnoMesFaturamento(),tipoMedicao);
						
						
						if (consumoMedioImovel != null
								&& consumoMedioImovel.length > 0) {
							
							
							
							if(consumoInformado != null && !consumoInformado.equals("")){
								consumo = new Integer(consumoInformado);
							}
								
							int consumoMedio = consumoMedioImovel[0];
							int consumoMedio5 = consumoMedio * 5;
							int qtdeEconomias30 = qtdeEconomias * 30;
							if(consumo > consumoMedio5 && consumo > qtdeEconomias30 && consumo > consumoMedioMes){
								
									httpServletRequest.setAttribute("caminhoActionConclusao",
											"/gsan/alterarDadosFaturamentoAction.do");
									// Monta a p�gina de confirma��o para perguntar se o usu�rio
									// quer aceitar o consumo informado superior a cinco vezes ao 
									// consumo m�dio do im�vel e superior a trinta vezes a quantidade de economias
									// e superior ao consumo m�dio
									
									return montarPaginaConfirmacao(
											"atencao.invalido.consumo",
											httpServletRequest, actionMapping, null,null);
								//}
								/*throw new ActionServletException(
									"atencao.invalido.consumo");*/
							}
						}
					  
					}
					
				//////////////////////////////////////////////////////////////////////////////////////////	
					

					if (idAnormalidadeLeitura != null
							&& !idAnormalidadeLeitura.equals("")) {
						FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
						filtroLeituraAnormalidade
								.adicionarParametro(new ParametroSimples(
										FiltroLeituraAnormalidade.ID,
										idAnormalidadeLeitura));

						Collection anormalidadeLeituraEncontrada = fachada
								.pesquisar(filtroLeituraAnormalidade,
										LeituraAnormalidade.class.getName());
						if (anormalidadeLeituraEncontrada != null
								&& !anormalidadeLeituraEncontrada.isEmpty()) {

							medicaoHistorico
									.setLeituraAnormalidadeInformada((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
											.get(0));
							medicaoHistorico
									.setLeituraAnormalidadeFaturamento((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
											.get(0));

						} else {

							sessao.setAttribute("nomeCampo", "idAnormalidade");

							throw new ActionServletException(
									"atencao.pesquisa_inexistente", null,
									"Anormalidade de Leitura");
						}

					} else {
						medicaoHistorico.setLeituraAnormalidadeInformada(null);
						medicaoHistorico
								.setLeituraAnormalidadeFaturamento(null);
					}

					if (leituraAtual.equals("") || new Integer(leituraAtual).intValue() == 0) {
						leituraSituacao.setId(LeituraSituacao.NAO_REALIZADA);
					} else {

						if (indicadorConfirmacao
								.equals(ConstantesSistema.CONFIRMADA)) {

							leituraSituacao.setId(LeituraSituacao.CONFIRMADA);

						} else {

							leituraSituacao.setId(LeituraSituacao.REALIZADA);

						}

					}

					medicaoHistorico.setLeituraSituacaoAtual(leituraSituacao);
					if (consumoInformado != null
							&& !consumoInformado.equalsIgnoreCase("")) {
						medicaoHistorico.setNumeroConsumoInformado(new Integer(
								consumoInformado));

					}					

					//regitrando operacao
//					medicaoHistorico.setOperacaoEfetuada(operacaoEfetuada);
//					medicaoHistorico.adicionarUsuario(usuarioLogado, UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
//					registradorOperacao.registrarOperacao(medicaoHistorico);
					
					if ( sessao.getAttribute("medicaoHistoricoGerada") != null ){
						
						fachada.inserirMedicaoHistorico(medicaoHistorico, usuarioLogado);
						sessao.removeAttribute( "medicaoHistoricoGerada" );						
					} else if (medicaoHistorico.getId() != null) {

						if ( medicaoHistorico != null && medicaoHistorico.getId() != null ) {
							fachada.atualizarMedicaoHistorico(medicaoHistorico, usuarioLogado);	
						}
					}			
					
					
				} else {
					if (idAnormalidadeLeitura != null
							&& !idAnormalidadeLeitura.equals("")) {
						FiltroLeituraAnormalidade filtroLeituraAnormalidade = new FiltroLeituraAnormalidade();
						filtroLeituraAnormalidade
								.adicionarParametro(new ParametroSimples(
										FiltroLeituraAnormalidade.ID,
										idAnormalidadeLeitura));

						Collection anormalidadeLeituraEncontrada = fachada
								.pesquisar(filtroLeituraAnormalidade,
										LeituraAnormalidade.class.getName());

						if (anormalidadeLeituraEncontrada != null
								&& !anormalidadeLeituraEncontrada.isEmpty()) {

							// Comparar a anormalidade
							if (((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
									.get(0)).getIndicadorImovelSemHidrometro()
									.equals(new Short("2"))) {

								sessao.setAttribute("nomeCampo",
										"idAnormalidade");

								throw new ActionServletException(
										"atencao.leitura.anormalidade.nao.permitida");

							}
							imovel
									.setLeituraAnormalidade((LeituraAnormalidade) ((List) anormalidadeLeituraEncontrada)
											.get(0));
						} else {

							sessao.setAttribute("nomeCampo", "idAnormalidade");

							throw new ActionServletException(
									"atencao.pesquisa_inexistente", null,
									"Anormalidade de Leitura");
						}

					} else {
						imovel.setLeituraAnormalidade(null);
						imovel.setUltimaAlteracao(new Date());
					}
					
					imovel.adicionarUsuario(usuarioLogado,	UsuarioAcao.USUARIO_ACAO_EFETUOU_OPERACAO);
					
					fachada.atualizarImovelAlterarFaturamento(imovel, usuarioLogado);

				}

			} else {

				sessao.setAttribute("nomeCampo", "idImovel");
				httpServletRequest.setAttribute("sucesso", "Dados do faturamento atualizados com sucesso");

				throw new ActionServletException("atencao.imovel.alterado");
			}
			
			if(sessao.getAttribute("habilitaBotaoAlterarDadosFaturamento") != null ){
				
				montarPaginaSucesso(httpServletRequest,
						"Dados do faturamento do im�vel " + imovel.getId()
								+ " atualizados com sucesso.",
						"Voltar para Analisar Excecoes de Leituras e Consumos",
						"exibirDadosAnaliseMedicaoConsumoResumoAction.do");
			
			}else if (retorno.getName().equalsIgnoreCase("telaSucesso")) {
				montarPaginaSucesso(httpServletRequest,
						"Dados do faturamento do im�vel " + imovel.getId()
								+ " atualizados com sucesso.",
						"Atualizar outros dados para faturamento",
						"exibirDadosFaturamentoAction.do?menu=sim");
			}

		}
		sessao.removeAttribute("telaMedicaoConsumoDadosAnt");

		return retorno;
	}
}
