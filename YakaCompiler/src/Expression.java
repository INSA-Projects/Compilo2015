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
	
	private Ident identAffected;
	private String nomIdentAffected;
	
	public void setIdentAffected(String ident) {
		this.identAffected = Yaka.tabIdent.findIdent(ident);
		this.nomIdentAffected = ident;
	}
	
	public void controlTypeAffectation() {
		Type type1 = operandes.pop();
		if (type1 == this.identAffected.getType()) {
			// Affectation correcte
			Yaka.yvm.istore(Yaka.tabIdent.getValue(nomIdentAffected));
			return;
		}
		System.out.println("Erreur : deux types ne correspondent pas dans une affectation \n");
	}
	
	public Expression(){
		this.operandes=new Stack<Type>();
		this.operators=new Stack<Operateur>();
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
	 * Contrôle du type sur l'opérateur operator
	 */
	public void controlType()
	{
		Operateur operator = this.operators.pop();
		Type op1 = this.operandes.pop();
		
		if (operator.ordinal() == Operateur.NON.ordinal())
		{
			this.operandes.push(tabControl[3][op1.ordinal()]);
			return;
		}
		
		this.operandes.pop();
		
		switch(operator)
		{
		case PLUS:
			Yaka.yvm.iadd();
		case MOINS:
			Yaka.yvm.isub();
		case MULT:
			Yaka.yvm.imul();
		case DIV:
			Yaka.yvm.idiv();
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
