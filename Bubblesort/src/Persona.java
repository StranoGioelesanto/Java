
public class Persona extends Comparable2 implements Comparable {

	int eta;
	String nome;

	public int getEta() {
		return eta;
	}

	public void setEta(int eta) {
		this.eta = eta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int compare(Persona p) {

		if (this.eta < p.eta) {
			return -1;
		} else if (this.eta > p.eta) {
			return 1;
		}

		return 0;
	}

	@Override
	public int compare(Object o) {

		return compare((Persona) o);
	}

	@Override
	public int compare(Object a, Object b) {
		return ((Persona) a).compare((Persona) b);
		
	}
	
	
	
	

}
