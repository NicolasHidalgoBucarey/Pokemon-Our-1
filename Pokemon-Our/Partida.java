import java.io.*;
public class Partida{
	private BufferedReader buffer;
	private Personaje jugador;
	private Combate [] combates;
	private int combateActual,caso;
	private int W;
	private int Posicion;
	private Pokedex pokedexGral;
	private Mapa mapa;
	private String lugar;


	Partida(Pokedex pokedexGral){
		buffer = new BufferedReader(new InputStreamReader(System.in));
		combates = new Combate[SetupPokemon.cantidadCombates];
		combateActual = 0;
		this.pokedexGral = pokedexGral;
		this.mapa=new Mapa();
		crearUsuario(pokedexGral);
		menu();
	}
	
	private void crearUsuario(Pokedex pokedexGral){
		String nombre;
		String usuario;
		try{
			System.out.println("\n\n********************************************************\n");
			System.out.println("    POKEMON FUNDAMENTOS DE LENGUAJES DE PROGRAMACIÓN");
			System.out.println("\n********************************************************\n\n");
			System.out.println("            Ingrese su nombre: \n            ");
			nombre = buffer.readLine();
			System.out.println("            Ingrese un nombre de usuario: \n            ");
			usuario = buffer.readLine();

			this.jugador = new Personaje(1,nombre,usuario,pokedexGral);	
		}catch(IOException e){
			System.out.println("Error de lectura desde el teclado...");
		}
	}
	public void menu(){
		int op=0;
		System.out.println("\n\n********************************************************\n\n");
		System.out.println("Comienza el juego...");
		System.out.println("\n\n********************************************************\n\n");
		try{
			do{
			
			System.out.println("\n\n********************************************************\n\n");
			System.out.println("Selecciona la acción que quieres realizar: ");
			System.out.println("1.- Combate. ");
			System.out.println("2.- Ver Pokedex. ");
			System.out.println("3.- Mapa. ");
			System.out.println("4.- Terminar Juego. ");
			op = Integer.parseInt(buffer.readLine());
			System.out.println("\n\n********************************************************\n\n");
			switch(op){
				case 1: System.out.println("\n\n********************************************************\n\n");
						crearCombate();
						System.out.println("\n\n********************************************************\n\n");
						break;
				case 2: System.out.println("\n\n********************************************************\n\n");
						mostrarPokedex();
						buffer.readLine();
						System.out.println("\n\n********************************************************\n\n");
						break;
				case 3:	System.out.println("\n\n********************************************************\n\n");
						this.Posicion=mapa.getPosicion();
						opcionesViajar();
						buffer.readLine();
						System.out.println("\n\n********************************************************\n\n");
						break;

				case 4: System.out.println("\n\n********************************************************\n\n");
						System.out.println("El juego ha terminado,  gracias por jugar PokemonFLP!!!");
						System.out.println("\n\n********************************************************\n\n\n\n\n\n\n\n\n\n");
						break;
			}

			}while(op!=4);
			//System.out.println("1.- Combate. ");

		}catch(IOException e){
			System.out.println("Error de lectura desde el teclado...");
		}

	}
	private void crearCombate(){
		this.W=0;
		int n=SetupPokemon.cantidadPokemones;
		Pokemon aux;
		System.out.println("\n\n********************************************************\n\n");
		System.out.println("Pokedex Personal");
		System.out.println("======= ========\n");
		for(int i=0;i<n;i++){
			aux = jugador.getPokedex().getPokemon(i);
			if(aux.getCapturado())
				System.out.println((i+1)+".- "+aux.getNombre());
		}
		
		int op=-1;
		boolean flag=false;
		do{
			if(flag) System.out.println("Ingrese una opción válida...");
			System.out.println("Elije tu pokemon para la batalla....");
			try{
				op = Integer.parseInt(buffer.readLine());
				flag=true;
			}catch(IOException e){
				System.out.println("Error de lectura desde el teclado...");
			}
		}while((op<0)||(op>n));
		System.out.println("\n\n********************************************************\n\n");
		System.out.println("Su pokemon para el combate es "+ jugador.getPokedex().getPokemon(op-1).getNombre());
		System.out.println("\n\n********************************************************\n\n");
		combates[combateActual] = new Combate(jugador.getPokedex().getPokemon(op-1), pokedexGral);
		int rival = combates[combateActual].combatir();
		if (rival > -1){
			jugador.getPokedex().capturarPokemon(rival);
			System.out.println("Haz capturado un nuevo pokemon!!!");
			this.W=1;
		}
		combateActual+=1;
	}

