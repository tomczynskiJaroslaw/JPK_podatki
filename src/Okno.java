import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Okno{
	private final int ileMiejsc = 1;
	private JButton zapisz = new JButton("zapisz");
	private JButton dodaj = new JButton("dodaj");
	private JPanel danePodstawowe;
	private JPanel wszystkieZakupy;
	private JPanel wszystkieSprzedaze;
	public Okno() {
		
		
		JFrame okienko = new JFrame();
		JTabbedPane jtp = new JTabbedPane();
		
//		JPanel podstawoweDane = new JPanel();
//		List<JPanel> paneleDanePodstawowe = produkujPanele(BibliotekaWspolnychMetod.pobierzTytulyKolumn(Zakres.PODSTAWOWE_DANE));
//		podstawoweDane.setLayout(new GridLayout(paneleDanePodstawowe.size(), 1));
//		for (JPanel p: paneleDanePodstawowe){
//			podstawoweDane.add(p);
//		}
		
		danePodstawowe = zrobZakladke(Zakres.PODSTAWOWE_DANE);
		wszystkieSprzedaze = zrobZakladke(Zakres.SPRZEDAZ);
		wszystkieZakupy = zrobZakladke(Zakres.ZAKUP);
		
//		dodaj.setLocation(100, 100);
//		dodaj.setSize(100, 100);
//		dodaj.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				zakup.remove(zakupy.get(0));
//				okienko.validate();
//			}
//		});
//		zakup.add(dodaj);
		
		
		jtp.addTab("podstawowe dane", danePodstawowe);
		jtp.addTab("sprzedaze", wszystkieSprzedaze);
		jtp.addTab("zakupy", wszystkieZakupy);
		
		okienko.setLayout(new BorderLayout());
		okienko.add(jtp);
		okienko.add(zapisz,BorderLayout.SOUTH);
		okienko.setSize(1024, 800);
		okienko.setLocationRelativeTo(null);
		okienko.setVisible(true);
		okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JPanel zrobZakladke(Zakres zakres) {
		JPanel wszystkiePozycje = new JPanel();
		wszystkiePozycje.setLayout(new GridLayout(1, ileMiejsc));
		for (int i=0;i<ileMiejsc;i++){
			List<JPanel> zakupy = produkujPanele(BibliotekaWspolnychMetod.pobierzTytulyKolumn(zakres));
			JPanel zakup = new JPanel();
			
			zakup.setLayout(new GridLayout(zakupy.size()/*<-!!!!*/, 1));
			for (JPanel p: zakupy){
				zakup.add(p);
			}
			wszystkiePozycje.add(zakup);
			System.out.println("-");
			//panelZbiorczyWszystkichZakupow.add(new JButton("000"));
		}
		return wszystkiePozycje;
	}
	
	public List<JPanel> produkujPanele(List<String> lista){
		ArrayList<JPanel> panele = new ArrayList<>();
		
		for (String s : lista) {
			JPanel panel = new JPanel();
			JLabel tytul = new JLabel(s);
			JTextField wprowadzDane = new JTextField();
			panel.setLayout(new GridLayout(1, 2));
			panel.add(tytul);
			panel.add(wprowadzDane);
			panele.add(panel);
		}
		return panele;
	}
	
	public void addZapiszListener(ActionListener ac){
		zapisz.addActionListener(ac);
	}
	
	public void addDodajListener(ActionListener ac){
		dodaj.addActionListener(ac);
	}
	
	public List<List<String>> getWpisaneDane(JPanel panel){
		//Zakup.usunWszystkie();
		ArrayList<List<String>> danePozycje = new ArrayList<>();
		Component[] panele = panel.getComponents();
		//List<Zakup> listaZakupow = new ArrayList<>();
		for (Component c: panele){
//			ArrayList<String> danePola = new ArrayList<>();
			JPanel p1 = (JPanel) c;
			Component[] wiersze = p1.getComponents();
			List<String> uzupelnionePola = new ArrayList<>();
			for (Component w: wiersze){
				JPanel p2 = (JPanel) w;
				String dana = ((JTextField) p2.getComponents()[1]).getText();
				uzupelnionePola.add(dana);
			}
			//listaZakupow.add(
			//new Zakup(zakup);
			danePozycje.add(uzupelnionePola);
		}
		
		//System.out.println(dane.get(0));
		return danePozycje;
	}
	
	public List<List<String>> getZakupy(){
		return getWpisaneDane(wszystkieZakupy);
	}
	
	public List<List<String>> getSprzedaze(){
		return getWpisaneDane(wszystkieSprzedaze);
	}
	
	public List<List<String>> getDodstawoweDane(){
		return getWpisaneDane(danePodstawowe);
	}
}
