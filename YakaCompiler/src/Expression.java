import java.util.Stack;


public class Expression 
{
	/**
	 * Tableau des types de résultat
	 */
	private static final Type[][] tabControl = 
	{		
			// ENTIER 	// BOOLEEN	 // ERREUR
			{Type.ENTIER,Type.ERREUR,Type.ERREUR}, // + - * /
			{Type.BOOLEEN,Type.ERREUR,Type.ERREUR}, // > < <= >=
			{Type.BOOLEEN,Type.BOOLEEN,Type.ERREUR}, // = !=
			{Type.ERREUR,Type.BOOLEEN,Type.ERREUR}, // ET OU
			{Type.ENTIER,Type.ERREUR,Type.ERREUR}, // NEG
			{Type.ERREUR,Type.BOOLEEN,Type.ERREUR} // NON
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
		Type operande = this.operandes.pop();
		
		// pop la deuxieme operande
		this.operandes.pop();
		
		switch(operator)
		{
		// + - * /
		case PLUS:
			this.operandes.push(tabControl[0][operande.ordinal()]);
			Yaka.yvm.iadd();
			break;
		case MOINS:
			this.operandes.push(tabControl[0][operande.ordinal()]);
			Yaka.yvm.isub();
			break;
		case MULT:
			this.operandes.push(tabControl[0][operande.ordinal()]);
			Yaka.yvm.imul();
			break;
		case DIV:
			this.operandes.push(tabControl[0][operande.ordinal()]);
			Yaka.yvm.idiv();
			break;
		
		// > < >= <=	
		case SUP:
			this.operandes.push(tabControl[1][operande.ordinal()]);
			Yaka.yvm.isup();
			break;
		case INF:
			this.operandes.push(tabControl[1][operande.ordinal()]);
			Yaka.yvm.iinf();
			break;
		case SUPEG:
			this.operandes.push(tabControl[1][operande.ordinal()]);
			Yaka.yvm.isupegal();
			break;
		case INFEG:
			this.operandes.push(tabControl[1][operande.ordinal()]);
			Yaka.yvm.iinfegal();
			break;
			
		// = !=	
		case EG:
			this.operandes.push(tabControl[2][operande.ordinal()]);
			Yaka.yvm.iegal();
			break;
		case DIFF:
			this.operandes.push(tabControl[2][operande.ordinal()]);
			Yaka.yvm.idiff();
			break;
			
		// ET OU	
		case ET:
			this.operandes.push(tabControl[3][operande.ordinal()]);
			Yaka.yvm.iand();
			break;
		case OU:
			this.operandes.push(tabControl[3][operande.ordinal()]);
			Yaka.yvm.ior();
			break;
			
			
		// Opérations unaires
		case NEG :
			this.operandes.push(tabControl[5][operande.ordinal()]);
			Yaka.yvm.ineg();
			break;
		case NON :
			this.operandes.push(tabControl[5][operande.ordinal()]);
			Yaka.yvm.inot();
			break;
			
		default:
			this.operandes.push(Type.ERREUR);
			break;
		}
	}
	
}
