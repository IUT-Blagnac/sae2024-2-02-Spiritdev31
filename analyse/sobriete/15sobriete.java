package iut.sae.algo;


public class sobriete{
    public static String RLE(String in){
        // Provide your algo here
        return "NotYetImplemented";
    }

    public static String unRLE(String in) throws AlgoException{
        // Provide your algo here
        return "NotYetImplemented";

    }
// Méthode pour compresser la chaîne avec l'algorithme RLE
    public static String RLE(String input, int iteration) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append(iteration).append(c);
        }
        return result.toString();
    }

    // Méthode pour décompresser la chaîne encodée avec l'algorithme RLE
    public static String unRLE(String input, int iteration) throws AlgoException {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < input.length()) {
            // Lire le nombre
            StringBuilder numStr = new StringBuilder();
            while (i < input.length() && Character.isDigit(input.charAt(i))) {
                numStr.append(input.charAt(i));
                i++;
            }
            // Convertir le nombre en entier
            int num;
            try {
                num = Integer.parseInt(numStr.toString());
            } catch (NumberFormatException e) {
                throw new AlgoException("Invalid RLE format");
            }
            // Lire le caractère
            if (i < input.length()) {
                char c = input.charAt(i);
                i++;
                // Ajouter le caractère à la chaîne résultat
                for (int j = 0; j < iteration; j++) {
                    result.append(c);
                }
            } else {
                throw new AlgoException("Invalid RLE format");
            }
        }
        return result.toString();
    }
    
}

