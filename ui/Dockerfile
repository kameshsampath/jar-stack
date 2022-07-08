FROM node:lts-alpine
LABEL org.opencontainers.image.source https://github.com/kameshsampath/fruits-app-ui

WORKDIR /app

ENV NODE_ENV production

RUN adduser --system --uid 1001 nextjs

RUN cp -r /app-build/.next/standalone/. ./ \
   && cp -r /app-build/public ./ \
   && cp /app-build/package.json ./package.json \
   && cp -r /app-build/.next/static ./.next/

RUN chown -R 1001:0 /app \
   && chmod -R g+=wrx /app

RUN ls -ltra /app

USER nextjs

EXPOSE 3000

ENV PORT 3000

# Next.js collects completely anonymous telemetry data about general usage.
# Learn more here: https://nextjs.org/telemetry
# Uncomment the following line in case you want to disable telemetry.
# ENV NEXT_TELEMETRY_DISABLED 1

CMD ["yarn", "start"]
