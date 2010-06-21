
@del *.bak
@del server.jks
@del *.cer
@del *.p12

: ------
: server
: ------
call keytool -genkey -keyalg RSA -dname "cn=localhost,ou=family168,o=www.family168.com,l=china,st=beijing,c=cn" -alias server -keypass password -keystore server.jks -storepass password

: ------
: user
: ------
call keytool -genkey -v -alias user -keyalg RSA -storetype PKCS12 -keystore user.p12 -dname "cn=user,ou=family168,o=www.family168.com,l=china,st=beijing,c=cn" -storepass password -keypass password

call keytool -export -alias user -keystore user.p12 -storetype PKCS12 -storepass password -rfc -file user.cer

call keytool -import -v -file user.cer -keystore server.jks -storepass password

: ------
: import
: ------
call keytool -export -trustcacerts -alias server -file server.cer -keystore server.jks -storepass password

call keytool -import -trustcacerts -alias server -file server.cer -keystore "%JAVA_HOME%/JRE/LIB/SECURITY/CACERTS" -storepass changeit

pause
