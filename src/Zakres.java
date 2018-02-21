public enum Zakres{
	PODSTAWOWE_DANE(0,22,new int[] {-1}),
	SPRZEDAZ(23,60,new int[] {41}),
	ZAKUP(63,78,new int[] {74}),
	
	_WSZYSTKIE_(0,81,new int[] {-1});
	
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