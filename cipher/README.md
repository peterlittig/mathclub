# Cipher

Encryption-related material for The Math Club podcast.

## Background

The NK23 cipher is a substitution cipher invented by podcast host Noah King when he was in high school. Noah describes the basic mechanism of the cipher in episode 10 of the podcast, "Key Ideas". In a nutshell, here's how he described it:

Start with a message that you'd like to encrypt, for example, "THE CROW FLIES AT MIDNIGHT". Break this message up into bigrams: "TH", "E ", "CR", "OW", " F", "LI", "ES", " A", "T ", "MI", "DN", "IG", and "HT". Next replace each of these bigrams with a 3-digit number. A table of the mapping between the bigrams and the numbers should already be selected (and stored away from prying eyes!). Since there are 27(27) = 729 possibly bigrams, the table will contain 729 different three-digit numbers (we allow for leading zeroes, so "001", "002", etc., are possible values.) Using the table, simply replace the bigrams with their corresponding numbers. So, if "TH" maps to "318", "E " maps to "097", "CR" maps to "442", etc., the encrypted message will be something like this:

```318097442606277381400522571003416825111```

This string of digits is the ciphertext associated to the input plaintext. Who on Earth can tell what that means?? Ah! Anyone who knows the table of mappings can use it convert each three-digit number back to the original bigram. Hopefully, the only one who knows the key besides Noah is the intended receipient of the message!

## The Code

This repo contains a few Java programs that let you play around with the NK23 cipher. In order to run the code, you'll need a Java 8 (or later)  compiler/runtime, and the Maven build tool.

## Commands

To create an NK23 key:
1. From the `cipher` directory, run the `keygen` command: `./keygen`
2. This will produce a `key.csv` file. If a file with that name already exists, the keygen command will exit without overwriting it.

To encrypt a plaintext file:
1. Copy a text file called `plaintext.txt` into the `cipher` directory.
2. From the `cipher` directory, run the `encrypt` command: `./encrypt`
   - Note: If the `cipher` directory does not contain a `key.csv` file, the `encrypt` command will generate one before performing any encryption.

To decrypt a ciphertext file:
1. Start with `ciphertext.txt` file in the the `cipher` directory. The `key.csv` file used to generate the ciphertext should be in the directory as well.
2. From the `cipher` directory, run the `decrypt` command: `./decrypt`
