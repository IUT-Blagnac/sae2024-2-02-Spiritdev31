package iut.sae.algo;

public class simplicite {

    /**
     * Méthode qui applique l'algorithme de compression RLE (Run Length Encoding) sur une chaîne de caractères.
     * @param in La chaîne de caractères à compresser.
     * @return La chaîne compressée.
     */
    public static String RLE(String in) {
        // Vérifie si la chaîne d'entrée est nulle ou vide, et retourne une chaîne vide dans ce cas.
        if (in == null || in.isEmpty()) {
            return "";
        }

        StringBuilder chaineConcat = new StringBuilder();
        int cpt = 1;  // Compteur pour le nombre d'occurrences d'un caractère.
        char caracPrecedent = in.charAt(0);  // Le premier caractère de la chaîne.

        // Parcourt la chaîne de caractères à partir du deuxième caractère.
        for (int i = 1; i < in.length(); i++) {
            char caracActuel = in.charAt(i);
            // Si le caractère actuel est identique au précédent.
            if (caracActuel == caracPrecedent) {
                if (cpt == 9) {  // Limite de l'encodage (9 occurrences max pour éviter des chiffres à deux chiffres).
                    chaineConcat.append(cpt).append(caracPrecedent);
                    cpt = 0;  // Réinitialise le compteur.
                }
                cpt++;
            } else {
                // Si le caractère actuel est différent du précédent, ajoute la séquence au résultat.
                chaineConcat.append(cpt).append(caracPrecedent);
                cpt = 1;  // Réinitialise le compteur.
                caracPrecedent = caracActuel;  // Met à jour le caractère précédent.
            }
        }

        // Ajoute la dernière séquence au résultat.
        chaineConcat.append(cpt).append(caracPrecedent);

        return chaineConcat.toString();
    }

    /**
     * Méthode qui applique plusieurs itérations de la compression RLE sur une chaîne de caractères.
     * @param in La chaîne de caractères à compresser.
     * @param iteration Le nombre d'itérations à appliquer.
     * @return La chaîne compressée après le nombre d'itérations spécifié.
     * @throws AlgoException Si une erreur se produit lors de la compression.
     */
    public static String RLE(String in, int iteration) throws AlgoException {
        for (int i = 0; i < iteration; i++) {
            in = RLE(in);
        }
        return in;
    }

    /**
     * Méthode qui applique l'algorithme de décompression RLE (Run Length Encoding) sur une chaîne de caractères.
     * @param in La chaîne de caractères à décompresser.
     * @return La chaîne décompressée.
     * @throws AlgoException Si le format de la chaîne d'entrée est invalide.
     */
    public static String unRLE(String in) throws AlgoException {
        // Vérifie si la chaîne d'entrée est nulle ou vide, et retourne une chaîne vide dans ce cas.
        if (in == null || in.isEmpty()) {
            return "";
        }

        StringBuilder chaineDeconcat = new StringBuilder();

        // Parcourt la chaîne de caractères.
        for (int i = 0; i < in.length(); i++) {
            // Vérifie si le caractère est un chiffre.
            if (Character.isDigit(in.charAt(i))) {
                int count = Character.getNumericValue(in.charAt(i));
                // Vérifie s'il y a un caractère suivant à associer au chiffre.
                if (i + 1 < in.length()) {
                    char character = in.charAt(i + 1);
                    // Ajoute le caractère 'count' fois à la chaîne décompressée.
                    for (int j = 0; j < count; j++) {
                        chaineDeconcat.append(character);
                    }
                    i++;  // Passe au caractère suivant après le chiffre.
                } else {
                    throw new AlgoException("Format non valide");
                }
            } else {
                throw new AlgoException("Format non valide");
            }
        }

        return chaineDeconcat.toString();
    }

    /**
     * Méthode qui applique plusieurs itérations de la décompression RLE sur une chaîne de caractères.
     * @param in La chaîne de caractères à décompresser.
     * @param iteration Le nombre d'itérations à appliquer.
     * @return La chaîne décompressée après le nombre d'itérations spécifié.
     * @throws AlgoException Si une erreur se produit lors de la décompression.
     */
    public static String unRLE(String in, int iteration) throws AlgoException {
        for (int i = 0; i < iteration; i++) {
            in = unRLE(in);
        }
        return in;
    }
}