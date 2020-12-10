import java.math.*;
import java.io.*;


public class Combate{
	private Pokemon player1;
	private Pokemon player2;
	private int indexRival;
	BufferedReader buffer;

	Combate(Pokemon player1, Pokedex pokedexGral){
		buffer = new BufferedReader(new InputStreamReader(System.in));
		this.player1 = player1;
		indexRival = (int)Math.round(Math.random()*9);
		this.player2 = pokedexGral.getPokemon(indexRival);
	}

	public void ataqueCpu(Pokemon player1,Pokemon player2){
			int op = (int)Math.round(Math.random()*2);
		
			System.out.println("El ataque de "+player1.getNombre()+" rival será: "+player1.getAtaques()[op].getNombre());


			player2.recibirAtaque(player1.getAtaques()[op]);
			System.out.println("\n\n********************************************************\n");
			System.out.println(player2.getNombre()+" tu nivel de Puntos de Salud es "+player2.getPs());
			System.out.println("\n********************************************************\n\n");
		
	}

	public void ataque(Pokemon player1,Pokemon player2){
		int op;
		try{
			System.out.println(player1.getNombre()+", El ataque será: ");
			System.out.println("1.-"+player1.getAtaques()[0].getNombre());
			System.out.println("2.-"+player1.getAtaques()[1].getNombre());
			System.out.println("3.-"+player1.getAtaques()[2].getNombre());
			op = Integer.parseInt(buffer.readLine());

			player2.recibirAtaque(player1.getAtaques()[op-1]);
			System.out.println("\n\n********************************************************\n");
			System.out.println("El nivel de Puntos de Salud de "+player2.getNombre()+" es "+player2.getPs());
			System.out.println("\n********************************************************\n\n");
		}catch(IOException e){
			System.out.println("Error de lectura desde el teclado...");
		}
	}

	public int combatir(){
		int out=0;
		boolean sigue=true;
		while(sigue){
			ataque(player1,player2);
			if(revisarTriunfo(player2)){
				System.out.println("\n\n********************************************************\n\n");
				System.out.println("Felicitaciones...Haz ganado la partida!!");
				System.out.println("\n\n********************************************************\n\n");
				out = indexRival;
				player1.restaurar();
				player2.restaurar();
				player1.ganarExp();
				break;
			}
			ataqueCpu(player2,player1);
			if(revisarTriunfo(player1)){
				System.out.println("\n\n********************************************************\n\n");
				System.out.println("Haz perdido la partida!!");
				System.out.println("\n\n********************************************************\n\n");
				out = -1;
				player1.restaurar();
				player2.restaurar();
				break;
			}
		}
		return out;
	}

	public boolean revisarTriunfo(Pokemon player){
		if(player.getPs()<=0) return true;
		else return false;
	}

}