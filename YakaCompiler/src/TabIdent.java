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

}
