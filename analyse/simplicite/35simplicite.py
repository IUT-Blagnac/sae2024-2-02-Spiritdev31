import unittest

def simplicite(text: str) -> str:
    if not text:
        return ""
    
    result = []
    count = 1
    prev_char = text[0]
    
    for char in text[1:]:
        if char == prev_char:
            count += 1
        else:
            result.append(f"{count}{prev_char}")
            prev_char = char
            count = 1
    
    result.append(f"{count}{prev_char}")
    
    return ''.join(result)

# Exemple d'utilisation
if __name__ == "__main__":
    test_string = "WWWWWWWWWBWWWWWWWWBBBWWWBWWWWWWW"
    compressed = simplicite(test_string)
    print(compressed)  # devrait imprimer "9W1B8W3B3W1B7W"
    unittest.main()

    def test_simplicite(self):
        self.assertEqual("", simplicite(""))
        self.assertEqual("1a1b1c", simplicite("abc"))
        self.assertEqual("1a2b3c", simplicite("abbccc"))
        self.assertEqual("3a1b2a", simplicite("aaabaa"))
        self.assertEqual("1a1A1a", simplicite("aAa"))

    def test_simplicite_recursive(self):
        self.assertEqual("", simplicite("", 1))
        self.assertEqual("", simplicite("", 3))
        self.assertEqual("1a1b1c", simplicite("abc", 1))
        self.assertEqual("1a2b3c", simplicite("abbccc", 1))
        self.assertEqual("3a1b2a", simplicite("aaabaa", 1))
        self.assertEqual("1a1A1a", simplicite("aAa", 1))
        self.assertEqual("111a111b111c", simplicite("abc", 2))
        self.assertEqual("311a311b311c", simplicite("abc", 3))
