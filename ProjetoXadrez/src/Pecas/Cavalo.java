package Pecas;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;
import Xadrez.Cor;
import Xadrez.PecaXadrez;


public class Cavalo extends PecaXadrez {
	
	public Cavalo(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString() {
		return "C";
	}

	private boolean PodeMover(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().Peca(posicao);
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] MovimentosPossiveis() {
		boolean[][] mat = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		Posicao p = new Posicao(0,0);
				p.setValor(posicao.getLinha() - 1, posicao.getColuna() - 2);
				if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValor(posicao.getLinha() - 2, posicao.getColuna() - 1);
				if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValor(posicao.getLinha() - 2, posicao.getColuna() + 1);
				if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValor(posicao.getLinha() - 1, posicao.getColuna() + 2);
				if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValor(posicao.getLinha() + 1, posicao.getColuna() + 2);
				if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValor(posicao.getLinha() + 2, posicao.getColuna() + 1);
				if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}

				p.setValor(posicao.getLinha() + 2, posicao.getColuna() - 1);
				if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
				p.setValor(posicao.getLinha() + 1, posicao.getColuna() - 2);
				if(getTabuleiro().PosicaoExist(p) && PodeMover(p)) {
					mat[p.getLinha()][p.getColuna()] = true;
				}
		
		return mat;
	}
}
