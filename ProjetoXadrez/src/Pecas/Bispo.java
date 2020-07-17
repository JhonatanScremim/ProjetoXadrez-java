package Pecas;
import Xadrez.PecaXadrez;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import Xadrez.Cor;


public class Bispo extends PecaXadrez {
	
	public Bispo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "B";
	}
	
	

	@Override
	public boolean[][] MovimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		Posicao p = new Posicao(0,0);
		//Noroeste
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() -1);
		while(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() - 1, p.getColuna() - 1);
		}
		if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Nordeste
		p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 1);
		while(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() - 1, p.getColuna() + 1);
		}
		if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Sudeste
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 1);
		while(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() + 1, p.getColuna() + 1);
		}
		if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		//Sudoeste
		p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 1);
		while(getTabuleiro().PosicaoExist(p) && !getTabuleiro().TemUmaPeca(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
			p.setValor(p.getLinha() + 1, p.getColuna() - 1);
		}
		if(getTabuleiro().PosicaoExist(p) && UmaPecaInimiga(p)) {
			mat[p.getLinha()][p.getColuna()] = true;
		}
		return mat;
	}
	
	
}
