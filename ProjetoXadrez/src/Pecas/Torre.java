package Pecas;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;

public class Torre extends PecaXadrez{

	public Torre(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "T";
	}
	
	@Override
	public boolean[][] MovimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		//Acima da peça
		p.setValor(posicao.getLinha() - 1, posicao.getColuna());
		while(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() - 1);
		}
		if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Esquerda
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1);
		while(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() - 1);
		}
		if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Direita
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1);
		while(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setColuna(p.getColuna() + 1);
		}
		if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Abaixo
		p.setValor(posicao.getLinha() + 1, posicao.getColuna());
		while(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setLinha(p.getLinha() + 1);
		}
		if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
	
}
