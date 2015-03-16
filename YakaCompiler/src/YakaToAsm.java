
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
		this.write("\t; queue\n"
				+ "\tnop\n"
				+ "\tEXITCODE\n"
				+ "\tEND debut\n");
	}
	
	public void ouvrePrinc()
	{
		this.write("\t; ouvrePrinc "+this.allocatedMemory+"\n"
				+ "\tmov bp,sp\n"
				+ "\tsub sp,"+this.allocatedMemory+"\n");
	}
	
	public void iconst(int value)
	{
		this.write("\t; iconst "+value+"\n"
				+ "\tpush word ptr "+value+"\n");
	}
	
	public void istore(int value)
	{
		this.write("\t; istore "+value+"\n"
				+ "\tpop ax\n"
				+ "\tmov word ptr [bp"+value+"],ax\n");
	}

	public void iadd ()
	{
		this.write("\t; iadd\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\tadd ax,bx\n"
				+ "\tpush ax\n");
	}
	
	public void isub()
	{
		this.write("\t; isub\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\tsub ax,bx\n"
				+ "\tpush ax\n");
	}
	
	public void imul()
	{
		this.write("\t; imul\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\timul bx\n"
				+ "\tpush ax\n");
	}
	
	public void idiv()
	{
		this.write("\t; idiv\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\tcwd\n"
				+ "\tidiv bx\n"
				+ "\tpush ax\n");
	}
	
	public void idiff() 
	{
		this.write("\t; idiff\n"
				+ "\t - to do - \n");
	}
	
	public void ineg()
	{
		write(";ineg\n"
				+ "\t - to do - \n");
		
	}
	
	public void iinf()
	{
		write(";iinf\n"
				+ "\t - to do - \n");
	}
	
	public void iinfegal()
	{
		this.write(";iinfegal\n"
				+ "\t - to do - \n");
	}
	
	public void isup()
	{
		write(";isup\n"
				+ "\t - to do - \n");
	}
	
	public void isupegal()
	{
		write(";isupegal\n"
				+ "\t - to do - \n");
	}
	
	public void ior()
	{
		this.write(";ior\n"
				+ "\t - to do - \n");
	}
	
	public void iand()
	{
		write(";iand\n"
				+ "\t - to do - \n");
	}
	
	public void inot()
	{
		write(";inot\n"
				+ "\t - to do - \n");
	}
	
	public void iegal()
	{
		write(";iegal\n"
				+ "\t - to do - \n");
	}
	
	public void iload(int value)
	{
		this.write("\t; iload "+value+"\n"
				+ "\tpush word ptr [bp"+value+"]\n"); 
	}
	
	public void ecrireChaine(String chaine) {
		write("; ecrireChaine "+chaine+"\n"
				+ ".DATA\n");
		chaine += "$";
		write("\tmess"+this.cptMess+" DB "+chaine+"=$\n"
				+ ".CODE\n"
				+ "\tlea dx,mess"+this.cptMess+"\n"
				+ "\tpush dx\n"
				+ "\tcall ecrch\n");
		this.cptMess++;
	}
	
	public void aLaLigne(){
		write("\t; aLaligne\n"
				+ "\tcall ligsuiv\n");
	}
	
	public void lireEnt(int nb){
		write("\t; lireEnt "+nb+"\n"
				+ "\tlea dx,[bp"+nb+"]\n"
				+ "\tpush dx\n"
				+ "\tcall lirent\n");
	}
	
	public void ecrireEnt()
	{
		this.write("\t; ecrireEnt\n"
				+ "\tcall ecrent\n");
	}
}
