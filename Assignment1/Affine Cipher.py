# Affine Cipher
def affine_cipher(plaintext, a, b):
    ciphertext = ""
    for char in plaintext:
        if char.isupper():
            ciphertext += chr((a * (ord(char) - ord('A')) + b) % 26 + ord('A'))
        elif char.islower():
            ciphertext += chr((a * (ord(char) - ord('a')) + b) % 26 + ord('a'))
        else:
            ciphertext += char
    return ciphertext

def gcd(x,y):
    while y:
        x,y=y,x%y
    return x  
def mod_reverse(a, m):
    a = a % m
    for x in range(1, m):
        if (a * x) % m == 1:
            return x
    return None

def affine_decrypt(ciphertext,a,b):
    a_inv = mod_reverse(a,26)
    if a_inv is None:
        raise ValueError("a must be coprime to 26 for decryption.")
    plaintext = ""
    for char in ciphertext:
        if char.isupper():
            plaintext += chr((a_inv * (ord(char) - ord('A') - b)) % 26 + ord('A'))
        elif char.islower():
            plaintext += chr((a_inv * (ord(char) - ord('a') - b)) % 26 + ord('a'))
        else:
            plaintext += char
    return plaintext
        
    
print("Affine Cipher")    
plaintext = input("Enter the plaintext: ")
a = int(input("Enter the value of a (must be coprime to 26): "))
b = int(input("Enter the value of b: "))
ciphertext = affine_cipher(plaintext, a, b)
decrypted_text = affine_decrypt(ciphertext, a, b)
print("Ciphertext:",ciphertext)
print("Decrypted text:", decrypted_text)

#OUTPUT
# Enter the plaintext: Hello World
# Enter the value of a (must be coprime to 26): 5
# Enter the value of b: 8
# Ciphertext: Rmjjy Btwjv
# Decrypted text: Hello World
