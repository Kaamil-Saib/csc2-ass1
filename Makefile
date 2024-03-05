compile:
    javac -cp ./bin GenericsKbArrayApp.java
    javac -cp ./bin GenericsKbBSTApp.java

clean:
    rm ./bin/*.class

run_array: compile
    java -cp ./bin GenericsKbArrayApp

run_bst: compile
    java -cp ./bin GenericsKbBSTApp
