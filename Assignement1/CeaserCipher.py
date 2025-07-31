# The code above implements a Caesar cipher for encryption and decryption.
print("Caesar Cipher")
plaintext=input("Enter the plaintext:")
key=int(input("Enter the key:"))

def encrpyt(plaintext,key):
    ciphertext = ""
    for char in plaintext:
        if char.isupper():
            char=chr((ord(char)-ord('A')+key)%26+ord('A'))
        elif char.islower():
            char=chr((ord(char)-ord('a')+key)%26+ord('a'))
        ciphertext += char
    return ciphertext        

def decrypt(ciphertext,key):
    plaintext =""
    for char in ciphertext:
        if char.isupper():
              char = chr(((ord(char) - ord('A') - key + 26) % 26) + ord('A'))
        elif char.islower():
              char = chr(((ord(char) - ord('a') - key + 26) % 26) + ord('a'))   
        plaintext +=char
    return plaintext


ciphertext=encrpyt(plaintext,key)
print("Ciphertext:",ciphertext)

decrypted_text=decrypt(ciphertext,key)
print("Decrypted text:", decrypted_text)

#Enter the plaintext:HELLO
#Enter the key:3
#Ciphertext: KHOOR
#Decrypted text: HELLO

