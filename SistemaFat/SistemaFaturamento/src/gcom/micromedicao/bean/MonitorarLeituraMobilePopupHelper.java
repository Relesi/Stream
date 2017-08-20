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
 * R�mulo Aur�lio de Melo Souza Filho
 * Rosana Carvalho Barbosa
 * S�vio Luiz de Andrade Cavalcante
 * Tai Mu Shih
 * Thiago Augusto Souza do Nascimento
 * Tiago Moreno Rodrigues
 * Vivianne Barbosa Sousa
 * Yara Taciane de Souza
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

package gcom.micromedicao.bean;

import gcom.micromedicao.consumo.ConsumoHistorico;

import java.io.Serializable;
import java.util.Collection;

/**
 *[UC0932] Monitorar Leituras Transmitidas
 *
 * Classe de apoio para o caso de uso acima
 * 
 * @author bruno
 *
 */

public class MonitorarLeituraMobilePopupHelper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String inscricao;
	private String idImovel;
	private String sequencialRota;
	private String leituraAnterior;
	private String leituraAtual;
	private String idAnormalidade;
	private String dtLeitura;
	private String dtRecebimento;
	private String icEmissaoConta;
	private String motivoNaoEmissao;	
	private String dadosCliente;
	private Collection<ConsumoHistorico> colConsumos;
	private String idTipoMedicao;
	private Integer idConsumoAnormalidade;
	private String descricaoAbreviadaAnormalidadeConsumo;
	
	public String getDtLeitura() {
		return dtLeitura;
	}
	public void setDtLeitura(String dtLeitura) {
		this.dtLeitura = dtLeitura;
	}
	public String getDtRecebimento() {
		return dtRecebimento;
	}
	public void setDtRecebimento(String dtRecebimento) {
		this.dtRecebimento = dtRecebimento;
	}
	public String getIcEmissaoConta() {
		return icEmissaoConta;
	}
	public void setIcEmissaoConta(String icEmissaoConta) {
		this.icEmissaoConta = icEmissaoConta;
	}
	public String getIdAnormalidade() {
		return idAnormalidade;
	}
	public void setIdAnormalidade(String idAnormalidade) {
		this.idAnormalidade = idAnormalidade;
	}
	public String getIdImovel() {
		return idImovel;
	}
	public void setIdImovel(String idImovel) {
		this.idImovel = idImovel;
	}
	public String getInscricao() {
		return inscricao;
	}
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}
	public String getLeituraAnterior() {
		return leituraAnterior;
	}
	public void setLeituraAnterior(String leituraAnterior) {
		this.leituraAnterior = leituraAnterior;
	}
	public String getLeituraAtual() {
		return leituraAtual;
	}
	public void setLeituraAtual(String leituraAtual) {
		this.leituraAtual = leituraAtual;
	}
	public String getMotivoNaoEmissao() {
		return motivoNaoEmissao;
	}
	public void setMotivoNaoEmissao(String motivoNaoEmissao) {
		this.motivoNaoEmissao = motivoNaoEmissao;
	}
	public String getSequencialRota() {
		return sequencialRota;
	}
	public void setSequencialRota(String sequencialRota) {
		this.sequencialRota = sequencialRota;
	}
	public String getDadosCliente() {
		return dadosCliente;
	}
	public void setDadosCliente(String dadosCliente) {
		this.dadosCliente = dadosCliente;
	}
	public Collection<ConsumoHistorico> getColConsumos() {
		return colConsumos;
	}
	public void setColConsumos(Collection<ConsumoHistorico> colConsumos) {
		this.colConsumos = colConsumos;
	}
	public String getIdTipoMedicao() {
		return idTipoMedicao;
	}
	public void setIdTipoMedicao(String idTipoMedicao) {
		this.idTipoMedicao = idTipoMedicao;
	}
	public Integer getIdConsumoAnormalidade() {
		return idConsumoAnormalidade;
	}
	public void setIdConsumoAnormalidade(Integer idConsumoAnormalidade) {
		this.idConsumoAnormalidade = idConsumoAnormalidade;
	}
	public String getDescricaoAbreviadaAnormalidadeConsumo() {
		return descricaoAbreviadaAnormalidadeConsumo;
	}
	public void setDescricaoAbreviadaAnormalidadeConsumo(
			String descricaoAbreviadaAnormalidadeConsumo) {
		this.descricaoAbreviadaAnormalidadeConsumo = descricaoAbreviadaAnormalidadeConsumo;
	}
}
