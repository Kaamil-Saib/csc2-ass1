echo "Choose an implementation:"
echo "1. Array implementation"
echo "2. BST implementation"
read -rp "Enter your choice: " choice; \
if [ $$choice -eq 1 ]; then \
    java -cp ./bin GenericsKbArrayApp; \
elif [ $$choice -eq 2 ]; then \
    java -cp ./bin GenericsKbBSTApp; \
else \
    echo "Invalid choice"; \
fi
