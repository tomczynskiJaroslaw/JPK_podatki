import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class BibliotekaWspolnychMetod {
	
	public static List<String> pobierzTytulyKolumn(Zakres zakres){
		ArrayList<String> lista = new ArrayList<>();
		Scanner sc = null;
		try {
			sc = new Scanner(new File("pierwszyWiersz"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String linia = sc.nextLine();
		String[] tytulyKolumn = linia.split(";");
		for(int i=zakres.a;i<zakres.b;i++){
			lista.add(tytulyKolumn[i]);
		}
		sc.close();
		return lista;
	}
	
	public static String exportDoString(List<Zakup> zakupy,Zakres zakres){
		String prefix = "";
		String sufix = "";
		
		//prefix+=
		
		String tekstDoZapisania = prefix;
		
		for (Zakup z: zakupy){
			for (int i=0;i<zakres.a;i++){
				tekstDoZapisania+=";";
			}
			for (String s: z.getListaDane()){
				tekstDoZapisania+=s+";";
			}
			for (int i=0;i<zakres.b-zakres.a;i++){
				tekstDoZapisania+=";";
			}
			tekstDoZapisania+="\n";//UWAGA mie wiem jaki znak konca lini :-(
		}
		tekstDoZapisania +=sufix;
		
		return tekstDoZapisania;
		
		//zapiszStringDoPliku(tekstDoZapisania, "plik.csv");
	}
	
	public static void zapiszStringDoPliku(String tekst,String nazwaPliku){
		FileWriter fw = null ;
		try {
			fw = new FileWriter(new File(nazwaPliku));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fw.write(tekst);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
