Generate Private-Public Key pair:
> openssl genrsa -out rootCA.key 2048


Extract Public Key from Key pair:
> openssl rsa -in rootCA.key -pubout -out rootCA.public.key

Creating CSR
> openssl req -new -key rootCA.key -out rootCA.csr


Verify CSR
> openssl req -text -in rootCA.csr -noout -verify

Create a self signed certificate
> openssl x509 -in rootCA.csr -out rootCA.crt -req -signkey rootCA.key -days 365



