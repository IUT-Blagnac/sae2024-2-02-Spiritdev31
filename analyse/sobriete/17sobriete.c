#include <stdio.h>
#include <malloc.h>
#include <string.h>
#include <ctype.h>

/**
 * Effectue une compression RLE (Run-Length Encoding) sur la chaîne d'entrée.
 *
 * L'utilisation d'un tableau resultats alloué dynamiquement minimise la surcharge de mémoire
 * par rapport aux opérations de concaténation de chaînes, ce qui est typique dans l'encodage RLE.
 * En gérant manuellement la mémoire avec malloc, nous optimisons l'efficacité de la gestion
 * des ressources mémoire.
 *
 * @param in Chaîne à compresser
 * @return Chaîne compressée selon l'algorithme RLE
 */
char* RLE(const char* in) {
    if (in == NULL || *in == '\0') return NULL;  // Vérifie si la chaîne d'entrée est vide

    char* resultats = (char*) malloc(2 * strlen(in) * sizeof(char));  // Alloue mémoire pour le résultat
    if (resultats == NULL) {
        perror("Allocation mémoire a échoué");
        exit(EXIT_FAILURE);
    }

    int compteur = 1;
    char caractereCourant = in[0];
    int resultats_index = 0;

    for (int i = 1; i < strlen(in); i++) {
        if (in[i] == caractereCourant) {
            compteur++;
            if (compteur == 9) {
                sprintf(resultats + resultats_index, "%d%c", compteur, caractereCourant);
                resultats_index += 2;
                compteur = 0;
            }
        } else {
            if (compteur > 0) {
                sprintf(resultats + resultats_index, "%d%c", compteur, caractereCourant);
                resultats_index += 2;
            }
            caractereCourant = in[i];
            compteur = 1;
        }
    }

    if (compteur > 0) {
        sprintf(resultats + resultats_index, "%d%c", compteur, caractereCourant);
    }

    return resultats;
}

/**
 * Effectue une compression RLE (Run-Length Encoding) itérative sur la chaîne d'entrée.
 *
 * L'allocation et la libération de mémoire sont optimisées pour minimiser les fuites de mémoire potentielles,
 * en libérant la mémoire dès qu'elle n'est plus nécessaire
 * (par exemple, en remplaçant l'ancienne version compressée par la nouvelle).
 *
 * @param in Chaîne à compresser
 * @param iteration Nombre d'itérations de compression RLE à appliquer
 * @return Chaîne compressée itérativement selon l'algorithme RLE
 * @throws AlgoException Si l'itération est inférieure à 1
 */
char* RLE_iterations(const char* in, int iteration) {
    if (in == NULL || *in == '\0') return NULL;
    if (iteration < 1) {
        perror("L'itération doit être supérieure à 0");
        exit(EXIT_FAILURE);
    }
    if (iteration == 1) return RLE(in);

    char* compresse = RLE(in);
    char* result = compresse;

    for (int i = 1; i < iteration; i++) {
        char* temp = RLE(compresse);
        free(compresse);
        compresse = temp;
    }

    return result;
}

/**
 * Effectue une décompression RLE (Run-Length Encoding) sur la chaîne d'entrée.
 *
 * La mémoire est allouée dynamiquement pour sortie en fonction de la longueur de in.
 * Cela garantit une utilisation efficace de la mémoire, en ne réservant que l'espace nécessaire
 * pour la sortie décompressée.
 *
 * L'utilisation d'un seul tableau sortie pour stocker la chaîne décompressée optimise l'utilisation
 * de la mémoire par rapport à une approche où plusieurs chaînes seraient allouées pour différentes
 * parties de la décompression.
 *
 * La libération de mémoire est gérée explicitement après usage, évitant ainsi les fuites de mémoire
 * potentielles et assurant une gestion efficace des ressources.
 *
 * @param in Chaîne à décompresser
 * @return Chaîne décompressée selon l'algorithme RLE
 * @throws AlgoException Si la longueur d'entrée est invalide ou un caractère de comptage non numérique est rencontré
 */
char* unRLE(const char* in) {
    if (in == NULL || *in == '\0' || strlen(in) % 2 != 0) {
        perror("Longueur d'entrée invalide");
        exit(EXIT_FAILURE);
    }

    char* sortie = (char*) malloc(strlen(in) * sizeof(char));  // Alloue mémoire pour la sortie
    if (sortie == NULL) {
        perror("Allocation mémoire a échoué");
        exit(EXIT_FAILURE);
    }

    int sortie_index = 0;

    for (int i = 0; i < strlen(in); i += 2) {
        char charCompteur = in[i];
        char caractere = in[i + 1];

        if (!isdigit(charCompteur)) {
            perror("Le caractère de comptage doit être un chiffre");
            exit(EXIT_FAILURE);
        }

        int compteur = charCompteur - '0';  // Convertit le caractère numérique en entier

        if (compteur <= 0) {
            perror("Le compteur doit être supérieur à zéro");
            exit(EXIT_FAILURE);
        }

        if (!(caractere >= 32 && caractere <= 126)) {
            perror("Caractère invalide rencontré");
            exit(EXIT_FAILURE);
        }

        for (int j = 0; j < compteur; j++) {
            sortie[sortie_index++] = caractere;
        }
    }

    sortie[sortie_index] = '\0';  // Ajoute le caractère de fin de chaîne

    return sortie;
}


/**
 * Effectue une décompression RLE (Run-Length Encoding) itérative sur la chaîne d'entrée.
 *
 * Comme pour la compression RLE, l'allocation et la libération de mémoire sont optimisées
 * pour éviter les fuites de mémoire potentielles, en libérant la mémoire dès qu'elle n'est plus
 * nécessaire (par exemple, en remplaçant l'ancienne version décompressée par la nouvelle).
 *
 * @param in Chaîne à décompresser
 * @param iteration Nombre d'itérations de décompression RLE à appliquer
 * @return Chaîne décompressée itérativement selon l'algorithme RLE
 * @throws AlgoException Si l'itération est inférieure à 1 ou la longueur d'entrée est invalide
 */
char* unRLE_iterations(const char* in, int iteration) {
    if (in == NULL || *in == '\0') return NULL;
    if (iteration < 1) {
        perror("L'itération doit être supérieure à 0");
        exit(EXIT_FAILURE);
    }

    char* decompresse = strdup(in);  // Alloue et copie la chaîne d'entrée
    if (decompresse == NULL) {
        perror("Allocation mémoire a échoué");
        exit(EXIT_FAILURE);
    }

    for (int i = 0; i < iteration; i++) {
        char* temp = unRLE(decompresse);
        free(decompresse);
        decompresse = temp;
    }

    return decompresse;
}



int main() {
    // tests
    return 0;
}
