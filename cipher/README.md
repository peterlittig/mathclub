# Cipher

Encryption-related material for The Math Club podcast.

## Background

The NK23 cipher is a substitution cipher invented by podcast host Noah King when he was in high school. Noah describes the basic mechanism of the cipher in episode 10 of the podcast, "Key Ideas". In a nutshell, here's his description:

1. Start with a message that you'd like to encrypt, for example, "THE CROW FLIES AT MIDNIGHT" (<-- that's how secret guys talk.)
2. Break the message up into bigrams: "TH", "E ", "CR", "OW", " F", "LI", "ES", " A", "T ", "MI", "DN", "IG", and "HT", making sure to keep them in the proper order.
3. Next, replace each bigram with a 3-digit number. A table of mappings between bigrams and numbers should already be selected (and stored away from prying eyes!).
  - Note that since there are `27(27) = 729` possible bigrams, the table will contain 729 different three-digit numbers.
  - We allow leading zeroes, so "001", "002", etc., are possible values.)
  - Using the table, simply replace the bigrams with their corresponding numbers.
  - So, if "TH" maps to "318", "E " maps to "097", "CR" maps to "442", etc., the encrypted message will be something like this:

```318097442606277381400522571003416825111```

This string of digits is the ciphertext associated to the input plaintext. Who on Earth can tell what that means?? Ah! Anyone who knows the table of mappings can use it convert each three-digit number back to the original bigram. Hopefully, the only one who knows the key besides Noah is the intended recipient of the message!

## The Code

This repo contains a few Java programs that let you play around with the NK23 cipher. In order to run the code, you'll need a Java 8 (or later)  compiler/runtime, and the Maven build tool.

## Commands

To create an NK23 key:
1. From the `cipher` directory, run the `keygen` command: `./keygen`
2. This will produce a `key.csv` file. If a file with that name already exists, the keygen command will exit without overwriting it.

To encrypt a plaintext file:
1. Copy a text file containing your secret message into the `cipher` directory.
2. From the `cipher` directory, run the `encrypt` command: `./encrypt <SECRET_FILENAME>`, where instead of `<SECRET_FILENAME>` you type the name of your secret file.
   - If you don't supply a filename, the `encrypt` command will use a default value of `plaintext.txt`.
   - Note: If the `cipher` directory does not contain a `key.csv` file, the `encrypt` command will generate one before performing any encryption.
3. The results of the encryption will be written to a file named `<SECRET_FILENAME>-encrypted`, where `<SECRET_FILENAME>` will be replaced with the name of your secret file.
   - If you didn't supply a filename, the ciphertext file will be named `ciphertext.txt`.

To decrypt a ciphertext file:
1. Start with an encrypted file in the the `cipher` directory. The `key.csv` file used to generate the ciphertext should be in the directory as well.
2. From the `cipher` directory, run the `decrypt` command: `./decrypt <ENCRYPTED_FILENAME>`, where instead of `<ENCRYPTED_FILENAME>` you enter the name of your ciphertext file.
   - If you do not supply a filename, the decrypt `command` will use a default value of `ciphertext.txt`.
3. The decrypted output file will be named `<ENCRYPTED_FILENAME>-decrypted` (or `decrypted.txt` by default.)
