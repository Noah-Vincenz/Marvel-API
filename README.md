# Marvel-API
Note that this project uses Java 11

add the following export statements to your bash_profile
```aidl
export MARVEL_API_PUBLIC_KEY=<publicKey>
export MARVEL_API_HASH=<hash>
```
where *publicKey* is the API public key retrieved via your developer account at https://developer.marvel.com, 
and *hash* is the MD5 hash generated from concatenating 1, your API private key and your API public key.
For instance, if your private key is 123 and your public key is abc then this will be the MD5 hash generated from 1123abc.
You can generate this hash [here](https://cryptii.com/pipes/md5-hash).

Then run source ~/.bash_profile to source the file.