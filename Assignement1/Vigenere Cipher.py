#Vigenere Cipher
print("Vigenere Cipher")

def Vigenre_encrypt(plaintext, key):
    ciphertext = ""
    key_length = len(key)
    key_as_int = [ord(i) -ord('A') for i in key.upper()]
    plaintext_int = [ord(i) - ord('A') for i in plaintext.upper() if i.isalpha()]
    for char in plaintext:
        if char.isupper():
            ciphertext += chr((ord(char) - ord('A') + key_as_int[len(ciphertext) % key_length]) % 26 + ord('A'))]))
    
    