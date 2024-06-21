
import unittest


class Algo() :
    def RLE(in_str) : 
        # Provide your algo here
        res = "" 
        count = 1 
        i = 0 
        while i < len(in_str) - 1 :
            if in_str[i] == in_str[i+1] : 
                count+=1
                if count == 10 : 
                    res += "9" + in_str[i] 
                    count = 1 
            else : 
                res+= str(count) + in_str[i] 
                count = 1 
            i+=1
        res += str(count) + in_str[-1]
        return res 


    print(RLE(""))