
public class Ident 
{
	protected Type type;
	protected int value;
	
	public Ident(Type type, int value) 
	{
		this.type = type;
		this.value = value;
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int v) {
		this.value = v;
	}
	
	public String toString(){
		return "Ident de type :"+this.type+" et de value "+this.value;
	}
	
	
}
