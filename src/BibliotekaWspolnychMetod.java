import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class BibliotekaWspolnychMetod {
	
	private final static int[] indexy = {0,9,11};
	
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
//		System.out.println(tytulyKolumn.length);
		for(int i=zakres.a;i<zakres.b;i++){
			lista.add(tytulyKolumn[i]);
		}
		sc.close();
		return lista;
	}
	
	public static String exportDoStringDanePodstawowe(List<List<String>> pozycje) {
//		int[] indexy = {0,9,12,22};
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
	}
	
	public static Zakladka importZeString(String tekst,Zakres zakres){
//		System.out.println(tekst);
		Zakladka dane = new Zakladka(zakres);
		String[] linie = tekst.split("\n");
		for (String linia: linie){
			List<String> wypelnionePola = new ArrayList<>();
//			System.out.println(linia);
//			System.out.println(linia);
			String[] dana = linia.split(";");
//			for (String s:dana) System.out.println(s+"|");
//			System.out.println(dana.length);
			
//			for (int i=0;i<dane.getZakres().a;i++) wypelnionePola.add("!");
			for (int i=dane.getZakres().a;i<dana.length;i++){//!!!
				wypelnionePola.add(dana[i]);
			}
			for (int i=dana.length;i<dane.getZakres().b;i++) wypelnionePola.add("");
			dane.add(wypelnionePola);
		}
		return dane;
	}
	
	public static String[] wczytaj(String sciezka){
		String[] zakladki = new String[3];
		Scanner sc = null;
		try {
			sc = new Scanner(new FileInputStream(sciezka),"UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.nextLine();
		String danePodstawowe = "";
		danePodstawowe += sc.nextLine()+"\n";
		danePodstawowe += sc.nextLine()+"\n";
		danePodstawowe += sc.nextLine()+"\n";
		zakladki[0] = danePodstawowe;
//		System.out.println(danePodstawowe);
		String sprzedaze = "";
//		System.out.println(danePodstawowe);
		//...
		//sc.next();
		//while (sc.hasNext()) System.out.println(sc.next());
		///..
		while (true){
//			System.out.println("-");
			String linia = sc.nextLine();
//			System.out.println(linia);
			if (linia.startsWith(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;")) break;
			sprzedaze += linia+"\n";
		}
		zakladki[1] = sprzedaze;
		sc.nextLine();
		String zakupy = "";
		while (true){
			String linia = sc.nextLine();
			if (linia.startsWith(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;")) break;
			zakupy += linia+"\n";
		}
		zakladki[2] = zakupy;
		return zakladki;
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

	public static Zakladka importujZeStringDanePodstawowe(String tekst) {
		
		Zakladka zakladka = new Zakladka(Zakres.PODSTAWOWE_DANE);
		List<String> wypelnionePola = new ArrayList<>();
		String[] linie = tekst.split("\n");
		String[] pierwszaLinia = linie[0].split(";");
		for (int i=indexy[0];i<pierwszaLinia.length;i++) wypelnionePola.add(pierwszaLinia[i]);
		String[] drugaLinia = linie[1].split(";");
		for (int i=indexy[1];i<drugaLinia.length;i++) wypelnionePola.add(drugaLinia[i]);
//		String[] trzeciaLinia = linie[2].split(";");
//		for (int i=indexy[2];i<trzeciaLinia.length;i++) wypelnionePola.add(trzeciaLinia[i]);
		zakladka.add(wypelnionePola);
		return zakladka;
	}
}
