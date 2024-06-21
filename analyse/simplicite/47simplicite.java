package iut.sae.algo;

public class simplicite {

    // Méthode pour compresser une chaîne en utilisant l'algorithme RLE
    public static String RLE(String entree) {

        if (entree == null || entree.equals("")) {
            return "";  // Retourne une chaîne vide si l'entrée est nulle ou vide
        }

        String messageCompresse = "";  // Chaîne pour stocker le message compressé
        int cpt = 0;  // Compteur pour les occurrences d'un caractère
        String caractere = entree.substring(0, 1);  // Premier caractère de la chaîne

        // Parcourir chaque caractère de la chaîne d'entrée
        for (int i = 0; i < entree.length(); i++) {
            String currentChar = entree.substring(i, i + 1);

            if (currentChar.equals(caractere)) {
                // Si le caractère courant est le même que le précédent
                if (cpt == 9) {  // Limite à 9 pour éviter de dépasser un seul chiffre
                    messageCompresse += cpt + caractere;  // Ajoute le caractère et son compte
                    cpt = 0;  // Réinitialise le compteur
                }
                cpt++;  // Incrémente le compteur
            } else {
                // Si le caractère courant est différent du précédent
                messageCompresse += cpt + caractere;  // Ajoute le caractère et son compte
                caractere = currentChar;  // Met à jour le caractère
                cpt = 1;  // Réinitialise le compteur pour le nouveau caractère
            }
        }

        // Ajoute la dernière séquence de caractère et son compte
        messageCompresse += cpt + caractere;

        return messageCompresse;  // Retourne la chaîne compressée
    }

    // Méthode pour compresser une chaîne en utilisant RLE plusieurs fois
    public static String RLE(String entree, int iteration) {
        if (entree == null || entree.equals("")) {
            return "";  // Retourne une chaîne vide si l'entrée est nulle ou vide
        }

        for (int i = 0; i < iteration; i++) {
            entree = RLE(entree);  // Applique la compression RLE plusieurs fois
        }

        return entree;  // Retourne la chaîne compressée après les itérations
    }

    // Méthode pour décompresser une chaîne utilisant l'algorithme RLE
    public static String unRLE(String entree) {
        if (entree == null || entree.equals("")) {
            return "";  // Retourne une chaîne vide si l'entrée est nulle ou vide
        }

        String messageDecrypte = "";  // Chaîne pour stocker le message décompressé

        // Parcourir chaque caractère de la chaîne compressée
        for (int i = 0; i < entree.length(); i++) {
            if (i % 2 == 0) {  // Chaque nombre de répétition est à une position paire
                int count = Integer.parseInt(entree.substring(i, i + 1));  // Nombre de répétitions
                char currentChar = entree.charAt(i + 1);  // Caractère à répéter

                // Ajoute le caractère décompressé le nombre de fois indiqué
                for (int j = 0; j < count; j++) {
                    messageDecrypte += currentChar;
                }
            }
        }

        return messageDecrypte;  // Retourne la chaîne décompressée
    }

    // Méthode pour décompresser une chaîne utilisant RLE plusieurs fois
    public static String unRLE(String entree, int iteration) {
        if (entree == null || entree.equals("")) {
            return "";  // Retourne une chaîne vide si l'entrée est nulle ou vide
        }

        for (int i = 0; i < iteration; i++) {
            entree = unRLE(entree);  // Applique la décompression RLE plusieurs fois
        }

        return entree;  // Retourne la chaîne décompressée après les itérations
    }
}
