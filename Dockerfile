From node:alpine

COPY map-server/resources/public/core.js /usr/src/node/map-server.js
COPY map-server/node_modules /usr/src/node/node_modules

WORKDIR /usr/src/node
CMD node map-server.js
