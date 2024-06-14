import time
from decimal import Decimal

#Fonction pour ajouter une chaine de caractères (lettre + compteur) à une chaine existante
def ajouter_chaine(chaine, lettre, compteur):
    while compteur > 9:
        chaine += '9' + lettre
        compteur -= 9
    chaine += str(compteur) + lettre
    return chaine

def RLE(donnees):
    #Cas où rien n'est donné
    if not donnees:
        return ""

    #initialisation des variables
    chaine = ""
    compteur = 1
    lettre_precedente = donnees[0]

    #Parcourir la chaine de caractères
    for lettre in donnees[1:]:
        #Si le caractère actuel est le même que le précédent, incrémenter le compteur
        if lettre == lettre_precedente:
            compteur += 1
        #Sinon, ajouter le groupe de caractères précédent à la chaine
        else:
            chaine = ajouter_chaine(chaine, lettre_precedente, compteur)
            #Réinitialiser le compteur
            lettre_precedente = lettre
            compteur = 1

    # Ajouter le dernier groupe de caractères
    chaine = ajouter_chaine(chaine, lettre_precedente, compteur)
    
    return chaine


#Catégorie récursif

def RLE(donnees, depth=1):

    # Si la chaîne est vide ou la profondeur est inférieure à 1, retourne une chaîne vide
    if not donnees or depth < 1:
        return ""
    
    # Applique la première compression RLE à la chaîne
    result = RLE_recursif(donnees)
    
    # Applique les compressions supplémentaires selon la profondeur spécifiée
    for _ in range(1, depth):
        result = RLE_recursif(result)
    
    return result

def RLE_recursif(donnees, chaine="", compteur=1, index=1):

    # Si on arrive à la fin de la chaîne, ajoute le dernier groupe de caractères compressé
    if index == len(donnees):
        return ajouter_chaine(chaine, donnees[index-1], compteur)
    
    # Si le caractère actuel est le même que le précédent, incrémenter le compteur
    if donnees[index] == donnees[index-1]:
        return RLE_recursif(donnees, chaine, compteur+1, index+1)
    else:
        # Si le caractère change, ajoute le groupe de caractères précédent compressé à la chaîne
        chaine = ajouter_chaine(chaine, donnees[index-1], compteur)
        # Puis commence un nouveau compteur pour le nouveau caractère
        return RLE_recursif(donnees, chaine, 1, index+1)












#Fonction pour décompresser une chaine de caractères compressée
def unRLE(donnee):
    # Si la chaîne de caractères est vide, on retourne une chaîne vide
    if not donnee:
        return ""

    # Initialisation de deux chaînes vides, chaine et chiffre
    chaine = ""
    chiffre = ""

    # On parcourt chaque caractère de la chaîne de caractères donnée
    for k in donnee:
        # Si le caractère est un chiffre, on l'ajoute à la chaîne chiffre
        if k.isdigit():
            chiffre += k
        else:
            # Si le caractère n'est pas un chiffre et que la chaîne chiffre n'est pas vide
            if chiffre:
                # On ajoute le caractère k à la chaîne chaine autant de fois que le nombre indiqué par chiffre
                chaine += k * int(chiffre)
                # On réinitialise la chaîne chiffre
                chiffre = ""
            else:
                # Si le caractère n'est pas un chiffre et que la chaîne chiffre est vide
                # On ajoute le caractère k à la chaîne chaine
                chaine += k  # Pour le cas où il n'y a pas de chiffre avant la lettre
    
    # On retourne la chaîne chaine
    return chaine
    
