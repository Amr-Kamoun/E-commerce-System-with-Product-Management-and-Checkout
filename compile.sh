#!/bin/bash

# Create bin directory if it doesn't exist
mkdir -p bin

# Compile all Java files
echo "Compiling Java files..."

find src -name "*.java" > sources.txt
javac -d bin -cp src @sources.txt
rm sources.txt

if [ $? -eq 0 ]; then
    echo "Compilation successful!"
    echo "Running demo..."
    echo "===================="
    cd bin && java ECommerceDemo
else
    echo "Compilation failed!"
    exit 1
fi
