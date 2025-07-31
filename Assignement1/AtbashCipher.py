# Atbash Cipher

def atbash_cipher(plaintext):
    ciphertext = ""
    for char in plaintext:
        if char.isupper():
            ciphertext += chr(ord('A') + (ord('Z') - ord(char)))
        elif char.islower():
            ciphertext += chr(ord('a') + (ord('z') - ord(char)))
        else:
            ciphertext += char 
    return ciphertext

print("Atbash Cipher")
plaintext = input("Enter the plaintext: ")
ciphertext = atbash_cipher(plaintext)
print("Ciphertext:", ciphertext)
