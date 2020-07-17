package Pecas;

import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;

public class Rei extends PecaXadrez{

	public Rei(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "R";
	}

	private boolean PodeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().Peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] MovimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0,0);
		//Acima
		p.setValor(posicao.getLinha() - 1, posicao.getColuna());
		if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Abaixo
		p.setValor(posicao.getLinha() + 1, posicao.getColuna());
		if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Esquerda
		p.setValor(posicao.getLinha(), posicao.getColuna() - 1);
		if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Direita
		p.setValor(posicao.getLinha(), posicao.getColuna() + 1);
		if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Noroeste
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 1);
		if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Nordeste
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
		if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Sudoeste
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Sudeste
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
		if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}

}
