#!/bin/bash -x
java -Xms1024m -Xmx2048m -Duser.timezone=Europe/Kiev -Dlog.path=/opt/logs/example -Dconfig.path=/opt/srv/config.properties -jar /opt/srv/jersey.grizzly.example.jar
