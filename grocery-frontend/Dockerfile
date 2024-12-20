# Use the official Node.js image
FROM node:18

# Set the working directory inside the container
WORKDIR /app

# Copy the frontend files into the container
COPY ./package.json ./package-lock.json ./

# Install the dependencies
RUN npm install

# Copy the rest of the Vite app files into the container
COPY ./ ./

# Expose the port Vite runs on
EXPOSE 5173

# Start the Vite development server
CMD ["npm", "run", "dev", "--", "--host"]

