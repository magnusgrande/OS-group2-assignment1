#!/bin/bash

# Performance Comparison Script for Single vs Multi-threaded Server

echo "======================================================================"
echo "Client-Server Arithmetic Calculator Performance Comparison"
echo "======================================================================"
echo ""

# Compile all Java files
echo "Compiling Java files..."
javac *.java
if [ $? -ne 0 ]; then
    echo "Compilation failed!"
    exit 1
fi
echo "Compilation successful!"
echo ""

# Test Single-Threaded Server
echo "======================================================================"
echo "TEST 1: Single-Threaded Server (Port 8080)"
echo "======================================================================"
java SingleThreadedServer &
SERVER1_PID=$!
sleep 2

java TestRunner single
echo ""

kill $SERVER1_PID 2>/dev/null
sleep 1

# Test Multi-Threaded Server
echo "======================================================================"
echo "TEST 2: Multi-Threaded Server (Port 8081)"
echo "======================================================================"
java MultiThreadedServer &
SERVER2_PID=$!
sleep 2

java TestRunner multi
echo ""

kill $SERVER2_PID 2>/dev/null

# Keep the terminal open for review
echo "Tests completed. Press Enter to exit."
read -p "Press Enter to continue..."