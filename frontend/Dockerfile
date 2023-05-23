# build environment
FROM node:latest
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ENV PATH /usr/src/app/node_modules/.bin:$PATH
COPY package*.json ./
COPY package.json /usr/src/app/package.json
RUN npm install 

COPY . /usr/src/app

CMD ["npm", "start"]
