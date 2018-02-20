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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("nacisnieto: "+e.getActionCommand()+" "+e);
		JButton b = (JButton) e.getSource();
		String nazwa = b.getText();
		if (nazwa.equals("zapisz")){
			System.out.println("zapisz");
			//...
			zapiszDoModelu();
			String dane_podstawowe = BibliotekaWspolnychMetod.exportDoString(Zakup.getListaZakupow(),Zakres.PODSTAWOWE_DANE);
			String sprzedaze = BibliotekaWspolnychMetod.exportDoString(Zakup.getListaZakupow(),Zakres.SPRZEDAZ);
			String zakupy = BibliotekaWspolnychMetod.exportDoString(Zakup.getListaZakupow(),Zakres.ZAKUP);
			BibliotekaWspolnychMetod.zapiszStringDoPliku("tutaj dopisac!!! \n"+dane_podstawowe+sprzedaze+zakupy+" \n i tu chyba tez", "plik.csv");
		}
		
	}
	
	public void zapiszDoModelu(){
		okno.zapiszDoModelu();
		
	}
}
