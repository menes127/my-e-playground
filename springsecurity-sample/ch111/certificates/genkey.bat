
@del genkey.bat.bak
@del server.jks
@del server.cer

call keytool -genkey -keyalg RSA -dname "cn=localhost,ou=family168,o=www.family168.com,l=china,st=beijing,c=cn" -alias server -keypass password -keystore server.jks -storepass password

call keytool -export -trustcacerts -alias server -file server.cer -keystore  server.jks -storepass password

call keytool -import -trustcacerts -alias server -file server.cer -keystore  D:/apps/jdk1.5.0_15/jre/lib/security/cacerts -storepass password

pause
