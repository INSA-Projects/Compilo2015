import java.util.Stack;


public class Expression 
{
	/**
	 * Tableau des types de résultat
	 */
	private static final Type[][] tabControl = 
	{		
			{Type.ENTIER,Type.ERREUR,Type.ERREUR},
			{Type.BOOLEEN,Type.ERREUR,Type.ERREUR},
			{Type.BOOLEEN,Type.BOOLEEN,Type.ERREUR},
			{Type.ERREUR,Type.BOOLEEN,Type.ERREUR}											
	};
	
	/**
	 * Type de la variable à affecter
	 */
	private Type typeIdentAffected;
	
	public void setIdentAffectedType(Type type) {
		this.typeIdentAffected = type;
	}
	
	public void controlTypeAffectation() {
		Type type1 = operandes.pop();
		if (type1 == typeIdentAffected) {
			// Affectation correcte
			return;
		}
		System.out.println("Erreur : deux types ne correspondent pas dans une affectation \n");
		
	}
	
	
	private  Stack<Operateur> operators;
	private  Stack<Type> operandes;
	
	/**
	 * Push le type de l'opérande dans la pile operandes
	 * @param t
	 */
	public void pushOperande ( Type t){
		operandes.push(t);
	}
	
	/**
	 * Push l'opérateur dans la pile operators
	 * @param op
	 */
	public void pushOperator (Operateur op){
		operators.push(op);
	}
	
	
	/**
	 * Contrôle du type par l'opérateur Michel
	 */
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
