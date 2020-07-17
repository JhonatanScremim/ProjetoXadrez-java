package Jogo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import Xadrez.PartidaXadrez;
import Xadrez.PecaXadrez;
import Xadrez.PosicaoXadrez;
import Xadrez.XadrezException;

public class Main {
	public static void main(String[] args) {
		PartidaXadrez partida = new PartidaXadrez();
		List<PecaXadrez> listaCapturadas = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		while(!partida.getCheckMate()) {
			try {
				InterfaceUsuario.LimparTela();
				InterfaceUsuario.PrintPartida(partida, listaCapturadas);
				System.out.println();
				System.out.print("Origem: ");
				PosicaoXadrez origem = InterfaceUsuario.LerPosicaoXadrez(sc);
				
				boolean[][] movimentosPossiveis = partida.MovimentosPossiveisTabuleiro(origem);
				InterfaceUsuario.LimparTela();
				InterfaceUsuario.PrintMovimentosPossiveis(partida.getPecas(), movimentosPossiveis);
				
				
				System.out.println();
				System.out.print("Destino: ");
				PosicaoXadrez destino = InterfaceUsuario.LerPosicaoXadrez(sc);
				
				PecaXadrez pecaCapturada = partida.ExecutarMovimento(origem, destino);
				if(pecaCapturada != null) {
					listaCapturadas.add(pecaCapturada);
				}
			}
			catch(XadrezException e) {
				System.out.println(e.getMessage());
				sc.hasNextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.hasNextLine();
			}
		}
		InterfaceUsuario.LimparTela();
		InterfaceUsuario.PrintPartida(partida, listaCapturadas);
		
	}
	
}
