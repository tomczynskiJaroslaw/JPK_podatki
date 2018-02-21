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
		System.out.println(tytulyKolumn.length);
		for(int i=zakres.a;i<zakres.b;i++){
			lista.add(tytulyKolumn[i]);
		}
		sc.close();
		return lista;
	}
	
	public static String exportDoStringDanePodstawowe(List<List<String>> pozycje) {
		int[] indexy = {0,9,12,22};
		String tekst = "";
		List<String> dane = pozycje.get(0);
		
		for (int i=0;i<indexy.length-1;i++){
			int n=0;
			for (int j=0;j<indexy[i];j++) tekst+=";";
			for (int j=indexy[i];j<indexy[i+1];j++) tekst+=dane.get(j)+";";
			for (int j=0;j<Zakres._WSZYSTKIE_.b-indexy[i+1]-1;j++) tekst+=";";
			tekst+="\n";
		}
		return tekst;
	}
	
	public static String exportDoString(List<List<String>> pozycje,Zakres zakres){
		String prefix = "";
		String sufix = "";
		
		//prefix+=
		
		String tekstDoZapisania = prefix;
		
		for (List<String> p: pozycje){
			for (int i=0;i<zakres.a;i++){
				tekstDoZapisania+=";";
			}
			for (String uzupelnionePole: p){
				tekstDoZapisania+=uzupelnionePole+";";
			}
			for (int i=0;i<Zakres._WSZYSTKIE_.b-zakres.b;i++){
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
