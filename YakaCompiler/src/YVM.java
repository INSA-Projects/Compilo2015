import java.util.Stack;


public class YVM 
{
	protected int allocatedMemory = 0;
	protected int allocParam =2;		
	
	protected int coptTaq =0;
	protected Stack<Integer> taquEtiq =new Stack<Integer>();
	protected int cptCond = 0;
	protected Stack<Integer> condEtiq = new Stack<Integer>();
	
	public void clear (){
		allocatedMemory=0;
		allocParam=2;
	}
	
	public void alloc()
	{
		this.allocatedMemory += 2;
	}
	
	public void addAllocParam(){
		this.allocParam+=2;
	}
	public void entete()
	{
		Ecriture.clearFile(Yaka.YVMfilename);
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
	
	public void ineg()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "ineg\n");
	}
	
	public void iinf()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "iinf\n");
	}
	
	public void iinfegal()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "iinfegal\n");
	}
	
	public void isup()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "isup\n");
	}
	
	public void isupegal()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "isupegal\n");
	}
	
	public void ior()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "ior\n");
	}
	
	public void iand()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "iand\n");
	}
	
	public void idiff() {
		Ecriture.ecrireString(Yaka.YVMfilename, "idiff\n");
	}
	
	public void inot()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "inot\n");
	}
	
	public void iegal()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "iegal\n");
	}
	
	public void iload(int offset) {
		Ecriture.ecrireString(Yaka.YVMfilename, "iload "+offset+"\n");
	}
	
	public void ecrireChaine(String chaine) {
		Ecriture.ecrireString(Yaka.YVMfilename, "ecrireChaine "+chaine+"\n");
	}
	
	public void aLaLigne()
	{
		Ecriture.ecrireString(Yaka.YVMfilename, "aLaLigne\n");
	}
	
	public void lireEnt(int nb){
		Ecriture.ecrireString(Yaka.YVMfilename, "lireEnt "+nb+"\n");
	}
	
	public void ecrireEnt(){
		Ecriture.ecrireString(Yaka.YVMfilename, "ecrireEnt\n");
	}
	
	// iteration
	public void tantQue()
	{
		coptTaq++;
		Ecriture.ecrireString(Yaka.YVMfilename, "FAIRE"+coptTaq+":\n");
		taquEtiq.push(coptTaq);
	}
	
	public void faire(){
		Ecriture.ecrireString(Yaka.YVMfilename, "iffaux FAIT"+taquEtiq.peek()+"\n");
	}
	
	public void fait(){
		Ecriture.ecrireString(Yaka.YVMfilename, "goto FAIRE"+taquEtiq.peek()+"\n");
		Ecriture.ecrireString(Yaka.YVMfilename, "FAIT"+taquEtiq.pop()+":\n");
	}
	
	
	// conditionnelle
	public void iffauxCond(){
		this.cptCond++;
		this.condEtiq.push(this.cptCond);
		Ecriture.ecrireString(Yaka.YVMfilename, "iffaux SINON"+this.cptCond+"\n");
	}
	
	public void goTo(){
		Ecriture.ecrireString(Yaka.YVMfilename, "goto FSI"+this.condEtiq.peek()+"\n");
	}
	
	public void sinon(){
		Ecriture.ecrireString(Yaka.YVMfilename, "SINON"+this.condEtiq.peek()+" :\n");
	}
	
	public void fsi(){
		Ecriture.ecrireString(Yaka.YVMfilename, "FSI"+this.condEtiq.pop()+" :\n");
	}
}


