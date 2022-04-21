FROM node:alpine AS node

WORKDIR /app

COPY --chmod="u+x" package.json /app

RUN npm install

FROM clojure

WORKDIR /app
COPY . /app
COPY --from=node /app/node_modules node_modules

RUN ["bash", "bin/install"]
CMD ["bash", "bin/run"]
