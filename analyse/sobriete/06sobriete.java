package iut.sae.algo;


public class sobriete{

    public static String RLE(String source) {

        if (source == null || source.isEmpty()) {
            return "";
        }
    
        StringBuilder chaineCodee = new StringBuilder();
        int length = source.length();
        int nbCaracteres = 1;
    
        for (int i = 0; i < length; i++) {
            
            while (i + 1 < length && source.charAt(i) == source.charAt(i + 1)) {
                nbCaracteres++;
                i++;
    
                // Gère les cas où le nombre de caractères consécutifs atteint 9
                if (nbCaracteres == 9) {
                    chaineCodee.append("9").append(source.charAt(i));
                    nbCaracteres = 0; 
                }
            }
    
            // Ajoute le nombre d'occurrences et le caractère à la chaîne encodée finale
            chaineCodee.append(nbCaracteres).append(source.charAt(i));
            
            nbCaracteres = 1;
        }
    
        return chaineCodee.toString();
    }
    
        

    public static String RLE(String source, int iteration) throws AlgoException{
        if (iteration < 1) {
            throw new AlgoException("L'itération doit être >= 1");
        }
        
        for (int i = 0; i < iteration; i++) {
            source = RLE(source);
        }
        return source;
    }


    public static String unRLE(String source) throws AlgoException {

        if (source == null || source.isEmpty()) {
            return "";
        }
    
        StringBuilder chaineDecodee = new StringBuilder();
    
        for (int i = 0; i < source.length(); i++) {
           
            char nbCaracteres = source.charAt(i);         
            int nbLettres = nbCaracteres - '0';               
            char lettre = source.charAt(i + 1);
    
            for (int j = 0; j < nbLettres; j++) {
                chaineDecodee.append(lettre);
            }
    
            i++; 
        }
    
        return chaineDecodee.toString();
    }
    
    public static String unRLE(String source, int iteration) throws AlgoException {

        if (iteration < 1) {
            throw new AlgoException("L'itération doit être >= 1");
        }
    
        for (int i = 0; i < iteration; i++) {
            source = unRLE(source); 
        }
    
        return source;
    }
    
    
}

