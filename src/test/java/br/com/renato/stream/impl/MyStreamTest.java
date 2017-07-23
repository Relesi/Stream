package br.com.renato.stream.impl;

import org.junit.Assert;
import org.junit.Test;

import br.com.renato.exception.ConsoanteNaoExisteException;
import br.com.renato.exception.VogalNaoEncontradaException;

public class MyStreamTest {
	
	@Test(expected = ConsoanteNaoExisteException.class)
	public void teste_quando_nao_contem_consoante(){
		new MyStream("AaeEUiOiU");
	}
	
	@Test(expected = VogalNaoEncontradaException.class)
	public void teste_quando_nao_contem_vogal(){
		new MyStream("FWstRL");
	}
	
	@Test(expected = VogalNaoEncontradaException.class)
	public void teste_quando_nao_contem_vogal_apos_a_consoante(){
		new MyStream("AEeiUFWstRL");
	}
	
	@Test(expected = VogalNaoEncontradaException.class)
	public void teste_quando_todas_as_vogais_se_repetem(){
		new MyStream("AcTaEiteIOuUo");
	}
	
	@Test
	public void teste_deve_achar_a_vogal_e_retonar_false_quando_ela_for_o_ultimo_caracter(){
		String input = "aAbBABacfe";
		MyStream stream = new MyStream(input);
		for (int i = 0; i < input.length(); i++){
			if (i == (input.length() - 1)){
				Assert.assertNotNull(stream.getNext());
				Assert.assertFalse(stream.hasNext());
			} else {
				Assert.assertNotNull(stream.getNext());
				Assert.assertTrue(stream.hasNext());
			}
		}
	}
	
	@Test
	public void teste_deve_achar_a_vogal_e_retonar_false_quando_ela_nao_for_o_ultimo_caracter(){
		String input = "aAbBABacfeAfU";
		MyStream stream = new MyStream(input);
		int index = 0;
		do {
			stream.getNext();
			index++;
		}while(stream.hasNext());
		Assert.assertEquals(10, index);
	}
	
	@Test
	public void teste_deve_achar_a_vogal_e_retonar_false_quando_ela_for_o_segundo_caracter(){
		String input = "TaEbBiBIcfeOfU";
		MyStream stream = new MyStream(input);
		int index = 0;
		do {
			stream.getNext();
			index++;
		}while(stream.hasNext());
		Assert.assertEquals(2, index);
	}

}
