import java.awt.List;
import java.util.HashMap;


public class Function extends Ident
{
	private HashMap<String,Type> parameters;
	
	public Function(Type t){
		super(t,0);
		parameters = new HashMap<String, Type>();
	}

	

	public void addParam (String nom, Type t){
		parameters.put(nom, t);
	}
	
	

}
