
public class Declaration {
	// Nom de la variable/constante
	private String keyName; 
	// Type de la variable
	private Type typeVar;
	// Offset de la variable
	private static int offset = -2;
	
	// Fonction en cours de déclaration
	public static Function fonctionEnCours;

	
	// Déclaration pour une constante
	public Declaration(String ident) {
		this.keyName = ident;
	}
	
	public void setType(Type type){
		this.typeVar=type;
	}
	
	// Getter pour typeVar
	public Type getType() {
		return this.typeVar;
	}
	
	// Getter pour Keyname
	public String getKeyName() {
		return this.keyName;
	}
	

	
	// Déclaration pour une variable ou une fonction
	public Declaration(Type type) {
		this.typeVar = type;
	}
	
	// Ajout dans TabIdent d'une constante
	public void setIdent(Type type, int value) {
		IdConst ident = new IdConst(type, value);
		Yaka.tabIdent.putConst(keyName,ident);
		Yaka.tabIdent.printMap();
	}
	
	// Ajout dans TabIdent d'une variable
	public void setIdent(String key){
		IdVar ident = new IdVar(typeVar, offset);
		offset-=2;
		Yaka.tabIdent.putVar(key,ident);
	}
	
	public void setParam (String key, Type type){
		Ident ident = new Ident(type, 0);
		Yaka.tabIdent.addLoco(key, ident);
	}

	
}
