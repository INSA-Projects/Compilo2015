
public class YVM 
{
	protected int allocatedMemory = 0;
	
	public void alloc()
	{
		this.allocatedMemory += 2;
	}
	
	public void entete()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "entete\n");
	}
	
	public void queue()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "queue\n");
	}
	
	public void ouvrePrinc()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "ouvrePrinc "+this.allocatedMemory+"\n");
	}
	
	public void iconst(int value)
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "iconst "+value+"\n");
	}
	
	public void istore(int value)
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "istore "+value+"\n");
	}

	public void iadd ()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "iadd\n");
	}
	
	public void isub()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "isub\n");
	}
	
	public void imul()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "imul\n");
	}
	
	public void idiv()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "idiv\n");
	}
	
	public void iinfegal()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "iinfegal\n");
	}
	
	public void ior()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "ior\n");
	}
	
	public void iload(int offset) {
		Ecriture.ecrireString(Yaka.YVMfilename, "iload "+offset+"\n");
	}
	
	public void ecrireChaine(String chaine) {
		Ecriture.ecrireString(Yaka.YVMfilename, "ecrireChaine \""+chaine+"\"\n");
	}
}
