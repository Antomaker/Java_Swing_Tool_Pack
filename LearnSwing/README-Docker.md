# LearnSwing Docker Setup

This document explains how to run the LearnSwing Java Swing application using Docker.

## Prerequisites

- Docker installed on your system
- Docker Compose installed
- X11 server running (for GUI display)

## Quick Start

### Using Docker Compose (Recommended)

1. **Build and run the application:**

   ```bash
   docker-compose up --build
   ```

2. **Run in background:**

   ```bash
   docker-compose up -d --build
   ```

3. **Stop the application:**
   ```bash
   docker-compose down
   ```

### Using Docker directly

1. **Build the image:**

   ```bash
   docker build -t learnswing-app .
   ```

2. **Run the container:**
   ```bash
   docker run -it --rm \
     -e DISPLAY=$DISPLAY \
     -v /tmp/.X11-unix:/tmp/.X11-unix:rw \
     -v ~/.Xauthority:/root/.Xauthority:rw \
     --network host \
     --security-opt seccomp:unconfined \
     learnswing-app
   ```

## Configuration

### Environment Variables

- `DISPLAY`: X11 display variable (automatically set from host)

### Volumes

- `/tmp/.X11-unix`: X11 socket for GUI display
- `~/.Xauthority`: X11 authorization file

## Troubleshooting

### GUI Not Displaying

If the application doesn't show up:

1. **Ensure X11 forwarding is enabled:**

   ```bash
   xhost +local:docker
   ```

2. **For Windows with WSL2:**

   - Install VcXsrv or MobaXterm
   - Set DISPLAY variable: `export DISPLAY=$(cat /etc/resolv.conf | grep nameserver | awk '{print $2}'):0.0`

3. **For macOS:**
   - Install XQuartz
   - Allow connections from network clients
   - Set DISPLAY: `export DISPLAY=host.docker.internal:0`

### Permission Issues

If you encounter permission errors:

```bash
sudo chmod 666 /tmp/.X11-unix/*
xhost +SI:localuser:root
```

### Build Issues

- Ensure all source files are present
- Check that Maven dependencies are available
- Verify Java 24 compatibility

## Development

To modify the application:

1. Edit source files in `src/`
2. Rebuild: `docker-compose up --build`
3. The application will restart automatically

## Architecture

- **Base Image:** OpenJDK 24 slim
- **Build Tool:** Maven
- **Main Class:** `retos.learnswing.LearnSwing`
- **GUI Framework:** Java Swing

## File Structure

```
.
├── Dockerfile              # Docker build configuration
├── docker-compose.yml      # Docker Compose configuration
├── pom.xml                 # Maven project configuration
├── src/                    # Java source code
└── README-Docker.md        # This documentation
```
