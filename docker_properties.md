### Deploying application
to deploy the application run the following commands from the current directory:
>```docker-compose up -d```

##### a temporary option! :
now you need to connect with postgres to creating a table into db
>```psql -h localhost -p 5400 -U postgres```

inside the database bash copy SQL query from .\src\main\resources\sql\create.sql and paste them
>```postgres=#  ${SQL query to creation table}```

press 'enter', you can leave postgres bash.
##### It`s all! Your application is running into docker container in the background.