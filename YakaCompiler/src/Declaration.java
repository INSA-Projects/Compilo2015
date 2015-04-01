
public class Declaration {
	// Nom de la variable/constante
	private String keyName; 
	// Type de la variable
	private Type typeVar;
	// Offset de la variable
	private static int offset = -2;
	
	// Déclaration pour une constante
	public Declaration(String ident) {
		this.keyName = ident;
	}
	
	// Getter pour typeVar
	public Type getType() {
		return this.typeVar;
	}
	
	// Déclaration pour une variable ou une fonction
	public Declaration(Type type) {
		this.typeVar = type;
	}
	
	// Ajout dans TabIdent d'une constante
	public void setIdent(Type type, int value) {
		IdConst ident = new IdConst(type, value);
		Yaka.tabIdent.putIdent(keyName,ident);
	}
	
	// Ajout dans TabIdent d'une variable
	public void setIdent(String key){
		IdVar ident = new IdVar(typeVar, offset);
		offset-=2;
		Yaka.tabIdent.putIdent(key,ident);
	}
	

	public void setParam (String key, Type type){
		Ident ident = new Ident(type, 0);
		Yaka.tabIdent.addLoco(key, ident);

	// Ajout dans TabIdent d'une fonction
	public void setFunction(String key) {
		Function function = new Function(typeVar);
		Yaka.tabIdent.putIdent(key,function);

	}
	
}
