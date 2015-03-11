
public class YakaToAsm extends YVM
{
	
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
		Ecriture.ecrireString(Yaka.ASMfilename, ";entete\n"
				+ ".model SMALL\n"
				+ ".586\n"
				+ ".CODE\n"
				+ "debut :\n"
				+ "\tSTARTUPCODE\n");
	}
	
	public void queue()
	{
		this.write(";queue\n"
				+ "\tnop\n"
				+ "\texitcode\n"
				+ "\tend debut\n");
	}
	
	public void ouvrePrinc()
	{
		this.write(";ouvrePrinc"+this.allocatedMemory+"\n"
				+ "\tmov bp,sp\n"
				+ "\tsub sp,"+this.allocatedMemory+"\n");
	}
	
	public void iconst(int value)
	{
		this.write(";iconst "+value+"\n"
				+ "\tpush "+value+"\n");
	}
	
	public void istore(int value)
	{
		this.write(";istore "+value+"\n"
				+ "\tpop ax\n"
				+ "\tmov word ptr [bp-"+value+"],ax\n");
	}

	public void iadd ()
	{
		this.write(";iadd\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\tadd ax,bx\n"
				+ "\tpush ax\n");
	}
	
	public void isub()
	{
		this.write(";isub\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\tsub ax,bx\n"
				+ "\tpush ax\n");
	}
	
	public void imul()
	{
		this.write(";imul\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\timul bx\n"
				+ "\tpush ax\n");
	}
	
	public void idiv()
	{
		this.write(";idiv\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\tcwd\n"
				+ "\tidiv bx\n"
				+ "\tpush ax\n");
	}
	
	public void iinfegal()
	{
		this.write(";iinfegal\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\tcmp ax,bx\n"
				+ "\tjg $+6\n"
				+ "\tpush -1\n"
				+ "\tjmp $+4\n"
				+ "\tpush 0\n");
	}
	
	public void ior()
	{
		this.write(";ior\n"
				+ "\tpop bx\n"
				+ "\tpop ax\n"
				+ "\tor ax,bx\n"
				+ "\tpush ax\n");
	}
}
