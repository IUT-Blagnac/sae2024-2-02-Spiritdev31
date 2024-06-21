package iut.sae.algo;//package iut.sae.algo;

public class sobriete{
    public static String RLE(String in){int s=in.length();String r="";for(int i=0;i<s;i++){char l=in.charAt(i);int c=1;while(i+1<s&&in.charAt(i+1)==l){i++;c++;}if(c>9){r=r+"9"+l+(c-9)+l;}else{r=r+c+""+l;}}return r;}

    public static String RLE(String in,int iteration)throws AlgoException{if(iteration==1){return RLE(in);}else{return RLE(RLE(in,iteration-1));}}

    public static String unRLE(String in)throws AlgoException{int s=in.length();String r="";for(int i=0;i<s;i++){char l=in.charAt(i);if(Character.isDigit(l)){int c=Character.getNumericValue(l);l=in.charAt(i + 1);for(int j=0;j<c;j++){r=r+l;}i++;}else{r=r+l;}}return r;}

    public static String unRLE(String in,int iteration)throws AlgoException{if(iteration==1){return unRLE(in);}else{return unRLE(unRLE(in,iteration-1));}}
}