package iut.sae.algo;

public class efficacite {
     public static String RLE(String in){
        if (in.isEmpty()) return "";

        StringBuilder str_compresse= new StringBuilder();
        char chr_lettre=in.charAt(0);
        int it_compter=1;
        for (int i=1; i<in.length();i++){
            if (in.charAt(i)==chr_lettre){
                it_compter++;
            }else{
                str_compresse.append(it_compter).append(chr_lettre);
                chr_lettre=in.charAt(i);
                it_compter=1;
            }
            if(it_compter==10){
                str_compresse.append(9).append(chr_lettre);
                it_compter=1;
            }
        }
        return str_compresse.append(it_compter).append(chr_lettre).toString();
    }

    public static String RLE(String in, int iteration) throws AlgoException{
        if (iteration<1) throw new AlgoException();

        String str_compresse=in;
        for (int i=0; i<iteration;i++){
            str_compresse=RLE(str_compresse);
        }
        return str_compresse;
    }

    public static String unRLE(String in) throws AlgoException{
        if (in.isEmpty()) return "";

        StringBuilder str_decompresse= new StringBuilder();
        for (int i = 0; i < in.length(); i+=2){
            int int_chiffre = Character.getNumericValue(in.charAt(i));
            if ((int_chiffre >= 1) || (int_chiffre <= 9)) {  
                while(int_chiffre-->0) {
                    str_decompresse.append(in.charAt(i+1));
                }
            }
        }
        return str_decompresse.toString();
    }

    public static String unRLE(String in, int iteration) throws AlgoException{
        if (iteration<1) throw new AlgoException();
        if (in.isEmpty()) return "";

        String str_decompresse=in;
        for (int i=0; i<iteration;i++){
            str_decompresse=unRLE(str_decompresse);
        }
        return str_decompresse;
    }
}
