package gcom.mobile.execucaoordemservico;

import gcom.util.filtro.Filtro;

import java.io.Serializable;

public class FiltroExecucaoOSFoto extends Filtro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String ID_ORDEM_SERVICO = "ordemServico.id";
	public static final String ID_ARQUIVO= "arquivoTextoOSCobranca.id";
	public static final String ID_SITUACAO= "fotoSituacaoOrdemServico.id";
	
	public static final String CAMINHO_SITUACAO= "fotoSituacaoOrdemServico";
	 
}
