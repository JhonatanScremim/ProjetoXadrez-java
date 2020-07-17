package Xadrez;

import TabuleiroJogo.Peca;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;

public abstract class PecaXadrez extends Peca{

	private Cor cor;
	private int movimentos;

	public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public PosicaoXadrez getPosicaoXadrez() {
		return PosicaoXadrez.ConvertXadrezPosicao(posicao);
	}
	
	protected boolean UmaPecaInimiga(Posicao posicao) {
		PecaXadrez p = (PecaXadrez)getTabuleiro().Peca(posicao);
		return p != null && p.getCor() != cor;
		
	}
	
	public int getMovimentos() {
		return movimentos;
	}
	
	public void AddMovimento() {
		movimentos++;
	}
	
	public void RemoveMovimento() {
		movimentos++;
	}
	
}
