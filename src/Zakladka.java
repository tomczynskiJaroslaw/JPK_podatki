import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Zakladka implements List<List<String>>{

	private List lista = new ArrayList<>();
	private Zakres zakres;
	
	public Zakladka(Zakres zakres) {
		this.zakres = zakres;
	}
	
	public Zakres getZakres(){
		return zakres;
	}
	
//	public void dodajPozycje(List<String> pozycja){
//		lista.add(pozycja);
//	}
	
	@Override
	public int size() {
		return lista.size();
	}

	@Override
	public boolean isEmpty() {
		return lista.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return lista.contains(o);
	}

	@Override
	public Iterator iterator() {
		return lista.iterator();
	}

	@Override
	public Object[] toArray() {
		return lista.toArray();
	}

	@Override
	public Object[] toArray(Object[] a) {
		return lista.toArray(a);
	}

	@Override
	public boolean add(List<String> e) {
		return lista.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return lista.remove(o);
	}

	@Override
	public boolean containsAll(Collection c) {
		return lista.containsAll(c);
	}

	@Override
	public boolean addAll(Collection c) {
		return lista.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection c) {
		return lista.addAll(index,c);
	}

	@Override
	public boolean removeAll(Collection c) {
		return lista.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection c) {
		return lista.retainAll(c);
	}

	@Override
	public void clear() {
		lista.clear();
	}

	@Override
	public List<String> get(int index) {
		return (List<String>) lista.get(index);
	}

	@Override
	public List<String> set(int index, List<String> element) {
		return (List<String>) lista.set(index, element);
	}


	@Override
	public List<String> remove(int index) {
		return (List<String>) lista.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return lista.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return lista.lastIndexOf(o);
	}

	@Override
	public ListIterator listIterator() {
		return lista.listIterator();
	}

	@Override
	public ListIterator listIterator(int index) {
		return lista.listIterator(index);
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		return lista.subList(fromIndex, toIndex);
	}

	@Override
	public void add(int index, List<String> element) {
		lista.add(index, element);
	}
	
	@Override
	public String toString() {
		return zakres+" "+lista.toString();
	}

}
