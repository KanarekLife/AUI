# syntax=docker/dockerfile:experimental
FROM node:23-alpine AS build

ENV PNPM_HOME="/pnpm"
ENV PATH="$PNPM_HOME:$PATH"
ENV NG_CLI_ANALYTICS=ci

RUN corepack enable
WORKDIR /app
COPY . .

RUN --mount=type=cache,id=pnpm,target=/pnpm/store pnpm install --frozen-lockfile
RUN pnpm run build

FROM nginx:1.26-alpine AS final
COPY nginx.conf /etc/nginx/conf.d/default.conf.template
COPY --from=build /app/dist/frontend/browser/ /usr/share/nginx/html
EXPOSE 80
CMD /bin/sh -c "envsubst '\$API_URL' < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf && nginx -g 'daemon off;'"
