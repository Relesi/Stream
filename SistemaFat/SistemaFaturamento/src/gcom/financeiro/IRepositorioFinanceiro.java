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
package gcom.financeiro;

import gcom.cadastro.geografico.Municipio;
import gcom.cadastro.localidade.Localidade;
import gcom.financeiro.bean.ParametrosPerdasFiscaisHelper;
import gcom.financeiro.lancamento.LancamentoItemContabil;
import gcom.relatorio.financeiro.RelatorioVolumesConsumidosNaoFaturadosBean;
import gcom.relatorio.financeiro.ResumoReceitaHelper;
import gcom.util.ErroRepositorioException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * Interface para o reposit�rio de financeiro
 * 
 * @author Raphael Rossiter
 * @since 09/01/2006
 */
public interface IRepositorioFinanceiro {

	
	/**
	 * Obt�m os dados do resumoFaturamento a partir do ano e m�s de refer�ncia
	 *
	 * [UC0175] - Gerar Lan�amentos Cont�beis do Faturamento
	 *
	 * @author Raphael Rossiter, Pedro Alexandre 
	 * @date 16/01/2006, 24/05/2007
	 *
	 * @param anoMesReferenciaFaturamento
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosResumoFaturamento(Integer anoMesReferenciaFaturamento, Integer idLocalidade)throws ErroRepositorioException ;
	
	
	/**
	 * Obt�m a conta cont�bil a partir do n�mero da raz�o cont�bil e do n�emro da conta
	 * @param razao
	 * @param conta
	 * @return ContaContabil
	 * @throws ErroRepositorioException
	 */
	public ContaContabil obterContaContabil(Short razao, Integer conta) 
		throws ErroRepositorioException;
	
	
	/**
	 * Obt�m a conta cont�bil a partir da tabela LANCAMENTO_ITEM_CONTABIL
	 * @param razao
	 * @param conta
	 * @return ContaContabil
	 * @throws ErroRepositorioException
	 */
	public ContaContabil obterContaContabil(LancamentoItemContabil lancamentoItemContabil) 
		throws ErroRepositorioException;

	/**
	 * 
	 * Gera Lan�amentos Contabeis do Faturamento
	 *
	 * [UC000348] - Gerar Lan�amento Const�beis da Arrecada��o
	 *
	 * Obter O Parametros Contabile Arrecadacao
	 *
	 * @author Rafael Santos
	 * @date 23/05/2006
	 *
	 * @param anoMesArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	/*public Collection obterParametrosContabilArrecadacao(Integer idCategoria,Integer idItemLancamentoContabil,
		Integer idItemLancamento,Integer idTipoLancamento,Integer idTipoRecebimento) 
		throws ErroRepositorioException; */
	
	/**
	 * 
	 * Gera Lan�amentos Contabeis do Faturamento
	 *
	 * [UC000348] - Gerar Lan�amento Const�beis da Arrecada��o
	 *
	 * @author Rafael Santos, Pedro Alexandre
	 * @date 23/05/2006, 25/05/2007
	 *
	 * @param anoMesArrecadacao
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection obterDadosResumoArrecadacao(Integer anoMesReferenciaArrecadacao, Integer idLocalidade) throws ErroRepositorioException ;
	
	/**
	 * 
	 * este metodo � utilizado para pesquisar os registros q ser�o
	 * usados para contru��o do txt do caso de uso
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 * @throws ErroRepositorioException 
	 * 
	 */
	public Collection pesquisarGerarIntegracaoContabilidade(String idLancamentoOrigem, String anoMes) 
		throws ErroRepositorioException;	
	
	/**
	 * Exclui os registros da tabela RESUMO_DEVEDORES_DUVIDOSOS 
	 * por ano m�s ref�ncia cont�bil 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(int anoMesReferenciaContabil)
		throws ErroRepositorioException;
	
	/**
	 * Atualiza com o valor nulo o m�s/ano de refer�ncia de baixa cont�bil
	 * das contas baixadas contabilmente no ano/m�s de refer�ncia 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil) 
		throws ErroRepositorioException;
	
	/**
	 * Seleciona todas as ocorrencias dos itens dos par�metros
	 * baixa cont�bil
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto
	 * @date 22/11/2006
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public Collection<ParametrosDevedoresDuvidososItem> pesquisaParametrosDevedoresDuvidososItem(
		Integer idParametrosDevedoresDuvidosos) throws ErroRepositorioException;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 01 Retorna o valor de �gua acumulado, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e m�s de refer�ncia do faturamento
	 * @param idLocalidade 		C�digo da localidade
	 * @param idCategoria 		C�digo da categoria
	 * @return retorna o valor acumulado de acordo com os par�metros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorAgua(int anoMesReferenciaBaixaContabil, 
		int idLocalidade, int idCategoria) throws ErroRepositorioException;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 02 Retorna o valor do esgoto acumulado, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e m�s de refer�ncia do faturamento
	 * @param idLocalidade 		C�digo da localidade
	 * @param idCategoria 		C�digo da categoria
	 * @return retorna o valor acumulado de acordo com os par�metros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorEsgoto(int anoMesReferenciaBaixaContabil, 
		int idLocalidade, int idCategoria) throws ErroRepositorioException ;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 03 Retorna o valor da categoria acumulado, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e m�s de refer�ncia do faturamento
	 * @param idLocalidade 		C�digo da localidade
	 * @param idCategoria 		C�digo da categoria
	 * @return retorna o valor acumulado de acordo com os par�metros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoAgua(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 03 Retorna o valor da categoria acumulado por financiamento tipo esgoto, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e m�s de refer�ncia do faturamento
	 * @param idLocalidade 		C�digo da localidade
	 * @param idCategoria 		C�digo da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os par�metros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoEsgoto(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 05 Retorna o valor da categoria acumulado por financiamento por gupo contabil, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e m�s de refer�ncia do faturamento
	 * @param idLocalidade 		C�digo da localidade
	 * @param idCategoria 		C�digo da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os par�metros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoServicos(
			int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria)
			throws ErroRepositorioException;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 06 Retorna o valor da categoria acumulado por financiamento juros parcelamentos, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e m�s de refer�ncia do faturamento
	 * @param idLocalidade 		C�digo da localidade
	 * @param idCategoria 		C�digo da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os par�metros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public ResumoDevedoresDuvidosos acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamento(
		int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria) 
		throws ErroRepositorioException ;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * Linha 07 Retorna o valor da categoria acumulado por financiamento por servi�o, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 * 
	 * @param anoMesReferencia 	Ano e m�s de refer�ncia do faturamento
	 * @param idLocalidade 		C�digo da localidade
	 * @param idCategoria 		C�digo da categoria
	 * 
	 * @return retorna o valor acumulado de acordo com os par�metros informados
	 * @throws ErroRepositorioException Erro no Hibernate
	 */
	public Collection acumularValorCategoriaDebitoTipoFinanciamentoServico(
			int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria)
			throws ErroRepositorioException ;	
	
