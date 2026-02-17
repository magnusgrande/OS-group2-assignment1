# OS-group2-assignment1
A client–server arithmetic calculator for an 'Operating Systems with System Programming'-assignment @ NTNU Ålesund

## Overview

This project implements a client-server arithmetic calculator using Java sockets. It includes:
- **SingleThreadedServer**: Handles one client at a time sequentially
- **MultiThreadedServer**: Handles multiple clients simultaneously (one thread per client)
- **Client**: Sends arithmetic operations to the server
- **TestRunner**: Runs 10 simultaneous client requests to compare server performance

## Supported Operations

- **A** - Addition
- **S** - Subtraction
- **M** - Multiplication
- **D** - Division

## Usage

### Compilation

```bash
javac *.java
```

### Running the Single-Threaded Server

```bash
java SingleThreadedServer
```

The server will start on port **8080**.

### Running the Multi-Threaded Server

```bash
java MultiThreadedServer
```

The server will start on port **8081**.

### Testing with 10 Simultaneous Clients

For Single-Threaded Server:
```bash
java TestRunner single
```

For Multi-Threaded Server:
```bash
java TestRunner multi
```

### Manual Client Usage

```bash
java Client <host> <port> <num1> <operator> <num2>
```

Example:
```bash
java Client localhost 8080 10 A 5
```

## Performance Comparison

The TestRunner executes 10 simultaneous client requests and measures:
- Individual client response times
- Total execution time
- Average time per request

This allows comparison between single-threaded and multi-threaded server performance under concurrent load.

## Expected Output

### Single-Threaded Server
Clients are handled sequentially, so total time is roughly the sum of all individual requests.

### Multi-Threaded Server
Clients are handled concurrently, so total time should be significantly lower than single-threaded approach.

# Contributors

Ida Soldal - @captnsundy

Magnus Grande - @magnusgrande

Maher Maoued - @mahermx

Mona Amundsen - @lordmanbat
