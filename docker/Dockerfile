FROM postgres
ENV POSTGRES_DB=mydb
ENV POSTGRES_USER=myuser
ENV POSTGRES_PASSWORD=myuser
COPY init.sql /docker-entrypoint-initdb.d/
