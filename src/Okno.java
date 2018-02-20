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
	private final int ileMiejsc = 5;
	private JButton zapisz = new JButton("zapisz");
	private JButton dodaj = new JButton("dodaj");
	private JPanel wszystkieZakupy;
	
	public Okno() {
		
		
		JFrame okienko = new JFrame();
		JTabbedPane jtp = new JTabbedPane();
		
		JPanel podstawoweDane = new JPanel();
		List<JPanel> paneleDanePodstawowe = produkujPanele(BibliotekaWspolnychMetod.pobierzTytulyKolumn(Zakres.PODSTAWOWE_DANE));
		podstawoweDane.setLayout(new GridLayout(paneleDanePodstawowe.size(), 1));
		for (JPanel p: paneleDanePodstawowe){
			podstawoweDane.add(p);
		}
		
		JPanel sprzedaz = new JPanel();
		List<JPanel> sprzedaze = produkujPanele(BibliotekaWspolnychMetod.pobierzTytulyKolumn(Zakres.SPRZEDAZ));
		sprzedaz.setLayout(new GridLayout(sprzedaze.size(), 1));
		for (JPanel p: sprzedaze){
			sprzedaz.add(p);
		}
		
		wszystkieZakupy = new JPanel();
		wszystkieZakupy.setLayout(new GridLayout(1, ileMiejsc));
		for (int i=0;i<ileMiejsc;i++){
			List<JPanel> zakupy = produkujPanele(BibliotekaWspolnychMetod.pobierzTytulyKolumn(Zakres.ZAKUP));
			JPanel zakup = new JPanel();
			
			zakup.setLayout(new GridLayout(zakupy.size()/*<-!!!!*/, 1));
			for (JPanel p: zakupy){
				zakup.add(p);
			}
			wszystkieZakupy.add(zakup);
			System.out.println("-");
			//panelZbiorczyWszystkichZakupow.add(new JButton("000"));
		}
		
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
		
		
		jtp.addTab("podstawowe dane", podstawoweDane);
		jtp.addTab("sprzedaze", sprzedaz);
		jtp.addTab("zakupy", wszystkieZakupy);
		
		okienko.setLayout(new BorderLayout());
		okienko.add(jtp);
		okienko.add(zapisz,BorderLayout.SOUTH);
		okienko.setSize(600, 400);
		okienko.setLocationRelativeTo(null);
		okienko.setVisible(true);
		okienko.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
	
	public List<Zakup> zapiszDoModelu(){
		Zakup.usunWszystkie();
		ArrayList<String> dane = new ArrayList<>();
		Component[] panele = wszystkieZakupy.getComponents();
		//List<Zakup> listaZakupow = new ArrayList<>();
		for (Component c: panele){
			JPanel p1 = (JPanel) c;
			Component[] wiersze = p1.getComponents();
			List<String> zakup = new ArrayList<>();
			for (Component w: wiersze){
				JPanel p2 = (JPanel) w;
				String dana = ((JTextField) p2.getComponents()[1]).getText();
				zakup.add(dana);
			}
			//listaZakupow.add(
			new Zakup(zakup);
		}
		
		//System.out.println(dane.get(0));
		return null;
	}
}
