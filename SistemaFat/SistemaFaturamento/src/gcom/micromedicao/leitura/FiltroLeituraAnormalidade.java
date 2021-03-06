/* Feito por Felipe na data 13/12/2005
 * pela causa deste filtro na [UC0091] Alterar Dados Faturamento */

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
package gcom.micromedicao.leitura;

import java.io.Serializable;

import gcom.util.filtro.Filtro;

public class FiltroLeituraAnormalidade extends Filtro implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor for the FiltroLeituraAnormalidade object
	 * 
	 * @param campoOrderBy
	 *            Description of the Parameter
	 */

    public FiltroLeituraAnormalidade(String campoOrderBy) {
        this.campoOrderBy = campoOrderBy;
    }	
    
    public FiltroLeituraAnormalidade(){}
	
    public final static String INDICADOR_USO = "indicadorUso";	
		
	// Colocado o ID por Felipe e por "" ,por enquanto, s� para o
	// exibirDadosFaturamentoAction
	public final static String ID = "id";
	
	public final static String DESCRICAO = "descricao";
	
	public final static String INDICADOR_RELATIVO_HIDROMETRO = "indicadorRelativoHidrometro";
	
	public final static String INDICADOR_IMOVEL_SEM_HIDROMETRO = "indicadorImovelSemHidrometro";
	
	public final static String INDICADOR_USO_SISTEMA = "indicadorSistema";
	
	public final static String INDICADOR_PERDA_TARIFA_SOCIAL = "indicadorPerdaTarifaSocial";
	
	public final static String INDICADOR_EMISSAO_ORDEM_SERVICO = "indicadorEmissaoOrdemServico";
	
	public final static String ID_TIPO_SERVICO = "servicoTipo";
	
	public final static String ID_CONSUMO_A_COBRAR_SEM_LEITURA = "leituraAnormalidadeConsumoSemleitura.id";
	
	public final static String ID_CONSUMO_A_COBRAR_COM_LEITURA = "leituraAnormalidadeConsumoComleitura.id";
	
	public final static String ID_LEITURA_A_FATURAR_SEM_LEITURA = "leituraAnormalidadeLeituraSemleitura.id";
	
	public final static String ID_LEITURA_A_FATURAR_COM_LEITURA = "leituraAnormalidadeLeituraComleitura.id";
	
	public final static String NUMERO_VEZES_ANORMALIDADE_SUSPENDER_LEITURA = "numeroVezesSuspendeLeitura";
	
	public final static String NUMERO_MESES_LEITURA_SUSPENSA = "numeroMesesLeituraSuspensa";
	
	public final static String INDICADOR_EXIBIR_ANORMALIDADE_RELATORIO = "indicadorExibirAnormalidadeRelatorio";
}
