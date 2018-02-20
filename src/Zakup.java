import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Zakup {
	private List<String> dane = new ArrayList<>();
	private static List<Zakup> listaZakupow = new ArrayList<>();
	public Zakup(List<String> dane) {
		this.dane=new ArrayList<>(dane);
		listaZakupow.add(this);
		System.out.println(dane);
	}
	
	public static List<Zakup> getListaZakupow(){
		return new ArrayList<>(listaZakupow);
	}
	
	public List<String> getListaDane(){
		return new ArrayList<>(dane);
	}

	public static void usunWszystkie() {
		listaZakupow.clear();
	}
}
