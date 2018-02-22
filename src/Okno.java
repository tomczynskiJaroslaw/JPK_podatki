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
	private JButton importuj = new JButton("importuj");
	private JButton zapisz = new JButton("zapisz");
	private JButton dodaj = new JButton("dodaj");
	private JButton usun = new JButton("usun");
	private Grupa danePodstawowe;
	private Grupa wszystkieZakupy;
	private Grupa wszystkieSprzedaze;
	public Okno() {
		
		JFrame okienko = new JFrame();
		JTabbedPane jtp = new JTabbedPane();
		
		danePodstawowe = zrobZakladke(Zakres.PODSTAWOWE_DANE);
		wszystkieSprzedaze = zrobZakladke(Zakres.SPRZEDAZ);
		wszystkieZakupy = zrobZakladke(Zakres.ZAKUP);
		
		jtp.addTab("podstawowe dane", danePodstawowe);
		jtp.addTab("sprzedaze", wszystkieSprzedaze);
		jtp.addTab("zakupy", wszystkieZakupy);
		
		okienko.setLayout(new BorderLayout());
		okienko.add(jtp);
		okienko.add(importuj,BorderLayout.NORTH);
		okienko.add(zapisz,BorderLayout.SOUTH);
		okienko.add(dodaj,BorderLayout.EAST);
		okienko.add(usun,BorderLayout.WEST);
		okienko.setSize(1024, 800);
		okienko.setLocationRelativeTo(null);
		okienko.setVisible(true);
		okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Grupa panel = (Grupa) jtp.getSelectedComponent();
				System.out.println(panel);
				panel = dodajPozycje(panel);
				okienko.validate();
			}
		});
		
		usun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Grupa panel = (Grupa) jtp.getSelectedComponent();
				panel = usunPozycje(panel);
				okienko.validate();
			}
		});
	}

	private Grupa zrobZakladke(Zakres zakres) {
		Grupa wszystkiePozycje = new Grupa(zakres);
		wszystkiePozycje.setLayout(new GridLayout(1, ileMiejsc));
		for (int i=0;i<ileMiejsc;i++){
			List<JPanel> zakupy = produkujPanele(BibliotekaWspolnychMetod.pobierzTytulyKolumn(zakres));
			JPanel pozycja = new JPanel();
			
			pozycja.setLayout(new GridLayout(zakupy.size()/*<-!!!!*/, 1));
			for (JPanel p: zakupy){
				pozycja.add(p);
			}
			wszystkiePozycje.add(pozycja);
		}
		return wszystkiePozycje;
	}
	
	private Grupa dodajPozycje(Grupa panel){
		Grupa wszystkiePozycje = panel;
		int ilePozycji = ((GridLayout) wszystkiePozycje.getLayout()).getColumns();
		wszystkiePozycje.setLayout(new GridLayout(1, ilePozycji+1));
		List<JPanel> pola = produkujPanele(BibliotekaWspolnychMetod.pobierzTytulyKolumn(panel.getZakres()));
		JPanel pozycja = new JPanel();
		pozycja.setLayout(new GridLayout(pola.size()/*<-!!!!*/, 1));
		for (JPanel p: pola){
			pozycja.add(p);
		}
		wszystkiePozycje.add(pozycja);
		return wszystkiePozycje;
	}
	
	private Grupa usunPozycje(Grupa panel){
		Grupa wszystkiePozycje = panel;
		int ilePozycji = ((GridLayout) wszystkiePozycje.getLayout()).getColumns();
		wszystkiePozycje.setLayout(new GridLayout(1, ilePozycji-1));
		List<JPanel> pola = produkujPanele(BibliotekaWspolnychMetod.pobierzTytulyKolumn(panel.getZakres()));
		wszystkiePozycje.remove(wszystkiePozycje.getComponentCount()-1);
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
	
	public void addImportujListener(ActionListener ac){
		importuj.addActionListener(ac);
	}
	
	public List<List<String>> getWpisaneDane(JPanel panel){
		ArrayList<List<String>> danePozycje = new ArrayList<>();
		Component[] panele = panel.getComponents();
		for (Component c: panele){
			JPanel p1 = (JPanel) c;
			Component[] wiersze = p1.getComponents();
			List<String> uzupelnionePola = new ArrayList<>();
			for (Component w: wiersze){
				JPanel p2 = (JPanel) w;
				String dana = ((JTextField) p2.getComponents()[1]).getText();
				uzupelnionePola.add(dana);
			}
			danePozycje.add(uzupelnionePola);
		}
		
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
	
	public void setZakupy(List<List<String>> pozycje){
		wszystkieZakupy = wprowadzZaimportowaneDane(pozycje,Zakres.ZAKUP);
	}
	
	private class Grupa extends JPanel{
		private Zakres zakres;
		public Grupa(Zakres zakres) {
			this.zakres=zakres;
		}
		public Zakres getZakres(){
			return zakres;
		}
	}
	
	public Grupa wprowadzZaimportowaneDane(List<List<String>> pozycje, Zakres zakres) {
		ArrayList<List<String>> danePozycje = new ArrayList<>();
		Component[] panele = panel.getComponents();
		for (Component c: panele){
			JPanel p1 = (JPanel) c;
			Component[] wiersze = p1.getComponents();
			List<String> uzupelnionePola = new ArrayList<>();
			for (Component w: wiersze){
				JPanel p2 = (JPanel) w;
				String dana = ((JTextField) p2.getComponents()[1]).getText();
				uzupelnionePola.add(dana);
			}
			danePozycje.add(uzupelnionePola);
		}
		
		return danePozycje;
	}
}
