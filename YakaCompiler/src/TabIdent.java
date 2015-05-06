import java.util.HashMap;

public class TabIdent 
{
	private HashMap<String,Ident> globaux;
	private HashMap<String ,Ident> locaux;
	public int cptParam = 0;
	public int cptVar = 0;
	
	

	public void printMap() {
		System.out.println("globaux : "+this.globaux.toString()+"\nlocaux"+this.locaux.toString());
	}
	
	/**
	 * Constructor
	 * @param size
	 */
	public TabIdent(){
		this.globaux = new HashMap<String, Ident>();
		this.locaux = new HashMap<String, Ident>();
	}
	
	
	/**
	 * Find the ident : It could be in globaux or locaux
	 * @param key
	 * @return
	 */
	public Ident findIdent (String key)
	{
		if (identExist(key)) {
			if (globaux.containsKey(key)) {
				return globaux.get(key);
			}
			else {
				return locaux.get(key);
			}
		}
		else {
			System.out.println("Ident : "+key+" Erreur cet Ident n'existe pas dans la table des identificateurs ligne "+SimpleCharStream.getEndLine()+"\n");
			return null;
		}

	}
	
	/**
	 * Return true if key exist
	 * @param key
	 * @return
	 */
	public boolean identExist (String key)
	{
		return globaux.containsKey(key) || locaux.containsKey(key);
	}
	
	/**
	 * Put ident
	 * @param key
	 * @param id
	 */
	public void putFonction(String key, Function id)
	{
		if (identExist(key)) {
			System.out.println("Ident : "+key+" Erreur cet Ident existe déjà dans la table des identificateurs ligne"+SimpleCharStream.getEndLine()+"\n");
			return;
		}
		globaux.put(key, id);
	}
	
	/**
	 * Put ident
	 * @param key
	 * @param id
	 */
	public void putVar (String key, Ident id)
	{
		if (identExist(key)) {
			System.out.println("Ident : "+key+" Erreur cet Ident existe déjà dans la table des identificateurs ligne"+SimpleCharStream.getEndLine()+"\n");
			return;
		}
		this.addLoco(key, id);
		this.cptVar++;
		int offset = this.calculateOffsetVar();
		id.setValue(offset);
	}
	
	public void putConst (String key, Ident id)
	{
		if (identExist(key)) {
			System.out.println("Ident : "+key+" Erreur cet Ident existe déjà dans la table des identificateurs ligne"+SimpleCharStream.getEndLine()+"\n");
			return;
		}
		this.addLoco(key, id);
	}
	
	
	public int calculateOffsetVar(){
		return  this.cptVar * (-2);
	}
	
	public int calculateOffsetParam(Param p){
		Function identFonction = (Function)this.findIdent(Declaration.getNomFonctionEnCours());
		return  2 * identFonction.tailleParams() + 4- (this.cptParam *2) ;
	}
	
	/**
	 * Get the type of the ident 
	 * @param key
	 * @return Type
	 */
	public Type getType (String key)
	{
		if (this.identExist(key)){
			Ident ident = this.locaux.get(key);
			return ident.getType();
		}
		else {
			return Type.ERREUR;
		}
	}
	
	/**
	 * Get the value of the ident
	 * @param key
	 * @return int
	 */
	public int getValue (String key) {
		if (identExist(key)) {
			return findIdent(key).getValue();
		}
		else {
			System.out.println("Ident : "+key+" Erreur cet Ident n'existe pas dans la table des identificateurs ligne "+SimpleCharStream.getEndLine()+"\n");
			return -1;
		}	
	}
	
	public void clearLoco(){
		this.cptParam=0;
		this.locaux.clear();
	}
	
	public void addLoco(String s,Ident i){
		locaux.put(s, i);
	}
	
	public int calculateOffsets()
	{
		int rang=1;
		int size = locaux.size();
		for (String mapKey : locaux.keySet()) {
			Ident ident = locaux.remove(mapKey);
			ident.setValue(size + 4 - (rang * 2));
			locaux.put(mapKey,ident);
			rang ++;
			// utilise ici hashMap.get(mapKey) pour accéder aux valeurs
		}
		return 0;
	}
	
	
	public int getNbVariable(){
		int cpt =0;
		for (String mapKey : locaux.keySet()) {
			if (locaux.get(mapKey).getValue()<0){
				cpt++;
			}
		}
		return cpt;
	}
	
	public void declNewFunctionParam(String functionName,String parameterName, Type parameterType){
		Function function = (Function) this.findIdent(functionName);
		// Ajout du paramètre à cette fonction
		Param p = function.declNewParam(parameterType, parameterName);
		function.setOffset(this.calculateOffsetParam(p));
		
		// Ajout du paramètre dans la hash map Locaux
		this.addLoco(parameterName,p);
	}
}


