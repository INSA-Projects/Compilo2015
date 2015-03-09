import java.util.Stack;


public class Expression 
{
	private static final Type[][] tabControl = 
	{		
			{Type.ENTIER,Type.ERREUR,Type.ERREUR},
			{Type.BOOLEEN,Type.ERREUR,Type.ERREUR},
			{Type.BOOLEEN,Type.BOOLEEN,Type.ERREUR},
			{Type.ERREUR,Type.BOOLEEN,Type.ERREUR}											
	};
	
	
	private  Stack<Operateur> operators;
	private  Stack<Type> operandes;
	
	public void pushOperande ( Type t){
		operandes.push(t);
	}
	
	public void pushOperator ( Operateur op){
		operators.push(op);
	}
	
	
	
	
	public void controlType()
	{
		Operateur michel = this.operators.pop();
		Type op1 = this.operandes.pop();
		if (michel.ordinal() == Operateur.NON.ordinal())
		{
			this.operandes.push(tabControl[3][op1.ordinal()]);
			return;
		}
		
		this.operandes.pop();
		
		switch(michel)
		{
		case PLUS:
		case MOINS:
		case MULT:
		case DIV:
			this.operandes.push(tabControl[0][op1.ordinal()]);
			break;
			
		case SUP:
		case INF:
		case SUPEG:
		case INFEG:
			this.operandes.push(tabControl[1][op1.ordinal()]);
			break;
			
		case EG:
		case DIFF:
			this.operandes.push(tabControl[2][op1.ordinal()]);
			break;
			
		case ET:
		case OU:
			this.operandes.push(tabControl[3][op1.ordinal()]);
			break;
			
		default:
			this.operandes.push(Type.ERREUR);
			break;
		}
	}
	
}
