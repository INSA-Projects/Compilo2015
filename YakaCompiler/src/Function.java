import java.util.Stack;

public class Function extends Ident
{
	private int cptParam;
	// Compteur pour l'offset des param�tres
	private int offset;
	
	// Param�tres de la fonction
	private Stack<Param> parameters;
	
	// Type pass�s en param�tres lors d'un appel � la fonction
	private Stack<Type> parametreATester;

	public void setOffset(int offset){
		this.offset = offset;
	}
	
	public Function(Type t){
		super(t,0);
		parameters = new Stack<Param>();
	}

	public int tailleParams(){
		return parameters.size();
	}
	public void addParam(Param p){
		p.value = cptParam;
		cptParam ++;
		parameters.push(p);
	}
	
	public void addParamToControl(Type t) {
		parametreATester.push(t);
	}
	
	// Appel� lors de la d�claration de la fonction : d�claration de param�tre
	public Param declNewParam(Type t, String nom) {
		Param p = new Param(t,offset,nom);
		this.addParam(p);
		return p;
	}
	
	// Effectue le contr�le de type des param�tres de la fonction
	public void controlTypeParam() {
		System.out.println("parameters : "+this.parameters.toString()+"\nparametereATester :"+this.parametreATester.toString());
		// Si diff�rence de taille entre param�tres attendu et param�tres lus : ERROR
		if (this.parameters.size() != this.parametreATester.size()) {
			System.out.println("Erreur : nombre de param�tres attendu : "+this.parameters.size()+" Nombre de param�tres lus : "+this.parametreATester.size()+". Ligne "+SimpleCharStream.getEndLine()+"\n");
			// Abbiamo vidato tutte le due pile
			this.parameters.clear();
			this.parametreATester.clear();
		}
		// Sinon contr�le de type
		else {
			while (!this.parameters.empty()) {
				Type typeParamAttendu = this.parameters.pop().getType();
				Type typeParamATester = this.parametreATester.pop();
				
				if (typeParamAttendu.ordinal() != typeParamATester.ordinal()) {
					System.out.println("Erreur : Mauvais type de param�tre, attendu "+typeParamAttendu.toString()+" Type du param�tre donn� : "+typeParamATester.toString()+". Ligne "+SimpleCharStream.getEndLine()+"\n");
				}
			}
		}
		
	}
	
}

