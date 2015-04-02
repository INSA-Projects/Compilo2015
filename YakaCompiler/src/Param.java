public class Param extends Ident {
	protected String nom;
	
	public Param(Type type, int offset, String name) {
		super(type,offset);
		this.nom = name;
	}
	
	public String toString() {
		return "Param nom="+nom+" type="+type+" [offset=" + value + "]";
	}

	public int getValeur() {
		return value;
	}

	public void generateIdent() {
		Yaka.yvm.iload(this.value);
	}
}