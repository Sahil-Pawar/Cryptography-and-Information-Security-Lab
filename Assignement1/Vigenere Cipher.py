#Vigenere Cipher
print("Vigenere Cipher")

def Vigenre_encrypt(plaintext, key):
    ciphertext = ""
    key_length = len(key)
    key_as_int = [ord(i) -ord('A') for i in key.upper()]
    plaintext_int = [ord(i) - ord('A') for i in plaintext.upper() if i.isalpha()]
    for char in plaintext:
        if char.isupper():
            ciphertext += chr((ord(char) - ord('A') + key_as_int[len(ciphertext) % key_length]) % 26 + ord('A'))
        elif char.islower():
            ciphertext += chr((ord(char) - ord('a') + key_as_int[len(ciphertext) % key_length]) % 26 + ord('a'))
        else:
            ciphertext += char
            
    return ciphertext

def decrypt_vigenere(ciphertext, key):
    plaintext = ""
    key_length = len(key)
    key_as_int = [ord(i) - ord('A') for i in key.upper()]
    ciphertext_int = [ord(i) - ord('A') for i in ciphertext.upper() if i.isalpha()]
    for char in ciphertext:
        if char.isupper():
            plaintext += chr((ord(char) - ord('A') - key_as_int[len(plaintext) % key_length]) % 26 + ord('A'))
        elif char.islower():
            plaintext += chr((ord(char) - ord('a') - key_as_int[len(plaintext) % key_length]) % 26 + ord('a'))
        else:
            plaintext += char
            
    return plaintext
print("Enter the plaintext:")
plaintext = input("Enter the plaintext:")
print("Enter the key:")
key=input("Enter the key:")
encrypt = Vigenre_encrypt(plaintext, key)
print("Encrypted text:", encrypt)

decrypt = decrypt_vigenere(ciphertext=encrypt, key=key)
print("Decrypted text:", decrypt)
             
    