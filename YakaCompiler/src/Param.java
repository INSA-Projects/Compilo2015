public class Param extends Ident {
	protected String nom;
	
	
	public Param(String identLu, Type typeDuParametre) 
	{
		super(typeDuParametre, 0);
		Yaka.tabIdent.addLoco(identLu, this);
	}
	
	public void setOffset(int offset)
	{
		super.value = offset;
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