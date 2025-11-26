# CreateFuture Coaching Service
CreateFuture Coaching Service

## Beginners Set up Guide 
Install IntelliJ and on the main screen connect to the CreateFuture License. 

````
java -version
brew --version
sdk version
brew tap sdkman/tap
brew install sdkman-cli
vi ~/.zshrc 
````
add in the following:
````
export SDKMAN_DIR=$(brew --prefix sdkman-cli)/libexec
[[ -s "${SDKMAN_DIR}/bin/sdkman-init.sh" ]] && source "${SDKMAN_DIR}/bin/sdkman-init.sh"
````
to save the file 
esc then :qw
reload the file (it will look like nothing has happened)
````
source ~/.zshrc
sdk install java 21.0.9-amzn
````
If you run the following in the normal laptop terminal
open /opt/homebrew/opt/sdkman-cli/libexec/candidates/java/ 
the folder location will open and you can drag the path 

In the right of IntelliJ, we have an M icon, 
when we click this we can 'Sync All Maven Projects' and 
'Download Sources and Documentation'. 

Once you have followed the above steps, you should be able to 