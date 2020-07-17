package TabuleiroJogo;

public class Tabuleiro {
	
	private int linhas;
	private int colunas;
	private Peca[][] pecas;
	
	public Tabuleiro(int linhas, int colunas) {
		if(linhas < 1 || colunas < 1) {
			throw new TabuleiroException("Erro ao criar tabuleiro: É necessario criar ao menos 1 linha e 1 coluna");
		}
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new Peca[linhas][colunas];
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}
	
	public Peca Peca(int linha, int coluna) {
		if(!PosicaoExist(linha, coluna)){
			throw new TabuleiroException("Posição não existe no tabuleiro");
		}
		return pecas[linha][coluna];
	}
	
	public Peca Peca(Posicao posicao) {
		if(!PosicaoExist(posicao)){
			throw new TabuleiroException("Posição não existe no tabuleiro");
		}
		return pecas[posicao.getLinha()][posicao.getColuna()];
	}
	
	public void ColocarPeca(Peca peca, Posicao posicao) {
		if(TemUmaPeca(posicao)) {
			throw new TabuleiroException("Já existe uma peça nessa posição !");
		}
		pecas[posicao.getLinha()][posicao.getColuna()] = peca;
		peca.posicao = posicao;
	}
	
	public Peca RemoverPeca(Posicao posicao) {
		if(!PosicaoExist(posicao)) {
			throw new TabuleiroException("Essa posição não existe !!");
		}
		if(Peca(posicao) == null){
			return null;
		}
		Peca aux = Peca(posicao);
		aux.posicao = null;
		pecas[posicao.getLinha()][posicao.getColuna()] = null;
		return aux;
	}
	
	private boolean PosicaoExist(int linha, int coluna) {
		return linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas;
	}
	
	public boolean PosicaoExist(Posicao posicao) {
		return PosicaoExist(posicao.getLinha(), posicao.getColuna());
	}
	
	public boolean TemUmaPeca(Posicao posicao) {
		if(!PosicaoExist(posicao)){
			throw new TabuleiroException("Essa posição não existe no tabuleiro");
		}
		return Peca(posicao) !=  null;
	}
}
