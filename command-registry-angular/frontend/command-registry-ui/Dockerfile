# version - 1 
#stage 1
# FROM node:latest as node
# WORKDIR /command-registry-ui
# COPY . .
# RUN npm install -g @angular/cli
# RUN ng build --prod

# #stage 2
# FROM nginx
# COPY --from=node /command-registry-ui/dist/* /usr/share/nginx/html/
# EXPOSE 4200

#version - 2 
# FROM node:alpine AS builder
# WORKDIR /src/app
# COPY . .
# RUN npm install && \
# npm run build
# FROM nginx:alpine
# COPY --from=builder /src/app/dist/* /usr/share/nginx/html/


# version - 3
# FROM node:11.15.0-alpine
# EXPOSE 4200
# COPY package.json ./
# WORKDIR src/app
# RUN npm install
# COPY . .
# RUN npm run build

# FROM nginx:1.16.0-alpine as prod-stage
# COPY --from=build-step /app/dist/command-registry-ui /usr/share/nginx/html
# EXPOSE 4200
# CMD ["nginx", "-g", "daemon off;"]


FROM node:latest
WORKDIR /app
COPY package.json /app/
RUN npm install
#RUN npm install -g @angular/cli
COPY . .

CMD ["npm" ,"start"," --", "--host", "0.0.0.0","--disableHostCheck","true"]

