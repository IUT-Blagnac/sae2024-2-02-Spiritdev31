package efficacite;
public class efficacite_meilleur {
    public static String RLE(String donnees) {
        if (donnees.isEmpty()) {
            return ""; // O(1)
        }

        StringBuilder chaine = new StringBuilder(); // O(1)
        int compteur = 1; // O(1)
        char lettre_precedente = donnees.charAt(0); // O(1)

        // Boucle principale sur la longueur des données moins un caractère
        for (int i = 1; i < donnees.length(); i++) { // O(n)
            char lettre = donnees.charAt(i); // O(1)
            if (lettre == lettre_precedente) { // O(1)
                compteur++; // O(1)
            } else {
                // Gérer les cas où le compteur dépasse 9
                while (compteur > 9) { // O(k), où k est le nombre de fois que compteur dépasse 9
                    chaine.append("9").append(lettre_precedente); // O(1)
                    compteur -= 9; // O(1)
                }
                chaine.append(compteur).append(lettre_precedente); // O(1)
                lettre_precedente = lettre; // O(1)
                compteur = 1; // O(1)
            }
        }

        // Gérer les derniers caractères
        while (compteur > 9) { // O(k)
            chaine.append("9").append(lettre_precedente); // O(1)
            compteur -= 9; // O(1)
        }
        chaine.append(compteur).append(lettre_precedente); // O(1)

        return chaine.toString(); // O(1)
    }

    //RLE recursif

    public static String RLE(String donnees, int depth) {
        if (donnees.isEmpty() || depth < 1) {
            return "";  // O(1)
        }
        String resultat = donnees;  // O(1)
        for (int i = 0; i < depth; i++) {  // O(d) où d est la profondeur de récursion
            resultat = RLE_recursif(resultat, "", 1, 1);  // O(n) * O(d) à chaque itération
        }
        return resultat;  // O(1)
    }


    private static String RLE_recursif(String donnees, String chaine, int compteur, int index) {
        if (donnees == null || donnees.isEmpty()) {
            return "";  // O(1)
        }
        if (index == donnees.length()) {
            return ajouter_chaine(chaine, donnees.charAt(index - 1), compteur);  // O(c)
        }
        if (donnees.charAt(index) == donnees.charAt(index - 1)) {
            return RLE_recursif(donnees, chaine, compteur + 1, index + 1);  // O(n) récursivement
        } else {
            chaine = ajouter_chaine(chaine, donnees.charAt(index - 1), compteur);  // O(c)
            return RLE_recursif(donnees, chaine, 1, index + 1);  // O(n) récursivement
        }
    }


    private static String ajouter_chaine(String chaine, char caractere, int compteur) {
        StringBuilder chaineBuilder = new StringBuilder(chaine);  // O(m), où m est la longueur actuelle de `chaine`
        while (compteur > 9) {
            chaineBuilder.append("9").append(caractere);  // O(1), exécuté c/9 fois
            compteur -= 9;
        }
        chaine = chaineBuilder.toString();  // O(m)
        return chaine + compteur + caractere;  // O(m)
    }





    //partie optionnelle (regroupant unRLE, unRLE recursif)



    public static String unRLE(String donnee) {
        if (donnee == null || donnee.isEmpty()) {
            return "";
        }

        StringBuilder chaine = new StringBuilder();
        int compteur = 0;

        for (int i = 0; i < donnee.length(); i++) {
            char c = donnee.charAt(i);
            if (Character.isDigit(c)) {
                compteur = compteur * 10 + (c - '0'); // Accumuler le chiffre dans compteur
            } else {
                if (compteur == 0) {
                    chaine.append(c); // Gérer le cas où aucun chiffre n'était présent avant le caractère
                } else {
                    for (int j = 0; j < compteur; j++) {
                        chaine.append(c); // Ajouter le caractère répété
                    }
                    compteur = 0; // Réinitialiser le compteur
                }
            }
        }

        return chaine.toString();
    }

}
