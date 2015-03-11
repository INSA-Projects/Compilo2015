import java.util.HashMap;

public class TabIdent 
{
	private HashMap<String,Ident> table;
	
	/**
	 * Constructor
	 * @param size
	 */
	public TabIdent(int size)
	{
		this.table = new HashMap<String, Ident>();
	}
	
	/**
	 * Find the ident
	 * @param key
	 * @return
	 */
	public Ident findIdent (String key)
	{
		return table.get(key);
	}
	
	/**
	 * Retrun true if key exist
	 * @param key
	 * @return
	 */
	public boolean identExist (String key)
	{
		return table.containsKey(key);
	}
	
	/**
	 * Put ident
	 * @param key
	 * @param id
	 */
	public void putIdent (String key, Ident id)
	{
		table.put(key, id);
	}
	
	/**
	 * Get the type of the ident 
	 * @param key
	 * @return Type
	 */
	public Type getType (String key)
	{
		if (this.identExist(key)){
			Ident ident = this.table.get(key);
			return ident.getType();
		}
		else {
			System.out.println("Erreur cet Ident n'existe pas dans la table des identificateurs \n");
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
			System.out.println("Cet Ident n'est pas présent dans la table des identificateurs");
		}
		return -1;
	}

}


