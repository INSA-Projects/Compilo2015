//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import java.io.InputStream;

public class TokenManager implements Constants {
    public String identLu;
    public int entierLu;
    private String lexeme;
    private int ligne;
    private InputStream flot;
    private char carLu;
    private static final char charEOF = '\u0000';

    public TokenManager(InputStream flot) {
        this.flot = flot;
        this.ligne = 1;
        this.lireCarLu();
    }

    public void finalize() {
        Lecture.fermer(this.flot);
    }

    private char lireCarLu() {
        if(!Lecture.finFichier(this.flot)) {
            this.carLu = Lecture.lireChar(this.flot);
            if(this.carLu == 10) {
                ++this.ligne;
            }
        } else {
            this.carLu = 0;
        }

        return this.carLu;
    }

    private int lireIdent() {
        this.lexeme = "";

        while(Character.isLetter(this.carLu)) {
            this.lexeme = this.lexeme + this.carLu;
            this.lireCarLu();
        }

        for(int i = 1; i <= 8; ++i) {
            if(this.lexeme.equals(images[i])) {
                return i;
            }
        }

        this.identLu = this.lexeme;
        return 9;
    }

    private void lireEntier() {
        this.lexeme = "";

        while(Character.isDigit(this.carLu)) {
            this.lexeme = this.lexeme + this.carLu;
            this.lireCarLu();
        }

        this.entierLu = Integer.parseInt(this.lexeme);
    }

    public int lireUnite() {
        while(Character.isWhitespace(this.carLu)) {
            this.lireCarLu();
        }

        this.lexeme = "";
        if(Character.isLetter(this.carLu)) {
            return this.lireIdent();
        } else if(Character.isDigit(this.carLu)) {
            this.lireEntier();
            return 10;
        } else {
            switch(this.carLu) {
                case '\u0000':
                    this.lexeme = "EOF";
                    return 14;
                case '*':
                    this.lireCarLu();
                    this.lexeme = "*";
                    return 13;
                case ',':
                    this.lireCarLu();
                    this.lexeme = ",";
                    return 12;
                case '.':
                    this.lireCarLu();
                    this.lexeme = ".";
                    return 11;
                default:
                    this.lireCarLu();
                    return 0;
            }
        }
    }

    public Token getToken() {
        Token t = new Token();
        t.unite = this.lireUnite();
        t.lexeme = this.lexeme;
        t.ligne = this.ligne;
        return t;
    }

    public static void main(String[] argv) {
        String nomFichier = argv[0];
        InputStream flot = Lecture.ouvrir(nomFichier);
        TokenManager l = new TokenManager(flot);

        for(Token t = l.getToken(); t.unite != 14; t = l.getToken()) {
            System.out.println(t);
        }

        l.finalize();
    }

	public static char getChareof() {
		return charEOF;
	}
}
