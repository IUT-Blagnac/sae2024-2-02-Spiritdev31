package iut.sae.algo;

public class simplicite {
    public static String RLE(String text) {
        // on initialise la première lettre et le compteur
        char lettre = text.charAt(0);
        StringBuilder chaineFinale = new StringBuilder();
        int compteurLettre = 0;

        // on parcours chaque parametre de la chaine
        for (int i = 0; i < text.length(); i++) {
            // si le caractère actuel est le même que le précédent et que le compteur est inférieur à 9
            if (lettre == text.charAt(i) && compteurLettre < 9) {
                // Incrémenter le compteur
                compteurLettre++;
            } else {
                // on ajoute le compteur et la lettre à la chaîne finale
                chaineFinale.append(compteurLettre).append(lettre);
                // on remets a jour le compteur et mettre à jour la lettre
                compteurLettre = 1;
                lettre = text.charAt(i);
            }

            // si le compteur atteint 9 (ce qu'il ne faut pas)
            if (compteurLettre == 9) {
                // on ajoute le compteur et la lettre à la chaîne finale
                chaineFinale.append(compteurLettre).append(lettre);
                // si nous on n'est pas à la fin de la chaîne
                if (i < text.length() - 1) {
                    // on mets à jour la lettre
                    lettre = text.charAt(i + 1);
                }
                // on reinitialise le compteur
                compteurLettre = 0;
            }
        }

        // si le compteur est supérieur à 0 à la fin de la boucle
        if (compteurLettre > 0) {
            // on ajoute ce qu'il reste a la chaine finale
            chaineFinale.append(compteurLettre).append(lettre);
        }

        // on mets le resultat sous forme de string et on le renvoie
        return chaineFinale.toString();
    }

    public static String RLE(String chaine, int nombre) {
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

    public static void main(String[] args) {
        System.out.println(RLE("1a1b1c"));
    }
}