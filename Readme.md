I couldnt get my code to run without "GenericsKB.txt" and "GenericsKB-addtional.txt" being outside of src

Makefile contents:
compile:
	javac -d bin src/*.java

clean:
	rm ./bin/*.class

run_array: compile
	java -cp ./bin GenericsKbArrayApp

run_bst: compile
	java -cp ./bin GenericsKbBSTApp
