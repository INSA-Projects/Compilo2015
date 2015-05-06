import java.util.Stack;


public class Expression 
{
	// Tableau des correspondances de types en fonction des opérations
	private static final Type[][] tabControl = 
	{		
			// ENTIER 	// BOOLEEN	 // ERREUR
			{Type.ENTIER,Type.ERREUR,Type.ERREUR}, // PLUS
			{Type.ENTIER,Type.ERREUR,Type.ERREUR}, // MOINS
			{Type.ENTIER,Type.ERREUR,Type.ERREUR}, // MULT
			{Type.ENTIER,Type.ERREUR,Type.ERREUR}, // DIV
			{Type.BOOLEEN,Type.ERREUR,Type.ERREUR}, // INF
			{Type.BOOLEEN,Type.ERREUR,Type.ERREUR}, // SUP
			{Type.BOOLEEN,Type.ERREUR,Type.ERREUR}, // INFEG
			{Type.BOOLEEN,Type.ERREUR,Type.ERREUR}, // SUPEG
			{Type.BOOLEEN,Type.BOOLEEN,Type.ERREUR}, // EG
			{Type.BOOLEEN,Type.BOOLEEN,Type.ERREUR}, // DIFF
			{Type.ERREUR,Type.BOOLEEN,Type.ERREUR}, // ET
			{Type.ERREUR,Type.BOOLEEN,Type.ERREUR}, // OU
			{Type.ENTIER,Type.ERREUR,Type.ERREUR}, // NEG
			{Type.ERREUR,Type.BOOLEEN,Type.ERREUR}, // NON
	};
	// Pile des opérateurs dans une expression
	private  Stack<Operateur> operators;
	// Pile des opérandes dans une expression
	private  Stack<Type> operandes;
	

	// Constructeur d'Expression
	public Expression(){
		this.operandes=new Stack<Type>();
		this.operators=new Stack<Operateur>();
	}
	
	// Ajoute une opérande dans la pile des opérandes
	public void pushOperande ( Type t){
		operandes.push(t);
	}
	
	// Peek le type d'opérande
	public Type peekTypeOperande() {
		return this.operandes.peek();
	}
	
	// Ajoute un opérateur dans la pile des opérateurs
	public void pushOperator (Operateur op){
		operators.push(op);
	}
	
	// Contrôle de type lors d'une expression
	public void controlType()
	{
		Operateur operator = this.operators.pop();
		Type operande = this.operandes.pop();
		
		// pop la deuxieme operande
		// Seulement dans le cas d'une opération binaire	
		if (!(operator.ordinal() == Operateur.NEG.ordinal() || operator.ordinal() == Operateur.NON.ordinal())) {
			Type tmp = this.operandes.pop();
			// En Yaka on suppose les deux opérandes de même type
			if (tmp!=operande) {
				System.out.println("Erreur : opération entre deux types différents \n");
				this.operandes.push(Type.ERREUR);
				return;
			}
		}
		
		switch(operator)
		{
		// + - * /
		case PLUS:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.iadd();
			break;
		case MOINS:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.isub();
			break;
		case MULT:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.imul();
			break;
		case DIV:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.idiv();
			break;
		
		// > < >= <=	
		case SUP:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.isup();
			break;
		case INF:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.iinf();
			break;
		case SUPEG:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.isupegal();
			break;
		case INFEG:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.iinfegal();
			break;
			
		// = !=	
		case EG:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.iegal();
			break;
		case DIFF:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.idiff();
			break;
			
		// ET OU	
		case ET:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.iand();
			break;
		case OU:
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.ior();
			break;
			
			
		// Opérations unaires
		case NEG :
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.ineg();
			break;
		case NON :
			this.operandes.push(tabControl[operator.ordinal()][operande.ordinal()]);
			Yaka.yvm.inot();
			break;
			
		default:
			this.operandes.push(Type.ERREUR);
			break;
		}
	}
	
	//-------------------------------------- Affectation --------------------------------------//
	
	// Ident en cours d'affectation
	private Ident identAffected;
	// Nom de l'ident en cours d'affectation
	private String nomIdentAffected;
	
	// Setter de l'ident en cours d'affectation (ne peut pas être une constante)
	public void setIdentAffected(String ident) {
		this.identAffected = Yaka.tabIdent.findIdent(ident);
		if (this.identAffected instanceof IdConst) {
			System.out.println("Erreur : "+ident+" n'est pas une variable ligne "+SimpleCharStream.getEndLine()+"\n");
		}
		
		this.nomIdentAffected = ident;
	}
	
	// Génère le code YVM permettant de modifier la valeur d'un ident. (iload et iconst)
	public void accedeIdent(String s) {
		Ident id = Yaka.tabIdent.findIdent(s);
		if (id instanceof IdConst) {
			Yaka.yvm.iconst(Yaka.tabIdent.getValue(YakaTokenManager.identLu));
		}
		else {
			Yaka.yvm.iload(Yaka.tabIdent.getValue(YakaTokenManager.identLu));
		}
	}
	
	// Contrôle si le type affecté correspond au type de l'ident 
	public void controlTypeAffectation() {
		Type type1 = operandes.pop();
		/*
		System.out.println("dans la pile d'opérandes : "+this.operandes.toString());
		System.out.println("type à affecter : "+type1.toString());
		System.out.println("type affected : "+this.identAffected.getType());
		*/
		if (type1 == this.identAffected.getType()) {
			// Affectation correcte
			Yaka.yvm.istore(Yaka.tabIdent.getValue(nomIdentAffected));
			return;
		}
		System.out.println("\nErreur : deux types ne correspondent pas dans une affectation ligne "+SimpleCharStream.getEndLine()+"\n");
	}

	//-------------------------------------- Itération --------------------------------------//
	
	// Contrôle si l'expression est booléenne (pour l'itération)
	public void isBooleanExpression() {
		Type typeExpr = this.operandes.peek();
		if (typeExpr == Type.BOOLEEN) {
			return;
		}
		System.out.println("Erreur de type d'expression ligne"+SimpleCharStream.getEndLine()+". Expression booléenne attendue. \n");
	}
	
	//-------------------------------------- Fonction --------------------------------------//

	// Pendant la déclaration : contrôle si le type de retour de la fonction correspond au type attendu
	public void controlTypeFonction(Type typeDecl) {		
		if (typeDecl != this.operandes.peek()) {
			System.out.println("\nErreur : le type de retour de la fonction ne correspond pas au type attendu "+"("+typeDecl+")"+" ligne "+SimpleCharStream.getEndLine()+"\n");
		}
	}

	

}
