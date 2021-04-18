# Marvel Characters API
This project represents a Marvel Characters REST API based on the [Marvel API](https://developer.marvel.com) written in Java 11.

## Get Started
In order to get started, head to the [Marvel API](https://developer.marvel.com) and create a developer account.
After completing this process, you will have been provided with an API public key, and an API private key to connect to the Marvel API.
Once this repository has been cloned onto your local machine, open a new terminal window in the root directory of the project and run
```bash
$ open ~/.bash_profile
```
This will open your bash profile, which we will use to keep your local environment variables. Add the following export statements to your *~/.bash_profile*
```aidl
export MARVEL_API_PUBLIC_KEY=publicKey
export MARVEL_API_HASH=hash
```
where *publicKey* is the API public key retrieved in the previous step, 
and *hash* is the MD5 hash generated from concatenating 1, your API private key, and your API public key.
For instance, if your private key is 123 and your public key is abc then this will be the MD5 hash generated from 1123abc.
You can generate this hash [here](https://cryptii.com/pipes/md5-hash).
Following this, in order for these changes to take effect, you can close the editor of your *~/.bash_profile* and run the following command
```bash
$ source ~/.bash_profile
```
You can then either run the tests of the project via running
```bash
$ ./gradlew test
```
... or you can run the application server via running
```bash
$ ./gradlew run
```
When running the application server, you can open up any browser and access
[http://localhost:8080/api/v1/characters](http://localhost:8080/api/v1/characters). Once the application has loaded
all the characters from the exernal Marvel API, you should be able to see a list of all the character IDs in this window.
You can also access [http://localhost:8080/api/v1/characters/someid](http://localhost:8080/api/v1/characters/someid), and replace
*someid* with one of the IDs taken from the previous window. This will show you some details about a specific character with the given ID.

