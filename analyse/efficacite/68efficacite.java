package iut.sae.algo;

/**
 * Cette classe contient des méthodes pour la compression et la décompression
 * d'une chaîne de caractères en utilisant l'algorithme Run-Length Encoding (RLE).
 *
 * <p>Les méthodes {@code RLE} permettent de compresser une chaîne en remplaçant
 * les séquences répétitives de caractères par un nombre suivi du caractère.
 * Les méthodes {@code unRLE} permettent de décompresser une chaîne encodée
 * avec RLE pour retrouver la chaîne originale.</p>
 *
 * <p>Les algorithmes utilisent des exceptions pour gérer les erreurs lors de
 * l'encodage et du décodage des chaînes.</p>
 *
 * @version 1.0
 */
public class efficacite {

    /**
     * Comprime une chaîne en utilisant l'algorithme Run-Length Encoding (RLE).
     *
     * @param in La chaîne à compresser.
     * @return La chaîne compressée.
     */
    public static String RLE(String in) {
        // Vérifie que la chaîne d'entrée n'est pas nulle ou vide
        if (in == null || in.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        char cX = in.charAt(0); // Caractère courant
        char c; // Caractère en cours de traitement
        int cpt = 1; // Compteur pour le nombre d'occurrences
        int length = in.length(); // Longueur de la chaîne

        // Parcourt la chaîne pour appliquer l'algorithme RLE
        for (int i = 1; i < length; i++) {
            c = in.charAt(i);

            if (c == cX && cpt < 9) {
                cpt++; // Incrémente le nombre d'occurrences si le caractère est le même
            } else {
                // Ajoute le nombre d'occurrences suivi du caractère au StringBuilder
                result.append(cpt).append(cX);
                cX = c; // Met à jour le caractère courant
                cpt = 1; // Réinitialise le compteur d'occurrences
            }
        }

        // Ajoute la dernière séquence compressée
        result.append(cpt).append(cX);

        return result.toString();
    }

    /**
     * Comprime récursivement une chaîne plusieurs fois en utilisant l'algorithme RLE.
     *
     * @param in La chaîne à compresser.
     * @param iteration Le nombre d'itérations de compression RLE.
     * @return La chaîne compressée après plusieurs itérations.
     * @throws AlgoException Si une erreur survient lors de la compression.
     */
    public static String RLE(String in, int iteration) throws AlgoException {
        // Vérification de l'argument
        if (iteration < 0) {
            throw new IllegalArgumentException("Le nombre d'itérations doit être non négatif.");
        }
        //Verification de l'argument
        if (in == null || in.isEmpty()) return "";

        String result = in;
        // Effectue l'algorithme RLE plusieurs fois
        for (int i = 0; i < iteration; i++) {
            result = RLE(result);
        }
        return result;
    }

    /**
     * Décompresse une chaîne encodée avec RLE en retrouvant la chaîne originale.
     *
     * @param in La chaîne à décompresser.
     * @return La chaîne décompressée.
     * @throws AlgoException Si une erreur survient lors de la décompression.
     */
    public static String unRLE(String in) throws AlgoException {
        int length = in.length();
        // Vérifie que la longueur de la chaîne est paire
        if (length % 2 != 0) {
            throw new IllegalArgumentException("La chaîne d'entrée doit avoir une longueur paire.");
        }

        //Verification de l'argument
        if (in == null || in.isEmpty()) return "";

        StringBuilder result = new StringBuilder();
        int nbChars; // Nombre de caractères à ajouter
        char c; // Caractère à ajouter

        // Parcourt la chaîne encodée avec RLE pour la décompresser
        for (int i = 0; i < length; i += 2) {
            nbChars = in.charAt(i) - '0'; // Convertit le chiffre en entier
            c = in.charAt(i + 1); // Récupère le caractère

            // Ajoute le caractère le nombre de fois indiqué
            for (int j = 0; j < nbChars; j++) {
                result.append(c);
            }
        }

        return result.toString();
    }

    /**
     * Décompresse récursivement une chaîne plusieurs fois encodée avec RLE.
     *
     * @param in La chaîne à décompresser.
     * @param iteration Le nombre d'itérations de décompression RLE.
     * @return La chaîne décompressée après plusieurs itérations.
     * @throws AlgoException Si une erreur survient lors de la décompression.
     */
    public static String unRLE(String in, int iteration) throws AlgoException {
        // Vérification des arguments
        if (iteration < 0) {
            throw new IllegalArgumentException("Le nombre d'itérations doit être non négatif.");
        }

        if (in == null || in.isEmpty()) return "";

        String result = in;
        // Effectue la décompression RLE plusieurs fois
        for (int i = 0; i < iteration; i++) {
            result = unRLE(result);
        }
        return result;
    }

}
