import java.awt.List;
import java.util.HashMap;


public class Function extends Ident
{
	private HashMap<String,Ident> parameters;
	
	public Function(Type t){
		super(t,0);
		parameters = new HashMap<String, Ident>();
	}

	public void addParam (String nom, Ident t){
		parameters.put(nom, t);
	}
	
	

}

