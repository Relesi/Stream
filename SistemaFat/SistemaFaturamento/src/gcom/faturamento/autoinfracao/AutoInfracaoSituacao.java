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
package gcom.faturamento.autoinfracao;

import java.io.Serializable;
import java.util.Date;

public class AutoInfracaoSituacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String descricao;
	
	private Short indicadorUso;
	
	private Date ultimaAlteracao;
	
	private Short indicadorGerarDebito;
	
	private Short indicadorDataInicioRecurso;
	
	private Short indicadorDataTerminoRecurso;
	
	private Integer numeroDiasPrazoRecursoInfracao;
	
	
	
	public static final Integer AUTO_EM_PRAZO_DE_RECURSO = 1;
	
	public static final Integer AUTO_COM_PRAZO_DE_RECURSO_VENCIDO = 2;
	
	public static final Integer AUTO_COM_RECURSO_EM_ANALISE_JULGAMENTO = 3;
	
	public static final Integer AUTO_COM_RECURSO_JULGADO_PROCEDENTE = 4;
	
	public static final Integer AUTO_COM_RECURSO_JULGADO_IMPROCEDENTE = 5;
	
	public static final Integer AUTO_CANCELADO = 6;
	
	
	
	
	
	public static final Short OBRIGATORIO = 1;
	
	public static final Short OPCIONAL = 2;
	
	public static final Short NULO = 3;
	
	
	public Short getIndicadorDataInicioRecurso() {
		return indicadorDataInicioRecurso;
	}

	public void setIndicadorDataInicioRecurso(Short indicadorDataInicioRecurso) {
		this.indicadorDataInicioRecurso = indicadorDataInicioRecurso;
	}

	public Short getIndicadorDataTerminoRecurso() {
		return indicadorDataTerminoRecurso;
	}

	public void setIndicadorDataTerminoRecurso(Short indicadorDataTerminoRecurso) {
		this.indicadorDataTerminoRecurso = indicadorDataTerminoRecurso;
	}

	public Short getIndicadorGerarDebito() {
		return indicadorGerarDebito;
	}

	public void setIndicadorGerarDebito(Short indicadorGerarDebito) {
		this.indicadorGerarDebito = indicadorGerarDebito;
	}

	public Integer getNumeroDiasPrazoRecursoInfracao() {
		return numeroDiasPrazoRecursoInfracao;
	}

	public void setNumeroDiasPrazoRecursoInfracao(
			Integer numeroDiasPrazoRecursoInfracao) {
		this.numeroDiasPrazoRecursoInfracao = numeroDiasPrazoRecursoInfracao;
	}

	public AutoInfracaoSituacao(){}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getIndicadorUso() {
		return indicadorUso;
	}

	public void setIndicadorUso(Short indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}
}
