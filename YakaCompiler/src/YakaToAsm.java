
public class YakaToAsm extends YVM
{
	
	private int cptMess;
	
	private void write (String s){
		Ecriture.ecrireString(Yaka.ASMfilename, "\t"+s);
	}
	
	public void test()
	{
		this.entete();
		this.ouvrePrinc();
		this.iconst(10);
		this.idiv();
		this.iadd();
		this.istore(566);
		this.ior();
		this.iinfegal();
		this.queue();
	}
	
	public void entete()
	{
		Ecriture.clearFile(Yaka.ASMfilename);
		Ecriture.ecrireString(Yaka.ASMfilename, "\t; entete\n"
				+ "\textrn lirent:proc, ecrent:proc\n"
				+ "\textrn ecrbool:proc\n"
				+ "\textrn ecrch:proc, ligsuiv:proc\n"
				+ ".model SMALL\n"
				+ ".586\n"
				+ ".CODE\n"
				+ "debut :\n"
				+ "\tSTARTUPCODE\n");
	}
	
	public void queue()
	{
		this.write("; queue\n"
				+ "\tnop\n"
				+ "\tEXITCODE\n"
				+ "\tEND debut\n");
	}
	
	public void ouvrePrinc()
	{
		this.write("; ouvrePrinc "+this.allocatedMemory+"\n"
				+ "\tmov bp,sp\n"
				+ "\tsub sp,"+this.allocatedMemory+"\n");
	}
	
	public void iconst(int value)
	{
		this.write("; iconst "+value+"\n"
				+ "\tpush "+value+"\n");
	}
	
	public void istore(int value)
	{
		this.write("; istore "+value+"\n"
				+ "\tpop ax\n"
				+ "\tmov word ptr [bp"+value+"],ax\n");
	}

	public void iadd ()
	{
		this.write("; iadd\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\tadd ax,bx\n"
				+ "\tpush ax\n");
	}
	
	public void isub()
	{
		this.write("; isub\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\tsub ax,bx\n"
				+ "\tpush ax\n");
	}
	
	public void imul()
	{
		this.write("; imul\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\timul bx\n"
				+ "\tpush ax\n");
	}
	
	public void idiv()
	{
		this.write("; idiv\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\tcwd\n"
				+ "\tidiv bx\n"
				+ "\tpush ax\n");
	}
	
	public void idiff() 
	{
		write(";idiff\n"
				+ "\tpop bx\n" 
				+ "\tpop ax\n" 
				+ "\tcmp ax, bx\n" 
				+ "\tje $+6\n" 
				+ "\tpush -1\n" 
				+ "\tjmp $+4\n" 
				+ "\tpush 0\n\n"); 
	}
	
	public void ineg()
	{
		write(";ineg\n"
				+ "pop ax\n"
				+ "neg ax\n"
				+ "push ax\n");
	}
	
	public void iinf()
	{
		write(";iinf\n"
				+ "\tpop bx\n" 
				+ "\tpop ax\n" 
				+ "\tcmp ax, bx\n" 
				+ "\tjge $+6\n" 
				+ "\tpush -1\n" 
				+ "jmp $+4\n" 
				+ "push 0\n\n"); 
	}
	
	public void iinfegal()
	{
		write(";iinf\n"
				+ "\tpop bx\n" 
				+ "\tpop ax\n" 
				+ "\tcmp ax, bx\n" 
				+ "\tjg $+6\n" 
				+ "\tpush -1\n" 
				+ "\tjmp $+4\n" 
				+ "\tpush 0\n\n"); 
	}
	
	public void isup()
	{
		write(";isup\n"
				+ "\tpop bx\n" 
				+ "\tpop ax\n" 
				+ "\tcmp ax, bx\n" 
				+ "\tjle $+6\n" 
				+ "\tpush -1\n" 
				+ "\tjmp $+4\n" 
				+ "\tpush 0\n\n"); 
	}
	
	public void isupegal()
	{
		write(";isup\n"
				+ "\tpop bx\n" 
				+ "\tpop ax\n" 
				+ "\tcmp ax, bx\n" 
				+ "\tjl $+6\n" 
				+ "\tpush -1\n" 
				+ "\tjmp $+4\n" 
				+ "\tpush 0\n\n"); 
	}
	
