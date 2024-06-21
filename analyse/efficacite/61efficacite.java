package iut.sae.algo;

public class efficacite {

    public static String RLE(String texte) {
        // si le texte est null ou vide, retourne une chaîne vide
        if (texte == null || texte.length() == 0) {
            return "";
        }

        // on cree un StringBuilder
        StringBuilder resultat = new StringBuilder();

        int compteur = 1;
        char caractere = texte.charAt(0);

        // Parcourt le texte à partir du deuxième caractère
        for (int i = 1; i < texte.length(); i++) {
            // si le caractère actuel est le même que le précédent et que le compteur est inférieur à 9
            if (texte.charAt(i) == caractere && compteur < 9) {
                compteur++;
            } else {
                // sinon on ajoute le compteur et le caractère au résultat
                resultat.append(compteur).append(caractere);
                // on remets les variables a jour
                compteur = 1;
                caractere = texte.charAt(i);
            }
        }

        // on ajoute ce qu'il reste au resultat
        resultat.append(compteur).append(caractere);

        // on mets le resultat sous forme de string et on le renvoie
        return resultat.toString();
    }

    public static String RLE(String chaine, int nombre) {
        if (nombre <= 0) {
            return chaine;
        }

        String resultat = chaine;
        for (int i = 0; i < nombre; i++) {
            resultat = RLE(resultat);
        }
        return resultat;
    }

    public static String unRLE(String chaine) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < chaine.length(); i += 2) {
            int nombre = Character.getNumericValue(chaine.charAt(i));
            char caractere = chaine.charAt(i + 1);
            for (int j = 0; j < nombre; j++) {
                result.append(caractere);
            }
        }
        return result.toString();
    }


    public static String unRLE(String chaine, int nombre) {
        String resultat = chaine;
        for (int i = 0; i < nombre; i++) {
            resultat = unRLE(resultat);
        }
        return resultat;
    }
}