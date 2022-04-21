FROM node:alpine AS node
WORKDIR /app
COPY  package.json /app
RUN npm install

FROM clojure:openjdk-17-tools-deps-alpine AS clojue
RUN apk add nodejs
WORKDIR /app
COPY . /app
COPY --from=node /app/node_modules node_modules
RUN ["bash", "bin/install"]
CMD ["bash", "bin/run"]
