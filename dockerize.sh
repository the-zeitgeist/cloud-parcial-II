#!/bin/bash

gradle :library:build

services=(service-conf service-admin service-register service-gateway service-user service-movie service-showtime service-booking)

for s in "${services[@]}"; do
    echo ${s}
    gradle :${s}:build
    docker build -t taller-cloud-${s} ${s}
    docker run -d -it --network=host --name ${s}_ii taller-cloud-${s}
    sleep 3
done
