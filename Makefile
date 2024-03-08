compile:
	javac -d bin src/*.java

clean:
	rm ./bin/*.class

run_array: compile
	java -cp ./bin GenericsKbArrayApp

run_bst: compile
	java -cp ./bin GenericsKbBSTApp
