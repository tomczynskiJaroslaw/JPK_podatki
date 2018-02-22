import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

public class Kontrola implements ActionListener{
	private Okno okno;
	private Model model;
	
	public Kontrola(Okno okno,Model model) {
		this.okno=okno;
		this.model=model;
		this.okno.addZapiszListener(this);
		this.okno.addDodajListener(this);
		this.okno.addImportujListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("nacisnieto: "+e.getActionCommand()+" "+e);
		JButton b = (JButton) e.getSource();
		String nazwa = b.getText();
		if (nazwa.equals("zapisz")){
			System.out.println("zapisz");

			String dane_podstawowe = BibliotekaWspolnychMetod.exportDoStringDanePodstawowe(okno.getDodstawoweDane());
			String sprzedaze = BibliotekaWspolnychMetod.exportDoString(okno.getSprzedaze(),Zakres.SPRZEDAZ);
			String zakupy = BibliotekaWspolnychMetod.exportDoString(okno.getZakupy(),Zakres.ZAKUP);
			
			String podliczenieSprzedazy = podliczenieSprzedazy(okno.getSprzedaze(),Zakres.SPRZEDAZ);
			String linia = "\n;...\n";
			String podliczenieZakupow = podliczenieSprzedazy(okno.getZakupy(),Zakres.ZAKUP);
			
			BibliotekaWspolnychMetod.zapiszStringDoPliku(
					tytulyKolumnZamienNaPierwszaLinie(BibliotekaWspolnychMetod.pobierzTytulyKolumn(Zakres._WSZYSTKIE_))
					+"\n"+dane_podstawowe+sprzedaze
					+podliczenieSprzedazy
					+linia
					+zakupy
					+podliczenieZakupow
					, "plik.csv");
		}
		if (nazwa.equals("importuj")){
			
		}
		
	}
	
	private String tytulyKolumnZamienNaPierwszaLinie(List<String> lista){
		String tekst = "";
		for (String s: lista){
			tekst+=s+";";
		}
		return tekst;
	}
	
	private String podliczenieSprzedazy(List<List<String>> sprzedaze, Zakres zakres) {
		String tekst = "";
		for (int i=0;i<zakres.b;i++) tekst+=";";
		tekst+=sprzedaze.size();
		double suma = 0;
		for (List<String> a : sprzedaze){
			for (int i : zakres.indexyKolumnZPodatkiem){
				String kwota = a.get(i-zakres.a);
				if(!kwota.equals("")) suma += Double.parseDouble(kwota); 
			}
		}
		tekst+=";";
		tekst+=suma;//UWAGA ./,
		for (int i=0;i<Zakres._WSZYSTKIE_.b-zakres.b;i++) tekst+=";";
		tekst.replaceAll(".", ",");//!!!
		return tekst;
	}
}
