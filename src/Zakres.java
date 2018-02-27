public enum Zakres{
	PODSTAWOWE_DANE(0,12,new int[] {-1},""),
	SPRZEDAZ(12,49,new int[] {29},"Sprzedaz"),
	ZAKUP(51,66,new int[] {61},"Zakup"),
	
	_WSZYSTKIE_(0,68,new int[] {-1},"");
	
	int a;
	int b;
	int[] indexyKolumnZPodatkiem;
	private String tagXML;
	
	private Zakres(int a,int b,int[] tab,String tagXML) {
		this.a=a;
		this.b=b;
		indexyKolumnZPodatkiem=tab;
		this.tagXML=tagXML;
	}
	
	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int[] getIndexyKolumnZPodatkiem() {
		return indexyKolumnZPodatkiem;
	}

	public String getTagXML() {
		return tagXML;
	}
}