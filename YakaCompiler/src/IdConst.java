
public class IdConst extends Ident 
{

	public IdConst(Type type, int value) 
	{
		super(type, value);
	}
	
	public void setValue(int v) {
		System.out.println("Erreur : Impossible de changer la valeur d'une constante (ligne"+Yaka.token.next.beginLine+") \n");
	}
	
}