	public void mostrarPokedex(){
		this.jugador.listarPokedexPersonal();
	}
	public void opcionesViajar(){
		caso=0;
		try{
			do{
				
			System.out.println("\n\n********************************************************\n\n");
			System.out.println("Selecciona la acción que quieres realizar: ");
			System.out.println("1.- Ciudad Actual. ");
			System.out.println("2.- Ciudades Cercanas. ");
			System.out.println("3.- Viajar ");
			System.out.println("4.- Salir del mapa. ");
			caso = Integer.parseInt(buffer.readLine());
			System.out.println("\n\n********************************************************\n\n");
			switch(caso){
				case 1: System.out.println("*********** CIUDAD ACTUAL ***********");
						System.out.println(""+this.mapa.getLugar(this.Posicion));
						System.out.println("\n\n********************************************************\n\n");
						
						break;
				case 2: System.out.println("*********** CIUDADES CERCANAS ***********");
						if (mapa.getViajar()[this.Posicion][1]=="no"){
							System.out.println("1.-"+mapa.getViajar()[this.Posicion][0]);
						}
						else{
							System.out.println("1.-"+mapa.getViajar()[this.Posicion][0]);
							System.out.println("2.-"+mapa.getViajar()[this.Posicion][1]);
						}
						System.out.println("\n\n********************************************************\n\n");
						break;
				case 3:	System.out.println("*********** VIAJAR A ***********");
						if (this.Posicion==0 || this.Posicion==4){
							System.out.println("1.-"+mapa.getViajar()[this.Posicion][0]);
							caso = Integer.parseInt(buffer.readLine());
							if(this.Posicion ==0 && caso == 1){
								this.mapa.setPosicion(Posicion+1);
								this.Posicion=mapa.getPosicion();
								
								encuentros();
								System.out.println(" *********** Llegamos a "+this.mapa.getLugar(this.Posicion)+" ***********");
								System.out.println("\n\n********************************************************\n\n");
								break;
							}
							else if(Posicion == 4 && caso==1){
								System.out.println("1.-"+mapa.getViajar()[this.Posicion][0]);
								this.mapa.setPosicion(Posicion-1);
								this.Posicion=mapa.getPosicion();
								encuentros();
								System.out.println(" *********** Llegamos a "+this.mapa.getLugar(this.Posicion)+" ***********");
								System.out.println("\n\n********************************************************\n\n");
								break;
							}
							
							else{
								System.out.println("\n\n********************************************************\n\n");
								break;
							}
						}
						else if(this.Posicion>=1 && this.Posicion<=4){
							System.out.println("1.-"+mapa.getViajar()[this.Posicion][0]);
							System.out.println("2.-"+mapa.getViajar()[this.Posicion][1]);
							caso = Integer.parseInt(buffer.readLine());
							if(caso == 1){
								this.mapa.setPosicion(Posicion-1);
								this.Posicion=mapa.getPosicion();
								encuentros();
								System.out.println(" *********** Llegamos a "+this.mapa.getLugar(this.Posicion)+" ***********");
								System.out.println("\n\n********************************************************\n\n");
								break;
							}
							else if(caso == 2){
								this.mapa.setPosicion(Posicion+1);
								this.Posicion=mapa.getPosicion();
								encuentros();
								System.out.println(" *********** Llegamos a "+this.mapa.getLugar(this.Posicion)+" ***********");
								System.out.println("\n\n********************************************************\n\n");
								break;
							}
							else{
								System.out.println("\n\n********************************************************\n\n");
								break;
							}
						}

				case 4: System.out.println("Ha salido del mapa");
						System.out.println("\n\n*********** PRESIONE ENTER PARA CONTINUAR **********\n\n\n\n\n\n\n\n\n\n");
						break;
				}


				}while(caso!=4);
			}catch(IOException e){
				System.out.println("Error de lectura desde el teclado...");
		}
	}
	public void encuentros(){
		for(int i=1;i<=3;i++){
			System.out.println("\n *********** Viajando a "+this.mapa.getLugar(this.Posicion)+" ***********\n");
			System.out.println("\nCombate "+i+"/3\n");
			if(i==1){
				System.out.println("\nFalta Bastante para llegar\n");
			}
			else if(i==2){
				System.out.println("\nA medio camino\n");
			}
			else{
				System.out.println("\nYa casi llegamos\n");
			}
			crearCombate();
			if(this.W==0){
				i-=1;
			}
		
	}
	
	}
}

