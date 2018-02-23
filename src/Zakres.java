public enum Zakres{
	PODSTAWOWE_DANE(0,12,new int[] {-1}),
	SPRZEDAZ(12,49,new int[] {29}),
	ZAKUP(52,68,new int[] {61}),
	
	_WSZYSTKIE_(0,68,new int[] {-1});
	
	int a;
	int b;
	int[] indexyKolumnZPodatkiem;
	
	private Zakres(int a,int b,int[] tab) {
		this.a=a;
		this.b=b;
		indexyKolumnZPodatkiem=tab;
	}
	
//	public int getStart(){
//		return a;
//	}
//	
//	public int getKoniec(){
//		return b;
//	}
}