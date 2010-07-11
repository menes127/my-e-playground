//----------------------------------------------------------------------------
Running Tools:
java -cp hsqldb-1.8.0.10.jar org.hsqldb.Server
java -cp hsqldb-1.8.0.10.jar org.hsqldb.util.DatabaseManager
java -cp hsqldb-1.8.0.10.jar org.hsqldb.util.DatabaseManagerSwing
java -cp hsqldb-1.8.0.10.jar org.hsqldb.util.Transfer
java -cp hsqldb-1.8.0.10.jar org.hsqldb.util.QueryTool
java -cp hsqldb-1.8.0.10.jar org.hsqldb.util.SqlTool
//----------------------------------------------------------------------------


//----------------------------------------------------------------------------
1.Server Modes(Hsqldb Server)
2.run the server: [ex] java -cp ../lib/hsqldb.jar org.hsqldb.Server -database.0 file:mydb -dbname.0 xdb
  (1)run the server: java -classpath hsqldb-1.8.0.10.jar org.hsqldb.Server -database.0 file:c:/hsqldb/ch002/test -dbname.0 test -port 9100
  (2)data in path  : file:c:/hsqldb/ch002/    [test.script]
  (3)run the client: java -classpath hsqldb-1.8.0.10.jar org.hsqldb.util.DatabaseManagerSwing
  [import!]
  Database: test
  URL     : jdbc:hsqldb:hsql://localhost:9100/test
//----------------------------------------------------------------------------


        <beans:property name="url" value="jdbc:hsqldb:res:/hsqldb/test"/>
        <beans:property name="url" value="jdbc:hsqldb:hsql://localhost:9100/test"/>
// 例子ch002
cd C:\Documents and Settings\gaomh\.m2\repository\org\hsqldb\hsqldb\1.8.0.10
java -classpath hsqldb-1.8.0.10.jar org.hsqldb.Server -database.0 file:c:/hsqldb/springsecurity3-sample/ch002/test -dbname.0 test -port 9100

cd  C:\Documents and Settings\Administrator\.m2\repository\org\hsqldb\hsqldb\1.8.0.10
java -classpath hsqldb-1.8.0.10.jar org.hsqldb.Server -database.0 file:C:/hsqldb/springsecurity3-sample/ch006/test -dbname.0 test -port 9100
