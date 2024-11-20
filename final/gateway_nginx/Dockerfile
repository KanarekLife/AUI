# syntax=docker/dockerfile:experimental
FROM nginx:1.26-alpine AS final
COPY nginx.conf /etc/nginx/conf.d/default.conf.template
EXPOSE 80
CMD /bin/sh -c "envsubst '\$GATEWAY_GAMEGENRESAPI \$GATEWAY_GAMESAPI' < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'"
