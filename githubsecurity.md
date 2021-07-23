# Securely Commiting to Github

As of Summer 2021, it will no longer be possible to use simple usernames and password combinations to save your work to github.
Instead, you must either use a Personal Access Token for https or use SSH certificate authentication.

## Personal Access Token

A Personal Access Token is really a very long and difficult to remember random password generated for you by github.
This is reasonably secure but you will need to save the token somewhere and if anyone finds it they can access your account. 

This link explains how to generate and use a token
https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure/creating-a-personal-access-token

If you were using a token to clone a repository you would use the command: 
```
git clone https://github.com/username/repo.git
Username: your_username
Password: your_token
```

In theory the windows git client can use the windows credential manager to store an access token but when you are using a shared university computer, managing your credentials through the windows credential manager may not be possible.
In practice the only way to use this will be to copy the token to a file and cut and paste it every time you need to use it. 
This is obviously a security risk if someone accesses your file. 

## SSH Keys
A more secure method is to use personal SSH certificates which have a passphrase.
SSH certificates are created as a private and public pair. 
Only you have the private certificate and this is used to encrypt your credentials when you use SSH to talk to github. 
You install your corresponding public certificate in Github (or any other platform which you want to authenticate yourself to using SSH).

Github will decrypt and validate your authentication credentials using the public key it holds for your account. 
The private certificate is stored on your local machine and the public certificate is pasted into your github account.
If you take the precation of creating a passphrase when you create your certificate pair, even though your public certificate is potentialy accessable on a shared machine it cannot be used without the passphrase which only you know.

Once you have your certificates installed, you can use the following commands to check out repositories
```
git clone git@github.com/username/repo.git
```
To check out your repository you can use the SSH link found in your repository page.

![alt text](../master/docs/images/git3.png "Figure git3.png" )

### using ssh certificates

On a windows machine with GIT installed, you can generate your certificates as follows

1. Right click in an empty folder and select git bash to get a terminal window

![alt text](../master/docs/images/git1.png "Figure git1.png" )

2. generate your certificate using the following command
```
ssh-keygen -t ed25519 -C "your_email@solent.ac.uk"
```
This creates a new ssh key pair, using the provided email as a label.

When you're prompted to "Enter a file in which to save the key," press Enter. 
This accepts the default file location on the university machine  which will be in your user account on the C: drive

```
Enter a file in which to save the key (/c/Users/you/.ssh/id_ed25519):[Press enter]
```
At the prompt, type a secure passphrase.
Make this something you can remember but do not disclose it to anyone - it is effectively your personal password.
```
Enter passphrase (empty for no passphrase): [Type a passphrase]
Enter same passphrase again: [Type passphrase again]
```
This will generate a public and private key pair on your personal account C: drive
```
C:/Users/your-user-id/.ssh/id_ed25519
C:/Users/your-user-id/.ssh/id_ed25519.pub
```
3. Take a copy of these two files - perhaps on your U drive. 

NOTE:  C: drives on the university machines are not persisted or shared between machines. 
There is no way to easily keep your ssh keys associated with your university account.
So you will need to keep a personal copy of these two files and each time you work at the university, copy them into this folder:
``
C:/Users/your-user-id/.ssh/
``

4. Copy your public key (id_ed25519.pub) to github.

Log into your github account and select user settings.
 
There you can select the 'SSH and GPG Keys' tab.

Select 'add new SSH key'

You can then open the id_ed25519.pub key file on your local machine using notepad and copy and paste the contents into github

![alt text](../master/docs/images/git2.png "Figure git2.png" )

3. Test your connection

The following command will test your connection to github using your keys
```
ssh -T git@github.com
Enter passphrase for key '/c/Users/gallenc/.ssh/id_ed25519':
Hi gallenc! You've successfully authenticated, but GitHub does not provide shell access.
```
If this does not succeed, use the v (verbose) option to find out where things have gone wrong. 
(e.g. Your key file may be in the wrong place or wrongly named)
```
ssh -Tv git@github.com
```


For mac or linux users, you can find further instructions here 
https://docs.github.com/en/github/authenticating-to-github/connecting-to-github-with-ssh/adding-a-new-ssh-key-to-your-github-account

Full details on Github secruity are here if your are interested in learning more
https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure








e.g. C:\\Users\.ssh\id_ed25519



you can test using 
ssh -Tv git@github.com