	/**
	 * [UC0345] - Gerar Relatorio de Resumo da Arrecada��o
	 *
	 * @author Vivianne Sousa
	 * @date 10/04/2007
	 *
	 * @param idLancamentoTipo
	 * @throws ErroRepositorioException
	 */	
	public String obterDescricaoLancamentoTipo(Integer idLancamentoTipo) 
		throws ErroRepositorioException;
	
	/**
	 * [UC00175] Gerar Lan�amentos Cont�beis do Faturamento
	 *
	 * Pesquisa os par�metros cont�bil do faturamento.
	 *
	 * @author Pedro Alexandre
	 * @date 24/05/2007
	 *
	 * @param idCategoria
	 * @param idItemLancamentoContabil
	 * @param idItemLancamento
	 * @param idTipoLancamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParametrosContabilFaturamento(Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento) throws ErroRepositorioException;

	/**
	 * Pesquisa as localidades que tem resumo de faturamento 
	 * para o ano/m�s de faturamento informado.
	 *
	 * [UC00175] Gerar Lan�amentos Cont�beis do Faturamento
	 *
	 * @author Pedro Alexandre
	 * @date 25/05/2007
	 *
	 * @param anoMesFaturamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisFaturamento(Integer anoMesFaturamento) throws ErroRepositorioException;

	/**
	 * [UC00348] Gerar Lan�amentos Cont�beis da Arrecada��o
	 *
	 * Pesquisa os par�metros cont�bil da arrecada��o.
	 *
	 * @author Pedro Alexandre
	 * @date 31/05/2007
	 *
	 * @param idRecebimentoTipo	
	 * @param idCategoria
	 * @param idItemLancamentoContabil
	 * @param idItemLancamento
	 * @param idTipoLancamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParametrosContabilArrecadacao(Integer idRecebimentoTipo, Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento) throws ErroRepositorioException;

	/**
	 * Pesquisa as localidades que tem resumo da arrecada��o 
	 * para o ano/m�s de arrecada��o informado.
	 *
	 * [UC00348] Gerar Lan�amentos Cont�beis da Arrecada��o
	 *
	 * @author Pedro Alexandre
	 * @date 31/05/2007
	 *
	 * @param anoMesArrecadacao
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesParaGerarLancamentosContabeisArrecadacao(Integer anoMesArrecadacao) throws ErroRepositorioException;

	/**
	 * Pesquisa os par�metros dos devedores duvidosos por
	 * ano/m�s de refer�ncia cont�bil.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 06/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParametrosDevedoresDuvidosos pesquisarParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil) throws ErroRepositorioException ;

	/**
	 * Atualiza com o valor nulo o m�s/ano de refer�ncia de baixa cont�bil
	 * das contas baixadas contabilmente no ano/m�s de refer�ncia 
	 * para a localidade informada.
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre
	 * @date 22/11/2006, 06/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade)	throws ErroRepositorioException ;

	/**
	 * Exclui os registros da tabela RESUMO_DEVEDORES_DUVIDOSOS 
	 * por ano m�s ref�ncia cont�bil e localidade
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre
	 * @date 22/11/2006, 06/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * 
	 * @throws ErroRepositorioException
	 */	
	public void removeResumoDevedoresDuvidososPorAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade, Integer idTipoPerda) throws ErroRepositorioException;

	/**
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesGerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil)throws ErroRepositorioException ;

	/**
	 * Linha 01 Retorna o valor de �gua acumulado, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 12/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorAgua(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	/**
	 * Linha 02 Retorna o valor do esgoto acumulado, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os par�metros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorEsgoto(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	/**
	 * Linha 03 Retorna o valor da categoria acumulado por tipo financiamento por parcelamento agua, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os par�metros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoAgua(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	/**
	 * Linha 04 Retorna o valor da categoria acumulado por financiamento por parcelamento esgoto, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria.
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os par�metros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoEsgoto(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	/**
	 * Linha 06 Retorna o valor da categoria acumulado por financiamento juros parcelamentos, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return retorna o valor acumulado de acordo com os par�metros informados
	 * @throws ErroRepositorioException
	 */
	public BigDecimal acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoJurosParcelamento(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;

	/**
	 * Linha 05 Retorna o valor da categoria acumulado por financiamento por parcelamento servi�o, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaPorTipoFinanciamento(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria,int idFinanciamentoTipo, Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;
	
	/**
	 * Este met�do � utilizado para pesquisar os registros q ser�o
	 * usados para contru��o do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 *
	 * @author Fl�vio Cordeiro
	 * @date 06/06/2007
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCaern(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;

	/**
	 * Obt�m os dados do resumo dos devedores duvidosos 
	 * a partir do ano e m�s de refer�ncia cont�bil e da localidade.
	 *
	 * [UC0486] - Gerar Lan�amentos Cont�beis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre 
	 * @date 21/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosResumoDevedoresDuvidosos(Integer anoMesReferenciaContabil, Integer idLocalidade, Integer idTipoPerda) throws ErroRepositorioException ;

	/**
	 * [UC0486] Gerar Lan�amentos Cont�beis dos Devedores Duvidosos
	 *
	 * Pesquisa os par�metros cont�bil dos devedores duvidosos.
	 *
	 * @author Pedro Alexandre
	 * @date 21/06/2007
	 *
	 * @param idCategoria
	 * @param idItemLancamentoContabil
	 * @param idItemLancamento
	 * @param idTipoLancamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParametrosContabilDevedoresDuvidosos(Integer idCategoria,Integer idLancamentoItemContabil, Integer idItemLancamento,Integer idTipoLancamento,
			Integer idTipoPerda) throws ErroRepositorioException;

	/**
	 * Pesquisa a cole��o de ids das localidades para processar os lan�amentos 
	 * cont�beis dos devedores duvidosos.
	 *
	 * [UC0485] Gerar Lan�amentos Cont�beis dos Devedores Duvidosos
	 *
	 * @author Pedro Alexandre
	 * @date 25/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLocalidadesGerarLancamentosContabeisDevedoresDuvidosos(int anoMesReferenciaContabil)throws ErroRepositorioException ;

	/**
	 * Pesquisa uma cole��o de ids de lan�amentos cont�beis por localidade.
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idLancamentoOrigem
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsLancamentosContabeis(Integer anoMesReferenciaContabil, Integer idLocalidade, Integer idLancamentoOrigem)throws ErroRepositorioException ;

	/**
	 * Remove os Itens do lan�amento cont�bil.
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param idLancamentoContabil
	 * @throws ErroRepositorioException
	 */
	public void removerItensLancamentoContabil(Integer idLancamentoContabil) throws ErroRepositorioException;

	/**
	 * Remove os Lan�amentos Cont�beis.
	 *
	 * @author Pedro Alexandre
	 * @date 26/06/2007
	 *
	 * @param colecaoIdsLancamentosContabeis
	 * @throws ErroRepositorioException
	 */
	public void removerLancamentosContabeis(Collection<Integer> colecaoIdsLancamentosContabeis) throws ErroRepositorioException;

	/**
	 * Linha 05 Retorna o valor da categoria acumulado por financiamento por parcelamento servi�o, 
	 * de acordo com o ano/m�s de refer�ncia baixa contabil, a localiade, a categoria 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos 
	 *
	 * @author Pedro Alexandre
	 * @date 13/06/2007
	 *
	 * @param anoMesReferenciaBaixaContabil
	 * @param idLocalidade
	 * @param idCategoria
	 * @param colecaoIdsContas
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection acumularValorCategoriaDebitoCobradoCategoriaTipoFinanciamentoParcelamentoServico(int anoMesReferenciaBaixaContabil, int idLocalidade, int idCategoria,Collection<Integer> colecaoIdsContas) throws ErroRepositorioException ;
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 19/07/2007, 17/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstado(
			int anoMesReferencia, int tipoPerda) throws ErroRepositorioException ;


	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 19/07/2007, 17/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstadoPorGerenciaRegional(
			int anoMesReferencia, int tipoPerda) throws ErroRepositorioException ;
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 19/07/2007, 17/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioEstadoPorUnidadeNegocio(
			int anoMesReferencia, int tipoPerda) throws ErroRepositorioException;

	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa, Davi Menezes
	 * @created 19/07/2007, 17/11/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorEstadoPorLocalidade(
			int anoMesReferencia, int tipoPerda) throws ErroRepositorioException;
	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegional(
			int anoMesReferencia, Integer gerenciaRegional, int tipoPerda)throws ErroRepositorioException;


	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorGerenciaRegionalPorLocalidade(
			int anoMesReferencia, Integer gerenciaRegional, int tipoPerda)throws ErroRepositorioException ;

	
	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer unidadeNegocio, int tipoPerda) throws ErroRepositorioException;

	/**
	 * Consulta ResumoDevedoresDuvidosos para a gera��o do relat�rio 
	 * [UC0487] Gerar Relat�rio de Resumo de Devedores Duvidosos
	 * de acordo com a op��o de totaliza��o.
	 * 
	 * @author Vivianne Sousa
	 * @created 19/07/2007
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarResumoDevedoresDuvidososRelatorioPorLocalidade(
			int anoMesReferencia, Integer localidade, int tipoPerda)throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Remove as contas a receber cont�bil
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void removerContasAReceberContabil(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores de �gua e esgoto pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosContasCategoriaValorAguaEsgoto(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores de impostos pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosContasCategoriaValorImpostos(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos d�bitos cobrados para servi�os pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitosCobradosCategoriaServico(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos d�bitos cobrados para parcelamentos pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitosCobradosCategoriaParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores das guias de pagamento para entradas de parcelamento pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosGuiasPagamentoCategoriaEntradaParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores das guias de pagamento para servi�os pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosGuiasPagamentoCategoriaServico(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos realizados para pagamentos em duplicidade ou em excesso pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaPagamentoExcesso(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos realizados para descontos no parcelamento pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos realizados para descontos condicionais pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoCondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos realizados para descontos incondicionais pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDescontoIncondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos realizados para ajustes para zerar conta pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaAjusteZerarConta(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos realizados para devolu��es pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoriaDevolucao(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos d�bitos a cobrar para servi�o pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaServico(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos d�bitos a cobrar para documentos emitidos pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaDocumentosEmitidos(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos d�bitos a cobrar para financiamentos a cobrar de
	 * curto prazo pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaFinancimentosCurtoPrazo(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos d�bitos a cobrar para financiamentos a cobrar de
	 * longo prazo pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaFinancimentosLongoPrazo(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos d�bitos a cobrar para parcelamentos a cobrar de
	 * curto prazo pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaParcelamentosCurtoPrazo(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos d�bitos a cobrar para parcelamentos a cobrar de
	 * longo prazo pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaParcelamentosLongoPrazo(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos d�bitos a cobrar para arrasto de �gua, arrasto de
	 * esgoto e arrasto de servi�o pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaArrasto(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos a realizar para descontos concedidos no
	 * parcelamento pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontosParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores residuais para os descontos concedidos no parcelamento pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontosParcelamento(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos a realizar para devolu��es pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDevolucao(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores residuais para as devolu��es pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDevolucao(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos a realizar para descontos incondicionais pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontoIncondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores residuais para os descontos incondicionais pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontoIncondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos a realizar para contas pagas em excesso
	 * ou em duplicidade pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaPagamentoExcesso(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores residuais para os pagamentos em excesso ou duplicidade no parcelamento pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualPagamentoExcesso(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos a realizar para descontos condicionais pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaDescontoCondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores residuais para os descontos condicionais pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualDescontoCondicional(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores dos cr�ditos a realizar para ajustes para zerar contas
	 * pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaAjusteZerarConta(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores residuais para os ajustes para zerar a conta no parcelamento pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 27/08/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaContabil
	 *            Ano e m�s de refer�ncia contabil
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosCreditosARealizarCategoriaValorResidualAjusteZerarConta(
			int anoMesReferenciaContabil, Integer idLocalidade)
			throws ErroRepositorioException;

	/**
	 * Seleciona as quadras da localidade informada 
	 * onde existe contas a serem baixadas contabiolmente
	 * 
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * 
	 * @author Pedro Alexandre
	 * @date 22/11/2006
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> obterQuadrasPorLocalidadeParaGerarResumoDevedoresDuvidosos(int anoMesReferenciaContabil, int idLocalidade) throws ErroRepositorioException ;


	/**
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * verifica se a conta informada possui cliente respons�vel 
	 * com esfera de poder de tipo de cliente igual a municipal,
	 * estadual ou federal.
	 *
	 * @author Pedro Alexandre
	 * @date 23/07/2007
	 *
	 * @param idConta
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean verificarExistenciaClienteResponsavelConta(int idConta) throws ErroRepositorioException ;

	/**
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado
	 * @author: Francisco do Nascimento, Diogo Peixoto
	 * @date: 21/12/2007, 12/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) 
			throws ErroRepositorioException;

	/**
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado
	 * @author: Francisco do Nascimento, Diogo Peixoto
	 * @date: 26/12/2007, 12/04/2011 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) 
			throws ErroRepositorioException;
	
	/**
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado
	 * @author: Francisco do Nascimento, Diogo Peixoto
	 * @date: 07/01/2008, 12/04/2011 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorEstado(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio)
			throws ErroRepositorioException; 
	
	/**
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado
	 * @author: Francisco do Nascimento
	 * @date: 21/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException;

	/**
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado
	 * @author: Francisco do Nascimento
	 * @date: 26/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado
	 * @author: Francisco do Nascimento
	 * @date: 07/01/2008 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorGerenciaRegional(
			int anoMesReferencia) throws ErroRepositorioException;	

	/**
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 21/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer gerencia) throws ErroRepositorioException;

	/**
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 26/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer gerencia) throws ErroRepositorioException;
	
	/**
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado e por Unidade de Negocio
	 * @author: Francisco do Nascimento
	 * @date: 07/01/2008 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorUnidadeNegocio(
			int anoMesReferencia, Integer gerencia) throws ErroRepositorioException;
	
	/**
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado e por Localidade
	 * @author: Francisco do Nascimento
	 * @date: 21/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException;

	/**
	 * Consultar Saldo da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado e por Munic�pio
	 * @author: Diogo Peixoto
	 * @date: 12/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarSaldoEvolucaoContasAReceberContabilRelatorioPorMunicipio(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado e por Localidade
	 * @author: Francisco do Nascimento
	 * @date: 26/12/2007 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException;
	
	/**
	 * Consultar dados da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado e por Munic�pio
	 * @author: Diogo Peixoto
	 * @date: 12/04/2011 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosEvolucaoContasAReceberContabilRelatorioPorMunicipio(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado e por Localidade
	 * @author: Francisco do Nascimento
	 * @date: 07/01/2008 
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorLocalidade(
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio) throws ErroRepositorioException;
	
	/**
	 * Consultar dados de recebimentos do Relatorio da Evolucao de Contas a Receber Contabil 
	 * [UC0718 - Gerar Relat�rio da Evolu��o do Contas a Receber Cont�bil] por Estado e por Munic�pio
	 * @author: Diogo Peixoto
	 * @date: 12/04/2011
	 * 
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarRecebimentosContasAReceberContabilRelatorioPorMunicipio(
			int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC0751] - Gerar Valor Referente a Volumes Consumidos e N�o Faturados
	 * 
	 * Remove o valor dos volumes consumidos e n�o faturados 
	 * 
	 * @author Rafael Corr�a
	 * @date 19/02/2008
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaFaturamento
	 *            Ano e m�s de refer�ncia faturamento
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public void removerValorVolumesConsumidosNaoFaturados(
			int anoMesReferenciaFaturamento, Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
	 * [UC0751] - Gerar Valor Referente a Volumes Consumidos e N�o Faturados
	 * 
	 * Acumula os valores de �gua e esgoto pela ger�ncia, localidade e categoria
	 * 
	 * @author Rafael Corr�a
	 * @date 08/11/2007
	 * 
	 * @param idLocalidade
	 * @param anoMesReferenciaFaturamento
	 *            Ano e m�s de refer�ncia faturamento
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosValorVolumesConsumidosNaoFaturadosAguaEsgoto(
			int anoMesReferenciaFaturamento, Integer idLocalidade, Date ultimoDiaMesCorrenteFaturamento)
			throws ErroRepositorioException;
	
	/**
	 * [UC0717] - Consultar dados do relatorio de Saldo do Contas a Receber Contabil
	 * 
	 * @date 16/01/08
	 * @author Frncisco do Nascimento
	 * 
	 * @param anoMesReferencia
	 * @param gerencia
	 * @param unidadeNegocio
	 * @param localidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection consultarDadosRelatorioSaldoContasAReceberContabil(String opcaoTotalizacao,
			int anoMesReferencia, Integer gerencia, Integer unidadeNegocio, Integer localidade, Integer municipio) 
			throws ErroRepositorioException;
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @param quantidadeMaxima 
     * @param indice 
     * @date: 09/04/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa1(
            Integer referenciaInicio, Integer referenciaFinal ,Integer localidade,Short periodicidade) throws ErroRepositorioException;

    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 09/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa2(
            Integer referenciaInicio, Integer referenciaFinal ,Integer localidade,Short periodicidade) throws ErroRepositorioException;
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 09/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> consultarDadosContasBaixadasContabilmentePorQuadraFaixa3(
            Integer referenciaInicio, Integer referenciaFinal ,Integer localidade,Short periodicidade) throws ErroRepositorioException;
    
    /**
     * [[UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa1(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException;
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa2(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException;
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Vivianne Sousa
     * @date: 20/05/2008 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection consultarSomatorioValorContasBaixadasContabilmenteFaixa3(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException;
    
    /**
     * [UC0799] - Gerar Txt das Contas Baixadas Contabilmente
     * 
     * @author: Rafael Corr�a
     * @date: 29/05/2013 
     * 
     * @return
     * @throws ErroRepositorioException
     */
    public Collection consultarSomatorioValorDoacoesContasBaixadasContabilmente(
            Integer referenciaInicio, Integer referenciaFinal,Short periodicidade ) throws ErroRepositorioException;

    /**
     * [UC0714] - Gerar Contas a Receber Cont�bil
     *
     * Acumula os valores dos d�bitos a cobrar para juros cobrados de
     * longo prazo pela ger�ncia, localidade e categoria
     *
     * @author Rafael Corr�a
     * @date 26/05/2008
     *
     * @param idLocalidade
     * @param anoMesReferenciaContabil
     *            Ano e m�s de refer�ncia contabil
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitoACobrarCategoriaJurosCobrados(
            int anoMesReferenciaContabil, Integer idLocalidade)
            throws ErroRepositorioException;
    /**
	 * [UC0824] Gerar Relat�rio dos Par�metros Cont�beis
	 * 
	 * @author Bruno Barros
	 * @date 08/07/2008
	 * 
	 * @return Collection<RelatorioParametrosContabeisFaturamentoBean>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> 
		pesquisarDadosRelatorioParametrosContabeisFaturamento( Integer referenciaContabil ) 
		throws ErroRepositorioException;    
        
    /**
     * [UC0824] Gerar Relat�rio dos Par�metros Cont�beis
     * 
     * @author Bruno Barros
     * @date 08/07/2008
     * 
     * @return Collection<RelatorioParametrosContabeisArrecadacaoBean>
     * @throws ErroRepositorioException
     */
    public Collection<Object[]> 
        pesquisarDadosRelatorioParametrosContabeisArrecadacao( Integer referenciaContabil ) 
        throws ErroRepositorioException;            
    
	/**
	 * [UC0822] Gerar Relat�rio do Valor Referente a Volumes Consumidos e N�o Faturados
	 * 
	 * @author Victor Cisneiros
	 * @date 15/07/2008
	 */
    public List<RelatorioVolumesConsumidosNaoFaturadosBean> pesquisarVolumesConsumidosNaoFaturados(
    		Integer mesAno, String opcaoTotalizacao, Integer idEntidade) throws ErroRepositorioException;
	
	/**
	 * Este met�do � utilizado para pesquisar os registros q ser�o
	 * usados para contru��o do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integra��o para a Contabilidade da Caema
	 *
	 * @author Arthur Carvalho
	 * @date 02/03/09
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCaema(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;
	
	/**
	 * [UC0714] - Gerar Contas a Receber Cont�bil
	 * 
	 * Acumula os valores de �gua e esgoto pela ger�ncia, localidade e categoria
	 * 
	 * @author Vivianne Sousa
	 * @date 14/08/2009
	 * 
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 *             Erro no hibernate
	 */
	public Collection<Object[]> pesquisarDadosContasCategoriaValorAguaEsgoto(Integer idLocalidade)
			throws ErroRepositorioException;
	
	/**
     * [UC0714] - Gerar Contas a Receber Cont�bil
     * 
     * Acumula DCCG_VLCATEGORIA da tabela DEBITO_COBRADO_CATEGORIA 
     * com DBCB_ID=DBCB_ID da tabela DEBITO_COBRADO e 
     * CNTA_ID=CNTA_ID da tabela CONTA 
     * com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo
     *
     * @author Vivianne Sousa
     * @date 14/08/2009
     *
     * @param idLocalidade
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosDebitosCobradosCategoria( Integer idLocalidade)
            throws ErroRepositorioException;
    
    /**
     * [UC0714] - Gerar Contas a Receber Cont�bil
     * 
     * Acumula negativamente CRCG_VLCATEGORIA 
     * da tabela CREDITO_REALIZADO_CATEGORIA 
     * com CRRZ_ID=CRRZ_ID da tabela CREDITO_REALIZADO 
     * e CNTA_ID=CNTA_ID da tabela CONTA 
     * com CNTA_AMREFERENCIABAIXACONTABIL diferente de nulo
     *
     * @author Vivianne Sousa
     * @date 14/08/2009
     *
     * @param idLocalidade
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarDadosCreditosRealizadosCategoria(Integer idLocalidade)
            throws ErroRepositorioException;
    
    /**
     * [UC0714] - Gerar Contas a Receber Cont�bil
     * 
     * @author Vivianne Sousa
     * @date 17/08/2009
     *
     * @param idLocalidade
     * @param anoMesReferenciaArrecadacao
     * @throws ErroRepositorioException
     *             Erro no hibernate
     */
    public Collection<Object[]> pesquisarValorPagamentoImovel(
    		Integer idLocalidade, Integer anoMesReferenciaArrecadacao)
            throws ErroRepositorioException;
    
    /**
     * [UC0714] - Gerar Contas a Receber Cont�bil
	 * @author Vivianne Sousa
	 * @date 17/08/2009
	 * 
	 * @throws ErroRepositorioException
	 */
	public Localidade pesquisarUnidadeNegocioEGerenciaDaLocalidade(
			Integer idLocalidade)throws ErroRepositorioException;
	
	/**
	 * Este met�do � utilizado para pesquisar os registros q ser�o
	 * usados para contru��o do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integra��o para a Contabilidade - COSANPA
	 *
	 * @author Raphael Rossiter
	 * @date 17/11/2009
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCOSANPA(String idLancamentoOrigem, String anoMes) 
		throws ErroRepositorioException;
	
	/**
	 * [UC0992] Gerar Lan�amentos Cont�beis dos Avisos Banc�rios
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param anoMesReferenciaArrecadacao
	 * @return Collection
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarAvisosBancariosParaGerarLancamentosContabeis(Integer anoMesReferenciaArrecadacao) 
	throws ErroRepositorioException;
	
	/**
	 * [UC0992] Gerar Lan�amentos Cont�beis dos Avisos Banc�rios 
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param nomeConta
	 * @return ContaContabil
	 * @throws ErroRepositorioException
	 */
	public ContaContabil pesquisarContaContabilPorNomeConta(String nomeConta) 
	throws ErroRepositorioException;
	
	/**
	 * [UC0992] Gerar Lan�amentos Cont�beis dos Avisos Banc�rios 
	 *
	 * @author Raphael Rossiter
	 * @date 22/02/2010
	 *
	 * @param idAvisoBancario
	 * @param valorContabilizado
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorContabilizado(Integer idAvisoBancario, BigDecimal valorContabilizado)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaRecebimentos
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void removerDocumentosAReceberResumo(
			int anoMesReferenciaRecebimentos, Integer idLocalidade)
			throws ErroRepositorioException ;
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarContasAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException ;
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarGuiasPagamentoAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException ;
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDebitosACobrarAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException ;
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Raphael Rossiter
	 * @date 10/03/2010
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarCreditosARealizarAReceberParaResumo(
			int anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException ;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarImpostoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDividaAtivaResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDividaAtivaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoHistoricoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarImpostoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarOutrasReceitasResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoGuiaHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	public Collection pesquisarPagamentoNaoClassificadoResumoPagamentoConta(Date dataInicial, Date dataFinal)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoDebitoCobrarResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoNaoClassificadoHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoDebitoCobrarHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoHistoricoSemCorrespondenteResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarPagamentoGuiaResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fl�vio Cordeiro
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas
	 * 
	 */
	public Collection pesquisarResumoPagamentoContaCredito(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	public Collection pesquisarResumoPagamentoContaServico(Date dataInicial, Date dataFinal)
	throws ErroRepositorioException;
	
	public Collection pesquisarResumoHistoricoPagamentoContaCredito(Date dataInicial, Date dataFinal)
	throws ErroRepositorioException;
	
	public Collection pesquisarResumoHistoricoPagamentoContaServico(Date dataInicial, Date dataFinal)
	throws ErroRepositorioException;
	
	public Collection pesquisarResumoReceitaAgrupadoPorBanco(ResumoReceitaHelper resumo)
	throws ErroRepositorioException;
	
	public Collection pesquisarResumoReceitaRelatorioAnalitico(ResumoReceitaHelper resumo)
	throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorTotalContasDevedoresDuvidosos( int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra, String anoMesString,
			Integer idParametrosDevedoresDuvidosos )
		throws ErroRepositorioException ;
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles Filho
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarOutrasReceitasHistoricoResumoPagamentoConta(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles Filho
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDevolucaoAvisoBancarioResumo(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;
	
	/**
	 * 
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 * @throws ErroRepositorioException
	 * 
	 * Autor: Fernando Fontelles Filho
	 * [UC 0982] Gerar Resumo da Receita
	 * [SB 0001] Resumo dos Pagamentos de Contas 
	 * 
	 */
	public Collection pesquisarDevolucaoAvisoBancarioHistoricoResumo(Date dataInicial, Date dataFinal)
		throws ErroRepositorioException;

	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidosos(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra , Integer idParametrosDevedoresDuvidosos) throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidosos(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra ,Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos( int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidosos( int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorServicoParceladoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade,  Integer idQuadra, Integer idParametrosDevedoresDuvidosos ) throws ErroRepositorioException;
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorJurosDoParcelamentoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos )  throws ErroRepositorioException ;
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorPorTipoFinanciamentoDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)  throws ErroRepositorioException ;

	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idParametrosDevedoresDuvidosos
	 * @throws ErroRepositorioException
	 */
	public void atualizaContaAnoMesReferenciaContabilDevedoresDuvidosos(int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra ,
			Integer idParametrosDevedoresDuvidosos, Integer idTipoPerda ) throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 10/01/2011
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public Collection<Integer> pesquisarIdsQuadrasParaGerarResumoDevedoresDuvidosos (Integer idLocalidade) throws ErroRepositorioException;
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 10/01/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idQuadra
	 * @throws ErroRepositorioException
	 */
	public void atualizaContaAnoMesReferenciaContabil(int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra, Integer idPerdaTipo)	
			throws ErroRepositorioException ;
	

	/**
	 * 
	 * @author Arthur Carvalho
	 * @date 17/03/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDevolucoesValoresContaDevedoresDuvidosos(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra, Integer idParametrosDevedoresDuvidosos)  throws ErroRepositorioException ;
	
	
	/**
	 * [UC0989] Gerar Resumo de Documentos a Receber 
	 *
	 * @author Mariana Victor
	 * @date 28/03/2011
	 *
	 * @param anoMesReferenciaRecebimentos
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */
	public void removerDocumentosAReceberFaixaResumo(int anoMesReferenciaRecebimentos, Integer idLocalidade) throws ErroRepositorioException;
	
	/**
	 * Este met�do � utilizado para pesquisar os registros q ser�o
	 * usados para contru��o do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 *
	 * @author Tiago Moreno
	 * @date 28/06/11
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCosama(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;
	
	
	/**
	 * 
	 * @author Arthur Carvalho
	 * @param anoMesReferenciaContabil
	 * @param valorTotalValoresBaixados
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorBaixadoParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil, BigDecimal valorTotalValoresBaixados) throws ErroRepositorioException ;
	
	/**
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Corr�a
	 * @date 31/05/2013
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public void atualizarValorBaixadoParametrosDevedoresDuvidosos(Integer anoMesReferenciaContabil, Integer idTipoPerda, Integer idParametro) throws ErroRepositorioException;
	
	/**
	 * 
	 *  Pesquisa os Valores Baixados No resumo Agrupados Por Localidade
	 * 
	 * @author Arthur Carvalho
	 * @date 19/09/2011
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorBaixadoAgrupadoPorLocalidadeResumoDevedoresDuvidosos( int anoMesReferenciaContabil )  throws ErroRepositorioException ;
	
	/**
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [FS0009-Pesquisar Resumo Perdas �rg�o P�blico] 
	 * [FS0013] - Pesquisar Resumo de Recupera��o da Provis�o de Perdas Societ�rias
     *
	 * @author Arthur Carvalho
	 * @date 17/11/2011
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public boolean pesquisarResumoDevedoresDuvidososPerdasOrgaoPublico( int anoMesReferenciaContabil , int idTipoPerda)  throws ErroRepositorioException ;
	/**
	 * Este met�do � utilizado para pesquisar os registros q ser�o
	 * usados para contru��o do txt do caso de uso
	 * 
	 * @author Erivan Sousa
	 * @date 07/11/2011
	 * @param idLancamentoOrigem
	 * @param dtInicioIntervalo
	 * @param dtFimIntervalo
	 * @return Collection<object>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarGerarIntegracaoContabilidadeJUAZEIRO(String idLancamentoOrigem, String dtInicioIntervalo, String dtFimIntervalo)  throws ErroRepositorioException ;
	
	/**
	 * O sistema verifica se existe contas que atendam os crit�rios informados para baixa societ�ria
	 * 
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [SB0003] - Validar Crit�rios Para Perdas Societ�rias
	 * 
	 *  @author Arthur Carvalho
	 *  @date 18/11/2011
	 *   
	 * @param anoMesInicial
	 * @param anoMesFinal
	 * @param anoMesMenosNumeroDeMesesInformados
	 * @param colecaoEsferaPoder
	 * @param colecaoCategoria
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisaQuantidadeContasBaixaSocietaria(String anoMesInicial, String anoMesFinal, Integer anoMesMenosNumeroDeMesesInformados,
			Collection<Integer> colecaoEsferaPoder, Collection<Integer> colecaoCategoria) throws ErroRepositorioException;
	
	/**
	 * O sistema verifica se existe contas que atendam os crit�rios informados para baixa societ�ria
	 * 
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 * [SB0011] - Validar Crit�rios Para Perdas �rg�os P�blicos
	 * 
	 *  @author Arthur Carvalho
	 *  @date 18/11/2011
	 * 
	 * @param quantidadeMinimaInformada
	 * @param colecaoEsferaPoder
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Integer pesquisaQuantidadeContasBaixaPerdasOrgaoPublico(Integer quantidadeMinimaInformada, Collection<Integer> colecaoEsferaPoder ) 
			throws ErroRepositorioException;
	
	/**
	 * Pesquisa os par�metros Perdas Societarias por
	 * ano/m�s de refer�ncia cont�bil.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *[SB0004] - Processar Perdas Societ�rias
	 *
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParametrosPerdasSocietarias pesquisarParametrosPerdasSocietarias(Integer anoMesReferenciaContabil) throws ErroRepositorioException;
	
	/**
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * [SB0006] - Retira Baixa das Contas  
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */	
	public void retiraBaixaDasContasPerdasSocietarias(int anoMesReferenciaContabil)	throws ErroRepositorioException;
	
	/**
	 * Pesquisa os par�metros Perdas OrgaoPublico por ano/m�s de refer�ncia cont�bil.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ParametrosPerdasOrgaoPublico pesquisarParametrosPerdasOrgaoPublico(Integer anoMesReferenciaContabil) throws ErroRepositorioException;
	
	/**
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void deletarParametrosPerdasOrgaoPublico(int anoMesReferenciaContabil)	throws ErroRepositorioException;
	
	/**
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * [SB0007] - Excluir Par�metros Perdas Societ�rias  
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @throws ErroRepositorioException
	 */	
	public void deletarParametrosPerdasSocietarias(int anoMesReferenciaContabil) throws ErroRepositorioException;
	
	
	/**
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * [SB0008] - Excluir Resumo    
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void deletarResumoDevedoresDuvidososPerdasTipo(int anoMesReferenciaContabil, int idPerdaTipo) throws ErroRepositorioException;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * [SB0010] - Atualizar Conta
	 * 
	 * @author  Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaPerdasSocietarias(int anoMesReferenciaContabil , Integer anoMesInicial, Integer anoMesFinal, Integer anoMesMenosNumeroDeMesesInformados,
			ParametrosPerdasSocietarias parametros, Integer idLocalidade, Integer idQuadra) throws ErroRepositorioException;
	
	/**
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void retiraBaixaDasContasPerdasOrgaoPublico(int anoMesReferenciaContabil)	throws ErroRepositorioException;
	
	/**
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 * [SB0017] - Atualizar Conta Baixa �rg�os P�blicos
	 * 
	 * @author  Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaPerdasOrgaoPublico(int anoMesReferenciaContabil, ParametrosPerdasOrgaoPublico parametros, Integer idLocalidade,
			Integer idQuadra ) throws ErroRepositorioException ;
	
	/**
	 * Pesquisa Resumo devedores duvidos.
	 *
	 * [UC0485] Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Arthur Carvalho
	 * @date 21/11/2011
	 *
	 * @param anoMesReferenciaContabil
	 * @param idPerdaTipo
	 * @return
	 * @throws ErroRepositorioException
	 */
	public ResumoDevedoresDuvidosos pesquisarResumoDevedoresDuvidososTipoPerda(Integer anoMesReferenciaContabil, Integer idPerdaTipo) throws ErroRepositorioException ;
	
	/**
	 * @author Arthur Carvalho
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param idQuadra
	 * @param idTipoPerda
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(int anoMesReferenciaContabil, 
		Integer idLocalidade, Integer idQuadra) throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = ESGOTO
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (agua)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (esgoto)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (grupo contabil)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorServicoParceladoDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra) 
			throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (juros)
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorJurosDoParcelamentoDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = FINANCIAMENTOS COBRADOS 
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorPorTipoFinanciamentoDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDevolucoesValoresContaDevedoresDuvidososRecuperacaoPerdasSocietariasPagamento(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = AGUA
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra) throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = ESGOTO
	 * 
	 * @author Arthur Carvalho
	 * @date 08/11/2010
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (agua)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (esgoto)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (grupo contabil)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorServicoParceladoDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, Integer idLocalidade, 
		Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (juros)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorJurosDoParcelamentoDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = FINANCIAMENTOS COBRADOS 
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorPorTipoFinanciamentoDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException ;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDevolucoesValoresContaDevedoresDuvidososRecuperacaoPerdasSocietariasRetificadas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = AGUA
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra) throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = ESGOTO
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (agua)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorAguaParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (esgoto)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorEsgotoParcelamentoAgrupadoPorCategoriaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException ;
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (grupo contabil)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorServicoParceladoDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, Integer idLocalidade, 
		Integer idQuadra)  throws ErroRepositorioException ;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = PARCELAMENTOS COBRADOS (juros)
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorJurosDoParcelamentoDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = FINANCIAMENTOS COBRADOS 
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorPorTipoFinanciamentoDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * 
	 * @author Arthur Carvalho
	 * @date 22/11/2011
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarDevolucoesValoresContaDevedoresDuvidososRecuperacaoPerdasSocietariasParceladas(  int anoMesReferenciaContabil, 
			Integer idLocalidade, Integer idQuadra)  throws ErroRepositorioException;
	
	/**
	 * @author Arthur Carvalho
	 *
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosResumoVolumesConsumidosNaoFaturados(Integer anoMesReferenciaContabil, Integer idLocalidade) throws ErroRepositorioException ;
	
	
	/**
	 * [UC0841] Gerar Lan�amentos Cont�beis Volumes Consumidos N�o Faturados
	 *
	 * @author Arthur Carvalho
	 * @date 28/11/2011
	 *
	 * @param idCategoria
	 * @param idItemLancamento
	 * @param idTipoLancamento
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Object[] obterParametrosContabilVolumesConsumidosNaoFaturados(Integer idCategoria , Integer idItemLancamento,Integer idTipoLancamento) throws ErroRepositorioException;

	/**
	 * [UC1272] Gerar Arquivos EFD PIS/Confins
	 * 
	 * Obtem o valor do item
	 * 
	 * @author Erivan Sousa
	 * @date 30/01/2012
	 *
	 * @param percPis
	 * @param lancTipo
	 * @param lancItem
	 * @return BigDecimal
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorItemArquivoEFDPisConfins(Collection<Integer> lancTipo, Collection<Integer> lancItem, int anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC1272] Gerar Arquivos EFD-PIS/Confins
	 * 
	 * [SB0001] Gerar registro C600 do EFD-PIS/Confins
	 * 
	 * @author Raphael Rossiter
	 * @date 31/01/2012
	 * 
	 * @param anoMesReferencia
	 * @return Collection<Municipio>
	 * @throws ErroRepositorioException
	 */
	public Collection<Municipio> obterMunicipiosGerarArquivoEFDPisConfins(Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC1272] Gerar Arquivos EFD-PIS/Confins
	 * 
	 * [SB0001] Gerar registro C600 do EFD-PIS/Confins
	 * 
	 * @author Raphael Rossiter
	 * @date 31/01/2012
	 * 
	 * @param anoMesReferencia
	 * @param idMunicipio
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer obterQuantidadeContasResumoFaturamentoSimulacao(Integer anoMesReferencia, Integer idMunicipio) 
		throws ErroRepositorioException;
	
	/**
	 * [UC1272] Gerar Arquivos EFD PIS/Confins
	 * [SB0005] - Gerar registro F600 do EFD-PIS/Confins 
	 * 
	 * Obtem os dados para gerar o arquivo F600
	 * 
	 * @author Erivan Sousa
	 * @date 01/02/2012
	 *
	 * @return Collection
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosArquivoEFDPisConfinsF600(Integer anoMesReferencia, Municipio municipio) throws ErroRepositorioException;
	
	/**
	 * [UC1272] Gerar Arquivos EFD-PIS/Confins
	 * 
	 * [SB0001] ? Gerar registro C600 do EFD-PIS/Confins
	 * 
	 * @author Raphael Rossiter
	 * @date 31/01/2012
	 * 
	 * @param anoMesReferencia
	 * @return Integer
	 * @throws ErroRepositorioException
	 */
	public Integer obterQuantidadeContasResumoFaturamentoSimulacao(Integer anoMesReferencia) throws ErroRepositorioException;
	
	/**
	 * [UC1272] Gerar Arquivos EFD PIS/Confins
	 * 
	 * Obtem o valor do item
	 * 
	 * @author Erivan Sousa
	 * @date 30/01/2012
	 *
	 * @param percPis
	 * @param lancTipo
	 * @param lancItem
	 * @return BigDecimal
	 * 
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorItemArquivoEFDPisConfins(Collection<Integer> lancTipo, 
			Collection<Integer> lancItem, int anoMesReferencia, Integer idMunicipio) throws ErroRepositorioException;

	/**
	 * [UC1272] Gerar Arquivos EFD PIS/Confins
	 * [SB0009] - Gerar registro M230 do EFD-PIS/Confins 
	 * [SB0010] - Gerar registro M630 do EFD-PIS/Confins 
	 * 
	 * Obtem os dados para gerar o arquivo M230 e M630
	 * 
	 * @author Erivan Sousa
	 * @date 06/03/2012
	 *
	 * @return Collection
	 * 
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterDadosArquivoEFDPisConfinsM230_M630(Integer anoMesReferencia, Municipio municipio) throws ErroRepositorioException;
	
	/**
	 *
	 *
	 * @author Rodrigo Cabral
	 * @date 17/10/2012
	 *
	 * @throws ErroRepositorioException
	 */	
	public void removerLancamentosContabeisEItens(Integer idLocalidade, Integer anoMesReferenciaContabil, Integer lancamentoOrigem)
		throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 * Por Esfera de Poder
	 *  
	 *  TIPO FINANCIAMENTO = AGUA
	 * 
	 * @author Anderson Cabral
	 * @date 08/11/2010
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> obterValorTotalContasDevedoresDuvidososPorEsferaDePoder( int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra, String anoMesString, 
			Integer idParametrosDevedoresDuvidosos, Collection colecaoEsferas)
			throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 * @author Rafael Corr�a
	 * @date 28/05/2013
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idParametrosDevedoresDuvidosos
	 * @return
	 * @throws ErroRepositorioException
	 */
	public BigDecimal obterValorTotalContasDevedoresDuvidosos( int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra, String anoMesString, 
			Integer idParametrosDevedoresDuvidosos, Collection colecaoEsferas) throws ErroRepositorioException;
	
	/**
	 * Atualiza com o valor de refer�ncia de baixa cont�bil
	 * das contas baixadas contabilmente no ano/m�s de refer�ncia 
	 *
	 * [UC0485] - Gerar Resumo dos Devedores Duvidosos
	 *
	 * @author Rafael Pinto, Pedro Alexandre, Arthur Carvalho, Anderson Cabral
	 * @date 22/11/2006, 14/06/2007 , 06/12-2010, 04/02/2013
	 *
	 * @param anoMesReferenciaContabil
	 * @param colecaoIdsContas
	 * @throws ErroRepositorioException
	 */	
	public void atualizaContaAnoMesReferenciaContabilDevedoresDuvidososPorEsferaDoPoder(int anoMesReferenciaContabil, Integer idLocalidade, Integer idQuadra ,
			Integer idParametrosDevedoresDuvidosos , Integer idTipoPerda, String colecaoEsferas ) throws ErroRepositorioException;
	
	/**
	 * [SB0001] - Acumular o resumo dos devedores duvidosos
	 *  
	 *  TIPO FINANCIAMENTO = ESTADUAL
	 * 
	 * @author Rafael Pinto
	 * @date 11/04/2013
	 * 
	 * @param anoMesReferenciaContabil
	 * @param idLocalidade
	 * @param anoMesString
	 * @param idTipoPerda
	 * 
	 * @return Collection<Object[]>
	 * @throws ErroRepositorioException
	 */
	public Collection<Object[]> pesquisarValorTotalEsferaPoderEstadualAgrupadoPorCategoriaDevedoresDuvidosos(
		int anoMesReferenciaContabil,Integer idLocalidade, Integer idQuadra, 
		Integer idTipoPerda) throws ErroRepositorioException ;

	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 12/04/2013
	 *
	 * @throws ErroRepositorioException
	 */
	public void gerarContasAReceberContabilSintetico() throws ErroRepositorioException, SQLException;
	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 12/04/2013
	 *
	 * @throws ErroRepositorioException
	 */
	public void gerarContasAReceberContabilAnalitico() throws ErroRepositorioException, SQLException;
	
	/**
	 * 
	 * @author Vivianne Sousa
	 * @date 12/04/2013
	 *
	 * @throws ErroRepositorioException
	 */
	public void gerarContasAReceberContabilAnaliticoTabelaAuxiliar() throws ErroRepositorioException, SQLException;
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 * @throws SQLException 
	 */
	public Boolean pesquisarAnoMesReferencia(Integer anoMesReferenciaContabil)
			throws ErroRepositorioException;
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 * @throws SQLException 
	 */
	public void removeParametrosPerdasFiscaisItens(int idParametrosPerdasFiscais)
			throws ErroRepositorioException;
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 * @throws SQLException 
	 */
	public void atualizarValorABaixarParametrosDevedoresDuvidosos(Integer idParametrosDevedoresDuvidosos, BigDecimal valorABaixar)
			throws ErroRepositorioException;
	
	/**
	 * @author Ricardo Germinio
	 * @date 10/12/2012
	 * 
	 * @throws ErroRepositorioException
	 * @throws SQLException 
	 */	
	
	public ParametrosPerdasFiscaisHelper pesquisarParametrosPerdasFiscais(ParametrosPerdasFiscaisHelper parametrosPerdasFiscaisHelper)
			throws ErroRepositorioException;
	
	
	/** Este met�do � utilizado para pesquisar os registros q ser�o
	 * usados para contru��o do txt do caso de uso
	 * 
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 * [SB0008] Gera arquivo txt para a CAER
	 *
	 * @author Hugo Azevedo
	 * @date 28/03/2014
	 *
	 * @param idLancamentoOrigem
	 * @param anoMes
	 * @return
	 * @throws ErroRepositorioException
	 */
	public Collection pesquisarGerarIntegracaoContabilidadeCaer(String idLancamentoOrigem, String anoMes) throws ErroRepositorioException;
	
	/**
	 * Este caso de uso gera a integra��o para a contabilidade
	 *
	 * [UC0469] Gerar Integra��o para a Contabilidade
	 * 
	 * Altera a n�mera��o do sequencial no lancamento cont�bil
	 * 
	 * @author Bruno Barros
	 * @date 10/10/2014
	 * @param idLancamento - Id do lancamento que ter� seu sequencial atualizado
	 * @param sequencial - Novo valor do sequencial
	 * 
	 * @throws ErroRepositorioException
	 */
	public void atualizarUltimoSequencialLancamentoContabil (int idLancamento, int sequencial)
			throws ErroRepositorioException;	     
	
}