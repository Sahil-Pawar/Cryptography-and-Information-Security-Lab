#Keyword Cipher
def keyword_cipher(plaintext, keyword):
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    keyword = "".join(sorted(set(keyword.upper()), key=keyword.index)) # Remove duplicates while preserving order
    keyword = keyword.upper() + "".join([char for char in alphabet if char not in keyword])
    ciphertext = ""
    for char in plaintext:
        if char.isupper():
            index = ord(char) - ord('A')
            ciphertext += keyword[index]
        elif char.islower():
            index = ord(char) - ord('a')
            ciphertext += keyword[index].lower()
        else:
            ciphertext += char
    return ciphertext

def keyword_decrypt(ciphertext, keyword):
    alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    keyword = "".join(sorted(set(keyword.upper()), key=keyword.index))
    keyword = keyword.upper() + "".join([char for char in alphabet if char not in keyword])
    plaintext = ""
    for char in ciphertext:
        if char.isupper():
            index = keyword.index(char)
            plaintext += chr(index + ord('A'))
        elif char.islower():
            index = keyword.index(char.upper())
            plaintext += chr(index + ord('a'))
        else:
            plaintext += char
    return plaintext

print("Keyword Cipher")
plaintext = input("Enter the plaintext:")
keyword = input("Enter the keyword:")                 
encrypted_text = keyword_cipher(plaintext, keyword)
decrypted_text = keyword_decrypt(encrypted_text,keyword)
print("Ciphertext:", encrypted_text)
print("Decrpypted text:", decrypted_text)
# OUTPUT   
# Enter the plaintext: Hello
# Enter the keyword: Key
# Ciphertext: Fbjjn
# Decrpypted text: Hello
    