package Pecas;

import Xadrez.PecaXadrez;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import Xadrez.Cor;

public class Peao extends PecaXadrez{

	public Peao(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "P";
	}

	@Override
	public boolean[][] MovimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0,0);
		//Movimentos para o pe�o branco
		if(getCor() == Cor.BRANCA) {
			//Avan�ar uma casa
			p.setValor(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//Avan�ar duas casas no come�o de jogo
			p.setValor(posicao.getLinha() - 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p) &&
					getTabuleiro().PosicaoExist(p2) 
					&& !getTabuleiro().TemUmaPeca(p2)
					&& getMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//Matar uma pe�a na diagonal esquerda
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() -1);
			if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//Matar uma pe�a na diagonal direita
			p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		//Movimentos para o pe�o preta
		else {
			//Avan�ar uma casa
			p.setValor(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//Avan�ar duas casas no come�o de jogo
			p.setValor(posicao.getLinha() + 2, posicao.getColuna());
			Posicao p2 = new Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p) &&
					getTabuleiro().PosicaoExist(p2) 
					&& !getTabuleiro().TemUmaPeca(p2)
					&& getMovimentos() == 0) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//Matar uma pe�a na diagonal esquerda
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() -1);
			if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
			//Matar uma pe�a na diagonal direita
			p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
				mat[p.getLinha()][p.getColuna()] = true;
			}
		}
		return mat;
	}
	
}
