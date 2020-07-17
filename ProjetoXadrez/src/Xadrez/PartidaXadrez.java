package Xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Pecas.Bispo;
import Pecas.Cavalo;
import Pecas.Peao;
import Pecas.Rainha;
import Pecas.Rei;
import Pecas.Torre;
import TabuleiroJogo.Peca;
import TabuleiroJogo.Posicao;
import TabuleiroJogo.Tabuleiro;

public class PartidaXadrez {

	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private List<Peca> pecasTabuleiro = new ArrayList<>();
	private List<Peca> pecasCapturadas = new ArrayList<>();
	private boolean check;
	private boolean checkMate;
	
	
	public PartidaXadrez() {
		tabuleiro = new Tabuleiro(8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		Iniciar();
	}
	
	
	public PecaXadrez[][] getPecas(){
		PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
		for(int i = 0; i < tabuleiro.getLinhas(); i++) {
			for(int j = 0; j < tabuleiro.getColunas(); j++) {
				mat[i][j] = (PecaXadrez)tabuleiro.Peca(i, j);
			}
		}
		return mat;
	}
	
	public int getTurno() {
		return turno;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public Cor getJogadorAtual() {
		return jogadorAtual;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	
	
	public boolean[][] MovimentosPossiveisTabuleiro(PosicaoXadrez posicaoOrigem){
		Posicao posicao = posicaoOrigem.ConvertPosicao();
		ValidarPosicaoOrigem(posicao);
		return tabuleiro.Peca(posicao).MovimentosPossiveis();
	}
	
	public PecaXadrez ExecutarMovimento(PosicaoXadrez posicaoSelecionada, PosicaoXadrez posicaoDestino) {
		Posicao origem = posicaoSelecionada.ConvertPosicao();
		Posicao destino = posicaoDestino.ConvertPosicao();
		ValidarPosicaoOrigem(origem);
		ValidarPosicaoDestino(origem, destino);
		Peca capturaPeca = Mover(origem, destino);
		if(TesteCheck(jogadorAtual)) {
			DesfazerMovimento(origem, destino, capturaPeca);
			throw new XadrezException("Você não pode se colocar em check");
		}
		check = (TesteCheck(Oponente(jogadorAtual))) ? true : false;
		if(TesteCheckMate(Oponente(jogadorAtual))) {
			checkMate = true;
		}
		else {
			ProxTurno();	
		}
		return (PecaXadrez)capturaPeca;
	}
	
	public void DesfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
		PecaXadrez p = (PecaXadrez)tabuleiro.RemoverPeca(destino);
		p.RemoveMovimento();
		tabuleiro.ColocarPeca(p, origem);
		if(pecaCapturada != null) {
			tabuleiro.ColocarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasTabuleiro.add(pecaCapturada);
		}
	}
	
	private void ValidarPosicaoOrigem(Posicao posicao) {
		if(!tabuleiro.TemUmaPeca(posicao)) {
			throw new XadrezException("Não existe uma peça na posição de origem");
		}
		if(jogadorAtual != ((PecaXadrez)tabuleiro.Peca(posicao)).getCor()) {
			throw new XadrezException("Essa peça não é sua");
		}
		if(!tabuleiro.Peca(posicao).PossivelSeMover()) {
			throw new XadrezException("Não existe movimentos possiveis para a peça escolhida");
		}
	}
	
	private void ValidarPosicaoDestino(Posicao origem, Posicao destino) {
		if(!tabuleiro.Peca(origem).MovimentoPossivel(destino)){
			throw new XadrezException("A peça escolhida não pode se mover para a posição de destino");
		}
	}
	
	private Peca Mover(Posicao origem, Posicao destino) {
		PecaXadrez p = (PecaXadrez)tabuleiro.RemoverPeca(origem);
		p.AddMovimento();
		Peca pecaCapturada = tabuleiro.RemoverPeca(destino);
		tabuleiro.ColocarPeca(p, destino);
		if(pecaCapturada != null) {
			pecasTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		return pecaCapturada;
	}
	
	private Cor Oponente(Cor cor) {
		return (cor == cor.BRANCA) ? Cor.PRETA : cor.BRANCA;
	}
	
	private PecaXadrez CorRei(Cor cor) {
		List<Peca> lista = pecasTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : lista) {
			if(p instanceof Rei) {
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe o rei da cor " + cor + " no tabuleiro");
	}
	
	private boolean TesteCheck(Cor cor) {
		Posicao posicaoRei = CorRei(cor).getPosicaoXadrez().ConvertPosicao();
		List<Peca> oponentePecas = pecasTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == Oponente(cor)).collect(Collectors.toList());
		for(Peca p : oponentePecas) {
			boolean[][] mat = p.MovimentosPossiveis();
			if(mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean TesteCheckMate(Cor cor) {
		if(!TesteCheck(cor)) {
			return false;
		}
		List<Peca> lista = pecasTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
		for(Peca p : lista) {
			boolean[][] mat = p.MovimentosPossiveis();
			for(int i = 0; i < tabuleiro.getLinhas(); i++) {
				for(int j = 0; j < tabuleiro.getColunas(); j++) {
					if(mat[i][j]) {
						Posicao origem = ((PecaXadrez)p).getPosicaoXadrez().ConvertPosicao();
						Posicao destino = new Posicao(i, j);
						Peca pecaCapturada = Mover(origem, destino);
						boolean testCheck = TesteCheck(cor);
						DesfazerMovimento(origem, destino, pecaCapturada);
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	private void ColocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
		tabuleiro.ColocarPeca(peca, new PosicaoXadrez(coluna, linha).ConvertPosicao());
		pecasTabuleiro.add(peca);
	}

	
	private void ProxTurno() {
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	
	private void Iniciar() {
		ColocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCA));
		ColocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCA));
        ColocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCA));
        ColocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCA));
        ColocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCA));
        
        ColocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETA));
        ColocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
        ColocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETA));
		ColocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETA));
        ColocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETA));
        ColocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETA));
        ColocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETA));
        ColocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETA));
        ColocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETA));
        ColocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETA));
        ColocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETA));
        ColocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETA));
	}
}