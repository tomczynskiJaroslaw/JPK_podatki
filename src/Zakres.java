public enum Zakres{
	PODSTAWOWE_DANE(0,11),
	SPRZEDAZ(12,48),
	ZAKUP(52,66);
	
	int a;
	int b;
	
	private Zakres(int a,int b) {
		this.a=a;
		this.b=b;
	}
	
//	public int getStart(){
//		return a;
//	}
//	
//	public int getKoniec(){
//		return b;
//	}
}