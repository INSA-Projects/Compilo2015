import java.util.Stack;

public class Function extends Ident
{
	private int cptParam;
	// Compteur pour l'offset des paramètres
	private int offset;
	
	// Paramètres de la fonction
	private Stack<Param> parameters;
	
	// Type passés en paramètres lors d'un appel à la fonction
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
	
	// Appelé lors de la déclaration de la fonction : déclaration de paramètre
	public Param declNewParam(Type t, String nom) {
		Param p = new Param(t,offset,nom);
		this.addParam(p);
		return p;
	}
	
	// Effectue le contrôle de type des paramètres de la fonction
	public void controlTypeParam() {
		System.out.println("parameters : "+this.parameters.toString()+"\nparametereATester :"+this.parametreATester.toString());
		// Si différence de taille entre paramètres attendu et paramètres lus : ERROR
		if (this.parameters.size() != this.parametreATester.size()) {
			System.out.println("Erreur : nombre de paramètres attendu : "+this.parameters.size()+" Nombre de paramètres lus : "+this.parametreATester.size()+". Ligne "+SimpleCharStream.getEndLine()+"\n");
			// Abbiamo vidato tutte le due pile
			this.parameters.clear();
			this.parametreATester.clear();
		}
		// Sinon contrôle de type
		else {
			while (!this.parameters.empty()) {
				Type typeParamAttendu = this.parameters.pop().getType();
				Type typeParamATester = this.parametreATester.pop();
				
				if (typeParamAttendu.ordinal() != typeParamATester.ordinal()) {
					System.out.println("Erreur : Mauvais type de paramètre, attendu "+typeParamAttendu.toString()+" Type du paramètre donné : "+typeParamATester.toString()+". Ligne "+SimpleCharStream.getEndLine()+"\n");
				}
			}
		}
		
	}
	
}

