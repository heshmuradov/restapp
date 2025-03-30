echo Starting to download postgres and configure RESTAPP database..
docker build -t restapp_db .
echo Setup finished!
pause 
echo Running RESTAPP database..
docker run --name restapp_db -e POSTGRES_PASSWORD=myuser -p 5432:5432 -d restapp_db
pause
