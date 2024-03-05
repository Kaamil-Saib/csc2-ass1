all:
    @echo "Choose implementation:"
    @echo "1. Array Implementation"
    @echo "2. BST Implementation"
    @read -p "Enter your choice (1 or 2): " choice; \
    if [ "$$choice" -eq "1" ]; then \
        java -cp ./bin GenericsKbArrayApp; \
    elif [ "$$choice" -eq "2" ]; then \
        java -cp ./bin GenericsKbBSTApp; \
    else \
        echo "Invalid choice. Please enter 1 or 2."; \
    fi