	public void ior()
	{
		write(";ior\n"
				+ "\tpop ax\n" 
				+ "\tpop bx\n" 
				+ "\tor ax, bx\n" 
				+ "\tpush ax\n\n"); 
	}
	
	public void iand()
	{
		write(";iand\n"
				+ "\tpop ax\n" 
				+ "\tpop bx\n" 
				+ "\tand ax, bx\n" 
				+ "\tpush ax\n\n"); 
	}
	
	public void inot()
	{
		write(";inot\n"
				+ "\t pop ax"
				+ "\t not ax \n"
				+ "push ax \n");
	}
	
	public void iegal()
	{
		write(";iegal\n"
				+ "\tpop bx\n" 
				+ "\tpop ax\n" 
				+ "\tcmp ax, bx\n" 
				+ "\tjne $+6\n" 
				+ "\tpush -1\n" 
				+ "\tjmp $+4\n" 
				+ "\tpush 0\n\n"); 
	}
	
	public void iload(int value)
	{
		this.write("; iload "+value+"\n"
				+ "\tpush word ptr [bp"+value+"]\n"); 
	}
	
	public void ecrireChaine(String chaine) {
		write("; ecrireChaine "+chaine+"\n"
				+ ".DATA\n");
		String tmp = chaine.substring(0,chaine.length()-1);
		chaine = tmp + "$\"";
		write("mess"+this.cptMess+" DB "+chaine+"\n"
				+ ".CODE\n"
				+ "\tlea dx,mess"+this.cptMess+"\n"
				+ "\tpush dx\n"
				+ "\tcall ecrch\n");
		this.cptMess++;
	}
	
	public void aLaLigne(){
		write("; aLaligne\n"
				+ "\tcall ligsuiv\n");
	}
	
	public void lireEnt(int nb){
		write("; lireEnt "+nb+"\n"
				+ "\tlea dx,[bp"+nb+"]\n"
				+ "\tpush dx\n"
				+ "\tcall lirent\n");
	}
	
	public void ecrireEnt()
	{
		this.write("; ecrireEnt\n"
				+ "\tcall ecrent\n");
	}
	
	// iteration
	public void tantQue()
	{
		coptTaq++;
		Ecriture.ecrireString(Yaka.ASMfilename, "FAIRE"+coptTaq+":\n");
		taquEtiq.push(coptTaq);
	}
	
	public void faire(){
		Ecriture.ecrireString(Yaka.ASMfilename, ";iffaux fait"+taquEtiq.peek()+"\n"
				+ "\tpop ax\n"
				+ "\tcmp ax,0\n"
				+ "je FAIT"+taquEtiq.peek()+"\n");
	}
	
	public void fait(){
		Ecriture.ecrireString(Yaka.ASMfilename, ";Goto faire"+taquEtiq.peek()+"\n"
				+ "\tjmp FAIRE"+taquEtiq.peek()+"\n");
		Ecriture.ecrireString(Yaka.ASMfilename, "fait"+taquEtiq.pop()+":\n");
	}
	
	// conditionnelle
	public void iffauxCond(){
		this.cptCond++;
		this.condEtiq.push(this.cptCond);
		Ecriture.ecrireString(Yaka.ASMfilename, 
				";iffaux SINON"+this.cptCond+"\n"
				+ "\tpop ax\n"
				+ "\tcmp ax,0\n"
				+ "\tje SINON"+this.cptCond+"\n");
	}
	
	public void goTo()
	{
		Ecriture.ecrireString(Yaka.ASMfilename,
				";goto FSI"+this.condEtiq.peek()+
				"\n"
				+ "\tgoto FSI"+this.condEtiq.peek()+"\n");
	}
	
	public void sinon(){
		Ecriture.ecrireString(Yaka.ASMfilename,
				"SINON"+this.condEtiq.peek()+" :\n");
	}
	
	public void fsi(){
		Ecriture.ecrireString(Yaka.ASMfilename, "FSI"+this.condEtiq.pop()+"\n");
	}
	
	
	public void ouvBloc (int i)
	{
		Ecriture.ecrireString(Yaka.YVMfilename, ";ouvbloc "+i+"\n"
				+ "enter "+i+",0\n");
	}
}
