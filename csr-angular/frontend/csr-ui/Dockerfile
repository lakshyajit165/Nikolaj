FROM node:latest
WORKDIR /app
COPY package.json /app/
RUN npm install
RUN npm install -g @angular/cli
COPY . /app
EXPOSE 4201
CMD ["ng" ,"serve", "--host", "0.0.0.0","--disableHostCheck","true"]