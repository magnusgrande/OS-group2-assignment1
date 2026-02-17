# OS-group2-assignment1

A client–server arithmetic calculator for an 'Operating Systems with System Programming'-assignment @ NTNU Ålesund

Please note that while one user has done all the work on the repo, all work was done in-person, as a complete group aided by copilot, on one computer (Mob Programming for greatest possible learning outcome :) )

## Usage

### Quick Start - Run Performance Comparison

The easiest way to test both servers is to use the provided script:

```bash
chmod +x run_comparison.sh
./run_comparison.sh
```

This will automatically compile, start each server, run 10 simultaneous clients, and display the performance comparison.

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

## Contributors

Ida Soldal - @captnsundy

Magnus Grande - @magnusgrande

Maher Maoued - @mahermx

Mona Amundsen - @lordmanbat

Additionally, AI was used to quickly feature-complete the code, so that the group could focus on the OS-relevant parts of the assignment. All code was manually verified by a human.
