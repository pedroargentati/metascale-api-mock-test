@echo off
docker run --rm -d -p 3333:8080 --name metascale-api-mock-test -e SPRING_DATASOURCE_USERNAME=user -e SPRING_DATASOURCE_PASSWORD=user -e SPRING_DATASOURCE_URL=jdbc:mysql://db:3306/VivoTest --network testeVivoNetwork metascale-api-mock-test